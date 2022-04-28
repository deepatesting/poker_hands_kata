package com.techreturners.pokerhands;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {

    private String player1Name = "";
    private String player2Name = "";
    private String [] splitStrings = null;
    ArrayList<String> player1 = new ArrayList<String>();
    ArrayList<String> player2 = new ArrayList<String>();
    LinkedHashMap<String, Integer> deck_map = new LinkedHashMap<String, Integer>();
    LinkedHashMap<String, String> name_map = new LinkedHashMap<String, String>();
    private String[] handOrder = {"high card", "pair", "two pairs", "three of a kind",
                                    "straight", "flush", "full house", "four of a kind", "straight flush" };
    int player1Rank = 0;
    int player2Rank = 0;

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

        if((inputStr == null) || (inputStr == " "))
            return " ";

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
        return playHands(player1, player2);

    }


    public int playPokerHandForAPlayer(ArrayList<String> player) {

        int pairCount = 0;
        int threeOfAKind = 0;
        int fourOfAKind = 0;
        int rank = 0;
        boolean consecutiveOrder = true;

        //Check if cards are consecutive in order before sorting
        List<String> stringWithoutSorting = player.stream()
                .map(x -> x.substring(0,1))
                .collect(Collectors.toList());

        //Sort the cards in increasing order
        Collections.sort(player);

        //Find the count of cards
        long countOfCards = player.stream().distinct().count();

        //Get first card
        String getFirstCard = player.get(0);

        //Check if all 5 cards are in the same suit
        boolean sameSuit = player.stream().allMatch(str -> str.charAt(1) == getFirstCard.charAt(1));
        if(sameSuit) {
            rank = 6;
        }

        //Sorting player cards based on first char e.g [2,3,4,5,6]
        List<String> sortedString = player.stream()
                .map(x -> x.substring(0,1))
                .sorted()
                .collect(Collectors.toList());

        //Map to count number of same deck
        Map<String, Long>  result = sortedString.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //Loop through to find pair counts
        for (long value : result.values()) {
            if (value == 2) {
                pairCount = pairCount + 1;
            }
            if (value == 3) {
                threeOfAKind =  1;
            }
            if (value == 4) {
                fourOfAKind =  1;
            }
        }

        //Find sum of map values pair count
        long sum = result.values().stream().mapToLong(Long::valueOf).sum();

        //Find if cards are in consecutive order
        for (int i = 0; i < stringWithoutSorting.size()-1; i++) {
            if (deck_map.get(stringWithoutSorting.get(i)) != deck_map.get(stringWithoutSorting.get(i+1)) - 1 )  {
                consecutiveOrder =  false;
            }
        }


        //Based on pair count and original count find rank
        if (sum == countOfCards) {
            if ((consecutiveOrder) && (sameSuit)) {
                    rank = 9;
            }
            else if (consecutiveOrder) {
                rank = 5;
            } else if (sameSuit) {
                rank = 6;
            }else if (pairCount == 1 ) {
                if(threeOfAKind == 1)
                    rank = 7;
                else
                    rank = 2;
            }
            else if(pairCount == 2)
                rank = 3;
            else if(threeOfAKind == 1)
                rank = 4;
            else if(fourOfAKind == 1)
                rank = 8;
            else
                rank = 1;
        }

        return rank;

    }

    public String playHands(ArrayList<String> player1, ArrayList<String> player2) {

        String Player1MAX = "";
        String Player2MAX = "";
        String winnerName = "";
        String cardNamePlayer1 = "";
        String cardNamePlayer2 = "";
        String cardName = "";
        String msg = "";

        String firstCharPlay1 = "";
        String firstCharPlay2 = "";

        int highValueForPlayer1;
        int highValueForPlayer2;


        //Get the highest card from both players
        Player1MAX = player1.get(player1.size() - 1);
        Player2MAX = player2.get(player1.size() - 1);


        //Get the next highest if first high is equal
        if (Player1MAX.charAt(0) == Player2MAX.charAt(0)) {
            //Get the next highest
            Player1MAX = player1.get(player1.size() - 2);
            Player2MAX = player2.get(player2.size() - 2);
        }

        //Get first character of max card as string
        firstCharPlay1 = Player1MAX.substring(0, 1);
        firstCharPlay2 = Player2MAX.substring(0, 1);


        //now get value of max card as integer
        highValueForPlayer1 = deck_map.get(firstCharPlay1);
        highValueForPlayer2 = deck_map.get(firstCharPlay2);


        if (name_map.containsKey(firstCharPlay1))
            cardNamePlayer1 = name_map.get(firstCharPlay1);
        else
            cardNamePlayer1 = firstCharPlay1;

        if (name_map.containsKey(firstCharPlay2))
            cardNamePlayer2 = name_map.get(firstCharPlay2);
        else
            cardNamePlayer2 = firstCharPlay2;


        //Call playPokerHand  for each player to find the rank
        player1Rank = playPokerHandForAPlayer(player1);
        player2Rank = playPokerHandForAPlayer(player2);

        if(player1Rank > player2Rank) {
            winnerName = player1Name;
            msg = handOrder[player1Rank-1];
            cardName = cardNamePlayer1;
        } else if (player1Rank <  player2Rank) {
            winnerName = player2Name;
            msg = handOrder[player2Rank-1];
            cardName = cardNamePlayer2;
        } else if (player1Rank ==  player2Rank) {
            msg = handOrder[0];

            if (highValueForPlayer1 > highValueForPlayer2) {
                cardName = cardNamePlayer1;
                winnerName = player1Name;
            } else if (highValueForPlayer1 < highValueForPlayer2) {
                cardName = cardNamePlayer2;
                winnerName = player2Name;
            } else {
                winnerName = null;
            }
        }

        if (winnerName != null) {
            System.out.println(winnerName + " wins. - with " + msg + ": " + cardName);
            return winnerName + " wins. - with " + msg + ": " + cardName;
        }else{
            System.out.println("Tie.");
            return "Tie.";
        }
    }

}

