package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class URLCreatorTest {
    @Test
    public void urlTest(){
        URLCreator urlCreator = new URLCreator();
        String result = urlCreator.urlMaker("Forza");
        Assertions.assertEquals("Forza", result);
    }
}
