package org.example;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Our scraper class
 */
public class App {

    /**
     * The main method of our class, which will also house the scraping
     * functionality.
     */
    public static void main(String[] args) {

        try {
            /**
             * Here we create a document object,
             * The we use JSoup to fetch the website.
             */
            Document doc = Jsoup.connect("https://lionfitness.pl/przepisy/page/1").get();

            /**
             * With the document fetched,
             * we use JSoup???s title() method to fetch the title
             */
            System.out.printf("\nWebsite Title: %s\n\n", doc.title());


            // Get the list of repositories
            Elements repositories = doc.getElementsByClass("card-content");


            /**
             * For each repository, extract the following information:
             * 1. Title
             * 2. Number of issues
             * 3. Description
             * 4. Full name on github
             */
            for (Element repository : repositories) {
               Elements repositortemp = repository.getElementsByClass( "waves-effect waves-light btn cyan" );
               String link = repositortemp.attr( "href" );


               Document doc2 = Jsoup.connect("https://lionfitness.pl/"+link).get();

                Elements repositories2 = doc2.getElementsByClass("card-image waves-effect waves-block waves-light");

                for (Element repository2 : repositories2) {

                    String title = repository2.getElementsByClass( "card-title" ).text();
                    System.out.println(title);
                }
                System.out.println("\n");
            }

            /**
             * Incase of any IO errors, we want the messages
             * written to the console
             */
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}