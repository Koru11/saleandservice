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
import Connection.SQLQueries;
import Connection.date;
import Controller.BrandController;
import Controller.CompanyController;
import Controller.CustomerController;
import Controller.CustomerServiceDetailController;
import Controller.DeptController;
import Controller.PostController;
import Controller.ServiceController;
import Controller.StaffController;
import Model.BrandModel;
import Model.CompanyModel;
import Model.CustomerModel;
import Model.CustomerServiceDetailModel;
import Model.DeptModel;
import Model.PostModel;
import Model.ServiceModel;
import Model.StaffModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Scrollbar;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ScrollPaneConstants;

public class ServiceView extends JFrame {

	private DeptController dc = new DeptController();
	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String serviceid;
	String service;
	int row;

	private JTable tblService;
	private JScrollPane scrollPane_1;
	private JLabel lblN;
	private JLabel lblserviceid;
	private JLabel lblNewLabel_3;
	private JButton btnSave;
	private JButton btnShowAll;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnClear;

	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
	private JMenuItem mntmNewMenuItem_6;
	private JLabel lblNewLabel_1;
	private JTextField txtSearchserviceid;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtBag;
	private JLabel lblNewLabel_5_1;
	private JLabel lblNewLabel_5_2;
	private JLabel lblNewLabel_5_3;
	private JLabel lblNewLabel_6;
	private JTextField txtCharger;
	private JTextField txtProcessor;
	private JTextField txtRam;
	private JTextField txtHarddisk;
	private JComboBox comboStaff = new JComboBox();
	private JLabel lblNewLabel_7;
	private JTextField txtPrice;
	private JTextField txtFault;
	private JTextField txtSerial;
	private JTextField txtCustomerID;
	private JTextField txtCustomerName;
	private JLabel lblItem;
	private JTextField txtItem;
	private JLabel lblBrand;
	private JTextField txtBrand;
	private JLabel date;
	private JLabel lblDate;
	JComboBox<String> cboWarranty = new JComboBox();
	CustomerController cc = new CustomerController();
	CustomerModel cm = new CustomerModel();

