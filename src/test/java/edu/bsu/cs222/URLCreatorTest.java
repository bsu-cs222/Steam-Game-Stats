package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class URLCreatorTest {
    @Test
    public void currentPriceDataTest() {
        URLCreator urlCreator =  new URLCreator();
        String actual = urlCreator.urlSearch("falloutiv", "steam");
        Assertions.assertEquals("https://api.isthereanydeal.com/v01/game/overview/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&region=us&country=US&plains=falloutiv&shop=steam&allowed=steam", actual);
    }
}
