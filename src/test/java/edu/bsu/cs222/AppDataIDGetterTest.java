package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppDataIDGetterTest {
    @Test
    public void priceTest_steamGame() {
        APIDataGetter apiDataGetter = new APIDataGetter();
        Integer result = apiDataGetter.currentPriceData("falloutiv", "steam");
        Assertions.assertEquals(10, result);
    }

    @Test
    public void priceTest_gameNotFound() {
        APIDataGetter apiDataGetter = new APIDataGetter();
        Integer result = apiDataGetter.historicalLowData("randomLetters", "steam");
        Assertions.assertEquals(-1, result);
    }
}
