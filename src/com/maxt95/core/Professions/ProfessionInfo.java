package com.maxt95.core.Professions;

import java.util.ArrayList;

public class ProfessionInfo {
	static ArrayList<String> warrants;
	public void setWarrants(ArrayList<String> warrants) {
		ProfessionInfo.warrants = warrants;
	}
	
	public static ArrayList<String> getWarrants() {
		return warrants;
	}
}