	CustomerServiceDetailController csdc = new CustomerServiceDetailController();
	CustomerServiceDetailModel csdm = new CustomerServiceDetailModel();
	private JTextField txtSearchItem;
	private JTextField txtSearchCustomerid;
	private JLabel lblNewLabel_1_1_2;
	private JTextField textSearchserial;
	private JButton btnOpenServiceDetail_1;
	private JButton btnOpenServiceDetail;
	JTextField txtPhone = new JTextField();
	JTextField txtAddress = new JTextField();
	JTextField txtEmail = new JTextField();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceView frame = new ServiceView();
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
	public ServiceView() {
		setTitle("Service");
		setResizable(false);
		ServiceModel bm = new ServiceModel();
		ServiceController bc = new ServiceController();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1361, 740);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 139));
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("List");
		lblNewLabel_2.setBounds(12, 11, 76, 14);
		lblNewLabel_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2);

		scrollPane_1 = new JScrollPane();
		tblService = new JTable();

		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 40, 1330, 143);
		tblService.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		contentPane.add(scrollPane_1);

		tblService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tblService.getSelectedRow();

				serviceid = (String) tblService.getValueAt(row, 0);
				lblserviceid.setText(serviceid);

				comboStaff.setSelectedItem((String) tblService.getValueAt(row, 1));
				txtItem.setText((String) tblService.getValueAt(row, 2));
				txtBrand.setText((String) tblService.getValueAt(row, 3));
				txtBag.setText((String) tblService.getValueAt(row, 4));
				txtFault.setText((String) tblService.getValueAt(row, 5));
				txtCharger.setText((String) tblService.getValueAt(row, 6));
				txtProcessor.setText((String) tblService.getValueAt(row, 7));
				txtRam.setText((String) tblService.getValueAt(row, 8));
				txtHarddisk.setText((String) tblService.getValueAt(row, 9));
				txtPrice.setText((String) tblService.getValueAt(row, 10));
				txtSerial.setText((String) tblService.getValueAt(row, 11));
				cboWarranty.setSelectedItem((String) tblService.getValueAt(row, 12));

				cm.setCustomerid((String) tblService.getValueAt(row, 13));

				if (!cc.CustomerExist(cm)) {
					JOptionPane.showMessageDialog(null, "The Customer doesn't exist", "Fail",
							JOptionPane.ERROR_MESSAGE);
					txtRam.requestFocus(true);
				} else {
					CustomerModel verifyCus = cc.searchCustomerDetail1(cm);

					txtCustomerID.setText(verifyCus.getCustomerid());
					txtCustomerName.setText(verifyCus.getCustomername());
					txtPhone.setText(verifyCus.getPhone());
					txtAddress.setText(verifyCus.getAddress());
					txtEmail.setText(verifyCus.getEmail());

				}

				lblDate.setText((String) tblService.getValueAt(row, 14));

				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);

				comboStaff.requestFocus(true);

			}
		});
		tblService.setBackground(new Color(192, 192, 192));
		tblService.setForeground(new Color(47, 79, 79));
		tblService.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		scrollPane_1.setViewportView(tblService);

		lblN = new JLabel("Service ID");
		lblN.setBounds(10, 244, 106, 23);
		lblN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblN.setForeground(new Color(47, 79, 79));
		contentPane.add(lblN);

		lblserviceid = new JLabel("");
		lblserviceid.setBounds(140, 246, 117, 18);
		lblserviceid.setForeground(new Color(47, 79, 79));
		lblserviceid.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		contentPane.add(lblserviceid);
		cboWarranty.setModel(new DefaultComboBoxModel(new String[] {"Choose"}));
		

		cboWarranty.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		cboWarranty.setBounds(140, 657, 210, 25);
		cboWarranty.addItem("Unknown");
		cboWarranty.addItem("Yes");
		cboWarranty.addItem("No");
		contentPane.add(cboWarranty);

		lblNewLabel_3 = new JLabel("Hard Disk");
		lblNewLabel_3.setBounds(12, 554, 120, 19);
		lblNewLabel_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_3);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.setCustomerid(txtCustomerID.getText().trim());

				ServiceModel sm = new ServiceModel();
				ServiceController sc = new ServiceController();

				if (lblserviceid.getText().trim().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "There is no service ID!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtBag.requestFocus(true);
					txtBag.selectAll();
				} else if (comboStaff.getSelectedIndex() <= 0) {
					JOptionPane.showMessageDialog(null, "Please choose Staff!", "Fail", JOptionPane.ERROR_MESSAGE);
					comboStaff.requestFocus(true);
				} else if (txtFault.getText().trim().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please type Fault!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtFault.requestFocus(true);
				}
				else if(txtPrice.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Price cannot be blank");
					txtPrice.requestFocus();
					txtPrice.selectAll();
				} 
				else if (txtItem.getText().trim().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please type Item!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtItem.requestFocus(true);
				} 
				else if (!cc.CustomerExist(cm)) {
					JOptionPane.showMessageDialog(null, "The Customer doesn't exist", "Fail",
							JOptionPane.ERROR_MESSAGE);
					txtCustomerID.requestFocus(true);
				} 
				else {
					StaffController pc = new StaffController();
					StaffModel pm = new StaffModel();
					pm.setStaffname(comboStaff.getSelectedItem().toString());
					String staffid = pc.searchStaffId(pm);

					ServiceController ic = new ServiceController();
					ServiceModel im = new ServiceModel();

					im.setServiceid(lblserviceid.getText().toString());
					im.setStaffid(staffid);
					im.setItem(txtItem.getText().trim());
					im.setBrand(txtBrand.getText().trim());
					// im.setFault(comboFault.getSelectedItem().toString());
					im.setBag(txtBag.getText().trim());
					im.setFault(txtFault.getText().trim());
					im.setCharger(txtCharger.getText().trim());
					im.setProcessor(txtProcessor.getText().trim());
					im.setRam(txtRam.getText().trim());
					im.setHarddisk(txtHarddisk.getText().trim());
					im.setPrice(Integer.parseInt(txtPrice.getText().trim()));
					im.setSerial(txtSerial.getText().trim());
					im.setWarranty((String) cboWarranty.getSelectedItem());
					im.setDate(lblDate.getText());

					csdm.setCustomerid(txtCustomerID.getText().trim());
					csdm.setServiceid(lblserviceid.getText().trim());
					csdm.setDate(lblDate.getText());
					csdm.setStatus("-");

					int rs = ic.insert(im);
					int cdrs = csdc.insert(csdm);
					if (rs == 1 && cdrs == 1) {
						AutoID();
						clear();
						try {
							showList();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Saved Successfully", "Success",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(null, "Insert Failed", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnSave.setBounds(387, 497, 89, 23);
		btnSave.setBackground(new Color(102, 205, 170));
		btnSave.setForeground(new Color(47, 79, 79));
		btnSave.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		contentPane.add(btnSave);

		lblNewLabel_1 = new JLabel("Search by ServiceID");
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(471, 9, 130, 19);
		contentPane.add(lblNewLabel_1);

		txtSearchserviceid = new JTextField();
		txtSearchserviceid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					showbyservice(txtSearchserviceid.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtSearchserviceid.setForeground(new Color(47, 79, 79));
		txtSearchserviceid.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearchserviceid.setBounds(603, 10, 150, 25);
		contentPane.add(txtSearchserviceid);
		txtSearchserviceid.setColumns(10);

		lblNewLabel_4 = new JLabel("Staff Name");
		lblNewLabel_4.setForeground(new Color(47, 79, 79));
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 285, 106, 23);
		contentPane.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Bag");
		lblNewLabel_5.setForeground(new Color(47, 79, 79));
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(12, 381, 106, 19);
		contentPane.add(lblNewLabel_5);

		txtBag = new JTextField();
		txtBag.setForeground(new Color(47, 79, 79));
		txtBag.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtBag.setColumns(10);
		txtBag.setBounds(140, 381, 210, 25);
		contentPane.add(txtBag);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.setCustomerid(txtCustomerID.getText().trim());

				ServiceModel sm = new ServiceModel();
				ServiceController sc = new ServiceController();

				if (lblserviceid.getText().trim().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "There is no service ID!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtBag.requestFocus(true);
					txtBag.selectAll();
				} else if (comboStaff.getSelectedIndex() <= 0) {
					JOptionPane.showMessageDialog(null, "Please choose Staff!", "Fail", JOptionPane.ERROR_MESSAGE);
					comboStaff.requestFocus(true);
				} else if (txtFault.getText().trim().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please type Fault!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtFault.requestFocus(true);
				}
				else if(txtPrice.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Price cannot be blank");
					txtPrice.requestFocus();
					txtPrice.selectAll();
				} 
				else if (txtItem.getText().trim().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please type Item!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtItem.requestFocus(true);
				} 
				else if (!cc.CustomerExist(cm)) {
					JOptionPane.showMessageDialog(null, "The Customer doesn't exist", "Fail",
							JOptionPane.ERROR_MESSAGE);
					txtCustomerID.requestFocus(true);
				} 
				else {
					StaffController pc = new StaffController();
					StaffModel pm = new StaffModel();
					pm.setStaffname(comboStaff.getSelectedItem().toString());
					String staffid = pc.searchStaffId(pm);

					ServiceController ic = new ServiceController();
					ServiceModel im = new ServiceModel();

					im.setServiceid(lblserviceid.getText().toString());
					im.setStaffid(staffid);
					im.setItem(txtItem.getText().trim());
					im.setBrand(txtBrand.getText().trim());
					// im.setFault(comboFault.getSelectedItem().toString());
					im.setBag(txtBag.getText().trim());
					im.setFault(txtFault.getText().trim());
					im.setCharger(txtCharger.getText().trim());
					im.setProcessor(txtProcessor.getText().trim());
					im.setRam(txtRam.getText().trim());
					im.setHarddisk(txtHarddisk.getText().trim());
					im.setPrice(Integer.parseInt(txtPrice.getText().trim()));
					im.setSerial(txtSerial.getText().trim());
					im.setWarranty((String) cboWarranty.getSelectedItem());
					im.setDate(lblDate.getText());

					int rs = ic.update(im);
					if (rs == 1) {
						AutoID();
						clear();
						try {
							showList();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Updated Successfully", "Success",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(null, "Updated Failed", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnUpdate.setForeground(new Color(47, 79, 79));

		btnUpdate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnUpdate.setEnabled(false);
		btnUpdate.setBackground(new Color(102, 205, 170));
		btnUpdate.setBounds(589, 497, 89, 23);
		contentPane.add(btnUpdate);

		btnShowAll = new JButton("Show");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtSearchserviceid.requestFocus(true);
				txtSearchserviceid.selectAll();
			}
		});
		btnShowAll.setForeground(new Color(47, 79, 79));
		btnShowAll.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnShowAll.setBackground(new Color(102, 205, 170));
		btnShowAll.setBounds(111, 193, 89, 23);
		contentPane.add(btnShowAll);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure do you want to Delete?", "Warning",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					delete(lblserviceid.getText(), (String) tblService.getValueAt(row, 13));
					AutoID();
					clear();
					try {
						showList();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					btnUpdate.setEnabled(false);
					btnDelete.setEnabled(false);
					btnSave.setEnabled(true);
				}

			}

		});
		btnDelete.setForeground(new Color(47, 79, 79));
		btnDelete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnDelete.setBackground(new Color(102, 205, 170));
		btnDelete.setBounds(10, 193, 89, 23);
		contentPane.add(btnDelete);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setForeground(new Color(47, 79, 79));
		btnClear.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnClear.setBackground(new Color(102, 205, 170));
		btnClear.setBounds(488, 497, 89, 23);
		contentPane.add(btnClear);

		btnDelete.setEnabled(false);

		lblNewLabel_5_1 = new JLabel("Fault");
		lblNewLabel_5_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_5_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5_1.setBounds(12, 418, 106, 19);
		contentPane.add(lblNewLabel_5_1);

		lblNewLabel_5_2 = new JLabel("Charger");
		lblNewLabel_5_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_5_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5_2.setBounds(12, 457, 106, 19);
		contentPane.add(lblNewLabel_5_2);

		lblNewLabel_5_3 = new JLabel("Processor");
		lblNewLabel_5_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_5_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5_3.setBounds(12, 491, 106, 19);
		contentPane.add(lblNewLabel_5_3);

		lblNewLabel_6 = new JLabel("Ram");
		lblNewLabel_6.setForeground(new Color(47, 79, 79));
		lblNewLabel_6.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(12, 523, 120, 19);
		contentPane.add(lblNewLabel_6);

		txtCharger = new JTextField();
		txtCharger.setForeground(new Color(47, 79, 79));
		txtCharger.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtCharger.setColumns(10);
		txtCharger.setBounds(140, 457, 210, 25);
		contentPane.add(txtCharger);

		txtProcessor = new JTextField();
		txtProcessor.setForeground(new Color(47, 79, 79));
		txtProcessor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtProcessor.setColumns(10);
		txtProcessor.setBounds(140, 491, 210, 25);
		contentPane.add(txtProcessor);

		comboStaff.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));
		comboStaff.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		comboStaff.setBounds(140, 285, 210, 25);
		contentPane.add(comboStaff);

		txtRam = new JTextField();
		txtRam.setForeground(new Color(47, 79, 79));
		txtRam.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtRam.setColumns(10);
		txtRam.setBounds(140, 523, 210, 25);
		contentPane.add(txtRam);

		txtHarddisk = new JTextField();
		txtHarddisk.setForeground(new Color(47, 79, 79));
		txtHarddisk.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtHarddisk.setColumns(10);
		txtHarddisk.setBounds(140, 555, 210, 25);
		contentPane.add(txtHarddisk);

		lblNewLabel_7 = new JLabel("Price");
		lblNewLabel_7.setForeground(new Color(47, 79, 79));
		lblNewLabel_7.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(14, 588, 120, 19);
		contentPane.add(lblNewLabel_7);

		txtPrice = new JTextField();
		txtPrice.setForeground(new Color(47, 79, 79));
		txtPrice.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(140, 588, 210, 25);
		contentPane.add(txtPrice);

		txtFault = new JTextField();
		txtFault.setForeground(new Color(47, 79, 79));
		txtFault.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtFault.setColumns(10);
		txtFault.setBounds(140, 418, 210, 25);
		contentPane.add(txtFault);

		txtSerial = new JTextField();
		txtSerial.setForeground(new Color(47, 79, 79));
		txtSerial.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSerial.setColumns(10);
		txtSerial.setBounds(140, 621, 210, 25);
		contentPane.add(txtSerial);


		JLabel lblNewLabel_7_1 = new JLabel("Serial");
		lblNewLabel_7_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_7_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_7_1.setBounds(14, 621, 120, 19);
		contentPane.add(lblNewLabel_7_1);

		JLabel lblNewLabel_5_4 = new JLabel("Customer ID");
		lblNewLabel_5_4.setForeground(new Color(47, 79, 79));
		lblNewLabel_5_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5_4.setBounds(387, 288, 106, 19);
		contentPane.add(lblNewLabel_5_4);

		txtCustomerID = new JTextField();
		txtCustomerID.setForeground(new Color(47, 79, 79));
		txtCustomerID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtCustomerID.setColumns(10);
		txtCustomerID.setBounds(517, 285, 161, 25);
		contentPane.add(txtCustomerID);

		JButton btnNewCustomer = new JButton("New Customer");
		btnNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataInputFrame dif;
				try {
					CustomerPanel bp = new CustomerPanel();
					dif = new DataInputFrame(bp);
					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewCustomer.setForeground(new Color(47, 79, 79));
		btnNewCustomer.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnNewCustomer.setBackground(new Color(102, 205, 170));
		btnNewCustomer.setBounds(387, 461, 291, 23);
		contentPane.add(btnNewCustomer);

		txtCustomerName = new JTextField();
		txtCustomerName.setForeground(new Color(47, 79, 79));
		txtCustomerName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(517, 324, 161, 25);
		contentPane.add(txtCustomerName);

		JLabel lblNewLabel_5_4_1 = new JLabel("Customer Name");
		lblNewLabel_5_4_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_5_4_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5_4_1.setBounds(387, 327, 106, 19);
		contentPane.add(lblNewLabel_5_4_1);

		JLabel lblNewLabel_5_4_1_1 = new JLabel("Customer Phone");
		lblNewLabel_5_4_1_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_5_4_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5_4_1_1.setBounds(387, 363, 106, 19);
		contentPane.add(lblNewLabel_5_4_1_1);

		txtPhone.setForeground(new Color(47, 79, 79));
		txtPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPhone.setColumns(10);
		txtPhone.setBounds(517, 360, 161, 25);
		contentPane.add(txtPhone);

		JLabel lblNewLabel_7_1_1 = new JLabel("Warranty");
		lblNewLabel_7_1_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_7_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_7_1_1.setBounds(14, 654, 120, 19);
		contentPane.add(lblNewLabel_7_1_1);


		txtCustomerName.setEnabled(false);
		txtPhone.setEnabled(false);

		lblItem = new JLabel("Item");
		lblItem.setForeground(new Color(47, 79, 79));
		lblItem.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblItem.setBounds(12, 318, 106, 19);
		contentPane.add(lblItem);

		txtItem = new JTextField();
		txtItem.setForeground(new Color(47, 79, 79));
		txtItem.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtItem.setColumns(10);
		txtItem.setBounds(140, 318, 210, 25);
		contentPane.add(txtItem);

		lblBrand = new JLabel("Brand");
		lblBrand.setForeground(new Color(47, 79, 79));
		lblBrand.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblBrand.setBounds(12, 348, 106, 19);
		contentPane.add(lblBrand);

		txtBrand = new JTextField();
		txtBrand.setForeground(new Color(47, 79, 79));
		txtBrand.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtBrand.setColumns(10);
		txtBrand.setBounds(140, 348, 210, 25);
		contentPane.add(txtBrand);

		date = new JLabel("Date");
		date.setForeground(new Color(47, 79, 79));
		date.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		date.setBounds(387, 248, 115, 19);
		contentPane.add(date);

		lblDate = new JLabel("2024-04-18");
		lblDate.setForeground(new Color(47, 79, 79));
		lblDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblDate.setBounds(527, 248, 130, 19);
		contentPane.add(lblDate);
		JLabel txtCustomerPhone;

		try {
			fillCombo();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		createTable();
		AutoID();
		date d = new date();
		lblDate.setText(d.getMySQLDateFormat());

		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.setCustomerid(txtCustomerID.getText().trim());

				if (!cc.CustomerExist(cm)) {
					JOptionPane.showMessageDialog(null, "The Customer doesn't exist", "Fail",
							JOptionPane.ERROR_MESSAGE);
				} else {
					CustomerModel verifyCus = cc.searchCustomerDetail1(cm);

					txtCustomerID.setText(verifyCus.getCustomerid());
					txtCustomerName.setText(verifyCus.getCustomername());
					txtPhone.setText(verifyCus.getPhone());
					txtAddress.setText(verifyCus.getAddress());
					txtEmail.setText(verifyCus.getEmail());

				}
			}
		});
		btnVerify.setForeground(new Color(47, 79, 79));
		btnVerify.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnVerify.setBackground(new Color(102, 205, 170));
		btnVerify.setBounds(690, 285, 89, 23);
		contentPane.add(btnVerify);

		JLabel lblNewLabel_5_4_1_2 = new JLabel("Customer Address");
		lblNewLabel_5_4_1_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_5_4_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5_4_1_2.setBounds(387, 395, 130, 19);
		contentPane.add(lblNewLabel_5_4_1_2);

		txtAddress = new JTextField();
		txtAddress.setForeground(new Color(47, 79, 79));
		txtAddress.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtAddress.setEnabled(false);
		txtAddress.setColumns(10);
		txtAddress.setBounds(517, 392, 161, 25);
		contentPane.add(txtAddress);

		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(47, 79, 79));
		txtEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(517, 428, 161, 25);
		contentPane.add(txtEmail);

		JLabel lblNewLabel_5_4_1_1_1 = new JLabel("Customer Email");
		lblNewLabel_5_4_1_1_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_5_4_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5_4_1_1_1.setBounds(387, 431, 106, 19);
		contentPane.add(lblNewLabel_5_4_1_1_1);

		txtPhone = new JTextField();
		txtPhone.setForeground(new Color(47, 79, 79));
		txtPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPhone.setEnabled(false);
		txtPhone.setColumns(10);
		txtPhone.setBounds(517, 360, 161, 23);
		contentPane.add(txtPhone);

		JLabel lblNewLabel_1_1 = new JLabel("Search by Item");
		lblNewLabel_1_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(199, 9, 99, 19);
		contentPane.add(lblNewLabel_1_1);

		txtSearchItem = new JTextField();
		txtSearchItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					showbyitem(txtSearchItem.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtSearchItem.setForeground(new Color(47, 79, 79));
		txtSearchItem.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearchItem.setColumns(10);
		txtSearchItem.setBounds(309, 10, 150, 25);
		contentPane.add(txtSearchItem);

		JLabel lblNewLabel_1_1_1 = new JLabel("Search by CustomerID");
		lblNewLabel_1_1_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(765, 9, 139, 19);
		contentPane.add(lblNewLabel_1_1_1);

		txtSearchCustomerid = new JTextField();
		txtSearchCustomerid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					showbycustomerid(txtSearchCustomerid.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtSearchCustomerid.setForeground(new Color(47, 79, 79));
		txtSearchCustomerid.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearchCustomerid.setColumns(10);
		txtSearchCustomerid.setBounds(916, 8, 150, 25);
		contentPane.add(txtSearchCustomerid);

		lblNewLabel_1_1_2 = new JLabel("Search by Serial");
		lblNewLabel_1_1_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_1_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_1_2.setBounds(1078, 9, 106, 19);
		contentPane.add(lblNewLabel_1_1_2);

		textSearchserial = new JTextField();
		textSearchserial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					showbyserial(textSearchserial.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		textSearchserial.setForeground(new Color(47, 79, 79));
		textSearchserial.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		textSearchserial.setColumns(10);
		textSearchserial.setBounds(1191, 8, 150, 25);
		contentPane.add(textSearchserial);
		
		btnOpenServiceDetail_1 = new JButton("Serial Identifier");
		btnOpenServiceDetail_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleDetailView sdv = new SaleDetailView();
				sdv.setVisible(true);
			}
		});
		btnOpenServiceDetail_1.setForeground(new Color(47, 79, 79));
		btnOpenServiceDetail_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnOpenServiceDetail_1.setBackground(new Color(102, 205, 170));
		btnOpenServiceDetail_1.setBounds(1042, 654, 291, 23);
		contentPane.add(btnOpenServiceDetail_1);
		
		btnOpenServiceDetail = new JButton("Open Service Detail");
		btnOpenServiceDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerServiceDetailView csdv = new CustomerServiceDetailView();
				csdv.setVisible(true);
			}
		});
		btnOpenServiceDetail.setForeground(new Color(47, 79, 79));
		btnOpenServiceDetail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnOpenServiceDetail.setBackground(new Color(102, 205, 170));
		btnOpenServiceDetail.setBounds(738, 654, 291, 23);
		contentPane.add(btnOpenServiceDetail);
		try {
			showList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setColoumnWidth(int index, int width) {

		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblService.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}

	public void fillCombo() throws ClassNotFoundException {
		DeptModel dm = new DeptModel();
		dm.setDeptname("service");
		SQLQueries.addStaffComboBox("staff", "staffname", comboStaff, dc.searchDeptID(dm));

	}

	public void createTable() {
		dtm.addColumn("Service ID");
		dtm.addColumn("Staff Name");
		dtm.addColumn("Item");
		dtm.addColumn("Brand");
		dtm.addColumn("Bag");
		dtm.addColumn("Fault");
		dtm.addColumn("Charger");
		dtm.addColumn("Processor");
		dtm.addColumn("Ram");
		dtm.addColumn("Harddisk");
		dtm.addColumn("Price");
		dtm.addColumn("Serial");
		dtm.addColumn("Warranty");
		dtm.addColumn("Customer ID");
		dtm.addColumn("Date");
		dtm.addColumn("Status");

		tblService.setModel(dtm);
		setColoumnWidth(0, 100);
		setColoumnWidth(1, 100);
		setColoumnWidth(2, 100);
		setColoumnWidth(3, 100);
		setColoumnWidth(4, 100);
		setColoumnWidth(5, 100);
		setColoumnWidth(6, 100);
		setColoumnWidth(7, 100);
		setColoumnWidth(8, 100);
		setColoumnWidth(9, 100);
		setColoumnWidth(10, 100);
		setColoumnWidth(11, 100);
		setColoumnWidth(12, 100);
		setColoumnWidth(13, 100);
		setColoumnWidth(14, 100);
		setColoumnWidth(15, 150);

	}

	public void AutoID() {
		ClsDBConnection dbcon = new ClsDBConnection();
		try {
			lblserviceid.setText(dbcon.AutoID("serviceid", "saleandservice.service", "SV-"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showList() throws SQLException {
		String data[] = new String[16];
		ServiceController bc = new ServiceController();

		List<ServiceModel> list = bc.selectAll();
		dtm.setRowCount(0);
		for (ServiceModel bm : list) {
			data[0] = bm.getServiceid();
			data[1] = bm.getStaffname();
			data[2] = bm.getItem();
			data[3] = bm.getBrand();
			data[4] = bm.getBag();
			data[5] = bm.getFault();
			data[6] = bm.getCharger();
			data[7] = bm.getProcessor();
			data[8] = bm.getRam();
			data[9] = bm.getHarddisk();
			data[10] = bm.getPrice() + "";
			data[11] = bm.getSerial();
			data[12] = bm.getWarranty();
			data[13] = csdc.SearchCustomer(bm.getServiceid());
			data[14] = bm.getDate();
			data[15] = csdc.searchStatus(data[0], data[13]);

			dtm.addRow(data);
		}
	}

	public void showbyitem(String name) throws SQLException {
		String data[] = new String[16];
		ServiceModel b = new ServiceModel();
		b.setItem(name);
		ServiceController bc = new ServiceController();
		List<ServiceModel> list = bc.selectbyitem(b);
		dtm.setRowCount(0);
		for (ServiceModel bm : list) {
			data[0] = bm.getServiceid();
			data[1] = bm.getStaffname();
			data[2] = bm.getItem();
			data[3] = bm.getBrand();
			data[4] = bm.getBag();
			data[5] = bm.getFault();
			data[6] = bm.getCharger();
			data[7] = bm.getProcessor();
			data[8] = bm.getRam();
			data[9] = bm.getHarddisk();
			data[10] = bm.getPrice() + "";
			data[11] = bm.getSerial();
			data[12] = bm.getWarranty();
			data[13] = csdc.SearchCustomer(bm.getServiceid());
			data[14] = bm.getDate();
			data[15] = csdc.searchStatus(data[0], data[13]);

			dtm.addRow(data);
		}
	}

	public void showbyservice(String name) throws SQLException {
		String data[] = new String[16];
		ServiceModel b = new ServiceModel();
		b.setServiceid(name);
		ServiceController bc = new ServiceController();
		List<ServiceModel> list = bc.selectbyserviceid(b);
		dtm.setRowCount(0);
		for (ServiceModel bm : list) {
			data[0] = bm.getServiceid();
			data[1] = bm.getStaffname();
			data[2] = bm.getItem();
			data[3] = bm.getBrand();
			data[4] = bm.getBag();
			data[5] = bm.getFault();
			data[6] = bm.getCharger();
			data[7] = bm.getProcessor();
			data[8] = bm.getRam();
			data[9] = bm.getHarddisk();
			data[10] = bm.getPrice() + "";
			data[11] = bm.getSerial();
			data[12] = bm.getWarranty();
			data[13] = csdc.SearchCustomer(bm.getServiceid());
			data[14] = bm.getDate();
			data[15] = csdc.searchStatus(data[0], data[13]);

			dtm.addRow(data);
		}
	}

	public void showbyserial(String name) throws SQLException {
		String data[] = new String[16];
		ServiceModel b = new ServiceModel();
		b.setSerial(name);
		ServiceController bc = new ServiceController();
		List<ServiceModel> list = bc.selectbyserial(b);
		dtm.setRowCount(0);
		for (ServiceModel bm : list) {
			data[0] = bm.getServiceid();
			data[1] = bm.getStaffname();
			data[2] = bm.getItem();
			data[3] = bm.getBrand();
			data[4] = bm.getBag();
			data[5] = bm.getFault();
			data[6] = bm.getCharger();
			data[7] = bm.getProcessor();
			data[8] = bm.getRam();
			data[9] = bm.getHarddisk();
			data[10] = bm.getPrice() + "";
			data[11] = bm.getSerial();
			data[12] = bm.getWarranty();
			data[13] = csdc.SearchCustomer(bm.getServiceid());
			data[14] = bm.getDate();
			data[15] = csdc.searchStatus(data[0], data[13]);

			dtm.addRow(data);
		}
	}

	public void showbycustomerid(String name) throws SQLException {
		String data[] = new String[16];

		ServiceModel b = new ServiceModel();

		dtm.setRowCount(0);

		ArrayList<CustomerServiceDetailModel> alist = csdc.selectbycustomerid(name);
		for (CustomerServiceDetailModel csm : alist) {
			ServiceController bc = new ServiceController();
			b.setServiceid(csm.getServiceid());
			b = bc.selectSerial(b.getServiceid());
			data[0] = b.getServiceid();
			data[1] = b.getStaffname();
			data[2] = b.getItem();
			data[3] = b.getBrand();
			data[4] = b.getBag();
			data[5] = b.getFault();
			data[6] = b.getCharger();
			data[7] = b.getProcessor();
			data[8] = b.getRam();
			data[9] = b.getHarddisk();
			data[10] = b.getPrice() + "";
			data[11] = b.getSerial();
			data[12] = b.getWarranty();
			data[13] = csdc.SearchCustomer(b.getServiceid());
			data[14] = b.getDate();
			data[15] = csdc.searchStatus(data[0], data[13]);

			dtm.addRow(data);
		}
	}

	public void delete(String sid, String cid) {
		ServiceController bc = new ServiceController();
		ServiceModel bm = new ServiceModel();

		csdm.setCustomerid(cid);
		csdm.setServiceid(sid);

		bm.setServiceid(sid);

		csdc.delete(csdm);
		bc.delete(bm);

	}

	public void clear() {
		AutoID();
		comboStaff.setSelectedIndex(0);
		txtItem.setText("");
		txtBrand.setText("");
		txtBag.setText("");
		txtFault.setText("");
		txtCharger.setText("");
		txtProcessor.setText("");
		txtRam.setText("");
		txtHarddisk.setText("");
		txtPrice.setText("");
		txtSerial.setText("");
		cboWarranty.setSelectedIndex(0);
		btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		date d = new date();
		lblDate.setText(d.getMySQLDateFormat());

		txtCustomerID.setText("");
		txtCustomerName.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
		txtEmail.setText("");
	}
}
