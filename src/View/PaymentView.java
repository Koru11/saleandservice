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
import Controller.PaymentController;
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
import Model.PaymentModel;
import Model.PurchaseDetailModel;
import Model.PurchaseModel;
import Model.SaleModel;
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

public class PaymentView extends JFrame {

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
	private JMenuItem mntmNewMenuItem_3;
	// String brandName;
	private JPanel panel;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JButton btnUpdate;
	private JButton btnDelete;

	String strdataitem[] = new String[10];
	String strquery[] = new String[7];
	Vector vid = new Vector();
	Vector vamount = new Vector();
	Vector vserial = new Vector();
	private JLabel lblTotal;
	private JButton btnSave;
	private boolean single;
	private BrandController bc = new BrandController();
	private TypeController tc = new TypeController();
	private ItemController ic = new ItemController();
	private PaymentController pc = new PaymentController();
	private ItemModel im = new ItemModel();
	private CustomerModel cm = new CustomerModel();
	private CustomerController cc = new CustomerController();
	private StaffController sc = new StaffController();
	private SaleOrderController soc = new SaleOrderController();
	private JTextField txtCustomerName;
	private JTable tblSale = new JTable();
	private int irow;
	private int row;
	private JTable tblPayment = new JTable();
	JComboBox cboStaff = new JComboBox();
	JComboBox cboPaymentType = new JComboBox();
	boolean update = false;
	JLabel lblPaymentID = new JLabel("");
	JButton btnDeleteSale = new JButton("Delete Sale");

