package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class URLCreatorTest {
    Path filePath = Path.of("C:\\Users\\caige\\Downloads\\Java Stuff\\Steam-Game-Stats\\src\\test\\java\\key.txt");
    protected String key = Files.readString(filePath);
    public URLCreatorTest() throws IOException {
    }

    @Test
    public void currentPriceDataTest() throws IOException {
        URLCreator urlCreator =  new URLCreator();
        String expected = String.format("https://api.isthereanydeal.com/v01/game/overview/?key=%s&region=us&country=US&plains=falloutiv&shop=steam&allowed=steam",key);
        String actual = urlCreator.urlSearch("falloutiv", "steam");
        Assertions.assertEquals(expected, actual);
    }
}
