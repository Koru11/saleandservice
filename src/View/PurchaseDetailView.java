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
import Controller.BrandController;
import Controller.PurchaseDetailController;
import Model.BrandModel;
import Model.PurchaseDetailModel;

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

public class PurchaseDetailView extends JFrame {

	private JPanel contentPane;
	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String BrandID=null;
	int r;
	private JTable tblDetail;
	private JScrollPane scrollPane_1;
	private JMenuItem mntmNewMenuItem_3;
	String brandName;
	private JTextField txtSearch;
	private JButton btnShowAll;
	private JLabel lblNewLabel_2;
	private JLabel lblPurchasingList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseDetailView frame = new PurchaseDetailView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public PurchaseDetailView() throws SQLException {
		setTitle("Purchase Detail");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 779, 502);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 139));
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 88, 763, 365);
		contentPane.add(scrollPane_1);
		
		tblDetail = new JTable();
		
		tblDetail.setBackground(new Color(192, 192, 192));
		tblDetail.setForeground(new Color(47, 79, 79));
		tblDetail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		scrollPane_1.setViewportView(tblDetail);
		
		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(473, 56, 61, 21);
		contentPane.add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				showListOne(txtSearch.getText());
			}
		});
		txtSearch.setForeground(new Color(47, 79, 79));
		txtSearch.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtSearch.setColumns(10);
		txtSearch.setBounds(544, 57, 106, 25);
		contentPane.add(txtSearch);
		
		btnShowAll = new JButton("Show");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showList();
			}
		});
		btnShowAll.setForeground(new Color(47, 79, 79));
		btnShowAll.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnShowAll.setEnabled(true);
		btnShowAll.setBackground(new Color(102, 205, 170));
		btnShowAll.setBounds(660, 56, 89, 23);
		contentPane.add(btnShowAll);
		
		lblNewLabel_2 = new JLabel("Purchase List");
		lblNewLabel_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 56, 112, 21);
		contentPane.add(lblNewLabel_2);
		
		lblPurchasingList = new JLabel("  Purchased List");
		lblPurchasingList.setOpaque(true);
		lblPurchasingList.setForeground(new Color(224, 255, 255));
		lblPurchasingList.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblPurchasingList.setBackground(new Color(32, 178, 170));
		lblPurchasingList.setBounds(0, 0, 763, 44);
		contentPane.add(lblPurchasingList);
		
		this.setMenu();
		
		createTable();
		//AutoID();
		//Clear();
		showList();
	}
	public void setColoumnWidth(int index,int width) {
		
		DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblDetail.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}
	
	 public void createTable()
		{
		     dtm.addColumn("Purchase Id");
		     dtm.addColumn("Item Name");
		     dtm.addColumn("Purchase_price"); 
		     dtm.addColumn("Purchase_qty");
		     
		     tblDetail.setModel(dtm);
		     setColoumnWidth(0,60);
		     setColoumnWidth(1,60);
		     setColoumnWidth(2,60);
		     setColoumnWidth(3,60);
		    
		     
	    }
	
	public void Clear() {
	}
	   public void showList(){
	    	String data[] = new String[4];
	    	PurchaseDetailController pdc = new PurchaseDetailController();
	    	try {
				List<PurchaseDetailModel> list = pdc.showAll();
				dtm.setRowCount(0);
				for(PurchaseDetailModel pdm:list) {
					data[0] = pdm.getPurchaseid();
					data[1] = pdm.getItemName();
					data[2] = pdm.getPurchaseprice()+"";
					data[3] = pdm.getPurchaseqty()+"";
					dtm.addRow(data);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public void showListOne(String name){
	    	String data[] = new String[4];
	    	PurchaseDetailController pdc = new PurchaseDetailController();
	    	PurchaseDetailModel p = new PurchaseDetailModel();
	    	p.setItemName(name);
	    	try {
				List<PurchaseDetailModel> list = pdc.showOne(p);
				dtm.setRowCount(0);
				for(PurchaseDetailModel pdm:list) {
					data[0] = pdm.getPurchaseid();
					data[1] = pdm.getItemName();
					data[2] = pdm.getPurchaseprice()+"";
					data[3] = pdm.getPurchaseqty()+"";
					dtm.addRow(data);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	public void delete(String id) {
		BrandController bc = new BrandController();
		BrandModel bm = new BrandModel();
		bm.setBrandid(id);
		bc.delete(bm);

	}
	
	public void setMenu() {
	}
}
