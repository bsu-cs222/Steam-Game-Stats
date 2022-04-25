package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsThereADealCallerTest {
    IsThereADealCaller isThereADealCaller = new IsThereADealCaller();

    @Test
    public void testCurrentPrice_Fallout4(){
        int expected = 20;
        int actual = isThereADealCaller.getCurrentPriceData("fallout 4","steam");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testLowestPrice_Fallout4(){
        int expected = 5;
        int actual = isThereADealCaller.getHistoricalLowData("fallout 4" , "steam");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testCurrentPrice_NoGameFound(){
        int expected = -1;
        int actual = isThereADealCaller.getCurrentPriceData("jadhsg", "steam");
        Assertions.assertEquals(expected, actual);
    }

}
