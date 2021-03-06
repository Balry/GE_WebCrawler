package com.gewebcrawler.json;

import java.util.Arrays;

public class Page {

    private String address;
    private String[] links;

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String[] getLinks () {
        return links;
    }

    public void setLinks (String[] links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Page [address = " + address + ", links = " + Arrays.toString(links) + "]";
    }
}