import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/*proj implements one listener and can trigger action off menu item clicked
 * with one action performed event handler!
 */
@SuppressWarnings("serial")
public class ticketsGUI extends JFrame implements ActionListener {

	//needed class level member vars
	private JFrame mainFrame;
	
	//incl main menu objects
	private	JMenu file = new JMenu("File");
	private	JMenu tickets = new JMenu("Tickets");
	int ticketID;
	JScrollPane sp = null;
	private JLabel statusLabel;
	//incl sub menu item objects
	
	
    JMenuItem ItemExit;
    JMenuItem ItemOpenTicket;
    JMenuItem ItemViewTicket;
    JMenuItem ItemDeleteTicket;
    JMenuItem ItemAdminViewTicket;
    JMenuItem ItemAdminEndTicket;
    JMenuItem ItemAdminChangeTicketStatus;
    JMenuItem ItemAdminCheckCredentials;
    //include more items below
    
   
	
	public ticketsGUI(final String name) {
				
		createTable();
		createMenu(name);
	    prepareGUI();
	}
	
	private void createTable()
	{	
		
     	
     	
		// vars. for SQL Query create
final String createTable = "CREATE TABLE dPatelTicket(ticket_id INT AUTO_INCREMENT PRIMARY KEY, ticket_name VARCHAR(30), ticket_description VARCHAR(200), start_date VARCHAR(200), end_date VARCHAR(200), ticket_status VARCHAR(10))";
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
	
private void createMenu(final String name)
{
	//set up mnemonics for main menu items (triggered by alt keys)
	file.setMnemonic('F');
	
	//initialize up sub menu items
	
	ItemExit = new JMenuItem("Log Off");
	ItemExit.setMnemonic('x');
	file.add(ItemExit);
	
	ItemOpenTicket = new JMenuItem("Open Ticket");
	ItemOpenTicket.setMnemonic('x');
	tickets.add(ItemOpenTicket);
	
	ItemViewTicket = new JMenuItem("View Ticket");
	ItemViewTicket.setMnemonic('x');
	tickets.add(ItemViewTicket);
	
	ItemDeleteTicket = new JMenuItem("Delete Ticket");
	ItemDeleteTicket.setMnemonic('x');
	tickets.add(ItemDeleteTicket);
	
	
	ItemAdminEndTicket = new JMenuItem("Admin: End Ticket");
	ItemAdminEndTicket.setMnemonic('x');
	tickets.add(ItemAdminEndTicket);
	
	ItemAdminChangeTicketStatus = new JMenuItem("Admin: Change Ticket Status");
	ItemAdminChangeTicketStatus.setMnemonic('x');
	tickets.add(ItemAdminChangeTicketStatus);
	
	ItemAdminCheckCredentials = new JMenuItem("Admin: Check Login Info");
	ItemAdminCheckCredentials.setMnemonic('x');
	tickets.add(ItemAdminCheckCredentials);
	
	
	//add listeners for each desired menu item 
		ItemExit.addActionListener(this);
		ItemOpenTicket.addActionListener(this);
		ItemViewTicket.addActionListener(this);
		ItemDeleteTicket.addActionListener(this);
		ItemAdminEndTicket.addActionListener(this);
		ItemAdminChangeTicketStatus.addActionListener(this);
		ItemAdminCheckCredentials.addActionListener(this);
		
	
	
	if(name.equalsIgnoreCase("admin"))
	{
	
	 ItemExit.setVisible(true);	
	 ItemOpenTicket.setVisible(false);
	 ItemViewTicket.setVisible(true);
	 ItemDeleteTicket.setVisible(true); 
	 ItemAdminEndTicket.setVisible(true);
	 ItemAdminChangeTicketStatus.setVisible(true);
	 ItemAdminCheckCredentials.setVisible(true);
	 
	}
	
	else{
	ItemExit.setVisible(true);	
	ItemOpenTicket.setVisible(true);
	ItemViewTicket.setVisible(true);
	ItemDeleteTicket.setVisible(true);
	ItemAdminEndTicket.setVisible(false);
	ItemAdminChangeTicketStatus.setVisible(false);
	ItemAdminCheckCredentials.setVisible(false);
	}
	

	statusLabel = new JLabel();
	
	statusLabel.setText("Currently logged in as "
			 + name); // user's name
	
	
}
 
private void prepareGUI()
{ 
	//initialize frame object
	mainFrame = new JFrame("Main");
	//adjust label position to sit in the upper right corner of window
    mainFrame.add(statusLabel);
    //create jmenu bar
	JMenuBar bar = new JMenuBar();
	bar.add(file);  //set menu orders
	bar.add(tickets);
	 //add menu bar component to frame
    mainFrame.setJMenuBar(bar); 
   
    mainFrame.setSize(1500,1500);
	mainFrame.setVisible(true);
}

public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	//add desired coding to trigger each sub menu action as examples shown below
	 if(e.getSource() == ItemExit){
         System.exit(0);
     }
	 else if(e.getSource() == ItemOpenTicket){

		 try {
			 String ticketName;
			 String ticketDesc;
			
			 
			//add a ticket information
			 do{
			 ticketName = JOptionPane.showInputDialog(null,"Enter your name");
			 }
			 while(ticketName.equalsIgnoreCase(""));
			 
			 do{
			 ticketDesc=
					 JOptionPane.showInputDialog(null,"Enter a ticket description");
			 }
			 while(ticketName.equalsIgnoreCase(""));
			 
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				Connection dbConn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
	                                                               + "user=fp411&password=411");
				
				System.out.println("Connection established");
	            
	            Statement statement = dbConn.createStatement();
	            
	            Random r = new Random();
	         	ticketID = r.nextInt(100000); 
	         
	         
	            String initialStatus = "Open";
	            
	            java.util.Date date = new java.util.Date();
	            long time = date.getTime();
	            java.sql.Date day = new java.sql.Date(time);
	            java.sql.Time time2 = new java.sql.Time(time);
	            java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
	            
	            int result = statement.executeUpdate("Insert into dPatelTicket(ticket_id, ticket_name, ticket_description, start_date, ticket_status) values('"+ticketID+"','"+ticketName+"','"+ticketDesc+"','"+timestamp+"','"+initialStatus+"')");
	            
	            if (result != 0) {
					System.out.println("Ticket Created Successfully!!!");
				} else {
					System.out.println("Ticket cannot be Created!!!");
				}
		 
	        
	            
		        JOptionPane.showMessageDialog(null,"Ticket id: " + ticketID); //******fill in with id value newly created in table!******//
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
	 else if(e.getSource() == ItemViewTicket){
			//retrieve ticket information
			 //ticketsJDBC dobj = new ticketsJDBC();
			 try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				Connection dbConn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
	                                                               + "user=fp411&password=411");
	            
	            Statement statement = dbConn.createStatement();
	            
	            ResultSet results = statement.executeQuery("SELECT * FROM dPatelTicket");
	            
	            // Use JTable built in functionality to build a table model and display the table model
	            //off a resultset!!!
	            JTable jt = new JTable(ticketsJTable.buildTableModel(results));
	           
	            jt.setBounds(500,500,1000,2000);
	            sp=new JScrollPane(jt);
	            mainFrame.add(sp);
	            mainFrame.setVisible(true); //repaint frame on screen
	            statement.close();
		        dbConn.close();   //close connections!!!
	            
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
	     }
	 
	 else if(e.getSource() == ItemDeleteTicket){
		 	 
		 try {
				
			 String ticketToDelete;
			 String confirmTicketToDelete;
			 
			 do{
				 ticketToDelete = JOptionPane.showInputDialog(null,"Enter the ticket ID you want to delete: ");
				 }
			 while(ticketToDelete.equalsIgnoreCase(""));
			
			 
				 confirmTicketToDelete = JOptionPane.showInputDialog(null,"Are you sure you want to delete Ticket# " +ticketToDelete+ "?" + "\n\n Type 'Yes' to delete this ticket.");
				
				
			 if(confirmTicketToDelete.equalsIgnoreCase("Yes")){
			 
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				Connection dbConn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
	                                                            + "user=fp411&password=411");
				
				System.out.println("Connection established");
	         
	         Statement statement = dbConn.createStatement();
	           
	         String userDeletedStatus = "Deleted";
	         
	         java.util.Date date = new java.util.Date();
	         long time = date.getTime();
	         java.sql.Date day = new java.sql.Date(time);
	         java.sql.Time time2 = new java.sql.Time(time);
	         java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
	         
	         int result = statement.executeUpdate("UPDATE dPatelTicket SET ticket_status = '"+userDeletedStatus+"' WHERE ticket_id = '"+ticketToDelete+"'");
	         int result2 = statement.executeUpdate("UPDATE dPatelTicket SET end_date = '"+timestamp+"' WHERE ticket_id = '"+ticketToDelete+"'");

	         
	         if (result != 0) {
					System.out.println("Ticket Deleted Successfully!!!");
				} else {
					System.out.println("Ticket NOT Deleted!!!");
				}
	         
	         if (result2 != 0) {
					System.out.println("Ticket Deleted Successfully!!!");
				} else {
					System.out.println("Ticket NOT Deleted!!!");
				}
	         
		        JOptionPane.showMessageDialog(null,"You have deleted Ticket# " + ticketToDelete+  "\nTicket Stauts Changed to: " + userDeletedStatus); 
			 }
			 
			 else{
			        JOptionPane.showMessageDialog(null,"You did not confirm correctly. Please try again.");
			 }
		    
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
	 
	  
	 
	 
	 else if(e.getSource() == ItemAdminEndTicket){
		 
		 try {
			
			 String ticketToEnd;
			 String confirmTicketToEnd;
			 
			 do{
				 ticketToEnd = JOptionPane.showInputDialog(null,"Admin: Enter the ticket ID you want to end: ");
				 }
			 while(ticketToEnd.equalsIgnoreCase(""));
			 
			 confirmTicketToEnd = JOptionPane.showInputDialog(null,"Are you sure you want to end Ticket# "+ ticketToEnd + "?" + "\n\n Type 'Yes' to delete this ticket.");
				
				
			 if(confirmTicketToEnd.equalsIgnoreCase("Yes")){
			
			 
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				Connection dbConn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
	                                                               + "user=fp411&password=411");
				
				System.out.println("Connection established");
	            
	            Statement statement = dbConn.createStatement();
	            
	        
	         	
	            String finalStatus = "Closed";
	            
	            java.util.Date date = new java.util.Date();
	            long time = date.getTime();
	            java.sql.Date day = new java.sql.Date(time);
	            java.sql.Time time2 = new java.sql.Time(time);
	            java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
	            
		        int result = statement.executeUpdate("UPDATE dPatelTicket SET end_date = '"+timestamp+"' WHERE ticket_id = '"+ticketToEnd+"'");
	            int result2 = statement.executeUpdate("UPDATE dPatelTicket SET ticket_status = '"+finalStatus+"' WHERE ticket_id = '"+ticketToEnd+"'");
	            
	            if (result != 0) {
					System.out.println("Ticket Deleted Successfully!!!");
				} else {
					System.out.println("Ticket cannot be Deleted!!!");
				}
	            
	            if (result2 != 0) {
					System.out.println("Ticket Status Updated Successfully!!!");
				} else {
					System.out.println("Ticket status cannot be Updated!!!");
				}
		 
	        
	            
		        JOptionPane.showMessageDialog(null,"Ticket# " +ticketToEnd+ " Ended"); 
		    } 
			 
			 else{
			        JOptionPane.showMessageDialog(null,"You did not confirm correctly. Please try again.");
			 }
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
	 
	 else if(e.getSource() == ItemAdminChangeTicketStatus){

	 try {
		
		 String ticketToChange;
		 String whatDoYouWantToChangeTheStatusTo;
		 do{
			 ticketToChange = JOptionPane.showInputDialog(null,"Admin: Enter the ticket ID for the status you want to change: ");
			 }
		 while(ticketToChange.equalsIgnoreCase(""));
		 
		 
		 do{
			 whatDoYouWantToChangeTheStatusTo = JOptionPane.showInputDialog(null,"Admin: Choose from the following status options (type them as they appear): \n\n "
			 		+ "In Review, Open, Closed, or Deleted");
			 }
		 while(whatDoYouWantToChangeTheStatusTo.equalsIgnoreCase(""));
		
		 if(whatDoYouWantToChangeTheStatusTo.equalsIgnoreCase("In Review") || whatDoYouWantToChangeTheStatusTo.equalsIgnoreCase("Open") || 
				 whatDoYouWantToChangeTheStatusTo.equalsIgnoreCase("Closed") || whatDoYouWantToChangeTheStatusTo.equalsIgnoreCase("Deleted")){
			 
		 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			Connection dbConn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
                                                            + "user=fp411&password=411");
			
			System.out.println("Connection established");
         
         Statement statement = dbConn.createStatement();
           
         
         int result = statement.executeUpdate("UPDATE dPatelTicket SET ticket_status = '"+whatDoYouWantToChangeTheStatusTo+"' WHERE ticket_id = '"+ticketToChange+"'");

         if (result != 0) {
				System.out.println("Ticket Status Changed Successfully!!!");
			} else {
				System.out.println("Ticket Status NOT Changed!!!");
			}

         
	        JOptionPane.showMessageDialog(null,"Ticket# " +ticketToChange+ " Stauts Changed to: " + whatDoYouWantToChangeTheStatusTo); 
	    } 
	 
	 else{
		 JOptionPane.showMessageDialog(null,"Not a valid option. Please try again.");
	 }}
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
	 
	 else if(e.getSource() == ItemAdminCheckCredentials){
			//retrieve ticket information
			 //ticketsJDBC dobj = new ticketsJDBC();
			 try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				Connection dbConn = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?"
	                                                               + "user=fp411&password=411");
	            
	            Statement statement = dbConn.createStatement();
	            
	            ResultSet results = statement.executeQuery("SELECT * FROM dPatelInfo");
	            
	            // Use JTable built in functionality to build a table model and display the table model
	            //off a resultset!!!
	            JTable jt = new JTable(ticketsJTable.buildTableModel(results));
	           
	            jt.setBounds(500,500,1000,2000);
	            sp=new JScrollPane(jt);
	            mainFrame.add(sp);
	            mainFrame.setVisible(true); //repaint frame on screen
	            statement.close();
		        dbConn.close();   //close connections!!!
	            
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
	     }
			}


				}

