package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import Controller.PostController;
import Model.PostModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PostPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String postid = null;
	int r;
	private JTable tblPost;
	private JScrollPane scrollPane_1;
	private JLabel lblN;
	private JLabel lblPostID;
	private JLabel lblNewLabel_3;
	private JTextField txtPostName;
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
	private JTextField txtSearch;
	private int row;
	private String post;
	PostController pc = new PostController();
	private JLabel lblPostEntryAnd;

	/**
	 * Create the panel.
	 */
	public PostPanel() throws SQLException {
		PostController pc = new PostController();
		PostModel pm = new PostModel();

		setBounds(100, 100, 765, 502);
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

		scrollPane_1.setBounds(0, 88, 763, 143);
		add(scrollPane_1);

		tblPost = new JTable();
		tblPost.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tblPost.getSelectedRow();
				post = (String) tblPost.getValueAt(row, 1);
				postid = (String) tblPost.getValueAt(row, 0);
				txtPostName.setText(post);

				lblPostID.setText(postid);

				System.out.println();

				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
			}
		});
		tblPost.setBackground(new Color(192, 192, 192));
		tblPost.setForeground(new Color(105, 105, 105));
		tblPost.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		scrollPane_1.setViewportView(tblPost);

		lblN = new JLabel("Position ID");
		lblN.setBounds(10, 257, 76, 19);
		lblN.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblN.setForeground(new Color(47, 79, 79));
		add(lblN);

		lblPostID = new JLabel("");
		lblPostID.setBounds(140, 257, 117, 18);
		lblPostID.setForeground(new Color(47, 79, 79));
		lblPostID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblPostID);

		lblNewLabel_3 = new JLabel("Position");
		lblNewLabel_3.setBounds(10, 311, 87, 14);
		lblNewLabel_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(lblNewLabel_3);

		txtPostName = new JTextField();
		txtPostName.setBounds(140, 311, 117, 25);
		txtPostName.setForeground(new Color(47, 79, 79));
		txtPostName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(txtPostName);
		txtPostName.setColumns(10);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PostModel bm = new PostModel();
				PostController bc = new PostController();
				if (lblPostID.getText().trim().equals("") || txtPostName.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtPostName.requestFocus();
					txtPostName.selectAll();
				} else {
					bm.setPostid(lblPostID.getText().toString());
					bm.setPost(txtPostName.getText().toString());
					if (Checking.IsValidName(bm.getPost()) || Checking.isAllNum(bm.getPost())) {
						JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid", JOptionPane.ERROR_MESSAGE);
						txtPostName.requestFocus();
						txtPostName.selectAll();
					} else {
						if (bc.hasDuplicate(bm)) {
							JOptionPane.showMessageDialog(null, "There is a same brand name!", "Fail",
									JOptionPane.ERROR_MESSAGE);
							txtPostName.requestFocus(true);
							txtPostName.selectAll();
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
					}
				}

			}
		});
		btnSave.setBounds(377, 307, 89, 23);
		btnSave.setBackground(new Color(102, 205, 170));
		btnSave.setForeground(new Color(47, 79, 79));
		btnSave.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		add(btnSave);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PostModel bm = new PostModel();
				PostController bc = new PostController();
				if (lblPostID.getText().trim().equals("") || txtPostName.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
					txtPostName.requestFocus();
					txtPostName.selectAll();
				} else {
					bm.setPostid(lblPostID.getText());
					bm.setPost(txtPostName.getText().toString());
					if (Checking.IsValidName(bm.getPost()) || Checking.isAllNum(bm.getPost())) {
						JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid", JOptionPane.ERROR_MESSAGE);
						txtPostName.requestFocus();
						txtPostName.selectAll();
					} else {
						if (bc.hasDuplicate(bm)) {
							JOptionPane.showMessageDialog(null, "There is a same brand name!", "Fail",
									JOptionPane.ERROR_MESSAGE);
							txtPostName.requestFocus(true);
							txtPostName.selectAll();
						} else {
							System.out.println("ran");
							int rs = bc.update(bm);
							if (rs == 1) {
								JOptionPane.showMessageDialog(null, "Updated Successfully", "Successfully",
										JOptionPane.INFORMATION_MESSAGE);
								AutoID();
								showList();
								Clear();

								btnUpdate.setEnabled(false);
								btnDelete.setEnabled(false);
								btnSave.setEnabled(true);
							}
						}
					}
				}

			}
		});
		btnUpdate.setBounds(513, 307, 89, 23);
		btnUpdate.setForeground(new Color(47, 79, 79));
		btnUpdate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnUpdate.setBackground(new Color(102, 205, 170));
		add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure do you want to Delete?", "Warning",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					pm.setPostid(postid);
					pc.delete(pm);

					showList();

					AutoID();
					btnUpdate.setEnabled(false);
					btnDelete.setEnabled(false);
					btnSave.setEnabled(true);
				}

			}
		});
		btnDelete.setBounds(451, 359, 89, 23);
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
		btnClear.setBounds(573, 359, 89, 23);
		btnClear.setForeground(new Color(47, 79, 79));
		btnClear.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnClear.setBackground(new Color(102, 205, 170));
		add(btnClear);

		btnShowAll = new JButton("Show");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showList();
			}
		});
		btnShowAll.setForeground(new Color(47, 79, 79));
		btnShowAll.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnShowAll.setBackground(new Color(102, 205, 170));
		btnShowAll.setBounds(650, 307, 89, 23);
		add(btnShowAll);

		lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(105, 105, 105));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(565, 56, 61, 14);
		add(lblNewLabel_1);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					showListOne(txtSearch.getText().trim());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtSearch.setForeground(new Color(105, 105, 105));
		txtSearch.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearch.setBounds(633, 55, 106, 25);
		add(txtSearch);
		txtSearch.setColumns(10);

		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnSave.setEnabled(true);
		
		lblPostEntryAnd = new JLabel("  Post Entry and List   ");
		lblPostEntryAnd.setOpaque(true);
		lblPostEntryAnd.setForeground(new Color(224, 255, 255));
		lblPostEntryAnd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblPostEntryAnd.setBackground(new Color(32, 178, 170));
		lblPostEntryAnd.setBounds(0, 0, 763, 44);
		add(lblPostEntryAnd);

		createTable();
		showList();

		AutoID();
	}

	public void setColoumnWidth(int index, int width) {

		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblPost.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}

	public void createTable() {
		dtm.addColumn("Post ID");
		dtm.addColumn("Position");

		tblPost.setModel(dtm);
		setColoumnWidth(0, 30);
		setColoumnWidth(1, 60);

	}

	public void AutoID() {
		ClsDBConnection dbcon = new ClsDBConnection();

		try {
			lblPostID.setText(dbcon.AutoID("postid", "saleandservice.post", "PO-"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Clear() {
		btnSave.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		txtPostName.setText("");
		txtPostName.requestFocus(true);
		AutoID();
	}

	public void showList() {
		String data[] = new String[5];
		PostController pc = new PostController();
		List<PostModel> list = pc.selectAll();
		dtm.setRowCount(0);
		for (PostModel bm : list) {
			data[0] = bm.getPostid();
			data[1] = bm.getPost();

			dtm.addRow(data);
		}
	};

	public void showListOne(String name) throws SQLException {
		String data[] = new String[5];
		PostModel pm = new PostModel();
		pm.setPost(name);
		List<PostModel> list = pc.selectOne(pm);
		dtm.setRowCount(0);
		for (PostModel bm : list) {
			data[0] = bm.getPostid();
			data[1] = bm.getPost();

			dtm.addRow(data);
		}

	}

}