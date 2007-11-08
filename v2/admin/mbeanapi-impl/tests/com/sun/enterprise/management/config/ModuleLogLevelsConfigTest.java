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
 * $Header: /cvs/glassfish/admin/mbeanapi-impl/tests/com/sun/enterprise/management/config/ModuleLogLevelsConfigTest.java,v 1.5 2005/12/25 03:41:39 tcfujii Exp $
 * $Revision: 1.5 $
 * $Date: 2005/12/25 03:41:39 $
 */
package com.sun.enterprise.management.config;

import java.util.Map;
import java.util.HashMap;

import javax.management.ObjectName;


import com.sun.enterprise.management.AMXTestBase;
import com.sun.enterprise.management.Capabilities;

import com.sun.appserv.management.config.ModuleLogLevelsConfig;
import com.sun.appserv.management.config.LogLevelValues;


/**
 */
public final class ModuleLogLevelsConfigTest extends AMXTestBase
{
		public
	ModuleLogLevelsConfigTest ()
	{
	}
	
		protected ModuleLogLevelsConfig
	getModuleLogLevelsConfig()
	{
		return getConfigConfig().getLogServiceConfig().getModuleLogLevelsConfig();
	}
	
		public void
	testGetAll()
	{
		final ModuleLogLevelsConfig	config	= getModuleLogLevelsConfig();
		
		final Map<String,String>	all	= config.getAllLevels();
		assert( all.size() == 24 );
	}
	
		public void
	testChangeAll()
	{
		final ModuleLogLevelsConfig	config	= getModuleLogLevelsConfig();
		
		config.changeAll( LogLevelValues.FINE );
		// verify that they were all changed
		 Map<String,String>	all	= config.getAllLevels();
		for( final String value : all.values() )
		{
		    assert( value.equals( LogLevelValues.FINE ) );
		}
		
		config.changeAll( LogLevelValues.INFO );
		// verify that they were all changed
		all	= config.getAllLevels();
		for( final String value : all.values() )
		{
		    assert( value.equals( LogLevelValues.INFO ) );
		}
	}
}


