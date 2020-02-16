import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.sql.ResultSetMetaData;

public class ViewBooking extends JInternalFrame implements ActionListener, MouseListener{
	
	JPanel mainPanel, headerPanel; 
	JTable table;
	JButton updButton;
	DefaultTableModel dtm;
	JScrollPane tableScroll;
	JLabel title;
	Vector<String> vec;
	Connection c;
	String flightID,usernameID;
	public void initComponent(){
		//Panel
		mainPanel = new JPanel(new BorderLayout());
		headerPanel= new JPanel(new BorderLayout());
		
		//component header			
		updButton = new JButton("Update Bookings");	
		title = new JLabel("Booking Details", SwingConstants.LEFT);
		
		//Table
		vec = new Vector<>();
		vec.add("User's Name");
		vec.add("Flight ID");
		vec.add("Airplane's");
		vec.add("Derpature");
		vec.add("Destination");
		vec.add("Date");
		vec.add("Ticket's Qty");
		vec.add("Description");
		vec.add("");
		dtm = new DefaultTableModel(vec,0);		

		table = new JTable(dtm){
			public boolean isCellEditable(int Row, int Column) {
				return false;
			}
		};
		tableScroll = new JScrollPane(table);
		//Hide UserId Column
		table.getColumnModel().getColumn(8).setWidth(0);
		table.getColumnModel().getColumn(8).setMinWidth(0);
		table.getColumnModel().getColumn(8).setMaxWidth(0);			
	}
	
	public void addComponent(){
		
		//add to frame
		headerPanel.add(title, BorderLayout.WEST);
		headerPanel.add(updButton, BorderLayout.EAST);
		mainPanel.add(headerPanel,BorderLayout.NORTH);
		mainPanel.add(tableScroll,BorderLayout.CENTER);
		
		add(mainPanel);
	}	
	
	public void fillTable(){
		String query= 
						"select Username,b.FlightID,Airplanename,(SELECT cityname from cities where cityid=DepartureCityID) as DerpatureCity,"
						+ "(SELECT cityname from cities where cityid=DestinationCityID) as DestinationCity, FlightDate,BookingQty,Additionaldescription,b.UserId "
						+ "from bookings b  join flights f on b.FlightID=f.FlightID "
						+ "join airplanes a on f.AirplaneID=a.AirplaneID join users u on b.UserID=u.UserID order by b.UserId, b.FlightID asc"
						;
		ResultSet rs=c.executeQuery(query);
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
	
	public void manageComponent(){
		//FONT
		title.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
		//BORDER
		tableScroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20), 
				BorderFactory.createCompoundBorder(
						BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black),
		                BorderFactory.createMatteBorder(0, 0, 10,0, new Color(238,238,238))
		        )
		));
		headerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//BACKGROUND COLOR
		headerPanel.setBackground(new Color(177, 209, 232));
		mainPanel.setBackground(new Color(177, 209, 232));
		tableScroll.setBackground(new Color(177, 209, 232));
	}
	
	public void addListener(){
		updButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					JDesktopPane desktopPane=getDesktopPane();
					new UpdateFrame(flightID,usernameID,desktopPane);
					dispose();
				}else if(table.getSelectedRow() == -1){
					JOptionPane.showMessageDialog
			        (null,"Pick any booking first to update!");  
				}
			}
		});
		
		table.addMouseListener(this);

	}
	
	public ViewBooking() {
	  super("" ,
              false, //resizable
              true, //closable
              false, //maximizable
              false);//iconifiable
	  
		//frame setting
		setSize(800, 300);
		setTitle("View All Booking");
		//frame setting
		c=new Connection();
		initComponent();
		addComponent();
		addListener();
		fillTable();
		manageComponent();	
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	@Override
	
	public void mouseReleased(MouseEvent e) {
		if (table.getSelectedRow() != -1) {
			flightID=table.getValueAt(table.getSelectedRow(), 1)
					.toString();
			usernameID=table.getValueAt(table.getSelectedRow(), 8)
					.toString();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}
}
