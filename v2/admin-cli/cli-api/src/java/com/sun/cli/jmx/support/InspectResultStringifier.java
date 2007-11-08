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
 * $Header: /cvs/glassfish/admin-cli/cli-api/src/java/com/sun/cli/jmx/support/InspectResultStringifier.java,v 1.3 2005/12/25 03:45:47 tcfujii Exp $
 * $Revision: 1.3 $
 * $Date: 2005/12/25 03:45:47 $
 */
 


package com.sun.cli.jmx.support;

import com.sun.cli.util.stringifier.*;
import com.sun.cli.jmx.support.*;

import java.lang.reflect.Array;
import javax.management.*;


public final class InspectResultStringifier implements Stringifier
{
		public
	InspectResultStringifier( )
	{
	}
	
	

		private String
	stringifyArray( Object [] a, Stringifier stringifier)
	{
		String	temp	= "";
		
		if ( Array.getLength( a ) != 0 )
		{
			temp	= "\n" + ArrayStringifier.stringify( a, "\n", stringifier);
		}
		return( temp );
	}

		public String
	stringify( Object o)
	{
		String			result	= "";
		final InspectResult	r	= (InspectResult)o;
	
		final MBeanFeatureInfoStringifierOptions options	= new MBeanFeatureInfoStringifierOptions( r.includeDescription, ",");
		
		result	= result + "--- " + r.objectInstance.getObjectName().toString() + " ---";

		if ( r.summary != null )
		{
			if ( result.length() != 0 )
			{
				result	= result + "\n";
			}

			result	= result + r.summary;
		}
		
		// Do formal terms like "Attributes" need to be I18n?  Probabably not as they are part of a specification.
		if ( r.attrInfo != null )
		{
			result	= result + "\n\n- Attributes -" +
						stringifyArray( r.attrInfo, new MBeanAttributeInfoStringifier(options) );
		}
		
		if ( r.operationsInfo != null )
		{
			result	= result + "\n\n- Operations -" +
						stringifyArray( r.operationsInfo, new MBeanOperationInfoStringifier(options) );
		}
		
		if ( r.constructorsInfo != null )
		{
			result	= result + "\n\n- Constructors -" +
						stringifyArray( r.constructorsInfo, new MBeanConstructorInfoStringifier(options) );
		}
		
		if ( r.notificationsInfo != null )
		{
			result	= result + "\n\n- Notifications -" + 
						stringifyArray( r.notificationsInfo, new MBeanNotificationInfoStringifier(options) );
		}
		
		return( result );
			
	}
}



