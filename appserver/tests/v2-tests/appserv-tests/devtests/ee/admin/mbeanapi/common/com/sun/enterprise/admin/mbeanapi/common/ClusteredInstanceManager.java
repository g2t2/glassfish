/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
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

package com.sun.enterprise.admin.mbeanapi.common;

import com.sun.appserv.management.config.*;
import com.sun.appserv.management.j2ee.J2EEServer;
import com.sun.appserv.management.util.stringifier.SmartStringifier;

import java.io.IOException;
import java.util.Map;
import java.util.Iterator;

/**
 * @author <a href=mailto:shreedhar.ganapathy@sun.com>Shreedhar Ganapathy</a>
 *         Date: Sep 12, 2004
 * @version $Revision: 1.6 $
 */
public class ClusteredInstanceManager {
    AMXConnector mAsapiConnector;
    ClusteredServerConfig mClusteredServerCfg;
    DomainConfig mDomainConfig;

    public ClusteredInstanceManager(
                final String host,
                final int port,
                final String adminUser,
                final String adminPassword,
                final boolean useTLS)
                    throws IOException
    {
        mAsapiConnector = new AMXConnector(host, port, adminUser, adminPassword, useTLS);
        mDomainConfig = mAsapiConnector.getDomainRoot().getDomainConfig();

    }

    public  ClusteredInstanceManager(final AMXConnector conn)
    {
        mAsapiConnector = conn;
        mDomainConfig = mAsapiConnector.getDomainRoot().getDomainConfig();
    }

    /**
     * creates a standalone instance
     * @param name
     * @param nodeAgentName
     * @param clusterName
     * @param optional
     *
     * @return ClusteredServerConfig
     */
    public ClusteredServerConfig createInstance(
                final String name,
                final String nodeAgentName,
                final String clusterName,
                final java.util.Map optional) throws Exception
    {
        println("creating instance...");
        mClusteredServerCfg = mDomainConfig.createClusteredServerConfig(name,
                                                    clusterName, nodeAgentName,
                                                    optional);
        return mClusteredServerCfg;
    }

    /**
     * removes a standalone instance
     * @param name
     */
    public void deleteInstance(final String name){
        mDomainConfig.removeClusteredServerConfig(name);
    }

    /**
     * Starts a named standalone instance
     * @param name
     * @throws ObjectNotFoundException
     */
    public void startInstance(final String name) throws ObjectNotFoundException {
        final J2EEServer instance = getInstance(name);
        if(instance != null){
            instance.start();
            return;
        }
        throw new ObjectNotFoundException("startInstance: instance "+
                                        name+" was not found by the DAS");
    }


    /**
     * stops a named standalone instance
     * @param name
     */
    public void stopInstance(final String name) throws ObjectNotFoundException {
        final J2EEServer instance = getInstance(name);
        if(instance != null){
            instance.stop();
            return;
        }
        throw new ObjectNotFoundException("stopInstance: instance "+
                                        name + " was not found by the DAS");
    }

    private J2EEServer getInstance(final String name) {
        final Map servers = mAsapiConnector.getDomainRoot().getJ2EEDomain().getServerMap();
        final Iterator iter;
        J2EEServer instance=null;
        String listName;
        for(iter=servers.keySet().iterator();iter.hasNext();){
            if((listName = (String)iter.next()).equals(name)){
                instance = (J2EEServer)servers.get(listName);
                break;
            }
        }
        return instance;
    }

    public void listInstances() {
        final Map servers = mAsapiConnector.getDomainRoot().getJ2EEDomain().getServerMap();
        final Iterator iter;
        for(iter=servers.keySet().iterator();iter.hasNext();){
            println(((J2EEServer)iter.next()).getName());
        }
    }

    public boolean isCreated(final String name){
        final J2EEServer instance = getInstance(name);
        if( instance != null )
            return true;
        return false;
    }

 /*   public static void main(final String [] args){

        final ClusteredInstanceManager cim;
        try {
            cim = new ClusteredInstanceManager(System.getProperty("HOST", "localhost"),
                                                Integer.parseInt(System.getProperty("PORT","8686")),
                                                System.getProperty("ADMIN_USER","admin"),
                                                System.getProperty("ADMIN_PASSWORD","adminadmin"),
                                                Boolean.valueOf(System.getProperty("USE_TLS","false")).booleanValue());
            final String instanceName = args[0];
            try{
                println("*******Creating Instance "+instanceName+".");
                cim.createInstance(instanceName, "testcluster", "iasengsol11.red.iplanet.com", null, null);
            } catch(Exception e){
                final Throwable ex = ExceptionUtil.getRootCause(e);
                if((ex.getMessage()).indexOf("WARNING") < 0){
                    println(ex.getMessage());
                }
            }
            if(cim.isCreated(instanceName)){
                println("*******Instance "+instanceName+" creation verified.");
                callStartInstance(instanceName, cim);
                return;
            }
        } catch (IOException e) {
            println(e.getMessage());
        }
    }

    private static void callStartInstance(final String instanceName, final ClusteredInstanceManager cim) {
        try {
            println("*******Starting Instance "+instanceName+".");
            cim.startInstance(instanceName);
            println("*******Instance "+instanceName+" started.");

        } catch (Exception e1) {
            final Throwable t = ExceptionUtil.getRootCause(e1);
            println("Exception Received:"+t.getClass().getName()+":"+t.getMessage());
        }
    }
  */
    public static void   println( final Object o )
    {
        System.out.println( toString( o ) );
    }

    private static String  toString(final Object o )
    {
        return( SmartStringifier.toString( o ) );
    }

}
