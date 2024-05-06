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

//import Connection.AutoID;
import Connection.Checking;
import Connection.ClsDBConnection;
import Connection.SQLQueries;
import Connection.date;
import Controller.BrandController;
import Controller.CompanyController;
import Controller.CustomerController;
import Controller.DeptController;
import Controller.ItemController;
import Controller.PurchaseController;
import Controller.PurchaseDetailController;
import Controller.SaleOrderController;
import Controller.SaleOrderDetailController;
import Controller.StaffController;
import Controller.TypeController;
import Model.BrandModel;
import Model.CompanyModel;
import Model.CustomerModel;
import Model.DeptModel;
import Model.ItemModel;
import Model.PurchaseDetailModel;
import Model.PurchaseModel;
import Model.SaleOrderDetailModel;
import Model.SaleOrderModel;
import Model.StaffModel;
import Model.TypeModel;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class SaleOrderView extends JFrame {

	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	DefaultTableModel idtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String BrandID = null;
	int r;
	String selectedSerial;

	private JScrollPane scrollPane_1;
	private JLabel lblCustomerName;
	private JTextField txtCustomerID;
	private JButton btnAdd;
	private JMenuItem mntmNewMenuItem_3;
	// String brandName;
	private JPanel panel;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JLabel lblSaleID;
	private JPanel panel_1;
	private JTextField txtAddress;
	private JTextField txtPrice;
	private JButton btnUpdate;
	private JButton btnDelete;
	String strdataitem[] = new String[10];
	String strquery[] = new String[7];
	Vector vid = new Vector();
	Vector vamount = new Vector();
	Vector vserial = new Vector();
	private JLabel lblTotal;
	private JButton btnSave;
	private JTextField txtItemName;
	private JTextField txtWarranty;
	private JTextField txtSerial;
	private boolean single;
	JComboBox cboTypeName = new JComboBox();
	JComboBox cboBrandName = new JComboBox();
	private JTextField txtRemark;
	private BrandController bc = new BrandController();
	private TypeController tc = new TypeController();
	private ItemController ic = new ItemController();
	private ItemModel im = new ItemModel();
	private CustomerModel cm = new CustomerModel();
	private CustomerController cc = new CustomerController();
	private StaffController sc = new StaffController();
	private JTextField txtItemID;
	private JTextField txtCustomerName;
	private JTable tblItem = new JTable();
	private int row;
	private JTable tblSale = new JTable();
	JComboBox cboStaff = new JComboBox();
	JComboBox cboPaymentType = new JComboBox();
	boolean update = false;

	SaleOrderDetailController sodc = new SaleOrderDetailController();
	private JTextField txtSearchbyserial;
	private JTextField txtSearchbyitemid;
	private JTextField txtSearchbyname;
	JLabel lblItemCount = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleOrderView frame = new SaleOrderView();
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
	public SaleOrderView() throws SQLException {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				showItemList();
			}

			public void windowLostFocus(WindowEvent e) {
			}
		});
		setTitle("Sale");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ButtonGroup group = new ButtonGroup();
		setBounds(0, 0, 1348, 750);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 139));
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 445, 700, 121);
		contentPane.add(scrollPane_1);

		tblSale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tblSale.getSelectedRow();
				txtItemID.setText((String) tblSale.getValueAt(row, 0));
				txtItemName.setText((String) tblSale.getValueAt(row, 1));
				cboBrandName.setSelectedItem((String) tblSale.getValueAt(row, 2));
				cboTypeName.setSelectedItem((String) tblSale.getValueAt(row, 3));
				txtPrice.setText((String) tblSale.getValueAt(row, 4));
				txtWarranty.setText((String) tblSale.getValueAt(row, 5));
				txtRemark.setText((String) tblSale.getValueAt(row, 6));
				txtSerial.setText((String) tblSale.getValueAt(row, 7));
				btnAdd.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				txtSerial.setEnabled(false);

				update = false;
			}
		});

		tblSale.setBackground(new Color(192, 192, 192));
		tblSale.setForeground(new Color(47, 79, 79));
		tblSale.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		tblSale.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(tblSale);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Customer",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(new Color(175, 238, 238));
		panel.setBounds(0, 0, 700, 155);
		contentPane.add(panel);
		panel.setLayout(null);

		lblCustomerName = new JLabel("Name");
		lblCustomerName.setBounds(10, 53, 115, 29);
		panel.add(lblCustomerName);
		lblCustomerName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblCustomerName.setForeground(new Color(47, 79, 79));

		JLabel lblCompanyId = new JLabel("Customer ID");
		lblCompanyId.setForeground(new Color(47, 79, 79));
		lblCompanyId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblCompanyId.setBounds(10, 23, 115, 19);
		panel.add(lblCompanyId);

		txtCustomerID = new JTextField();
		txtCustomerID.setBounds(132, 23, 130, 25);
		panel.add(txtCustomerID);
		txtCustomerID.setForeground(new Color(47, 79, 79));
		txtCustomerID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtCustomerID.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(new Color(47, 79, 79));
		lblAddress.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblAddress.setBounds(10, 93, 115, 19);
		panel.add(lblAddress);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(47, 79, 79));
		lblEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblEmail.setBounds(10, 123, 115, 19);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setText("");
		txtEmail.setForeground(new Color(47, 79, 79));
		txtEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(132, 119, 130, 25);
		panel.add(txtEmail);
		txtEmail.setEnabled(false);

		JLabel lblPhone = new JLabel("Phone No");
		lblPhone.setForeground(new Color(47, 79, 79));
		lblPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPhone.setBounds(376, 26, 115, 19);
		panel.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setText("");
		txtPhone.setForeground(new Color(47, 79, 79));
		txtPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPhone.setColumns(10);
		txtPhone.setBounds(451, 23, 130, 25);
		panel.add(txtPhone);

		JLabel date = new JLabel("Date");
		date.setForeground(new Color(47, 79, 79));
		date.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		date.setBounds(376, 112, 60, 19);
		panel.add(date);

		JLabel lblDate = new JLabel("");
		lblDate.setForeground(new Color(47, 79, 79));
		lblDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblDate.setBounds(466, 112, 102, 19);
		panel.add(lblDate);

		JLabel lb = new JLabel("Sale ID");
		lb.setForeground(new Color(47, 79, 79));
		lb.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lb.setBounds(376, 83, 115, 19);
		panel.add(lb);

		lblSaleID = new JLabel("");
		lblSaleID.setForeground(new Color(47, 79, 79));
		lblSaleID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblSaleID.setBounds(466, 83, 115, 19);
		panel.add(lblSaleID);

		txtAddress = new JTextField();
		txtAddress.setText("");
		txtAddress.setForeground(new Color(47, 79, 79));
		txtAddress.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtAddress.setColumns(10);
		txtAddress.setBounds(132, 87, 130, 25);
		panel.add(txtAddress);
		txtAddress.setEnabled(false);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(175, 238, 238));
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Item",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(0, 166, 700, 268);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setForeground(new Color(47, 79, 79));
		lblItemName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblItemName.setBounds(10, 63, 115, 14);
		panel_1.add(lblItemName);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(47, 79, 79));
		lblPrice.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPrice.setBounds(379, 61, 59, 19);
		panel_1.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setEnabled(false);
		txtPrice.setText("");
		txtPrice.setForeground(new Color(47, 79, 79));
		txtPrice.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(453, 59, 130, 25);
		panel_1.add(txtPrice);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				im.setItemid(txtItemID.getText().trim());

				if (txtItemID.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "You must choose Item ID!");
					txtItemName.requestFocus();
				} else if (sodc.itemBought(txtItemID.getText())) {
					JOptionPane.showMessageDialog(null, "This item has already been sold!");
					txtItemName.requestFocus();
				} else if (!ic.itemidExist(im.getItemid())) {
					JOptionPane.showMessageDialog(null, "The id doesn't exist", "Fail", JOptionPane.ERROR_MESSAGE);
				} else if (txtSerial.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Serial Cannot be blank, if there's no serial use -");
					txtItemName.requestFocus();
				} else if (!Checking.checktxtprice(txtPrice.getText())) {
//					JOptionPane.showMessageDialog(null, "You must enter less than 1000000000");
					txtItemName.requestFocus();
				}
