import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainPage extends JFrame implements ActionListener{
	
	JMenuBar mb;
	JMenu menu1,menu2;
	JMenuItem itemMg1,itemMg2,itemMg3, itemV1,itemV2,itemV3;
	JDesktopPane desktop;
	Image img;

	public void initComponent(){
		mb = new JMenuBar();
		menu1= new JMenu("View");
		menu2= new JMenu("Manage");
		
		itemV1=new JMenuItem("View All Bookings");
		itemV2= new JMenuItem("Next Flights");
		itemV3= new JMenuItem("Previous Flights");
		
		itemMg1=new JMenuItem("New Booking");
		itemMg2= new JMenuItem("Manage Customer");
		itemMg3= new JMenuItem("Manage Flights");
		
		try {
				img= ImageIO.read(new File("Image/Background.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			desktop=new JDesktopPane(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
				}
			}; 
			
	}
	
	public void addComponent(){
		setContentPane(desktop);
		menu1.add(itemV1);
		menu1.addSeparator();
		menu1.add(itemV2);
		menu1.add(itemV3);
		
	
		menu2.add(itemMg1);
		menu2.add(itemMg2);
		menu2.add(itemMg3);
		
		mb.add(menu1);		
		mb.add(menu2);
				
		setJMenuBar(mb);	
		itemV1.addActionListener(this);
		itemV2.addActionListener(this); 
		itemV3.addActionListener(this); 
		itemMg1.addActionListener(this);
		itemMg2.addActionListener(this); 
		itemMg3.addActionListener(this); 
	}
	
	public MainPage() {
		
		setSize(1300,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("LaVe's Airline");
		
		initComponent();
		addComponent();
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==itemV1){
			desktop.moveToFront(desktop.add(new ViewBooking()));
		}
		else if(e.getSource()==itemV2){
			desktop.moveToFront(desktop.add(new NextFlightsDetail()));
		}

		else if(e.getSource()==itemV3){
			desktop.moveToFront(desktop.add(new PrevFlightsDetail()));
		}
		else if(e.getSource()==itemMg1){
			desktop.moveToFront(desktop.add(new InsertNewBooking()));
		}
		else if(e.getSource()==itemMg2){
			desktop.moveToFront(desktop.add(new InsertNewCustomer()));
		}
		else if(e.getSource()==itemMg3){
			desktop.moveToFront(desktop.add(new ManageFlights()));
		}
	}

}
