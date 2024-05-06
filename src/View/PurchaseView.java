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
import Controller.ItemController;
import Controller.PurchaseController;
import Controller.PurchaseDetailController;
import Controller.TypeController;
import Model.BrandModel;
import Model.CompanyModel;
import Model.ItemModel;
import Model.PurchaseDetailModel;
import Model.PurchaseModel;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class PurchaseView extends JFrame {

	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String BrandID = null;
	int r;
	String selectedSerial;

	private JTable tblPurchase;
	private JScrollPane scrollPane_1;
	private JLabel lblN;
	private JTextField txtCompanyID;
	private JButton btnAdd;
	private JMenuItem mntmNewMenuItem_3;
	// String brandName;
	private JPanel panel;
	private JComboBox comboCompany;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JLabel lblPurchaseID;
	private JPanel panel_1;
	private JTextField txtAddress;
	private JTextField txtQty;
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
	private JButton btnPurchaseDetail;
	private JLabel lblPurchasingEntry;
	JRadioButton rdoSin = new JRadioButton("Single");
	JRadioButton rdoMul = new JRadioButton("Multiple");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseView frame = new PurchaseView();
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
	public PurchaseView() throws SQLException {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				System.out.println("hello");
				try {
					fillCombo();
					SQLQueries.addComboBox("company", "companyname", comboCompany);

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void windowLostFocus(WindowEvent e) {
			}
		});

		setTitle("Purchase");
		setResizable(false);
		Color textcolor = new Color(47, 79, 79);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ButtonGroup group = new ButtonGroup();
		setBounds(0, 0, 779, 692);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 139));
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 490, 763, 121);
		contentPane.add(scrollPane_1);

		tblPurchase = new JTable();
		tblPurchase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				r = tblPurchase.getSelectedRow();

				String collection = (String) tblPurchase.getValueAt(r, 1);
				String[] parts = collection.split("/");
				String itemName = parts[0];
				String brand = parts[1];
				String type = parts[2];

				selectedSerial = (String) tblPurchase.getValueAt(r, 2);
				txtItemName.setText(itemName);
				cboBrandName.setSelectedItem(brand);
				cboTypeName.setSelectedItem(type);
				txtSerial.setText((String) tblPurchase.getValueAt(r, 2));
				txtWarranty.setText((String) tblPurchase.getValueAt(r, 3));
