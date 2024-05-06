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
import Connection.date;
import Controller.BrandController;
import Controller.CustomerController;
import Controller.CustomerServiceDetailController;
import Controller.ServiceController;
import Model.BrandModel;
import Model.CustomerModel;
import Model.CustomerServiceDetailModel;
import Model.ServiceModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

public class CustomerServiceDetailView extends JFrame {

	private JPanel contentPane;

	private JScrollPane scrollPane;
	String CustomerID;
	String CustomerName;
	int row;
	private JTextField txtItemName;

	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
	private JMenuItem mntmNewMenuItem_6;
	private JMenu mnNewMenu;
	private JLabel lblNewLabel_4;
	private JTextField txtFault;
	private JLabel lblNewLabel_5;
	private JTextField txtCustomerName;
	private JLabel lblNewLabel_6;
	private JTextField txtPhone;
	private JButton btnShow;
	private JButton btnSave;
	private JButton bthDelete;
	private JButton btnClear;
	private JButton btnUpdate;
	DefaultTableModel dtm = new DefaultTableModel();
	private JTextField txtServiceID = new JTextField();
	CustomerServiceDetailController csdc = new CustomerServiceDetailController();
	CustomerServiceDetailModel csdm = new CustomerServiceDetailModel();
	private JTextField txtSerial;
	CustomerController cc = new CustomerController();
	CustomerModel cm = new CustomerModel();
	private JTextField txtPrice;
	private JTextField txtDate;
	JLabel lblDate = new JLabel("");
	JTextArea txtSolution = new JTextArea();
	String customer;
	JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerServiceDetailView frame = new CustomerServiceDetailView();
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
	public CustomerServiceDetailView() {
		setResizable(false);
		CustomerModel bm = new CustomerModel();
		CustomerController bc = new CustomerController();
		Color textcolor = new Color(47, 79, 79);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1030, 501);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 149));
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 12));
		menuBar.setBounds(0, 0, 1045, 49);
		menuBar.setBackground(new Color(32, 178, 170));
		contentPane.add(menuBar);

		JLabel lblNewLabel = new JLabel("  Customer Service Detail");
		lblNewLabel.setForeground(new Color(224, 255, 255));
		lblNewLabel.setBackground(new Color(0, 0, 149));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		menuBar.add(lblNewLabel);

