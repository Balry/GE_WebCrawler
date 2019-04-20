package com.gewebcrawler;

import com.gewebcrawler.json.GSONFileReader;
import com.gewebcrawler.json.Internet;
import com.gewebcrawler.json.Page;

import java.util.ArrayList;
import java.util.List;

class App {
    private static Internet internet;
    private static final List<String> internetAddresses = new ArrayList<>();  //Comprehensive list of addresses in the Internet

    public static void main(String[] args){
        try{

            try {
                GSONFileReader gsonFileReader = new GSONFileReader("reqDoc\\internet_1.json");  //Read JSON File
                internet = gsonFileReader.deserializeInternetJSON();                                  //Deserialize JSON file
            }catch (Exception e) { e.getStackTrace(); }

            //Retrieve all addresses from the internet
            for(Page page: internet.getPages()) {
                internetAddresses.add(page.getAddress());
            }

            Database.getInstance().getUnvisitedLinks().add(internetAddresses.get(internetAddresses.size()-1));      //Select starting page
            Database.getInstance().getUnvisitedLinks().add(internetAddresses.get(0));                               //Select starting page

            Thread webCrawler0 = new WebCrawler(internet, internetAddresses, 0);
            Thread webCrawler1 = new WebCrawler(internet, internetAddresses, 1);

            webCrawler0.start();
            webCrawler1.start();

            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){}

            //Console output
            System.out.println("Success:\n" + Database.getInstance().getSuccess().toString());
            System.out.println("\nSkipped:\n" + Database.getInstance().getSkipped().toString());
            System.out.println("\nError:\n" + Database.getInstance().getError().toString());

        }catch (Exception e){ e.getStackTrace();  System.out.println(e); }
    }
}
