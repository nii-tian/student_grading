package project;
import java.sql.*;
import javax.swing.*;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.SystemColor;

public class grading extends JFrame {
	public void grade() {
		try {
		
			//self.iconbitmap("C:\\Users\\Eunice\\Documents\\XU-Files\\Sayge\\PythonProjects")
			 String query = "select * from StudentGrade";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet	rs = pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				table.getColumnModel().getColumn(0).setPreferredWidth(150);
				table.getColumnModel().getColumn(1).setPreferredWidth(80);
				 table.getColumnModel().getColumn(2).setPreferredWidth(80);
				 table.getColumnModel().getColumn(3).setPreferredWidth(80);
				 table.getColumnModel().getColumn(4).setPreferredWidth(80);
				 table.getColumnModel().getColumn(5).setPreferredWidth(80);
				 table.getColumnModel().getColumn(6).setPreferredWidth(90);
				 
				 for (int c = 0; c < table.getColumnCount(); c++)
				 {
				     Class<?> col_class = table.getColumnClass(c);
				     table.setDefaultEditor(col_class,null);        
				 }
			 rs.close();
			 pst.close();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
	}

	public void average() {
		try {
		
			
			 String query = "select * from Student";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet	rs = pst.executeQuery();
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
				table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
				table_1.getColumnModel().getColumn(1).setPreferredWidth(150);
				 table_1.getColumnModel().getColumn(2).setPreferredWidth(80);
				 table_1.getColumnModel().getColumn(3).setPreferredWidth(80);
				 
				 
				 for (int c = 0; c < table_1.getColumnCount(); c++)
				 {
				     Class<?> col_class = table_1.getColumnClass(c);
				     table_1.setDefaultEditor(col_class,null);        
				 }
			 rs.close();
			 pst.close();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					grading frame = new grading();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection connection = null;
	private JTextField textR;
	private JTextField textD;
	private JTextField textC;
	private JTextField textE;
	private JTextField textPF;
	private JTable table;
	private JTextField textStudentName;
	private JTable table_1;

private JButton btnViewStudentAverage;
	public grading() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Manager IC\\Downloads\\\u2014Pngtree\u2014info icon_3550246.png"));
		setTitle("Student Grade");
		setResizable(false);
		JButton btnAddgrade = new JButton("Grade Student");
		btnAddgrade.setBackground(SystemColor.inactiveCaption);
		connection = SQlite.dbConnector();
		JLabel lblstudentname = new JLabel("");
		JLabel lblGraded = new JLabel("Graded");
		JLabel lblstatus = new JLabel("Ungraded");
		JLabel lblav = new JLabel("New label");
		JLabel lbla = new JLabel("New label");
		JButton btnNewButton = new JButton("Add Student");
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		JButton btnUpdate = new JButton("Change Grade");
		btnUpdate.setBackground(SystemColor.inactiveCaption);
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				String chemistry, descrete, rizal, pathfit, english;
				double average;
				double gpa = 0;
				
				chemistry = textC.getText () ;
				descrete = textD.getText () ;
				rizal = textR.getText () ;
				pathfit = textPF.getText () ;
				english  = textE.getText () ; 
				
				double chem = Double.parseDouble(chemistry);
				double desc = Double.parseDouble(descrete);
				double riz = Double.parseDouble(rizal);
				double path = Double.parseDouble(pathfit);
				double eng = Double.parseDouble(english);
				
				average = ( (chem + desc + riz + path + eng) / 5);
				String av = String.valueOf(average);
				lblav.setText(av);
				if (chem >= 90) {
					lbla.setText("A");
					gpa = gpa + 4.0;
				}else if (chem >= 80 && chem < 90 ) {
					lbla.setText("B");
					gpa = gpa + 3.0;
				}else if (chem >= 70 && chem < 80) {
					lbla.setText("C");
					gpa = gpa + 2.0;
				}else if (chem >= 60 && chem < 70) {
					lbla.setText("D");
					gpa = gpa + 1.0;
				}else {
					lbla.setText("F");
					gpa = gpa + 0;
					
					}
				String name = lblstudentname.getText();
				if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "Please select Student to Update Grade");
				}else {
				String query = "Update StudentGrade set Rizal='" + textR.getText()+"', DescreteMath='" +textD.getText()+"', Chemistry='" +textC.getText()+
						"', English='" +textE.getText()+"', PathFit='" +textPF.getText()+"', Status='" +lblGraded.getText()+
						"'where StudentName='" +lblstudentname.getText()+"' ";
						PreparedStatement pst = connection.prepareStatement(query);
				pst.execute();
				pst.close();
				
				String query1 = "Update Student set Average='" + lblav.getText()+"', GPA='" +lbla.getText()+
						"'where StudentName='" +lblstudentname.getText()+"' ";
						PreparedStatement pst1 = connection.prepareStatement(query1);
				pst1.execute();
				pst1.close();
				textC.setText("");
				textE.setText("");
				textR.setText("");
				textD.setText("");
				textPF.setText("");
				lblstudentname.setText("");
				textC.setEnabled(false);
				textE.setEnabled(false);
				textR.setEnabled(false);
				textPF.setEnabled(false);
				textD.setEnabled(false);
				btnAddgrade.setEnabled(false);
				btnNewButton.setEnabled(true);
				btnUpdate.setEnabled(false);
				grade();
				average();
				}
			}catch (Exception e4) {
				e4.printStackTrace();
			}
			}
		});
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 422);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 108, 22);
		contentPane.add(lblNewLabel);
		
		
		lblstudentname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblstudentname.setBounds(30, 35, 108, 22);
		contentPane.add(lblstudentname);
		
		JLabel lblFilipino = new JLabel("Rizal :");
		lblFilipino.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFilipino.setBounds(60, 79, 36, 22);
		contentPane.add(lblFilipino);
		
		JLabel lblEnglish = new JLabel("Discrete Math:");
		lblEnglish.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnglish.setBounds(10, 112, 86, 22);
		contentPane.add(lblEnglish);
		
		JLabel lblScience = new JLabel("Chemistry :");
		lblScience.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblScience.setBounds(30, 145, 66, 22);
		contentPane.add(lblScience);
		
		JLabel lblMapeh = new JLabel("English :");
		lblMapeh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMapeh.setBounds(47, 178, 49, 22);
		contentPane.add(lblMapeh);
		
		JLabel lblAp = new JLabel("Path Fit:");
		lblAp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAp.setBounds(47, 210, 49, 22);
		contentPane.add(lblAp);
		
		textR = new JTextField();
		textR.setEnabled(false);
		textR.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textR.setBounds(133, 79, 86, 20);
		contentPane.add(textR);
		textR.setColumns(10);
		
		textD = new JTextField();
		textD.setEnabled(false);
		textD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textD.setColumns(10);
		textD.setBounds(133, 112, 86, 20);
		contentPane.add(textD);
		
		textC = new JTextField();
		textC.setEnabled(false);
		textC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textC.setColumns(10);
		textC.setBounds(133, 145, 86, 20);
		contentPane.add(textC);
		
		textE = new JTextField();
		textE.setEnabled(false);
		textE.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textE.setColumns(10);
		textE.setBounds(133, 178, 86, 20);
		contentPane.add(textE);
		
		textPF = new JTextField();
		textPF.setEnabled(false);
		textPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPF.setColumns(10);
		textPF.setBounds(133, 211, 86, 20);
		contentPane.add(textPF);
		
		JLabel lblStudent = new JLabel("Student Name:");
		lblStudent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStudent.setBounds(820, 280, 93, 22);
		contentPane.add(lblStudent);
		
		JLabel lblstudentame_1 = new JLabel("");
		lblstudentame_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblstudentame_1.setBounds(863, 305, 108, 22);
		contentPane.add(lblstudentame_1);
		
		JLabel lblAverage = new JLabel("Average:");
		lblAverage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAverage.setBounds(778, 336, 66, 22);
		contentPane.add(lblAverage);
		
		JLabel lblaverage = new JLabel("");
		lblaverage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblaverage.setBounds(821, 361, 108, 22);
		contentPane.add(lblaverage);
		
		JLabel lblGpaa = new JLabel("GPA:");
		lblGpaa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGpaa.setBounds(941, 336, 66, 22);
		contentPane.add(lblGpaa);
		
		JLabel lblgpa = new JLabel("");
		lblgpa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblgpa.setBounds(984, 361, 108, 22);
		contentPane.add(lblgpa);
		
		
		btnAddgrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				
						
						String chemistry, descrete, rizal, pathfit, english;
						double average;
						double gpa = 0;
						
						chemistry = textC.getText () ;
						descrete = textD.getText () ;
						rizal = textR.getText () ;
						pathfit = textPF.getText () ;
						english  = textE.getText () ; 
						
						double chem = Double.parseDouble(chemistry);
						double desc = Double.parseDouble(descrete);
						double riz = Double.parseDouble(rizal);
						double path = Double.parseDouble(pathfit);
						double eng = Double.parseDouble(english);
						
						average = ( (chem + desc + riz + path + eng) / 5);
						String av = String.valueOf(average);
						lblav.setText(av);
						if (chem >= 90) {
							lbla.setText("A");
							gpa = gpa + 4.0;
						}else if (chem >= 80 && chem < 90 ) {
							lbla.setText("B");
							gpa = gpa + 3.0;
						}else if (chem >= 70 && chem < 80) {
							lbla.setText("C");
							gpa = gpa + 2.0;
						}else if (chem >= 60 && chem < 70) {
							lbla.setText("D");
							gpa = gpa + 1.0;
						}else {
							lbla.setText("F");
							gpa = gpa + 0;
							
							}
						String student = lblstudentname.getText();
						if(student.equals("")) {
							JOptionPane.showMessageDialog(null, "Please Select Student");
						}else {
							String query = "Update StudentGrade set Rizal='" + textR.getText()+"', DiscreteMath='" +textD.getText()+"', Chemistry='" +textC.getText()+
									"', English='" +textE.getText()+"', PathFit='" +textPF.getText()+"', Status='" +lblGraded.getText()+
									"'where StudentName='" +lblstudentname.getText()+"' ";
									PreparedStatement pst = connection.prepareStatement(query);
							pst.execute();
							pst.close();
							
							String query2 = "select * from Student where StudentName=?";
							PreparedStatement pst2=connection.prepareStatement(query2);
							pst2.setString(1,lblstudentname.getText());
							ResultSet rs2= pst2.executeQuery();
							grade();
							average();
							
							int view = 0;
							
							while (rs2.next()) {
								view++;
							}
							
							if(view > 1) {
							JOptionPane.showMessageDialog(null, "Student Already graded");
							textC.setText("");
							textE.setText("");
							textR.setText("");
							textD.setText("");
							textPF.setText("");
							lblstudentname.setText("");
							textC.setEnabled(false);
							textE.setEnabled(false);
							textR.setEnabled(false);
							textPF.setEnabled(false);
							textD.setEnabled(false);
							btnAddgrade.setEnabled(false);
							btnNewButton.setEnabled(true);
							}else if(view == 1) {
							JOptionPane.showMessageDialog(null, "Student Already graded");
							textStudentName.setText("");
							textC.setText("");
							textE.setText("");
							textR.setText("");
							textD.setText("");
							textPF.setText("");
							lblstudentname.setText("");
							btnAddgrade.setEnabled(false);
							btnNewButton.setEnabled(true);
							}else {
							
							if(student.equals("")) {
								JOptionPane.showMessageDialog(null, "Please insert Student");
							}else {
							pst2.close();	
							rs2.close();
							
							String query1 = "insert into Student ( StudentName, Average, GPA ) values (?,?,?)";
							PreparedStatement pst1 = connection.prepareStatement(query1);
							
							pst1 .setString(1, lblstudentname.getText());
							pst1 .setString(2, lblav.getText());
							pst1 .setString(3, lbla.getText());
							
							pst1.execute();
							pst1.close();
							
							textC.setText("");
							textE.setText("");
							textR.setText("");
							textD.setText("");
							textPF.setText("");
							lblstudentname.setText("");
							btnAddgrade.setEnabled(false);
							btnNewButton.setEnabled(true);
							grade();
							average();
							}
							}
						}
						
						
						
						
			}catch(Exception e3) {
				e3.printStackTrace();
			}
				
			}
		});
		btnAddgrade.setEnabled(false);
		btnAddgrade.setBounds(10, 244, 151, 36);
		contentPane.add(btnAddgrade);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(246, 45, 487, 240);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			try {
				String name = lblstudentname.getText();
		
				if (name.equals("")) {
					int row = table.getSelectedRow();
					String srow = (table.getModel().getValueAt(row, 0)) . toString();
					String query = "select * from StudentGrade where StudentName='"+srow+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
					
						lblstudentname.setText(rs.getString("StudentName"));
						textC.setText(rs.getString("Chemistry"));
						textE.setText(rs.getString("English"));
						textR.setText(rs.getString("Rizal"));
						textPF.setText(rs.getString("PathFit"));
						textD.setText(rs.getString("DiscreteMath"));
					
					}
					btnNewButton.setEnabled(false);
					btnAddgrade.setEnabled(true);
					btnUpdate.setEnabled(true);
					textC.setEnabled(true);
					textE.setEnabled(true);
					textR.setEnabled(true);
					textPF.setEnabled(true);
					textD.setEnabled(true);
				}else {
					lblstudentname.setText("");
					textC.setText("");
					textE.setText("");
					textR.setText("");
					textPF.setText("");
					textD.setText("");
					textC.setEnabled(false);
					textE.setEnabled(false);
					textR.setEnabled(false);
					textPF.setEnabled(false);
					textD.setEnabled(false);
					
					btnNewButton.setEnabled(true);
					btnAddgrade.setEnabled(false);
					btnUpdate.setEnabled(false);
					table.getSelectedRow();
					table.clearSelection();
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
			}
			
		}); //done
		scrollPane.setViewportView(table);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(246, 305, 66, 22);
		contentPane.add(lblName);
		
		textStudentName = new JTextField();
		textStudentName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textStudentName.setColumns(10);
		textStudentName.setBounds(311, 307, 148, 20);
		contentPane.add(textStudentName);
		
		JLabel lblEnrollStudent = new JLabel("Add Student");
		lblEnrollStudent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnrollStudent.setBounds(246, 280, 108, 22);
		contentPane.add(lblEnrollStudent);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from StudentGrade where StudentName=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,textStudentName.getText());
					ResultSet rs= pst.executeQuery();
					int view = 0;
					while (rs.next()) {
						view++;
					}
					if(view > 1) {
					JOptionPane.showMessageDialog(null, "Student Already Enrolled");
					textStudentName.setText("");
					}else if(view == 1) {
						
					JOptionPane.showMessageDialog(null, "Student Already Enrolled");
					textStudentName.setText("");
					}else {
					String txt = textStudentName.getText();
					if(txt.equals("")) {
						JOptionPane.showMessageDialog(null, "Please insert Student");
					}else {
					String query1 = "insert into StudentGrade ( StudentName,Status ) values ('"+textStudentName.getText() +"','"+lblstatus.getText()+"')";
				PreparedStatement pst1 = connection.prepareStatement(query1);
				pst1.execute();
				pst1.close();
				textStudentName.setText("");
				grade();
				average();
					}
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(321, 328, 125, 41);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(803, 45, 264, 236);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				String name = lblstudentname.getText();
				
				if (name.equals("")) {
					int row = table_1.getSelectedRow();
					String srow = (table_1.getModel().getValueAt(row, 0)) . toString();
					String query = "select * from Student where id='"+srow+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
					
						lblstudentame_1.setText(rs.getString("StudentName"));
						lblaverage.setText(rs.getString("Average"));
						lblgpa.setText(rs.getString("GPA"));
					
					}
					
				}else {
					
					table_1.getSelectedRow();
					table_1.clearSelection();
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		
		lbla.setBounds(75, 594, 46, 14);
		contentPane.add(lbla);
		
		
		lblav.setBounds(131, 594, 46, 14);
		contentPane.add(lblav);
		
		
		lblstatus.setBounds(687, 611, 46, 14);
		contentPane.add(lblstatus);
		
		
		lblGraded.setBounds(625, 611, 46, 14);
		contentPane.add(lblGraded);
		
		
		btnUpdate.setEnabled(false);
		
		btnUpdate.setBounds(10, 280, 151, 36);
		contentPane.add(btnUpdate);
		JButton btnViewStudentAverage_1 = new JButton();
		btnViewStudentAverage_1.setBackground(SystemColor.inactiveCaption);
		btnViewStudentAverage = new JButton();
		btnViewStudentAverage.setBackground(SystemColor.inactiveCaption);
		btnViewStudentAverage.setText("View Student Average");
		btnViewStudentAverage_1.setText("Close Student Average");
		btnViewStudentAverage_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				grading frame = new grading();
				frame.setVisible(true);
				frame.setBounds(100, 100, 759, 422);
				frame.setLocationRelativeTo(null);
				frame.btnViewStudentAverage.setVisible(true);
				btnViewStudentAverage_1.setVisible(false);
				
			}
		});
		
		btnViewStudentAverage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				dispose();
				grading frame = new grading();
				frame.setVisible(true);
				frame.setBounds(100, 100, 1131, 422);
				frame.setLocationRelativeTo(null);
				frame.btnViewStudentAverage.setVisible(false);
				
			}
		});
		btnViewStudentAverage.setBounds(554, 305, 179, 41);
		contentPane.add(btnViewStudentAverage);
		
		
		
		btnViewStudentAverage_1.setBounds(554, 305, 179, 41);
		contentPane.add(btnViewStudentAverage_1);
		
		grade();
		average();
	}
}


/*def calculate_average(self, chem, desc, riz, path, eng):
        average = (chem + desc + riz + path + eng) / 5
        self.lbl_average_value.config(text=str(average))

        gpa = 0.0
        if chem >= 90:
            self.lbl_gpa_value.config(text="A")
            gpa += 4.0
        elif 80 <= chem < 90:
            self.lbl_gpa_value.config(text="B")
            gpa += 3.0
        elif 70 <= chem < 80:
            self.lbl_gpa_value.config(text="C")
            gpa += 2.0
        elif 60 <= chem < 70:
            self.lbl_gpa_value.config(text="D")
            gpa += 1.0
        else:
            self.lbl_gpa_value.config(text="F")
            gpa += 0.0

        return gpa */