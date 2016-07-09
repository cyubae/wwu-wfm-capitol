package de.unimuenster.wfm.capitol.settlement.helper;

import java.util.HashMap;

import de.unimuenster.wfm.capitol.enums.ClaimDecision;

public class EnumMapper {
		
	//0 = not covered, 1 = partially covered , 2 = covered, 3 = third party covered
	public static final HashMap<ClaimDecision, Integer> CLAIMDECISION_TO_INTEGER;
	static {
		CLAIMDECISION_TO_INTEGER = new HashMap<ClaimDecision, Integer>();
		CLAIMDECISION_TO_INTEGER.put(ClaimDecision.NOT_COVERED, 0);
		CLAIMDECISION_TO_INTEGER.put(ClaimDecision.PARTIALLY_COVERED, 1);
		CLAIMDECISION_TO_INTEGER.put(ClaimDecision.COVERED, 2);
		CLAIMDECISION_TO_INTEGER.put(ClaimDecision.THIRD_PARTY_COVERED, 2);
	}	

}
