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
 * $Header: /cvs/glassfish/appserv-api/src/java/com/sun/appserv/management/j2ee/J2EEManagementEvent.java,v 1.1 2006/12/02 06:03:39 llc Exp $
 * $Revision: 1.1 $
 * $Date: 2006/12/02 06:03:39 $
 */
 
package com.sun.appserv.management.j2ee;



/**
 */
public interface J2EEManagementEvent
{
	/**
		The name of the managed object that generated this event.
		
 		@return the ObjectName of the object, as a String
	 */
	public String	getSource();
	
	/**
		The time of the event represented as a long, whose value is
		the number of milliseconds since January 1, 1970, 00:00:00.
	 */
	public long			getWhen();
	
	/**
		The sequence number of the event.
		Identifies the position of the event in a stream
		of events. The sequence number provides a means of
		determining the order of sequential events that
		occurred with the same timestamp (within the
		minimum supported unit of time).
	 */
	public long			getSequence();
	
	/**
		The type of the event. State manageable objects generate a
		J2EEEvent object with the type attribute set to "STATE"
		whenever they reach the RUNNING, STOPPED or FAILED states.
	 */
	public String		getType();
	
	/**
		An informational message about the event.
	 */
	public String		getMessage();
}
