package com.Menu;
import java.*;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.Dbconection;
import com.admin.SuggestText;

public class Catagory extends JPanel
{
	BorderLayout boder=new BorderLayout();
	
	JPanel panelwest=new JPanel();
	JPanel panelcenter=new JPanel();
	JPanel panelwestnorth=new JPanel();
	JPanel panelwestsouth=new JPanel();
	JPanel panelwestcenter=new JPanel();
	
	SuggestText cmbsearch=new SuggestText();
	
	JLabel lblcatagoryid=new JLabel("Catagory ID ");
	JLabel lblcatagoryname=new JLabel("Catagory Name ");
	JLabel lblusername=new JLabel("User Name");
	JLabel lblsubsearch=new JLabel("Search:  ");
	JLabel photoofsearch=new JLabel(new ImageIcon("image/search.png"));
	JLabel lblsearch=new JLabel("Search:  ");
	
	JTextField txtcatagoryid=new JTextField(20);
	JTextField txtcatagoryname=new JTextField(20);
	JTextField txtusername=new JTextField(20);
	
	JButton btnadd=new  JButton("Add",new ImageIcon("image/add.png"));
	JButton btnedit=new  JButton("Edit",new ImageIcon("image/edit.png"));
	JButton btnrefresh=new  JButton("Refresh",new ImageIcon("image/refresh.png"));
	JButton btndelete=new  JButton("Delete",new ImageIcon("image/delete.png"));
	
	String colname[]={"Catagory Id","Catagory Name", "User Name"};
	Object rowname[][]={};
	DefaultTableModel catmodel=new DefaultTableModel(rowname,colname);
	JTable cattable=new JTable(catmodel);
	JScrollPane catscroll=new JScrollPane(cattable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	boolean isupdatecat=false;
	
	
	public Catagory()
	{
		setBackground(Color.lightGray);
		panelwork();
		btnaction();
		catagoryevent();
		catinitialaction(true);
	}
	private boolean catinitialaction(boolean b)
	{
		btnadd.setEnabled(b);
		btnedit.setEnabled(!b);
		return b;
	}
	public void catsearchcatdataload(String id)
	{
		String sql="select  catagoryid,catagroyname,username from tbcatagoryinfo where catagoryid='"+id+"'";
		Dbconection.conect();
		try
		{
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			if(rs.next())
			{
				txtcatagoryid.setText(rs.getString("catagoryid"));
				txtcatagoryname.setText(rs.getString("catagroyname"));
				txtusername.setText(rs.getString("username"));	
			}
			Dbconection.con.close();
			catinitialaction(false);
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp, "error loading",JOptionPane.WARNING_MESSAGE);
			
		}
	}
	
