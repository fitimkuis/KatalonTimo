import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import java.sql.*;


//SQLiteJDBC.connectToSqlite()
//SQLiteJDBC.createTable()
//SQLiteJDBC.insertIntoToTable()
SQLiteJDBC.selectData()
SQLiteJDBC.updateData()

public class SQLiteJDBC {
	
  public static connectToSqlite() {
	  Connection c = null;
	  
	  try {
		 Class.forName("org.sqlite.JDBC");
		 c = DriverManager.getConnection("jdbc:sqlite:test.db");
	  } catch ( Exception e ) {
		 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		 System.exit(0);
	  }
	  System.out.println("Opened database successfully");
   }
  
  public static void createTable() {
	  Connection c = null;
	  Statement stmt = null;
	  
	  try {
		 Class.forName("org.sqlite.JDBC");
		 c = DriverManager.getConnection("jdbc:sqlite:test.db");
		 System.out.println("Opened database successfully");

		 stmt = c.createStatement();
		 String sql = "CREATE TABLE COMPANY " +
						"(ID INT PRIMARY KEY     NOT NULL," +
						" NAME           TEXT    NOT NULL, " +
						" AGE            INT     NOT NULL, " +
						" ADDRESS        CHAR(50), " +
						" SALARY         REAL)";
		 stmt.executeUpdate(sql);
		 stmt.close();
		 c.close();
	  } catch ( Exception e ) {
		 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		 System.exit(0);
	  }
	  System.out.println("Table created successfully");
   }
  
  public static insertIntoToTable(){
	  
	  Connection c = null;
	  Statement stmt = null;
	  
	  try {
		 Class.forName("org.sqlite.JDBC");
		 c = DriverManager.getConnection("jdbc:sqlite:test.db");
		 c.setAutoCommit(false);
		 System.out.println("Opened database successfully");

		 stmt = c.createStatement();
		 String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
						"VALUES (1, 'Paul', 32, 'California', 20000.00 );";
		 stmt.executeUpdate(sql);

		 sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
				  "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
		 stmt.executeUpdate(sql);

		 sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
				  "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
		 stmt.executeUpdate(sql);

		 sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
				  "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
		 stmt.executeUpdate(sql);

		 stmt.close();
		 c.commit();
		 c.close();
	  } catch ( Exception e ) {
		 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		 System.exit(0);
	  }
	  System.out.println("Records created successfully");
  }
  
  public static void selectData(){
	  
	  Connection c = null;
	  Statement stmt = null;
	  try {
		 Class.forName("org.sqlite.JDBC");
		 c = DriverManager.getConnection("jdbc:sqlite:test.db");
		 c.setAutoCommit(false);
		 System.out.println("Opened database successfully");
   
		 stmt = c.createStatement();
		 ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
		 
		 while ( rs.next() ) {
			int id = rs.getInt("id");
			String  name = rs.getString("name");
			int age  = rs.getInt("age");
			String  address = rs.getString("address");
			float salary = rs.getFloat("salary");
			
			System.out.println( "ID = " + id );
			System.out.println( "NAME = " + name );
			System.out.println( "AGE = " + age );
			System.out.println( "ADDRESS = " + address );
			System.out.println( "SALARY = " + salary );
			System.out.println();
		 }
		 rs.close();
		 stmt.close();
		 c.close();
	  } catch ( Exception e ) {
		 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		 System.exit(0);
	  }
	  System.out.println("Operation done successfully");
  }
  
  public static void updateData(){
	  
	  Connection c = null;
	  Statement stmt = null;
	  
	  try {
		 Class.forName("org.sqlite.JDBC");
		 c = DriverManager.getConnection("jdbc:sqlite:test.db");
		 c.setAutoCommit(false);
		 System.out.println("Opened database successfully");
   
		 stmt = c.createStatement();
		 String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
		 stmt.executeUpdate(sql);
		 c.commit();
   
		 ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
		 
		 while ( rs.next() ) {
			int id = rs.getInt("id");
			String  name = rs.getString("name");
			int age  = rs.getInt("age");
			String  address = rs.getString("address");
			float salary = rs.getFloat("salary");
			
			System.out.println( "ID = " + id );
			System.out.println( "NAME = " + name );
			System.out.println( "AGE = " + age );
			System.out.println( "ADDRESS = " + address );
			System.out.println( "SALARY = " + salary );
			System.out.println();
		 }
		 rs.close();
		 stmt.close();
		 c.close();
	  } catch ( Exception e ) {
		 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		 System.exit(0);
	  }
  }
}

