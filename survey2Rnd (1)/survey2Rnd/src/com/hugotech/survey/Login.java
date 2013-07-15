package com.hugotech.survey;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener{
	private JPanel contentPanel;
	private CardLayout cardLayout;
	
	private HashMap<String,String> mapList;
	private JTextField field;
	
	private JLabel label;
	private JLabel label1;
	private String input;
	private String sql;
  
	private final JTextField textField;
	private ButtonGroup button;
	private static JRadioButton button1;
	private static JRadioButton button2;
	private static JRadioButton button3;
	private String typedText;
	private String groupName;
	private JButton previousButton;
	private JButton nextButton;
	private JLabel groupLabel;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	@SuppressWarnings("unchecked")
	Login() throws Exception {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection(DataStore.load());
		statement = connection.createStatement();
		String query="select distinct q.question_group as questionGroup  from questions q;";
		
		
		setSize(650, 400);
		setLocation(90,90);
        setLayout(new BorderLayout());

        
		cardLayout = new CardLayout(0, 0);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		JLabel l=new JLabel("SURVEY FORM");
		l.setSize(10, 10);
		 l.setBounds(250, 00, 250, 20);
		 contentPanel.add(l);
		 label1=new JLabel("please press enterkey after UserId is enter");
		 label1.setOpaque(true);
		 label1.setForeground(Color.red);
		 label1.setBounds(160, 40, 250, 20);
		 contentPanel.add(label1);
		 label1.setVisible(true);
		 label=new JLabel("User :");
		 label.setBounds(160, 80, 80, 20);
		 contentPanel.add(label);
		 
		 field=new JTextField();
		 field.setBounds(200, 80, 40, 20);
		 contentPanel.add(field);
		 
		 textField=new JTextField();
		 textField.setBounds(250, 80, 100, 20);
		 //textField.setText("please press enterkey after UserId is enter ");
		 contentPanel.add(textField);
		 
		button = new ButtonGroup();
		button1 = new JRadioButton("English");
		button1.setBounds(150, 110, 80, 20);
		button.add(button1);
		contentPanel.add(button1);
			
		button2 = new JRadioButton("Hindi");
	    button2.setBounds(240, 110, 80, 20);
		button.add(button2);
		contentPanel.add(button2);
			
		button3 = new JRadioButton("Orissa");
		button3.setBounds(320, 110, 80, 20);
		button.add(button3);
		contentPanel.add(button3);
		
	    groupLabel=new JLabel("Group:");
	    groupLabel.setBounds(160, 140, 100, 20);
		contentPanel.add(groupLabel);
		
		 comboBox = new JComboBox();
       
       // System.out.println(query);
        resultSet = statement.executeQuery(query);
        comboBox.addItem("--SELECT--");
        while(resultSet.next()){
        String name=resultSet.getString("questionGroup");
        comboBox.addItem(name);
       
        }
        resultSet=null;
        comboBox.setEditable(true);
        comboBox.setBounds(200, 140, 100, 20);
        contentPanel.add(comboBox,BorderLayout.EAST);
	
		nextButton = new JButton(" SUBMIT ");
		nextButton.setBounds(250, 200, 100, 20);
		nextButton.addActionListener(this);
		contentPanel.add(nextButton ,BorderLayout.CENTER);
		
		add(contentPanel);
		
	
		
	field.addActionListener(new ActionListener(){
          
		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 String text = field.getText();
			// System.out.println(text);
			 String sql="select u.user_name as userName from users u where id="+text;
			 textField.setText("");
			  try {
				 
				resultSet = statement.executeQuery(sql);
				
				if (!resultSet.next()) {
					//System.out.println("select a valid user id");
					 textField.setText("");
					label1.setText("please select valid userId");
					 label1.setVisible(true);
				}else{
					 textField.setText("");
					String userName=resultSet.getString("userName");
					textField.setText(userName);
					textField.setEditable(false);
					label1.setVisible(false);				
			     }
							
			} catch (SQLException e1) {
				System.out.println(e1);
			
			}
			 
		}
		
		
		
	});
	
	comboBox.addItemListener(new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
		     if (e.getStateChange() == ItemEvent.SELECTED) {
			 groupName=(String) e.getItemSelectable().getSelectedObjects()[0];
			// System.out.println(groupName);
		     } 
		}
    });
        
		
		
	
		
	button1.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (AbstractButton.SELECTED_ICON_CHANGED_PROPERTY != null) {

				typedText = button1.getActionCommand();
			
			}

		}
	});
   
	
	button2.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (AbstractButton.SELECTED_ICON_CHANGED_PROPERTY != null) {

				typedText = button2.getActionCommand();
				//System.out.println("hi");
			}

		}
	});
	
	button3.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (AbstractButton.SELECTED_ICON_CHANGED_PROPERTY != null) {

				typedText = button3.getActionCommand();
				
			}

		}
	});
}
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		@Override
		public void run() {
			try {
				Login home = new Login();
				home.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 
		if((e.getSource() == previousButton)){
			
			cardLayout.previous(contentPanel);
			
		}
		if((e.getSource() == nextButton)){
			
			try {
				if(typedText=="" || typedText==null){
					JOptionPane.showMessageDialog(this,"Language Not Selected");
					System.exit(0);
				}
				
				input= textField.getText();
				if(input.equalsIgnoreCase("") || input.equalsIgnoreCase(null)){
					JOptionPane.showMessageDialog(this,"Select Valid UserId");
					System.exit(0);
					
				}
				
				
				if(groupName=="" || groupName==null){
					JOptionPane.showMessageDialog(this,"group not selected");
					System.exit(0);
				}
				
				 sql= "INSERT INTO Login (name,language) VALUES('"+input+"','"+typedText+"')";
				 
				int no = statement.executeUpdate(sql);
				
				if(no>0){
				this.setVisible(false);
				mapList=new HashMap<String, String>();
				mapList.put("language", typedText);
				mapList.put("groupName",groupName);
				 Main page=new Main(mapList);
				  page.setVisible(true);
				}
				else{
					JOptionPane.showInputDialog("please check your database ");
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {

				try {
					//resultSet.close();
					statement.close();
					connection.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
			
			
			//cardLayout.show(contentPanel,card+""+count);
			//count++;
		
		}
		
		}
		
		
		
	}
	
