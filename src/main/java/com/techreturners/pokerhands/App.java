package com.techreturners.pokerhands;

import java.util.*;
import java.util.stream.Stream;

public class App {

    private String player1Name = "";
    private String player2Name = "";
    private String [] splitStrings = null;
    ArrayList<String> player1 = new ArrayList<String>();
    ArrayList<String> player2 = new ArrayList<String>();
    LinkedHashMap<String, Integer> deck_map = new LinkedHashMap<String, Integer>();
    LinkedHashMap<String, String> name_map = new LinkedHashMap<String, String>();
    private String[] deckSuit = {"C", "D", "H", "S"};


    public App(){
        deck_map.put("2", 2);
        deck_map.put("3", 3);
        deck_map.put("4", 4);
        deck_map.put("5", 5);
        deck_map.put("6", 6);
        deck_map.put("7", 7);
        deck_map.put("8", 8);
        deck_map.put("9", 9);
        deck_map.put("T", 10);
        deck_map.put("J", 11);
        deck_map.put("Q", 12);
        deck_map.put("K", 13);
        deck_map.put("A", 14);

        name_map.put("J", "Jack");
        name_map.put("Q", "Queen");
        name_map.put("K", "King");
        name_map.put("A", "Ace");
    }

    public String comparePokerHands(String inputStr){

        splitStrings = inputStr.trim().split("\\s*:|\\s", 15);

        for(int i = 0; i < splitStrings.length; i++) {
            if (splitStrings[i] != "") {
                if (i <= 6) {
                    if(splitStrings[i].length() > 2)
                        player1Name = splitStrings[i];
                    else
                        player1.add(splitStrings[i]);
                } else {
                    if(splitStrings[i].length() > 2)
                        player2Name = splitStrings[i];
                    else
                        player2.add(splitStrings[i]);
                }
            }
        }


        //Call playHands function by passing parsed array list
        playHands(player1, player2);

        //playHandsFlush(player1, player2);

        return " ";
    }

    public void playHandsFlush(ArrayList<String> player1, ArrayList<String> player2){
        String Player1MAX = "";
        String Player2MAX = "";
        String winnerName = "";
        String cardName = "";

        Collections.sort(player1);
        Collections.sort(player2);

        long count = player1.stream().distinct().count();
        long count1 = player2.stream().distinct().count();

        String s = player2.get(0);

        boolean answer = player2.stream().allMatch(str -> str.substring(0,1) == s);
        System.out.println(answer);
    }
    public void playHands(ArrayList<String> player1, ArrayList<String> player2) {

        String Player1MAX = "";
        String Player2MAX = "";
        String winnerName = "";
        String cardName = "";

        Collections.sort(player1);
        Collections.sort(player2);

        long count = player1.stream().distinct().count();
        long count1 = player2.stream().distinct().count();

        System.out.println(count);
        System.out.println(count1);

        if(count == player1.size())
            Player1MAX = player1.get(player1.size()-1);

        if(count1 == player2.size())
            Player2MAX = player2.get(player1.size()-1);


        //Get the next highest if first high is equal
        if(Player1MAX.charAt(0) == Player2MAX.charAt(0)) {
            //Get the next highest
            Player1MAX = player1.get(player1.size()-2);
            Player2MAX = player2.get(player2.size()-2);
            System.out.println(Player1MAX);
        }

        String firstCharPlay1 = Player1MAX.substring(0,1);
        String firstCharPlay2 = Player2MAX.substring(0,1);

        int high1 =  deck_map.get(firstCharPlay1);
        int high2 = deck_map.get(firstCharPlay2);

        if (high1 > high2) {
            if (name_map.containsKey(firstCharPlay1))
                cardName = name_map.get(firstCharPlay1);
            else
                cardName = firstCharPlay1;
            winnerName = player1Name;
        } else {
            if (name_map.containsKey(firstCharPlay2))
                cardName = name_map.get(firstCharPlay2);
            else
                cardName = firstCharPlay2;
            winnerName = player2Name;
        }

        System.out.println(winnerName + " wins. - with high card: " + cardName);

        /*
        Player1MAX = player1.stream()
                .max(Comparator.naturalOrder())
                .map(Object::toString)
                .orElse(null);

        Player2MAX = player2.stream()
                .max(Comparator.naturalOrder())
                .map(Object::toString)
                .orElse(null);

        if (Player1MAX.isPresent())
            System.out.println(Player1MAX.get());

        Optional<String> Player2MAX = player2.stream().max(Comparator.naturalOrder());

        if (Player2MAX.isPresent())
            System.out.println(Player2MAX.get());

        //String MAX = player1.stream().max(Comparator.comparing(String::valueOf)).get();

        for (int i = 0; i < player1.size(); i++) {
            if(player1.get(i).substring(1).equals("D") ) {
                countD = countD + 1;
                pairD.add(player1.get(i));
                System.out.println(pairD);
            }
        }

        for (int i = 0; i < player2.size(); i++) {
            System.out.println(player2.get(i));
        }
*/


    }

}
