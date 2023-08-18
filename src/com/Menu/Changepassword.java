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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.Dbconection;
//import com.admin.SessionBean;

public class Changepassword extends JPanel
{
	JPanel panelnorth=new JPanel();
	JPanel panelcenter=new JPanel();

	JButton btnchange=new JButton("Change",new ImageIcon("image/change.png"));
	JButton btnrefresh=new JButton("Refresh",new ImageIcon("image/refresh.png"));

	JLabel  lblusername=new JLabel("Username");
	JLabel  lbloldpassword=new JLabel("Old password");
	JLabel  lblnewpassword=new JLabel("New Password");
	JLabel  lblconfirmpassword=new JLabel("Confirm Password");

	JTextField txtusername=new JTextField(20);
	JTextField txtoldpassword=new JTextField(20);
	JTextField txtnewpassword=new JTextField(20);
	JTextField txtconfirmpassword=new JTextField(20);
		
	//SessionBean session;
	public Changepassword(/*SessionBean bean*/)
	{
		//this.session=bean;
		panelwork();
		btnaction();
		//txtusername.setText(session.getusername());
	}
	private boolean chkvalidation()
	{
		if(!txtusername.getText().trim().isEmpty()){
			if(!txtoldpassword.getText().isEmpty()){
				if(!txtnewpassword.getText().isEmpty()){
					if(!txtconfirmpassword.getText().isEmpty())
					{
						return true;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Insert Confirming Password Plz..","Warning..",JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Insert NewPassword Plz..","Warning..",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Insert OldPassword Plz..","Warning..",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Insert UserName Plz..","Warning..",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	
	}
	
	private boolean chkoldpassword()
	{
		try
		{
			Dbconection.conect();
			String sql="select * from tbnewuserinfo where " +
					   "username='"+txtusername.getText().trim()+"' "+
					   "and password='"+txtoldpassword.getText().trim()+"'";
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				return true;
			}
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp, "Error....",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	private boolean confirmation()
	{
		int a=JOptionPane.showConfirmDialog(null, "Do you want to change ? ","Confirm..",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
	
	private boolean chknewandconfirm()
	{
		String newpassword,confirmpass;
		newpassword=txtnewpassword.getText().trim();
		confirmpass=txtconfirmpassword.getText().trim();
		if(newpassword.equals(confirmpass))
		{
			return true;
		}
		return false;
	}
	
	private boolean updatepassword()
	{
		try
		{
			Dbconection.conect();
			String sql="update tbnewuserinfo set password='"+txtconfirmpassword.getText().trim()+"'" +
					   "where username='"+txtusername.getText().trim()+"' and password='"+txtoldpassword.getText().trim()+"'";
			Dbconection.sta.executeUpdate(sql);
			Dbconection.con.close();
			return true;	
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp);
		}
		return false;
	}
	
	private void reset()
	{
		//txtusername.setText(session.getusername());
		txtoldpassword.setText("");
		txtnewpassword.setText("");
		txtconfirmpassword.setText("");
	}
	
	private void btnaction()
	{
		btnrefresh.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				reset();
			}
		});
		
		btnchange.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				if(chkvalidation())
				{
					if(chkoldpassword())
					{
						if(chknewandconfirm())
						{
							if(confirmation())
							{
								if(updatepassword())
								{
									reset();
									JOptionPane.showMessageDialog(null, "password update successfully !","warning..",JOptionPane.WARNING_MESSAGE);
								}
							}	
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Newpasswoed and Confirmpassword is not matched  !","warning..",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "This password is not valid !","warning..",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}

	private void panelwork() 
	{
		setLayout(new BorderLayout());
		add(panelcenter,BorderLayout.CENTER);
		panelcenterwork();
		add(panelnorth,BorderLayout.NORTH);
		panelnorthwork();	
	}
	
	private void panelnorthwork() 
	{
		panelnorth.setBackground(Color.LIGHT_GRAY);
		panelnorth.setPreferredSize(new Dimension(0,370));
		panelnorth.setLayout(new GridBagLayout());
		GridBagConstraints con=new GridBagConstraints();
		con.fill=GridBagConstraints.BOTH;
		con.insets=new Insets(5, 5, 5, 5);
		con.gridx=0;
		con.gridy=0;
		panelnorth.add(lblusername,con);
		lblusername.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		con.gridx=1;
		con.gridy=0;
		panelnorth.add(txtusername,con);
		txtusername.setPreferredSize(new Dimension(300,30));
		
		con.gridx=0;
		con.gridy=1;
		panelnorth.add(lbloldpassword,con);
		lbloldpassword.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=1;
		panelnorth.add(txtoldpassword,con);
		
		con.gridx=0;
		con.gridy=2;
		panelnorth.add(lblnewpassword,con);
		lblnewpassword.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=2;
		panelnorth.add(txtnewpassword,con);

		con.gridx=0;
		con.gridy=3;
		panelnorth.add(lblconfirmpassword,con);
		lblconfirmpassword.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=3;
		panelnorth.add(txtconfirmpassword,con);
	}

	private void panelcenterwork()
	{	
		panelcenter.setBackground(Color.LIGHT_GRAY);
		FlowLayout flow=new FlowLayout();
		panelcenter.setLayout(flow);
		flow.setVgap(300);
		flow.setAlignment(FlowLayout.RIGHT);
		panelcenter.add(btnchange);
		btnchange.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		panelcenter.add(btnrefresh);
		btnrefresh.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	}
}
