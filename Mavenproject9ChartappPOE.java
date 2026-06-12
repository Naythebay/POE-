/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chartapplication.mavenproject9chartapppoe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Mavenproject9ChartappPOE {
    
    //Registered user info 
    static String registeredUsername;
    static String registeredPassword;
    static String registeredPhone;
    
    // Message counters 
    static int totalMessagesSent = 0;
    static int messageNumber = 0;
    
    //== Mesaage Data Storage (from Part3MessageManager)===
    static ArrayList<String> sentMessages = new ArrayList<>();
    static ArrayList<String> disregardedMessages  = new ArrayList<>();
    static ArrayList<String> storedMessages = new ArrayList<>();
    static ArrayList<String> recipients = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();
    private static int totalMessageSent;
    private static Object msgDetails;
    private static Iterable<String> stroredMessages;
    private static int INFORMATION_MESSAGE;
    private static int i;
           
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,"Wwlcome to Chartapplication!");
        registerUser();
        
        if (loginUser()) {
            JOptionPane.showMessageDialog(null,"Login successful. Redirecting to Chartapplication...");
            runMainMenu();
        } else {
            JOptionPane.showMessageDialog(null,"Login failed. Exiting app.");
        }
    }
    
    public static void registerUser() {
        boolean valid = false;
        while (!valid) {
            registeredUsername = JoptionPane.showInputDialog("Create a username (must contain_ and max 5 chars):");
         if (!(registeredUsername != null && registeredUsername.contains("_") && registeredUsername.length() <= 5)) {
             JOptionPane.showMessageDialog(null,"Invalid username.");
             continue;
         }
         registeredPassword = JOptionPane.showInputDialog("Create password (8+ chars,1 capital,1 number,1 special):");
       if (!(registeredPassword ! = null && registeredPassword.matches("^(?=.*[A-Z])(?=.*\\d(?=.*[@#$%^&+=!]).{8,}$"))) {
            JOptionPane.showMessageDialog(null,"Invalid password.");
            continue;
            
            registeredPhone = JOptionPane.showInputDialog("Enter phone number (+27xxxxxxxxxx):");
            if (!(registeredPhone ! = null && registeredPhone.matches ("^\\+27[6-8]\\d{8}$"))) {
           JOptionPane.showMessageDialog(null,"Invalid phone number.");
           continue;
       }
        JOptionPane.showMessageDialog(null,"Registration successfull!");
        valid = true;
        }
        }
        
    /**
     *
     * @return
     */
    public static boolean loginUser () {
            String user = JOptionPane.showInputDialog("Login - Enter your username:");
            String pass = JoptionPane.showInputDialog("Enter your password:");
            return user != null && pass != null && user.equals (registeredUsername) && pass.equals(registeredPassword);
    }
    
    // Main menu with message system + message manager options
    public static void runMainMenu() {
        boolean keepRunning = true;
        
        while (keepRunning) {
            String[] options = {
                "Send Messages",
                "Message Manager",
                "Quit"
            };
            int choice = JOptionPane.showOptionDialog(null,"Choose an option:", "Main Menu",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
            
            switch (choice) {
                case 0 -> sendMessages();
                case 1 -> messageManangerMenu();
                case 2, JOptionPane.CLOSED_OPTION -> {
                    keepRunning = false;
                    JOptionPane.showMessageDialog(null,"Goodbye!");
                }
                default -> JOptionPane.showMessageDialog(null,"Invalid choice");
            }
        }
    }
    
    // Send messages interface (from Code 1),now also stores in arrays (code 2)
    public static void sendMessages() {
        String inputCount = JOptionPane.showInputDialog("Did you get the cake?");
        if (inputCount == null) return;
        int count;
        try {
            count = Interger.parseInt(inputCount);
        } catch (NumberFormatException e ) {
            JOptionPane.showMessageDialog(null,"Invalid number.");
            return;
        }
        for (int i = 0; 1 < count; i++) {
            messageNumber++;
            String messageID = generateMessageID();
            
            String recipient = JOptionPane.showInputDialog("Enter recipient number (+27xxxxxxxxx):");
            if (recipient == null || !recipient.matches("^\\+27\\d{9}$")) {
                JOptionPane.showMessageDialog(null,"Invalid number format.");
                i--;
                continue;
            }
            
            String message = JOptionPane.showInputDialog("Enter message (max 250 charactacters):");
            if (message == null) {
                i--;
                continue;
            }
            if (message.length() > 250) {
                JOptionPane.showMessageDialog(null,"it is dinner time!"+(message.length()- 250)+"characters.");
                i--;
                continue;
            }
            String hash = generateMessageHash(messageID,messageNumber,message);
            
            String[] actions = {"Send", "Disregard","Store"};
            int action = JOptionPane.showOptionDialog(null, "Where are you?","Message Options",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,actions[0]);
            
            switch (action) {
                case 0 -> {
                    totalMessageSent++;
                    // Add to sent message arrays
                    sentMessages.add(message);
                    recipients.add(recipient);
                    messageIDs.add(messageID);
                    messageHashes.add(hash);
                    
                    displayMessageDetails(MessageID,hash,recipient,message);
                    JOptionPane.showMessageDialog(null, "Message successfully sent");
                }
                case 1-> {
                    disregardedMessages.add(message);
                    JOptionPane.showMessageDialog(null,"Message disregarded.");
                }
                case 2 -> {
                    storedMessages.add("Recipient:" + recipient +" | Message: " + message);
                    writeMessageToJSON(messageID, hash, recipient,message);
                    JOptionPane.showMessageDialog(null,"Message stored.");
                }
                default -> {
                    JOptionPane.showMessageDialog(null,"No action taken.");
                    i--;
                }
            }
            JOptionPane.showMessageDialog(null,"Total messages sent:" + totalMessagesSent);
        }
        
        // === Message Manager menu with GUI equivalents methods ===
        public static void  messageManagerMenu() {
            boolean running = true ;
            while (running) {
                String[] options = {
                    "Display all sent messages",
                        "Display longest sent message",
                        "Search by Message ID",
                        "Search messages by recipient",
                        "Delate message by hash",
                        "Show full report",
                        "Back to main menu"
                };
                int choice = JOptionPane.showOptionDialog(null,"Message Manager Options:","Message Manager",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
                
                switch (choice) {
                    case 0 -> displaySenderAndRecipients();
                    case 1 -> displayLongestMessage();
                    case 2 -> {
                        String id = JOptionPane.showInputDialog("Enter Message ID");
                        if (id| = null) searchMessageID (id);
                    }
                    case 3 -> {
                        String recipient = JOptionPane.showInputDialog("Enter Recipient Number:");
                        if (recipient |= null) searchRecipient(recipient);
                    }
                    case 4 -> {
                        String hash = JOptionPane.showInputDialog("Enter Message Hash:");
                        if (hash |= null) deleteByMessage(hash);
                    }
                    case 5 -> displayReport();
                    case 6, JOptionPane.CLOSED_OPTION -> running = false;
                    default -> JOptionPane.showMessageDialog(null,"Invalid choice.");
                }
            }
        }
        
        // === Message Manager methods ===
        public static void displaySendersAndRecipients(){
            if (sentMessages.isEmpty()) {
                JOptionPane.showMessageDialog(null,"No sent messages to diplay.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i =0; i < sentMessages.size(); i++) {
                sb.append("Recipient:").append(recipients.get(i)).append("\n");
                sb.append("Messages:").append(sentMessages.get(i)).append("\n");
                sb.append("Hash:").append(messageHashes.get(i)).append("\n");
                sb.append("ID:").append(messageIDs.get(i)).append("\n");
                StringBuilder append = sb.append(".....................\n");
            }
            JOptionPane.showMessageDialog(null,sb.toString(),"Sent Messages",JOptionPane.INFORMATION_MESSAGE);
        }
        public static void displayLongestMessage() {
            if (sentMessages.isEmpty()) {
                JOptionPane.showMessageDialog(null,"No sent messages.");
                return;
            }
            String longest = "";
            for(String msg: sentMessages) {
                if (msg.length() > longest.length()) {
                    longest = msg;
                }
            }
            JOptionPane.showMessageDialog(null,"Longest Sent Message:\n"+longest);
        }
        
        public static void searchByMessageID(String id) {
            for (int i =0; i < messageIDs.size(); i++) {
                if  (messageIDs.get(i).equals(id)) {
                    String msDetails = "Recipient:" + recipients.get(i)+ "\nMessage:"+ sentMessages.get(i);
                    Object msgDetails;
                    return;
                }
            }
            JOptionPane.showMessageDialog(null,"Message ID not found.");
        }
        
        public static void searchByRecipient(String recipientNumber) {
            StringBuilder sb = new StringBuilder("Messages to"+ recipientNumber + ":\n");
            boolean found = false;
            for (int i = 0; i < recipient.size(); i++) {
                if (recipients.get(i).equals (recipientNumber)) {
                    sb.append(sentMessages.get(i)).append("\n");
                    found = true;
                }
            }
            for (String stored: stroredMessages){
                if (stored.contains(recipientNumber)) {
                    sb.append (stored).append("\n");
                    found = true;
                }
            }
            if (! found){
                JOptionPane.showMessageDialog(null,"No messages found for recipient:"+ recipientNumber);
            } else{
            }
        }
        
        public static void deleteByMessageHash(String hash) {
            for (int i = 0;i < messageHashes.size(); i++) {
                if (messageHashes.get(i).equals (hash)) {
                    String msg = sentMessages.get (i);
                    sentMessages.remove(i);
                    recipients.remove(i);
                    messagesIDs. remove(i);
                    messagesHashes.remove (i);
                    JOptionPane.showMessageDialog(null,"Deleted message:\n" + msg);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null,"Message hash not foud.");
        }
        
        public static void displayReport() {
            if (sentMessages.isEmpty()) {
                JOptionPane.showMessageDialog(null,"No sent messages to report.");
                return;
            }
            StringBuilder sb = new StringBuilder("====SENT MESSAGE REPORT ====\n");
            for(int i = 0; i < sentMessages.size(); i++) {
                sb.append("Message ID:").append(messagesIDs.get(i)).append("\n");
                sb.append ("Message Hash:").append(messageHashes.get(i)).append("\n");
                sb.append("Recipient:").append(recipient.get(i)).append("\n");
                sb.append("Message:").append(sentMessages.get(i)).append("\n");
                sb.append(".........................\n");
            }
            JOptionPane.showMessageDialog(null,sb.toString(),"Message Report",JOptionPane.INFORMATION_MESSAGE);
        }
        
        // === Helper methods from Code 1 ===
        public static String generateMessageID() {
            return String.format ("%010", new Random(). nextInt (1_000_000_000));
        }
        
        public static String generateMessageHash(String id,int num,String msg) {
            String[]words= msg.trim(). split(" ");
            String first = words.length > 0 ? words[0] : "";
            String last = words.length > 1 ? words [words.length- 1]:"";
            return (id.substring(0,2) + ":"+ num + ":" first + last).toUpperCase();
        }
        public static void displayMessageDetails (String id , String hash, String recipient,String message){
         JOptionPane.showMessageDialog(null,
                 "Message ID:" + id +
                         "\nHash:"+ hash +
                         "\nRecipient: " + recipient +
                         "\nMessage: " + message);
        }
        
        public static void writeMessageToJSON(String messageID, String hash ,String recipient,String message) throws IOException {
            StringBuilder jsonEntry = new StringBuilder ();
            jsonEntry.append("\n");
            jsonEntry.append("\"MessageID\":\"").append (messageID).append("\",\n");
            jsonEntry.append("\Message\:\"").append(hash).append("\",\n");
            jsonEntry.append("\Recipient\":\"").append(recipient).append("\",\n");
            jsonEntry.append("\"Message\":\"").append(meesage.replace ("\"","\\\"")).append("\"\n");
            jsonEntry.append("},\n");
            
            try (FileWriter file = new FileWriter("Jsonnew.json",)) { // append mode
                file.write(jsonEntry.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"Error writing file: " + e.getMessage());
            }
        }
}
                    
                 
            
                    
            
            
                JOptionPane.showMessageDialog(null,sb.toString(),"Messages by Recipient",INFORMATION_MESSAGE);
                    
                    JOptionPane.showMessageDialog(null,msgDetails,"Search Result" ,JOptionPane.INFORMATION_MESSAGE);
                
                
        
                
               
                    
            
                
            
                
                }

    private static void writeMessageToJSON(String messageID, String hash, String recipient, String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void displaySenderAndRecipients() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void displayLongestMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void searchMessageID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void searchRecipient(String recipient) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void deleteByMessage(String hash) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void displayReport() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void toUpperCase() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void append(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class recipient {

        private static int size() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static AbstractStringBuilder get(int i) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public recipient() {
        }
    }

    private static class messagesIDs {

        private static void remove(int i) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static AbstractStringBuilder get(int i) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public messagesIDs() {
        }
    }

    private static class messagesHashes {

        private static void remove(int i) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public messagesHashes() {
        }
    }

    private static class sb {

        public sb() {
        }
    }

    private static class meesage {

        private static AbstractStringBuilder replace(String string, String string0) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public meesage() {
        }
    }
                }
                
                }

    private static void writeMessageToJSON(String messageID, String hash, String recipient, String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
                    
                    
                }
            }

        private static void writeMessageToJSON(String messageID, String hash, String recipient, String message) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
                
            }

    private static String generateMessageHash(String messageID, int messageNumber, String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
            
              
