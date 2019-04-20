package com.gewebcrawler;

public class App {

    public static void main(String[] args){
        try{

            Thread webCrawler = new WebCrawler();

            while (Database.getInstance().getUnvisitedLinks().size() > 0) {
                webCrawler.start();

                /*
                System.out.println("Number of Threads: " + Thread.activeCount());
                Thread[] listofThreads = new Thread[Thread.activeCount()];
                Thread.enumerate(listofThreads);
                for(Thread i:listofThreads){
                    System.out.println(i.getName());
                }*/

                try{
                    Thread.sleep(1);
                }catch (InterruptedException e){}

            }

            //Console output
            System.out.println("Success:\n" + Database.getInstance().getSuccess().toString());
            System.out.println("\nSkipped:\n" + Database.getInstance().getSkipped().toString());
            System.out.println("\nError:\n" + Database.getInstance().getError().toString());

        }catch (Exception e){ e.getStackTrace(); }
    }
}
