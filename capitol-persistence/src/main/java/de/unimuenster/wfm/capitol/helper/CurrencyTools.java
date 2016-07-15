package de.unimuenster.wfm.capitol.helper;

import java.text.DecimalFormat;

public class CurrencyTools {
	
	/**
	 * 
	 * @param cents
	 * @return euro,cent-formatting as double
	 */
	public static double getDoubleFromCents(int cents) {
		double centsDouble = (double) cents;
		DecimalFormat df = new DecimalFormat("#.00"); 
		return centsDouble/100;
	}
	
	public static int getCentsFromDouble(double euros) {
		return (int) euros*100;
	}
	
	public static void main(String[] args) {
		System.out.println(getDoubleFromCents(100));
	}
}
