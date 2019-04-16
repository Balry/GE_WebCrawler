package com.gewebcrawler.json;

public class Internet {

    private Page[] pages;

    public Page[] getPages () {
        return pages;
    }

    public void setPages (Page[] pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "ClassPojo [pages = " + pages + "]";
    }
}
