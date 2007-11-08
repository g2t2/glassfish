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

/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * Use is subject to license terms.
 */

/*
 * HAContainerListener.java
 *
 * Created on May 29, 2002, 12:55 PM
 */

package com.sun.enterprise.ee.web.sessmgmt;

import java.util.logging.Logger;
import com.sun.logging.LogDomains;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import org.apache.catalina.ContainerEvent;
import org.apache.catalina.ContainerListener;
import org.apache.catalina.Context;
import org.apache.catalina.Session;

/**
 *
 * @author  lwhite
 */
public class HAContainerListener implements ContainerListener{
    
    /** Creates a new instance of HAContainerListener */
    public HAContainerListener() {
        super();
        if (_logger == null) {
            _logger = LogDomains.getLogger(LogDomains.WEB_LOGGER);
        }        
    }    
    
    /** Acknowledge the occurrence of the specified event.
     *
     * @param event ContainerEvent that has occurred
     */
    public void containerEvent(ContainerEvent event) {
        if (event.getType().equals("sessionSetAttributeCalledForAdd")) {
            this.processAttributeAddEvent(event);
        } else {
        if (event.getType().equals("sessionSetAttributeCalledForReplace")) {
            this.processAttributeUpdateEvent(event);            
        } else {
        if (event.getType().equals("sessionRemoveAttributeCalled")) {
            this.processAttributeRemoveEvent(event);            
        }}}
    }    

    /** 
     * process the occurrence of the specified attribute add event.
     *
     * @param event ContainerEvent that has occurred
     */    
    private void processAttributeAddEvent(ContainerEvent event) {
        _logger.finest("IN CONTAINER_EVENT for sessionSetAttributeCalledForAdd");
        this.processSessionAttributeEvent(event);     
    }
 
    /** 
     * process the occurrence of the specified attribute update event.
     *
     * @param event ContainerEvent that has occurred
     */     
    private void processAttributeUpdateEvent(ContainerEvent event) {
        _logger.finest("IN CONTAINER_EVENT for sessionSetAttributeCalledForReplace");       
        this.processSessionAttributeEvent(event);     
    } 

    /** 
     * process the occurrence of the specified attribute remove event.
     *
     * @param event ContainerEvent that has occurred
     */     
    private void processAttributeRemoveEvent(ContainerEvent event) {
        _logger.finest("IN CONTAINER_EVENT for sessionRemoveAttributeCalled");
        this.processSessionAttributeEvent(event);                 
    }
    
    /** 
     * process the occurrence of the specified attribute event.
     *
     * @param event ContainerEvent that has occurred
     */     
    private void processSessionAttributeEvent(ContainerEvent event) {
        Context context = (Context) event.getContainer();
        HttpSessionBindingEvent hsbe =
            (HttpSessionBindingEvent) event.getData();

        //name is the name of the attribute
        String name = hsbe.getName();
        //value is the value of the attribute
        Object value = hsbe.getValue();
        HttpSession sess = hsbe.getSession();
        String sessId = null;
        if (sess != null) {
            sessId = sess.getId();
        }
        _logger.finest("ATTRIBUTE_NAME = " + name + ": SESSION_ID = " + sessId); 
        
        if (sess != null) {
            Session session = (Session) sess;
            HAWebEventPersistentManager pMgr = 
                (HAWebEventPersistentManager) session.getManager();
            //using doValveSave is fine here
            pMgr.doValveSave(session);
        }        
    }
    
    /**
     * The logger to use for logging ALL web container related messages.
     */
    private static Logger _logger = null;      
    
}
