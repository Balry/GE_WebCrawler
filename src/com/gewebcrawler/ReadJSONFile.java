package com.gewebcrawler;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadJSONFile {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try
        {
            Object obj = parser.parse(new FileReader("myJSON.json"));
        }
        catch (FileNotFoundException | ParseException e){ e.printStackTrace(); }
        catch (Exception e){ e.printStackTrace(); }
    }

}
