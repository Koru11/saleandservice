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

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.sun.tools.javac.Main;

import Connection.Checking;
import Connection.ClsDBConnection;
import Controller.BrandController;
import Model.BrandModel;

import java.awt.Color;
import java.awt.Dimension;

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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BrandPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String BrandID = null;
	int r;
	private JTable tblBrand;
	private JScrollPane scrollPane_1;
	private JLabel lblN;
	private JLabel lblBrandID;
	private JLabel lblNewLabel_3;
	private JTextField txtBrandName;
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
	String brandName;

	/**
	 * Launch the application.
	 */

	// JFrame Change
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BrandView frame = new BrandView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */

	public BrandPanel() throws SQLException {
		setBackground(new Color(224, 255, 255));
//		FlatLightLaf.setup();

		setBounds(100, 100, 764, 502);
		contentPane = new JPanel();

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

		tblBrand = new JTable();
		tblBrand.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				r = tblBrand.getSelectedRow();

				BrandID = (String) tblBrand.getValueAt(r, 0);
				brandName = (String) tblBrand.getValueAt(r, 1);
				lblBrandID.setText(BrandID);
				txtBrandName.setText((String) tblBrand.getValueAt(r, 1));

				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);

			}
		});
		tblBrand.setBackground(new Color(192, 192, 192));
		tblBrand.setForeground(new Color(47, 79, 79));
		tblBrand.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		scrollPane_1.setViewportView(tblBrand);

		lblN = new JLabel("Brand ID");
		lblN.setBounds(10, 257, 76, 19);
		lblN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblN.setForeground(new Color(47, 79, 79));
		add(lblN);

		lblBrandID = new JLabel("");
		lblBrandID.setBounds(140, 257, 117, 18);
		lblBrandID.setForeground(new Color(47, 79, 79));
		lblBrandID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblBrandID);

		lblNewLabel_3 = new JLabel("Brand Name");
		lblNewLabel_3.setBounds(10, 311, 87, 14);
		lblNewLabel_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblNewLabel_3);

		txtBrandName = new JTextField();
		txtBrandName.setBounds(140, 311, 117, 25);
		txtBrandName.setForeground(new Color(47, 79, 79));
		txtBrandName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(txtBrandName);
		txtBrandName.setColumns(10);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BrandModel bm = new BrandModel();
				BrandController bc = new BrandController();
				if (lblBrandID.getText().trim().equals("") || txtBrandName.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtBrandName.requestFocus();
					txtBrandName.selectAll();
				} else {
					bm.setBrandid(lblBrandID.getText().toString());
					bm.setBrandname(txtBrandName.getText().toString());
					if (Checking.IsValidName(bm.getBrandname()) || (Checking.isAllNum(bm.getBrandname()))) {
						JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid", JOptionPane.ERROR_MESSAGE);
						txtBrandName.requestFocus();
						txtBrandName.selectAll();
					} else {
						try {
							if (bc.isduplicate(bm)) {
								JOptionPane.showMessageDialog(null, "There is a same brand name!", "Fail",
										JOptionPane.ERROR_MESSAGE);
								txtBrandName.requestFocus(true);
								txtBrandName.selectAll();
							} else {
								int rs = bc.insert(bm);
								if (rs == 1) {
									JOptionPane.showMessageDialog(null, "Save Successfully", "Successfully",
											JOptionPane.INFORMATION_MESSAGE);
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
		btnSave.setBounds(396, 307, 89, 23);
		btnSave.setBackground(new Color(102, 205, 170));
		btnSave.setForeground(new Color(47, 79, 79));

		;
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
				BrandModel bm = new BrandModel();
				BrandController bc = new BrandController();
				bm.setBrandid(lblBrandID.getText().toString());
				bm.setBrandname(txtBrandName.getText().toString());
				if (lblBrandID.getText().trim().toString().equals("")
						|| txtBrandName.getText().trim().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtBrandName.requestFocus(true);
					txtBrandName.selectAll();
				} else if (brandName.equals(txtBrandName.getText())) {
					JOptionPane.showMessageDialog(null, "The name is the same from previous", "Fail",
							JOptionPane.ERROR_MESSAGE);
					txtBrandName.requestFocus(true);
					txtBrandName.selectAll();
				} else
					try {
						if (bc.isduplicate(bm)) {
							JOptionPane.showMessageDialog(null, "There is a Duplicate", "Fail",
									JOptionPane.ERROR_MESSAGE);
							txtBrandName.requestFocus(true);
							txtBrandName.selectAll();
						} else {

							if (Checking.IsValidName(bm.getBrandname())) {
								JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid",
										JOptionPane.ERROR_MESSAGE);
								txtBrandName.requestFocus(true);
								txtBrandName.selectAll();
							} else {
								if (bc.isduplicate(bm)) {
									JOptionPane.showMessageDialog(null, "There is a same brand name!", "Fail",
											JOptionPane.ERROR_MESSAGE);
									txtBrandName.requestFocus(true);
									txtBrandName.selectAll();
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
		btnUpdate.setBounds(528, 307, 89, 23);
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
		btnShowAll.setBounds(665, 307, 89, 23);
		add(btnShowAll);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure do you want to Delete?", "Warning",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					delete(lblBrandID.getText());
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
		btnDelete.setBounds(466, 351, 89, 23);
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
		btnClear.setBounds(596, 351, 89, 23);
		add(btnClear);

		JLabel lblNewLabel = new JLabel(" Brand Entry and List   ");
		lblNewLabel.setBounds(0, 0, 763, 44);
		add(lblNewLabel);
		lblNewLabel.setForeground(new Color(224, 255, 255));
		lblNewLabel.setBackground(new Color(32, 178, 170));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setOpaque(true);

		createTable();
		AutoID();
		Clear();
		showList();
	}

	public void setColoumnWidth(int index, int width) {

		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblBrand.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}

	public void createTable() {
		dtm.addColumn("Brand ID");
		dtm.addColumn("Brand Name");
		tblBrand.setModel(dtm);
		setColoumnWidth(0, 30);
		setColoumnWidth(1, 60);
	}

	public void AutoID() {
		ClsDBConnection dbcon = new ClsDBConnection();

		try {
			lblBrandID.setText(dbcon.AutoID("brandid", "saleandservice.brand", "BR-"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Clear() {
		btnSave.setEnabled(true);
		txtBrandName.requestFocus(true);
		txtBrandName.setText("");
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		AutoID();
	}

	public void showList() throws SQLException {
		String data[] = new String[2];
		BrandController bc = new BrandController();
		List<BrandModel> list = bc.selectall();
		dtm.setRowCount(0);
		for (BrandModel bm : list) {
			data[0] = bm.getBrandid();
			data[1] = bm.getBrandname();
			dtm.addRow(data);
		}
	}

	public void showListone(String name) throws SQLException {
		String data[] = new String[2];
		BrandModel b = new BrandModel();
		b.setBrandname(name);
		BrandController bc = new BrandController();
		List<BrandModel> list = bc.selectone(b);
		dtm.setRowCount(0);
		for (BrandModel bm : list) {
			data[0] = bm.getBrandid();
			data[1] = bm.getBrandname();
			dtm.addRow(data);
		}
	}

	public void delete(String id) {
		BrandController bc = new BrandController();
		BrandModel bm = new BrandModel();
		bm.setBrandid(id);
		bc.delete(bm);

	}
}
