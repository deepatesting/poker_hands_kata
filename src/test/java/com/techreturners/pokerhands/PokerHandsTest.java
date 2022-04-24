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
}
