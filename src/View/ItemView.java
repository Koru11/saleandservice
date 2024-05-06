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
import Controller.BrandController;
import Controller.ItemController;
import Controller.TypeController;
import Model.BrandModel;
import Model.ItemModel;
import Model.TypeModel;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ItemView extends JFrame {

	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String ItemID;
	int r;
	private JTable tblItem;
	private JScrollPane scrollPane_1;
	private JLabel lblN;
	private JLabel lblItemID;
	private JLabel lblNewLabel_3;
	private JTextField txtItemName;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnClear;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
	private JMenuItem mntmNewMenuItem_6;
	private JButton btnShowAll;
	private JLabel lblNewLabel_1;
	private JTextField txtSearch;
	private JTextField txtWarranty;
	private JTextField txtSerial;
	private JTextField txtRemark;
	private JTextField txtPrice;
	
	private JComboBox comboBrandName;
	private JComboBox comboTypeName;
	String brandid = "";
	private JLabel lblItemEntryAnd;
	private ItemController ic = new ItemController();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemView frame = new ItemView();
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
	public ItemView() {
		setTitle("Item");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 650);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 139));
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("List");
		lblNewLabel_2.setBounds(10, 56, 76, 14);
		lblNewLabel_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 88, 1084, 250);
		contentPane.add(scrollPane_1);
		
		tblItem = new JTable();
		tblItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				r = tblItem.getSelectedRow();
				ItemID = (String)tblItem.getValueAt(r, 0);
				String str = (String) tblItem.getValueAt(r, 1);
				String[] parts = str.split("/");
				String brandName = parts[0].trim();
				String typeName = parts[1].trim();
				String itemName = parts[2].trim();
				lblItemID.setText(ItemID);
				comboBrandName.setSelectedItem(brandName);
				comboTypeName.setSelectedItem(typeName);
				txtItemName.setText(itemName);
				txtPrice.setText((String)tblItem.getValueAt(r, 2));
				txtRemark.setText((String)tblItem.getValueAt(r, 5));
				txtWarranty.setText((String)tblItem.getValueAt(r, 3));
				txtSerial.setText((String)tblItem.getValueAt(r, 4));
				//btnSave.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				comboBrandName.requestFocus(true);
			}
		});
		tblItem.setBackground(new Color(192, 192, 192));
		tblItem.setForeground(new Color(47, 79, 79));
		tblItem.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		scrollPane_1.setViewportView(tblItem);
		
		lblN = new JLabel("Item ID");
		lblN.setBounds(10, 360, 76, 19);
		lblN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblN.setForeground(new Color(47, 79, 79));
		contentPane.add(lblN);
		
		lblItemID = new JLabel("");
		lblItemID.setBounds(142, 360, 117, 18);
		lblItemID.setForeground(new Color(47, 79, 79));
		lblItemID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		contentPane.add(lblItemID);
		
		lblNewLabel_3 = new JLabel("Item Name");
		lblNewLabel_3.setBounds(10, 400, 87, 14);
		lblNewLabel_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_3);
		
		txtItemName = new JTextField();
		txtItemName.setBounds(142, 400, 117, 25);
		txtItemName.setForeground(new Color(47, 79, 79));
		txtItemName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		contentPane.add(txtItemName);
		txtItemName.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lblItemID.getText().trim().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "There is a blank field!","Fail", JOptionPane.ERROR_MESSAGE);
					txtItemName.requestFocus(true);
					txtItemName.selectAll();
				}else if(comboBrandName.getSelectedIndex()<=0) {
					JOptionPane.showMessageDialog(null, "Please choose brand Name!","Fail", JOptionPane.ERROR_MESSAGE);
					comboBrandName.requestFocus(true);
				}else if(comboTypeName.getSelectedIndex()<=0) {
					JOptionPane.showMessageDialog(null, "Please choose type Name!","Fail", JOptionPane.ERROR_MESSAGE);
					comboTypeName.requestFocus(true);
				}else if(txtPrice.getText().trim().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Please type Price!","Fail", JOptionPane.ERROR_MESSAGE);
					txtItemName.requestFocus(true);
				}else if(txtRemark.getText().trim().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Please type remark!","Fail", JOptionPane.ERROR_MESSAGE);
					txtItemName.requestFocus(true);
				}else if(ic.serialExist(txtSerial.getText()) && !txtSerial.getText().equals("-")) {
					JOptionPane.showMessageDialog(null, "The serial already existed in database");
					txtItemName.requestFocus();
					Clear();
				}else {
					BrandController bc = new BrandController();
					BrandModel bm = new BrandModel();
					bm.setBrandname(comboBrandName.getSelectedItem().toString());
					String brandid = bc.searchBrandId(bm);
					
					TypeController tc = new TypeController();
					TypeModel tm = new TypeModel();
					tm.setTypename(comboTypeName.getSelectedItem().toString());
					String typeid = tc.searchTypeID(tm);
					
					ItemController ic = new ItemController();
					ItemModel im = new ItemModel();
					im.setItemid(lblItemID.getText().toString());
					im.setBrandid(brandid);
					im.setTypeid(typeid);
					im.setItemname(txtItemName.getText().toString());
					im.setCurrentprice(Integer.parseInt(txtPrice.getText().toString()));
					im.setWarranty(txtWarranty.getText().toString());
					im.setRemark(txtRemark.getText().toString());
					im.setSerial(txtSerial.getText().toString());
					im.setTotalqty(0);
					
					if(Checking.IsValidName(im.getItemname())) {
						JOptionPane.showMessageDialog(null, "Invalid Name!","Fail", JOptionPane.ERROR_MESSAGE);
						txtItemName.requestFocus(true);
						txtItemName.selectAll();
					}else {
						int rs = ic.update(im);
						if(rs==1) {
//							AutoID();
							Clear();
							showList();
							JOptionPane.showMessageDialog(null, "Update Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(416, 448, 89, 23);
		btnUpdate.setForeground(new Color(47, 79, 79));
		btnUpdate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnUpdate.setBackground(new Color(102, 205, 170));
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemModel im = new ItemModel();
				im.setItemid(lblItemID.getText().toString());
				if(!im.getItemid().isBlank()) {
					if(JOptionPane.showConfirmDialog(null,"Are you sure you want to delete?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) {
						ItemController ic = new ItemController();
						int rs = ic.delete(im);
						//System.out.println(rs);
						if(rs==1) {
							JOptionPane.showMessageDialog(null,"Delete Successfully","Successfully", JOptionPane.INFORMATION_MESSAGE);
//							AutoID();
							showList();
							Clear();
						}else {
							JOptionPane.showMessageDialog(null,"Delete fails, there could be a customer related to this product","Successfully", JOptionPane.ERROR_MESSAGE);
							}
						}
		
					}
				}
			}
		);
		btnDelete.setEnabled(false);
		btnDelete.setBounds(552, 448, 89, 23);
		btnDelete.setForeground(new Color(47, 79, 79));
		btnDelete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnDelete.setBackground(new Color(102, 205, 170));
		contentPane.add(btnDelete);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		btnClear.setBounds(552, 494, 89, 23);
		btnClear.setForeground(new Color(47, 79, 79));
		btnClear.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnClear.setBackground(new Color(102, 205, 170));
		contentPane.add(btnClear);

		

		btnShowAll = new JButton("Show");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showList();
				txtSearch.requestFocus(true);
				txtSearch.selectAll();
			}
		});
		btnShowAll.setForeground(new Color(47, 79, 79));
		btnShowAll.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnShowAll.setBackground(new Color(102, 205, 170));
		btnShowAll.setBounds(416, 494, 89, 23);
		contentPane.add(btnShowAll);
		
		lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(893, 53, 61, 17);
		contentPane.add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				showListOne(txtSearch.getText().trim());
				
			}
		});
		txtSearch.setForeground(new Color(47, 79, 79));
		txtSearch.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearch.setBounds(966, 53, 106, 25);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Brand Name");
		lblNewLabel_3_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(10, 438, 87, 14);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Type Name");
		lblNewLabel_3_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(10, 473, 87, 19);
		contentPane.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Warranty");
		lblNewLabel_3_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_3.setBounds(10, 509, 87, 22);
		contentPane.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("Serial No");
		lblNewLabel_3_4.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_4.setBounds(10, 547, 87, 14);
		contentPane.add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_3_6 = new JLabel("Remark");
		lblNewLabel_3_6.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_6.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_6.setBounds(416, 400, 87, 14);
		contentPane.add(lblNewLabel_3_6);
		
		txtWarranty = new JTextField();
		txtWarranty.setForeground(new Color(47, 79, 79));
		txtWarranty.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtWarranty.setColumns(10);
		txtWarranty.setBounds(142, 507, 117, 25);
		contentPane.add(txtWarranty);
		
		txtSerial = new JTextField();
		txtSerial.setForeground(new Color(47, 79, 79));
		txtSerial.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSerial.setColumns(10);
		txtSerial.setBounds(142, 541, 117, 25);
		contentPane.add(txtSerial);
		
		txtRemark = new JTextField();
		txtRemark.setForeground(new Color(47, 79, 79));
		txtRemark.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtRemark.setColumns(10);
		txtRemark.setBounds(524, 397, 117, 25);
		contentPane.add(txtRemark);
		
		JLabel lblNewLabel_3_6_1 = new JLabel("Current Price");
		lblNewLabel_3_6_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_3_6_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3_6_1.setBounds(10, 581, 87, 14);
		contentPane.add(lblNewLabel_3_6_1);
		
		txtPrice = new JTextField();
		txtPrice.setForeground(new Color(47, 79, 79));
		txtPrice.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(142, 575, 117, 25);
		contentPane.add(txtPrice);
		
		comboBrandName = new JComboBox();
		comboBrandName.setModel(new DefaultComboBoxModel(new String[] {"Choose"}));
		comboBrandName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		comboBrandName.setBounds(142, 434, 117, 25);
		contentPane.add(comboBrandName);
		
		comboTypeName = new JComboBox();
		comboTypeName.setModel(new DefaultComboBoxModel(new String[] {"Choose"}));
		comboTypeName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		comboTypeName.setBounds(142, 471, 117, 25);
		contentPane.add(comboTypeName);
		
		lblItemEntryAnd = new JLabel("  Item Management");
		lblItemEntryAnd.setOpaque(true);
		lblItemEntryAnd.setForeground(new Color(224, 255, 255));
		lblItemEntryAnd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblItemEntryAnd.setBackground(new Color(32, 178, 170));
		lblItemEntryAnd.setBounds(0, 0, 1084, 44);
		contentPane.add(lblItemEntryAnd);
		try {
			fillCombo();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createTable();
//		AutoID();
		showList();
	}
	public void fillCombo() throws ClassNotFoundException {
		SQLQueries.addComboBox("brand", "brandname", comboBrandName);
		SQLQueries.addComboBox("type", "typename", comboTypeName);
	}
	public void setColoumnWidth(int index,int width) {
		
		DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblItem.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}
	
	public void createTable() {
		dtm.addColumn("Item ID");
		
		dtm.addColumn("Item");
		dtm.addColumn("Current Price");
		//dtm.addColumn("Total Qty");
		dtm.addColumn("Warranty");
		dtm.addColumn("Serial");
		dtm.addColumn("Remark");
		tblItem.setModel(dtm);
		setColoumnWidth(0,50);
		setColoumnWidth(1,270);
		setColoumnWidth(2,90);
		setColoumnWidth(3,90);
		setColoumnWidth(4,270);
		setColoumnWidth(5,270);
	
		
	}
