package com.gewebcrawler;

import com.gewebcrawler.json.Internet;

import java.util.Arrays;
import java.util.List;

//TODO fix nullpointers/IndexOutOfBoundsException conflicts (not running smoothly every time)
public class WebCrawler extends Thread {
    private final Internet internet;
    private final List<String> internetAddresses;  //Comprehensive list of addresses in the Internet
    private Database database = Database.getInstance();
    private final int index;

    public WebCrawler(Internet internet, List<String> internetAddresses, int index ) {
        this.internet = internet;
        this.internetAddresses = internetAddresses;
        this.index = index;
    }

    @Override
    public void run() {
        //TODO optimize if statements
        //Visit new links in order
        while (database.getUnvisitedLinks().size() > 0 && database.getUnvisitedLinks().get(index) != null) {
            //Mark as error if link does not exist in list of Pages in Internet and not in error list
            if (!internetAddresses.contains(database.getUnvisitedLinks().get(0)) && !database.getError().contains(database.getUnvisitedLinks().get(index))){
                database.getError().add(database.getUnvisitedLinks().get(0));
            }
            //Mark as successful if not visited yet and not in error list
            else if(!database.getSuccess().contains(database.getUnvisitedLinks().get(0)) && !database.getError().contains(database.getUnvisitedLinks().get(index))) {
                database.getSuccess().add(database.getUnvisitedLinks().get(0));
                database.getUnvisitedLinks().addAll(Arrays.asList(internet.getPages()[internetAddresses.indexOf(database.getUnvisitedLinks().get(index))].getLinks()));
            }
            //Mark as skipped if not in skipped list and not in error list
            else if(!database.getSkipped().contains(database.getUnvisitedLinks().get(0)) && !database.getError().contains(database.getUnvisitedLinks().get(index))) {
                database.getSkipped().add(database.getUnvisitedLinks().get(0));
            }
            database.getUnvisitedLinks().remove(0);                                       //Remove checked link
        }
    }
}
