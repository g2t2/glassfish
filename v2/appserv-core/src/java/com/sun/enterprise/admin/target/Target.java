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
 * $Id: Target.java,v 1.3 2005/12/25 04:14:39 tcfujii Exp $
 */

package com.sun.enterprise.admin.target;

//jdk imports
import java.io.Serializable;

//config imports
import com.sun.enterprise.config.ConfigContext;
import com.sun.enterprise.config.ConfigException;

import com.sun.enterprise.util.i18n.StringManager;

import com.sun.enterprise.config.serverbeans.Server;
import com.sun.enterprise.config.serverbeans.Cluster;
import com.sun.enterprise.config.serverbeans.Config;
import com.sun.enterprise.config.serverbeans.NodeAgent;
import com.sun.enterprise.config.serverbeans.ApplicationRef;
import com.sun.enterprise.config.serverbeans.ResourceRef;

public abstract class Target implements Serializable
{
    /**
     * i18n strings manager object
     */
    private static final StringManager strMgr = 
        StringManager.getManager(Target.class);

    private final String        name;
    private final transient ConfigContext cc;

    Target(String name, ConfigContext cc)
    {
        checkArg(name, strMgr.getString("target.name"));
        checkArg(cc, strMgr.getString("target.config_context"));
        this.name   = name;
        this.cc     = cc;
    }

    public String getName()
    {
        return name;
    }

    public abstract TargetType getType();
    public abstract String getConfigRef() throws ConfigException;
    public abstract ConfigTarget getConfigTarget() throws Exception;
    public abstract String getTargetObjectName(String[] tokens);
    
    public abstract Server[] getServers() throws ConfigException;
    public abstract Cluster[] getClusters() throws ConfigException;
    public abstract Config[] getConfigs() throws ConfigException;
    public abstract NodeAgent[] getNodeAgents() throws ConfigException;
    public abstract ApplicationRef[] getApplicationRefs() throws ConfigException;
    public abstract ResourceRef[] getResourceRefs() throws ConfigException;

    protected ConfigContext getConfigContext()
    {
        return cc;
    }

    protected void checkArg(Object o, Object name)
    {
        if (null == o)
        {
            throw new IllegalArgumentException(
                strMgr.getString("target.cant_be_null", name.toString()));
        }
    }

    protected void checkTokens(String[] tokens, int minLen)
    {
        checkArg(tokens, name);
        if (tokens.length < minLen)
        {
            throw new IllegalArgumentException(
                strMgr.getString("target.min_token_length", "" + minLen));
        }
    }
}