//				else if (ic.serialExist(txtSerial.getText()) && !txtSerial.getText().equals("-")) {
//					JOptionPane.showMessageDialog(null, "The serial already existed in database");
//					txtItemName.requestFocus();
//					Clear();
//				}
				else if (Checking.IsContain(txtSerial.getText(), vserial) && !txtSerial.getText().equals("-")
						|| Checking.IsContain(txtItemID.getText(), vid)) {
					JOptionPane.showMessageDialog(null, "The serial or item you selected is already existed!");
					txtItemName.requestFocus();
				} else {
					itemsetmethod();
					itemaddmethod();
					lblTotal.setText(Checking.Sumamount(vamount, 1));
					Clear();
					txtItemID.setEnabled(true);
					txtSerial.setEnabled(true);
				}

			}
		});
		btnAdd.setBounds(418, 164, 89, 23);
		panel_1.add(btnAdd);

		btnAdd.setBackground(new Color(102, 205, 170));
		btnAdd.setForeground(new Color(47, 79, 79));
		btnAdd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				im.setItemid(txtItemID.getText().trim());
				if (!update) {
					JOptionPane.showMessageDialog(null, "Please Verify ID first");
					txtItemName.requestFocus();
				} else if (txtItemID.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "You must choose Item ID!");
					txtItemName.requestFocus();
				} else if (!ic.itemidExist(im.getItemid())) {
					JOptionPane.showMessageDialog(null, "The id doesn't exist", "Fail", JOptionPane.ERROR_MESSAGE);
				} else if (txtSerial.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Serial Cannot be blank, if there's no serial use -");
					txtItemName.requestFocus();
				} else if (!Checking.checktxtprice(txtPrice.getText())) {
//					JOptionPane.showMessageDialog(null, "You must enter less than 1000000000");
					txtItemName.requestFocus();
				}
