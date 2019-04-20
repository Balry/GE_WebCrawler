package com.gewebcrawler;

import java.util.LinkedList;
import java.util.List;

public class Database {
    private static Database databaseInstance = null;
    private volatile List<String> unvisitedLinks = new LinkedList<>();    //Links retrieved from visited pages
    private volatile List<String> success = new LinkedList<>();           //Successful page visits
    private volatile List<String> skipped = new LinkedList<>();           //Skipped page visits (already successful)
    private volatile List<String> error = new LinkedList<>();             //Error page visits (not in internet)

    private Database() {
    }

    public static Database getInstance() {
        if (databaseInstance == null)
            databaseInstance = new Database();
        return databaseInstance;
    }

    public static Database getDatabaseInstance() {
        return databaseInstance;
    }

    public List<String> getUnvisitedLinks() {
        return unvisitedLinks;
    }

    public List<String> getSuccess() {
        return success;
    }

    public List<String> getSkipped() {
        return skipped;
    }

    public List<String> getError() {
        return error;
    }

}
