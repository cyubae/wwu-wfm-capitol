package de.unimuenster.wfm.capitol.contracting.helper;

import java.util.HashMap;

import de.unimuenster.wfm.capitol.enums.CarType;
import de.unimuenster.wfm.capitol.enums.InsuranceType;

public class EnumMapper {
	
	public static final HashMap<String, CarType> STRING_TO_CARTYPE;
	static {
		STRING_TO_CARTYPE = new HashMap<String, CarType>();
		STRING_TO_CARTYPE.put("mini_car", CarType.MINI_CAR);
		STRING_TO_CARTYPE.put("small_car", CarType.SMALL_CAR);
		STRING_TO_CARTYPE.put("car", CarType.CAR);
		STRING_TO_CARTYPE.put("kombi", CarType.KOMBI);
		STRING_TO_CARTYPE.put("pickup", CarType.KOMBI);
		STRING_TO_CARTYPE.put("van", CarType.VAN);
		STRING_TO_CARTYPE.put("limousine", CarType.LIMOUSINE);
		STRING_TO_CARTYPE.put("truck", CarType.TRUCK);
	}
	
	public static final HashMap<String, InsuranceType> STRING_TO_INSURANCETYPE;
	static {
		STRING_TO_INSURANCETYPE = new HashMap<String, InsuranceType>();
		STRING_TO_INSURANCETYPE.put("total", InsuranceType.TOTAL);
		STRING_TO_INSURANCETYPE.put("partial", InsuranceType.PARTIAL);
		STRING_TO_INSURANCETYPE.put("liability", InsuranceType.LIABILITY);
	}	

}
