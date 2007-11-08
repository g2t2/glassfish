/*
 * Copyright � 2003 Sun Microsystems, Inc.  All rights reserved.  U.S.
 * Government Rights - Commercial software.  Government users are subject
 * to the Sun Microsystems, Inc. standard license agreement and
 * applicable provisions of the FAR and its supplements.  Use is subject
 * to license terms.
 *
 * This distribution may include materials developed by third parties.
 * Sun, Sun Microsystems, the Sun logo, Java and J2EE are trademarks
 * or registered trademarks of Sun Microsystems, Inc. in the U.S. and
 * other countries.
 
 */


package dataregistry;

import java.util.Collection;

import javax.ejb.*;


public interface LocalLineItemHome extends EJBLocalHome {

    public LocalLineItem findByPrimaryKey(LineItemKey aKey)
            throws FinderException;

    public LocalLineItem create(LocalOrder order, int quantity,
            LocalVendorPart vendorPart) throws CreateException;

    public Collection findAll() throws FinderException;
}
