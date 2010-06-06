/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2010 Sun Microsystems, Inc. All rights reserved.
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

package com.sun.enterprise.admin.cli.cluster;

import com.sun.enterprise.admin.cli.CLILogger;
import java.util.*;
import org.jvnet.hk2.annotations.*;
import org.jvnet.hk2.component.*;
import org.glassfish.api.Param;
import org.glassfish.api.admin.*;
import com.sun.enterprise.admin.cli.remote.RemoteCommand;
import com.sun.enterprise.admin.remote.RemoteAdminCommand;
import com.sun.enterprise.universal.i18n.LocalStringsImpl;


/**
 *  This is a local command that calls the primitive remote create-instance to add the
 *  entries in domain.xml and then the primitive local command _create-instance-filesystem
 *  to create the empty directory structure and das.properties
 *
 */
@Service(name = "create-local-instance")
@Scoped(PerLookup.class)
public final class CreateLocalInstanceCommand extends CreateLocalInstanceFilesystemCommand {

    @Param(name = "filesystemonly", optional = true, defaultValue = "false")
    private boolean filesystemOnly = false;

    @Param(name = "config", optional = true)
    private String configName;

    @Param(name = "cluster", optional = true)
    private String clusterName;

    @Param(name = "systemproperties", optional = true, separator = ':')
    private String systemProperties;     // XXX - should it be a Properties?

    private static final String RENDEZVOUS_PROPERTY_NAME = "rendezvousOccurred";
    private String INSTANCE_DOTTED_NAME;
    private String RENDEZVOUS_DOTTED_NAME;
    private boolean _rendezvousOccurred;

    private static final LocalStringsImpl strings =
            new LocalStringsImpl(CreateLocalInstanceCommand.class);

    /**
     */
    @Override
    protected void validate()
            throws CommandException {
        
        if (configName != null && clusterName != null) {
            throw new CommandException(
                    strings.get("ConfigClusterConflict"));
        }

        super.validate();  // instanceName is validated and set in super.validate()
        INSTANCE_DOTTED_NAME = "servers.server." + instanceName;
        RENDEZVOUS_DOTTED_NAME = INSTANCE_DOTTED_NAME + ".property." + RENDEZVOUS_PROPERTY_NAME;

        if (!filesystemOnly) {
            if (!rendezvousWithDAS()) {
                instanceDir.delete();
                throw new CommandException(
                        strings.get("Instance.rendezvousFailed", DASHost, "" + DASPort));
            }

            _rendezvousOccurred = rendezvousOccurred();
            if (_rendezvousOccurred) {
                throw new CommandException(
                        strings.get("Instance.rendezvousAlready", instanceName, DASHost, "" + DASPort));
            }
        }
    }

    /**
     */
    @Override
    protected int executeCommand()
            throws CommandException, CommandValidationException {
        int exitCode = -1;
        
        if (!this.filesystemOnly) {
            if (isRegisteredToDAS()) {
                if (!_rendezvousOccurred) {
                    setRendezvousOccurred("true");
                    _rendezvousOccurred = true;
                }

            } else {
                registerToDAS();
                _rendezvousOccurred = true;
            }
        }
        try {
            exitCode = super.executeCommand();
        } catch (CommandException ce) {
            String msg = "Something went wrong in creating the local filesystem for instance " + instanceName;
            if (ce.getLocalizedMessage() != null) {
                msg = msg + ": " + ce.getLocalizedMessage();
            }
            logger.printError(msg);
            if (!filesystemOnly) {
                setRendezvousOccurred("false");
                _rendezvousOccurred = false;
            }
            throw new CommandException(msg, ce);
        }
        return exitCode;
    }

    private boolean rendezvousWithDAS() {
        try {
            logger.printMessage(strings.get("Instance.rendezvousAttempt", DASHost, "" + DASPort));
            RemoteAdminCommand rac = new RemoteAdminCommand("uptime", DASHost, DASPort, dasIsSecure, "admin", null, logger.getLogger());
            rac.setConnectTimeout(10000);
            ParameterMap map = new ParameterMap();
            rac.executeCommand(map);
            logger.printMessage(strings.get("Instance.rendezvousSuccess", DASHost, "" + DASPort));
            return true;
        } catch (CommandException ex) {
            return false;
        }
    }

    private int registerToDAS() throws CommandException {
        ArrayList<String> argsList = new ArrayList<String>();
        argsList.add(0, "create-instance");
        if (clusterName != null) {
            argsList.add("--cluster");
            argsList.add(clusterName);
        }
        if (configName != null) {
            argsList.add("--config");
            argsList.add(configName);
        }
        if (nodeAgent != null) {
            argsList.add("--nodeagent");
            argsList.add(nodeAgent);
        }
        if (systemProperties != null) {
            argsList.add("--systemproperties");
            argsList.add(systemProperties);
        }
        argsList.add("--properties");
        argsList.add(RENDEZVOUS_PROPERTY_NAME+"=true");
        argsList.add(this.instanceName);

        String[] argsArray = new String[argsList.size()];
        argsArray = argsList.toArray(argsArray);

        RemoteCommand rc = new RemoteCommand("create-instance", this.programOpts, this.env);
        return rc.execute(argsArray);
    }

    private boolean isRegisteredToDAS() {
        boolean isRegistered = false;
        try {
            RemoteCommand rc = new RemoteCommand("get", this.programOpts, this.env);
            int exitCode = rc.execute("get", INSTANCE_DOTTED_NAME);
            if (exitCode == SUCCESS) {
                isRegistered = true;
            }
        } catch (CommandException ex) {
            logger.printDebugMessage("asadmin get " + INSTANCE_DOTTED_NAME + " failed.");
            logger.printDebugMessage(instanceName +" may not be registered yet to DAS.");
            //logger.printExceptionStackTrace(ex);
        }
        return isRegistered;
    }

    private boolean rendezvousOccurred() {
        boolean rendezvousOccurred = false;
        RemoteCommand rc = null;
        try {
            rc = new RemoteCommand("get", this.programOpts, this.env);
            Map<String, String> map = rc.executeAndReturnAttributes("get", RENDEZVOUS_DOTTED_NAME);
            String output = map.get("children");
            String val = output.substring(output.indexOf("=") + 1);
            rendezvousOccurred = Boolean.parseBoolean(val);
            if (CLILogger.isDebug()) {
                logger.printDebugMessage("rendezvousOccurred = " + val + " for instance " + instanceName);
            }
        } catch (CommandException ce) {
            logger.printDebugMessage("Remote command failed:");
            if (rc != null) {
                logger.printDebugMessage(rc.toString());
            }
            logger.printDebugMessage(RENDEZVOUS_PROPERTY_NAME+" property may not be set yet on instance " + instanceName);
            if (ce.getLocalizedMessage() != null) {
                logger.printDebugMessage(ce.getLocalizedMessage());
            }
            //logger.printExceptionStackTrace(ce);
        }
        return rendezvousOccurred;
    }

    private int setRendezvousOccurred(String rendezVal) throws CommandException {
        String dottedName = RENDEZVOUS_DOTTED_NAME + "=" + rendezVal;
        RemoteCommand rc = new RemoteCommand("set", this.programOpts, this.env);
        if (CLILogger.isDebug()) {
            logger.printDebugMessage("Setting rendezvousOccurred to " + rendezVal + " for instance " + instanceName);
            logger.printDebugMessage(rc.toString());
        }
        return rc.execute("set", dottedName);
    }
}