//				comboItem.setSelectedItem(itemName);
				txtPrice.setText(tblPurchase.getValueAt(r, 4).toString());
				String Qty = tblPurchase.getValueAt(r, 5).toString();
				txtQty.setText(Qty);
				if (Qty.equals("1")) {
					group.setSelected(rdoSin.getModel(), true);
					txtQty.setEnabled(false);
					txtSerial.setEnabled(true);
				}

				btnAdd.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
			}
		});

		tblPurchase.setBackground(new Color(192, 192, 192));
		tblPurchase.setForeground(new Color(47, 79, 79));
		tblPurchase.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		scrollPane_1.setViewportView(tblPurchase);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Company", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(175, 238, 238));
		panel.setBounds(0, 43, 763, 158);
		contentPane.add(panel);
		panel.setLayout(null);

		lblN = new JLabel("Company Name");
		lblN.setBounds(10, 22, 115, 19);
		panel.add(lblN);
		lblN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblN.setForeground(new Color(47, 79, 79));

		comboCompany = new JComboBox();
		comboCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboCompany.getSelectedIndex() > 0) {
					CompanyModel sm = new CompanyModel();
					CompanyController sc = new CompanyController();
					ArrayList<CompanyModel> list = new ArrayList<CompanyModel>();
					sm.setCompanyname(comboCompany.getSelectedItem().toString());
					list = (ArrayList<CompanyModel>) sc.searchcompanyDetail(sm);
					for (CompanyModel s : list) {
						txtCompanyID.setText(s.getCompanyid());
						txtAddress.setText(s.getAddress());
						txtEmail.setText(s.getEmail());
						txtPhone.setText(s.getPhone());
					}

				}
			}
		});
		comboCompany.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		comboCompany.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));
		comboCompany.setBounds(140, 22, 130, 25);
		panel.add(comboCompany);

		JLabel lblCompanyId = new JLabel("Company ID");
		lblCompanyId.setForeground(new Color(47, 79, 79));
		lblCompanyId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblCompanyId.setBounds(10, 58, 115, 19);
		panel.add(lblCompanyId);

		txtCompanyID = new JTextField();
		txtCompanyID.setBounds(140, 56, 130, 25);
		panel.add(txtCompanyID);
		txtCompanyID.setForeground(new Color(47, 79, 79));
		txtCompanyID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtCompanyID.setColumns(10);
		txtCompanyID.setEnabled(false);
		txtCompanyID.setDisabledTextColor(textcolor);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(new Color(47, 79, 79));
		lblAddress.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblAddress.setBounds(10, 97, 115, 19);
		panel.add(lblAddress);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(47, 79, 79));
		lblEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblEmail.setBounds(10, 130, 115, 19);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setText("");
		txtEmail.setForeground(new Color(47, 79, 79));
		txtEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(140, 126, 130, 25);
		panel.add(txtEmail);
		txtEmail.setEnabled(false);
		txtEmail.setDisabledTextColor(textcolor);

		JLabel lblPhone = new JLabel("Phone No");
		lblPhone.setForeground(new Color(47, 79, 79));
		lblPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPhone.setBounds(417, 97, 115, 19);
		panel.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setText("");
		txtPhone.setForeground(new Color(47, 79, 79));
		txtPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPhone.setColumns(10);
		txtPhone.setBounds(557, 95, 130, 25);
		panel.add(txtPhone);
		txtPhone.setEnabled(false);
		txtPhone.setDisabledTextColor(textcolor);

		JLabel date = new JLabel("Date");
		date.setForeground(new Color(47, 79, 79));
		date.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		date.setBounds(417, 58, 115, 19);
		panel.add(date);

		JLabel lblDate = new JLabel("");
		lblDate.setForeground(new Color(47, 79, 79));
		lblDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblDate.setBounds(557, 58, 130, 19);
		panel.add(lblDate);

		JLabel lb = new JLabel("Purchase ID");
		lb.setForeground(new Color(47, 79, 79));
		lb.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lb.setBounds(417, 22, 115, 19);
		panel.add(lb);

		lblPurchaseID = new JLabel("");
		lblPurchaseID.setForeground(new Color(47, 79, 79));
		lblPurchaseID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPurchaseID.setBounds(557, 22, 115, 19);
		panel.add(lblPurchaseID);

		txtAddress = new JTextField();
		txtAddress.setText("");
		txtAddress.setForeground(new Color(47, 79, 79));
		txtAddress.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtAddress.setColumns(10);
		txtAddress.setBounds(140, 89, 130, 25);
		panel.add(txtAddress);
		txtAddress.setEnabled(false);
		txtAddress.setDisabledTextColor(textcolor);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(175, 238, 238));
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Item",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(0, 212, 763, 268);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setForeground(new Color(47, 79, 79));
		lblItemName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblItemName.setBounds(10, 23, 115, 19);
		panel_1.add(lblItemName);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(47, 79, 79));
		lblPrice.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblPrice.setBounds(421, 23, 115, 19);
		panel_1.add(lblPrice);

		txtQty = new JTextField();
		txtQty.setText("");
		txtQty.setForeground(new Color(47, 79, 79));
		txtQty.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtQty.setColumns(10);
		txtQty.setBounds(557, 57, 130, 25);
		panel_1.add(txtQty);
		txtQty.setDisabledTextColor(textcolor);

		txtPrice = new JTextField();
		txtPrice.setText("");
		txtPrice.setForeground(new Color(47, 79, 79));
		txtPrice.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(557, 20, 130, 25);
		panel_1.add(txtPrice);
		txtPrice.setDisabledTextColor(textcolor);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(new Color(47, 79, 79));
		lblQuantity.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblQuantity.setBounds(421, 59, 115, 19);
		panel_1.add(lblQuantity);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtItemName.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "You must choose Item Name!");
					txtItemName.requestFocus();
				} else if (txtSerial.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Serial Cannot be blank, if there's no serial use -");
					txtItemName.requestFocus();
				} else if (cboBrandName.getSelectedIndex() <= 0 || cboTypeName.getSelectedIndex() <= 0) {
					JOptionPane.showMessageDialog(null, "You must choose Brand and Type");
					txtItemName.requestFocus();
					txtQty.selectAll();
				} else if (!Checking.checktxtprice(txtPrice.getText())) {
//					JOptionPane.showMessageDialog(null, "You must enter less than 1000000000");
					txtItemName.requestFocus();
					txtPrice.selectAll();
				} else if (!Checking.checktxtquantity(txtQty.getText())) {
//					JOptionPane.showMessageDialog(null, "You must enter less than 10000!");
					txtItemName.requestFocus();
					txtQty.selectAll();
				} else if (ic.serialExist(txtSerial.getText()) && !txtSerial.getText().equals("-")) {
					JOptionPane.showMessageDialog(null, "The serial already existed in database");
					txtItemName.requestFocus();
					Clear();
				} else if (Checking.IsContain(txtSerial.getText(), vserial) && !txtSerial.getText().equals("-")) {
					JOptionPane.showMessageDialog(null, "The serial or item you selected is already existed!");
					txtItemName.requestFocus();
				} else if (txtRemark.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Remark Cannot be blank, if there's no remark use -");
					txtItemName.requestFocus();
				} else {
					itemsetmethod();
					itemaddmethod();
//					lblTotal.setText(Checking.Sumamount(vamount, 1) + "Kyats");
					lblTotal.setText(Checking.Sumamount(vamount, 1));
					Clear();
				}
			}
		});
		btnAdd.setBounds(409, 211, 89, 23);
		panel_1.add(btnAdd);

		btnAdd.setBackground(new Color(102, 205, 170));
		btnAdd.setForeground(new Color(47, 79, 79));
		btnAdd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtItemName.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "You must choose Item Name!");
					txtItemName.requestFocus();
				} else if (txtSerial.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Serial Cannot be blank, if there's no serial use -");
					txtItemName.requestFocus();
				} else if (cboBrandName.getSelectedIndex() <= 0 || cboTypeName.getSelectedIndex() <= 0) {
					JOptionPane.showMessageDialog(null, "You must choose Brand and Type");
					txtItemName.requestFocus();
					txtQty.selectAll();
				} else if (!Checking.checktxtprice(txtPrice.getText())) {
//					JOptionPane.showMessageDialog(null, "You must enter less than 1000000000");
					txtItemName.requestFocus();
					txtPrice.selectAll();
				} else if (!Checking.checktxtquantity(txtQty.getText())) {
//					JOptionPane.showMessageDialog(null, "You must enter less than 10000!");
					txtItemName.requestFocus();
					txtQty.selectAll();
				} else if (ic.serialExist(txtSerial.getText()) && !txtSerial.getText().equals("-")) {
					JOptionPane.showMessageDialog(null, "The serial already existed in database");
					txtItemName.requestFocus();
					Clear();
				} else if (Checking.IsContain(txtSerial.getText(), vserial) && !txtSerial.getText().equals("-")
						&& !txtSerial.getText().equals(selectedSerial)) {
					JOptionPane.showMessageDialog(null, "The serial or item you selected is already existed!");
					txtItemName.requestFocus();
				} else {
					deleteRow();
					itemsetmethod();
					itemaddmethod();
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
		btnUpdate.setBounds(538, 211, 89, 23);
		panel_1.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblPurchase.getSelectedRow() < 0) {
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
		btnDelete.setBounds(662, 211, 89, 23);
		panel_1.add(btnDelete);

		txtItemName = new JTextField();
		txtItemName.setText("");
		txtItemName.setForeground(new Color(47, 79, 79));
		txtItemName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtItemName.setColumns(10);
		txtItemName.setBounds(135, 20, 130, 25);
		panel_1.add(txtItemName);

		JLabel lblNewLabel_3_1 = new JLabel("Brand Name");
		lblNewLabel_3_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(10, 64, 87, 14);
		panel_1.add(lblNewLabel_3_1);
		cboBrandName.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));

		cboBrandName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		cboBrandName.setBounds(135, 60, 130, 25);
		panel_1.add(cboBrandName);
		cboTypeName.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));

		cboTypeName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		cboTypeName.setBounds(135, 96, 130, 25);
		panel_1.add(cboTypeName);

		JLabel lblNewLabel_3_2 = new JLabel("Type Name");
		lblNewLabel_3_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(10, 96, 87, 19);
		panel_1.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_3 = new JLabel("Warranty");
		lblNewLabel_3_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_3.setBounds(10, 137, 87, 19);
		panel_1.add(lblNewLabel_3_3);

		JLabel lblNewLabel_3_4 = new JLabel("Serial No");
		lblNewLabel_3_4.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_4.setBounds(421, 97, 87, 14);
		panel_1.add(lblNewLabel_3_4);

		txtWarranty = new JTextField();
		txtWarranty.setText("");
		txtWarranty.setForeground(new Color(47, 79, 79));
		txtWarranty.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtWarranty.setColumns(10);
		txtWarranty.setBounds(135, 135, 130, 25);
		panel_1.add(txtWarranty);

		txtSerial = new JTextField();
		txtSerial.setText("");
		txtSerial.setForeground(new Color(47, 79, 79));
		txtSerial.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSerial.setColumns(10);
		txtSerial.setBounds(557, 95, 130, 25);
		panel_1.add(txtSerial);

		rdoSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtQty.setEnabled(false);
				txtSerial.setEnabled(true);
				txtQty.setText("1");
				single = true;
			}
		});
		rdoSin.setBounds(10, 213, 65, 21);
		panel_1.add(rdoSin);

		rdoMul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtQty.setEnabled(true);
				txtSerial.setEnabled(false);
				txtSerial.setText("-");
				single = false;
			}
		});
		rdoMul.setBounds(85, 213, 80, 21);
		panel_1.add(rdoMul);

		group.add(rdoMul);
		group.add(rdoSin);
		rdoSin.setSelected(true);
		txtQty.setEnabled(false);
		txtQty.setText("1");
		txtSerial.setText("-");

		JLabel lblNewLabel_3_4_1 = new JLabel("Remark");
		lblNewLabel_3_4_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_4_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_4_1.setBounds(421, 142, 87, 14);
		panel_1.add(lblNewLabel_3_4_1);

		txtRemark = new JTextField();
		txtRemark.setText("");
		txtRemark.setForeground(new Color(47, 79, 79));
		txtRemark.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtRemark.setColumns(10);
		txtRemark.setBounds(557, 137, 130, 25);
		panel_1.add(txtRemark);

		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setForeground(new Color(47, 79, 79));
		lblTotalAmount.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblTotalAmount.setBounds(421, 622, 115, 19);
		contentPane.add(lblTotalAmount);

		lblTotal = new JLabel("");
		lblTotal.setForeground(new Color(47, 79, 79));
		lblTotal.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblTotal.setBounds(538, 622, 115, 19);
		contentPane.add(lblTotal);

		btnSave = new JButton("Purchase");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboCompany.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "You must select a Company ID!");
					comboCompany.requestFocus();
				} else if (vserial.size() == 0) {
					JOptionPane.showMessageDialog(null, "There is no item to Purchase!");
					comboCompany.requestFocus();
				} else {
					if (JOptionPane.showConfirmDialog(null, "Are you sure do you want to Purchase?", "Confirm",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						int save = 0;
						PurchaseController pc = new PurchaseController();
						PurchaseModel pm = new PurchaseModel();
						pm.setPurchaseid(lblPurchaseID.getText().toString());
						pm.setCompanyid(txtCompanyID.getText().toString());
						pm.setPurchasedate(lblDate.getText().toString());
						save = pc.insert(pm);
						if (save == 1) {
							for (int i = 0; i < vid.size(); i++) {
								String purchase = (String) tblPurchase.getValueAt(i, 1);
								String[] part = purchase.split("/");

								PurchaseDetailController pdc = new PurchaseDetailController();
								PurchaseDetailModel pdm = new PurchaseDetailModel();
//								pdm.setPurchaseid(lblPurchaseID.getText().toString());
//								pdm.setItemName(part[0]);
//								pdm.setPurchaseqty(Integer.parseInt((String) tblPurchase.getValueAt(i, 5)));
//								pdm.setPurchaseprice(Integer.parseInt((String) tblPurchase.getValueAt(i, 4)));
//								save = pdc.insert(pdm);

								for (int k = 0; k < Integer.parseInt((String) tblPurchase.getValueAt(i, 5)); k++) {
									pdm.setPurchaseid(lblPurchaseID.getText().toString());
									pdm.setItemName(part[0]);
									pdm.setPurchaseqty(Integer.parseInt((String) tblPurchase.getValueAt(i, 5)));
									pdm.setPurchaseprice(Integer.parseInt((String) tblPurchase.getValueAt(i, 4)));

									ItemModel im = new ItemModel();
									ItemController ic = new ItemController();
									BrandModel bm = new BrandModel();
									TypeModel tm = new TypeModel();

									bm.setBrandname(part[1]);
									tm.setTypename(part[2]);

									im.setItemname(part[0]);
									im.setBrandid(bc.searchBrandId(bm));
									im.setTypeid(tc.searchTypeID(tm));
									im.setCurrentprice(Integer.parseInt((String) tblPurchase.getValueAt(i, 4)));
									im.setWarranty((String) tblPurchase.getValueAt(i, 3));
									im.setRemark(part[3]);
									im.setSerial((String) tblPurchase.getValueAt(i, 2));

									ic.insert(im);
									try {
										pdm.setItemid(ic.selectLast());
//										System.out.println("last item id "+ic.selectLast());
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									save = pdc.insert(pdm);
								}

//								im.setItemid((String) tblPurchase.getValueAt(i, 1));
//								try {
//									String data[] = SQLQueries.getItemData1(im);
//									String nowqty = data[5];
//									int totalqty = Integer.parseInt(nowqty) + pdm.getPurchaseqty();
//									int price = 0;
//									price = (int) (pdm.getPurchaseprice() + (pdm.getPurchaseprice() * 0.1));
//									im.setTotalqty(totalqty);
//									im.setCurrentprice(price);
//									int save2 = ic.update1(im);
//								} catch (SQLException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
							}
						}
						if (save == 1) {
							JOptionPane.showMessageDialog(null, "All records are successfully saved!");
							AutoID();
							clearAll();
							vamount.removeAllElements();
							vid.removeAllElements();
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
		btnSave.setBounds(293, 620, 100, 23);
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
		SQLQueries.addComboBox("company", "companyname", comboCompany);

		JButton btncompanyadd = new JButton("More");
		btncompanyadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CompanyPanel bp = new CompanyPanel();
					DataInputFrame dif = new DataInputFrame(bp);
					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btncompanyadd.setForeground(new Color(47, 79, 79));
		btncompanyadd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btncompanyadd.setBackground(new Color(102, 205, 170));
		btncompanyadd.setBounds(282, 22, 89, 23);
		panel.add(btncompanyadd);
		txtRemark.setText("-");

		btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);

		JButton btnbrandadd = new JButton("More");
		btnbrandadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BrandPanel bp = new BrandPanel();
					DataInputFrame dif = new DataInputFrame(bp);
					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnbrandadd.setForeground(new Color(47, 79, 79));
		btnbrandadd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnbrandadd.setBackground(new Color(102, 205, 170));
		btnbrandadd.setBounds(277, 61, 89, 23);
		panel_1.add(btnbrandadd);

		JButton btntypeadd = new JButton("More");
		btntypeadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TypePanel bp = new TypePanel();
					DataInputFrame dif = new DataInputFrame(bp);
					dif.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btntypeadd.setForeground(new Color(47, 79, 79));
		btntypeadd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btntypeadd.setBackground(new Color(102, 205, 170));
		btntypeadd.setBounds(277, 99, 89, 23);
		panel_1.add(btntypeadd);

		btnPurchaseDetail = new JButton("Purchase Detail");
		btnPurchaseDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PurchaseDetailView pdv;
				try {
					pdv = new PurchaseDetailView();
					pdv.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPurchaseDetail.setForeground(new Color(47, 79, 79));
		btnPurchaseDetail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnPurchaseDetail.setBackground(new Color(102, 205, 170));
		btnPurchaseDetail.setBounds(10, 622, 171, 23);
		contentPane.add(btnPurchaseDetail);

		lblPurchasingEntry = new JLabel("  Purchasing Entry  ");
		lblPurchasingEntry.setOpaque(true);
		lblPurchasingEntry.setForeground(new Color(224, 255, 255));
		lblPurchasingEntry.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblPurchasingEntry.setBackground(new Color(32, 178, 170));
		lblPurchasingEntry.setBounds(0, 0, 763, 44);
		contentPane.add(lblPurchasingEntry);
	}

	public void fillCombo() throws ClassNotFoundException {
		SQLQueries.addComboBox("brand", "brandname", cboBrandName);
		SQLQueries.addComboBox("type", "typename", cboTypeName);
	}

	public void setColoumnWidth(int index, int width) {

		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblPurchase.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}

	public void AutoID() {
		ClsDBConnection dbcon = new ClsDBConnection();
		try {
			lblPurchaseID.setText(dbcon.AutoID("purchaseid", "saleandservice.purchase", "PR-"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void itemsetmethod() {
		strdataitem[0] = String.valueOf(vserial.size() + 1);
		strdataitem[1] = txtItemName.getText().trim();
		strdataitem[2] = txtSerial.getText().trim();
		strdataitem[3] = txtWarranty.getText().trim();
		strdataitem[4] = txtPrice.getText().trim();
		strdataitem[5] = txtQty.getText().trim();
		strdataitem[7] = (String) cboBrandName.getSelectedItem();
		strdataitem[8] = (String) cboTypeName.getSelectedItem();
		strdataitem[9] = txtRemark.getText().trim().trim();

	}

	public void itemaddmethod() {
		strdataitem[0] = String.valueOf(vid.size() + 1);
		vid.addElement(strdataitem[1]);
		strdataitem[1] += "/" + strdataitem[7] + "/" + strdataitem[8] + "/" + strdataitem[9];
//		strdataitem[2] = txtSerial.getText();
//		strdataitem[5] = txtPrice.getText();
//		strdataitem[6] = txtQty.getText();
		vserial.addElement(strdataitem[2]);

		Long amount = Integer.parseInt(strdataitem[4]) * Long.parseLong(strdataitem[5]);
		strdataitem[6] = String.valueOf(amount);
		vamount.addElement(strdataitem[6]);
		dtm.addRow(strdataitem);
		tblPurchase.setModel(dtm);
//		comboItem.requestFocus();
	}

	public void createTable() {
		dtm.addColumn("No");
		dtm.addColumn("Item");
		dtm.addColumn("Serial");
		dtm.addColumn("Warranty");
		dtm.addColumn("Price");
		dtm.addColumn("Quantity");
		dtm.addColumn("Amount");
		tblPurchase.setModel(dtm);
		setColoumnWidth(0, 30);
		setColoumnWidth(1, 200);
		setColoumnWidth(2, 200);
		setColoumnWidth(3, 150);
		setColoumnWidth(4, 150);
		setColoumnWidth(5, 70);
		setColoumnWidth(6, 70);

	}

	public void deleteRow() {
		int i = tblPurchase.getSelectedRow();
		vamount.remove(i);
		if (!vid.lastElement().equals(vid.get(i))) {
			System.out.println("size " + vid.size());
			vserial.remove(i);
			vid.remove(i);
			dtm.removeRow(i);
			for (int j = 0; j < vid.size() - i; j++) {
				dtm.setValueAt(j + i + 1, i + j, 0);

			}
		} else {
			vid.remove(i);
			vserial.remove(i);
			dtm.removeRow(i);
		}
	}

	public void Clear() {
		txtItemName.setText("");
		txtWarranty.setText("");
		txtQty.setText("1");
		cboBrandName.setSelectedIndex(0);
		cboTypeName.setSelectedIndex(0);
		txtRemark.setText("-");
		txtSerial.setText("-");
		txtPrice.setText("");

	}

	public void clearAll() {
		comboCompany.setSelectedIndex(0);
		txtCompanyID.setText("");
		txtAddress.setText("");
		txtEmail.setText("");
		txtPhone.setText("");

		txtItemName.setText("");
		cboBrandName.setSelectedIndex(0);
		cboTypeName.setSelectedIndex(0);
		txtWarranty.setText("");
		txtPrice.setText("");
		txtQty.setText("1");
		txtSerial.setText("-");
		txtRemark.setText("-");

		dtm.setRowCount(0);
		lblTotal.setText("0Kyat");
	}
}
