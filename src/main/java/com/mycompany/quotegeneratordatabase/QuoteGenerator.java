
package com.mycompany.quotegeneratordatabase;
import java.util.*;

public class QuoteGenerator {
    private static String[] quotes;
    
    QuoteGenerator(String[] quotes){
        this.quotes = quotes;
    }
    
    public static void generateQuotes(int numberOfQuotes) {
        
        System.out.println("Generating quotes...");
        System.out.println("");
        Random random = new Random();

        // If user has inputted an integer other than 1, it produces more quotes.
        // else it will produce one quote.
        if (numberOfQuotes > 1){
               
            System.out.println("Generated Quotes: ");
            
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
            System.out.println("Generated Quote: " + quotes[index]);
        }

    }
    
}
