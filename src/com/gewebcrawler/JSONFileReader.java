package com.gewebcrawler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONFileReader {

    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();

        try
        {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("reqDoc/internet_1.json"));
            JSONArray pages = (JSONArray) jsonObject.get("pages");
            //Map pageMap = new HashMap();
            System.out.println(" hgj ");

            System.out.println(pages.get(0).toString());

           /* Iterator pagesIter = pages.iterator();
            while(pagesIter.hasNext())
            {

            }*/



        }
        catch (FileNotFoundException | ParseException e){ e.printStackTrace(); }
        catch (Exception e){ e.printStackTrace(); }
    }

}