	SaleOrderDetailController sodc = new SaleOrderDetailController();
	private JTextField txtSearchbysaleid;
	private JTextField txtSearchbypaymentid;
	private JTextField txtSaleorderid;
	private JTextField txtRemark;
	private JTextField txtPayamount;
	private JTextField txtsearchbysaleID;
	private JLabel lblPaymentIndicator = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentView frame = new PaymentView();
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
	public PaymentView() throws SQLException {
		Color txtcolor = new Color(0, 0, 0);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ButtonGroup group = new ButtonGroup();
		setBounds(0, 0, 1348, 689);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 139));
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(712, 351, 608, 260);
		contentPane.add(scrollPane_1);

		tblPayment.setBackground(new Color(192, 192, 192));
		tblPayment.setForeground(new Color(47, 79, 79));
		tblPayment.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		tblPayment.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(tblPayment);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Payment",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(new Color(175, 238, 238));
		panel.setBounds(0, 0, 700, 611);
		contentPane.add(panel);
		panel.setLayout(null);

		lblCustomerName = new JLabel("Name");
		lblCustomerName.setBounds(10, 60, 115, 19);
		panel.add(lblCustomerName);
		lblCustomerName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblCustomerName.setForeground(new Color(47, 79, 79));

		JLabel lblCompanyId = new JLabel("Customer ID");
		lblCompanyId.setForeground(new Color(47, 79, 79));
		lblCompanyId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblCompanyId.setBounds(10, 20, 115, 19);
		panel.add(lblCompanyId);

		txtCustomerID = new JTextField();
		txtCustomerID.setBounds(132, 20, 130, 25);
		panel.add(txtCustomerID);
		txtCustomerID.setForeground(new Color(47, 79, 79));
		txtCustomerID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtCustomerID.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(new Color(47, 79, 79));
		lblAddress.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblAddress.setBounds(10, 100, 115, 19);
		panel.add(lblAddress);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(47, 79, 79));
		lblEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblEmail.setBounds(10, 140, 115, 19);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setText("");
		txtEmail.setForeground(new Color(47, 79, 79));
		txtEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(132, 140, 130, 25);
		panel.add(txtEmail);
		txtEmail.setEnabled(false);

		JLabel lblPhone = new JLabel("Phone No");
		lblPhone.setForeground(new Color(47, 79, 79));
		lblPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPhone.setBounds(376, 23, 115, 19);
		panel.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setText("");
		txtPhone.setForeground(new Color(47, 79, 79));
		txtPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPhone.setColumns(10);
		txtPhone.setBounds(472, 20, 130, 25);
		panel.add(txtPhone);

		JLabel date = new JLabel("Date");
		date.setForeground(new Color(47, 79, 79));
		date.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		date.setBounds(10, 420, 60, 19);
		panel.add(date);

		JLabel lblDate = new JLabel("");
		lblDate.setForeground(new Color(47, 79, 79));
		lblDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblDate.setBounds(134, 420, 102, 19);
		panel.add(lblDate);

		JLabel lb = new JLabel("Payment ID");
		lb.setForeground(new Color(47, 79, 79));
		lb.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lb.setBounds(10, 380, 115, 19);
		panel.add(lb);

		lblPaymentID.setForeground(new Color(47, 79, 79));
		lblPaymentID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPaymentID.setBounds(134, 380, 115, 19);
		panel.add(lblPaymentID);

		txtAddress = new JTextField();
		txtAddress.setText("");
		txtAddress.setForeground(new Color(47, 79, 79));
		txtAddress.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtAddress.setColumns(10);
		txtAddress.setBounds(132, 100, 130, 25);
		panel.add(txtAddress);
		txtAddress.setEnabled(false);

		txtCustomerName = new JTextField();
		txtCustomerName.setForeground(new Color(47, 79, 79));
		txtCustomerName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(132, 60, 130, 25);
		txtCustomerName.setEnabled(false);
		panel.add(txtCustomerName);
		cboStaff.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		cboStaff.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));
		cboStaff.setBounds(132, 180, 130, 25);
		panel.add(cboStaff);
		cboPaymentType.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		cboPaymentType.setBounds(132, 220, 130, 25);
		panel.add(cboPaymentType);
		cboPaymentType.addItem("Full Payment");
		cboPaymentType.addItem("Installment");

		JLabel lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setBounds(10, 460, 96, 23);
		panel.add(lblTotalAmount);
		lblTotalAmount.setForeground(new Color(47, 79, 79));
		lblTotalAmount.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		lblTotal = new JLabel("");
		lblTotal.setBounds(134, 460, 115, 23);
		panel.add(lblTotal);
		lblTotal.setForeground(new Color(47, 79, 79));
		lblTotal.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		JLabel lblStaff = new JLabel("Staff");
		lblStaff.setForeground(new Color(47, 79, 79));
		lblStaff.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblStaff.setBounds(10, 180, 115, 19);
		panel.add(lblStaff);

		JLabel lblPaymentType = new JLabel("Payment Type");
		lblPaymentType.setForeground(new Color(47, 79, 79));
		lblPaymentType.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPaymentType.setBounds(10, 220, 115, 21);
		panel.add(lblPaymentType);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(376, 487, 89, 23);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.setCustomerid(txtCustomerID.getText().trim());
				if (cboStaff.getSelectedIndex() <= 0) {
					JOptionPane.showMessageDialog(null, "Please Choose a Staff", "Fail", JOptionPane.ERROR_MESSAGE);
				} else if (txtSaleorderid.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please Choose a SaleOrder ID", "Fail",
							JOptionPane.ERROR_MESSAGE);
				} else if (txtPayamount.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please Enter the amount to pay", "Fail",
							JOptionPane.ERROR_MESSAGE);
				} else if (((String) cboPaymentType.getSelectedItem()).equals("Full Payment")
						&& Integer.valueOf(txtPayamount.getText()) < Integer.valueOf(lblTotal.getText())) {
					JOptionPane.showMessageDialog(null,
							"The Pay Amount cannot be lower than total amount in Full Payment", "Fail",
							JOptionPane.ERROR_MESSAGE);
				} else if (JOptionPane.showConfirmDialog(null, "Are you sure that the payment will be made?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					StaffModel sm = new StaffModel();
					sm.setStaffname((String) cboStaff.getSelectedItem());

					int save = 0;
					PaymentModel pm = new PaymentModel();
					pm.setPaymentid(lblPaymentID.getText());
					pm.setSaleorderid(txtSaleorderid.getText());
					pm.setStaffid(sc.searchStaffId(sm));
					pm.setAmount(Integer.valueOf(txtPayamount.getText()));
					pm.setPaymenttype((String) cboPaymentType.getSelectedItem());
					pm.setRemark(txtRemark.getText());
					pm.setDate(lblDate.getText());

					save = pc.update(pm);

					if (save == 1) {
						JOptionPane.showMessageDialog(null, "All records are successfully saved!");
						AutoID();
						showSaleList();
						showPaymentList();
						Clear();
//								ClearAll();
					} else {
						JOptionPane.showMessageDialog(null, "All records are fail saved!");
					}
					btnSave.setEnabled(true);
					btnUpdate.setEnabled(false);
					btnDelete.setEnabled(false);
				}
			}
		});
		btnUpdate.setForeground(new Color(47, 79, 79));
		btnUpdate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnUpdate.setEnabled(true);
		btnUpdate.setBackground(new Color(102, 205, 170));
		btnUpdate.setEnabled(false);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaymentModel pm = new PaymentModel();
				pm.setPaymentid(lblPaymentID.getText());
				pc.delete(pm);

				showSaleList();
				showPaymentList();
				btnSave.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				Clear();
			}
		});
		btnDelete.setBounds(492, 487, 89, 23);
		panel.add(btnDelete);

		btnDelete.setForeground(new Color(47, 79, 79));
		btnDelete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnDelete.setEnabled(true);
		btnDelete.setBackground(new Color(102, 205, 170));
		btnDelete.setEnabled(false);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(601, 487, 89, 23);
		panel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				btnDeleteSale.setEnabled(false);
				showSaleList();
				showPaymentList();
				Clear();
			}
		});
		btnClear.setForeground(new Color(47, 79, 79));
		btnClear.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnClear.setBackground(new Color(102, 205, 170));

		JLabel lblSaleId = new JLabel("SaleOrder ID");
		lblSaleId.setForeground(new Color(47, 79, 79));
		lblSaleId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblSaleId.setBounds(10, 260, 115, 19);
		panel.add(lblSaleId);

		txtSaleorderid = new JTextField();
		txtSaleorderid.setText("");
		txtSaleorderid.setForeground(new Color(47, 79, 79));
		txtSaleorderid.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSaleorderid.setEnabled(false);
		txtSaleorderid.setColumns(10);
		txtSaleorderid.setBounds(132, 260, 130, 25);
		panel.add(txtSaleorderid);

		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setForeground(new Color(47, 79, 79));
		lblRemark.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblRemark.setBounds(10, 300, 115, 19);
		panel.add(lblRemark);

		txtRemark = new JTextField();
		txtRemark.setText("");
		txtRemark.setForeground(new Color(47, 79, 79));
		txtRemark.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtRemark.setEnabled(false);
		txtRemark.setColumns(10);
		txtRemark.setBounds(132, 300, 130, 25);
		panel.add(txtRemark);

		JLabel lblCurrentPayment = new JLabel("Pay Amount");
		lblCurrentPayment.setForeground(new Color(47, 79, 79));
		lblCurrentPayment.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblCurrentPayment.setBounds(10, 340, 115, 19);
		panel.add(lblCurrentPayment);

		txtPayamount = new JTextField();
		txtPayamount.setText("");
		txtPayamount.setForeground(new Color(47, 79, 79));
		txtPayamount.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPayamount.setEnabled(false);
		txtPayamount.setColumns(10);
		txtPayamount.setBounds(132, 340, 130, 25);
		panel.add(txtPayamount);

		btnSave = new JButton("Save");
		btnSave.setBounds(266, 487, 89, 23);
		panel.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					cm.setCustomerid(txtCustomerID.getText().trim());
					if (cboStaff.getSelectedIndex() <= 0) {
						JOptionPane.showMessageDialog(null, "Please Choose a Staff", "Fail", JOptionPane.ERROR_MESSAGE);
					} else if (txtSaleorderid.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "Please Choose a SaleOrder ID", "Fail",
								JOptionPane.ERROR_MESSAGE);
					} else if (txtPayamount.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "Please Enter the amount to pay", "Fail",
								JOptionPane.ERROR_MESSAGE);
					} else if (((String) cboPaymentType.getSelectedItem()).equals("Full Payment")
							&& Integer.valueOf(txtPayamount.getText()) < Integer.valueOf(lblTotal.getText())) {
						JOptionPane.showMessageDialog(null,
								"The Pay Amount cannot be lower than total amount in Full Payment", "Fail",
								JOptionPane.ERROR_MESSAGE);
					} else if (JOptionPane.showConfirmDialog(null, "Are you sure that the payment will be updated?",
							"Confirm", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						StaffModel sm = new StaffModel();
						sm.setStaffname((String) cboStaff.getSelectedItem());

						int save = 0;
						PaymentModel pm = new PaymentModel();
						pm.setPaymentid(lblPaymentID.getText());
						pm.setSaleorderid(txtSaleorderid.getText());
						pm.setStaffid(sc.searchStaffId(sm));
						pm.setAmount(Integer.valueOf(txtPayamount.getText()));
						pm.setPaymenttype((String) cboPaymentType.getSelectedItem());
						pm.setRemark(txtRemark.getText());
						pm.setDate(lblDate.getText());

						save = pc.insert(pm);

						if (save == 1) {
							JOptionPane.showMessageDialog(null, "All records are successfully saved!");
							AutoID();
							showSaleList();
							showPaymentList();
							Clear();
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

		btnSave.setEnabled(true);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(712, 44, 608, 260);

		contentPane.add(scrollPane_2);
		tblSale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				irow = tblSale.getSelectedRow();

				cboPaymentType.setSelectedItem((String) tblSale.getValueAt(irow, 2));
				txtSaleorderid.setText((String) tblSale.getValueAt(irow, 0));
				String soi = (String) tblSale.getValueAt(irow, 0);
				lblTotal.setText((String) tblSale.getValueAt(irow, 3));
				txtCustomerID.setText((String) tblSale.getValueAt(irow, 5));
				txtCustomerName.setText((String) tblSale.getValueAt(irow, 6));
				txtPhone.setText((String) tblSale.getValueAt(irow, 7));
				txtAddress.setText((String) tblSale.getValueAt(irow, 8));
				txtEmail.setText((String) tblSale.getValueAt(irow, 9));

				btnDeleteSale.setEnabled(true);

				int p = paymentcount(soi);
				if (cboPaymentType.getSelectedItem().toString().equals("Installment")) {
					if (p == 0) {
						lblPaymentIndicator.setText("First Payment");
						txtRemark.setText("First Payment");
						int fourty = (Integer.valueOf(lblTotal.getText()) * 40 / 100);
						txtPayamount.setText(fourty + "");
						txtPayamount.setEnabled(false);
					} else if (p == 1) {
						lblPaymentIndicator.setText("Second Payment");
						txtRemark.setText("Second Payment");
						int fourty = (Integer.valueOf(lblTotal.getText()) * 20 / 100);
						txtPayamount.setText(fourty + "");
						txtPayamount.setEnabled(false);

					} else if (p == 2) {
						lblPaymentIndicator.setText("Third Payment");
						txtRemark.setText("Thrid Payment");
						int fourty = (Integer.valueOf(lblTotal.getText()) * 20 / 100);
						txtPayamount.setText(fourty + "");
						txtPayamount.setEnabled(false);

					} else if (p > 2) {
						lblPaymentIndicator.setText("Payment Completed");
						btnSave.setEnabled(false);
						txtPayamount.setEnabled(false);
					}
				} else if (cboPaymentType.getSelectedItem().toString().equals("Full Payment") && p >= 1) {
					lblPaymentIndicator.setText("Payment Completed");
					btnSave.setEnabled(false);
					txtPayamount.setEnabled(false);
					txtRemark.setText("");
					txtPayamount.setText("");
				}else {
					btnSave.setEnabled(true);
					txtPayamount.setEnabled(true);
					txtRemark.setText("");
					txtPayamount.setText("");
				}
			}
		});

		tblSale.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_2.setViewportView(tblSale);

		tblPayment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tblPayment.getSelectedRow();

				lblPaymentID.setText((String) tblPayment.getValueAt(row, 0));
				String saleid = (String) tblPayment.getValueAt(row, 1);
				txtSaleorderid.setText(saleid);
				showSaleListOne(txtSaleorderid.getText());

				txtRemark.setText((String) tblPayment.getValueAt(row, 5));
				txtPayamount.setText((String) tblPayment.getValueAt(row, 3));

				StaffModel sm = new StaffModel();
				sm.setStaffid((String) tblPayment.getValueAt(row, 2));
				cboStaff.setSelectedItem(sc.searchStaffName(sm));

				lblTotal.setText((String) tblSale.getValueAt(0, 3));
				txtCustomerID.setText((String) tblSale.getValueAt(0, 5));
				txtCustomerName.setText((String) tblSale.getValueAt(0, 6));
				txtPhone.setText((String) tblSale.getValueAt(0, 7));
				txtAddress.setText((String) tblSale.getValueAt(0, 8));
				txtEmail.setText((String) tblSale.getValueAt(0, 9));

				btnSave.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
			}
		});

		lblPaymentID.setForeground(new Color(47, 79, 79));
		lblPaymentID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPaymentID.setBounds(132, 380, 102, 19);
		panel.add(lblPaymentID);

		txtSearchbysaleid = new JTextField();
		txtSearchbysaleid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				showSaleListOne(txtSearchbysaleid.getText());

			}
		});
		txtSearchbysaleid.setText("");
		txtSearchbysaleid.setForeground(new Color(47, 79, 79));
		txtSearchbysaleid.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearchbysaleid.setColumns(10);
		txtSearchbysaleid.setBounds(839, 12, 130, 25);
		contentPane.add(txtSearchbysaleid);

		JLabel lblSearchByItem = new JLabel("Search by Sale ID");
		lblSearchByItem.setForeground(new Color(47, 79, 79));
		lblSearchByItem.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblSearchByItem.setBounds(712, 11, 115, 23);
		contentPane.add(lblSearchByItem);

		txtSearchbypaymentid = new JTextField();
		txtSearchbypaymentid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				showPaymentListbyid(txtSearchbypaymentid.getText());

			}
		});

		txtSearchbypaymentid.setText("");
		txtSearchbypaymentid.setForeground(new Color(47, 79, 79));
		txtSearchbypaymentid.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearchbypaymentid.setColumns(10);
		txtSearchbypaymentid.setBounds(863, 316, 130, 25);
		contentPane.add(txtSearchbypaymentid);

		JLabel lblSearchByItem_1 = new JLabel("Search by PaymentID");
		lblSearchByItem_1.setForeground(new Color(47, 79, 79));
		lblSearchByItem_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblSearchByItem_1.setBounds(712, 318, 139, 23);
		contentPane.add(lblSearchByItem_1);
		try {
			fillStaffCombo();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createTable();
		createSaleTable();
		showPaymentList();
		showSaleList();
		lock();
		btnDeleteSale.setEnabled(false);

		AutoID();
//		Clear();
//		showList();
		date d = new date();
		lblDate.setText(d.getMySQLDateFormat());

		txtCustomerID.setDisabledTextColor(txtcolor);
		txtCustomerName.setDisabledTextColor(txtcolor);
		txtPhone.setDisabledTextColor(txtcolor);
		txtAddress.setDisabledTextColor(txtcolor);
		txtEmail.setDisabledTextColor(txtcolor);
		txtSaleorderid.setDisabledTextColor(txtcolor);
		txtRemark.setDisabledTextColor(txtcolor);
		txtPayamount.setDisabledTextColor(txtcolor);

		JButton btnSaleView = new JButton("Sale View");
		btnSaleView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleView sv = new SaleView();
				sv.setVisible(true);
			}
		});
		btnSaleView.setForeground(new Color(47, 79, 79));
		btnSaleView.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnSaleView.setBackground(new Color(102, 205, 170));
		btnSaleView.setBounds(10, 577, 180, 23);
		panel.add(btnSaleView);

		lblPaymentIndicator.setForeground(new Color(47, 79, 79));
		lblPaymentIndicator.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPaymentIndicator.setBounds(274, 340, 226, 25);
		panel.add(lblPaymentIndicator);

		btnDeleteSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure that this Sale will be Deleted?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (JOptionPane.showConfirmDialog(null,
							"All the payment information will be deleted as well, are you sure?", "Confirm",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						SaleOrderController soc = new SaleOrderController();
						try {
							int rs = soc.deletesale(txtSaleorderid.getText());
							if (rs == 1) {
								JOptionPane.showMessageDialog(null,
										"Deleted " + txtSaleorderid.getText() + " Successfully");
							} else {
								JOptionPane.showMessageDialog(null, "Deleted Failed");
							}
							showSaleList();
							showPaymentList();
							Clear();
							btnDeleteSale.setEnabled(false);
							btnSave.setEnabled(true);
							btnUpdate.setEnabled(false);
							btnDelete.setEnabled(false);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}
		});
		btnDeleteSale.setForeground(new Color(47, 79, 79));
		btnDeleteSale.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnDeleteSale.setBackground(new Color(102, 205, 170));
		btnDeleteSale.setBounds(1169, 11, 151, 23);
		contentPane.add(btnDeleteSale);

		JLabel lblSearchByItem_1_1 = new JLabel("Search by SaleID");
		lblSearchByItem_1_1.setForeground(new Color(47, 79, 79));
		lblSearchByItem_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblSearchByItem_1_1.setBounds(1005, 318, 139, 23);
		contentPane.add(lblSearchByItem_1_1);

		txtsearchbysaleID = new JTextField();
		txtsearchbysaleID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				showPaymentListbysaleid(txtsearchbysaleID.getText());
			}
		});
		txtsearchbysaleID.setText("");
		txtsearchbysaleID.setForeground(new Color(47, 79, 79));
		txtsearchbysaleID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtsearchbysaleID.setColumns(10);
		txtsearchbysaleID.setBounds(1124, 316, 130, 25);
		contentPane.add(txtsearchbysaleID);
	}

	public void fillStaffCombo() throws ClassNotFoundException {
		DeptModel dm = new DeptModel();
		DeptController dc = new DeptController();
		dm.setDeptname("account");
		SQLQueries.addStaffComboBox("staff", "staffname", cboStaff, dc.searchDeptID(dm));

	}

	public void setColoumnWidth(int index, int width) {

		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblPayment.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}

	public void setItemColoumnWidth(int index, int width) {

		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblSale.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}

	public void AutoID() {
		ClsDBConnection dbcon = new ClsDBConnection();
		try {
			lblPaymentID.setText(dbcon.AutoID("paymentid", "saleandservice.payment", "PA-"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createTable() {
		dtm.addColumn("PaymentID");
		dtm.addColumn("SaleOrderID");
		dtm.addColumn("StaffID");
		dtm.addColumn("Paid Amount");
		dtm.addColumn("Payment Type");
		dtm.addColumn("Remark");
		dtm.addColumn("Paid Date");
		tblPayment.setModel(dtm);

		setColoumnWidth(0, 100);
		setColoumnWidth(1, 100);
		setColoumnWidth(2, 100);
		setColoumnWidth(3, 100);
		setColoumnWidth(4, 150);
		setColoumnWidth(5, 300);
		setColoumnWidth(6, 150);

	}

	public void createSaleTable() {
		idtm.addColumn("SaleOrderID");
		idtm.addColumn("StaffID");
		idtm.addColumn("Payment Type");
		idtm.addColumn("Amount");
		idtm.addColumn("Date");
		idtm.addColumn("CustomerID");
		idtm.addColumn("Customer Name");
		idtm.addColumn("Phone");
		idtm.addColumn("Address");
		idtm.addColumn("Email");
		tblSale.setModel(idtm);

		setItemColoumnWidth(0, 100);
		setItemColoumnWidth(1, 100);
		setItemColoumnWidth(2, 100);
		setItemColoumnWidth(3, 100);
		setItemColoumnWidth(4, 100);
		setItemColoumnWidth(5, 100);
		setItemColoumnWidth(6, 100);
		setItemColoumnWidth(7, 100);
		setItemColoumnWidth(8, 100);
	}

	public void showPaymentList() {
		String data[] = new String[8];
		try {
			ArrayList<PaymentModel> list = pc.selectall();
			dtm.setRowCount(0);
			for (PaymentModel pm : list) {

				data[0] = pm.getPaymentid();
				data[1] = pm.getSaleorderid();
				data[2] = pm.getStaffid();
				data[3] = pm.getAmount() + "";
				data[4] = pm.getPaymenttype();
				data[5] = pm.getRemark();
				data[6] = pm.getDate();

				dtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showPaymentListbyid(String name) {
		String data[] = new String[8];
		try {
			ArrayList<PaymentModel> list = pc.selectbyid(name);
			dtm.setRowCount(0);
			for (PaymentModel pm : list) {

				data[0] = pm.getPaymentid();
				data[1] = pm.getSaleorderid();
				data[2] = pm.getStaffid();
				data[3] = pm.getAmount() + "";
				data[4] = pm.getPaymenttype();
				data[5] = pm.getRemark();
				data[6] = pm.getDate();

				dtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showPaymentListbysaleid(String name) {
		String data[] = new String[8];
		try {
			ArrayList<PaymentModel> list = pc.selectbysaleid(name);
			dtm.setRowCount(0);
			for (PaymentModel pm : list) {

				data[0] = pm.getPaymentid();
				data[1] = pm.getSaleorderid();
				data[2] = pm.getStaffid();
				data[3] = pm.getAmount() + "";
				data[4] = pm.getPaymenttype();
				data[5] = pm.getRemark();
				data[6] = pm.getDate();

				dtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	showSaleList();
//	showPaymentList();
	public void showSaleList() {
		String data[] = new String[10];
		try {
			ArrayList<SaleModel> list = soc.showAllSale();
			idtm.setRowCount(0);
			for (SaleModel pm : list) {

				data[0] = pm.getSaleorderid();
				data[1] = pm.getStaffid();
				data[2] = pm.getPaymenttype();
				data[3] = pm.getAmount() + "";
				data[4] = pm.getDate();
				data[5] = pm.getCustomerid();
				data[6] = pm.getCustomername();
				data[7] = pm.getPhone();
				data[8] = pm.getAddress();
				data[9] = pm.getEmail();

				idtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showSaleListOne(String name) {
		String data[] = new String[10];
		try {
			ArrayList<SaleModel> list = soc.searchbyid(name);
			idtm.setRowCount(0);
			for (SaleModel pm : list) {

				data[0] = pm.getSaleorderid();
				data[1] = pm.getStaffid();
				data[2] = pm.getPaymenttype();
				data[3] = pm.getAmount() + "";
				data[4] = pm.getDate();
				data[5] = pm.getCustomerid();
				data[6] = pm.getCustomername();
				data[7] = pm.getPhone();
				data[8] = pm.getAddress();
				data[9] = pm.getEmail();

				idtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int paymentcount(String name) {
		int i = 0;
		try {
			i = pc.countbysaleid(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	public void lock() {
		txtCustomerID.setEnabled(false);
		txtCustomerName.setEnabled(false);
		txtPhone.setEnabled(false);
		txtAddress.setEnabled(false);
		txtEmail.setEnabled(false);
		cboPaymentType.setEnabled(false);
		txtSaleorderid.setEnabled(false);
		txtRemark.setEnabled(true);
		txtPayamount.setEnabled(true);

	}

	public void Clear() {
		txtCustomerID.setText("");
		txtCustomerName.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
		txtEmail.setText("");
		cboStaff.setSelectedIndex(0);
		txtSaleorderid.setText("");
		txtRemark.setText("");
		txtPayamount.setText("");
		lblTotal.setText("");

		AutoID();
		lblPaymentIndicator.setText("");
		txtPayamount.setEnabled(true);
	}
}
