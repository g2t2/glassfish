package com.sun.s1peqe.security.integration.bankadmin.daomanager;

import javax.ejb.EntityContext;
import java.util.Collection;
import java.util.logging.*;
import javax.ejb.*;

import com.sun.ejte.ccl.reporter.*;



public abstract class CustomerBean extends EnterpriseBeanLogger implements javax.ejb.EntityBean
{
    AccountLocalHome accountHome=null;
    public javax.naming.Context jndiContext=null;
    Object objref=null;
    
    private static Logger logger = Logger.getLogger("bank.admin");
    private static ConsoleHandler ch = new ConsoleHandler();
    
    public abstract String getCustomerID();
    public abstract void setCustomerID(String id);
    
    public abstract String getCustomerName();
    public abstract void setCustomerName(String name);
    
    //relationship fields
    
    public abstract Collection getAccounts();
    public abstract void setAccounts(Collection account);
    

        //Business methods

    public String ejbCreateCustomer(String id,String name)
    throws javax.ejb.CreateException
    {
        
        toXML("ejbCreateCustomer","Enter");
        //if(ejbContext.isCallerInRole("ADMIN"))
        if(ejbContext.isCallerInRole("Administrator"))
            toXML("ejbCreateCustomer","isCallerInRole: "+"administrator");
        else
            toXML("ejbCreateCustomer","isCallerInRole: "+"NOT IN administrator");
        
        setCustomerID(id);
        setCustomerName(name);
        toXML("ejbCreateCustomer","Created Customer: "+id);
        java.security.Principal principal=ejbContext.getCallerPrincipal();
        toXML("ejbCreateCustomer","getCallerPrincipal: "+principal);
        toXML("ejbCreateCustomer","Exit");
        
        return null;
    }
    
    public boolean TestCallerInRole()
    {
        boolean isCallerInRole=false;
        //if(ejbContext.isCallerInRole("ADMIN"))
        if(ejbContext.isCallerInRole("Administrator"))
        {
            toXML("TestCallerInRole","isCallerInRole: "+"Administrator");
            isCallerInRole=true;
        }
        else
        {
            toXML("TestCallerInRole","isCallerInRole: "+ejbContext.getCallerPrincipal().getName());
            isCallerInRole=false;
        }
        return isCallerInRole;
        
    }

    public void addAccount(AccountDataObject ado)
    {
        toXML("addAccount","Enter");
        try
        {
            java.security.Principal principal=ejbContext.getCallerPrincipal();
            toXML("addAccount","getCallerPrincipal: "+ principal);
            
            if(ejbContext.isCallerInRole("Administrator"))
                toXML("addAccount","isCallerInRole: "+"administrator");
            else
                toXML("addAccount","isCallerInRole: "+"NOT IN administrator");
            
            logger.info("CustomerBean\t Primary key of acct being added\t"+ado.getAccountID());
            AccountLocal accountLocal=accountHome.createAccount(ado);
            getAccounts().add(accountLocal);
            toXML("addAccount","Added account by Calling CMR field-add");
        }
        catch(DuplicateKeyException e)
        {
            logLocalXMLException(e,"Account already exists");
        }
        catch(Throwable e)
        {
           logXMLException((Exception)e,"Exception occured while adding Account");
            throw new EJBException((Exception)e);
        }
        toXML("addAccount","Exit");
    }

    public void ejbPostCreateCustomer(String id,String name)
    throws javax.ejb.CreateException
    {}

    public EntityContext ejbContext;

    public void ejbActivate() {
    }

    public void ejbPassivate() {}

    public void ejbLoad() {}

    public void ejbStore() {}

    public void ejbRemove() {}

    public void setEntityContext(EntityContext ctx) {
        toXML("setEntityContext","ENTER");
        ejbContext=ctx;
        try {
            /*Creating Account Context as there was no other way to get Remote Interface!!");
            addAccount Needs Remote Interface instead of Local,stupud workaround");*/
            jndiContext=new javax.naming.InitialContext();
            toXML("setEntityContext", "got_JNDIContext");
            
            objref=jndiContext.lookup("java:comp/env/ejb/Account");
            toXML("setEntityContext","Looked_up_Account");
            
            accountHome=(AccountLocalHome)javax.rmi.PortableRemoteObject.narrow(objref,AccountLocalHome.class);
            toXML("setEntityContext","Created Account Home");
            
        }
        catch(Throwable e) {
            logXMLException((Exception)e,"SetEntityContext Failed!");
            throw new EJBException((Exception)e);
        }
        toXML("setEntityContext","Exit");
    }

    public void unsetEntityContext() {
        ejbContext=null;
    }

    public EntityContext getEJBContext() {
        return ejbContext;
    }
  }
