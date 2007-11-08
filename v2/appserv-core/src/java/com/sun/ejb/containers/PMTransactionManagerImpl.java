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

package com.sun.ejb.containers;

import java.util.*;
import javax.transaction.*;
import com.sun.enterprise.*;


/**
 * A "wrapper" implementation of javax.transaction.TransactionManager 
 * used by the container to communicate with the PersistenceManager.
 * The PM can only call getTransaction and getStatus methods.
 * This object is available at the JNDI name "java:pm/TransactionManager".
 */
public class PMTransactionManagerImpl implements TransactionManager, 
						 java.io.Serializable {

    transient private TransactionManager transactionManager = null;

    /**
     * Create a transaction manager instance
     */
    PMTransactionManagerImpl() {}

    /**
     * Obtain the status of the transaction associated with the current thread.
     * 
     * @return The transaction status. If no transaction is associated with 
     *    the current thread, this method returns the Status.NoTransaction 
     *    value.
     */
    public int getStatus() throws SystemException 
    {
	if ( transactionManager == null )
	    transactionManager = Switch.getSwitch().getTransactionManager();
	return transactionManager.getStatus();
    }

    /**
     * Get the transaction object that represents the transaction 
     * context of the calling thread
     */
    public Transaction getTransaction() throws SystemException 
    {
	if ( transactionManager == null )
	    transactionManager = Switch.getSwitch().getTransactionManager();
	Transaction tx = transactionManager.getTransaction();
	if ( tx == null )
	    return null;
	else
	    return new PMTransactionImpl(tx);
    }


    public void begin() 
        throws IllegalStateException, SystemException {

        throw new IllegalStateException("Operation not allowed");
    }

    public void commit() throws RollbackException, 
	HeuristicMixedException, HeuristicRollbackException,
        SecurityException, IllegalStateException, SystemException {

        throw new IllegalStateException("Operation not allowed");
    }

    public void rollback() 
        throws IllegalStateException, SecurityException, SystemException {

        throw new IllegalStateException("Operation not allowed");
    }

    public void setRollbackOnly() 
        throws IllegalStateException, SystemException {

        throw new IllegalStateException("Operation not allowed");
    }

    public void resume(Transaction suspended) throws
        InvalidTransactionException, IllegalStateException, SystemException {

        throw new IllegalStateException("Operation not allowed");
    }

    public Transaction suspend() throws SystemException {
        throw new IllegalStateException("Operation not allowed");
    }

    public void setTransactionTimeout(int seconds)
        throws SystemException {

        throw new IllegalStateException("Operation not allowed");
    }

}

