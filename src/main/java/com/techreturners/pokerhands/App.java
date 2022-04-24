package com.techreturners.pokerhands;

import java.util.ArrayList;
import java.util.Arrays;

public class App {

    private String [] splitStrings = null;
    ArrayList<String> player1 = new ArrayList<String>();
    ArrayList<String> player2 = new ArrayList<String>();


    public App(){}

    public String comparePokerHands(String inputStr){

        splitStrings = inputStr.trim().split("\\s*:|\\s", 15);

        for(int i = 0; i < splitStrings.length; i++) {
            if (splitStrings[i] != "") {
                if (i <= 6) {
                    player1.add(splitStrings[i]);
                } else
                    player2.add(splitStrings[i]);
            }
        }

        for (String str1 : player1) {
            System.out.println(str1);
        }
        for (String str2 : player2) {
            System.out.println(str2);
        }

        return " ";
    }
}
