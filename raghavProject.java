package raghavCapstone;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Canvas;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class raghavProject {

	private JFrame frame;
	private JTextField txtrating;
	private JTextField txtbname;
	private JTextField txtday;
	private JTable table;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					raghavProject window = new raghavProject();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public raghavProject() {
		initialize();
		Connect();
		table_load();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	int ec = 0;
	int h = 0;
	int n = 0;
	int s = 0;
	int d = 0;
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/JavaCrud", "root","");
			
			
		}
		catch (ClassNotFoundException ex) {
			
		}
		catch (SQLException ex) {
			
		}
	}
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from Mood");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 1146, 870);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mood Tracker");
		lblNewLabel.setForeground(new Color(255, 215, 0));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		lblNewLabel.setBounds(443, 16, 409, 94);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 215, 0));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Entry", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(38, 147, 386, 220);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mood");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1.setBounds(39, 49, 61, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Rating");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(39, 101, 61, 16);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Day");
		lblNewLabel_1_2.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(39, 144, 116, 16);
		panel.add(lblNewLabel_1_2);
		
		txtrating = new JTextField();
		txtrating.setBounds(112, 97, 172, 26);
		panel.add(txtrating);
		txtrating.setColumns(10);
		
		txtbname = new JTextField();
		txtbname.setColumns(10);
		txtbname.setBounds(112, 45, 172, 26);
		panel.add(txtbname);
		
		txtday = new JTextField();
		txtday.setColumns(10);
		txtday.setBounds(113, 140, 172, 26);
		panel.add(txtday);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setForeground(new Color(255, 215, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mname, rating, day;
				mname = txtbname.getText();
				rating = txtrating.getText();
				day= txtday.getText();
				if(mname.equals("Excited")) {
					ec++;
					textField.setText(String.valueOf(ec));
				}
				else if(mname.equals("Happy")) {
					h++;
					textField_1.setText(String.valueOf(h));
				}
				else if(mname.equals("Normal")) {
					n++;
					textField_2.setText(String.valueOf(n));
				}
				else if(mname.equals("Sad")) {
					s++;
					textField_3.setText(String.valueOf(s));
				}
				else {
					d++;
					textField_4.setText(String.valueOf(d));
				}
				
				
				try {
					pst = con.prepareStatement("insert into Mood(Mood, Rating, Day)values(?,?,?)");
					pst.setString(1, mname);
					pst.setString(2,rating);
					pst.setString(3, day);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Added!");
					table_load();
					txtbname.setText("");
					txtrating.setText("");
					txtday.setText("");
					txtbname.requestFocus();
					
				}
				catch (SQLException el) {
					el.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBounds(48, 396, 110, 63);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(255, 215, 0));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setBounds(181, 396, 110, 63);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(255, 215, 0));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbname.setText("");
				txtrating.setText("");
				txtday.setText("");
				txtbname.requestFocus();
				
			}
		});
		btnClear.setBounds(314, 396, 110, 63);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(555, 148, 537, 294);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 215, 0));
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(38, 490, 386, 88);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Entry #");
		lblNewLabel_1_1_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(32, 41, 294, 16);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String id = txtid.getText();
					pst = con.prepareStatement("select Mood,Rating,Day from Mood where Entry = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					if(rs.next()==true) {
						String Name = rs.getString(1);
						String rating = rs.getString(2);
						String day = rs.getString(3);
						
						txtbname.setText(Name);
						txtrating.setText(rating);
						txtday.setText(day);
					}
					else {
						txtbname.setText("");
						txtrating.setText("");
						txtday.setText("");
						
					}
				}
				catch (SQLException ex) {
					
				}
			}
		});
		txtid.setColumns(10);
		txtid.setBounds(114, 37, 212, 26);
		panel_1.add(txtid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(new Color(255, 215, 0));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mname, rating, day,bid;
				mname = txtbname.getText();
				rating = txtrating.getText();
				day= txtday.getText();
				bid = txtid.getText();
				if(mname.equals("Excited")) {
					ec++;
					textField.setText(String.valueOf(ec));
				}
				else if(mname.equals("Happy")) {
					h++;
					textField_1.setText(String.valueOf(h));
				}
				else if(mname.equals("Normal")) {
					n++;
					textField_2.setText(String.valueOf(n));
				}
				else if(mname.equals("Sad")) {
					s++;
					textField_3.setText(String.valueOf(s));
				}
				else {
					d++;
					textField_4.setText(String.valueOf(d));
				}
				
				
				try {
					pst = con.prepareStatement("update Mood set Mood = ?, Rating = ?, Day = ? where Entry = ?");
					pst.setString(1, mname);
					pst.setString(2,rating);
					pst.setString(3, day);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Updated!");
					table_load();
					txtbname.setText("");
					txtrating.setText("");
					txtday.setText("");
					txtbname.requestFocus();
					
				}
				catch (SQLException el) {
					el.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		btnUpdate.setBounds(655, 463, 110, 63);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 215, 0));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bid;
			
				bid = txtid.getText();
				if(txtbname.getText().equals("Excited")) {
					ec--;
					textField.setText(String.valueOf(ec));
				}
				else if(txtbname.getText().equals("Happy")) {
					h--;
					textField_1.setText(String.valueOf(h));
				}
				else if(txtbname.getText().equals("Normal")) {
					n--;
					textField_2.setText(String.valueOf(n));
				}
				else if(txtbname.getText().equals("Sad")) {
					s--;
					textField_3.setText(String.valueOf(s));
				}
				else {
					d--;
					textField_4.setText(String.valueOf(d));
				}
				
				
				
				try {
					pst = con.prepareStatement("delete from Mood where Entry = ?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Deleted!");
					table_load();
					txtbname.setText("");
					txtrating.setText("");
					txtday.setText("");
					txtbname.requestFocus();
					
				}
				catch (SQLException el) {
					el.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		btnDelete.setBounds(849, 463, 110, 63);
		frame.getContentPane().add(btnDelete);
		
		JButton btnNewButton_1 = new JButton("Excited ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBackground(new Color(255, 204, 0));
		btnNewButton_1.setBounds(45, 655, 155, 36);
		frame.getContentPane().add(btnNewButton_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 204, 0));
		textArea.setBounds(35, 645, 177, 47);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton_1_1 = new JButton("Happy");
		btnNewButton_1_1.setBackground(new Color(255, 204, 0));
		btnNewButton_1_1.setBounds(268, 654, 155, 36);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBackground(new Color(255, 204, 0));
		textArea_1.setBounds(258, 644, 177, 47);
		frame.getContentPane().add(textArea_1);
		
		JButton btnNewButton_1_2 = new JButton("Normal");
		btnNewButton_1_2.setBackground(new Color(255, 204, 0));
		btnNewButton_1_2.setBounds(493, 655, 155, 36);
		frame.getContentPane().add(btnNewButton_1_2);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBackground(new Color(255, 204, 0));
		textArea_2.setBounds(483, 645, 177, 47);
		frame.getContentPane().add(textArea_2);
		
		JButton btnNewButton_1_3 = new JButton("Sad");
		btnNewButton_1_3.setBackground(new Color(255, 204, 0));
		btnNewButton_1_3.setBounds(723, 654, 155, 36);
		frame.getContentPane().add(btnNewButton_1_3);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBackground(new Color(255, 204, 0));
		textArea_3.setBounds(713, 644, 177, 47);
		frame.getContentPane().add(textArea_3);
		
		JButton btnNewButton_1_4 = new JButton("Depressed");
		btnNewButton_1_4.setBackground(new Color(255, 204, 0));
		btnNewButton_1_4.setBounds(933, 653, 155, 36);
		frame.getContentPane().add(btnNewButton_1_4);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBackground(new Color(255, 204, 0));
		textArea_4.setBounds(923, 643, 177, 47);
		frame.getContentPane().add(textArea_4);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		textField.setText(String.valueOf(ec));
		textField.setBounds(38, 703, 174, 94);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText(String.valueOf(h));
		textField_1.setColumns(10);
		textField_1.setBounds(258, 702, 174, 94);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		textField_2.setText(String.valueOf(n));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBounds(483, 703, 174, 94);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setText(String.valueOf(s));
		textField_3.setColumns(10);
		textField_3.setBounds(713, 702, 174, 94);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setText(String.valueOf(d));
		textField_4.setColumns(10);
		textField_4.setBounds(923, 703, 174, 94);
		frame.getContentPane().add(textField_4);
	}
}