//	public void AutoID(){
//		ClsDBConnection dbcon = new ClsDBConnection();
//		try {
//			lblItemID.setText(dbcon.AutoID("itemid", "saleandservice.item","IT-"));
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public void Clear() {
		comboBrandName.setSelectedIndex(0);
		comboTypeName.setSelectedIndex(0);
		lblItemID.setText("");
		txtItemName.setText("");
		txtPrice.setText("0");
		txtRemark.setText("");
		txtWarranty.setText("");
		txtSerial.setText("");
		//btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		comboBrandName.requestFocus(true);
	}
	
	
	public void showList() {
		String data[] = new String[7];
		ItemController ic = new ItemController();
		try {
			List<ItemModel> list = ic.selectall();
			dtm.setRowCount(0);
			for(ItemModel im:list) {
				data[0] = im.getItemid();
				String itemname = im.getBrandname() + " / " +im.getTypename() + " / " + im.getItemname();
				data[1] = itemname;
				data[2] = String.valueOf(im.getCurrentprice());
				//data[3] = String.valueOf(im.getTotalqty());
				data[3]=im.getWarranty();
				data[4]=im.getSerial();
				data[5] = im.getRemark();
				
				
				dtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showListOne(String name) {
		String data[] = new String[7];
		ItemController ic = new ItemController();
		ItemModel m = new ItemModel();
		m.setItemname(name);
		List<ItemModel> list = ic.selectone(m);
		dtm.setRowCount(0);
		for(ItemModel im:list) {
			data[0] = im.getItemid();
			String itemname = im.getBrandname() + " / " +im.getTypename() + " / " + im.getItemname();
			data[1] = itemname;
			data[2] = String.valueOf(im.getCurrentprice());
			//data[3] = String.valueOf(im.getTotalqty());
			data[3]=im.getWarranty();
			data[4]=im.getSerial();
			
			data[5] = im.getRemark();
			
			
			dtm.addRow(data);
		}
	}
}
