import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PrevFlightsDetail extends JInternalFrame{
	JTable tableScroll;
	DefaultTableModel dtm;
	JScrollPane scroll;
	Vector<String> vecHead;
	JPanel mainPanel;
	JLabel listLabel;
	Connection c;
	
	public void initComponent(){
		mainPanel=new JPanel(new BorderLayout());	
		
		//Label Header
		listLabel=new JLabel("Previous Available Flights");	
				
		//Table
		vecHead= new Vector<>();
		vecHead.add("ID");
		vecHead.add("Airplane");
		vecHead.add("Departure"); 
		vecHead.add("Destination"); 
		vecHead.add("Date"); 
		vecHead.add("Duration"); 
		vecHead.add("Price"); 
		dtm= new DefaultTableModel(vecHead,0); //baris 0
		tableScroll= new JTable(dtm){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scroll= new JScrollPane(tableScroll);
	}
	
	public void addComponent(){
		
		mainPanel.add(listLabel, BorderLayout.NORTH);
		mainPanel.add(scroll, BorderLayout.CENTER);
		add(mainPanel);
		
	}
	
	public void manageComponent(){
		//Background Color
		mainPanel.setBackground(new Color(177, 209, 232));
		scroll.setBackground(new Color(177, 209, 232));
	
		//Border
		scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), 
				BorderFactory.createCompoundBorder(
						BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black),
		                BorderFactory.createMatteBorder(0, 0, 10,0, new Color(238,238,238))
		        )
		));
		listLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 2, 2));
		//Font
		listLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
			
	}
	
	public void dataComponent(){	
		for(int i=0;i<20;i++){
			dtm.addRow(new Object[]
					{"FLG001","Duille Cirrus","London","Tokyo","2018-02-01","945","13700000"}
			
					);
		}
	}

	public void fillTable(){
		String query= "Select FlightID, AirplaneName , (SELECT cityname from cities where cityid=DepartureCityID) as DerpatureCity ,"
				+ "(SELECT cityname from cities where cityid=DestinationCityID) as DestinationCity, FlightDate, FlightDuration, FlightPrice "
				+ " FROM flights f JOIN airplanes a on f.AirplaneID=a.AirplaneID where flightdate<curdate() order by FlightDate asc";
		ResultSet rs=c.executeQuery(query);
		dtm=new DefaultTableModel(vecHead,0);
		try {
			while(rs.next()){
				Vector<Object> vecRow=new Vector<>();
				ResultSetMetaData rsm=rs.getMetaData();
				for(int i=1;i<=rsm.getColumnCount();i++){
					vecRow.add(rs.getObject(i));
				}
				dtm.addRow(vecRow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tableScroll.setModel(dtm);	
	}
	
	public PrevFlightsDetail() {
		
	  super("" ,
              false, //resizable
              true, //closable
              false, //maximizable
              false);//iconifiable
	  
	  //frame setting
	  setSize(800, 300); 
	  setTitle("Flight Details");	
	  c= new Connection();
	  initComponent();
	  addComponent(); 
	  fillTable();
	  manageComponent(); 
	  setVisible(true);
	}
}
