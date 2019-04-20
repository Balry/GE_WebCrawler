package com.gewebcrawler;

class App {

    public static void main(String[] args){
        try{

            Thread webCrawler = new WebCrawler();

            while (Database.getInstance().getUnvisitedLinks().size() > 0) {
                webCrawler.start();


                /*System.out.println("Number of Threads: " + Thread.activeCount());
                Thread[] listOfThreads = new Thread[Thread.activeCount()];
                Thread.enumerate(listOfThreads);
                for(Thread i:listOfThreads){
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
