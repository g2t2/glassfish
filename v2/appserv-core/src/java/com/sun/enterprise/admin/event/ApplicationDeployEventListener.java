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

/**
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 *
 * Copyright 2001-2002 by iPlanet/Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 */
package com.sun.enterprise.admin.event;

/**
 * This is the listener interface that should be implemented to handle all
 * application deployment events (deployed, undeployed, redeployed, enabled
 * and disabled).
 */
public interface ApplicationDeployEventListener extends AdminEventListener {

    /**
     * Invoked when a J2EE application is deployed.
     * @param event the application deploy event
     * @throws AdminEventListenerException when the listener is unable to
     *         process the event.
     */
    public void applicationDeployed(ApplicationDeployEvent event)
             throws AdminEventListenerException;

    /**
     * Invoked when a J2EE application is undeployed.
     * @throws AdminEventListenerException when the listener is unable to
     *         process the event.
     */
    public void applicationUndeployed(ApplicationDeployEvent event)
             throws AdminEventListenerException;

    /**
     * Invoked when a J2EE application is redeployed.
     * @throws AdminEventListenerException when the listener is unable to
     *         process the event.
     */
    public void applicationRedeployed(ApplicationDeployEvent event)
             throws AdminEventListenerException;

    /**
     * Invoked when a J2EE application is enabled.
     * @throws AdminEventListenerException when the listener is unable to
     *         process the event.
     */
    public void applicationEnabled(ApplicationDeployEvent event)
             throws AdminEventListenerException;

    /**
     * Invoked when a J2EE application is disabled.
     * @throws AdminEventListenerException when the listener is unable to
     *         process the event.
     */
    public void applicationDisabled(ApplicationDeployEvent event)
             throws AdminEventListenerException;

    /**
     * Invoked when an application reference is created from a
     * server instance (or cluster) to a particular application.
     *
     * @throws AdminEventListenerException when the listener is unable to
     *         process the event.
     */
    public void applicationReferenceAdded(ApplicationDeployEvent event)
            throws AdminEventListenerException;

    /**
     * Invoked when a reference is removed from a
     * server instance (or cluster) to a particular application.
     *
     * @throws AdminEventListenerException when the listener is unable to
     *         process the event.
     */
    public void applicationReferenceRemoved(ApplicationDeployEvent event)
            throws AdminEventListenerException;

}
