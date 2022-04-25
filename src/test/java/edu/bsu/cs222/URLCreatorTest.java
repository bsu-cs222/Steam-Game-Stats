package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class URLCreatorTest {
    KeyHolder keyHolder = new KeyHolder();

    @Test
    public void currentPriceDataTest() {
        URLCreator urlCreator =  new URLCreator();
        String expected = String.format("https://api.isthereanydeal.com/v01/game/overview/?key=%s&region=us&country=US&plains=falloutiv&shop=steam&allowed=steam", keyHolder.keyReturner());
        String actual = urlCreator.urlSearch("falloutiv", "steam");
        Assertions.assertEquals(expected, actual);
    }
}
