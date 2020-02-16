import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class InsertNewCustomer extends JInternalFrame implements ActionListener {
	JPanel mainPanel, gridPanel, buttonPanel, spinnerPanel, genderPanel;
	JButton addCust;
	JLabel idLbl, idCust, nameLbl, genderLbl, dateLbl, phoneLbl, pic;
	JTextField nameText, phoneText;
	JRadioButton maleButton, femaleButton;
	ButtonGroup genderGroup;
	JComboBox  dateSpinner,monthSpinner, yearSpinner;
	JButton addcust;
	BufferedImage myPicture;
	
	Connection c;

	public void initComponent() {

		// Panel
		mainPanel = new JPanel(new BorderLayout());
		gridPanel = new JPanel(new GridLayout(5, 2));
		buttonPanel = new JPanel(new FlowLayout());
		spinnerPanel = new JPanel(new GridLayout(1, 3));
		genderPanel = new JPanel(new GridLayout(1, 2));

		// Header
		try {
			myPicture = ImageIO.read(new File("image/usericon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pic = new JLabel(new ImageIcon(myPicture));

		// Field
		idLbl = new JLabel("ID");
		idCust = new JLabel();

		nameLbl = new JLabel("Name");
		nameText = new JTextField();

		genderLbl = new JLabel("Gender");
		maleButton = new JRadioButton("Male");
		femaleButton = new JRadioButton("Female");
		genderGroup = new ButtonGroup();
		genderGroup.add(maleButton);
		genderGroup.add(femaleButton);

		dateLbl = new JLabel("Date of Birth");
		List<Integer> date = new ArrayList<Integer>();
		 for(int i=1; i<=31; i++) {
			 date.add(i);
	      }
		 dateSpinner = new JComboBox(date.toArray()); 
		 
		 List<Integer> month = new ArrayList<Integer>();
		 for(int i=1; i<=12; i++) {
			 month.add(i);
	      }
		 monthSpinner = new JComboBox(month.toArray()); 
		 
		 List<Integer> year = new ArrayList<Integer>();
		 for(int i=1900; i<=2010; i++) {
			 year.add(i);
	      }
		 yearSpinner = new JComboBox(year.toArray()); 

		phoneLbl = new JLabel("Phone Number");
		phoneText = new JTextField();
		addcust = new JButton("Add Customer");
	}

	public void addComponent() {
		// Spinner
		spinnerPanel.add(dateSpinner);
		spinnerPanel.add(monthSpinner);
		spinnerPanel.add(yearSpinner);

		// Gender
		genderPanel.add(femaleButton);
		genderPanel.add(maleButton);
		// Grid Panel
		gridPanel.add(idLbl);
		gridPanel.add(idCust);
		gridPanel.add(nameLbl);
		gridPanel.add(nameText);
		gridPanel.add(dateLbl);
		gridPanel.add(spinnerPanel);
		gridPanel.add(genderLbl);
		gridPanel.add(genderPanel);
		gridPanel.add(phoneLbl);
		gridPanel.add(phoneText);

		buttonPanel.add(addcust);
		mainPanel.add(pic, BorderLayout.NORTH);
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		add(mainPanel);
	}

	public void manageComponent() {
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// color
		mainPanel.setBackground(new Color(177, 209, 232));
		gridPanel.setBackground(new Color(177, 209, 232));
		genderPanel.setBackground(new Color(177, 209, 232));
		femaleButton.setBackground(new Color(177, 209, 232));
		maleButton.setBackground(new Color(177, 209, 232));
		buttonPanel.setBackground(new Color(177, 209, 232));
		// Border Field top
		idCust.setBorder(BorderFactory.createMatteBorder(10, 1, 10, 1, new Color(177, 209, 232)));
		nameText.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(5, 1, 15, 1, new Color(177, 209, 232)),
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray)));
		phoneText.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(5, 1, 15, 1, new Color(177, 209, 232)),
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray)));
		spinnerPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(5, 5, 15, 5, new Color(177, 209, 232)),
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray)));
	}

	public void addListener() {
		addcust.addActionListener(this);

	}

	public InsertNewCustomer() {

		super(" ", false, // resizable
				true, // closable
				false, // maximizable
				false);// iconifiable
		// frame
		setSize(500, 500);
		setTitle("Insert New Customer");
		
		c = new Connection();
		initComponent();
		generateID();
		addComponent();
		addListener();
		manageComponent();
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addcust) {
			insertData();
			
		}
	}
	public void generateID(){
		String prefix="US";
		DecimalFormat nf3 = new DecimalFormat("#000");
		String query="SELECT cast(substring(userid,3,3) as int)+1 as id FROM `USERS` order by UserID desc limit 1";
		ResultSet rs = c.executeQuery(query);
		int angkaID=0;
		try {
			if(rs.next()){
				angkaID= rs.getInt("id");	
			}
			String angkaFormat=nf3.format(angkaID);
			String idUserNew=prefix+angkaFormat;
			idCust.setText(idUserNew);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}		
	}
	public void refreshField(){
		generateID();
		nameText.setText("");
		dateSpinner.setSelectedIndex(0);
		monthSpinner.setSelectedIndex(0);
		yearSpinner.setSelectedIndex(0);
		phoneText.setText("");
		genderGroup.clearSelection();
		
	}

	
	public void insertData(){
		String id = idCust.getText();
		String name = nameText.getText();
		//String dd="";
		int dd = (Integer) dateSpinner.getSelectedItem();
		int mm = (Integer) monthSpinner.getSelectedItem();
		int yy = (Integer) yearSpinner.getSelectedItem();
		
		String dob = yy + "-" + mm + "-" + dd;
		
		String gender = "";	
		//gender
		if(maleButton.isSelected()){
			gender = "Male";
		}
		else if(femaleButton.isSelected()){
			gender = "Female";
		}else{
			gender=null;
		}
		
		String query1="select DATEDIFF('"+dob+"',curdate()) as dif";
		ResultSet res=c.executeQuery(query1);
		String phone = phoneText.getText();
		try {
			res.next();
			String dVal=res.getString("dif");
			if(name.isEmpty()){
				JOptionPane.showMessageDialog(null, "Name must be filled!");
			}
			else if(dVal==null){
				JOptionPane.showMessageDialog(this,
						"Date does not exist");
			}
			else if(gender == null){
				JOptionPane.showMessageDialog(null, "Gender must be choose!");
			}
			else if(phone.isEmpty()){
				JOptionPane.showMessageDialog(null, "Phone number must be filled!");
			}

			else if(!phone.matches("[0-9]+")){
				JOptionPane.showMessageDialog(null, "Phone must be numeric!");
			}
			
			else{
				String query = "INSERT INTO users VALUES ('"+id+"','"+name+"','"+dob+"','"+phone+"','"+gender+"')";
				JOptionPane.showMessageDialog(null, "Data saved to Database");
				c.executeUpdate(query);
				refreshField();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
