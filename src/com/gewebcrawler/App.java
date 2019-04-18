package com.gewebcrawler;

import com.gewebcrawler.json.GSONFileReader;
import com.gewebcrawler.json.Internet;
import com.gewebcrawler.json.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class App {
    private static Internet internet;
    private static List<String> internetAddresses = new ArrayList<>();  //Comprehensive list of addresses in the Internet
    private static List<String> unvisitedLinks = new LinkedList<>();    //Links retrieved from visited pages
    private static List<String> success = new LinkedList<>();           //Successful page visits
    private static List<String> skipped = new LinkedList<>();           //Skipped page visits (already successful)
    private static List<String> error = new LinkedList<>();             //Error page visits (not in internet)

    public static void main(String[] args){
        try{
            GSONFileReader gsonFileReader = new GSONFileReader("reqDoc\\internet_1.json");  //Read JSON File
            internet = gsonFileReader.deseraliazeInternetJSON();                                  //Deseraliaze JSON file

            //Retrieve all addresses from the internet
            for(Page page: internet.getPages()) {
                internetAddresses.add(page.getAddress());
            }

            unvisitedLinks.add(internetAddresses.get(internetAddresses.size()-1));    //Select starting page

            //TODO optimize if statements
            //Visit new links in order
            while (unvisitedLinks.size() > 0) {
                //Mark as error if link does not exist in list of Pages in Internet and not in error list
                if (!internetAddresses.contains(unvisitedLinks.get(0)) && !error.contains(unvisitedLinks.get(0))){
                    error.add(unvisitedLinks.get(0));
                }
                //Mark as successful if not visited yet and not in error list
                else if(!success.contains(unvisitedLinks.get(0)) && !error.contains(unvisitedLinks.get(0))) {
                    success.add(unvisitedLinks.get(0));
                    unvisitedLinks.addAll(Arrays.asList(internet.getPages()[internetAddresses.indexOf(unvisitedLinks.get(0))].getLinks()));
                }
                //Mark as skipped if not in skipped list and not in error list
                else if(!skipped.contains(unvisitedLinks.get(0)) && !error.contains(unvisitedLinks.get(0))) {
                    skipped.add(unvisitedLinks.get(0));
                }
                unvisitedLinks.remove(0);                                       //Remove checked link
            }

            //Console output
            System.out.println("Success:\n" + success.toString());
            System.out.println("\nSkipped:\n" + skipped.toString());
            System.out.println("\nError:\n" + error.toString());
        }catch (Exception e){ e.getStackTrace(); }
    }
}
