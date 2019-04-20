package com.gewebcrawler;

import com.gewebcrawler.json.GSONFileReader;
import com.gewebcrawler.json.Internet;
import com.gewebcrawler.json.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO only spans one thread... maybe if json was larger it would span more?
public class WebCrawler extends Thread {
    private Internet internet;
    private static List<String> internetAddresses = new ArrayList<>();  //Comprehensive list of addresses in the Internet
    private Database database = Database.getInstance();

    public WebCrawler() {

        try {
            GSONFileReader gsonFileReader = new GSONFileReader("reqDoc\\internet_1.json");  //Read JSON File
            internet = gsonFileReader.deseraliazeInternetJSON();                                  //Deseraliaze JSON file
        }catch (Exception e) { e.getStackTrace(); }

        //Retrieve all addresses from the internet
        for(Page page: internet.getPages()) {
            internetAddresses.add(page.getAddress());
        }

        database.getUnvisitedLinks().add(internetAddresses.get(internetAddresses.size()-1));      //Select starting page
    }

    @Override
    public void run() {

        //TODO optimize if statements
        //Visit new links in order
        while (database.getUnvisitedLinks().size() > 0) {
            //Mark as error if link does not exist in list of Pages in Internet and not in error list
            if (!internetAddresses.contains(database.getUnvisitedLinks().get(0)) && !database.getError().contains(database.getUnvisitedLinks().get(0))){
                database.getError().add(database.getUnvisitedLinks().get(0));
            }
            //Mark as successful if not visited yet and not in error list
            else if(!database.getSuccess().contains(database.getUnvisitedLinks().get(0)) && !database.getError().contains(database.getUnvisitedLinks().get(0))) {
                database.getSuccess().add(database.getUnvisitedLinks().get(0));
                database.getUnvisitedLinks().addAll(Arrays.asList(internet.getPages()[internetAddresses.indexOf(database.getUnvisitedLinks().get(0))].getLinks()));
            }
            //Mark as skipped if not in skipped list and not in error list
            else if(!database.getSkipped().contains(database.getUnvisitedLinks().get(0)) && !database.getError().contains(database.getUnvisitedLinks().get(0))) {
                database.getSkipped().add(database.getUnvisitedLinks().get(0));
            }
            database.getUnvisitedLinks().remove(0);                                       //Remove checked link
        }
    }
}