	public void catagoryevent()
	{
		cattable.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {	}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0)
			{
				catsearchcatdataload(catmodel.getValueAt(cattable.getSelectedRow(),0).toString());		
			}
		});
		
		cattable.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0)
			{
				catsearchcatdataload(catmodel.getValueAt(cattable.getSelectedRow(),0).toString());	
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		
		cmbsearch.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(!cmbsearch.txtSuggest.getText().trim().isEmpty())
				{
					String idname=cmbsearch.txtSuggest.getText();
					StringTokenizer token=new StringTokenizer(idname,"#");
					catsearchcatdataload(token.nextToken());
				}
			}
		});
	}
	public boolean catcheakvalidation()
	{
		if(!txtcatagoryname.getText().trim().isEmpty()){
			if(!txtusername.getText().trim().isEmpty())
			{
				return true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "insert username plz...!","warning...",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "insert Catagoryname plz...!","warning...",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	public boolean catconfirmation(String caption )
	{
		int a=JOptionPane.showConfirmDialog(null,caption ,"confirm...",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION)
		{
			return true;	
		}
		else
		{
			return false;
		}
	}
	private boolean deletedatacat()
	{
		try
		{
			Dbconection.conect();
			String sql="delete from tbcatagoryinfo where catagoryid='"+txtcatagoryid.getText().trim()+"'";
			Dbconection.sta.executeUpdate(sql);
			Dbconection.con.close();
			return true;
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error...",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public boolean insertdata()
	{
		try
		{
			String sql="insert into tbcatagoryinfo (catagoryid,catagroyname,username,entrytime,userip) " +
					   "values ('"+txtcatagoryid.getText().trim()+"','"+txtcatagoryname.getText().trim()+"'," +
					   	"'"+txtusername.getText().trim()+"',now(),'')";
			
			Dbconection.conect();
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
	public void resetcat()
	{
		catautoid();
		txtcatagoryname.setText("");
		txtusername.setText("Sourav");
		isupdatecat=false;
	}
	public void catdataload()
	{
		try
		{
			int a;
			for(a=cattable.getRowCount()-1;a>=0;a--)
			{
				catmodel.removeRow(a);
				
			}
			String sql="select catagoryid,catagroyname,username from tbcatagoryinfo order by catagroyname";
			Dbconection.conect();
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			
			while(rs.next())
			{
				catmodel.addRow(new Object[]
						{
								rs.getString("catagoryid"),
								rs.getString("catagroyname"),
								rs.getString("username")
						});
			}
			
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp);
		}
	}
	public void catcmbsearch()
	{
		try
		{
			cmbsearch.v.clear();
			cmbsearch.v.add("");
			
			String sql="select catagoryid,catagroyname from tbcatagoryinfo order by catagroyname";
			Dbconection.conect();
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbsearch.v.add(rs.getString("catagoryid")+"#"+rs.getString("catagroyname"));
			}
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp);
		}
		
	}
	private void btnaction() 
	{
		btnadd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(catcheakvalidation())
				{
					if(catconfirmation(isupdatecat?"Do you want to edit ?":"Do you want to save ?"))
					{
							if(isupdatecat)
								{
									if(deletedatacat())
									{
										if(insertdata())
										{
											catdataload();
											JOptionPane.showMessageDialog(null, "All info Edit successfully","information....!",JOptionPane.INFORMATION_MESSAGE);
											catcmbsearch();
											resetcat();
											catautoid();
										}
									}
								}
							else
							{
								if(insertdata())
								{
									catdataload();
									JOptionPane.showMessageDialog(null, "All info saved successfully..!","information....",JOptionPane.INFORMATION_MESSAGE);
									catcmbsearch();
									resetcat();
									catautoid();
								}
							}
					}
				}
				
			}
		});
		btnedit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				isupdatecat=true;
				catinitialaction(true);
			}
		});
		btnrefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				resetcat();
			}
		});
		btndelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(catcheakvalidation())
				{
					if(catconfirmation("Do you want to delete ?"))
					{
						deletedatacat();
						catdataload();
						JOptionPane.showMessageDialog(null, "All info deleted successfully..!","information....",JOptionPane.INFORMATION_MESSAGE);
						catcmbsearch();
						resetcat();
						catautoid();
						catinitialaction(true);
					}
				}
			}
		});
	}

	public  void catautoid()
	{
		try{
			
			com.Dbconection.conect();
			String sql="select ifnull(max(cast(substring(catagoryid,"+
					   "locate('-',catagoryid)+1,"+
					   "length(catagoryid)-locate('-',catagoryid))"+
					    "as UNSIGNED)),0)+1 id from tbcatagoryinfo";
			
			ResultSet rs=com.Dbconection.sta.executeQuery(sql);
			
			if(rs.next())
			{
				String id=rs.getString("id");
				txtcatagoryid.setText("cat-"+id);
			}
			
			com.Dbconection.con.close();	
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, "exp");
		}
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
		panelcenter.setBackground(Color.LIGHT_GRAY);
		FlowLayout flow=new FlowLayout();
		panelcenter.setLayout(flow);
		panelcenter.add(catscroll);
		cattable.getTableHeader().setReorderingAllowed(false);
		catscroll.setPreferredSize(new Dimension(550,700));	
	}

	private void panelwestwork() 
	{
		panelwest.setPreferredSize(new Dimension(600,768));
		panelwest.setBorder(BorderFactory.createRaisedBevelBorder());
		panelwestpanelwork();
		
	}

	private void panelwestpanelwork() 
	{
		panelwest.setBackground(Color.LIGHT_GRAY);
		panelwest.setLayout(new BorderLayout());
		panelwest.setBorder(BorderFactory.createRaisedBevelBorder());
		panelwest.add(panelwestnorth, BorderLayout.NORTH);
		panelwestnorthwork();
		panelwest.add(panelwestcenter, BorderLayout.CENTER);
		panelwestcenterwork();
		panelwest.add(panelwestsouth, BorderLayout.SOUTH);
		panelwestsouthwork();
	}

	private void panelwestnorthwork() 
	{
		panelwestnorth.setBackground(Color.LIGHT_GRAY);
		panelwestnorth.setPreferredSize(new Dimension(600,100));
		panelwestnorth.setBorder(BorderFactory.createRaisedBevelBorder());
		FlowLayout flow=new FlowLayout();
		panelwestnorth.setLayout(flow);
		flow.setVgap(30);
		flow.setHgap(5);
		panelwestnorth.add(lblsearch);
		lblsearch .setFont(new Font("serif", Font.BOLD+Font.ITALIC, 20));
		panelwestnorth.add(photoofsearch);
		panelwestnorth.add(cmbsearch.cmbSuggest);
		cmbsearch.cmbSuggest.setPreferredSize(new Dimension(300,30));
	}

	private void panelwestsouthwork()
	{
		panelwestsouth.setBackground(Color.LIGHT_GRAY);
		panelwestsouth.setPreferredSize(new Dimension(600,150));
		panelwestsouth.setLayout(new FlowLayout());
		panelwestsouth.add(btnadd);
		btnadd.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		panelwestsouth.add(btnedit);
		btnedit.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		panelwestsouth.add(btnrefresh);
		btnrefresh.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		panelwestsouth.add(btndelete);
		btndelete.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	}

	private void panelwestcenterwork() 
	{
		panelwestcenter.setBackground(Color.LIGHT_GRAY);
		TitledBorder title=new TitledBorder(BorderFactory.createRaisedBevelBorder(),"Catagory");
		title.setTitleColor(Color.BLACK);
		title.setTitleFont(new Font("serif", Font.BOLD+Font.ITALIC, 20));
		title.setTitleJustification(TitledBorder.LEFT);
		panelwestcenter.setBorder(title);
		panelwestcenter.setLayout(new GridBagLayout());
		GridBagConstraints con=new GridBagConstraints();
	
		con.fill=GridBagConstraints.BOTH;
		con.insets=new Insets(5, 5, 5, 5);
		
		con.gridx=0;
		con.gridy=0;
		panelwestcenter.add(lblcatagoryid,con);
		lblcatagoryid.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		con.gridx=1;
		con.gridy=0;
		panelwestcenter.add(txtcatagoryid,con);
		txtcatagoryid.setEditable(false);
		
		con.gridx=0;
		con.gridy=1;
		panelwestcenter.add(lblcatagoryname,con);
		lblcatagoryname.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=1;
		panelwestcenter.add(txtcatagoryname,con);
		
		con.gridx=0;
		con.gridy=2;
		panelwestcenter.add(lblusername,con);
		lblusername.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=2;
		panelwestcenter.add(txtusername,con);
		txtusername.setEditable(false);
		txtusername.setText("Sourav");	
	}

}
