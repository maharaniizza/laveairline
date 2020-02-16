import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class InsertNewBooking extends JInternalFrame implements ActionListener {
	JPanel mainPanel, topFieldPanel, topButtonPanel, botFieldPanel, botButtonPanel, centerPanel, topPanel, bottomPanel,
			outSpinPanel, cpright;
	JLabel userIDLabel, flightIDLabel, quantityLabel, noteLabel, userNameLabel, airplaneNameLabel, departureLabel,
			destinationLabel, dateLabel, ticketQtyLabel, totalPriceLabel, descriptionLabel, uNText, aNText, deparText,
			desText, dateText, tQText, tPText, descText;
	JTextArea noteText;
	SpinnerModel quantityModel;
	JSpinner quantitySpinner;
	JButton createButton, showDIButton;

	JComboBox<ComboRoles> userIDCB, flightIDCB;
	
	//FIELD
	// Validate
	ComboRoles userItemV,flightItemV;
	String userIDV ,flightIDV ,descV;
	int bookingQTYV;
	// Data FIX (Yang Ke Save Yang Dlm Show Info , Kalau data diubah harus klik  Show info Lagi)
	String userIDF ,flightIDF ,descF;
	int bookingQTYF;
	Connection c;

	public void initComponent() {
		// Panel
		mainPanel = new JPanel(new BorderLayout());
		topFieldPanel = new JPanel(new GridLayout(4, 2));
		topButtonPanel = new JPanel(new FlowLayout());
		botFieldPanel = new JPanel(new GridLayout(8, 2));
		botButtonPanel = new JPanel(new FlowLayout());
		centerPanel = new JPanel(new GridLayout(2, 1));
		topPanel = new JPanel(new BorderLayout());
		bottomPanel = new JPanel(new BorderLayout());
		outSpinPanel = new JPanel(new GridLayout(1, 1));
		cpright = new JPanel(new BorderLayout());

		// Top Field
		userIDLabel = new JLabel("User ID");
		flightIDLabel = new JLabel("Flight ID");
		quantityLabel = new JLabel("Quantity");
		noteLabel = new JLabel("Additional Note");

		// Top
		noteText = new JTextArea();
		quantityModel = new SpinnerNumberModel(0, 0, 15, 1);
		quantitySpinner = new JSpinner(quantityModel);

		// Top Button
		showDIButton = new JButton("Show Detailed Information");

		// Bottom Field
		userNameLabel = new JLabel("User's Name");
		airplaneNameLabel = new JLabel("Airplane's Name");
		departureLabel = new JLabel("Departure");
		destinationLabel = new JLabel("Destination");
		dateLabel = new JLabel("Date");
		ticketQtyLabel = new JLabel("Ticket's Qty");
		totalPriceLabel = new JLabel("Total Price");
		descriptionLabel = new JLabel("Additional Description");

		uNText = new JLabel("-");
		aNText = new JLabel("-");
		deparText = new JLabel("-");
		desText = new JLabel("-");
		dateText = new JLabel("-");
		tQText = new JLabel("-");
		tPText = new JLabel("-");
		descText = new JLabel("-");

		// Bottom button
		createButton = new JButton("Create Booking");
		createButton.setEnabled(false);
	}

	public void dataCombobox() {
		// TOP Component Combobox
		userIDCB = new JComboBox<ComboRoles>();
		flightIDCB = new JComboBox<ComboRoles>();

		// Query
		String queryUser = "SELECT UserID,UserName FROM users";
		String queryFlight = "SELECT FlightID, (SELECT cityname from cities where cityid=DepartureCityID) as DerpatureCity,"
				+ " (SELECT cityname from cities where cityid=DestinationCityID) as DestinationCity,"
				+ "DepartureCityID,DestinationCityID FROM flights where flightdate>curdate()";

		// Fill combobox
		fillComboBox(queryUser, "UserID", "UserName", null, userIDCB);
		fillComboBox(queryFlight, "FlightID", "DerpatureCity", "DestinationCity", flightIDCB);

	}

	public void fillComboBox(String query, String p1, String p2, String p3, JComboBox<ComboRoles> comboBox) {
		ResultSet rs = c.executeQuery(query);
		try {
			Vector<ComboRoles> vector = new Vector<>();
			while (rs.next()) {
				if (p3 == null) {
					String param1 = rs.getString(p1);
					String param2 = rs.getString(p2);
					vector.addElement(new ComboRoles(param1, param1 + " - " + param2));
				} else {
					String param1 = rs.getString(p1);
					String param2 = rs.getString(p2);
					String param3 = rs.getString(p3);
					vector.addElement(new ComboRoles(param1, param1 + " - from " + param2 + " to " + param3));
				}
				comboBox.setModel(new DefaultComboBoxModel<ComboRoles>(vector));
				comboBox.setRenderer(new ItemRenderer());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to Connect to Database", "Error Connection",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}

	public void addComponent() {
		outSpinPanel.add(quantitySpinner);

		// Top Field Panel
		topFieldPanel.add(userIDLabel);
		topFieldPanel.add(userIDCB);
		topFieldPanel.add(flightIDLabel);
		topFieldPanel.add(flightIDCB);
		topFieldPanel.add(quantityLabel);
		topFieldPanel.add(outSpinPanel);
		topFieldPanel.add(noteLabel);
		topFieldPanel.add(noteText);

		// Top Button Panel

		topButtonPanel.add(showDIButton);

		// Bottom Field Panel
		botFieldPanel.add(userNameLabel);
		botFieldPanel.add(uNText);

		botFieldPanel.add(airplaneNameLabel);
		botFieldPanel.add(aNText);

		botFieldPanel.add(departureLabel);
		botFieldPanel.add(deparText);

		botFieldPanel.add(destinationLabel);
		botFieldPanel.add(desText);

		botFieldPanel.add(dateLabel);
		botFieldPanel.add(dateText);

		botFieldPanel.add(ticketQtyLabel);
		botFieldPanel.add(tQText);

		botFieldPanel.add(totalPriceLabel);
		botFieldPanel.add(tPText);

		botFieldPanel.add(descriptionLabel);
		botFieldPanel.add(descText);

		// Bottom Button Panel

		botButtonPanel.add(createButton);

		// top panel
		topPanel.add(topFieldPanel, BorderLayout.CENTER);
		topPanel.add(topButtonPanel, BorderLayout.SOUTH);

		// bottom panel
		bottomPanel.add(botFieldPanel, BorderLayout.CENTER);
		bottomPanel.add(botButtonPanel, BorderLayout.SOUTH);

		// center panel
		centerPanel.add(topPanel);
		centerPanel.add(bottomPanel);
		
		
		//Copyright	
		JLabel A= new JLabel("<html><body>&copy LaVe's Airline </body></html>", SwingConstants.RIGHT);
		cpright.add(A,BorderLayout.EAST);
				
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(cpright, BorderLayout.SOUTH);
		add(mainPanel);

	}

	public void manageComponent() {
		// COLOR
		setBackColor(topFieldPanel);
		setBackColor(topButtonPanel);
		setBackColor(botFieldPanel);
		setBackColor(botButtonPanel);
		cpright.setBackground (new Color(177, 209, 232));
	
		// Border
		topPanel.setBorder(BorderFactory.createMatteBorder(20, 20, 5, 20, new Color(177, 209, 232)));
		cpright.setBorder(BorderFactory.createMatteBorder(0, 20, 5, 20, new Color(177, 209, 232)));
		bottomPanel.setBorder(BorderFactory.createMatteBorder(5, 20, 0, 20, new Color(177, 209, 232)));
		topFieldPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		botFieldPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Border Field top
		setBorderField(userIDCB);
		setBorderField(flightIDCB);
		setBorderField(noteText);
		setBorderField(outSpinPanel);
		// Font
		setFontText(uNText);
		setFontText(aNText);
		setFontText(deparText);
		setFontText(descText);
		setFontText(dateText);
		setFontText(tQText);
		setFontText(tPText);
		setFontText(descText);
	}

	public void setBackColor(JPanel arg) {
		arg.setBackground(new Color(228, 255, 255));
	}

	public void setFontText(JLabel label) {
		label.setFont(new Font("Arial", Font.BOLD, 13));
	}

	public void setBorderField(JComponent arg) {
		arg.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(10, 5, 5, 5, new Color(228, 255, 255)),
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray)));
	}

	public void addListener() {
		createButton.addActionListener(this);
		showDIButton.addActionListener(this);
	}

	public void showDetailInfo() {
		userIDF=userIDV;
		flightIDF=flightIDV;
		descF=descV;
		bookingQTYF=bookingQTYV;
		String qtyS = Integer.toString(bookingQTYF);
		tQText.setText(qtyS);
		descText.setText(descF);			
		int fPrice = 0;
		String tPrice ;
		

		// QUERY USER
		String queryUser = "SELECT UserName FROM users WHERE UserID='" + userIDF + "'";
		ResultSet rs = c.executeQuery(queryUser);

		try {
			rs.next();
			uNText.setText(rs.getString("UserName"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// QUERY FLIGHT
		String queryFlight = "SELECT AirplaneName , (SELECT cityname from cities where cityid=DepartureCityID) as DerpatureCity,"
				+ "(SELECT cityname from cities where cityid=DestinationCityID) as DestinationCity,DATE_FORMAT(FlightDate,'%d %b %Y') as FlightDate,FlightPrice from "
				+ "flights f join airplanes p on f.AirplaneID=p.AirplaneID  where FlightID='" + flightIDF + "'";
		ResultSet rst = c.executeQuery(queryFlight);
		try {
			rst.next();
			aNText.setText(rst.getString(1));
			deparText.setText(rst.getString("DerpatureCity"));
			desText.setText(rst.getString("DestinationCity"));
			dateText.setText(rst.getString("FlightDate"));
			fPrice = rst.getInt("FlightPrice");
			tPrice = ""+fPrice * bookingQTYF +" (in thousands rupiah)";
			tPText.setText(tPrice);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void insertData(){
		String query = "insert into bookings values('" + flightIDF + "','" + userIDF + "'," + bookingQTYF + ",'"
		+ descF + "')";
		c.executeUpdate(query);		
		JOptionPane.showMessageDialog(this, "Data Saved! " +flightIDF + " is booked by " +userIDF);
		refreshDataTop();
		refreshDataBot();
	}
	
	public void refreshDataTop(){
		userIDCB.setSelectedIndex(0);
		flightIDCB.setSelectedIndex(0);
		quantitySpinner.setValue(0);
		noteText.setText("");
	}
	public void refreshDataBot(){
		createButton.setEnabled(false);
		uNText.setText("-");
		aNText.setText("-");
		deparText.setText("-");
		desText.setText("-");
		dateText.setText("-");
		tQText.setText("-");
		tPText.setText("-");
		descText.setText("-");
		
	}


	public void validateBooking() {
		userItemV = (ComboRoles) userIDCB.getSelectedItem();
		flightItemV = (ComboRoles) flightIDCB.getSelectedItem();
		userIDV = userItemV.getID();
		flightIDV = flightItemV.getID();

		bookingQTYV = (int) quantitySpinner.getValue();

		descV = noteText.getText();
		String query1 = "select * from bookings where flightID='" + flightIDV + "' and userID='" + userIDV + "'";
		ResultSet rs = c.executeQuery(query1);
		
		try {
			if (rs.next()) {
				String[] options={"Update","Cancel"};
				int x=JOptionPane.showOptionDialog(null, "This User is Already Booked the Flight. Please Update to Change the Data.", 
						"Failed to Save Data", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				if(x==0){	
					JDesktopPane desktopPane=getDesktopPane();
					desktopPane.moveToFront(desktopPane.add(new ViewBooking()));
				}	                                	                                                       
			}
			
			else if(bookingQTYV==0){
				JOptionPane.showMessageDialog(null,"Quantity must be more than zero!");
			}
			
			else {
				showDetailInfo();
				createButton.setEnabled(true);

			}
			
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public InsertNewBooking() {
		super("", false, true, false, false);

		// frame settings
		setSize(800, 600);
		setTitle("Insert New Booking");
		// frame settings
		c = new Connection();
		initComponent();
		dataCombobox();
		addComponent();
		addListener();
		manageComponent();
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createButton) {
			insertData();
		} else if (e.getSource() == showDIButton) {
			validateBooking();
		
		}
	}

}
