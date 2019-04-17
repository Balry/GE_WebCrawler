package com.gewebcrawler;

import com.gewebcrawler.json.Internet;

public class WebCrawler {
    private static Internet internet;

    public static void main(String[] args){
        try{
            GSONFileReader gsonFileReader = new GSONFileReader();
            internet = gsonFileReader.deseraliazeInternetJSON();

            System.out.println(internet.toString());

        }catch (Exception e){ e.getStackTrace(); }
    }
}
