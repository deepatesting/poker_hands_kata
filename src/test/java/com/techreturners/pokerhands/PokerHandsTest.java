package com.techreturners.pokerhands;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PokerHandsTest {

    public String inputStr = " ";
    public String actualScore = " ";

    @Test
    public void testEmptyOutput(){
        //Arrange
        App pokerApp = new App();

        //Act
        actualScore = pokerApp.comparePokerHands(inputStr);

        //Assert
        Assertions.assertEquals(" ", actualScore);
    }

    @Test
    public void testFirstHighCardCheck(){
        //Arrange
        App pokerApp = new App();
        inputStr = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH";

        //Act
        actualScore = pokerApp.comparePokerHands(inputStr);

        //Assert
        Assertions.assertEquals("White wins. - with high card: Ace", actualScore);
    }

    @Test
    public void testSecondHighCardCheck(){
        //Arrange
        App pokerApp = new App();
        inputStr = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH";

        //Act
        actualScore = pokerApp.comparePokerHands(inputStr);

        //Assert
        Assertions.assertEquals("Black wins. - with high card: 9", actualScore);
    }

    @Test
    public void testFullHouseCheck(){
        //Arrange
        App pokerApp = new App();
        inputStr = "Black: 2H 4S 4C 2D 4H White: 2S 8S AS QS 3S";

        //Act
        actualScore = pokerApp.comparePokerHands(inputStr);

        //Assert
        Assertions.assertEquals("Black wins. - with full house: 4", actualScore);
    }

    @Test
    public void testTieCheck(){
        //Arrange
        App pokerApp = new App();
        inputStr = "Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH";

        //Act
        actualScore = pokerApp.comparePokerHands(inputStr);

        //Assert
        Assertions.assertEquals("Tie.", actualScore);
    }
}