//				else if (ic.serialExist(txtSerial.getText()) && !txtSerial.getText().equals("-")) {
//					JOptionPane.showMessageDialog(null, "The serial already existed in database");
//					txtItemName.requestFocus();
//					Clear();
//				}
				else if (Checking.IsContain(txtSerial.getText(), vserial) && !txtSerial.getText().equals("-")
						|| Checking.IsContain(txtItemID.getText(), vid)) {
					JOptionPane.showMessageDialog(null, "The serial or item you selected is already existed!");
					txtItemName.requestFocus();
				} else {
					deleteRow();
					itemsetmethod();
					itemaddmethod();
//					lblTotal.setText(Checking.Sumamount(vamount, 1) + "Kyats");
					lblTotal.setText(Checking.Sumamount(vamount, 1));
					Clear();
//					comboItem.setSelectedIndex(0);
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
					btnAdd.setEnabled(true);
				}
			}
		});
		btnUpdate.setForeground(new Color(47, 79, 79));
		btnUpdate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnUpdate.setEnabled(true);
		btnUpdate.setBackground(new Color(102, 205, 170));
		btnUpdate.setBounds(554, 164, 89, 23);
		panel_1.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblSale.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Please select row to delete");
				} else {
					deleteRow();
					Clear();
					lblTotal.setText(Checking.Sumamount(vamount, 1));
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
					btnAdd.setEnabled(true);
				}
			}
		});
		btnDelete.setForeground(new Color(47, 79, 79));
		btnDelete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnDelete.setEnabled(true);
		btnDelete.setBackground(new Color(102, 205, 170));
		btnDelete.setBounds(418, 211, 89, 23);
		panel_1.add(btnDelete);

		txtItemName = new JTextField();
		txtItemName.setEnabled(false);
		txtItemName.setText("");
		txtItemName.setForeground(new Color(47, 79, 79));
		txtItemName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtItemName.setColumns(10);
		txtItemName.setBounds(135, 59, 130, 25);
		panel_1.add(txtItemName);

		JLabel lblNewLabel_3_1 = new JLabel("Brand Name");
		lblNewLabel_3_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(10, 94, 87, 19);
		panel_1.add(lblNewLabel_3_1);
		cboBrandName.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));
		cboBrandName.setEnabled(false);

		cboBrandName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		cboBrandName.setBounds(135, 95, 130, 25);
		panel_1.add(cboBrandName);
		cboTypeName.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));
		cboTypeName.setEnabled(false);

		cboTypeName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		cboTypeName.setBounds(135, 130, 130, 25);
		panel_1.add(cboTypeName);

		JLabel lblNewLabel_3_2 = new JLabel("Type Name");
		lblNewLabel_3_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(10, 130, 87, 19);
		panel_1.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_3 = new JLabel("Warranty");
		lblNewLabel_3_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_3.setBounds(10, 166, 87, 19);
		panel_1.add(lblNewLabel_3_3);

		JLabel lblNewLabel_3_4 = new JLabel("Serial No");
		lblNewLabel_3_4.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_4.setBounds(379, 25, 87, 25);
		panel_1.add(lblNewLabel_3_4);

		txtWarranty = new JTextField();
		txtWarranty.setEnabled(false);
		txtWarranty.setText("");
		txtWarranty.setForeground(new Color(47, 79, 79));
		txtWarranty.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtWarranty.setColumns(10);
		txtWarranty.setBounds(135, 164, 130, 25);
		panel_1.add(txtWarranty);

		txtSerial = new JTextField();
		txtSerial.setText("");
		txtSerial.setForeground(new Color(47, 79, 79));
		txtSerial.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSerial.setColumns(10);
		txtSerial.setBounds(453, 25, 130, 25);
		panel_1.add(txtSerial);
		txtSerial.setText("-");

		JLabel lblNewLabel_3_4_1 = new JLabel("Remark");
		lblNewLabel_3_4_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_4_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_4_1.setBounds(379, 96, 59, 14);
		panel_1.add(lblNewLabel_3_4_1);

		txtRemark = new JTextField();
		txtRemark.setEnabled(false);
		txtRemark.setText("");
		txtRemark.setForeground(new Color(47, 79, 79));
		txtRemark.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtRemark.setColumns(10);
		txtRemark.setBounds(453, 92, 130, 25);
		panel_1.add(txtRemark);

		JLabel lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setForeground(new Color(47, 79, 79));
		lblTotalAmount.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblTotalAmount.setBounds(423, 593, 96, 23);
		contentPane.add(lblTotalAmount);

		lblTotal = new JLabel("");
		lblTotal.setForeground(new Color(47, 79, 79));
		lblTotal.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblTotal.setBounds(539, 593, 115, 23);
		contentPane.add(lblTotal);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					cm.setCustomerid(txtCustomerID.getText().trim());
					if (cboStaff.getSelectedIndex() <= 0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Staff", "Fail", JOptionPane.ERROR_MESSAGE);
					} else if (!cc.CustomerExist(cm)) {
						JOptionPane.showMessageDialog(null, "The Customer doesn't exist", "Fail",
								JOptionPane.ERROR_MESSAGE);
					} else if (tblSale.getRowCount() <= 0) {
						JOptionPane.showMessageDialog(null, "No Item has been added yet", "Fail",
								JOptionPane.ERROR_MESSAGE);
					} else if (cboPaymentType.getSelectedIndex() <= 0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Payment type", "Fail",
								JOptionPane.ERROR_MESSAGE);
					}

					else if (JOptionPane.showConfirmDialog(null, "Are you sure do you want to Purchase?", "Confirm",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						StaffModel sm = new StaffModel();
						sm.setStaffname((String) cboStaff.getSelectedItem());
						int save = 0;
						SaleOrderController soc = new SaleOrderController();
						SaleOrderModel som = new SaleOrderModel();
						som.setSaleorderid(lblSaleID.getText().trim());
						som.setStaffid(sc.searchStaffId(sm));
						som.setDate(lblDate.getText());
						som.setPaymenttype((String) cboPaymentType.getSelectedItem());
						if (cboPaymentType.getSelectedIndex() == 1) {
							
							som.setAmount(Integer.valueOf(Checking.calculateAmount(vamount, 1)));
						} else if (cboPaymentType.getSelectedIndex() == 2) {
							System.out.println("install");
							int amount = Integer.valueOf(Checking.calculateAmount(vamount, 1));
							som.setAmount(amount + (amount / 10));

						}
//						som.setAmount(Integer.valueOf(Checking.calculateAmount(vamount, 1)));
						save = soc.insert(som);
						if (save == 1) {
							for (int i = 0; i < vid.size(); i++) {
								String purchase = (String) tblSale.getValueAt(i, 1);
								String[] part = purchase.split("/");

								SaleOrderDetailController sodc = new SaleOrderDetailController();
								SaleOrderDetailModel sodm = new SaleOrderDetailModel();
								sodm.setCustomerid(txtCustomerID.getText().trim());
								sodm.setSaleorderid(lblSaleID.getText().trim());
								sodm.setItemid((String) tblSale.getValueAt(i, 0));
								sodm.setSerial((String) tblSale.getValueAt(i, 7));
								sodm.setPrice(Integer.valueOf((String) tblSale.getValueAt(i, 4)));
								save = sodc.insert(sodm);

							}
						}
						if (save == 1) {
							JOptionPane.showMessageDialog(null, "All records are successfully saved!");
							if (JOptionPane.showConfirmDialog(null, "Do you want to go to payment?", "Confirm",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
								PaymentView pv;
								try {
									pv = new PaymentView();
									pv.setVisible(true);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
							AutoID();
							showItemList();
							ClearAll();
						} else {
							JOptionPane.showMessageDialog(null, "All records are fail saved!");
						}
						btnSave.setEnabled(true);
						btnUpdate.setEnabled(false);
						btnDelete.setEnabled(false);
					}
				}
			}
		});
		btnSave.setForeground(new Color(47, 79, 79));
		btnSave.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnSave.setEnabled(true);
		btnSave.setBackground(new Color(102, 205, 170));
		btnSave.setBounds(611, 665, 89, 23);
		contentPane.add(btnSave);

		try {
			fillCombo();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createTable();
		AutoID();
//		Clear();
//		showList();
		date d = new date();
		lblDate.setText(d.getMySQLDateFormat());

		txtCustomerName = new JTextField();
		txtCustomerName.setForeground(new Color(47, 79, 79));
		txtCustomerName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(132, 55, 130, 25);
		txtCustomerName.setEnabled(false);
		panel.add(txtCustomerName);

		JButton btnVerifyCID = new JButton("Verify");
		btnVerifyCID.addActionListener(new ActionListener() {
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
		btnVerifyCID.setForeground(new Color(47, 79, 79));
		btnVerifyCID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnVerifyCID.setBackground(new Color(102, 205, 170));
		btnVerifyCID.setBounds(274, 25, 89, 23);
		panel.add(btnVerifyCID);

		JButton btnVerifyPhone = new JButton("Verify");
		btnVerifyPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.setPhone(txtPhone.getText().trim());

				if (!cc.phoneExist(cm)) {
					JOptionPane.showMessageDialog(null, "The Customer doesn't exist", "Fail",
							JOptionPane.ERROR_MESSAGE);
				} else {
					CustomerModel verifyCus = cc.searchbyPhone(cm);

					txtCustomerID.setText(verifyCus.getCustomerid());
					txtCustomerName.setText(verifyCus.getCustomername());
					txtPhone.setText(verifyCus.getPhone());
					txtAddress.setText(verifyCus.getAddress());
					txtEmail.setText(verifyCus.getEmail());

				}
			}
		});
		btnVerifyPhone.setForeground(new Color(47, 79, 79));
		btnVerifyPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnVerifyPhone.setBackground(new Color(102, 205, 170));
		btnVerifyPhone.setBounds(593, 23, 89, 23);
		panel.add(btnVerifyPhone);

		btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);

		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setForeground(new Color(47, 79, 79));
		lblItemId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblItemId.setBounds(10, 27, 115, 19);
		panel_1.add(lblItemId);

		txtItemID = new JTextField();
		txtItemID.setText("");
		txtItemID.setForeground(new Color(47, 79, 79));
		txtItemID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtItemID.setColumns(10);
		txtItemID.setBounds(135, 24, 130, 25);
		panel_1.add(txtItemID);

		JButton btnVerifyItemID = new JButton("Verify");
		btnVerifyItemID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				im.setItemid(txtItemID.getText().trim());

				if (!ic.itemidExist(im.getItemid())) {
					JOptionPane.showMessageDialog(null, "The id doesn't exist", "Fail", JOptionPane.ERROR_MESSAGE);
				} else {
					try {

						ItemModel verifyItem = ic.searchbyItemid(im.getItemid());
						txtItemID.setText(verifyItem.getItemid());
						String brandid = verifyItem.getBrandid();
						String typeid = verifyItem.getTypeid();
						BrandModel bm = new BrandModel();
						bm.setBrandid(brandid);
						TypeModel tm = new TypeModel();
						tm.setTypeid(typeid);
						cboBrandName.setSelectedItem(bc.searchBrandname(bm));
						cboTypeName.setSelectedItem(tc.searchTypeName(tm));
						txtItemName.setText(verifyItem.getItemname());
						txtPrice.setText(verifyItem.getCurrentprice() + "");
						txtWarranty.setText(verifyItem.getWarranty());
						txtRemark.setText(verifyItem.getRemark());
						txtSerial.setText(verifyItem.getSerial());

						txtItemID.setEnabled(false);
						txtSerial.setEnabled(false);

						update = true;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnVerifyItemID.setForeground(new Color(47, 79, 79));
		btnVerifyItemID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnVerifyItemID.setBackground(new Color(102, 205, 170));
		btnVerifyItemID.setBounds(278, 23, 89, 23);
		panel_1.add(btnVerifyItemID);

		JButton btnVerifySerial = new JButton("Verify");
		btnVerifySerial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				im.setSerial(txtSerial.getText().trim());
				if (txtSerial.getText().equals("-")) {
					JOptionPane.showMessageDialog(null, "The Serial cannot be \"-\"", "Fail",
							JOptionPane.ERROR_MESSAGE);
				} else if (!ic.serialExist(im.getSerial())) {
					JOptionPane.showMessageDialog(null, "The Serial doesn't exist", "Fail", JOptionPane.ERROR_MESSAGE);
				} else {
					try {

						ItemModel verifyItem = ic.searchbySerial(im.getSerial());
						txtItemID.setText(verifyItem.getItemid());
						String brandid = verifyItem.getBrandid();
						String typeid = verifyItem.getTypeid();
						BrandModel bm = new BrandModel();
						bm.setBrandid(brandid);
						TypeModel tm = new TypeModel();
						tm.setTypeid(typeid);
						cboBrandName.setSelectedItem(bc.searchBrandname(bm));
						cboTypeName.setSelectedItem(tc.searchTypeName(tm));
						txtItemName.setText(verifyItem.getItemname());
						txtPrice.setText(verifyItem.getCurrentprice() + "");
						txtWarranty.setText(verifyItem.getWarranty());
						txtRemark.setText(verifyItem.getRemark());
						txtSerial.setText(verifyItem.getSerial());

						txtItemID.setEnabled(false);
						txtSerial.setEnabled(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		btnVerifySerial.setForeground(new Color(47, 79, 79));
		btnVerifySerial.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnVerifySerial.setBackground(new Color(102, 205, 170));
		btnVerifySerial.setBounds(595, 25, 89, 23);
		panel_1.add(btnVerifySerial);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdd.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				Clear();
			}
		});
		btnClear.setForeground(new Color(47, 79, 79));
		btnClear.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnClear.setBackground(new Color(102, 205, 170));
		btnClear.setBounds(554, 211, 89, 23);
		panel_1.add(btnClear);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(712, 0, 608, 566);
		contentPane.add(scrollPane_2);
		tblItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tblItem.getSelectedRow();
				txtItemID.setText((String) tblItem.getValueAt(row, 0));
				txtItemName.setText((String) tblItem.getValueAt(row, 1));
				cboBrandName.setSelectedItem((String) tblItem.getValueAt(row, 2));
				cboTypeName.setSelectedItem((String) tblItem.getValueAt(row, 3));
				txtPrice.setText((String) tblItem.getValueAt(row, 4));
				txtWarranty.setText((String) tblItem.getValueAt(row, 5));
				txtRemark.setText((String) tblItem.getValueAt(row, 6));
				txtSerial.setText((String) tblItem.getValueAt(row, 7));

				btnAdd.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);

				txtItemID.setEnabled(false);
				txtSerial.setEnabled(false);
			}
		});
		tblItem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPane_2.setViewportView(tblItem);
		cboPaymentType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cboPaymentType.getSelectedIndex() == 1) {
					lblTotal.setText(Integer.valueOf(Checking.calculateAmount(vamount, 1)) + "");
				} else if (cboPaymentType.getSelectedIndex() == 2) {
					int amount = Integer.valueOf(Checking.calculateAmount(vamount, 1));
					lblTotal.setText((amount + (amount / 10)) + "");
				}
			}
		});
		cboPaymentType.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		cboPaymentType.setBounds(279, 592, 121, 25);
		cboPaymentType.addItem("Choose");
		cboPaymentType.addItem("Full Payment");
		cboPaymentType.addItem("Installment");
		contentPane.add(cboPaymentType);
		cboStaff.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));
		cboStaff.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		cboStaff.setBounds(66, 592, 115, 25);
		contentPane.add(cboStaff);

		txtSearchbyserial = new JTextField();
		txtSearchbyserial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				showItemListbyserial(txtSearchbyserial.getText());
			}
		});
		txtSearchbyserial.setText("");
		txtSearchbyserial.setForeground(new Color(47, 79, 79));
		txtSearchbyserial.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearchbyserial.setColumns(10);
		txtSearchbyserial.setBounds(867, 576, 130, 25);
		contentPane.add(txtSearchbyserial);

		txtSearchbyitemid = new JTextField();
		txtSearchbyitemid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				showItemListbyid(txtSearchbyitemid.getText());
			}
		});
		txtSearchbyitemid.setText("");
		txtSearchbyitemid.setForeground(new Color(47, 79, 79));
		txtSearchbyitemid.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearchbyitemid.setColumns(10);
		txtSearchbyitemid.setBounds(1148, 576, 130, 25);
		contentPane.add(txtSearchbyitemid);

		JLabel lblSearchByItem = new JLabel("Search by Item ID");
		lblSearchByItem.setForeground(new Color(47, 79, 79));
		lblSearchByItem.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblSearchByItem.setBounds(1021, 576, 115, 23);
		contentPane.add(lblSearchByItem);

		JLabel lblSearchBySerial = new JLabel("Search by Serial");
		lblSearchBySerial.setForeground(new Color(47, 79, 79));
		lblSearchBySerial.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblSearchBySerial.setBounds(712, 576, 115, 23);
		contentPane.add(lblSearchBySerial);

		txtSearchbyname = new JTextField();
		txtSearchbyname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				showItemListbyname(txtSearchbyname.getText());

				lblItemCount.setText(showCount(txtSearchbyname.getText()) + " results have been found");
			}
		});
		txtSearchbyname.setText("");
		txtSearchbyname.setForeground(new Color(47, 79, 79));
		txtSearchbyname.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearchbyname.setColumns(10);
		txtSearchbyname.setBounds(867, 611, 130, 25);
		contentPane.add(txtSearchbyname);

		JLabel lblSearchByItem_1 = new JLabel("Search by Item Name");
		lblSearchByItem_1.setForeground(new Color(47, 79, 79));
		lblSearchByItem_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblSearchByItem_1.setBounds(712, 611, 139, 23);
		contentPane.add(lblSearchByItem_1);

		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showItemList();
			}
		});
		btnShowAll.setForeground(new Color(47, 79, 79));
		btnShowAll.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnShowAll.setEnabled(true);
		btnShowAll.setBackground(new Color(102, 205, 170));
		btnShowAll.setBounds(1148, 611, 130, 23);
		contentPane.add(btnShowAll);

		JButton btnShowAll_1 = new JButton("Item View");
		btnShowAll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemView csdv = new ItemView();
				csdv.setVisible(true);
			}
		});
		btnShowAll_1.setForeground(new Color(47, 79, 79));
		btnShowAll_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnShowAll_1.setEnabled(true);
		btnShowAll_1.setBackground(new Color(102, 205, 170));
		btnShowAll_1.setBounds(150, 629, 130, 23);
		contentPane.add(btnShowAll_1);

		JButton btnShowAll_1_1 = new JButton("Sale View");
		btnShowAll_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleView sv = new SaleView();
				sv.setVisible(true);
			}
		});
		btnShowAll_1_1.setForeground(new Color(47, 79, 79));
		btnShowAll_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnShowAll_1_1.setEnabled(true);
		btnShowAll_1_1.setBackground(new Color(102, 205, 170));
		btnShowAll_1_1.setBounds(12, 629, 130, 23);
		contentPane.add(btnShowAll_1_1);

		JLabel lblStaff = new JLabel("Staff:");
		lblStaff.setForeground(new Color(47, 79, 79));
		lblStaff.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblStaff.setBounds(22, 593, 96, 23);
		contentPane.add(lblStaff);

		JLabel lblPaymentType = new JLabel("Payment:");
		lblPaymentType.setForeground(new Color(47, 79, 79));
		lblPaymentType.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPaymentType.setBounds(213, 593, 96, 23);
		contentPane.add(lblPaymentType);

		JButton btnShowAll_1_2 = new JButton("Add Customer");
		btnShowAll_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CustomerPanel cp = new CustomerPanel();
					DataInputFrame dif = new DataInputFrame(cp);
					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowAll_1_2.setForeground(new Color(47, 79, 79));
		btnShowAll_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnShowAll_1_2.setEnabled(true);
		btnShowAll_1_2.setBackground(new Color(102, 205, 170));
		btnShowAll_1_2.setBounds(12, 665, 268, 23);
		contentPane.add(btnShowAll_1_2);

		lblItemCount.setForeground(new Color(47, 79, 79));
		lblItemCount.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblItemCount.setBounds(712, 644, 285, 23);
		contentPane.add(lblItemCount);
		try {
			fillStaffCombo();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		createItemTable();
		showItemList();
	}

	public void fillCombo() throws ClassNotFoundException {
		SQLQueries.addComboBox("brand", "brandname", cboBrandName);
		SQLQueries.addComboBox("type", "typename", cboTypeName);
	}

	public void fillStaffCombo() throws ClassNotFoundException {
		DeptModel dm = new DeptModel();
		DeptController dc = new DeptController();
		dm.setDeptname("sale");
		SQLQueries.addStaffComboBox("staff", "staffname", cboStaff, dc.searchDeptID(dm));

	}

	public void setColoumnWidth(int index, int width) {

		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblSale.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}

	public void setColoumnWidthitem(int index, int width) {

		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblItem.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}

	public void AutoID() {
		ClsDBConnection dbcon = new ClsDBConnection();
		try {
			lblSaleID.setText(dbcon.AutoID("saleorderid", "saleandservice.saleorder", "SA-"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void itemsetmethod() {
		strdataitem[0] = txtItemID.getText().trim();
		strdataitem[1] = txtItemName.getText().trim();
		strdataitem[2] = (String) cboBrandName.getSelectedItem();
		strdataitem[3] = (String) cboTypeName.getSelectedItem();
		strdataitem[4] = txtPrice.getText().trim();
		strdataitem[5] = txtWarranty.getText().trim();
		strdataitem[6] = txtRemark.getText().trim();
		strdataitem[7] = txtSerial.getText().trim();

	}

	public void itemaddmethod() {
//		strdataitem[0] = String.valueOf(vid.size() + 1);
//		vid.addElement(strdataitem[1]);
//		strdataitem[1] += "/" + strdataitem[7] + "/" + strdataitem[8] + "/" + strdataitem[9];
//		strdataitem[2] = txtSerial.getText();
//		strdataitem[5] = txtPrice.getText();
//		strdataitem[6] = txtQty.getText();
//		vserial.addElement(strdataitem[2]);

		Long amount = Long.parseLong(strdataitem[4]);
		strdataitem[4] = String.valueOf(amount);
		vamount.addElement(strdataitem[4]);
		vid.addElement(strdataitem[0]);
		vserial.addElement(strdataitem[7]);
		dtm.addRow(strdataitem);
		tblSale.setModel(dtm);
//		comboItem.requestFocus();
	}

	public void createTable() {
		dtm.addColumn("ID");
		dtm.addColumn("Item");
		dtm.addColumn("Brand");
		dtm.addColumn("Type");
		dtm.addColumn("Price");
		dtm.addColumn("Warranty");
		dtm.addColumn("Remark");
		dtm.addColumn("Serial");
		tblSale.setModel(dtm);
		setColoumnWidth(0, 30);
		setColoumnWidth(1, 150);
		setColoumnWidth(2, 100);
		setColoumnWidth(3, 100);
		setColoumnWidth(4, 90);
		setColoumnWidth(5, 90);
		setColoumnWidth(6, 150);
		setColoumnWidth(7, 150);

	}

	public void createItemTable() {
		idtm.addColumn("ID");
		idtm.addColumn("Item");
		idtm.addColumn("Brand");
		idtm.addColumn("Type");
		idtm.addColumn("Price");
		idtm.addColumn("Warranty");
		idtm.addColumn("Remark");
		idtm.addColumn("Serial");
		tblItem.setModel(idtm);

		setColoumnWidthitem(0, 30);
		setColoumnWidthitem(1, 150);
		setColoumnWidthitem(2, 50);
		setColoumnWidthitem(3, 50);
		setColoumnWidthitem(4, 50);
		setColoumnWidthitem(5, 70);
		setColoumnWidthitem(6, 200);
		setColoumnWidthitem(7, 200);

	}

	public void showItemList() {
		String data[] = new String[8];
		try {
			ArrayList<ItemModel> list = ic.selectStock();
			idtm.setRowCount(0);
			for (ItemModel iml : list) {
				BrandModel bm = new BrandModel();
				bm.setBrandid(iml.getBrandid());
				TypeModel tm = new TypeModel();
				tm.setTypeid(iml.getTypeid());
				data[0] = iml.getItemid();
				data[1] = iml.getItemname();
				data[2] = bc.searchBrandname(bm);
				data[3] = tc.searchTypeName(tm);
				data[4] = iml.getCurrentprice() + "";
				data[5] = iml.getWarranty();
				data[6] = iml.getRemark();
				data[7] = iml.getSerial();

				idtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showItemListbyid(String name) {
		String data[] = new String[8];
		try {
			ArrayList<ItemModel> list = ic.selectStockbyid(name);
			idtm.setRowCount(0);
			for (ItemModel iml : list) {
				BrandModel bm = new BrandModel();
				bm.setBrandid(iml.getBrandid());
				TypeModel tm = new TypeModel();
				tm.setTypeid(iml.getTypeid());
				data[0] = iml.getItemid();
				data[1] = iml.getItemname();
				data[2] = bc.searchBrandname(bm);
				data[3] = tc.searchTypeName(tm);
				data[4] = iml.getCurrentprice() + "";
				data[5] = iml.getWarranty();
				data[6] = iml.getRemark();
				data[7] = iml.getSerial();

				idtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showItemListbyserial(String name) {
		String data[] = new String[8];
		try {
			ArrayList<ItemModel> list = ic.selectStockbyserial(name);
			idtm.setRowCount(0);
			for (ItemModel iml : list) {
				BrandModel bm = new BrandModel();
				bm.setBrandid(iml.getBrandid());
				TypeModel tm = new TypeModel();
				tm.setTypeid(iml.getTypeid());
				data[0] = iml.getItemid();
				data[1] = iml.getItemname();
				data[2] = bc.searchBrandname(bm);
				data[3] = tc.searchTypeName(tm);
				data[4] = iml.getCurrentprice() + "";
				data[5] = iml.getWarranty();
				data[6] = iml.getRemark();
				data[7] = iml.getSerial();

				idtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showItemListbyname(String name) {
		String data[] = new String[8];
		try {
			ArrayList<ItemModel> list = ic.selectStockbyname(name);
			idtm.setRowCount(0);
			for (ItemModel iml : list) {
				BrandModel bm = new BrandModel();
				bm.setBrandid(iml.getBrandid());
				TypeModel tm = new TypeModel();
				tm.setTypeid(iml.getTypeid());
				data[0] = iml.getItemid();
				data[1] = iml.getItemname();
				data[2] = bc.searchBrandname(bm);
				data[3] = tc.searchTypeName(tm);
				data[4] = iml.getCurrentprice() + "";
				data[5] = iml.getWarranty();
				data[6] = iml.getRemark();
				data[7] = iml.getSerial();

				idtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int showCount(String name) {
		int i = 0;
		i = ic.namecount(name);

		return i;

	}

	public void deleteRow() {
		if (!((String) tblSale.getValueAt(row, 7)).equals("-")) {
			vserial.remove(row);
		}
		vamount.remove(row);
		vid.remove(row);
		dtm.removeRow(row);
	}

	public void Clear() {
		txtItemID.setText("");
		txtItemName.setText("");
		txtWarranty.setText("");
		cboBrandName.setSelectedIndex(0);
		cboTypeName.setSelectedIndex(0);
		txtRemark.setText("");
		txtSerial.setText("-");
		txtPrice.setText("");

		txtItemID.setEnabled(true);
		txtSerial.setEnabled(true);
		showItemList();
	}

	public void ClearAll() {
		txtItemID.setText("");
		txtItemName.setText("");
		txtWarranty.setText("");
		cboBrandName.setSelectedIndex(0);
		cboTypeName.setSelectedIndex(0);
		txtRemark.setText("");
		txtSerial.setText("-");
		txtPrice.setText("");
		txtCustomerID.setText("");
		txtPhone.setText("");
		txtCustomerName.setText("");
		txtAddress.setText("");
		txtEmail.setText("");
		dtm.setRowCount(0);
		cboStaff.setSelectedIndex(0);
		vid.removeAllElements();
		vserial.removeAllElements();
		vamount.removeAllElements();
		lblTotal.setText("");
	}
}
