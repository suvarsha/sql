package jdbc;
import java.sql.*;
public class procefure1 {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/test";
	   static final String username = "root";
	   static final String password = "root";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   CallableStatement stmt = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,username,password);
	      System.out.println("Creating statement...");
	      String sql = "{call getempname (?, ?)}";
	      stmt = conn.prepareCall(sql);
	      int eid = 3;
	      stmt.setInt(1, eid);
	      stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
	      System.out.println("Executing stored procedure..." );
	      stmt.execute();
	      String empname = stmt.getString(2);
	      System.out.println("Emp Name with ID:" + 
	               eid + " is " + empname);
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   System.out.println("Goodbye!");
	}
}
