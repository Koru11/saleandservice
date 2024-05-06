package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Connection.Checking;
import Connection.date;
import Controller.BrandController;
import Controller.CustomerController;
import Controller.ItemController;
import Controller.SaleOrderController;
import Controller.SaleOrderDetailController;
import Controller.ServiceController;
import Controller.StaffController;
import Controller.TypeController;
import Model.BrandModel;
import Model.CustomerModel;
import Model.ItemModel;
import Model.SaleModel;
import Model.SaleOrderDetailModel;
import Model.SaleOrderModel;
import Model.ServiceModel;
import Model.StaffModel;
import Model.TypeModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SaleView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	DefaultTableModel idtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String BrandID = null;
	int r;
	String selectedSerial;

	private JScrollPane scrollPane_1;
	private JMenuItem mntmNewMenuItem_3;
	String strdataitem[] = new String[10];
	String strquery[] = new String[7];
	Vector vid = new Vector();
	Vector vamount = new Vector();
	Vector vserial = new Vector();
	private JLabel lblTotal;
	private boolean single;
	private BrandController bc = new BrandController();
	private TypeController tc = new TypeController();
	private ItemController ic = new ItemController();
	private ItemModel im = new ItemModel();
	private CustomerModel cm = new CustomerModel();
	private CustomerController cc = new CustomerController();
	private StaffController sc = new StaffController();
	private int row;
	private JTable tblSale = new JTable();

	SaleOrderDetailController sodc = new SaleOrderDetailController();
	private JLabel lblNewLabel;
	private JTextField textField;
	private JLabel lblresult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleView frame = new SaleView();
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
	public SaleView() {
		setTitle("Sale");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ButtonGroup group = new ButtonGroup();
		setBounds(0, 0, 1294, 690);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 139));
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(12, 10, 1259, 600);
		contentPane.add(scrollPane_1);

//		tblSale.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				row = tblSale.getSelectedRow();
//				txtItemID.setText((String) tblSale.getValueAt(row, 0));
//				txtItemName.setText((String) tblSale.getValueAt(row, 1));
//				cboBrandName.setSelectedItem((String) tblSale.getValueAt(row, 2));
//				cboTypeName.setSelectedItem((String) tblSale.getValueAt(row, 3));
//				txtPrice.setText((String) tblSale.getValueAt(row, 4));
//				txtWarranty.setText((String) tblSale.getValueAt(row, 5));
//				txtRemark.setText((String) tblSale.getValueAt(row, 6));
//				txtSerial.setText((String) tblSale.getValueAt(row, 7));
//				btnAdd.setEnabled(false);
//				btnUpdate.setEnabled(true);
//				btnDelete.setEnabled(true);
//			}
//		});

		tblSale.setBackground(new Color(192, 192, 192));
		tblSale.setForeground(new Color(47, 79, 79));
		tblSale.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		tblSale.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(tblSale);

		lblTotal = new JLabel("");
		lblTotal.setForeground(new Color(47, 79, 79));
		lblTotal.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblTotal.setBounds(585, 636, 115, 23);
		contentPane.add(lblTotal);
		
		lblNewLabel = new JLabel("Item Name");
		lblNewLabel.setForeground(new Color(47, 79, 79));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 616, 89, 25);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					showListbyname(textField.getText());
					lblresult.setText(showCount(textField.getText())+" results have been found");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		textField.setForeground(new Color(47, 79, 79));
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(113, 615, 117, 25);
		contentPane.add(textField);
		
		lblresult = new JLabel("");
		lblresult.setForeground(new Color(47, 79, 79));
		lblresult.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblresult.setBounds(260, 616, 235, 25);
		contentPane.add(lblresult);


//		Clear();
//		showList();
		date d = new date();


		createTable();
		try {
			showList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

	public void setColoumnWidth(int index, int width) {

		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblSale.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}
	
	public void createTable() {
		dtm.addColumn("SaleOrderID");
		dtm.addColumn("ItemID");
		dtm.addColumn("Item Name");
		dtm.addColumn("Customer ID");
		dtm.addColumn("Customer Name");
		dtm.addColumn("Serial");
		dtm.addColumn("Address");
		dtm.addColumn("Phone");
		dtm.addColumn("Email");
		dtm.addColumn("Brand ID");
		dtm.addColumn("Type ID");
		dtm.addColumn("Current Price");
		dtm.addColumn("Warranty");
		dtm.addColumn("Remark");
		tblSale.setModel(dtm);
		setColoumnWidth(0, 100);
		setColoumnWidth(1, 100);
		setColoumnWidth(2, 100);
		setColoumnWidth(3, 100);
		setColoumnWidth(4, 100);
		setColoumnWidth(5, 100);
		setColoumnWidth(6, 150);
		setColoumnWidth(7, 150);
		setColoumnWidth(8, 150);
		setColoumnWidth(9, 150);
		setColoumnWidth(10, 150);
		setColoumnWidth(11, 150);
		setColoumnWidth(12, 150);
		setColoumnWidth(13, 150);


	}
	
	public void showList() throws SQLException {
		String data[] = new String[14];
		SaleOrderDetailController bc = new SaleOrderDetailController();

		List<SaleModel> list = bc.showAll();
		dtm.setRowCount(0);
		for (SaleModel bm : list) {
			data[0] = bm.getSaleorderid();
			data[1] = bm.getItemid();
			data[2] = bm.getItemname();
			data[3] = bm.getCustomerid();
			data[4] = bm.getCustomername();
			data[5] = bm.getSerial();
			data[6] = bm.getAddress();
			data[7] = bm.getPhone();
			data[8] = bm.getEmail();
			data[9] = bm.getBrandid();
			data[10] = bm.getTypeid();
			data[11] = bm.getCurrentprice()+"";
			data[12] = bm.getWarranty();
			data[13] = bm.getRemark();

			dtm.addRow(data);
		}
	}
	
	public void showListbyname(String name) throws SQLException {
		String data[] = new String[14];
		SaleOrderDetailController bc = new SaleOrderDetailController();

		List<SaleModel> list = bc.showAllbyname(name);
		dtm.setRowCount(0);
		for (SaleModel bm : list) {
			data[0] = bm.getSaleorderid();
			data[1] = bm.getItemid();
			data[2] = bm.getItemname();
			data[3] = bm.getCustomerid();
			data[4] = bm.getCustomername();
			data[5] = bm.getSerial();
			data[6] = bm.getAddress();
			data[7] = bm.getPhone();
			data[8] = bm.getEmail();
			data[9] = bm.getBrandid();
			data[10] = bm.getTypeid();
			data[11] = bm.getCurrentprice()+"";
			data[12] = bm.getWarranty();
			data[13] = bm.getRemark();

			dtm.addRow(data);
		}
	}
	
	public int showCount(String name) throws SQLException {
		String data[] = new String[14];
		SaleOrderDetailController bc = new SaleOrderDetailController();

		int i = bc.showCount(name);
		return i;
	}

}
