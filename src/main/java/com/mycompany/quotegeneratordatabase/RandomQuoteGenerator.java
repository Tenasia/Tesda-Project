
package com.mycompany.quotegeneratordatabase;
import java.util.*;

// Final, it makes the list of quotes that it receives to be randomized, adds some validation and sanitation.

public class RandomQuoteGenerator {
    private static String[] quotes;
    
    RandomQuoteGenerator(String[] quotes){
        this.quotes = quotes;
    }
    
    public static void generateRandomQuote(int numberOfQuotes) {
        
        System.out.println("Generating quotes...");
        Random random = new Random();

        // If user has inputted an integer other than 1, it produces more quotes.
        // else it will produce one quote.
        if (numberOfQuotes > 1){
               
            System.out.println("Random Quotes: ");
            
            Set<String> uniqueQuotes = new HashSet<>();
                           
            while(uniqueQuotes.size() != numberOfQuotes){
                int index = random.nextInt(quotes.length);
                uniqueQuotes.add(quotes[index]);                
            }

            for (String quote : uniqueQuotes){
                
                System.out.println(quote);
            }
            
        } else {
            int index = random.nextInt(quotes.length);
            System.out.println("Random Quote: " + quotes[index]);
        }

    }
}
