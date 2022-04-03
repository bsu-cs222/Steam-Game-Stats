package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

public class URLCreatorTest {
    @Test
    public void urlTest() throws MalformedURLException {
        APIDataGetter urlCreator = new APIDataGetter();
        String result = urlCreator.currentPriceData("Forza");
        Assertions.assertEquals("Forza", result);
    }
}
