package com.techreturners.pokerhands;


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
    public void testFirstInput(){
        //Arrange
        App pokerApp = new App();
        inputStr = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH";

        //Act
        actualScore = pokerApp.comparePokerHands(inputStr);

        //Assert
        Assertions.assertEquals(" ", actualScore);
    }
}
