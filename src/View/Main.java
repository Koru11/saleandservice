package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblNewLabel = new JLabel("");
	JLabel lblComputer = new JLabel("Computer Sale and Service");
	JLabel lblAdmin = new JLabel("Admin Dashboard");

	ImageIcon dabIcon;
	private JButton btnBrand;
	private JButton btnCompany;
	private JButton btnItem;
	private JButton btnType;
	private JButton btnPosition;
	private JButton btnDepartment;
	private JButton btnStaff;
	private JButton btnCustomer;
	private JButton btnPayment;
	private JButton btnSaleOrder;
	private JButton btnPurchase;
	private JButton btnService;
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	JPanel panel_1_1 = new JPanel();
	private JButton btnBrand_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
//					frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		ArrayList<JFrame> jarray = new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setBounds(0, 0, 1100, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		FlatLightLaf.setup();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnBrand_1 = new JButton("Logout");
		btnBrand_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				LoginView lv;
				try {
					lv = new LoginView();
					for(JFrame jf:jarray) {
						jf.dispose();
					}
					lv.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBrand_1.setForeground(new Color(0, 128, 128));
		btnBrand_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnBrand_1.setBackground(new Color(224, 255, 255));
		btnBrand_1.setBounds(909, 598, 163, 33);
		contentPane.add(btnBrand_1);
		
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setForeground(new Color(0, 206, 209));
		lblAdmin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblAdmin.setBounds(200, 98, 500, 37);
		contentPane.add(lblAdmin);
		
		lblComputer.setHorizontalAlignment(SwingConstants.CENTER);
		lblComputer.setForeground(new Color(0, 206, 209));
		lblComputer.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 30));
		lblComputer.setBounds(200, 48, 500, 37);
		contentPane.add(lblComputer);
		panel.setBorder(new LineBorder(new Color(32, 178, 170)));
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(23, 180, 266, 300);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Stock Item Input");
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(31, 11, 202, 33);
		panel.add(lblNewLabel_1);
		
		btnBrand = new JButton("Brand");
		btnBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					BrandPanel	bp = new BrandPanel();
					DataInputFrame dif=new DataInputFrame(bp);
					jarray.add(dif);
					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
		btnBrand.setForeground(new Color(0, 128, 128));
		btnBrand.setIcon(new ImageIcon(Main.class.getResource("/image/brand (1).gif")));
		btnBrand.setBackground(new Color(224, 255, 255));
		btnBrand.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnBrand.setBounds(49, 66, 163, 33);
		panel.add(btnBrand);
		
		btnCompany = new JButton("Company");
		btnCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CompanyPanel	cp = new CompanyPanel();
					DataInputFrame dif=new DataInputFrame(cp);
					jarray.add(dif);
					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCompany.setForeground(new Color(0, 128, 128));
		btnCompany.setBackground(new Color(224, 255, 255));
		btnCompany.setIcon(new ImageIcon(Main.class.getResource("/image/company (1).gif")));
		btnCompany.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnCompany.setBounds(49, 110, 163, 33);
		panel.add(btnCompany);
		
		btnItem = new JButton("Item");
		btnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemView serp = new ItemView();
				jarray.add(serp);

				serp.setVisible(true);
			}
		});
