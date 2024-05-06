package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Connection.Checking;
import Connection.ClsDBConnection;
import Controller.BrandController;
import Controller.LoginController;
import Model.BrandModel;
import Model.LoginModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class RegisterView extends JFrame {

	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String BrandID = null;
	int r;
	private JMenuItem mntmNewMenuItem_3;
	String brandName;
	private JPanel panel_1;
	private JTextField txtusername;
	private JPasswordField txtpassword;
	private JTextField txtMasterKey;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterView frame = new RegisterView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public RegisterView() throws SQLException {
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 502);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 139));
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setMenu();

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 205, 170));
		panel_1.setBounds(0, 0, 763, 463);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMasterKey = new JLabel("Master Key");
		lblMasterKey.setForeground(new Color(47, 79, 79));
		lblMasterKey.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblMasterKey.setBounds(49, 81, 148, 19);
		panel_1.add(lblMasterKey);

		txtMasterKey = new JTextField();
		txtMasterKey.setForeground(new Color(47, 79, 79));
		txtMasterKey.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtMasterKey.setColumns(10);
		txtMasterKey.setBounds(49, 124, 178, 26);
		panel_1.add(txtMasterKey);

		JLabel lblTechAceComputer = new JLabel("User Registration");
		lblTechAceComputer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTechAceComputer.setForeground(new Color(47, 79, 79));
		lblTechAceComputer.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblTechAceComputer.setBounds(0, 22, 763, 49);
		panel_1.add(lblTechAceComputer);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LoginView.class.getResource("/image/Tech Ace.png")));
		lblNewLabel_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(435, 175, 266, 114);
		panel_1.add(lblNewLabel_2);

		JLabel lblN = new JLabel("Username");
		lblN.setIcon(new ImageIcon(LoginView.class.getResource("/image/user.png")));
		lblN.setForeground(new Color(47, 79, 79));
		lblN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblN.setBounds(49, 175, 148, 19);
		panel_1.add(lblN);

		txtusername = new JTextField();
		txtusername.setForeground(new Color(47, 79, 79));
		txtusername.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtusername.setColumns(10);
		txtusername.setBounds(49, 218, 178, 26);
		panel_1.add(txtusername);

		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setIcon(new ImageIcon(LoginView.class.getResource("/image/hide.png")));
		lblNewLabel_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(51, 274, 146, 19);
		panel_1.add(lblNewLabel_3);

		txtpassword = new JPasswordField();
		txtpassword.setBounds(49, 319, 178, 26);
		panel_1.add(txtpassword);

		JButton btnLogin = new JButton("Save");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtMasterKey.getText().equals("2024")) {
					JOptionPane.showMessageDialog(null, "Invalid Master Key", "Invalid", JOptionPane.ERROR_MESSAGE);
				} else {
					char[] passwordChars = txtpassword.getPassword();
					String passwordString = new String(passwordChars);
					LoginController lc = new LoginController();
					int rs = lc.insert(txtusername.getText(), passwordString);
					if (rs == 1) {
						JOptionPane.showMessageDialog(null, "Saved Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

					}
				}

			}
		});
		btnLogin.setForeground(new Color(47, 79, 79));
		btnLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnLogin.setEnabled(true);
		btnLogin.setBackground(new Color(102, 205, 170));
		btnLogin.setBounds(49, 382, 101, 26);
		panel_1.add(btnLogin);

		JButton btnRegister = new JButton("Back");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginView lv;
				try {
					lv = new LoginView();
					lv.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRegister.setForeground(new Color(47, 79, 79));
		btnRegister.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnRegister.setEnabled(true);
		btnRegister.setBackground(new Color(102, 205, 170));
		btnRegister.setBounds(180, 382, 101, 26);
		panel_1.add(btnRegister);

		JLabel lblNewLabel_4 = new JLabel("Login  Here");
		lblNewLabel_4.setIcon(new ImageIcon(LoginView.class.getResource("/image/background.png")));
		lblNewLabel_4.setBounds(0, 0, 763, 463);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(new Color(47, 79, 79));
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));

	}

	public void setMenu() {
	}
}
