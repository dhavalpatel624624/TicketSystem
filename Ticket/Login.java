

//Programmer: Dhaval Patel

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.*;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.*;

public class Login {
    
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    int cid;
    String name;
    String username;
    String password;

    
    public Login(){
        prepareGUI();
    }
    
    public static void main(String[] args){
    	createTable();
        Login login = new Login();
        login.showTextField();
    }
    
    private static void createTable()
   	{	
   		
        	
        	
   		// vars. for SQL Query create
   final String createTable = "CREATE TABLE dPatelInfo(name VARCHAR(30), username VARCHAR(30), password VARCHAR(200), date_created VARCHAR(200))";
   		  Connection connect = null;
   		  Statement statement = null;
   				  
   		try {
   		      // This will load the MySQL driver, each DB has its own driver
   		      Class.forName("com.mysql.jdbc.Driver");
   		      // Setup the connection with the DB
   		      connect = DriverManager
   		          .getConnection("jdbc:mysql://www.papademas.net/tickets?"
   		              + "user=fp411&password=411");
   	 	      
   		      //create table
   		    
   		      statement = connect.createStatement();
   		      
   		      statement.executeUpdate(createTable);
   		      System.out.println("Created table in given database...");

   			//end create table
   		    //close connection/statement object  
   		     statement.close();
   		     connect.close();
   		    } catch (Exception e) {
   		    	System.out.println(e.getMessage());  
   		    }  

   		
   	}

    
    private void prepareGUI(){
        mainFrame = new JFrame("MainWindow");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent wE){
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        
        statusLabel.setSize(350,100);
        
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    
    private void showTextField(){
        headerLabel.setText("Account Access");
        
        JLabel  namelabel= new JLabel("User ID: ", JLabel.RIGHT);
        JLabel  passwordLabel = new JLabel("Password: ", JLabel.CENTER);
        final JTextField userText = new JTextField(6);
        final JPasswordField passwordText = new JPasswordField(6);      
        final JButton loginButton = new JButton("Login");
        final JButton createAccountButton = new JButton("Create Account");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
         	
            	String correctUser = username; //hardcoded username
            	String correctPassword = password; //hardcoded password
            	
            	String admin = "admin"; //hardcoded username
            	String adminPassword = "admin"; //hardcoded password
            	
            	String username2 = userText.getText(); //get username input
            	String password2 = passwordText.getText(); //get password input
            	
            	
            	if(username2.equals(correctUser) && password2.equals(correctPassword)){
            		//open up MainWindow
            		mainFrame.dispose();
            		new ticketsGUI(name);  //if both correct, open the MainWindow
            		
            	}
            	
            	else if(username2.equals(admin) && password2.equals(adminPassword)){
            		//open up MainWindow
            		mainFrame.dispose();
            		name = "admin";
            		new ticketsGUI(name);  //if both correct, open the MainWindow
            		
            	}
            	
            	else {
            		JOptionPane.showMessageDialog(null, "Username or Password Incorrect", "Error", JOptionPane.PLAIN_MESSAGE);
            		//if one or either incorrect, open error message
            	}
            	
            	}
            
            	
        });
       
        
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try{
            		
            		do{
            			name = JOptionPane.showInputDialog(null, "Please enter your full name");       		
                  		 }
              			 while(name.equals(""));
            		
            		do{
            			username = JOptionPane.showInputDialog(null, "Please enter a username");                  	
            			}
              			 while(username.equals(""));
            		
            		do{
                        password = JOptionPane.showInputDialog(null, "Please enter a password");
            		}
              			 while(password.equals(""));
            		
            	
            	JOptionPane.showMessageDialog(null, "Welcome " + name + "\n\nYour username is: " + username+ "\nYour password is: " + password + 
            			"\n\nPlease use these credentials to login. You must create a new username and password prior to logging in each time for security purposes.", "Login Info", JOptionPane.PLAIN_MESSAGE);
            	
           		 
           		java.util.Date date = new java.util.Date();
    	            long time = date.getTime();
    	            java.sql.Date day = new java.sql.Date(time);
    	            java.sql.Time time2 = new java.sql.Time(time);
    	            java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
           			 
           				Class.forName("com.mysql.jdbc.Driver").newInstance();
           				
           				Connection dbConn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
           	                                                               + "user=fp411&password=411");
           				
           				System.out.println("Connection established");
           	            
           	            Statement statement = dbConn.createStatement();
           	      
           	            statement.executeUpdate("Insert into dPatelInfo(name, username, password, date_created) values('"+name+"','"+username+"','"+password+"','"+timestamp+"')");
           	            
           	           
           		 
           	        
            		}
           		       catch (SQLException ex) {
           				// TODO Auto-generated catch block
           				ex.printStackTrace();
           		       } catch (InstantiationException e1) {
           				// TODO Auto-generated catch block
           				e1.printStackTrace();
           			} catch (IllegalAccessException e1) {
           				// TODO Auto-generated catch block
           				e1.printStackTrace();
           			} catch (ClassNotFoundException e1) {
           				// TODO Auto-generated catch block
           				e1.printStackTrace();
           			}
               	
               }	

            	
            
            });
        
        controlPanel.add(namelabel);
        controlPanel.add(userText);
        controlPanel.add(passwordLabel);       
        controlPanel.add(passwordText);
        controlPanel.add(loginButton);
        controlPanel.add(createAccountButton);
        mainFrame.setVisible(true);  
   
    
    }
}
