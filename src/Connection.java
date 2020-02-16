import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Connection {
	Statement stmt;
	public Connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/lave","root","");
			stmt = con.createStatement(); 
			System.out.println("Connected with DB");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Failed to Connect to Database"); 
			System.exit(0);
		}
	}
	public ResultSet executeQuery(String sql){
		ResultSet rs=null;
		try {
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
    public void executeUpdate(String sql) {
    	try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    


}
