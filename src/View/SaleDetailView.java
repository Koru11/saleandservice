package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Connection.date;
import Controller.SaleDetailController;
import Controller.SaleOrderDetailController;
import Controller.ServiceController;
import Model.CustomerModel;
import Model.SaleDetailModel;
import Model.SaleOrderDetailModel;
import Model.ServiceModel;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaleDetailView extends JFrame {

	private JPanel contentPane;
	private JTextField txtSNo;
	private JTextField txtItemID;
	private JTextField txtComName;
	private JTextField txtCompanyAddress;
	private JTextField txtCompanyPhone;
	private JTextField txtCompanyEmail;
	private JTextField txtItemName;
	private JTextField txtBrandName;
	private JTextField txtTypeName;
	private JTextField txtSerial;
	private JTextField txtCurPrice;
	private JTextField txtWarranty;
	private JTextField txtCompanyName;
	private JTextField txtPurchaseDate;
	private JTextField txtStaffID;
	private JTextField txtStaffName;
	private JTextField txtType;
	private JTextField txtSaleDate;
	private JTextField txtAmount;
	private JTextField txtCusName;
	private JTextField txtCusAddress;
	private JTextField txtCusPhone;
	private JTextField txtCusEmail;
	private JTextField txtRemark;
	private JButton btnVerifyIID;
	SaleDetailController sdc = new SaleDetailController();
	SaleDetailModel sdm = new SaleDetailModel();
	private JLabel lblPurchaseID;
	private JLabel lblItemID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleDetailView frame = new SaleDetailView();
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
	public SaleDetailView() {
		setResizable(false);
		setTitle("Sale Detail");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1180, 695);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Company",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(374, 41, 358, 263);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1_5_1 = new JLabel("Company Name");
		lblNewLabel_1_5_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_1.setBounds(10, 70, 170, 22);
		panel_2.add(lblNewLabel_1_5_1);

		JLabel lblNewLabel_1_5_2 = new JLabel("Address");
		lblNewLabel_1_5_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_2.setBounds(10, 127, 101, 22);
		panel_2.add(lblNewLabel_1_5_2);

		JLabel lblNewLabel_1_5_3 = new JLabel("Phone");
		lblNewLabel_1_5_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_3.setBounds(10, 170, 101, 22);
		panel_2.add(lblNewLabel_1_5_3);

		JLabel lblNewLabel_1_5_4 = new JLabel("Email");
		lblNewLabel_1_5_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_4.setBounds(10, 216, 101, 22);
		panel_2.add(lblNewLabel_1_5_4);

		JLabel lblNewLabel_1_6 = new JLabel("Company ID");
		lblNewLabel_1_6.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6.setBounds(10, 25, 139, 22);
		panel_2.add(lblNewLabel_1_6);

		JLabel lblCompanyID = new JLabel("");
		lblCompanyID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblCompanyID.setBounds(171, 25, 175, 22);
		panel_2.add(lblCompanyID);

		txtComName = new JTextField();
		txtComName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtComName.setColumns(10);
		txtComName.setBounds(171, 70, 175, 33);
		panel_2.add(txtComName);
		txtComName.setEnabled(false);
		txtComName.setDisabledTextColor(new Color(0, 0, 0));

		txtCompanyAddress = new JTextField();
		txtCompanyAddress.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtCompanyAddress.setColumns(10);
		txtCompanyAddress.setBounds(171, 122, 175, 33);
		panel_2.add(txtCompanyAddress);

		txtCompanyPhone = new JTextField();
		txtCompanyPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtCompanyPhone.setColumns(10);
		txtCompanyPhone.setBounds(171, 170, 175, 33);
		panel_2.add(txtCompanyPhone);

		txtCompanyEmail = new JTextField();
		txtCompanyEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtCompanyEmail.setColumns(10);
		txtCompanyEmail.setBounds(171, 216, 175, 33);
		panel_2.add(txtCompanyEmail);

		JLabel lblNewLabel = new JLabel("Serial No");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(528, 13, 67, 18);
		contentPane.add(lblNewLabel);

		txtSNo = new JTextField();
		txtSNo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtSNo.setBounds(605, 10, 116, 23);
		contentPane.add(txtSNo);
		txtSNo.setColumns(10);

		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblItemId.setBounds(846, 13, 67, 18);
		contentPane.add(lblItemId);

		txtItemID = new JTextField();
		txtItemID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtItemID.setColumns(10);
		txtItemID.setBounds(939, 10, 116, 23);
		contentPane.add(txtItemID);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Purchase", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(374, 316, 358, 335);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_1_6_1_2 = new JLabel("Purchase ID");
		lblNewLabel_1_6_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6_1_2.setBounds(10, 24, 154, 22);
		panel_4.add(lblNewLabel_1_6_1_2);

		lblPurchaseID = new JLabel("");
		lblPurchaseID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblPurchaseID.setBounds(176, 24, 170, 22);
		panel_4.add(lblPurchaseID);

		JLabel lblNewLabel_1_6_1_2_1 = new JLabel("Company Name");
		lblNewLabel_1_6_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6_1_2_1.setBounds(10, 73, 154, 22);
		panel_4.add(lblNewLabel_1_6_1_2_1);

		JLabel lblNewLabel_1_6_1_2_2 = new JLabel("Purchase Date");
		lblNewLabel_1_6_1_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6_1_2_2.setBounds(10, 121, 154, 22);
		panel_4.add(lblNewLabel_1_6_1_2_2);

		txtCompanyName = new JTextField();
		txtCompanyName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtCompanyName.setColumns(10);
		txtCompanyName.setBounds(176, 68, 171, 33);
		panel_4.add(txtCompanyName);

		txtPurchaseDate = new JTextField();
		txtPurchaseDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtPurchaseDate.setColumns(10);
		txtPurchaseDate.setBounds(176, 116, 171, 33);
		panel_4.add(txtPurchaseDate);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Item",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2_1.setBounds(759, 40, 397, 609);
		contentPane.add(panel_2_1);

		JLabel lblNewLabel_1_5_1_1 = new JLabel("Item Name");
		lblNewLabel_1_5_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_1_1.setBounds(10, 75, 150, 22);
		panel_2_1.add(lblNewLabel_1_5_1_1);

		JLabel lblNewLabel_1_5_2_1 = new JLabel("Brand Name");
		lblNewLabel_1_5_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_2_1.setBounds(10, 135, 150, 22);
		panel_2_1.add(lblNewLabel_1_5_2_1);

		JLabel lblNewLabel_1_5_3_1 = new JLabel("Type Name");
		lblNewLabel_1_5_3_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_3_1.setBounds(10, 195, 150, 22);
		panel_2_1.add(lblNewLabel_1_5_3_1);

		JLabel lblNewLabel_1_5_4_1 = new JLabel("Serial No\r\n");
		lblNewLabel_1_5_4_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_4_1.setBounds(10, 265, 150, 22);
		panel_2_1.add(lblNewLabel_1_5_4_1);

		JLabel lblNewLabel_1_5_5_1 = new JLabel("Current Price");
		lblNewLabel_1_5_5_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_5_1.setBounds(10, 335, 150, 22);
		panel_2_1.add(lblNewLabel_1_5_5_1);

		JLabel lblNewLabel_1_5_6_1 = new JLabel("Warranty");
		lblNewLabel_1_5_6_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_6_1.setBounds(10, 395, 150, 22);
		panel_2_1.add(lblNewLabel_1_5_6_1);

		JLabel lblNewLabel_1_6_1 = new JLabel("Item ID");
		lblNewLabel_1_6_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6_1.setBounds(10, 26, 150, 22);
		panel_2_1.add(lblNewLabel_1_6_1);

		lblItemID = new JLabel("");
		lblItemID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblItemID.setBounds(185, 26, 202, 22);
		panel_2_1.add(lblItemID);

		txtItemName = new JTextField();
		txtItemName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtItemName.setColumns(10);
		txtItemName.setBounds(185, 70, 202, 33);
		panel_2_1.add(txtItemName);

		txtBrandName = new JTextField();
		txtBrandName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtBrandName.setColumns(10);
		txtBrandName.setBounds(186, 130, 201, 33);
		panel_2_1.add(txtBrandName);

		txtTypeName = new JTextField();
		txtTypeName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtTypeName.setColumns(10);
		txtTypeName.setBounds(185, 190, 202, 33);
		panel_2_1.add(txtTypeName);

		txtSerial = new JTextField();
		txtSerial.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtSerial.setColumns(10);
		txtSerial.setBounds(185, 260, 202, 33);
		panel_2_1.add(txtSerial);

		txtCurPrice = new JTextField();
		txtCurPrice.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtCurPrice.setColumns(10);
		txtCurPrice.setBounds(186, 330, 201, 33);
		panel_2_1.add(txtCurPrice);

		txtWarranty = new JTextField();
		txtWarranty.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtWarranty.setColumns(10);
		txtWarranty.setBounds(185, 390, 202, 33);
		panel_2_1.add(txtWarranty);

		JLabel lblItemID_1_2 = new JLabel("");
		lblItemID_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblItemID_1_2.setBounds(158, 16, 105, 22);
		panel_2_1.add(lblItemID_1_2);

		JLabel lblNewLabel_1_5_6_1_1 = new JLabel("Remark");
		lblNewLabel_1_5_6_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_6_1_1.setBounds(10, 458, 150, 22);
		panel_2_1.add(lblNewLabel_1_5_6_1_1);

		txtRemark = new JTextField();
		txtRemark.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtRemark.setColumns(10);
		txtRemark.setBounds(186, 453, 201, 33);
		panel_2_1.add(txtRemark);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Sale Order", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 316, 324, 335);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1_6_1_1_1 = new JLabel("Sale Order ID");
		lblNewLabel_1_6_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6_1_1_1.setBounds(10, 21, 130, 33);
		panel.add(lblNewLabel_1_6_1_1_1);

		JLabel lblNewLabel_1_6_1_1_2 = new JLabel("Staff ID");
		lblNewLabel_1_6_1_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6_1_1_2.setBounds(10, 76, 101, 22);
		panel.add(lblNewLabel_1_6_1_1_2);

		JLabel lblNewLabel_1_6_1_1_3 = new JLabel("Staff Name");
		lblNewLabel_1_6_1_1_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6_1_1_3.setBounds(10, 125, 161, 22);
		panel.add(lblNewLabel_1_6_1_1_3);

		JLabel lblNewLabel_1_6_1_1_4 = new JLabel("Type");
		lblNewLabel_1_6_1_1_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6_1_1_4.setBounds(10, 175, 101, 22);
		panel.add(lblNewLabel_1_6_1_1_4);

		JLabel lblNewLabel_1_6_1_1_4_1 = new JLabel("Sale Date");
		lblNewLabel_1_6_1_1_4_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6_1_1_4_1.setBounds(10, 223, 101, 22);
		panel.add(lblNewLabel_1_6_1_1_4_1);

		JLabel lblNewLabel_1_6_1_1_4_1_1 = new JLabel("Amount");
		lblNewLabel_1_6_1_1_4_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6_1_1_4_1_1.setBounds(10, 277, 101, 22);
		panel.add(lblNewLabel_1_6_1_1_4_1_1);

		JLabel lblSaleID = new JLabel("");
		lblSaleID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblSaleID.setBounds(163, 32, 169, 22);
		panel.add(lblSaleID);

		txtStaffID = new JTextField();
		txtStaffID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtStaffID.setColumns(10);
		txtStaffID.setBounds(163, 71, 151, 33);
		panel.add(txtStaffID);

		txtStaffName = new JTextField();
		txtStaffName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtStaffName.setColumns(10);
		txtStaffName.setBounds(163, 120, 151, 33);
		panel.add(txtStaffName);

		txtType = new JTextField();
		txtType.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtType.setColumns(10);
		txtType.setBounds(163, 169, 150, 34);
		panel.add(txtType);

		txtSaleDate = new JTextField();
		txtSaleDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtSaleDate.setColumns(10);
		txtSaleDate.setBounds(163, 218, 151, 33);
		panel.add(txtSaleDate);

		txtAmount = new JTextField();
		txtAmount.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtAmount.setColumns(10);
		txtAmount.setBounds(163, 272, 151, 33);
		panel.add(txtAmount);

		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Customer",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2_2.setBounds(10, 40, 324, 264);
		contentPane.add(panel_2_2);

		JLabel lblNewLabel_1_5_1_2 = new JLabel("Customer Name");
		lblNewLabel_1_5_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_1_2.setBounds(10, 70, 184, 22);
		panel_2_2.add(lblNewLabel_1_5_1_2);

		JLabel lblNewLabel_1_5_2_2 = new JLabel("Address");
		lblNewLabel_1_5_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_2_2.setBounds(10, 116, 101, 22);
		panel_2_2.add(lblNewLabel_1_5_2_2);

		JLabel lblNewLabel_1_5_3_2 = new JLabel("Phone");
		lblNewLabel_1_5_3_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_3_2.setBounds(10, 170, 101, 22);
		panel_2_2.add(lblNewLabel_1_5_3_2);

		JLabel lblNewLabel_1_5_4_2 = new JLabel("Email");
		lblNewLabel_1_5_4_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_5_4_2.setBounds(10, 216, 101, 22);
		panel_2_2.add(lblNewLabel_1_5_4_2);

		JLabel lblNewLabel_1_6_2 = new JLabel("Customer ID");
		lblNewLabel_1_6_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_6_2.setBounds(10, 26, 169, 22);
		panel_2_2.add(lblNewLabel_1_6_2);

		JLabel lblCusID = new JLabel("");
		lblCusID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblCusID.setBounds(171, 26, 161, 22);
		panel_2_2.add(lblCusID);

		txtCusName = new JTextField();
		txtCusName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtCusName.setColumns(10);
		txtCusName.setBounds(171, 65, 143, 34);
		panel_2_2.add(txtCusName);

		txtCusAddress = new JTextField();
		txtCusAddress.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtCusAddress.setColumns(10);
		txtCusAddress.setBounds(171, 115, 143, 34);
		panel_2_2.add(txtCusAddress);

		txtCusPhone = new JTextField();
		txtCusPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtCusPhone.setColumns(10);
		txtCusPhone.setBounds(171, 165, 143, 34);
		panel_2_2.add(txtCusPhone);

		txtCusEmail = new JTextField();
		txtCusEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtCusEmail.setColumns(10);
		txtCusEmail.setBounds(171, 215, 143, 34);
		panel_2_2.add(txtCusEmail);

		JButton btnVerifySID = new JButton("Verify");
		btnVerifySID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if (!sdc.CustomerExist(sdm)) {
//					JOptionPane.showMessageDialog(null, "The Customer doesn't exist", "Fail",
//							JOptionPane.ERROR_MESSAGE);
//					txtRam.requestFocus(true);
//				} else {
				try {
					sdm.setSerial(txtSNo.getText());
					if (!sdc.serialExist(sdm)) {
						JOptionPane.showMessageDialog(null, "This Serial Doesn't exist in the system", "Fail",
								JOptionPane.ERROR_MESSAGE);
					} else {

						SaleDetailModel verifyCus = sdc.verifySerial(sdm);
						// System.out.println(txtSerial.getText());
						lblCusID.setText(verifyCus.getCustomerid());
						txtCusAddress.setText(verifyCus.getCustomeraddress());
						txtCusName.setText(verifyCus.getCustomername());
						txtCusPhone.setText(verifyCus.getCustomerphone());
						txtCusEmail.setText(verifyCus.getCustomeremail());
						txtCusAddress.setText(verifyCus.getCustomeraddress());
						lblSaleID.setText(verifyCus.getSaleorderid());
						txtStaffID.setText(verifyCus.getStaffid());
						txtStaffName.setText(verifyCus.getStaffname());
						txtType.setText(verifyCus.getTypename());
						txtSaleDate.setText(verifyCus.getSaleorderdate());
						txtAmount.setText(String.valueOf(verifyCus.getAmount()));
						lblCompanyID.setText(verifyCus.getCompanyid());
						txtCompanyAddress.setText(verifyCus.getCompanyaddress());
						txtComName.setText(verifyCus.getCompanyname());
						txtCompanyPhone.setText(verifyCus.getCompanyphone());
						txtCompanyEmail.setText(verifyCus.getCompanyemail());
						lblPurchaseID.setText(verifyCus.getPurchaseid());
						txtCompanyName.setText(verifyCus.getCompanyname());
						txtPurchaseDate.setText(verifyCus.getPurchasedate());
						lblItemID.setText(String.valueOf(verifyCus.getItemid()));
						txtItemName.setText(verifyCus.getItemname());
						txtBrandName.setText(verifyCus.getBrandname());
						txtTypeName.setText(verifyCus.getTypename());
						txtSerial.setText(verifyCus.getSerial());
						txtCurPrice.setText(String.valueOf(verifyCus.getCurrentprice()));
						txtWarranty.setText(verifyCus.getWarranty());
						txtRemark.setText(verifyCus.getRemark());
						System.out.println(verifyCus.getWarranty());
						
						txtItemID.setText("");
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// txtCustomerID.setText(verifyCus.getCustomerid());

			}
//			}
		});
		btnVerifySID.setForeground(new Color(47, 79, 79));
		btnVerifySID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnVerifySID.setBackground(new Color(102, 205, 170));
		btnVerifySID.setBounds(730, 11, 89, 23);
		contentPane.add(btnVerifySID);

		btnVerifyIID = new JButton("Verify");
		btnVerifyIID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleDetailModel verifyCus;
				try {
					if (txtItemID.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "Item ID cannot be blank", "Fail",
								JOptionPane.ERROR_MESSAGE);
					} else {
						sdm.setItemid(Integer.parseInt(txtItemID.getText()));
 
					}
					if (!sdc.idExist(sdm)&&!txtItemID.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "This id Doesn't exist in the system", "Fail",
								JOptionPane.ERROR_MESSAGE);
					} else if(!txtItemID.getText().isBlank()) {
 
						System.out.println(lblItemID.getText());
						verifyCus = sdc.verifyItemID(sdm);
						lblCusID.setText(verifyCus.getCustomerid());
						txtCusAddress.setText(verifyCus.getCustomeraddress());
						txtCusName.setText(verifyCus.getCustomername());
						txtCusPhone.setText(verifyCus.getCustomerphone());
						txtCusEmail.setText(verifyCus.getCustomeremail());
						System.out.println(verifyCus.getCustomeraddress());
						lblSaleID.setText(verifyCus.getSaleorderid());
						txtStaffID.setText(verifyCus.getStaffid());
						txtStaffName.setText(verifyCus.getStaffname());
						txtType.setText(verifyCus.getTypename());
						txtSaleDate.setText(verifyCus.getSaleorderdate());
						txtAmount.setText(String.valueOf(verifyCus.getAmount()));
						lblCompanyID.setText(verifyCus.getCompanyid());
						txtCompanyAddress.setText(verifyCus.getCompanyaddress());
						txtComName.setText(verifyCus.getCompanyname());
						txtCompanyPhone.setText(verifyCus.getCompanyphone());
						txtCompanyEmail.setText(verifyCus.getCompanyemail());
						lblPurchaseID.setText(verifyCus.getPurchaseid());
						txtCompanyName.setText(verifyCus.getCompanyname());
						txtPurchaseDate.setText(verifyCus.getPurchasedate());
						txtItemName.setText(verifyCus.getItemname());
						txtBrandName.setText(verifyCus.getBrandname());
						txtTypeName.setText(verifyCus.getTypename());
						txtSerial.setText(verifyCus.getSerial());
						txtCurPrice.setText(String.valueOf(verifyCus.getCurrentprice()));
						txtWarranty.setText(verifyCus.getWarranty());
						txtRemark.setText(verifyCus.getRemark());
						
						txtSNo.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 
			}
		});
		btnVerifyIID.setForeground(new Color(47, 79, 79));
		btnVerifyIID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnVerifyIID.setBackground(new Color(102, 205, 170));
		btnVerifyIID.setBounds(1065, 11, 89, 23);
		contentPane.add(btnVerifyIID);
		
		date d = new date();

		txtCusName.setEnabled(false);
		txtCusAddress.setEnabled(false);
		txtCusPhone.setEnabled(false);
		txtCusEmail.setEnabled(false);
		txtStaffID.setEnabled(false);
		txtStaffName.setEnabled(false);
		txtType.setEnabled(false);
		txtSaleDate.setEnabled(false);
		txtAmount.setEnabled(false);
		txtCompanyAddress.setEnabled(false);
		txtCompanyPhone.setEnabled(false);
		txtCompanyEmail.setEnabled(false);
		txtCompanyName.setEnabled(false);
		txtPurchaseDate.setEnabled(false);
		txtItemName.setEnabled(false);
		txtBrandName.setEnabled(false);
		txtTypeName.setEnabled(false);
		txtSerial.setEnabled(false);
		txtCurPrice.setEnabled(false);
		txtWarranty.setEnabled(false);
		txtRemark.setEnabled(false);

		Color textcolor = new Color(0, 0, 0);
		txtCusName.setDisabledTextColor(textcolor);
		txtCusAddress.setDisabledTextColor(textcolor);
		txtCusPhone.setDisabledTextColor(textcolor);
		txtCusEmail.setDisabledTextColor(textcolor);
		txtStaffID.setDisabledTextColor(textcolor);
		txtStaffName.setDisabledTextColor(textcolor);
		txtType.setDisabledTextColor(textcolor);
		txtSaleDate.setDisabledTextColor(textcolor);
		txtAmount.setDisabledTextColor(textcolor);
		txtCompanyAddress.setDisabledTextColor(textcolor);
		txtCompanyPhone.setDisabledTextColor(textcolor);
		txtCompanyEmail.setDisabledTextColor(textcolor);
		txtCompanyName.setDisabledTextColor(textcolor);
		txtPurchaseDate.setDisabledTextColor(textcolor);
		txtItemName.setDisabledTextColor(textcolor);
		txtBrandName.setDisabledTextColor(textcolor);
		txtTypeName.setDisabledTextColor(textcolor);
		txtSerial.setDisabledTextColor(textcolor);
		txtCurPrice.setDisabledTextColor(textcolor);
		txtWarranty.setDisabledTextColor(textcolor);
		txtRemark.setDisabledTextColor(textcolor);
	}
}
