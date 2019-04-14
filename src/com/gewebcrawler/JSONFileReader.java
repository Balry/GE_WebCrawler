package com.gewebcrawler;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class JSONFileReader {

    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();

        try
        {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("reqDoc/internet_1.json"));
            JSONArray pages = (JSONArray) jsonObject.get("pages");
            //Map pageMap = new HashMap();

            Iterator pagesIter = pages.iterator();
            while(pagesIter.hasNext())
            {

            }



        }
        catch (FileNotFoundException | ParseException e){ e.printStackTrace(); }
        catch (Exception e){ e.printStackTrace(); }
    }

}
