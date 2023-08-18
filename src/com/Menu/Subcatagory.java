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

public class Subcatagory extends JPanel
{
	JPanel panelwest=new JPanel();
	JPanel panelcenter=new JPanel();
	JPanel panelwestnorth=new JPanel();
	JPanel panelwestsouth=new JPanel();
	JPanel panelwestcenter=new JPanel();
	
	SuggestText cmbsubsearch=new SuggestText();
	SuggestText cmbcatagoryid=new SuggestText();
	
	JLabel lblsubcatagoryid=new JLabel("SubCatagory Id");
	JLabel lblsubcatagoryname=new JLabel("SubCatagory Name ");
	JLabel lblsubusername=new JLabel("User Name");
	JLabel lblcmbsubcatagoryid=new JLabel("Catagory Id");
	JLabel lblsubsearch=new JLabel("Search:  ");
	JLabel photoofsearch=new JLabel(new ImageIcon("image/search.png"));
	
	JTextField txtsubcatagoryid=new JTextField(20);
	JTextField txtsubcatagoryname=new JTextField(20);
	JTextField txtsubusername=new JTextField(20);
	
	JButton btnsubadd=new  JButton("Add",new ImageIcon("image/add.png"));
	JButton btnsubedit=new  JButton("Edit",new ImageIcon("image/edit.png"));
	JButton btnsubrefresh=new  JButton("Refresh",new ImageIcon("image/refresh.png"));
	JButton btnsubdelete=new  JButton("Delete",new ImageIcon("image/delete.png"));
	
