/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.enterprise.appclient.jws;

import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

/**
 *
 * @author tjquinn
 */
/**
 *Represents dynamic content in template form.
 *<p>
 *This class also keeps track of the most recent times the object's template
 *was used to generated different content from the previous time.  This 
 *information is used in responding to HTTP HEAD requests.  Java Web Start uses
 *HEAD requests to find out if a document on the server is more recent than
 *the locally cached copy on the client.  If so, then Java Web Start will 
 *request the updated version with a routine GET request. 
 *<p>
 *To avoid incorrectly reporting obsolete cached documents as correct, this
 *class keeps track of when the content generated by the template is different
 *from the previous generation.  
 *<p>
 *The generated content can depend on request-time information
 *(such as command line arguments passed in the query string of the HTTP request).
 *We save and track only a few individual response instances, because the assumption
 *is that requests that carry query strings (which are converted into command line
 *arguments passed to ACC and on through to the app client) are likely to change
 *frequently and not necessarily be reused often.  Keeping a few allows caching
 *the different content resulting from a small number of different argument
 *value settings, but avoids the problems of caching every single response
 *which could become a large memory drain if each request specified a 
 *different set of argument (for instance, one of the arguments could be a
 *timestamp that would change every time).
 */
public class DynamicContent extends Content {

    /** maximum number of instances of content to keep for each template */
    private static final int MAX_INSTANCES = 4;
    
    /** JNLP element to request full permissions - used if the app client jar is signed */
    protected static final String ALL_PERMISSIONS_JNLP_SETTING = "<security><all-permissions/></security>";
    
    /** JNLP element to request no permissions - used if the app client jar is not signed - no perms is the JNLP default */
    protected static final String NO_PERMISSIONS_JNLP_SETTING = "";

    /**
     *the template which will be used at runtime to create the actual response
     *to the HTTP request
     */
    private String template;

    /** the MIME type of the data represented by this DynamicContent instance */
    protected String mimeType;

    /** content instances resulting from previous HTTP GET requests */
    private LinkedList<Instance> instances = new LinkedList<Instance>();
    
    /** records whether this content should request elevated permissions */
    private boolean requiresElevatedPermissions;
    
    /**
     *Returns a new instance of DynamicContent.
     *@param origin the ContentOrigin for the new content instance
     *@param contentKey the content key used to store and retrieve the content
     *@param path the path relative to the subcategory in which this document is addressable
     *@param mimeType the MIME type of data represented by the content generated by this
     *object.
     *@return new DynamicContent object
     */
    public DynamicContent(ContentOrigin origin, String contentKey, String path, String template, String mimeType) {
        this(origin, contentKey, path, template, mimeType, false /* requiresElevatedPermissions */);
    }

    /**
     *Returns a new instance of DynamicContent.
     *@param origin the ContentOrigin for the new content instance
     *@param contentKey the content key used to store and retrieve the content
     *@param path the path relative to the subcategory in which this document is addressable
     *@param mimeType the MIME type of data represented by the content generated by this
     *object.
     *@return new DynamicContent object
     */
    public DynamicContent(ContentOrigin origin, String contentKey, String path, String template, String mimeType, boolean requiresElevatedPermissions) {
        super(origin, contentKey, path);
        this.template = template;
        this.mimeType = mimeType;
        this.requiresElevatedPermissions = requiresElevatedPermissions;
     }

    /**
     *Returns the DynamicContent.Instance for this template corresponding to 
     *the specified substitution token values.  
     *@param tokenValues the name/value pairs to be substituted in the template
     *@param createIfAbsent selects whether a new DynamicContent.Instance should
     *be created for the resulting text if the content text resulting from the
     *substitution is not already cached by this DynamicContent.
     *@return the instance corresponding to the content generated by the tokenValues;
     *null if no such instance already exists for this DynamicContent and createIfAbsent
     *was false.
     */
    public Instance findInstance(Properties tokenValues, boolean createIfAbsent) {
        Instance result = null;
        String textWithPlaceholdersReplaced = Util.replaceTokens(template, tokenValues);
        
        /*
         *Look for an instance with its text matching the just-computed replacement text.
         */
        synchronized (instances) {
            for (Instance i : instances) {
                if (i.textEquals(textWithPlaceholdersReplaced)) {
                    result = i;
                    break;
                }
            }
            if (result == null && createIfAbsent) {
                result = new Instance(textWithPlaceholdersReplaced);
                addInstance(result);
            }
        }
        return result;
    }
    
    /**
     *Adds a new content instance to this dynamic content.  If adding the instance
     *makes the cache too long, discards the oldest instance.
     *@param newInstance the new instance to be added to the cache
     */
    private void addInstance(Instance newInstance) {
        synchronized (instances) {
            instances.addFirst(newInstance);
            if (instances.size() > MAX_INSTANCES) {
                instances.removeLast();
            }
        }
    }
    

    /**
     *Returns the MIME type associated with this content.
     *@return the MIME type for this content
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     *Returns the appropriate JNLP security setting for this content, based on 
     *whether it requires elevated permissions or not.
     *@return the JNLP element string or an empty string if no privs are needed
     */
    public String getJNLPSecuritySetting() {
        return requiresElevatedPermissions ? ALL_PERMISSIONS_JNLP_SETTING : NO_PERMISSIONS_JNLP_SETTING;
    }
    
    /**
     *Returns whether this dynamic content requires elevated permissions.
     *@return true if the related static content does require elevated permissions
     */
    public boolean requiresElevatedPermissions() {
        return requiresElevatedPermissions;
    }
    
    /**
     *Clears the cached instances.
     */
    protected void clearInstances() {
        instances.clear();
    }
    
    /**
     *Returns a string representation of the DynamicContent.
     */
    public String toString() {
        return super.toString() + ", template=" + template + ", MIME type=" + mimeType;// + ", most recent change in generated content=" + timestamp;
    }

    /**
     *Represents the result of substituting a single set of token values into
     *the DynamicContent's template.  
     */
    public class Instance {
        
        /** when this instance was created */
        private Date timestamp;
        
        /** the content of this instance */
        private String text;
        
        /**
         *Creates a new instance of Instance (!) holding the result of a 
         *specific substitution of values for placeholders.
         *@param text the content for this new Instance
         */
        private Instance(String text) {
            this.text = text;
            timestamp = new Date();
        }
        
        /**
         *Compares the specified String to the text content of the Instance.
         *@param other the text to compare to this Instance's text
         *@return whether the text matches or not
         */
        private boolean textEquals(String other) {
            return text.equals(other);
        }
        
        /**
         *Returns the time stamp associated with this content Instance.
         *@return the Date representing when this Instance was created
         */
        public Date getTimestamp() {
            return timestamp;
        }
        
        /**
         *Returns the content associated with this Instance.
         *@return the text content stored in the Instance
         */
        public String getText() {
            return text;
        }
    }
}