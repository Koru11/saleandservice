package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Connection.Checking;
import Connection.ClsDBConnection;
import Controller.DeptController;
import Model.DeptModel;

public class DeptPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String DeptID=null;
	int r;
	private JTable tblDept;
	private JScrollPane scrollPane_1;
	private JLabel lblN;
	private JLabel lblDeptID;
	private JLabel lblNewLabel_3;
	private JTextField txtdeptName;
	private JButton btnSave;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
	private JMenuItem mntmNewMenuItem_6;
	private JLabel lblNewLabel_1;
	private JTextField txtSearch;
	private JButton btnUpdate;
	private JButton btnShowAll;
	private JButton btnDelete;
	private JButton btnClear;
	String deptName;
	private JLabel lblDepartmentEntryAnd;
	/**
	 * Create the panel.
	 */
	public DeptPanel() throws SQLException{
		setBounds(100, 100, 765, 502);
		setForeground(new Color(0, 0, 139));
		setBackground(new Color(224, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("List");
		lblNewLabel_2.setBounds(10, 56, 76, 14);
		lblNewLabel_2.setForeground(new Color(105, 105, 105));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblNewLabel_2);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 88, 763, 143);
		add(scrollPane_1);
		
		tblDept = new JTable();
		tblDept.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				r = tblDept.getSelectedRow();

				DeptID = (String) tblDept.getValueAt(r, 0);
				deptName = (String) tblDept.getValueAt(r,1);
				lblDeptID.setText(DeptID);
				txtdeptName.setText((String) tblDept.getValueAt(r, 1));

				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);

			}
		});
		tblDept.setBackground(new Color(192, 192, 192));
		tblDept.setForeground(new Color(47, 79, 79));
		tblDept.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		scrollPane_1.setViewportView(tblDept);
		
		lblN = new JLabel("Department ID");
		lblN.setBounds(10, 257, 120, 19);
		lblN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblN.setForeground(new Color(47, 79, 79));
		add(lblN);
		
		lblDeptID = new JLabel("");
		lblDeptID.setBounds(145, 257, 117, 18);
		lblDeptID.setForeground(new Color(47, 79, 79));
		lblDeptID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblDeptID);
		
		lblNewLabel_3 = new JLabel("Department Name");
		lblNewLabel_3.setBounds(10, 296, 131, 17);
		lblNewLabel_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblNewLabel_3);
		
		txtdeptName = new JTextField();
		txtdeptName.setBounds(145, 294, 117, 25);
		txtdeptName.setForeground(new Color(47, 79, 79));
		txtdeptName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(txtdeptName);
		txtdeptName.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeptModel bm = new DeptModel();
				DeptController bc = new DeptController();
				if(lblDeptID.getText().trim().equals("") || txtdeptName.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null,"There is a blank field!","Fail", JOptionPane.ERROR_MESSAGE);
					txtdeptName.requestFocus();
					txtdeptName.selectAll();
				}else {
					bm.setDeptid(lblDeptID.getText().toString());
					bm.setDeptname(txtdeptName.getText().toString());
					if(Checking.IsValidName(bm.getDeptname()) || (Checking.isAllNum(bm.getDeptname()))) {
						JOptionPane.showMessageDialog(null,"Invalid Name","Invalid", JOptionPane.ERROR_MESSAGE);
						txtdeptName.requestFocus();
						txtdeptName.selectAll();
					}else {
						try {
							if(bc.isduplicate(bm)) {
								JOptionPane.showMessageDialog(null, "There is a same Department name!","Fail", JOptionPane.ERROR_MESSAGE);	
								txtdeptName.requestFocus(true);
								txtdeptName.selectAll();
							}else {
								int rs = bc.insert(bm);
								if(rs==1) {
									JOptionPane.showMessageDialog(null,"Save Successfully","Successfully", JOptionPane.INFORMATION_MESSAGE);
									AutoID();
									showList();
									Clear();
								}
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnSave.setBounds(424, 293, 89, 23);
		btnSave.setBackground(new Color(102, 205, 170));
		btnSave.setForeground(new Color(47, 79, 79));
		btnSave.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(btnSave);

		

		
		lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(565, 56, 61, 14);
		add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener((KeyListener) new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					showListone(txtSearch.getText().trim());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		txtSearch.setForeground(new Color(47, 79, 79));
		txtSearch.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearch.setBounds(633, 55, 106, 25);
		add(txtSearch);
		txtSearch.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeptModel bm = new DeptModel();
				DeptController bc = new DeptController();
				bm.setDeptid(lblDeptID.getText().toString());
				bm.setDeptname(txtdeptName.getText().toString());
				if (lblDeptID.getText().trim().toString().equals("")
						|| txtdeptName.getText().trim().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtdeptName.requestFocus(true);
					txtdeptName.selectAll();
				}else if(deptName.equals(txtdeptName.getText())) {
					JOptionPane.showMessageDialog(null, "The name is the same from previous", "Fail", JOptionPane.ERROR_MESSAGE);
					txtdeptName.requestFocus(true);
					txtdeptName.selectAll();
				} else
					try {
						if (bc.isduplicate(bm)) {
							JOptionPane.showMessageDialog(null, "There is a Duplicate", "Fail", JOptionPane.ERROR_MESSAGE);
							txtdeptName.requestFocus(true);
							txtdeptName.selectAll();
						} else {

							if (Checking.IsValidName(bm.getDeptname())) {
								JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid", JOptionPane.ERROR_MESSAGE);
								txtdeptName.requestFocus(true);
								txtdeptName.selectAll();
							} else {
								if (bc.isduplicate(bm)) {
									JOptionPane.showMessageDialog(null, "There is a same Department name!", "Fail",
											JOptionPane.ERROR_MESSAGE);
									txtdeptName.requestFocus(true);
									txtdeptName.selectAll();
								} else {
									int rs = bc.update(bm);
									if (rs == 1) {
										JOptionPane.showMessageDialog(null, "Update Successfully", "Successfully",
												JOptionPane.INFORMATION_MESSAGE);
										AutoID();
										Clear();
										showList();
									}
								}
							}
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

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
		});
		btnUpdate.setForeground(new Color(47, 79, 79));
		btnUpdate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnUpdate.setEnabled(false);
		btnUpdate.setBackground(new Color(102, 205, 170));
		btnUpdate.setBounds(545, 293, 89, 23);
		add(btnUpdate);
		
		btnShowAll = new JButton("Show");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtSearch.setText("");
			}
		});
		btnShowAll.setForeground(new Color(47, 79, 79));
		btnShowAll.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnShowAll.setEnabled(true);
		btnShowAll.setBackground(new Color(102, 205, 170));
		btnShowAll.setBounds(666, 293, 89, 23);
		add(btnShowAll);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure do you want to Delete?", "Warning",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					delete(lblDeptID.getText());
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
		btnDelete.setEnabled(false);
		btnDelete.setBackground(new Color(102, 205, 170));
		btnDelete.setBounds(482, 339, 89, 23);
		add(btnDelete);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		btnClear.setForeground(new Color(47, 79, 79));
		btnClear.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnClear.setEnabled(true);
		btnClear.setBackground(new Color(102, 205, 170));
		btnClear.setBounds(612, 339, 89, 23);
		add(btnClear);
		
		lblDepartmentEntryAnd = new JLabel("  Department Entry and List   ");
		lblDepartmentEntryAnd.setOpaque(true);
		lblDepartmentEntryAnd.setForeground(new Color(224, 255, 255));
		lblDepartmentEntryAnd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblDepartmentEntryAnd.setBackground(new Color(32, 178, 170));
		lblDepartmentEntryAnd.setBounds(0, 0, 763, 44);
		add(lblDepartmentEntryAnd);
		
		createTable();
		AutoID();
		Clear();
		showList();
	}
	public void setColoumnWidth(int index,int width) {
		
		DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblDept.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}
	
	public void createTable() {
		dtm.addColumn("Department ID");
		dtm.addColumn("Department Name");
		tblDept.setModel(dtm);
		setColoumnWidth(0,30);
		setColoumnWidth(1,60);
	}
	public void AutoID(){
		ClsDBConnection dbcon = new ClsDBConnection();

		try {
			lblDeptID.setText(dbcon.AutoID("deptid", "saleandservice.department","DP-"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Clear() {
		btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		txtdeptName.setText("");
		txtdeptName.requestFocus(true);
		AutoID();
	}
	public void showList() throws SQLException{
		String data[] = new String[2];
		DeptController bc = new DeptController();
		List<DeptModel> list = bc.selectall();
		dtm.setRowCount(0);
		for(DeptModel bm:list) {
			data[0] = bm.getDeptid();
			data[1] = bm.getDeptname();
			dtm.addRow(data);
		}
	}
	
	public void showListone(String name) throws SQLException{
		String data[]= new String[2];
		DeptModel b = new DeptModel();
		b.setDeptname(name);
		DeptController bc = new DeptController();
		List<DeptModel> list = bc.selectone(b);
		dtm.setRowCount(0);
		for(DeptModel bm:list) {
			data[0]=bm.getDeptid();
			data[1]=bm.getDeptname();
			dtm.addRow(data);
		}
	}
	public void delete(String id) {
		DeptController bc = new DeptController();
		DeptModel bm = new DeptModel();
		bm.setDeptid(id);
		bc.delete(bm);

	}
}