	String colname1[]={"Catagory Id","SubCatagory Id","SubCatagory Name","User Name"};
	Object rowname1[][]={};
	DefaultTableModel submodel=new DefaultTableModel(rowname1,colname1);
	JTable subtable=new JTable(submodel);
	JScrollPane subscroll=new JScrollPane(subtable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	boolean isupdate=false;
	public Subcatagory( )
	{
		panelwork();
		initial(true);
		btnaction();
		eventaction();
	}
	private void eventaction()
	{
		subtable.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				subcatagorydatasearch(submodel.getValueAt(subtable.getSelectedRow(), 1).toString());
			}
		});
		subtable.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				subcatagorydatasearch(submodel.getValueAt(subtable.getSelectedRow(), 1).toString());
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		cmbsubsearch.cmbSuggest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbsubsearch.txtSuggest.getText().isEmpty())
				{
					String subcatagory=cmbsubsearch.txtSuggest.getText();
					StringTokenizer subtoken=new StringTokenizer(subcatagory,"#");
					String subid=subtoken.nextToken();
					subcatagorydatasearch(subid);
				}
			}
		});
	}
	
	private void subcatagorydatasearch(String subid)
	{
		try
		{
			String sql="select catagoryid,catagoryname,subcatagoryid," +
					   "subcatagoryname,username from tbsubcatagoryinfo where subcatagoryid like '"+subid+"'";
			Dbconection.conect();
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbcatagoryid.txtSuggest.setText(rs.getString("catagoryid")+"#"+rs.getString("catagoryname"));
				txtsubcatagoryid.setText(rs.getString("subcatagoryid"));
				txtsubcatagoryname.setText(rs.getString("subcatagoryname"));
				txtsubusername.setText(rs.getString("username"));
				
			}
			Dbconection.con.close();
			isupdate=true;
			initial(false);
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error.......",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void reset()
	{
		cmbsubsearch.txtSuggest.setText("");
		cmbcatagoryid.txtSuggest.setText("");
		subcatautoid();
		txtsubcatagoryname.setText("");
		txtsubusername.setText("Sourav");
		isupdate=false;
		initial(true);
	}
	
	public  void subcatautoid()
	{
		try{
			
			com.Dbconection.conect();
			String sql="select ifnull(max(cast(substring(subcatagoryid,"+
					   "locate('-',subcatagoryid)+1,"+
					   "length(subcatagoryid)-locate('-',subcatagoryid))"+
					   "as UNSIGNED)),0)+1 id from tbsubcatagoryinfo";
			
			ResultSet rs=com.Dbconection.sta.executeQuery(sql);
			
			if(rs.next())
			{
				String id=rs.getString("id");
				txtsubcatagoryid.setText("subcat-"+id);
			}
			
			com.Dbconection.con.close();	
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, "exp2");
		}
	}
	public boolean  subcatconfirmation(String caption)
	{
		int a=JOptionPane.showConfirmDialog(null,caption ,"confirm..",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION)
		{
			return true;		
		}
		else
		{
			return false;
		}
	}
	private boolean deletedatasubcat()
	{
		try
		{
			Dbconection.conect();
			String sql="delete from tbsubcatagoryinfo where subcatagoryid='"+txtsubcatagoryid.getText().trim()+"'";
			Dbconection.sta.executeUpdate(sql);
			Dbconection.con.close();
			return true;
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp ,"Delete Error...",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	private void btnaction() 
	{
		btnsubadd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(subcatcheakvalidation())
				{
					if(subcatconfirmation(isupdate?"Do you want to edit ?":"Do you want to save ?"))
					{
							if(isupdate)
							{
								if(deletedatasubcat())
								{
									if(subcatinsertdata())
									{
										subcatdataload();
										JOptionPane.showMessageDialog(null, "All info edit successfully !","Confirmation...",JOptionPane.INFORMATION_MESSAGE);
										subcatcmbsearch();
										reset();
										subcatautoid();
									}
								}
							}
							else
							{
								if(subcatinsertdata())
								{
									subcatdataload();
									JOptionPane.showMessageDialog(null, "All info saved successfully","Confirmation...",JOptionPane.INFORMATION_MESSAGE);
									subcatcmbsearch();
									reset();
									subcatautoid();
								}
							}
					}	
				}
			}
		});
		btnsubrefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				reset();
			}
		});
		btnsubedit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				isupdate=true;
				initial(true);
			}
		});
		btnsubdelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(subcatcheakvalidation())
				{
					if(subcatconfirmation("Sure to delete ?"))
					{
						deletedatasubcat();
						subcatdataload();
						JOptionPane.showMessageDialog(null, "All info deleted Successfully","Info..",JOptionPane.INFORMATION_MESSAGE);
						subcatcmbsearch();
						reset();
						subcatautoid();
					}
				}
			}
		});
		
	}
	public void subcatdataload()
	{
		try
		{
			int a;
			for(a=subtable.getRowCount()-1;a>=0;a--)
			{
				submodel.removeRow(a);
			}
			String sql="select catagoryid,subcatagoryid,subcatagoryname,username from tbsubcatagoryinfo";
			Dbconection.conect();
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			
			while(rs.next())
			{
				submodel.addRow(new Object[]
						{
								rs.getString("catagoryid"),
								rs.getString("subcatagoryid"),
								rs.getString("subcatagoryname"),
								rs.getString("username")
						});
			}
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"DataLoad Error...",JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean subcatinsertdata()
	{
		try
		{
			String idname=cmbcatagoryid.txtSuggest.getText();
			StringTokenizer token=new StringTokenizer(idname,"#");
			String catid=token.nextToken();
			String catname=token.nextToken();
			
			String sql="insert into tbsubcatagoryinfo (catagoryid,catagoryname,subcatagoryid," +
					   "subcatagoryname,username,entrytime,userip) values ('"+catid+"','"+catname+"',"+
					   "'"+txtsubcatagoryid.getText().trim()+"','"+txtsubcatagoryname.getText().trim()+"'," +
					   "'"+txtsubusername.getText().trim()+"',now(),'')";
			Dbconection.conect();
			Dbconection.sta.executeUpdate(sql);
			Dbconection.con.close();
			return true;
		}
		catch(Exception exp)		
		{
			JOptionPane.showMessageDialog(null, exp,"Insert error...",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	private boolean initial(boolean b) 
	{
		btnsubadd.setEnabled(b);
		btnsubedit.setEnabled(!b);
		return true;
	}

	public boolean subcatcheakvalidation()
	{	
		if(!cmbcatagoryid.txtSuggest.getText().trim().isEmpty()){
			if(!txtsubcatagoryname.getText().trim().isEmpty()){
				if(!txtsubusername.getText().trim().isEmpty())
				{
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "insert username plz !","warning...",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "insert subCatagoryname plz !","warning...",JOptionPane.WARNING_MESSAGE);		
			}			
		}		
		else
		{
			JOptionPane.showMessageDialog(null, "Select CatagoryId plz !","Warinig....",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	
	public void cmbcatagroyload()
	{
		cmbcatagoryid.v.clear();
		cmbcatagoryid.v.add("");
		
		try
		{
			String sql="select catagoryid,catagroyname from tbcatagoryinfo";
			Dbconection.conect();
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbcatagoryid.v.add(rs.getString("catagoryid")+"#"+rs.getString("catagroyname"));
			}
			Dbconection.con.close();
			
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"CatagroyLpad Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void subcatcmbsearch()
	{
		try
		{
			cmbsubsearch.v.clear();
			cmbsubsearch.v.add("");
			
			String sql="select subcatagoryid,subcatagoryname from tbsubcatagoryinfo order by subcatagoryname";
			Dbconection.conect();
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbsubsearch.v.add(rs.getString("subcatagoryid")+"#"+rs.getString("subcatagoryname"));
			}
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Cmbsubcatagoryload error",JOptionPane.ERROR_MESSAGE);
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
		panelcenter.add(subscroll);
		subtable.getTableHeader().setReorderingAllowed(false);
		subscroll.setPreferredSize(new Dimension(550,700));	
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
		panelwest.setPreferredSize(new Dimension(600,740));
		panelwest.setBorder(BorderFactory.createRaisedBevelBorder());
		panelwest.add(panelwestnorth, BorderLayout.NORTH);
		panelwestnorthwork();
		panelwest.add(panelwestcenter, BorderLayout.CENTER);
		panelwestcenterwork();
		panelwest.add(panelwestsouth, BorderLayout.SOUTH);
		panelwestsouthwork();
	}
	private void panelwestcenterwork()
	{
		panelwestcenter.setBackground(Color.LIGHT_GRAY);
		TitledBorder title=new TitledBorder(BorderFactory.createRaisedBevelBorder(),"Sub Catagory");
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
		panelwestcenter.add(lblcmbsubcatagoryid,con);
		lblcmbsubcatagoryid.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 20));
		
		con.gridx=1;
		con.gridy=0;
		panelwestcenter.add(cmbcatagoryid.cmbSuggest,con);

		con.gridx=0;
		con.gridy=1;
		panelwestcenter.add(lblsubcatagoryid,con);
		lblsubcatagoryid.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=1;
		panelwestcenter.add(txtsubcatagoryid,con);
		txtsubcatagoryid.setEditable(false);
		
		con.gridx=0;
		con.gridy=2;
		panelwestcenter.add(lblsubcatagoryname,con);
		lblsubcatagoryname.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=2;
		panelwestcenter.add(txtsubcatagoryname,con);

		con.gridx=0;
		con.gridy=3;
		panelwestcenter.add(lblsubusername,con);
		lblsubusername.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=3;
		panelwestcenter.add(txtsubusername,con);
		txtsubusername.setEditable(false);
		txtsubusername.setText("sourav");
		
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
		panelwestnorth.add(lblsubsearch);
		lblsubsearch .setFont(new Font("serif", Font.BOLD+Font.ITALIC, 20));
		panelwestnorth.add(photoofsearch);
		panelwestnorth.add(cmbsubsearch.cmbSuggest);
		cmbsubsearch.cmbSuggest.setPreferredSize(new Dimension(300,30));
	}
	private void panelwestsouthwork()
	{
		panelwestsouth.setBackground(Color.LIGHT_GRAY);
		panelwestsouth.setPreferredSize(new Dimension(600,150));
		panelwestsouth.setLayout(new FlowLayout());
		panelwestsouth.add(btnsubadd);
		btnsubadd.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		panelwestsouth.add(btnsubedit);
		btnsubedit.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		panelwestsouth.add(btnsubrefresh);
		btnsubrefresh.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		panelwestsouth.add(btnsubdelete);
		btnsubdelete.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	}

}
