import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class UpdateFrame extends JFrame implements ActionListener {
	JPanel mainPanel, gridPanel, buttonPanel, spinnerPanel;
	JButton update2;
	JLabel userLabel, userName, flight, ticQty, addDes;
	JTextField addDesField;
	SpinnerModel qty;
	JSpinner qtySpinner;
	String[] flightID;
	JComboBox<ComboRoles> comboFlightID;
	// Tampung Data
	String getFlightID, getUsernameID, getUsername, getDesc;
	int getBookingQty;
	JDesktopPane dPane;
	Connection c;
	

	public void initComponent() {
		mainPanel = new JPanel(new BorderLayout());
		gridPanel = new JPanel(new GridLayout(4, 2));
		buttonPanel = new JPanel(new FlowLayout());
		spinnerPanel = new JPanel(new GridLayout(1, 1));

		// isinya
		userLabel = new JLabel("User's Name	");
		userName = new JLabel(getUsername);
		flight = new JLabel("Flight ID");
		ticQty = new JLabel("Tickets Qty");
		addDes = new JLabel("Additional Description");
		addDesField = new JTextField(getDesc);
		qty = new SpinnerNumberModel(getBookingQty, 0, 100, 1);
		qtySpinner = new JSpinner(qty);

		update2 = new JButton("Update");
	}

	public void dataCombobox() {
		comboFlightID = new JComboBox<ComboRoles>();

		// Query
		String queryFlight = "SELECT FlightID FROM flights";
		// Fill combobox
		fillComboBox(queryFlight, "FlightID", comboFlightID);

		selectItemByString(getFlightID, comboFlightID);

	}

	public void fillComboBox(String query, String p1, JComboBox<ComboRoles> comboBox) {
		ResultSet rs = c.executeQuery(query);
		try {
			Vector<ComboRoles> vector = new Vector<>();
			while (rs.next()) {

				String param1 = rs.getString(p1);
				vector.addElement(new ComboRoles(param1, param1));
				comboBox.setModel(new DefaultComboBoxModel<ComboRoles>(vector));
				comboBox.setRenderer(new ItemRenderer());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to Connect to Database", "Error Connection",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}

	// get selected value
	private static void selectItemByString(String s, JComboBox<ComboRoles> comboBox) {
		ComboRoles item;
		for (int i = 0; i < comboBox.getItemCount(); i++) {
			item = (ComboRoles) comboBox.getItemAt(i);
			if (item.getID().equals(s)) {
				comboBox.setSelectedIndex(i);
				break;
			}
		}
	}

	public void addComponent() {
		// Spinner
		spinnerPanel.add(qtySpinner);
		// Grid
		gridPanel.add(userLabel);
		gridPanel.add(userName);
		gridPanel.add(flight);
		gridPanel.add(comboFlightID);
		gridPanel.add(ticQty);
		gridPanel.add(spinnerPanel);
		gridPanel.add(addDes);
		gridPanel.add(addDesField);

		buttonPanel.add(update2);

		mainPanel.add(gridPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		add(mainPanel);
	}

	public void manageComponent() {
		// Border
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Color Background
		gridPanel.setBackground(new Color(177, 209, 232));
		mainPanel.setBackground(new Color(177, 209, 232));
		buttonPanel.setBackground(new Color(177, 209, 232));

		// Field Color
		comboFlightID.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(15, 1, 5, 1, new Color(177, 209, 232)),
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray)));
		spinnerPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(5, 1, 5, 1, new Color(177, 209, 232)),
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray)));
		addDesField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(5, 1, 5, 1, new Color(177, 209, 232)),
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray)));
	}

	public void updateBookingData() {
		ComboRoles flightItem = (ComboRoles) comboFlightID.getSelectedItem();
		String newFlightId = flightItem.getID();
		int newBookingQty = (int) qtySpinner.getValue();
		String newDescription = addDesField.getText();
		String val="";
		String query1="SELECT flightid,UserID,BookingQty,AdditionalDescription FROM bookings WHERE userid= '"+getUsernameID+"' AND FlightID='"+newFlightId+"'";
		ResultSet rs = c.executeQuery(query1);
	
		try {
			if(rs.next()){
				val="DoubleData";
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(val.equals("DoubleData")&&!newFlightId.equals(getFlightID)){
			JOptionPane.showMessageDialog(null, "Choose another flight");
		}	
		else if(newBookingQty==0){
			JOptionPane.showMessageDialog(null, "Ticket quantity must be more than zero!");
		}
		else{
			String query = "update bookings set flightID = '" + newFlightId + "', bookingqty= " + newBookingQty
					+ ", additionaldescription = '" + newDescription + "' " + "where flightID ='" + getFlightID
					+ "' &&  userID ='" + getUsernameID + "'";

			c.executeUpdate(query);
			JOptionPane.showMessageDialog(this, newFlightId + " - Has been updated to Database");
			dispose();
			dPane.moveToFront(dPane.add(new ViewBooking()));
		}
		
	}

	public void addListener() {
		update2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateBookingData();
			}
		});
	}

	public void getDataViewBook() {
		String query = "select username,bookingqty, additionaldescription from bookings b"
				+ " join users u on b.userid=u.userid where b.userid='" + getUsernameID + "' and flightid='"
				+ getFlightID + "'";
		ResultSet rs = c.executeQuery(query);
		try {
			rs.next();
			getUsername = rs.getString("username");
			getBookingQty = rs.getInt("bookingqty");
			getDesc = rs.getString("additionaldescription");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public UpdateFrame(String pFlightID, String pUserID, JDesktopPane desktopPane) {
		dPane=desktopPane;
		getFlightID = pFlightID;
		getUsernameID = pUserID;
		c = new Connection();
		setSize(500, 300);
		setLocationRelativeTo(null);
		setTitle("Update Booking");
		getDataViewBook();
		initComponent();
		dataCombobox();
		addComponent();
		manageComponent();
		getDataViewBook();
		addListener();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
