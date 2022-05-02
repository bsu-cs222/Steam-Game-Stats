package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class URLCreatorTest {
    private final KeyHolder keyHolder = new KeyHolder();
    private final URLCreator urlCreator =  new URLCreator();

    @Test
    public void testGameURL() {
        String expected = String.format("https://api.isthereanydeal.com/v01/game/overview/?key=%s&region=us&country=US&plains=falloutiv&shop=steam&allowed=steam", keyHolder.getKey());
        String actual = urlCreator.urlSearch("falloutiv", "steam");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testReturnsReviewURL() {
        String expected = String.format("https://api.isthereanydeal.com/v01/game/info/?key=%s&plains=falloutiv",keyHolder.getKey());
        String actual = urlCreator.returnsReviewURL("falloutiv");
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
