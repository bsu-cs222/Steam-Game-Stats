package edu.bsu.cs222;

public class URLCreator {
    public static String urlDesigner(String input){
        return String.format("https://api.isthereanydeal.com/v01/game/overview/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&region=us&country=US&plains=%s&shop=steam",input);
    }

}
