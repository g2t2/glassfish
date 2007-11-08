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
 * ConstraintUtils.java        May 20, 2003, 1:19 PM
 *
 */

package com.sun.enterprise.tools.common.validation.constraints;

import java.text.MessageFormat;
import com.sun.enterprise.tools.common.validation.util.BundleReader;


/**
 * ConstraintUtils is an <code>Object</code> providing the Utilities.
 * <code>formatFailureMessage</code> methods, are the utility methods
 * to format the failure messages. These Methods are used to format 
 * failure messages. Method <code>print</code> is the utility method
 * to print this <code>Object</code>
 * 
 * @author  Rajeshwar Patil
 * @version %I%, %G%
 */
class ConstraintUtils {
    /* A class implementation comment can go here. */

    
    /** Creates a new instance of ConstraintUtils */
    ConstraintUtils() {
    }


    /**
    * Prints this <code>Object</code>
    */
    void print() {
        String format = BundleReader.getValue("Name_Value_Pair_Format");//NOI18N
        Object[] arguments = new Object[]{"Constraint", this};          //NOI18N
        System.out.println(MessageFormat.format(format, arguments));
    }


    /**
    * Formats the failure message from the given information.
    * 
    * @param constraint the failed <code>Constraint</code> name
    * @param value the value the <code>constriant</code> failed for
    * @param name the name of the <code>value</code> the 
    * <code>constriant</code> failed for
    *
    * @return the formatted failure message
    */
    String formatFailureMessage(String constraint, String value,
            String name){
        String failureMessage = null;
        if(!((constraint == null) || (constraint.length() == 0) ||
            (value == null) || (name == null) || (name.length() == 0))){

            String format = BundleReader.getValue("MSG_Failure");       //NOI18N
            Object[] arguments = new Object[]{constraint, value, name};

            failureMessage = MessageFormat.format(format, arguments);
        }
        return failureMessage;
    }


    /**
    * Formats the failure message from the given information.
    * 
    * @param constraint the failed <code>Constraint</code> name
    * @param name the name of the element, the <code>constriant</code>
    * failed for
    *
    * @return the formatted failure message
    */
    String formatFailureMessage(String constraint, String name){
        String failureMessage = null;
        if(!((constraint == null) || (constraint.length() == 0) ||
                (name == null) || (name.length() == 0))){

            String format = BundleReader.getValue("MSG_Failure_1");     //NOI18N
            Object[] arguments = new Object[]{constraint, name};

            failureMessage = MessageFormat.format(format, arguments);
        }
        return failureMessage;
    }
}
