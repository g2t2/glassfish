package com.sun.s1asdev.jdbc.simplemysql.ejb;

import javax.ejb.*;
import java.rmi.*;

public interface SimpleBMP
    extends EJBObject {
    public boolean test1( int numRuns) throws RemoteException;
    public boolean test2() throws RemoteException;
    public boolean test3() throws RemoteException;
}
