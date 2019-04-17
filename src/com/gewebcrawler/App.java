package com.gewebcrawler;

import com.gewebcrawler.json.GSONFileReader;
import com.gewebcrawler.json.Internet;
import com.gewebcrawler.json.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class App {
    private static Internet internet;
    private static List<Page> queue = new ArrayList<>();
    private static List<String> currentLinks = new ArrayList<>();
    private static List<String> success = new ArrayList<>();
    private static List<String> skipped = new ArrayList<>();
    private static List<String> error = new ArrayList<>();

    public static void main(String[] args){
        try{
            GSONFileReader gsonFileReader = new GSONFileReader();
            internet = gsonFileReader.deseraliazeInternetJSON();

            queue.add(internet.getPages()[internet.getPages().length-1]); //Select starting page
            success.add(queue.get(0).getAddress()); //submit first success YAY!

            //extract links
            currentLinks = Arrays.asList(queue.get(0).getLinks());
            queue.remove(0);

            //travel to new links in order
            while (currentLinks.size() > 0) {
                //add new link to queue or mark as error
                for (int i = 0; i < internet.getPages().length; i++) {
                    Page page = internet.getPages()[i];
                    if (success.contains(page.getAddress()) && !skipped.contains(page.getAddress()))
                        skipped.add(page.getAddress());


                    if (internet.getPages()[0].getAddress().equals(queue.get(0).getLinks()[0]))
                        queue.add(new Page());


                    //queue.add(internet.getPages()[0].getAddress().equals(queue.get(0).getLinks()[0]));
                }

                currentLinks.remove(0);
            }


        }catch (Exception e){ e.getStackTrace(); }
    }
}
