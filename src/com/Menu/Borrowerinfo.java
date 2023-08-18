package com.Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.Dbconection;

public class Borrowerinfo extends JPanel
{
	JPanel panelwest=new JPanel();
	JPanel panelcenter=new JPanel();
	
	JPanel panelwestnorth=new JPanel();
	JPanel panelwestcenter=new JPanel();
	JPanel panelwestsouth=new JPanel();
	JPanel panelwestcenterwest=new JPanel();
	JPanel panelwestcentercenter=new JPanel();
	JPanel panelwestcentercenternorth=new JPanel();
	JPanel panelwestcentercentercenter=new JPanel();
	
	JLabel lblborrowerid=new JLabel("Borrower Id");
	JLabel lblborrowername=new JLabel("Borrower Name");
	JLabel lblmailid=new JLabel("Mail Address");
	JLabel lbladdress=new JLabel("Address");
	JLabel lblphoneno=new JLabel("Phone Number");
	JLabel lblusername=new JLabel("Username");
	JLabel lblreferenceby=new JLabel("Reference By");
	JLabel lblborrowedno=new JLabel("No. of borrowedbooks");
	JLabel lblbooksdescription=new JLabel("Books Description");
	JLabel lblsearch=new JLabel("Search:  ");
	JLabel photo=new JLabel();
	JLabel lblphotosearch=new JLabel(new ImageIcon("image/search.png"));
	
