package com.sun.s1peqe.webservices.servlet.taxcal;

import java.rmi.RemoteException;
import java.rmi.Remote;


public interface FedTaxIF extends Remote{

	public double getFedTax(double income, double deductions) throws RemoteException;

}
