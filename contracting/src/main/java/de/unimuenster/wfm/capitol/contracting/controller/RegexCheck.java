package de.unimuenster.wfm.capitol.contracting.controller;

import java.util.regex.*;
public class RegexCheck {

	public static void main(String[] args)
	{
		System.out.println(returnWithoutTempSuffix("regsdghsgrdfgssdf_Temp"));
		System.out.println(returnWithoutTempSuffix("blub_temp"));
		System.out.println(returnWithoutTempSuffix("blubtempregsdghsgrdfgssdf_Temp"));
		System.out.println(returnWithoutTempSuffix("regs_tempdghsgrdfgssdf_Temp"));
		System.out.println(returnWithoutTempSuffix("regs_tempdghsgrdfgssdf"));
		System.out.println(returnWithoutTempSuffix("abc_temp"));
		
	}
	
	/**
	 * Returns name. If name ends on "_temp", this is cut out.
	 * @param name
	 * @return
	 */
	private static String returnWithoutTempSuffix(String name) {
		String re1="((?:[a-z][a-z]+))";	// Word 1
		String re2="(_)";	// Any Single Character 1
		String re3="(Temp)";	// Word 2

		Pattern p = Pattern.compile(re1+re2+re3,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(name);
		if (m.find())
		{
			return m.group(1);
		}
		return name;
	}

}
