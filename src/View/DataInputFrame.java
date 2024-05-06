package View;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

import com.sun.tools.javac.Main;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class DataInputFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MenuPanel menuPanel = new MenuPanel();

	JPanel brandpanel = new BrandPanel();
	JPanel deptpanel = new DeptPanel();
	JPanel staffpanel = new StaffPanel();
	JPanel postpanel = new PostPanel();
	JPanel customerpanel = new CustomerPanel();
	JPanel typepanel = new TypePanel();
	JPanel companypanel = new CompanyPanel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DataInputFrame frame = new DataInputFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DataInputFrame(JPanel panel) throws SQLException {
		setResizable(false);
		Border border = BorderFactory.createLineBorder(Color.RED);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		FlatLightLaf.setup();

		setBounds(0, 0, 995, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		menuPanel.setLocation(0, 0);
		setContentPane(contentPane);

		contentPane.setLayout(null);

		brandpanel.setBounds(276, 0, 764, 749);

		contentPane.add(menuPanel);
		contentPane.add(panel);
		panel.setBounds(213, 0, 764, 749);

		menuPanel.lblBrand.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblBrand.setBackground(new Color(188, 235, 235));
				contentPane.remove(deptpanel);
				contentPane.remove(staffpanel);
				contentPane.remove(postpanel);
				contentPane.remove(customerpanel);
				contentPane.remove(typepanel);
				contentPane.remove(companypanel);
				contentPane.remove(panel);

				contentPane.add(brandpanel);
				brandpanel.setBounds(213, 0, 764, 749);

				contentPane.revalidate(); // Revalidate the contentPane to reflect changes
				contentPane.repaint(); // Repaint the contentPane

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblBrand.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblBrand.setBackground(new Color(62, 200, 210));

			}
		});
		menuPanel.lblDepartment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblDepartment.setBackground(new Color(188, 235, 235));
				contentPane.remove(brandpanel);
				contentPane.remove(staffpanel);
				contentPane.remove(postpanel);
				contentPane.remove(customerpanel);
				contentPane.remove(typepanel);
				contentPane.remove(companypanel);
				contentPane.remove(panel);

				contentPane.add(deptpanel);
				deptpanel.setBounds(213, 0, 764, 749);

				contentPane.revalidate(); // Revalidate the contentPane to reflect changes
				contentPane.repaint(); // Repaint the contentPane

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblDepartment.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblDepartment.setBackground(new Color(62, 200, 210));

			}

		});
		menuPanel.lblPost.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblPost.setBackground(new Color(188, 235, 235));
				contentPane.remove(brandpanel);
				contentPane.remove(staffpanel);
				contentPane.remove(deptpanel);
				contentPane.remove(customerpanel);
				contentPane.remove(typepanel);
				contentPane.remove(companypanel);
				contentPane.remove(panel);

				contentPane.add(postpanel);
				postpanel.setBounds(213, 0, 764, 749);
				contentPane.revalidate(); // Revalidate the contentPane to reflect changes
				contentPane.repaint(); // Repaint the contentPane

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblPost.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblPost.setBackground(new Color(62, 200, 210));

			}

		});
		menuPanel.lblCompany.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblCompany.setBackground(new Color(188, 235, 235));
				contentPane.remove(brandpanel);
				contentPane.remove(staffpanel);
				contentPane.remove(deptpanel);
				contentPane.remove(postpanel);
				contentPane.remove(typepanel);
				contentPane.remove(companypanel);
				contentPane.remove(panel);

				contentPane.add(companypanel);
				companypanel.setBounds(213, 0, 764, 749);
				contentPane.revalidate(); // Revalidate the contentPane to reflect changes
				contentPane.repaint(); // Repaint the contentPane

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblCompany.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblCompany.setBackground(new Color(62, 200, 210));

			}

		});
		menuPanel.lblType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblType.setBackground(new Color(188, 235, 235));
				contentPane.remove(brandpanel);
				contentPane.remove(staffpanel);
				contentPane.remove(deptpanel);
				contentPane.remove(postpanel);
				contentPane.remove(customerpanel);
				contentPane.remove(companypanel);
				contentPane.remove(panel);

				contentPane.add(typepanel);
				typepanel.setBounds(213, 0, 764, 749);
				contentPane.revalidate(); // Revalidate the contentPane to reflect changes
				contentPane.repaint(); // Repaint the contentPane

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblType.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblType.setBackground(new Color(62, 200, 210));

			}

		});
		menuPanel.lblCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblCustomer.setBackground(new Color(188, 235, 235));
				contentPane.remove(brandpanel);
				contentPane.remove(staffpanel);
				contentPane.remove(deptpanel);
				contentPane.remove(postpanel);
				contentPane.remove(typepanel);
				contentPane.remove(companypanel);
				contentPane.remove(panel);

				contentPane.add(customerpanel);
				customerpanel.setBounds(213, 0, 764, 749);

				contentPane.revalidate(); // Revalidate the contentPane to reflect changes
				contentPane.repaint(); // Repaint the contentPane

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblCustomer.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblCustomer.setBackground(new Color(62, 200, 210));

			}

		});

		menuPanel.lblStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblStaff.setBackground(new Color(188, 235, 235));
				contentPane.remove(brandpanel);
				contentPane.remove(customerpanel);
				contentPane.remove(deptpanel);
				contentPane.remove(postpanel);
				contentPane.remove(typepanel);
				contentPane.remove(companypanel);
				contentPane.remove(panel);

				contentPane.add(staffpanel);
				staffpanel.setBounds(213, 0, 764, 749);

				contentPane.revalidate(); // Revalidate the contentPane to reflect changes
				contentPane.repaint(); // Repaint the contentPane

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblStaff.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblStaff.setBackground(new Color(62, 200, 210));

			}

		});

		menuPanel.lblSaleOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblSaleOrder.setBackground(new Color(188, 235, 235));
				SaleOrderView pv;
				try {
					pv = new SaleOrderView();
					pv.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblSaleOrder.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblSaleOrder.setBackground(new Color(62, 200, 210));

			}

		});
		menuPanel.lblPurchase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblPurchase.setBackground(new Color(188, 235, 235));
				PurchaseView pv;
				try {
					pv = new PurchaseView();
					pv.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblPurchase.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblPurchase.setBackground(new Color(62, 200, 210));

			}

		});

		menuPanel.lblPayment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblPayment.setBackground(new Color(188, 235, 235));
				PaymentView pv;
				try {
					pv = new PaymentView();
					pv.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblPayment.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblPayment.setBackground(new Color(62, 200, 210));

			}

		});

		menuPanel.lblService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblService.setBackground(new Color(188, 235, 235));
				ServiceView pv;
				pv = new ServiceView();
				pv.setVisible(true);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblService.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblService.setBackground(new Color(62, 200, 210));

			}

		});

		menuPanel.lblItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblItem.setBackground(new Color(188, 235, 235));
				ItemView pv;
				pv = new ItemView();
				pv.setVisible(true);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblItem.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblItem.setBackground(new Color(62, 200, 210));

			}

		});

		menuPanel.lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				menuPanel.lblLogout.setBackground(new Color(188, 235, 235));
				dispose();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.lblLogout.setBackground(new Color(188, 235, 235));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.lblLogout.setBackground(new Color(62, 200, 210));

			}

		});

	}
}