//		ItemView iv=new ItemView();
//		iv.show();
//		dispose();
		btnItem.setForeground(new Color(0, 128, 128));
		btnItem.setBackground(new Color(224, 255, 255));
		btnItem.setIcon(new ImageIcon(Main.class.getResource("/image/item (1).gif")));
		btnItem.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnItem.setBounds(49, 154, 163, 33);
		panel.add(btnItem);
		
		btnType = new JButton("Type");
		btnType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TypePanel	tp = new TypePanel();
					DataInputFrame dif=new DataInputFrame(tp);
					jarray.add(dif);

					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnType.setForeground(new Color(0, 128, 128));
		btnType.setBackground(new Color(224, 255, 255));
		btnType.setIcon(new ImageIcon(Main.class.getResource("/image/type (1).gif")));
		btnType.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnType.setBounds(49, 198, 163, 33);
		panel.add(btnType);
		
		panel_1.setBorder(new LineBorder(new Color(139, 0, 139)));
		panel_1.setBackground(new Color(216, 191, 216));
		panel_1.setBounds(322, 180, 266, 300);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Staff & Customer Info");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(31, 11, 202, 33);
		panel_1.add(lblNewLabel_1_1);
		
		btnDepartment = new JButton("Department");
		btnDepartment.setIcon(new ImageIcon(Main.class.getResource("/image/dept (1).gif")));
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DeptPanel	dp = new DeptPanel();
					DataInputFrame dif=new DataInputFrame(dp);
					jarray.add(dif);

					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDepartment.setForeground(new Color(128, 0, 128));
		btnDepartment.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnDepartment.setBackground(new Color(216, 191, 216));
		btnDepartment.setBounds(55, 59, 163, 33);
		panel_1.add(btnDepartment);
		
		btnPosition = new JButton("Position\r\n");
		btnPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PostPanel	pp = new PostPanel();
					DataInputFrame dif=new DataInputFrame(pp);
					jarray.add(dif);

					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPosition.setIcon(new ImageIcon(Main.class.getResource("/image/post (1).gif")));
		btnPosition.setForeground(new Color(128, 0, 128));
		btnPosition.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnPosition.setBackground(new Color(216, 191, 216));
		btnPosition.setBounds(55, 103, 163, 33);
		panel_1.add(btnPosition);
		
		btnStaff = new JButton("Staff");
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StaffPanel	sp = new StaffPanel();
					DataInputFrame dif=new DataInputFrame(sp);
					jarray.add(dif);

					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		btnStaff.setIcon(new ImageIcon(Main.class.getResource("/image/staff (1).gif")));
		btnStaff.setForeground(new Color(128, 0, 128));
		btnStaff.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnStaff.setBackground(new Color(216, 191, 216));
		btnStaff.setBounds(55, 147, 163, 33);
		panel_1.add(btnStaff);
		
		btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CustomerPanel	cusp = new CustomerPanel();
					DataInputFrame dif=new DataInputFrame(cusp);
					jarray.add(dif);

					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		btnCustomer.setIcon(new ImageIcon(Main.class.getResource("/image/cus (1).gif")));
		btnCustomer.setForeground(new Color(128, 0, 128));
		btnCustomer.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnCustomer.setBackground(new Color(216, 191, 216));
		btnCustomer.setBounds(55, 191, 163, 33);
		panel_1.add(btnCustomer);
		
		panel_1_1.setBorder(new LineBorder(new Color(199, 21, 133)));
		panel_1_1.setBackground(new Color(255, 240, 245));
		panel_1_1.setBounds(620, 180, 266, 300);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Sale, Service & Purchase");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(30, 11, 202, 33);
		panel_1_1.add(lblNewLabel_1_1_1);
		
		btnService = new JButton("Service");
		btnService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ServiceView	serp = new ServiceView();
					jarray.add(serp);

					serp.setVisible(true);

			}

		});
		btnService.setIcon(new ImageIcon(Main.class.getResource("/image/service (1).gif")));
		btnService.setForeground(new Color(240, 128, 128));
		btnService.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnService.setBackground(new Color(255, 245, 238));
		btnService.setBounds(53, 55, 163, 33);
		panel_1_1.add(btnService);
		
		btnSaleOrder = new JButton("Sale Order");
		btnSaleOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SaleOrderView sov = new SaleOrderView();
					jarray.add(sov);

					sov.setVisible(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSaleOrder.setIcon(new ImageIcon(Main.class.getResource("/image/order (1).gif")));
		btnSaleOrder.setForeground(new Color(240, 128, 128));
		btnSaleOrder.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnSaleOrder.setBackground(new Color(255, 245, 238));
		btnSaleOrder.setBounds(53, 99, 163, 33);
		panel_1_1.add(btnSaleOrder);
		
		btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PurchaseView purp = new PurchaseView();
					jarray.add(purp);
					purp.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
			
		});
		btnPurchase.setIcon(new ImageIcon(Main.class.getResource("/image/purchase (1).gif")));
		btnPurchase.setForeground(new Color(233, 150, 122));
		btnPurchase.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnPurchase.setBackground(new Color(255, 245, 238));
		btnPurchase.setBounds(53, 143, 163, 33);
		panel_1_1.add(btnPurchase);
		
		btnPayment = new JButton("Payment");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PaymentView payment = new PaymentView();
					jarray.add(payment);
					payment.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		btnPayment.setIcon(new ImageIcon(Main.class.getResource("/image/payment (1).gif")));
		btnPayment.setForeground(new Color(240, 128, 128));
		btnPayment.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnPayment.setBackground(new Color(255, 245, 238));
		btnPayment.setBounds(53, 189, 163, 33);
		panel_1_1.add(btnPayment);
		
		
		lblNewLabel.setBounds(0, 0, screenSize.width, screenSize.height); // Set size to contentPane size
		contentPane.add(lblNewLabel);

		dabIcon = new ImageIcon(getClass().getResource("../image/background.png"));

//		Image dabImage = dabIcon.getImage();
//		Image modifiedDabImage = dabImage.getScaledInstance(screenSize.width, screenSize.height,java.awt.Image.SCALE_SMOOTH);
//		dabIcon = new ImageIcon(modifiedDabImage);
		lblNewLabel.setIcon(dabIcon);
        updateBackgroundImage();

        // Add a component listener to listen for resize events
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackgroundImage();
                updatePanelPositions();            }
        });

	}
	
    private void updateBackgroundImage() {
        Dimension size = getSize();
        Image scaledImage = dabIcon.getImage().getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
        lblNewLabel.setIcon(new ImageIcon(scaledImage));
        lblNewLabel.setBounds(0, 0, size.width, size.height);
    }
    
    private void updatePanelPositions() {
        int contentWidth = contentPane.getWidth();
        int contentHeight = contentPane.getHeight();

        // Calculate positions for the three panels
        int panelWidth = 266;
        int panelHeight = 250;
        int panelX = (contentWidth - panelWidth * 3 - 46 * 2) / 2; // Subtracting the horizontal gaps
        int panelY = (contentHeight - panelHeight) / 2;

        // Update panel positions
        panel.setBounds(panelX, panelY, panelWidth, panelHeight);
        panel_1.setBounds(panelX + panelWidth + 23, panelY, panelWidth, panelHeight);
        panel_1_1.setBounds(panelX + (panelWidth + 23) * 2, panelY, panelWidth, panelHeight);
        
        lblAdmin.setBounds(panelX + panelWidth + 65, panelY-70, 200, 37);
        lblComputer.setBounds(panelX + panelWidth - 20, panelY-150, 370, 37);
        btnBrand_1.setBounds(contentWidth-(contentWidth/5),contentHeight-(contentHeight/7), 200, 37);
    }
}
