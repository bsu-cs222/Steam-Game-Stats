package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class SteamSearchTest {

    @Test
    public void testParse() throws IOException {
        SteamSearch parser = new SteamSearch();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("");
        String  = parser.parse(testDataStream);
        Assertions.assertEquals("", );
    }
}
