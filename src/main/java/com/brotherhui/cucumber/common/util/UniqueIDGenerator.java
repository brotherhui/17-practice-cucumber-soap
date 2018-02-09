package com.brotherhui.cucumber.common.util;

import java.math.BigInteger;
import java.util.UUID;

public class UniqueIDGenerator {

	/**
	 * 
	 * @return return a string of random document id
	 */
	public static String generateRandomOrderID() {
		String randomXDSUniqueID = "2.22";
		String uuid = UUID.randomUUID().toString().toLowerCase();
		String[] uuidGroup = uuid.split("-");
		for (int i = 0; i < uuidGroup.length; i++) {
			BigInteger number = new BigInteger(uuidGroup[i], 16);
			randomXDSUniqueID += "." + number.toString();
		}

		return randomXDSUniqueID;
	}
	
	public static String generateRandomDispenseID() {
		String randomXDSUniqueID = "2.25";
		String uuid = UUID.randomUUID().toString().toLowerCase();
		String[] uuidGroup = uuid.split("-");
		for (int i = 0; i < uuidGroup.length; i++) {
			BigInteger number = new BigInteger(uuidGroup[i], 16);
			randomXDSUniqueID += "." + number.toString();
		}

		return randomXDSUniqueID;
	}
}
