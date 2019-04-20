package com.gewebcrawler.json;

import java.util.Arrays;

public class Internet {

    private Page[] pages;

    public Page[] getPages () {
        return pages;
    }

    @Override
    public String toString() {
        return "Internet [pages = " + Arrays.toString(pages) + "]";
    }
}
