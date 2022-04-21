package edu.bsu.cs222;

import java.util.Locale;

public class URLCreator {
    public String numberToRoman(int input){
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
        String[] part = numberTitle.split("(?<=\\D)(?=\\d)");
        String romanTitle;
        romanTitle = part[0] + numberToRoman(Integer.parseInt(part[1]));
        return String.format("https://api.isthereanydeal.com/v01/game/overview/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&region=us&country=US&plains=%s&shop=%s&allowed=%s",romanTitle, store, store);
    }
    public String returnsReviews(String title){
        return String.format("https://api.isthereanydeal.com/v01/game/info/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&plains=%s",title);
    }
}
