
package com.mycompany.quotegeneratordatabase;

import java.util.Scanner;



// Implement switch case that asks the user how they want to get quotes


public class QuoteGeneratorDatabase {
    
    static String[] quotes;
    static String numberOfQuotes;
    static String userChoice;
    static int modeChoice;

    static boolean isValidInput;
    
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        // Database user, password, and database name - This will initialize data and create database.
        DatabaseCreation database = new DatabaseCreation("root", "", "quoteGenerator");
        
        if (database.isDatabaseCreated("quoteGenerator")){     
            database.createDatabase();
            database.initializeData();
        }
        
        do {
            System.out.println("Select the mode of generating quotes (Enter a number between 1 and 6 - or 0 to quit.");
            System.out.println("1. Generate Random Quotes");
            System.out.println("2. Generate Life Quotes");
            System.out.println("3. Generate Success Quotes");
            System.out.println("4. Generate Creativity Quotes");
            System.out.println("5. Generate Happiness Quotes");
            System.out.println("6. Generate Innovation Quotes");
            System.out.print("Enter a number: ");
            modeChoice = scanner.nextInt();

            switch (modeChoice) {
                case 1:
                    quotes = database.getAllQuotes();
                    generateQuotes(quotes, "Random");
                    break;
                case 2:
                    quotes = database.getLifeQuotes();
                    generateQuotes(quotes, "Life");
                    break;
                case 3:
                    quotes = database.getSuccessQuotes();    
                    generateQuotes(quotes, "Success");
                    break;
                case 4:
                    quotes = database.getCreativityQuotes();    
                    generateQuotes(quotes, "Creativity");
                    break;
                case 5:
                    quotes = database.getHappinessQuotes();    
                    generateQuotes(quotes, "Happiness");
                    break;
                case 6:
                    quotes = database.getInnovationQuotes();    
                    generateQuotes(quotes, "Innovation");
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
            
            if(modeChoice != 0 && modeChoice < 7) {  
                System.out.println("Quotes generated, would you like to generate more quotes? Y/N");
                do {
                    userChoice = scanner.nextLine().toLowerCase();
                } while(!userChoice.equals("y") && !userChoice.equals("n"));
                
                if (userChoice.equals("n")){
                    System.out.println("Quitting Program...");
                    break;
                }
            }



        } while(modeChoice != 0);

        scanner.close();
        
    }
    
    public static void generateQuotes(String[] quotes, String quoteName){
        Scanner scanner = new Scanner(System.in);
                
        QuoteGenerator quoteGenerator = new QuoteGenerator(quotes);
        System.out.println("Generate " + quoteName + " Quotes, enter number of quotes you want generated, else just press enter: ");
        
        do {
            numberOfQuotes = scanner.nextLine();
            isValidInput = isParseableInt(numberOfQuotes, quotes);
        } while (!isValidInput);
        
        // Checks whether user wants one or more quotes
        if (numberOfQuotes.isEmpty()){
            quoteGenerator.generateQuotes(1);
        } else {
            quoteGenerator.generateQuotes(Integer.parseInt(numberOfQuotes));
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
