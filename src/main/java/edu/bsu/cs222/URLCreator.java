package edu.bsu.cs222;

public class URLCreator {
    public String numberToRoman(int input){
        int[] values = {10,9,5,4,1};
        String[] romanLiterals = {"x","ix","v","iv","i"};

        StringBuilder roman = new StringBuilder();

        for(int steps = 0; steps < values.length; steps++) {
            while(input >= values[steps]) {
                input -= values[steps];
                roman.append(romanLiterals[steps]);
            }
        }
        return roman.toString();
    }
    public String urlSearch(String numberTitle, String store) {
        String romanTitle = transformTitle(numberTitle);
        return String.format("https://api.isthereanydeal.com/v01/game/overview/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&region=us&country=US&plains=%s&shop=%s&allowed=%s",romanTitle, store, store);
    }
    public String returnsReviews(String title){
        String romanTitle = transformTitle(title);
        return String.format("https://api.isthereanydeal.com/v01/game/info/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&plains=%s",romanTitle);
    }
    public String transformTitle(String title) {
        StringBuilder urlTitle = new StringBuilder();
        for(int letters = 0;letters<title.length();letters++) {
            char charTitle = title.charAt(letters);
            if (Character.isDigit(charTitle)){
                int characterNumericValue = Character.getNumericValue(charTitle);
                urlTitle.append(numberToRoman(characterNumericValue));
            } else if (Character.isWhitespace(charTitle));
            else {
                urlTitle.append(Character.toLowerCase(charTitle));
            }
        }
        return urlTitle.toString();
    }
}