//		mnNewMenu = mf.getMenu();
//		mnNewMenu = new JMenu("Menu List");
//		mnNewMenu.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 17));
//		mnNewMenu.setForeground(new Color(224, 255, 255));
//		menuBar.add(mnNewMenu);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setBounds(10, 60, 990, 169);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel_4 = new JLabel("Service ID");
		lblNewLabel_4.setBounds(10, 22, 106, 22);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(new Color(47, 79, 79));
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		JLabel lblNewLabel_4_1 = new JLabel("Item Name");
		lblNewLabel_4_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_4_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(10, 59, 106, 22);
		panel.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_2 = new JLabel("Fault");
		lblNewLabel_4_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_4_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_4_2.setBounds(10, 98, 106, 14);
		panel.add(lblNewLabel_4_2);

		txtItemName = new JTextField();
		txtItemName.setBounds(145, 60, 117, 25);
		panel.add(txtItemName);
		txtItemName.setForeground(new Color(47, 79, 79));
		txtItemName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtItemName.setColumns(10);
		txtItemName.setDisabledTextColor(textcolor);
		txtItemName.setEnabled(false);

		JLabel lblNewLabel_4_2_1 = new JLabel("Update Date");
		lblNewLabel_4_2_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_4_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_4_2_1.setBounds(390, 26, 106, 14);
		panel.add(lblNewLabel_4_2_1);

		txtFault = new JTextField();
		txtFault.setBounds(146, 95, 117, 25);
		panel.add(txtFault);
		txtFault.setForeground(new Color(47, 79, 79));
		txtFault.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtFault.setColumns(10);
		txtFault.setDisabledTextColor(textcolor);
		txtFault.setEnabled(false);

		lblNewLabel_5 = new JLabel("Customer Name");
		lblNewLabel_5.setBounds(390, 63, 106, 14);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setForeground(new Color(47, 79, 79));
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		txtCustomerName = new JTextField();
		txtCustomerName.setBounds(534, 60, 117, 25);
		panel.add(txtCustomerName);
		txtCustomerName.setForeground(new Color(47, 79, 79));
		txtCustomerName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtCustomerName.setColumns(10);
		txtCustomerName.setDisabledTextColor(textcolor);
		txtCustomerName.setEnabled(false);

		lblNewLabel_6 = new JLabel("Phone");
		lblNewLabel_6.setBounds(390, 98, 106, 14);
		panel.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_6.setForeground(new Color(47, 79, 79));

		txtPhone = new JTextField();
		txtPhone.setBounds(534, 95, 117, 25);
		panel.add(txtPhone);
		txtPhone.setForeground(new Color(47, 79, 79));
		txtPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPhone.setColumns(10);
		txtPhone.setDisabledTextColor(textcolor);
		txtPhone.setEnabled(false);

		lblDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblDate.setBounds(534, 22, 117, 20);
		panel.add(lblDate);

		txtServiceID.setForeground(new Color(47, 79, 79));
		txtServiceID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtServiceID.setColumns(10);
		txtServiceID.setBounds(145, 25, 117, 25);
		panel.add(txtServiceID);
		txtServiceID.setDisabledTextColor(textcolor);

		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showbyservice(txtServiceID.getText());

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVerify.setBounds(274, 22, 89, 23);
		panel.add(btnVerify);
		btnVerify.setForeground(new Color(47, 79, 79));
		btnVerify.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnVerify.setBackground(new Color(102, 205, 170));

		JLabel lblNewLabel_5_1 = new JLabel("Serial");
		lblNewLabel_5_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_5_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5_1.setBounds(730, 63, 80, 14);
		panel.add(lblNewLabel_5_1);

		JLabel lblNewLabel_6_1 = new JLabel("Status");
		lblNewLabel_6_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_6_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_6_1.setBounds(730, 98, 80, 14);
		panel.add(lblNewLabel_6_1);

		txtSerial = new JTextField();
		txtSerial.setForeground(new Color(47, 79, 79));
		txtSerial.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSerial.setColumns(10);
		txtSerial.setBounds(822, 59, 145, 25);
		panel.add(txtSerial);
		txtSerial.setEnabled(false);
		txtSerial.setDisabledTextColor(new Color(47, 79, 79));
		txtSerial.setEnabled(false);

		JLabel lblNewLabel_4_2_2 = new JLabel("Price");
		lblNewLabel_4_2_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_4_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_4_2_2.setBounds(10, 137, 106, 14);
		panel.add(lblNewLabel_4_2_2);

		txtPrice = new JTextField();
		txtPrice.setForeground(new Color(47, 79, 79));
		txtPrice.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(146, 134, 117, 25);
		panel.add(txtPrice);
		txtPrice.setDisabledTextColor(textcolor);
		txtPrice.setEnabled(false);

		JLabel lblNewLabel_6_2 = new JLabel("Received Date");
		lblNewLabel_6_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_6_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_6_2.setBounds(390, 137, 106, 14);
		panel.add(lblNewLabel_6_2);

		txtDate = new JTextField();
		txtDate.setForeground(new Color(47, 79, 79));
		txtDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtDate.setColumns(10);
		txtDate.setBounds(534, 134, 117, 25);
		panel.add(txtDate);
		txtDate.setDisabledTextColor(textcolor);
		txtDate.setEnabled(false);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() > 0) {
					String status = (String) comboBox.getSelectedItem();
					String solution = txtSolution.getText();
					CustomerServiceDetailModel cm = new CustomerServiceDetailModel();
					cm.setServiceid(txtServiceID.getText());
					cm.setCustomerid(customer);
					cm.setDate(lblDate.getText());
					cm.setStatus(status);
					cm.setSolution(solution);
					int save = csdc.update(cm);
					if (save == 1) {
						JOptionPane.showMessageDialog(null, "Updated Successfully", "Successfully",
								JOptionPane.INFORMATION_MESSAGE);
						clear();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select status", "Successfully",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnUpdate.setForeground(new Color(47, 79, 79));
		btnUpdate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnUpdate.setBackground(new Color(102, 205, 170));
		btnUpdate.setBounds(574, 402, 89, 23);
		contentPane.add(btnUpdate);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setForeground(new Color(47, 79, 79));
		btnClear.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnClear.setBackground(new Color(102, 205, 170));
		btnClear.setBounds(698, 402, 89, 23);
		contentPane.add(btnClear);

		JLabel lblNewLabel_2 = new JLabel("Solution");
		lblNewLabel_2.setBounds(12, 233, 50, 23);
		contentPane.add(lblNewLabel_2);

		txtSolution.setBounds(10, 261, 990, 131);
		contentPane.add(txtSolution);

		// createTable();
		// AutoID();
		try {
			showList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		date d = new date();
		lblDate.setText(d.getMySQLDateFormat());

		comboBox.setBounds(822, 96, 145, 25);
		panel.add(comboBox);
		comboBox.addItem("-select-");
		comboBox.addItem("Incomplete");
		comboBox.addItem("Completed");
		comboBox.addItem("Received by Customer");

	}

	public void showbyservice(String name) throws SQLException {
		ServiceModel b = new ServiceModel();
		b.setServiceid(name);
		ServiceController bc = new ServiceController();
		List<ServiceModel> list = bc.searchserviceDetail1(b);

		for (ServiceModel s : list) {

			txtServiceID.setText(s.getServiceid());
			showcsdm(s.getServiceid());
			comboBox.setSelectedItem(csdm.getStatus());
			txtSolution.setText(csdm.getSolution());

			String sname = s.getStaffname();
			txtItemName.setText(s.getItem());

			txtPrice.setText(s.getPrice() + "");
			txtSerial.setText(s.getSerial());
			txtFault.setText(s.getFault());

			searchCustomer(txtServiceID.getText());
			txtDate.setText(s.getDate());

		}
	}

	public void searchCustomer(String serviceid) {
		customer = csdc.SearchCustomer(serviceid);
		cm.setCustomerid(customer);
		cm = cc.searchCustomerDetail1(cm);

		txtCustomerName.setText(cm.getCustomername());
		txtPhone.setText(cm.getPhone());
	}

	public void Clear() {

		btnSave.setEnabled(true);
		txtFault.setText("");
		txtCustomerName.setText("");
		txtPhone.setText("");
		txtItemName.setText("");
		txtItemName.requestFocus(true);
	}

	public void showcsdm(String id) {
		String data[] = new String[5];
		List<CustomerServiceDetailModel> list = csdc.searchbyserviceid(id);
		dtm.setRowCount(0);
		for (CustomerServiceDetailModel bm : list) {
			csdm.setServiceid(bm.getServiceid());
			csdm.setCustomerid(bm.getCustomerid());
			csdm.setDate(bm.getDate());
			csdm.setStatus(bm.getStatus());
			csdm.setSolution(bm.getSolution());

			dtm.addRow(data);
		}
	}

	public void showList() throws SQLException {
		String data[] = new String[5];
		CustomerController bc = new CustomerController();
		List<CustomerModel> list = bc.selectAll();
		dtm.setRowCount(0);
		for (CustomerModel bm : list) {
			data[0] = bm.getCustomerid();
			data[1] = bm.getCustomername();
			data[2] = bm.getAddress();
			data[3] = bm.getPhone();
			data[4] = bm.getEmail();

			dtm.addRow(data);
		}
	}

	public void showListone(String name) throws SQLException {
		String data[] = new String[5];
		CustomerModel b = new CustomerModel();
		b.setCustomername(btnShow.getText().toString().trim());
		CustomerController bc = new CustomerController();
		List<CustomerModel> list = bc.selectOne(b);
		dtm.setRowCount(0);
		for (CustomerModel bm : list) {
			data[0] = bm.getCustomerid();
			data[1] = bm.getCustomername();
			data[2] = bm.getAddress();
			data[3] = bm.getPhone();
			data[4] = bm.getEmail();

			dtm.addRow(data);
		}
	}

	public void delete(String id) {
		CustomerController bc = new CustomerController();
		CustomerModel bm = new CustomerModel();
		bm.setCustomerid(id);
		bc.delete(bm);

	}

	public void clear() {
		txtServiceID.setText("");
		txtItemName.setText("");
		txtFault.setText("");
		txtPrice.setText("");
		txtCustomerName.setText("");
		txtPhone.setText("");
		txtDate.setText("");
		txtSerial.setText("");
		comboBox.setSelectedIndex(0);
		txtSolution.setText("");

	}
}
