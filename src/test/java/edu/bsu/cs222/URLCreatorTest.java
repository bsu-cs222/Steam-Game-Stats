package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class URLCreatorTest {
    private final KeyHolder keyHolder = new KeyHolder();
    private final URLCreator urlCreator =  new URLCreator();

    @Test
    public void currentPriceDataTest() {
        String expected = String.format("https://api.isthereanydeal.com/v01/game/overview/?key=%s&region=us&country=US&plains=falloutiv&shop=steam&allowed=steam", keyHolder.keyReturner());
        String actual = urlCreator.urlSearch("falloutiv", "steam");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testTransformTitle() {
        String expected = "falloutiv";
        String actual = urlCreator.transformTitle("Fallout 4");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testNumberToRoman() {
        String expected = "vii";
        String actual = urlCreator.numberToRoman(7);
        Assertions.assertEquals(expected, actual);
    }
}
