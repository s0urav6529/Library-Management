package com.admin;

import java.awt.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import javax.swing.*;

public class Login extends JFrame
{
	JPanel mainPanel=new JPanel();
	JPanel Panelsouth=new JPanel();
	JPanel Panelwest=new JPanel();
	JPanel Panelcenter=new JPanel();
	JPanel Panelnorth=new JPanel();
	JPanel panelcentersouth=new JPanel();
	JPanel panelcentercenter=new JPanel();
	
	JPasswordField txtnewpassword=new JPasswordField(20);
	JPasswordField txtpassword=new JPasswordField(20);
	JTextField txtusername=new JTextField(20);
	
	JLabel lblusertype=new JLabel("Usertype");
	JLabel lblusername=new JLabel("ManegerName");
	JLabel lblpassword=new JLabel("Password");
	JLabel lbllogo=new JLabel(new ImageIcon("image/cp.jpg"));
	
	SuggestText cmbusertype=new SuggestText();
	JButton btnnewuser=new JButton("New Maneger");
	JButton btncheackin=new JButton("Check In");
	JButton btncencel=new JButton("Cencel");
	
	Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	
	public Login()
	{
		frm();
		panelwork();
		btnaction();
	}
	public void cheackinaction()
	{
		mainPanel.setVisible(false);
		WorkingPanel wp=new WorkingPanel();
		add(wp);
		wp.setVisible(true);
		setSize(screen);
		setLocationRelativeTo(null);
	}
	
	private void btnaction()
	{
		btncheackin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(cheackvaridation2())
				{
					cheackinaction();
					
				}
			}
		});
		btncencel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(confiremation())
				{
					dispose();
				}
			}
		});
	}
	public boolean cheackvaridation2()
	{
		if(!txtusername.getText().isEmpty()){
			if(!txtpassword.getText().isEmpty()){
				if(!cmbusertype.txtSuggest.getText().isEmpty())
				{
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Enter usertype pls !","warning....",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Enter password pls !","warning....",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Enter user name pls !","warning....",JOptionPane.WARNING_MESSAGE);
		}
		return false;	
	}

	public boolean cheackvaridation()
	{
		if(!txtnewpassword.getText().isEmpty())
		{
			if(!cmbusertype.txtSuggest.getText().isEmpty())
			{
				return true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Enter usertype pls !","warning....",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "enter your new password plz !","warning......",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	
	public boolean confiremation()
	{
		 int a=JOptionPane.showConfirmDialog(null,"Do you want to cencel ?","confirm.....",JOptionPane.YES_NO_OPTION);
		 		if(a==JOptionPane.YES_OPTION)
		 		{
		 			return true;
		 		}
		return false;
	}
	
	private void panelwork()
	{
		add(mainPanel);
		BorderLayout border=new BorderLayout();
		mainPanel.setLayout(border);
		mainPanel.add(Panelnorth,BorderLayout.NORTH);
		Panelnorthwork();
		mainPanel.add(Panelcenter,BorderLayout.CENTER);
		Panelcenterwork();
		mainPanel.add(Panelsouth,BorderLayout.SOUTH);
		Panelsouthwork();
		mainPanel.add(Panelwest,BorderLayout.WEST);
		Panelwestwork();
	}
	private void Panelwestwork()
	{
		Panelwest.setPreferredSize(new Dimension(100,0));
		Panelwest.setBackground(Color.lightGray);	
	}
	private void Panelsouthwork() 
	{
		Panelsouth.setPreferredSize(new Dimension(0,60));
		Panelsouth.setBackground(Color.lightGray);
		FlowLayout flow=new FlowLayout();
		Panelsouth.setLayout(flow);
		flow.setVgap(20);
		Panelsouth.add(txtnewpassword);
		Panelsouth.add(btnnewuser);
		btnnewuser.setPreferredSize(new Dimension(150,25));
		flow.setAlignment(FlowLayout.RIGHT);
	}
	
	private void Panelcenterwork() 
	{
		Panelcenter.setBackground(Color.darkGray);
		Panelcenter.setLayout(new BorderLayout());
		Panelcenter.add(panelcentercenter,BorderLayout.CENTER);
		panelcentercenterwork();
		Panelcenter.add(panelcentersouth,BorderLayout.SOUTH);
		panelcentersouthwork();
	}
	private void panelcentersouthwork() 
	{
		panelcentersouth.setBorder(BorderFactory.createRaisedBevelBorder());
		panelcentersouth.setPreferredSize(new Dimension(0,100));
		panelcentersouth.setBackground(Color.RED);
		FlowLayout flow=new FlowLayout();
		panelcentersouth.setLayout(flow);
		flow.setVgap(10);
		panelcentersouth.add(btncheackin);
		btncheackin.setPreferredSize(new Dimension(300,30));
		panelcentersouth.add(btncencel);
		btncencel.setPreferredSize(new Dimension(300,30));
	}
	private void panelcentercenterwork() 
	{
		panelcentercenter.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.insets=new Insets(5,5,5,5);
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=0;
		panelcentercenter.add(lblusername,c);
		lblusername.setFont(new Font("serif",Font.BOLD+Font.ITALIC,15));
		c.gridx=1;
		c.gridy=0;
		panelcentercenter.add(txtusername,c);
		txtusername.setFont(new Font("serif",Font.BOLD+Font.ITALIC,15));
		txtusername.setText("maneger");
		c.gridx=0;
		c.gridy=1;
		panelcentercenter.add(lblpassword,c);
		lblpassword.setFont(new Font("serif",Font.BOLD+Font.ITALIC,15));
		c.gridx=1;
		c.gridy=1;
		panelcentercenter.add(txtpassword,c);
		txtpassword.setText("maneger");
		
		c.gridx=0;
		c.gridy=2;
		panelcentercenter.add(lblusertype,c);
		lblusertype.setFont(new Font("serif",Font.BOLD+Font.ITALIC,15));
		c.gridx=1;
		c.gridy=2;
		panelcentercenter.add(cmbusertype.cmbSuggest,c);
		cmbusertype.txtSuggest.setText("Admin");
		cmbusertype.txtSuggest.setFont(new Font("serif",Font.BOLD+Font.ITALIC,15));
		cmbusertype.v.add("");
		cmbusertype.v.add("Admin");
		cmbusertype.v.add("Superadmin");
		cmbusertype.v.add("Executive");
	}

	private void Panelnorthwork() 
	{
		Panelnorth.setPreferredSize(new Dimension(0,50));
		Panelnorth.setBackground(Color.lightGray);
	}

	private void frm()
	{
	
		setSize(500, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Library Manegment");
		setLocationRelativeTo(null);
		
	}

}
