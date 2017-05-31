package com.maxt95.core.Professions;
import java.util.ArrayList;

import org.bukkit.entity.Player;


public class EconomyPayout {
	RPPlayer rpPlayer;
	public EconomyPayout() {
		
	}
	double baseRate = 100;
	double mayorRate = 1000;
	double policeOfficerRate = 150;
	double drugDealerRate = 170;
	double gunDealerRate = 170;
	double blackmarketDealerRate = 160;
	double thiefRate = 130;
	double mobsterRate = 140;
	double mobbossRate = 200;
	double hitmanRate = 150;
	double guardRate = 180;
	
	public void dailyPay(ArrayList<RPPlayer> onlinePlayers) {
		for(int i = 0; i < onlinePlayers.size(); i++)
		{
			rpPlayer = onlinePlayers.get(i);
			String profession = rpPlayer.getProfession();
			Player p = rpPlayer.getPlayer();
			if(profession.equals("citizen")) {
				
				//Economy.add
				Main.economy.depositPlayer(p, baseRate);
			}
			else if(profession.equals("mayor")) {
				
				try {
					Main.economy.depositPlayer(p, mayorRate);
				} catch (ArithmeticException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(profession.equals("police")) {
				
				try {
					Main.economy.depositPlayer(p, policeOfficerRate);
				} catch (ArithmeticException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(profession.equals("drugdealer")) {
				
				try {
					Main.economy.depositPlayer(p, drugDealerRate);
				} catch (ArithmeticException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(profession.equals("gundealer")) {
				try {
					Main.economy.depositPlayer(p,  gunDealerRate);
				} catch (ArithmeticException e) {
					e.printStackTrace();
				}
			}
			else if(profession.equals("blackmarketdealer")) {
				try {
					 Main.economy.depositPlayer(p, blackmarketDealerRate);
				}
				catch(ArithmeticException e) {
					
				}
			}
			else if (profession.equals("guard")) {
				try {
					Main.economy.depositPlayer(p, guardRate);
				}
				catch(ArithmeticException e) {
					
				}
				
			}
			else if(profession.equals("thief")) {
				try {
					Main.economy.depositPlayer(p, thiefRate);
				}
				catch(ArithmeticException e) {
					
				}
			}
			else if(profession.equals("mobster")) {
				try {
					Main.economy.depositPlayer(p, mobsterRate);
				}
				catch(ArithmeticException e) {
					
				}
				
			}
			else if(profession.equals("mobboss")) {
				try {
					Main.economy.depositPlayer(p, mobbossRate);
				}
				catch(ArithmeticException e){
					
				}
			}
			else if(profession.equals("hitman")) {
				try {
					Main.economy.depositPlayer(p, hitmanRate);
				}
				catch(ArithmeticException e) {
					
				}
			}
			else {
				
			}
		}
		
	}
}
