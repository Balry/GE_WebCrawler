package com.gewebcrawler.json;

import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Paths;

public class GSONFileReader {
    private static String json;

    public GSONFileReader(String path) throws Exception {
        json = readFileAsString(path);
    }

    private static String readFileAsString(String fileName)throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public Internet deserializeInternetJSON() {
        return new Gson().fromJson(json, Internet.class);
    }

}
