package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

public class URLCreatorTest {
    @Test
    public void currentPriceDataTest() throws MalformedURLException {
        APIDataGetter currentPrice = new APIDataGetter();
        String result = currentPrice.currentPriceData("Forza");
        Assertions.assertEquals("Forza", result);
    }
}
