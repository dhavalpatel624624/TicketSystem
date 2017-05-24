import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class delete {

	public static void main(String [] args){ 
	
	//final String deleteTable = "DELETE FROM dPatelTicket";
	final String deleteTable = "DELETE FROM dPatelInfo";
	  Connection connect = null;
	  Statement statement = null;
	  {
			  
	try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://www.papademas.net/tickets?"
	              + "user=fp411&password=411");
	      
	      //create table
	    
	      statement = connect.createStatement();
	      
	      statement.executeUpdate(deleteTable);
	      System.out.println("Deleted table in given database...");

		//end create table
	    //close connection/statement object  
	     statement.close();
	     connect.close();
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());  
	    }  
	

}
}}