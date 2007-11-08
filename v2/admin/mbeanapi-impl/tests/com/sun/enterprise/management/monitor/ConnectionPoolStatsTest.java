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
 * $Header: /cvs/glassfish/admin/mbeanapi-impl/tests/com/sun/enterprise/management/monitor/ConnectionPoolStatsTest.java,v 1.4 2005/12/25 03:41:54 tcfujii Exp $
 * $Revision: 1.4 $
 * $Date: 2005/12/25 03:41:54 $
 */
package com.sun.enterprise.management.monitor;

import java.util.Set;
import java.util.Iterator;

import javax.management.j2ee.statistics.CountStatistic;
import javax.management.j2ee.statistics.RangeStatistic;

import com.sun.appserv.management.base.QueryMgr;
import com.sun.appserv.management.monitor.statistics.ConnectionPoolStats;
import com.sun.appserv.management.monitor.MonitoringStats;


import com.sun.enterprise.management.AMXTestBase;
import com.sun.enterprise.management.Capabilities;


abstract class ConnectionPoolStatsTest extends AMXMonitorTestBase
{
    	public
    ConnectionPoolStatsTest()
    {
    }
  	
    
    protected abstract void	nextMonitor( final MonitoringStats ms);
    
  		protected int
  	iterateAllMonitors( final String j2eeType )
  	{
  		final Set	monitors	= getQueryMgr().queryJ2EETypeSet( j2eeType );
  		final Iterator	iter	= monitors.iterator();
  		
  		int	numMonitors	= 0;
  		while ( iter.hasNext() )
  		{
  			final MonitoringStats ms	= (MonitoringStats)iter.next();
  			++numMonitors;
  			nextMonitor( ms );
  		}
  		
  		return numMonitors;
  	}
  	
    /**
    	Verify that every Statistic can be successfully accessed.
     */
    	protected void
    accessAllStatistics( final ConnectionPoolStats s )
    {
	    final RangeStatistic r1	= s.getNumConnUsed();
	    assert( r1 != null );
	    
	    final RangeStatistic r2	= s.getNumConnFree() ;
	    assert( r2 != null );
	    
		final RangeStatistic r3 = s.getConnRequestWaitTime();
	    assert( r3 != null );

	    final CountStatistic c1 = s.getNumConnFailedValidation() ;
	    assert( c1 != null );
	    
	    final CountStatistic c2 = s.getNumConnTimedOut();
	    assert( c2 != null );
	    
		final CountStatistic c3 = s.getWaitQueueLength();
	    assert( c3 != null );
	    
		final CountStatistic c4	= s.getNumConnCreated();
	    assert( c4 != null );
	    
		final CountStatistic c5	= s.getNumConnDestroyed();
	    assert( c5 != null );
		
		//final CountStatistic c6	= s.getNumConnOpened();
	   // assert( c6 != null );
	    
		//final CountStatistic c7	= s.getNumConnClosed();
	   // assert( c7 != null );
	    
		final CountStatistic c8 = s.getAverageConnWaitTime();
	    assert( c8 != null );
    }
 
}






