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
 * $Header: /cvs/glassfish/admin/mbeanapi-impl/src/java/com/sun/enterprise/management/config/AdminServiceConfigImpl.java,v 1.7 2006/03/09 20:30:36 llc Exp $
 * $Revision: 1.7 $
 * $Date: 2006/03/09 20:30:36 $
 */


package com.sun.enterprise.management.config;

import java.util.Map;

import javax.management.ObjectName;
import com.sun.appserv.management.base.XTypes;
import com.sun.appserv.management.base.Util;

import com.sun.appserv.management.config.JMXConnectorConfig;
import com.sun.appserv.management.config.AdminServiceConfig;

import com.sun.enterprise.management.config.AMXConfigImplBase;
import com.sun.enterprise.management.support.Delegate;

/**
	Configuration for the &lt;admin-service&gt; element.
 */
public final class AdminServiceConfigImpl extends AMXConfigImplBase
	implements ConfigFactoryCallback
{
		public
	AdminServiceConfigImpl( final Delegate delegate )
	{
		super( delegate );
	}  

	/**
	    @deprecated
	 */
		public ObjectName
	createJMXConnectorConfig(
	    final String name,
	    final String address, 
		final int port,
		final String authRealmName,
		final Map<String,String> optional )
	{
	    final String    portString  = "" + port;
	    
	    final JMXConnectorConfig    config  = ((AdminServiceConfig)getSelf()).createJMXConnectorConfig(
                name, address, portString, authRealmName, optional ); 
        return Util.getObjectName( config );
	}
	

}







