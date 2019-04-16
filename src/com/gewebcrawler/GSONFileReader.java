package com.gewebcrawler;

import java.nio.file.Files;
import java.nio.file.Paths;

public class GSONFileReader {
    static String json = "";

    public static String readFileAsString(String fileName)throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static void main(String[] args) throws Exception {
        json = readFileAsString("C:\\Users\\Balry-PC\\OneDrive\\Documents\\ProgProjects\\IntelliJProjects\\GE_WebCrawler\\reqDoc\\internet_1.json");
        System.out.println(json);
    }


}
