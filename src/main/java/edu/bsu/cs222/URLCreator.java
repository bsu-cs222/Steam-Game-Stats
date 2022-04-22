package edu.bsu.cs222;

import java.util.Locale;

public class URLCreator {
    public String numberToRoman(char charInput){
        int input = Integer.parseInt(String.valueOf(charInput));

        System.out.println("Integer: " + input);
        int[] values = {10,9,5,4,1};
        String[] romanLiterals = {"X","IX","V","IV","I"};

        StringBuilder roman = new StringBuilder();

        for(int i=0;i<values.length;i++) {
            while(input >= values[i]) {
                input -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString().toLowerCase(Locale.ROOT);
    }
    public String urlSearch(String numberTitle, String store) {
        String romanTitle = returnsReviewsWithSpaces(numberTitle);
        return String.format("https://api.isthereanydeal.com/v01/game/overview/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&region=us&country=US&plains=%s&shop=%s&allowed=%s",romanTitle, store, store);
    }
    public String returnsReviews(String title){
        String romanTitle = returnsReviewsWithSpaces(title);
        System.out.println(romanTitle);
        return String.format("https://api.isthereanydeal.com/v01/game/info/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&plains=%s",romanTitle);
    }
    public String returnsReviewsWithSpaces(String title) {
        StringBuilder urlTitle = new StringBuilder();
        for(int i = 0;i<title.length();i++) {
            char c = title.charAt(i);
            System.out.println(c);
            if (Character.isDigit(c)){
                urlTitle.append(numberToRoman(c));
            } else if (Character.isWhitespace(c)) {
            } else {
                urlTitle.append(Character.toLowerCase(c));
            }
        }
        System.out.println(urlTitle);
        return urlTitle.toString();
    }
}
