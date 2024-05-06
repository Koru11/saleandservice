package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Connection.Checking;
import Connection.ClsDBConnection;
import Controller.CompanyController;
import Model.CompanyModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;

public class CompanyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String companyid;
	String companyName;	
	int row;
	
	private JTable tblCompany;
	private JScrollPane scrollPane_1;
	private JLabel lblN;
	private JLabel lblcompanyid;
	private JLabel lblNewLabel_3;
	private JTextField txtcompanyName;
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
	private JTextField textSearch;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtPhone;
	private JLabel lblNewLabel_6;
	private JTextField txtEmail;
	JTextArea txtAddress = new JTextArea();

	/**
	 * Create the panel.
	 */
	public CompanyPanel() {
		CompanyModel bm = new CompanyModel();
		CompanyController bc = new CompanyController();
		
		setBounds(100, 100, 779, 502);
		contentPane = new JPanel();
		setForeground(new Color(0, 0, 139));
		setBackground(new Color(224, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		txtAddress.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel lblNewLabel_2 = new JLabel("List");
		lblNewLabel_2.setBounds(10, 56, 76, 14);
		lblNewLabel_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblNewLabel_2);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 88, 763, 143);
		add(scrollPane_1);
		
		tblCompany = new JTable();
		tblCompany.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tblCompany.getSelectedRow();

				companyid = (String) tblCompany.getValueAt(row, 0);
				companyName = (String) tblCompany.getValueAt(row, 1);
				
				lblcompanyid.setText(companyid);
				txtcompanyName.setText((String) tblCompany.getValueAt(row, 1));
				txtAddress.setText((String)tblCompany.getValueAt(row, 2));
				txtPhone.setText((String)tblCompany.getValueAt(row, 3));
				txtEmail.setText((String)tblCompany.getValueAt(row, 4));
				
				
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);

			}
		});
		tblCompany.setBackground(new Color(192, 192, 192));
		tblCompany.setForeground(new Color(47, 79, 79));
		tblCompany.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		scrollPane_1.setViewportView(tblCompany);
		
		lblN = new JLabel("Company ID");
		lblN.setBounds(10, 258, 106, 23);
		lblN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblN.setForeground(new Color(47, 79, 79));
		add(lblN);
		
		lblcompanyid = new JLabel("");
		lblcompanyid.setBounds(140, 263, 117, 18);
		lblcompanyid.setForeground(new Color(47, 79, 79));
		lblcompanyid.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblcompanyid);
		
		lblNewLabel_3 = new JLabel("Company Name");
		lblNewLabel_3.setBounds(10, 299, 120, 19);
		lblNewLabel_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblNewLabel_3);
		
		txtcompanyName = new JTextField();
		txtcompanyName.setBounds(140, 299, 117, 25);
		txtcompanyName.setForeground(new Color(47, 79, 79));
		txtcompanyName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(txtcompanyName);
		txtcompanyName.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bm.setCompanyid(lblcompanyid.getText().toString());
				bm.setCompanyname(txtcompanyName.getText().toString());
				bm.setEmail(txtEmail.getText().toString());
				bm.setAddress(txtAddress.getText().toString());
				bm.setPhone(txtPhone.getText().toString());


				if (lblcompanyid.getText().isBlank() || txtcompanyName.getText().isBlank() || txtPhone.getText().isBlank()
						|| txtEmail.getText().isBlank() || txtAddress.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtcompanyName.requestFocus(true);
					txtcompanyName.selectAll();
				} else if (Checking.InvalidEmail(txtEmail.getText())) {
					JOptionPane.showMessageDialog(null, "Invalid Email Address", "Fail", JOptionPane.ERROR_MESSAGE);
					txtPhone.requestFocus(true);
					txtPhone.selectAll();
				} else if (Checking.isPhoneNo(txtPhone.getText())) {
					JOptionPane.showMessageDialog(null, "Invalid Phone Number", "Fail", JOptionPane.ERROR_MESSAGE);
					txtAddress.requestFocus(true);
					txtAddress.selectAll();
				}

				else {

					if (Checking.IsValidName(bm.getCompanyname())) {
						JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid", JOptionPane.ERROR_MESSAGE);
						txtcompanyName.requestFocus(true);
						txtcompanyName.selectAll();
					} else {
						int rs = bc.insert(bm);
						if (rs == 1) {
							JOptionPane.showMessageDialog(null, "Save Successfully", "Successfully",
									JOptionPane.INFORMATION_MESSAGE);
							AutoID();
							Clear();
							try {
								showList();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				}
			}
		});

		btnSave.setBounds(405, 307, 89, 23);
		btnSave.setBackground(new Color(102, 205, 170));
		btnSave.setForeground(new Color(47, 79, 79));
		btnSave.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(btnSave);
		
		

		
		lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(565, 56, 61, 14);
		add(lblNewLabel_1);
		
		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					showListone(textSearch.getText().trim());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		textSearch.setForeground(new Color(47, 79, 79));
		textSearch.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		textSearch.setBounds(633, 55, 106, 25);
		add(textSearch);
		textSearch.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setForeground(new Color(47, 79, 79));
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 421, 106, 23);
		add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Phone No");
		lblNewLabel_5.setForeground(new Color(47, 79, 79));
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(10, 345, 106, 14);
		add(lblNewLabel_5);
		
		txtPhone = new JTextField();
		txtPhone.setForeground(new Color(47, 79, 79));
		txtPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPhone.setColumns(10);
		txtPhone.setBounds(140, 343, 117, 25);
		add(txtPhone);
		
		lblNewLabel_6 = 	new JLabel("Email");
		lblNewLabel_6.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_6.setForeground(new Color(47, 79, 79));
		lblNewLabel_6.setBounds(10, 388, 106, 14);
		add(lblNewLabel_6);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(47, 79, 79));
		txtEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(140, 386, 117, 25);
		add(txtEmail);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bm.setCompanyid(lblcompanyid.getText().toString());
				bm.setCompanyname(txtcompanyName.getText().toString());
				bm.setEmail(txtEmail.getText().toString());
				bm.setAddress(txtAddress.getText().toString());
				bm.setPhone(txtPhone.getText().toString());


				if (lblcompanyid.getText().isBlank() || txtcompanyName.getText().isBlank()
						|| txtEmail.getText().isBlank() || txtAddress.getText().isBlank() || txtPhone.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtcompanyName.requestFocus(true);
					txtcompanyName.selectAll();
				} else if (Checking.InvalidEmail(txtEmail.getText())) {
					JOptionPane.showMessageDialog(null, "Invalid Email Address", "Fail", JOptionPane.ERROR_MESSAGE);
					txtPhone.requestFocus(true);
					txtPhone.selectAll();
				} else if (Checking.isPhoneNo(txtPhone.getText())) {
					JOptionPane.showMessageDialog(null, "Invalid Phone Number", "Fail", JOptionPane.ERROR_MESSAGE);
					txtAddress.requestFocus(true);
					txtAddress.selectAll();
				}
				{

					if (Checking.IsValidName(bm.getCompanyname())) {
						JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid", JOptionPane.ERROR_MESSAGE);
						txtcompanyName.requestFocus(true);
						txtcompanyName.selectAll();
					} else {
						int rs = bc.update(bm);
						if (rs == 1) {
							JOptionPane.showMessageDialog(null, "Update Successfully", "Successfully",
									JOptionPane.INFORMATION_MESSAGE);
							AutoID();
							Clear();
							try {
								showList();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				}

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
		});

		btnUpdate.setForeground(new Color(47, 79, 79));
		
		btnUpdate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnUpdate.setEnabled(false);
		btnUpdate.setBackground(new Color(102, 205, 170));
		btnUpdate.setBounds(527, 309, 89, 23);
		add(btnUpdate);
		
		btnShowAll = new JButton("Show");
		btnShowAll.setForeground(new Color(47, 79, 79));
		btnShowAll.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnShowAll.setBackground(new Color(102, 205, 170));
		btnShowAll.setBounds(650, 309, 89, 23);
		add(btnShowAll);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure do you want to Delete?", "Warning",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					delete(lblcompanyid.getText());
					AutoID();
					Clear();
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
		btnDelete.setBounds(448, 354, 89, 23);
		add(btnDelete);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		btnClear.setForeground(new Color(47, 79, 79));
		btnClear.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnClear.setBackground(new Color(102, 205, 170));
		btnClear.setBounds(590, 354, 89, 23);
		add(btnClear);
		
		btnDelete.setEnabled(false);
		
		JLabel lblCompanyEntryAnd = new JLabel("  Company Entry and List   ");
		lblCompanyEntryAnd.setOpaque(true);
		lblCompanyEntryAnd.setForeground(new Color(224, 255, 255));
		lblCompanyEntryAnd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblCompanyEntryAnd.setBackground(new Color(32, 178, 170));
		lblCompanyEntryAnd.setBounds(0, 0, 763, 44);
		add(lblCompanyEntryAnd);
		txtAddress.setLineWrap(true);
		
		txtAddress.setBounds(140, 422, 117, 69);
		add(txtAddress);

		
		createTable();
		AutoID();
		try {
			showList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void setColoumnWidth(int index,int width) {
		
		DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblCompany.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}
	
	public void createTable() {
		dtm.addColumn("company ID");
		dtm.addColumn("company Name");
		dtm.addColumn("Address");
		dtm.addColumn("Phone Number");
		dtm.addColumn("Email");
		
	
		tblCompany.setModel(dtm);
		setColoumnWidth(0,30);

		setColoumnWidth(1,60);
		setColoumnWidth(2,60);
		setColoumnWidth(3,80);
		setColoumnWidth(4,60);
		
	}
	public void AutoID(){
		ClsDBConnection dbcon = new ClsDBConnection();
		try {
			lblcompanyid.setText(dbcon.AutoID("companyid", "saleandservice.company","CP-"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Clear() {
		AutoID();
		btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		txtAddress.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
		txtcompanyName.setText("");
		txtcompanyName.requestFocus(true);
		AutoID();
	}
	public void showList() throws SQLException{
		String data[] = new String[5];
		CompanyController bc = new CompanyController();
		List<CompanyModel> list = bc.selectAll();
		dtm.setRowCount(0);
		for(CompanyModel bm:list) {
			data[0] = bm.getCompanyid();
			data[1] = bm.getCompanyname();
			data[2]=bm.getAddress();
			data[3]=bm.getPhone();
			data[4]=bm.getEmail();

			dtm.addRow(data);
		}
	}
	
	public void showListone(String name) throws SQLException{
		String data[]= new String[5];
		CompanyModel b = new CompanyModel();
		b.setCompanyname(name);
		CompanyController bc = new CompanyController();
		List<CompanyModel> list = bc.selectOne(b);
		dtm.setRowCount(0);
		for(CompanyModel bm:list) {
			data[0]=bm.getCompanyid();
			data[1]=bm.getCompanyname();
			data[2]=bm.getAddress();
			data[3]=bm.getPhone();
			data[4]=bm.getEmail();

			dtm.addRow(data);
		}
	}
	public void delete(String id) {
		CompanyController bc = new CompanyController();
		CompanyModel bm = new CompanyModel();
		bm.setCompanyid(id);
		bc.delete(bm);

	}
}
