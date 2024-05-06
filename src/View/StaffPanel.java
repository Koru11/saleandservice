package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Connection.Checking;
import Connection.ClsDBConnection;
import Connection.SQLQueries;
import Controller.DeptController;
import Controller.PostController;
import Controller.StaffController;
import Model.DeptModel;
import Model.PostModel;
import Model.StaffModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class StaffPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String StaffID = null;
	int r;
	private JTable tblStaff;
	private JScrollPane scrollPane_1;
	private JLabel lblN;
	private JLabel lblStaffID;
	private JLabel lblNewLabel_3;
	private JTextField txtStaffName;
	private JButton btnSave;
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
	private JTextField textField;
	private JLabel lblNewLabel_4;
	private JComboBox cboPost;
	private JLabel lblNewLabel_5;
	private JComboBox cboDept;
	private JLabel lblNewLabel_6;
	private JTextField txtEmail;
	private JLabel lblNewLabel_7;
	private JTextField txtPhone;
	private JLabel lblNewLabel_8;
	JTextArea txtAddress = new JTextArea();


	private String postid;
	private String deptid;
	private int row;
	private String staffid;

	DeptController dc = new DeptController();
	DeptModel dm = new DeptModel();
	StaffController sc = new StaffController();
	PostController pc = new PostController();
	PostModel pm = new PostModel();
	private JLabel lblStaffEntryAnd;

	/**
	 * Create the panel.
	 */
	public StaffPanel() {


		setBounds(100, 100, 779, 540);
		contentPane = new JPanel();
		setForeground(new Color(0, 0, 139));
		setBackground(new Color(224, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("List");
		lblNewLabel_2.setBounds(10, 56, 76, 14);
		lblNewLabel_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblNewLabel_2);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 88, 779, 143);
		add(scrollPane_1);

		txtAddress.setBorder(BorderFactory.createLineBorder(Color.black));

		
		tblStaff = new JTable();
		tblStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tblStaff.getSelectedRow();
				staffid = (String) tblStaff.getValueAt(row, 0);
				lblStaffID.setText(staffid);
				txtStaffName.setText((String) tblStaff.getValueAt(row, 1));
				cboPost.setSelectedItem((String) tblStaff.getValueAt(row, 2));
				cboDept.setSelectedItem((String) tblStaff.getValueAt(row, 3));
				txtEmail.setText((String) tblStaff.getValueAt(row, 5));
				txtPhone.setText((String) tblStaff.getValueAt(row, 4));
				txtAddress.setText((String) tblStaff.getValueAt(row, 6));

				btnSave.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);

			}
		});
		tblStaff.setBackground(new Color(192, 192, 192));
		tblStaff.setForeground(new Color(47, 79, 79));
		tblStaff.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		scrollPane_1.setViewportView(tblStaff);
		
		tblStaff.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		lblN = new JLabel("Staff ID");
		lblN.setBounds(10, 242, 76, 19);
		lblN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblN.setForeground(new Color(47, 79, 79));
		add(lblN);

		lblStaffID = new JLabel("");
		lblStaffID.setBounds(140, 242, 117, 18);
		lblStaffID.setForeground(new Color(47, 79, 79));
		lblStaffID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblStaffID);

		lblNewLabel_3 = new JLabel("Staff Name");
		lblNewLabel_3.setBounds(10, 278, 87, 14);
		lblNewLabel_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblNewLabel_3);

		txtStaffName = new JTextField();
		txtStaffName.setBounds(140, 273, 117, 25);
		txtStaffName.setForeground(new Color(47, 79, 79));
		txtStaffName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(txtStaffName);
		txtStaffName.setColumns(10);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffModel saveModel = new StaffModel();

				if (txtStaffName.getText().isBlank() || txtAddress.getText().isBlank() || txtEmail.getText().isBlank()
						|| txtPhone.getText().isBlank() || cboDept.getSelectedIndex() <= 0
						|| cboPost.getSelectedIndex() <= 0) {
					JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtStaffName.requestFocus();
					txtStaffName.selectAll();
				} else if (Checking.IsValidName(txtStaffName.getText()) || Checking.isAllNum(txtStaffName.getText())) {
					JOptionPane.showMessageDialog(null, "The name is Invalid!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtStaffName.requestFocus(true);
					txtStaffName.selectAll();
				} else if (Checking.InvalidEmail(txtEmail.getText())) {
					JOptionPane.showMessageDialog(null, "The Email is Invalid!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtStaffName.requestFocus(true);
					txtStaffName.selectAll();
				} else if (Checking.isPhoneNo(txtPhone.getText())) {
					JOptionPane.showMessageDialog(null, "The Phone number is Invalid!", "Fail",
							JOptionPane.ERROR_MESSAGE);
					txtStaffName.requestFocus(true);
					txtStaffName.selectAll();
				} else if(sc.dupeStaffName(txtStaffName.getText())){
					JOptionPane.showMessageDialog(null, "There can't be a duplicate Staff name! Use a prefix", "Fail",
							JOptionPane.ERROR_MESSAGE);
					txtStaffName.requestFocus(true);
					txtStaffName.selectAll();
				}else {
					saveModel.setStaffid(lblStaffID.getText());
					saveModel.setStaffname(txtStaffName.getText());
					saveModel.setDepid(deptid);
					saveModel.setPostid(postid);
					saveModel.setPhone(txtPhone.getText());
					saveModel.setEmail(txtEmail.getText());
					saveModel.setAddress(txtAddress.getText());

					int result = sc.insert(saveModel);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Save Successfully", "Successfully",
								JOptionPane.INFORMATION_MESSAGE);
						AutoID();
						showList();
						Clear();
					}
				}

			}
		});
		btnSave.setBounds(392, 356, 89, 23);
		btnSave.setBackground(new Color(102, 205, 170));
		btnSave.setForeground(new Color(47, 79, 79));
		btnSave.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(btnSave);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffModel saveModel = new StaffModel();

				if (txtStaffName.getText().isBlank() || txtAddress.getText().isBlank() || txtEmail.getText().isBlank()
						|| txtPhone.getText().isBlank() || cboDept.getSelectedIndex() <= 0
						|| cboPost.getSelectedIndex() <= 0) {
					JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtStaffName.requestFocus();
					txtStaffName.selectAll();
				} else if (Checking.IsValidName(txtStaffName.getText()) || Checking.isAllNum(txtStaffName.getText())) {
					JOptionPane.showMessageDialog(null, "The name is Invalid!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtStaffName.requestFocus(true);
					txtStaffName.selectAll();
				} else if (Checking.InvalidEmail(txtEmail.getText())) {
					JOptionPane.showMessageDialog(null, "The Email is Invalid!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtStaffName.requestFocus(true);
					txtStaffName.selectAll();
				} else if (Checking.isPhoneNo(txtPhone.getText())) {
					JOptionPane.showMessageDialog(null, "The Phone number is Invalid!", "Fail",
							JOptionPane.ERROR_MESSAGE);
					txtStaffName.requestFocus(true);
					txtStaffName.selectAll();
				} else {
					saveModel.setStaffid(staffid);
					saveModel.setStaffname(txtStaffName.getText());
					saveModel.setDepid(deptid);
					saveModel.setPostid(postid);
					saveModel.setPhone(txtPhone.getText());
					saveModel.setEmail(txtEmail.getText());
					saveModel.setAddress(txtAddress.getText());

					int result = sc.update(saveModel);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Updated Successfully", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						AutoID();
						showList();
						Clear();
					}
				}
			}
		});
		btnUpdate.setBounds(522, 356, 89, 23);
		btnUpdate.setForeground(new Color(47, 79, 79));
		btnUpdate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnUpdate.setBackground(new Color(102, 205, 170));
		add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure do you want to Delete?", "Warning",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					StaffModel sm = new StaffModel();
					sm.setStaffid(staffid);
					sc.delete(sm);
					AutoID();
					showList();
					Clear();
				}

			}
		});
		btnDelete.setBounds(449, 408, 89, 23);
		btnDelete.setForeground(new Color(47, 79, 79));
		btnDelete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnDelete.setBackground(new Color(102, 205, 170));
		add(btnDelete);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		btnClear.setBounds(582, 408, 89, 23);
		btnClear.setForeground(new Color(47, 79, 79));
		btnClear.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnClear.setBackground(new Color(102, 205, 170));
		add(btnClear);

		btnShowAll = new JButton("Show");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillcombo();
				Clear();
				showList();
			}
		});
		btnShowAll.setForeground(new Color(47, 79, 79));
		btnShowAll.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnShowAll.setBackground(new Color(102, 205, 170));
		btnShowAll.setBounds(650, 356, 89, 23);
		add(btnShowAll);

		lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(565, 56, 61, 14);
		add(lblNewLabel_1);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				showOne(textField.getText().trim());

			}
		});
		textField.setForeground(new Color(47, 79, 79));
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		textField.setBounds(633, 55, 106, 25);
		add(textField);
		textField.setColumns(10);

		lblNewLabel_4 = new JLabel("Position");
		lblNewLabel_4.setForeground(new Color(47, 79, 79));
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 317, 76, 14);
		add(lblNewLabel_4);

		cboPost = new JComboBox();
		cboPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.setPost((String) cboPost.getSelectedItem());
				postid = pc.searchPostID(pm);
			}
		});

		cboPost.setForeground(new Color(47, 79, 79));
		cboPost.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		cboPost.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));
		cboPost.setBounds(140, 314, 117, 25);
		add(cboPost);

		lblNewLabel_5 = new JLabel("Department");
		lblNewLabel_5.setForeground(new Color(47, 79, 79));
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(10, 359, 98, 17);
		add(lblNewLabel_5);

		cboDept = new JComboBox();
		cboDept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.setDeptname((String) cboDept.getSelectedItem());
				deptid = dc.searchDeptID(dm);
			}
		});
		cboDept.setForeground(new Color(47, 79, 79));
		cboDept.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		cboDept.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));
		cboDept.setBounds(140, 358, 117, 25);
		add(cboDept);

		lblNewLabel_6 = new JLabel("Email");
		lblNewLabel_6.setForeground(new Color(47, 79, 79));
		lblNewLabel_6.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(10, 400, 76, 14);
		add(lblNewLabel_6);

		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(47, 79, 79));
		txtEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtEmail.setBounds(140, 399, 116, 25);
		add(txtEmail);
		txtEmail.setColumns(10);

		lblNewLabel_7 = new JLabel("Phone No");
		lblNewLabel_7.setForeground(new Color(47, 79, 79));
		lblNewLabel_7.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(10, 441, 76, 14);
		add(lblNewLabel_7);

		txtPhone = new JTextField();
		txtPhone.setForeground(new Color(47, 79, 79));
		txtPhone.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtPhone.setColumns(10);
		txtPhone.setBounds(140, 438, 116, 25);
		add(txtPhone);

		lblNewLabel_8 = new JLabel("Address");
		lblNewLabel_8.setForeground(new Color(47, 79, 79));
		lblNewLabel_8.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(10, 485, 76, 14);
		add(lblNewLabel_8);
		
		lblStaffEntryAnd = new JLabel("  Staff Entry and List   ");
		lblStaffEntryAnd.setOpaque(true);
		lblStaffEntryAnd.setForeground(new Color(224, 255, 255));
		lblStaffEntryAnd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblStaffEntryAnd.setBackground(new Color(32, 178, 170));
		lblStaffEntryAnd.setBounds(0, 0, 779, 44);
		add(lblStaffEntryAnd);

		createTable();
		AutoID();
		fillcombo();
		showList();
		
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		txtAddress.setLineWrap(true);
		
		txtAddress.setBounds(140, 482, 117, 58);
		add(txtAddress);
	}

	public void setColoumnWidth(int index, int width) {

		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblStaff.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}

	public void showList() {
		String[] list = new String[7];
		ArrayList<StaffModel> alist = new ArrayList<>();
		dtm.setRowCount(0);
		alist = sc.selectAll();

		for (StaffModel sm : alist) {
			list[0] = sm.getStaffid();
			list[1] = sm.getStaffname();

			PostModel pm = new PostModel();
			pm.setPostid(sm.getPostid());
			list[2] = pc.searchPostName(pm);

			DeptModel dm = new DeptModel();
			dm.setDeptid(sm.getDepid());
			list[3] = dc.searchDeptName(dm);

			list[4] = sm.getPhone();
			list[5] = sm.getEmail();
			list[6] = sm.getAddress();

			dtm.addRow(list);
		}

	}
	
	public void showOne(String name) {
		String[] list = new String[7];
		ArrayList<StaffModel> alist = new ArrayList<>();
		StaffModel s = new StaffModel();
		s.setStaffname(name);
		dtm.setRowCount(0);
		alist = sc.selectOne(s);
		
		for(StaffModel sm: alist) {
			list[0] = sm.getStaffid();
			list[1] = sm.getStaffname();

			PostModel pm = new PostModel();
			pm.setPostid(sm.getPostid());
			list[2] = pc.searchPostName(pm);

			DeptModel dm = new DeptModel();
			dm.setDeptid(sm.getDepid());
			list[3] = dc.searchDeptName(dm);

			list[4] = sm.getPhone();
			list[5] = sm.getEmail();
			list[6] = sm.getAddress();

			dtm.addRow(list);
			
		}
	}

	public void createTable() {
		dtm.addColumn("Staff ID");
		dtm.addColumn("Staff Name");
		dtm.addColumn("Position");
		dtm.addColumn("Department");
		dtm.addColumn("Phone Number");
		dtm.addColumn("Email");
		dtm.addColumn("Address");

		tblStaff.setModel(dtm);
		setColoumnWidth(0, 100);
		setColoumnWidth(1, 130);
		setColoumnWidth(2, 150);
		setColoumnWidth(3, 120);
		setColoumnWidth(4, 110);
		setColoumnWidth(5, 150);
		setColoumnWidth(6, 150);

	}

	public void AutoID() {
		ClsDBConnection dbcon = new ClsDBConnection();
		try {
			lblStaffID.setText(dbcon.AutoID("staffid", "saleandservice.staff", "ST-"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Clear() {
		btnSave.setEnabled(true);
		AutoID();
		txtStaffName.setText("");
		cboPost.setSelectedIndex(0);
		cboDept.setSelectedIndex(0);
		txtEmail.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
		btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		fillcombo();
	}

	public void fillcombo() {
		SQLQueries.addComboBox("saleandservice.department", "deptname", cboDept);
		SQLQueries.addComboBox("saleandservice.post", "postname", cboPost);
	}
}
