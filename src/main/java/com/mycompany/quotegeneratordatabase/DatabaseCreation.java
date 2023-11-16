
package com.mycompany.quotegeneratordatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Still has a lot of optimizations keep refactoring
// Put the driver into a variable
// 

public class DatabaseCreation {
    
    String user;
    String password;
    
    String urlDatabase;
    String urlTable;

    
    DatabaseCreation(String user, String password, String urlTable){
        this.user = user;
        this.password = password;
        
        this.urlDatabase = "jdbc:mysql://localhost:3306/";
        this.urlTable = "jdbc:mysql://localhost:3306/" + urlTable;
        
        try {
            // Register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch(Exception e) {
            e.printStackTrace();
        }
       
    };
    
    public void createDatabase(){
        // Creating Database
        try {
            // Open a connection
            Connection conn = DriverManager.getConnection(urlDatabase, user, password);

            // Execute a query to create a new database
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE " + urlTable);

            System.out.println("Database created successfully...");

            // Select the newly created database
            stmt.executeUpdate("USE " + urlTable);

            // Create the 'quotes' table
            stmt.executeUpdate("CREATE TABLE quotes ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "genre_id INT, "
                    + "quotes VARCHAR(255)"
                    + ")");

            // Create the 'genres' table
            stmt.executeUpdate("CREATE TABLE genres ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "genres VARCHAR(255)"
                    + ")");

            System.out.println("Tables created successfully...");
            
            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    } 
    
    public void initializeData(){
        try {
            // Register JDBC driver

            // Open a connection
            Connection conn = DriverManager.getConnection(urlTable, user, password);

            // Create a statement
            Statement stmt = conn.createStatement();

            // Insert values into the 'genres' table
            stmt.executeUpdate("INSERT INTO genres (genres) VALUES ('Life'), ('Success'), ('Creativity'), ('Happiness'), ('Innovation')");

            stmt.executeUpdate("INSERT INTO quotes (quotes, genre_id) VALUES "
                    + "('\"Life is what happens when you''re busy making other plans.\" - John Lennon', 1), "
                    + "('\"The only limit to our realization of tomorrow will be our doubts of today.\" - Franklin D. Roosevelt', 1), "
                    + "('\"The way to get started is to quit talking and begin doing.\" - Walt Disney', 1), "
                    + "('\"In three words I can sum up everything I''ve learned about life: it goes on.\" - Robert Frost', 1)");

            
            stmt.executeUpdate("INSERT INTO quotes (quotes, genre_id) VALUES "
                    + "('\"Success is not final, failure is not fatal: It is the courage to continue that counts.\" - Winston Churchill', 2), "
                    + "('\"Don''t watch the clock; do what it does. Keep going.\" - Sam Levenson', 2), "
                    + "('\"The only place where success comes before work is in the dictionary.\" - Vidal Sassoon', 2)");
            
            stmt.executeUpdate("INSERT INTO quotes (quotes, genre_id) VALUES "
                    + "('\"Creativity is intelligence having fun.\" - Albert Einstein', 3), "
                    + "('\"The only thing we have to fear is fear itself.\" - Franklin D. Roosevelt', 3), "
                    + "('\"It does not matter how slowly you go, as long as you do not stop.\" - Confucius', 3)");

            stmt.executeUpdate("INSERT INTO quotes (quotes, genre_id) VALUES "
                    + "('\"The purpose of our lives is to be happy.\" - Dalai Lama', 4), "
                    + "('\"Happiness is not something ready made. It comes from your own actions.\" - Dalai Lama', 4), "
                    + "('\"The best way to predict the future is to create it.\" - Peter Drucker', 4)");
            
            stmt.executeUpdate("INSERT INTO quotes (quotes, genre_id) VALUES "
                    + "('\"Innovation distinguishes between a leader and a follower.\" - Steve Jobs', 5), "
                    + "('\"The future belongs to those who believe in the beauty of their dreams.\" - Eleanor Roosevelt', 5), "
                    + "('\"Change your thoughts and you change your world.\" - Norman Vincent Peale', 5)");


            System.out.println("Values inserted successfully...");
            
            
            
            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public boolean isDatabaseCreated(String dbName) {
        boolean isCreated = false;

        try (Connection conn = DriverManager.getConnection(urlTable, user, password);
             ResultSet resultSet = conn.getMetaData().getCatalogs()) {

            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if(databaseName.equals(dbName)){
                    isCreated = true;
                    break;
                }
            }
            resultSet.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return isCreated;
    }
    
    public String[] getAllQuotes() {
        String query = "SELECT quotes FROM quotes";
        String[] quotesArray = null;

        try (Connection conn = DriverManager.getConnection(urlTable, user, password);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            ArrayList<String> quotes = new ArrayList<>();

            while (rs.next()) {
                quotes.add(rs.getString("quotes"));
            }

            // Convert ArrayList to Array
            quotesArray = quotes.toArray(new String[0]);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       
        return quotesArray;
    }
    
    public String[] getQuotesByGenre(int genreId) {
        String query = "SELECT quotes FROM quotes WHERE genre_id = " + genreId;
        String[] quotesArray = null;

        try (Connection conn = DriverManager.getConnection(urlTable, user, password);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            ArrayList<String> quotes = new ArrayList<>();

            while (rs.next()) {
                quotes.add(rs.getString("quotes"));
            }

            // Convert ArrayList to Array
            quotesArray = quotes.toArray(new String[0]);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return quotesArray;
    }

    public String[] getLifeQuotes() {
        return getQuotesByGenre(1);
    }

    public String[] getSuccessQuotes() {
        return getQuotesByGenre(2);
    }
    
    public String[] getCreativityQuotes() {
        return getQuotesByGenre(3);
    }
    
    public String[] getHappinessQuotes() {
        return getQuotesByGenre(4);
    }
    
    public String[] getInnovationQuotes(){
        return getQuotesByGenre(5);
    }
    
}