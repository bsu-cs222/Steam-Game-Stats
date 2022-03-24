package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

public class URLCreatorTest {
    @Test
    public void urlTest() throws MalformedURLException {
        URLCreator urlCreator = new URLCreator();
        String result = urlCreator.urlMaker("Forza");
        Assertions.assertEquals("Forza", result);
    }
}
