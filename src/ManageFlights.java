import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ManageFlights extends JInternalFrame implements ActionListener{
	JPanel mainPanel, centerPanel, gridPanel, buttonPanel, spinnerPanel; 
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scrollTable;
	Vector<String> vecHead;
	
	JLabel flightIDLabel, airplaneNameLabel, departureLabel, destinationLabel,
			dateLabel, durationLabel, priceLabel,monthLabel,headerLabel;
	JTextField flightIDText,dateText,durationText,priceText,flightDelete;
	
	JComboBox<ComboRoles> airplaneNameCB,departureCB,destinationCB ;
	String[] stringAirplaneName,stringDeparture,stringDestination ;
	JSpinner dateSpinner,monthSpinner,yearSpinner, durationSpinner;
	JButton insertButton,deleteButton;
	Connection c;
	String date;
	
	public void initComponent(){
		
		//panel
		mainPanel=new JPanel(new BorderLayout());
		centerPanel = new JPanel(new GridLayout(2,1));
		gridPanel = new JPanel(new GridLayout(7,2));
		buttonPanel = new JPanel(new FlowLayout());
		spinnerPanel= new JPanel(new GridLayout(1,3));
		
		//Header
		headerLabel = new JLabel("Manage Airplane", SwingConstants.CENTER);
		
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
		table= new JTable(dtm){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollTable= new JScrollPane(table);
		
		//Label for Tabel Panel
				flightIDLabel = new JLabel("Flight ID");
				airplaneNameLabel = new JLabel("Airplane Name");
				departureLabel = new JLabel("Departure City");
				destinationLabel = new JLabel("Destination City");
				dateLabel = new JLabel("Date");
				durationLabel = new JLabel("Duration");
				priceLabel = new JLabel("Price");
				
				//JText Field
				flightIDText = new JTextField();
				flightDelete = new JTextField();
				flightIDText.setEnabled(false);
				dateText = new JTextField();
				durationText = new JTextField();
				priceText = new JTextField();
				
		//Month Field
		monthLabel = new JLabel("Date");
			//date
			dateSpinner = new JSpinner(new SpinnerNumberModel(1,0,31,1));
	
			//month
			monthSpinner = new JSpinner(new SpinnerNumberModel(1,0,12,1));
	
			//year
			yearSpinner = new JSpinner(new SpinnerNumberModel(2020,2020,2025,1));
	        yearSpinner.setEditor(new JSpinner.NumberEditor(yearSpinner, "#"));
    	
	    //duration
	        durationSpinner = new JSpinner(new SpinnerNumberModel(60,60,900,1));
	        
	    //button panel
		insertButton = new JButton("Insert");
		deleteButton = new JButton("Delete");
		deleteButton.setEnabled(false);
	}
	
	public void addComponent(){		
		
		//Spinner
		spinnerPanel.add(dateSpinner);
		spinnerPanel.add(monthSpinner);
		spinnerPanel.add(yearSpinner);
		
		//Grid Panel
		gridPanel.add(flightIDLabel);
		gridPanel.add(flightIDText);
		gridPanel.add(airplaneNameLabel);
		gridPanel.add(airplaneNameCB);
		gridPanel.add(departureLabel);
		gridPanel.add(departureCB);
		gridPanel.add(destinationLabel);
		gridPanel.add(destinationCB);
		gridPanel.add(monthLabel);
		gridPanel.add(spinnerPanel);
		gridPanel.add(durationLabel);
		gridPanel.add(durationSpinner);
		gridPanel.add(priceLabel);
		gridPanel.add(priceText);	
		
		//Center Panel
		centerPanel.add(scrollTable);
		centerPanel.add(gridPanel);
		
		//Button Panel
		buttonPanel.add(insertButton);
		buttonPanel.add(deleteButton);
		
		//Main Panel
		mainPanel.add(headerLabel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel,BorderLayout.SOUTH);
		
		add(mainPanel);

	}
	
	public void dataComponent(){
		
		airplaneNameCB = new JComboBox<ComboRoles>();
		departureCB = new JComboBox<ComboRoles>();
		destinationCB = new JComboBox<ComboRoles>();
		
		String Airplane="SELECT AirplaneID,AirplaneName FROM airplanes";
		String Derpature="SELECT CityID,CityName FROM cities";
		String Destination="SELECT CityID,CityName FROM cities";
		
		String airplaneDefault="--AIRPLANES--";
		String cityDefault="--CITIES--";
		
		fillComboBox(Airplane,"AirplaneID","AirplaneName",airplaneNameCB, airplaneDefault);
		fillComboBox(Derpature,"CityID","CityName",departureCB, cityDefault);
		fillComboBox(Destination,"CityID","CityName",destinationCB, cityDefault);						
	}
	
	public void fillComboBox(String query,String p1, String p2, JComboBox<ComboRoles> comboBox, String defaultCB){
		ResultSet rs=c.executeQuery(query);
		try {  
			Vector<ComboRoles> vector = new Vector<>();
			vector.addElement(new ComboRoles("defaultCB", defaultCB));
			while(rs.next()){
				String param1 = rs.getString(p1);
				String param2 = rs.getString(p2);
				vector.addElement(new ComboRoles(param1, param1+" - "+param2));  		
			}
			comboBox.setModel(new DefaultComboBoxModel<ComboRoles>(vector));   
			comboBox.setRenderer(new ItemRenderer());
		} catch (Exception e) {  
			JOptionPane.showMessageDialog(null,"Failed to Connect to Database","Error Connection", JOptionPane.WARNING_MESSAGE);  
			System.exit(0);  
		}  		
	}
	
	public void manageComponent(){
		headerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		//Border
		scrollTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), 
				BorderFactory.createCompoundBorder(
						BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black),
		                BorderFactory.createMatteBorder(0, 0, 10,0, new Color(238,238,238))
		        )
		));
		gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	
		//Background Color	
		scrollTable.setBackground(new Color(177,209,232));
		mainPanel.setBackground(new Color(177,209,232));
		centerPanel.setBackground(new Color(177,209,232));
		buttonPanel.setBackground(new Color(177,209,232));
		gridPanel.setBackground(new Color(177,209,232));
		
	}
	
	public void fillTable(){
		String query= "Select FlightID, AirplaneName , (SELECT cityname from cities where cityid=DepartureCityID) as DerpatureCity ,"
				+ "(SELECT cityname from cities where cityid=DestinationCityID) as DestinationCity, FlightDate, FlightDuration, FlightPrice "
				+ " FROM flights f JOIN airplanes a on f.AirplaneID=a.AirplaneID order by FlightID asc "
				;
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
		table.setModel(dtm);	
	}
	
	public void addListener(){
		insertButton.addActionListener(this);
		deleteButton.addActionListener(this);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(table.getSelectedRow()!=-1){
					deleteButton.setEnabled(true);
					int selectedRow=table.rowAtPoint(e.getPoint());
					String flightID=(String)table.getModel().getValueAt(selectedRow, 0);
					flightDelete.setText(flightID);
				}
			}
		});
	}
	
	public void deleteFlight(){
		String flightID = flightDelete.getText();
		String query="delete from flights where flightid='"+flightID+"'";
		c.executeUpdate(query);
		JOptionPane.showMessageDialog (null,flightID+ " has been succcessfully deleted");
		fillTable();
		generateID();
	}
	
	public void generateID(){
		String prefix = "FLGH";
		DecimalFormat nf3 = new DecimalFormat("#000");
		
		String query = "select cast(substring(flightID,5,3) as int)+ 1 as id "
				+ "from flights order by flightID desc limit 1";
		
		ResultSet rs = c.executeQuery(query);
			int angkaID = 0;
				
				try {
					if(rs.next()){
						angkaID = rs.getInt("id");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
	
				String angkaFormat=nf3.format(angkaID);
				String idFlightNew = prefix+angkaFormat;
				flightIDText.setText(idFlightNew);
			
	}
	
	public void getInsertData(){
		String flightID=flightIDText.getText();
		
		//From Combo Box
		ComboRoles airplaneItem = (ComboRoles)airplaneNameCB.getSelectedItem();
	    ComboRoles dpItem = (ComboRoles)departureCB.getSelectedItem();
	    ComboRoles dsItem = (ComboRoles)destinationCB.getSelectedItem();
	    String airplaneID=airplaneItem.getID();
	    String dpID=dpItem.getID();
	    String dsID=dsItem.getID();
		
	    //From Spinner
	    int dd=(int) dateSpinner.getValue();
	    int mm=(int) monthSpinner.getValue();
	    int yy=(int) yearSpinner.getValue();
	    
		date=yy+"-"+mm+"-"+dd;
	    int duration= (int) durationSpinner.getValue();
	    String price=priceText.getText().trim();
	    int dur=0;
	    int p=0;	
		
		String query1="select DATEDIFF('"+date+"',curdate()) as dif";
		ResultSet res=c.executeQuery(query1);
		
		try {
			res.next();
			String dVal = res.getString("dif");
			int valdate=res.getInt("dif");
			if(airplaneID.equals("defaultCB")){
				JOptionPane.showMessageDialog(this,
				"Airplane's Name must not be default");
			}
			else if(dpID.equals("defaultCB")){
				JOptionPane.showMessageDialog(this,
				"Departure city must not be default");
			}
			else if(dsID.equals("defaultCB")){
				JOptionPane.showMessageDialog(this,
				"Destination city must not be default");
			}
			else if(dVal == null){
				JOptionPane.showMessageDialog(this,
						"Date does not exist");	
			}
			else if(valdate<7){
				JOptionPane.showMessageDialog(this,
						"Date must be more than a week from now.");
			}
	    	else if(price.length()==0){
				JOptionPane.showMessageDialog
		        (null,"Price must be filled");
			}
	    	else if(!price.matches("[0-9]+")){
				JOptionPane.showMessageDialog(null, "Price must be numeric!");
			}
	    	else{
	    		p=Integer.parseInt(price);
	    		if(duration<60 || duration>900){
			    	JOptionPane.showMessageDialog(this,
					"Duration has to be between 60 and 900");
			    }
	    		else if(p ==0 || p %750000!=0){
			    	JOptionPane.showMessageDialog(this,
					"Price must be multiple of 750.000");
	    		}else{
					insertData(airplaneID, dpID, dsID, date, duration, p);	
					refreshField();
	    		}
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
	public void refreshField(){
		generateID();
		airplaneNameCB.setSelectedIndex(0);
		departureCB.setSelectedIndex(0);
		destinationCB.setSelectedIndex(0);
		durationSpinner.setValue(60);
		priceText.setText("");
		dateSpinner.setValue(1);
		monthSpinner.setValue(1);
		yearSpinner.setValue(2020);
	}
	
	
	public void insertData(String airplaneID, String dpID, String dsID, String date, int duration, int price){
		String flightID=flightIDText.getText();
		
		String query="insert into flights values('"+flightID+"','"+airplaneID+"','"+dpID+"','"+dsID+"',"
				+ "'"+date+"',"+duration+","+price+")";

		
		// INSERT UPDATE
		c.executeUpdate(query);
		JOptionPane.showMessageDialog(this,
				flightID+ " Saved to Database");
		fillTable();		
	}
	
	public ManageFlights() {

	  super(" " ,
              false, //resizable
              true, //closable
              false, //maximizable
              false);//iconifiable
	  
	//frame settings
		setSize(800,600);	
		setTitle("Manage Flights");
	//frame settings	
		c= new Connection();
		initComponent();
		dataComponent();
		generateID();
		addComponent();
		addListener();
		fillTable();
		manageComponent();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==insertButton){
			getInsertData();
		
			
		}else if(e.getSource()==deleteButton){
			int dialogResult = JOptionPane.showConfirmDialog(null,"This action will delete related flight data. Continue to delete?", "Confirmation",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
			if(dialogResult == JOptionPane.YES_OPTION){
				deleteFlight();
			}                                                              
			
		}
	}

}
