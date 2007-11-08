package com.sun.s1asdev.jdbc.conval.client;

import javax.naming.*;
import java.rmi.*;
import java.util.*;

import com.sun.s1asdev.jdbc.conval.ejb.SimpleBMPHome;
import com.sun.s1asdev.jdbc.conval.ejb.SimpleBMP;
import com.sun.ejte.ccl.reporter.SimpleReporterAdapter;

public class Client {

    public static void main(String[] args)
            throws Exception {

        SimpleReporterAdapter stat = new
                SimpleReporterAdapter("appserv-tests");
        String testSuite = "jdbcconval ";
        InitialContext ic = new InitialContext();
        Object objRef = ic.lookup("java:comp/env/ejb/SimpleBMPHome");
        SimpleBMPHome convalBMPHome = (SimpleBMPHome)
                javax.rmi.PortableRemoteObject.narrow(objRef, SimpleBMPHome.class);

        SimpleBMP convalBMP = convalBMPHome.create();

        if (args != null && args.length > 0) {
            String param = args[0];

            switch (Integer.parseInt(param)) {
                case 1: {
                    if (convalBMP.test1()) {
                        stat.addStatus("jdbc-conval : test-1 ", stat.PASS);
                    } else {
                        stat.addStatus("jdbc-conval : test-1 ", stat.FAIL);
                    }
                    break;
                }
                case 3: {
                    if (convalBMP.test1()) {
                        stat.addStatus("jdbc-conval : test-3 ", stat.PASS);
                        System.out.println("test-3 returned true as validation is enabled ");
                    } else {
                        stat.addStatus("jdbc-conval : test-3 ", stat.FAIL);
                    }
                    break;
                }
                case 4: {
                    if (convalBMP.test1()) {
                        stat.addStatus("jdbc-conval : test-4 ", stat.PASS);
                        System.out.println("test-4 returned true as validation is enabled ");
                    } else {
                        stat.addStatus("jdbc-conval : test-4 ", stat.FAIL);
                    }
                    break;
                }

                case 2: {
                    try {
                        if(convalBMP.test1()){
                            stat.addStatus("jdbc-conval : test-2 ", stat.FAIL);
                        }else{
                            stat.addStatus("jdbc-conval : test-2 ", stat.PASS);
                            System.out.println("test-2 returned false as validation is not enabled ");
                        }
                    }
                    catch (Exception e) {
                        stat.addStatus("jdbc-conval : test1 ", stat.PASS);
                    }
                    break;
                }
            }
            stat.printSummary("conval tests");
        }
    }
}
