package edu.bsu.cs222;

public class URLCreator {
    public String urlSearch(String input, String store){
        //TODO put # to roman numeral here             |
        //TODO ask teacher about magic string on this \/
        return String.format("https://api.isthereanydeal.com/v01/game/overview/?key=420d3d4cd304e25e8b0ac4e1a58dfa406283946d&region=us&country=US&plains=%s&shop=%s&allowed=%s",input, store, store);
    }

    public String gameIDGetter(){
        return String.format("");
    }

}
