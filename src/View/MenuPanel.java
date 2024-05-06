package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel lblBrand = new JLabel("Brand");
	JLabel lblDepartment = new JLabel("Department");
	JLabel lblCompany = new JLabel("Company");
	JLabel lblType = new JLabel("Type");
	JLabel lblCustomer = new JLabel("Customer");
	JLabel lblStaff = new JLabel("Staff");
	JLabel lblPurchase = new JLabel("Purchase");
	JLabel lblSaleOrder = new JLabel("Sale Order");
	JLabel lblPayment = new JLabel("Payment");
	JLabel lblItem = new JLabel("Item");
	JLabel lblService =new JLabel("Service");
	JLabel lblLogout = new JLabel("Exit");

	JLabel lblPost = new JLabel("Post");

	public interface MenuPanelListener {
		void buttonClicked();
	}

	private MenuPanelListener listener;
	private final JLabel lblAdmin = new JLabel("");
	private final JLabel lblNewLabel = new JLabel("");

//	private final JLabel lblSaleOrder = new JLabel("Sale Order");
//	private final JLabel lblPurchase = new JLabel("Purchase");
//	private final JLabel lblPayment = new JLabel("Payment");

	public void setMenuPanelListener(MenuPanelListener listener) {
		this.listener = listener;

	}

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		Border border = BorderFactory.createLineBorder(Color.RED);

		FlatLightLaf.setup();

		setBounds(100, 100, 212, 750);
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 212, 749);
		add(panel);
		panel.setBackground(new Color(151, 231, 225));
		panel.setLayout(null);
		lblAdmin.setBounds(7, 33, 2, 2);
		lblAdmin.setOpaque(true);
		lblAdmin.setIconTextGap(25);
		lblAdmin.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmin.setForeground(Color.WHITE);
		lblAdmin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblAdmin.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblAdmin.setBackground(new Color(62, 200, 210));

		panel.add(lblAdmin);
		lblBrand.setBounds(7, 46, 198, 42);
		lblBrand.setForeground(SystemColor.textHighlightText);

		lblBrand.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblBrand.setOpaque(true);// set background;

		lblBrand.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblBrand.setBackground(new Color(62, 200, 210));
		lblBrand.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblBrand);
		lblBrand.setIcon(new ImageIcon(getClass().getResource("../image/user.png")));
		lblBrand.setIconTextGap(25);
		lblDepartment.setBounds(7, 91, 198, 42);
		lblDepartment.setForeground(SystemColor.textHighlightText);

		lblDepartment.setOpaque(true);
		lblDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartment.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblDepartment.setBackground(new Color(62, 200, 210));
		panel.add(lblDepartment);
		lblDepartment.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblDepartment.setIcon(new ImageIcon(getClass().getResource("../image/building.png")));
		lblDepartment.setOpaque(true);// set background;
		lblDepartment.setIconTextGap(25);
		lblPost.setBounds(7, 137, 198, 42);

		lblPost.setForeground(SystemColor.textHighlightText);
		lblPost.setOpaque(true);
		lblPost.setHorizontalAlignment(SwingConstants.LEFT);
		lblPost.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblPost.setBackground(new Color(62, 200, 210));
		panel.add(lblPost);
		lblPost.setIcon(new ImageIcon(getClass().getResource("../image/id-badge.png")));
		lblPost.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblPost.setOpaque(true);// set background;
		lblPost.setIconTextGap(25);
		lblCompany.setBounds(7, 183, 198, 42);
		lblCompany.setForeground(SystemColor.textHighlightText);

		lblCompany.setOpaque(true);
		lblCompany.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompany.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblCompany.setBackground(new Color(62, 200, 210));
		panel.add(lblCompany);
		lblCompany.setIcon(new ImageIcon(getClass().getResource("../image/bank.png")));
		lblCompany.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblCompany.setOpaque(true);// set background;
		lblCompany.setIconTextGap(25);
		lblType.setBounds(7, 229, 198, 42);
		lblType.setForeground(SystemColor.textHighlightText);

		lblType.setOpaque(true);
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblType.setBackground(new Color(62, 200, 210));
		panel.add(lblType);
		lblType.setIcon(new ImageIcon(getClass().getResource("../image/settings-sliders.png")));
		lblType.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblType.setOpaque(true);// set background;
		lblType.setIconTextGap(25);
		lblCustomer.setBounds(7, 323, 198, 42);
		lblCustomer.setForeground(SystemColor.textHighlightText);

		lblCustomer.setOpaque(true);
		lblCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomer.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblCustomer.setBackground(new Color(62, 200, 210));
		panel.add(lblCustomer);
		lblCustomer.setIcon(new ImageIcon(getClass().getResource("../image/users.png")));
		lblCustomer.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblCustomer.setOpaque(true);// set background;
		lblCustomer.setIconTextGap(25);
		lblStaff.setBounds(7, 414, 198, 42);
		lblStaff.setForeground(SystemColor.textHighlightText);
		lblStaff.setOpaque(true);
		lblStaff.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaff.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblStaff.setBackground(new Color(62, 200, 210));
		panel.add(lblStaff);
		lblStaff.setIcon(new ImageIcon(getClass().getResource("../image/user.png")));
		lblStaff.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblStaff.setOpaque(true);
		lblStaff.setIconTextGap(25);

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MenuPanel.class.getResource("/image/three.png")));
		lblNewLabel.setBounds(7, 0, 198, 42);

		panel.add(lblNewLabel);
		lblSaleOrder.setIcon(new ImageIcon(getClass().getResource("../image/pre-order.png")));
		lblSaleOrder.setOpaque(true);
		lblSaleOrder.setIconTextGap(25);
		lblSaleOrder.setHorizontalAlignment(SwingConstants.LEFT);
		lblSaleOrder.setForeground(Color.WHITE);
		lblSaleOrder.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblSaleOrder.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblSaleOrder.setBackground(new Color(62, 200, 210));
		lblSaleOrder.setBounds(7, 459, 198, 42);

		panel.add(lblSaleOrder);
		lblPurchase.setIcon(new ImageIcon(MenuPanel.class.getResource("/image/purchase-order.png")));
		lblPurchase.setOpaque(true);
		lblPurchase.setIconTextGap(25);
		lblPurchase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurchase.setForeground(Color.WHITE);
		lblPurchase.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblPurchase.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblPurchase.setBackground(new Color(62, 200, 210));
		lblPurchase.setBounds(7, 504, 198, 42);

		panel.add(lblPurchase);
		lblPayment.setIcon(new ImageIcon(MenuPanel.class.getResource("/image/payment-method.png")));
		lblPayment.setOpaque(true);
		lblPayment.setIconTextGap(25);
		lblPayment.setHorizontalAlignment(SwingConstants.LEFT);
		lblPayment.setForeground(Color.WHITE);
		lblPayment.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblPayment.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblPayment.setBackground(new Color(62, 200, 210));
		lblPayment.setBounds(7, 551, 198, 42);

		panel.add(lblPayment);
		
		lblItem.setIcon(new ImageIcon(MenuPanel.class.getResource("/image/list-items.png")));
		lblItem.setOpaque(true);
		lblItem.setIconTextGap(25);
		lblItem.setHorizontalAlignment(SwingConstants.LEFT);
		lblItem.setForeground(Color.WHITE);
		lblItem.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblItem.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblItem.setBackground(new Color(62, 200, 210));
		lblItem.setBounds(7, 276, 198, 42);
		panel.add(lblItem);
		lblService.setIcon(new ImageIcon(MenuPanel.class.getResource("/image/customer-service.png")));
		lblService.setOpaque(true);
		lblService.setIconTextGap(25);
		lblService.setHorizontalAlignment(SwingConstants.LEFT);
		lblService.setForeground(Color.WHITE);
		lblService.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblService.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblService.setBackground(new Color(62, 200, 210));
		lblService.setBounds(7, 368, 198, 42);
		
		panel.add(lblService);
		lblLogout.setIcon(new ImageIcon(MenuPanel.class.getResource("/image/logout.png")));
		lblLogout.setOpaque(true);
		lblLogout.setIconTextGap(25);
		lblLogout.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblLogout.setBorder(new LineBorder(new Color(0, 128, 192), 1, true));
		lblLogout.setBackground(new Color(62, 200, 210));
		lblLogout.setBounds(7, 597, 198, 42);
		
		panel.add(lblLogout);

	}
}