	JTextField txtborrowerid=new JTextField(20);
	JTextField txtborrowername=new JTextField(20);
	JTextField txtmailid=new JTextField(20);
	JTextField txtphoneno=new JTextField(20);
	JTextField txtusername=new JTextField(20);
	JTextField txtbooksdescription=new JTextField(20);
	JTextArea  txtaddress=new JTextArea(5,3);
	JScrollPane addressscoll=new JScrollPane(txtaddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


	JComboBox cmbborrowedno=new JComboBox();
	JComboBox cmbreferenceby=new JComboBox();
	JComboBox cmbsearch=new JComboBox();
	
	JButton btnadd=new  JButton("Add",new ImageIcon("image/add.png"));
	JButton btnedit=new  JButton("Edit",new ImageIcon("image/edit.png"));
	JButton btnrefresh=new  JButton("Refresh",new ImageIcon("image/refresh.png"));
	JButton btndelete=new  JButton("Delete",new ImageIcon("image/delete.png"));
	JButton btnupload=new JButton("Upload",new ImageIcon("image/Upload.png"));
	
	public Borrowerinfo()
	{
	   panelwork();
	   btnaction();
		
	}
	public void referecebyload()
	{
		cmbreferenceby.removeAllItems();
		cmbreferenceby.addItem("");
		try
		{
			Dbconection.conect();
			String sql="select supplierid,suppliername from tbsupplierinfo order by suppliername";
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbreferenceby.addItem(rs.getString("supplierid")+"#"+rs.getString("suppliername"));
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	private void btnaction()
	{
		btnadd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				
			}
		});
		btnedit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				
			}
		});
		btnrefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				
			}
		});
		btndelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				
			}
		});
	}
	private void panelwork()
	{
		setLayout(new BorderLayout());
		add(panelwest,BorderLayout.WEST);
		panelwestwork();
		add(panelcenter,BorderLayout.CENTER);
		panelcenterwork();
	}
	private void panelcenterwork() 
	{
		panelcenter.setBorder(BorderFactory.createLoweredBevelBorder());

	}
	private void panelwestwork() 
	{
		panelwest.setPreferredSize(new Dimension(650, 0));
		panelwest.setBorder(BorderFactory.createRaisedBevelBorder());
		panelwest.setLayout(new BorderLayout());
		panelwest.add(panelwestcenter,BorderLayout.CENTER);
		panelwestcenterwork();
		panelwest.add(panelwestnorth,BorderLayout.NORTH);
		panelwestnorthwork();
		panelwest.add(panelwestsouth,BorderLayout.SOUTH);
		panelwestsouthwork();
	}
	private void panelwestsouthwork() 
	{
		Font font=new Font("serif", Font.BOLD+Font.ITALIC, 15);
		FlowLayout flow=new FlowLayout();
		panelwestsouth.setLayout(flow);
		flow.setVgap(10);
		panelwestsouth.setPreferredSize(new Dimension(650,100));
		panelwestsouth.setBorder(BorderFactory.createRaisedBevelBorder());
		panelwestsouth.add(btnadd);
		btnadd.setFont(font);
		panelwestsouth.add(btnedit);
		btnedit.setFont(font);
		panelwestsouth.add(btnrefresh);
		btnrefresh.setFont(font);
		panelwestsouth.add(btndelete);
		btndelete.setFont(font);
		
	}
	private void panelwestnorthwork()
	{
		FlowLayout flow=new FlowLayout();
		panelwestnorth.setLayout(flow);
		panelwestnorth.setPreferredSize(new Dimension(650,100));
		panelwestnorth.setBorder(BorderFactory.createRaisedBevelBorder());
		flow.setVgap(20);
		panelwestnorth.add(lblsearch);
		panelwestnorth.add(lblphotosearch);
		lblsearch.setFont(new Font("serif",Font.BOLD+Font.ITALIC, 20));
		panelwestnorth.add(cmbsearch);
		cmbsearch.setPreferredSize(new Dimension(300,30));
	}
	private void panelwestcenterwork()
	{
		panelwestcenter.setLayout(new BorderLayout());
		panelwestcenter.setBorder(BorderFactory.createLoweredBevelBorder());
		panelwestcenter.add(panelwestcentercenter,BorderLayout.CENTER);
		panelwestcentercenterwork();
		panelwestcenter.add(panelwestcenterwest,BorderLayout.WEST);
		panelwestcenterwestwork();
	}
	private void panelwestcenterwestwork()
	{
		panelwestcenterwest.setBackground(Color.LIGHT_GRAY);
		panelwestcenterwest.setPreferredSize(new Dimension(450,0));

		panelwestcenterwest.setLayout(new GridBagLayout());
		GridBagConstraints con=new GridBagConstraints();
		con.fill=GridBagConstraints.BOTH;
		con.insets=new Insets(5, 5, 5, 5);

		con.gridx=0;
		con.gridy=0;
		panelwestcenterwest.add(lblborrowerid,con);
		lblborrowerid.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=0;
		panelwestcenterwest.add(txtborrowerid,con);
		txtborrowerid.setEditable(false);

		con.gridx=0;
		con.gridy=1;
		panelwestcenterwest.add(lblborrowername,con);
		lblborrowername.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=1;
		panelwestcenterwest.add(txtborrowername,con);

		con.gridx=0;
		con.gridy=2;
		panelwestcenterwest.add(lblmailid,con);
		lblmailid.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=2;
		panelwestcenterwest.add(txtmailid,con);

		con.gridx=0;
		con.gridy=3;
		panelwestcenterwest.add(lblphoneno,con);
		lblphoneno.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=3;
		panelwestcenterwest.add(txtphoneno,con);

		con.gridx=0;
		con.gridy=4;
		panelwestcenterwest.add(lblborrowedno,con);
		lblborrowedno.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=4;
		panelwestcenterwest.add(cmbborrowedno,con);

		con.gridx=0;
		con.gridy=5;
		panelwestcenterwest.add(lblbooksdescription,con);
		lblbooksdescription.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=5;
		panelwestcenterwest.add(txtbooksdescription,con);

		con.gridx=0;
		con.gridy=6;
		panelwestcenterwest.add(lbladdress,con);
		lbladdress.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=6;
		panelwestcenterwest.add(addressscoll,con);
		
		
		con.gridx=0;
		con.gridy=7;
		panelwestcenterwest.add(lblreferenceby,con);
		lblreferenceby.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=7;
		panelwestcenterwest.add(cmbreferenceby,con);

		con.gridx=0;
		con.gridy=8;
		panelwestcenterwest.add(lblusername,con);
		lblusername.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=8;
		panelwestcenterwest.add(txtusername,con);
		txtusername.setText("Sourav");
		txtusername.setEditable(false);
	}
	private void panelwestcentercenterwork() 
	{
		panelwestcentercenter.setBackground(Color.LIGHT_GRAY);
		panelwestcentercenter.setLayout(new BorderLayout());
		panelwestcentercenter.add(panelwestcentercenternorth,BorderLayout.NORTH);
		panelwestcentercenternorthwork();
		panelwestcentercenter.add(panelwestcentercentercenter,BorderLayout.CENTER);
		panelwestcentercentercenterwork();
	}
	private void panelwestcentercenternorthwork() 
	{
		panelwestcentercenternorth.setBackground(Color.LIGHT_GRAY);

		panelwestcentercenternorth.setLayout(new BorderLayout());
		panelwestcentercenternorth.setPreferredSize(new Dimension(180,35));

		photo.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		photo.setPreferredSize(new Dimension(100,140));

		FlowLayout flow=new FlowLayout();
		flow.setVgap(0);
		panelwestcentercentercenter.setLayout(flow);
		panelwestcentercentercenter.add(photo);
		
	}
	private void panelwestcentercentercenterwork() 
	{
		panelwestcentercentercenter.setBackground(Color.LIGHT_GRAY);
		panelwestcentercentercenter.setLayout(new FlowLayout());
		panelwestcentercentercenter.add(btnupload);
		btnupload.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

	}
}
