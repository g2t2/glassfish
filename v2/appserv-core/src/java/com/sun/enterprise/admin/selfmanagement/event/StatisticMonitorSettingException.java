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
 * StatisticMonitorSettingException.java
 * 
 * Created on July 11, 2005 3:00 PM
 */

package com.sun.enterprise.admin.selfmanagement.event; 


/**
 * Exception thrown by the statistic monitor when a monitor setting becomes invalid while the monitor is running.
 * <P>
 * As the monitor attributes may change at runtime, a check is performed before each observation.
 * If a monitor attribute has become invalid, a monitor setting exception is thrown.
 * Used for JDK version greater than 1.5
 * @author      Sun Microsystems, Inc
 */
public class StatisticMonitorSettingException extends javax.management.JMRuntimeException { 

    /* Serial version */
    private static final long serialVersionUID = -8807913418190202007L;

    /**
     * Default constructor.
     */
    public StatisticMonitorSettingException() {
        super();
    }

    /**
     * Constructor that allows an error message to be specified.
     *
     * @param message The specific error message.
     */
    public StatisticMonitorSettingException(String message) {
        super(message);
    }
}
