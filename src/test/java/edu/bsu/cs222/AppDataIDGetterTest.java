package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppDataIDGetterTest {
    @Test
    public void AppIDTest (){
        AppDataIDGetter appID = new AppDataIDGetter();
        String result = appID.appIDMaker("Forza 2");
        Assertions.assertEquals("12345",result);
    }
}
