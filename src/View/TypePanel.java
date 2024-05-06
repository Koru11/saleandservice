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
import Controller.TypeController;
import Model.TypeModel;

public class TypePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String TypeID=null;
	int r;
	private JTable tblType;
	private JScrollPane scrollPane_1;
	private JLabel lblN;
	private JLabel lblTypeID;
	private JLabel lblNewLabel_3;
	private JTextField txttypeName;
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
	String typeName;
	private JLabel lblTypeEntryAnd;
	/**
	 * Create the panel.
	 */
	public TypePanel() throws SQLException {
		setBounds(100, 100, 779, 502);
		contentPane = new JPanel();
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
		scrollPane_1.setBounds(0, 88, 779, 143);
		add(scrollPane_1);
		
		tblType = new JTable();
		tblType.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				r = tblType.getSelectedRow();

				TypeID = (String) tblType.getValueAt(r, 0);
				typeName = (String) tblType.getValueAt(r,1);
				lblTypeID.setText(TypeID);
				txttypeName.setText((String) tblType.getValueAt(r, 1));

				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);

			}
		});
		tblType.setBackground(new Color(192, 192, 192));
		tblType.setForeground(new Color(47, 79, 79));
		tblType.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		scrollPane_1.setViewportView(tblType);
		
		lblN = new JLabel("Type ID");
		lblN.setBounds(10, 257, 76, 23);
		lblN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblN.setForeground(new Color(47, 79, 79));
		add(lblN);
		
		lblTypeID = new JLabel("");
		lblTypeID.setBounds(140, 259, 117, 18);
		lblTypeID.setForeground(new Color(47, 79, 79));
		lblTypeID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblTypeID);
		
		lblNewLabel_3 = new JLabel("Type Name");
		lblNewLabel_3.setBounds(10, 311, 87, 19);
		lblNewLabel_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblNewLabel_3);
		
		txttypeName = new JTextField();
		txttypeName.setBounds(140, 311, 117, 25);
		txttypeName.setForeground(new Color(47, 79, 79));
		txttypeName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(txttypeName);
		txttypeName.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TypeModel bm = new TypeModel();
				TypeController bc = new TypeController();
				if(lblTypeID.getText().trim().equals("") || txttypeName.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null,"There is a blank field!","Fail", JOptionPane.ERROR_MESSAGE);
					txttypeName.requestFocus();
					txttypeName.selectAll();
				}else {
					bm.setTypeid(lblTypeID.getText().toString());
					bm.setTypename(txttypeName.getText().toString());
					if(Checking.IsValidName(bm.getTypename()) || (Checking.isAllNum(bm.getTypename()))) {
						JOptionPane.showMessageDialog(null,"Invalid Name","Invalid", JOptionPane.ERROR_MESSAGE);
						txttypeName.requestFocus();
						txttypeName.selectAll();
					}else {
						try {
							if(bc.isduplicate(bm)) {
								JOptionPane.showMessageDialog(null, "There is a same Type name!","Fail", JOptionPane.ERROR_MESSAGE);	
								txttypeName.requestFocus(true);
								txttypeName.selectAll();
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
		btnSave.setBounds(401, 309, 89, 23);
		btnSave.setBackground(new Color(102, 205, 170));
		btnSave.setForeground(new Color(47, 79, 79));
		btnSave.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(btnSave);
		
		lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(592, 56, 61, 14);
		add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener((KeyListener) new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					showListone(txtSearch.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		txtSearch.setForeground(new Color(47, 79, 79));
		txtSearch.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearch.setBounds(663, 53, 106, 25);
		add(txtSearch);
		txtSearch.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TypeModel bm = new TypeModel();
				TypeController bc = new TypeController();
				bm.setTypeid(lblTypeID.getText().toString());
				bm.setTypename(txttypeName.getText().toString());
				if (lblTypeID.getText().trim().toString().equals("")
						|| txttypeName.getText().trim().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
					txttypeName.requestFocus(true);
					txttypeName.selectAll();
				}else if(typeName.equals(txttypeName.getText())) {
					JOptionPane.showMessageDialog(null, "The name is the same from previous", "Fail", JOptionPane.ERROR_MESSAGE);
					txttypeName.requestFocus(true);
					txttypeName.selectAll();
				} else
					try {
						if (bc.isduplicate(bm)) {
							JOptionPane.showMessageDialog(null, "There is a Duplicate", "Fail", JOptionPane.ERROR_MESSAGE);
							txttypeName.requestFocus(true);
							txttypeName.selectAll();
						} else {

							if (Checking.IsValidName(bm.getTypename())) {
								JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid", JOptionPane.ERROR_MESSAGE);
								txttypeName.requestFocus(true);
								txttypeName.selectAll();
							} else {
								if (bc.isduplicate(bm)) {
									JOptionPane.showMessageDialog(null, "There is a same Type name!", "Fail",
											JOptionPane.ERROR_MESSAGE);
									txttypeName.requestFocus(true);
									txttypeName.selectAll();
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
		btnUpdate.setBounds(537, 309, 89, 23);
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
		btnShowAll.setBounds(654, 307, 89, 23);
		add(btnShowAll);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure do you want to Delete?", "Warning",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					delete(lblTypeID.getText());
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
		btnDelete.setBounds(468, 348, 89, 23);
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
		btnClear.setBounds(595, 348, 89, 23);
		add(btnClear);
		
		lblTypeEntryAnd = new JLabel(" Type Entry and List   ");
		lblTypeEntryAnd.setOpaque(true);
		lblTypeEntryAnd.setForeground(new Color(224, 255, 255));
		lblTypeEntryAnd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblTypeEntryAnd.setBackground(new Color(32, 178, 170));
		lblTypeEntryAnd.setBounds(0, 0, 779, 44);
		add(lblTypeEntryAnd);
		
		createTable();
		AutoID();
		Clear();
		showList();
	}
	public void setColoumnWidth(int index,int width) {
		
		DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblType.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}
	
	public void createTable() {
		dtm.addColumn("Type ID");
		dtm.addColumn("Type Name");
		tblType.setModel(dtm);
		setColoumnWidth(0,30);
		setColoumnWidth(1,60);
	}
	public void AutoID(){
		ClsDBConnection dbcon = new ClsDBConnection();

		try {
			lblTypeID.setText(dbcon.AutoID("TypeID", "saleandservice.Type","TY-"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Clear() {
		btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		txttypeName.setText("");
		txttypeName.requestFocus(true);
		AutoID();
		
	}
	public void showList() throws SQLException{
		String data[] = new String[2];
		TypeController bc = new TypeController();
		List<TypeModel> list = bc.selectall();
		dtm.setRowCount(0);
		for(TypeModel bm:list) {
			data[0] = bm.getTypeid();
			data[1] = bm.getTypename();
			dtm.addRow(data);
		}
	}
	
	public void showListone(String name) throws SQLException{
		String data[]= new String[2];
		TypeModel b = new TypeModel();
		b.setTypename(name.trim());
		TypeController bc = new TypeController();
		List<TypeModel> list = bc.selectone(b);
		dtm.setRowCount(0);
		for(TypeModel bm:list) {
			data[0]=bm.getTypeid();
			data[1]=bm.getTypename();
			dtm.addRow(data);
		}
	}
	public void delete(String id) {
		TypeController bc = new TypeController();
		TypeModel bm = new TypeModel();
		bm.setTypeid(id);
		bc.delete(bm);

	}
	
}
