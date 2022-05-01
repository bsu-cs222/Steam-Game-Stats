package edu.bsu.cs222;

public class URLCreator {
    public String urlSearch(String userInputtedTitle, String store) {
        String romanTitle = transformTitle(userInputtedTitle);
        //Url returns the Overview data from the IsThereADeal Api
        return String.format("https://api.isthereanydeal.com/v01/game/overview/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&region=us&country=US&plains=%s&shop=%s&allowed=%s", romanTitle, store, store);
    }

    public String returnsReviews(String userInputtedTitle) {
        String romanTitle = transformTitle(userInputtedTitle);
        //Url returns the review information from the IsThereADeal Api
        return String.format("https://api.isthereanydeal.com/v01/game/info/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&plains=%s", romanTitle);
    }

    public String transformTitle(String userInputtedTitle) {
        StringBuilder builderTitle = new StringBuilder();
        for (int letter = 0; letter < userInputtedTitle.length(); letter++) {
            char charOfTitle = userInputtedTitle.charAt(letter);
            if (Character.isDigit(charOfTitle)) {
                int characterNumericValue = Character.getNumericValue(charOfTitle);
                builderTitle.append(numberToRoman(characterNumericValue));
            } else if (Character.isWhitespace(charOfTitle)) ;
            else {
                builderTitle.append(Character.toLowerCase(charOfTitle));
            }
        }
        return builderTitle.toString();
    }

    public String numberToRoman(int input) {
        int[] arabicLiterals = {10, 9, 5, 4, 1};
        String[] romanLiterals = {"x", "ix", "v", "iv", "i"};
        StringBuilder roman = new StringBuilder();
        for (int steps = 0; steps < arabicLiterals.length; steps++) {
            while (input >= arabicLiterals[steps]) {
                input -= arabicLiterals[steps];
                roman.append(romanLiterals[steps]);
            }
        }
        return roman.toString();
    }
}
