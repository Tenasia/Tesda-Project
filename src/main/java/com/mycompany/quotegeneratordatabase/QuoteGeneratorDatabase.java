
package com.mycompany.quotegeneratordatabase;

import java.util.Scanner;



// Implement switch case that asks the user how they want to get quotes


public class QuoteGeneratorDatabase {
    
    static String numberOfQuotes;
    static String[] quotes;
    static boolean isValidInput;
    
    static boolean selectRandomQuotes;
    
    
    public static void main(String[] args) {

        
        DatabaseCreation database = new DatabaseCreation("root", "Akosiwilliam47");
        
        if (database.isDatabaseCreated("quoteGenerator")){     
            database.createDatabase();
            database.initializeData();
        }
        
        selectRandomQuotes = true;
        
        // replace with switch case
        if (selectRandomQuotes){
            quotes = database.getAllQuotes();
            
            RandomQuoteGenerator randomQuoteGenerator = new RandomQuoteGenerator(quotes);
            System.out.print("Generate Random Quotes, enter number of quotes you want generated, else just press enter: ");
            
            Scanner scanner = new Scanner(System.in);

            do {
                numberOfQuotes = scanner.nextLine();
                isValidInput = isParseableInt(numberOfQuotes, quotes);
            } while (!isValidInput);
            
            // Checks whether user wants one or more quotes
            if (numberOfQuotes.isEmpty()){
                randomQuoteGenerator.generateRandomQuote(1);
            } else {
                randomQuoteGenerator.generateRandomQuote(Integer.parseInt(numberOfQuotes));
            }
        } 
        
    }
    
    
    // Similar to tryParseInt
    public static boolean isParseableInt(String str, String[] quotes) {
        try {
            
            if (str.isEmpty()){
                return true;
            }
                
            int number = Integer.parseInt(str);
            
            if (number > quotes.length){
                System.out.println("Sorry, the number of quotes that we have are only " + quotes.length);
                System.out.println("Please enter a number only up to that number, or just press enter to generate one!");
                return false;
            } else {
                return true;  // Parsing successful
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number, or just leave it blank to generate one quote.");
            return false; // Parsing failed
        }
    }
}
