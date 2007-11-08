/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sun.s1asdev.deployment.ejb30.ear.xmloverride;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class SlessEJB implements Sless
{
    @RolesAllowed(value={"sunuser"})
    public String hello() {
        System.out.println("In SlessEJB:hello()");
        return "hello" + " " + sc.getCallerPrincipal().getName() + "!";
    }

    @RolesAllowed(value={"j2ee"})
    public String goodMorning() {
        System.out.println("In SlessEJB:goodMorning()");
        return "good morning";
    }

    @RolesAllowed(value={"nobody"})
    public String goodBye() {
        System.out.println("In SlessEJB:goodBye()");
        return "good bye";
    }

    private SessionContext sc;
    @Resource
    private void setEJBContext(SessionContext context) {
        sc = context;
    }
}
