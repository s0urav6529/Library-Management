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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.Dbconection;
import com.admin.SuggestText;

public class Booksinfo extends JPanel
{
	JPanel panelwest=new JPanel();
	JPanel panelcenter=new JPanel();
	JPanel panelwestsouth=new JPanel();
	JPanel panelwestcenter=new JPanel();
	JPanel panelwestnorth=new JPanel();
	
	JLabel lblsearch=new JLabel("Search:  ");
	JLabel photoofsearch=new JLabel(new ImageIcon("image/search.png"));
	JLabel lblbookid=new JLabel("Book Id");
	JLabel lblcatagory=new JLabel("Catagory");
	JLabel lblsubcatagory=new JLabel("Sub Catagory");
	JLabel lblproductname=new JLabel("Book Name");
	JLabel lblbookdescription=new JLabel("Book Description");
	JLabel lblunit=new JLabel("Unit");
	JLabel lbldealerprice=new JLabel("Dealer Price");
	JLabel lbltradeprice=new JLabel("Trade Price");
	JLabel lblprofitperunit=new JLabel("Profit Per Unit");
	JLabel lblsuppliername=new JLabel("Supplier Name");
	JLabel lblusername=new JLabel("User Name");
	
	JTextField txtbookdescription=new JTextField(20);
	JTextField txtbookid=new JTextField(20);
	JTextField txtbookname=new JTextField(20);
	JTextField txtunit=new JTextField(20);
	JTextField txttradeprice=new JTextField(20);
	JTextField txtprofitperunit=new JTextField(20);
	JTextField txtsuppliername=new JTextField(20);
	JTextField txtdealerprice=new JTextField(20);
	JTextField txtusername=new JTextField(20);

	SuggestText cmbsearch=new SuggestText();
	JComboBox cmbcatagory=new JComboBox();
	SuggestText cmbsubcatagory=new SuggestText();
	SuggestText cmbsuppliername=new SuggestText();

	JButton btnadd=new  JButton("Add",new ImageIcon("image/add.png"));
	JButton btnedit=new  JButton("Edit",new ImageIcon("image/edit.png"));
	JButton btnrefresh=new  JButton("Refresh",new ImageIcon("image/refresh.png"));
	JButton btndelete=new  JButton("Delete",new ImageIcon("image/delete.png"));

	String colname[]={"Book Id","Book Name","Dealer Price", "Trade Price"};
	Object rowname[][]={};
	DefaultTableModel bookmodel=new DefaultTableModel(rowname,colname);
	JTable booktable=new JTable(bookmodel);
	JScrollPane scroll=new JScrollPane(booktable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	boolean isupdate=false;
	DecimalFormat decimalformet=new DecimalFormat("#0.00");
	public Booksinfo()
	{
		panelwork();
		btnaction();
		bookinitial(true);
		Keyandmouseevent();
	}
	
	private void searchdataload(String bookid)
	{
		try
		{
			String sql="select Bookid,subcatagoryid,subcatagoryname,catagoryid," +
					"catagoryname,bookname,bookdescription,unit,dealerprice,tradeprice," +
					"profitperunit,Supplierid,suppliername,username from tbbooksinfo where bookid like '"+bookid+"'";
			Dbconection.conect();
			
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			if(rs.next())
			{
				txtbookid.setText(rs.getString("Bookid"));
				cmbsubcatagory.txtSuggest.setText(rs.getString("subcatagoryid")+"#"+rs.getString("subcatagoryname"));
				cmbsubcatagory.cmbSuggest.setSelectedItem(rs.getString("subcatagoryid")+"#"+rs.getString("subcatagoryname"));
				txtbookname.setText(rs.getString("bookname"));
				txtbookdescription.setText(rs.getString("bookdescription"));
				txtunit.setText(rs.getString("unit"));
				txtdealerprice.setText(rs.getString("dealerprice"));
				txttradeprice.setText(rs.getString("tradeprice"));
				txtprofitperunit.setText(rs.getString("profitperunit"));
				cmbsuppliername.txtSuggest.setText(rs.getString("Supplierid")+"#"+rs.getString("suppliername"));
				txtusername.setText(rs.getString("username"));
			}
			Dbconection.con.close();
			bookinitial(false);
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void Keyandmouseevent() 
	{
			booktable.addMouseListener(new MouseListener() 
			{
				public void mouseReleased(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseClicked(MouseEvent arg0)
				{
					searchdataload(bookmodel.getValueAt(booktable.getSelectedRow(), 0).toString());
				}
			});
			booktable.addKeyListener(new KeyListener() 
			{
				public void keyTyped(KeyEvent arg0) {}
				public void keyReleased(KeyEvent arg0) 
				{
					searchdataload(bookmodel.getValueAt(booktable.getSelectedRow(), 0).toString());
				}
				public void keyPressed(KeyEvent arg0) {}
				
			});
			cmbsearch.cmbSuggest.addActionListener(new ActionListener() 
			{
				
				public void actionPerformed(ActionEvent arg0) 
				{
					if(!cmbsearch.txtSuggest.getText().trim().isEmpty())
					{
						String bookidname=cmbsearch.txtSuggest.getText();
						StringTokenizer token=new StringTokenizer(bookidname,"#");
						String bookid=token.nextToken();
						searchdataload(bookid);
					}
				}
			});
	}
	
	private void profitperunitcal()
	{
		double dealer,trade,ppunit;
		dealer=Double.parseDouble(txtdealerprice.getText().trim().isEmpty()?"0":txtdealerprice.getText().trim());
		trade=Double.parseDouble(txttradeprice.getText().trim().isEmpty()?"0":txttradeprice.getText().trim());
		ppunit=trade-dealer;
		txtprofitperunit.setText(decimalformet.format(ppunit));
	}
	private boolean bookinitial(boolean b)
	{
		btnadd.setEnabled(b);
		btnedit.setEnabled(!b);
		return b;
	}
	private void reset()
	{
		cmbsearch.txtSuggest.setText("");
		cmbcatagory.removeAllItems();
		cmbsubcatagory.txtSuggest.setText("");
		txtbookname.setText("");
		txtbookdescription.setText("");
		txtunit.setText("");
		txtdealerprice.setText("");
		txttradeprice.setText("");
		txtprofitperunit.setText("0.00");
		cmbsuppliername.txtSuggest.setText("");
		txtusername.setText("Sourav");	
	}
	public void suppliernameload()
	{
		try
		{
			cmbsuppliername.v.clear();
			cmbsuppliername.v.add("");
			
			Dbconection.conect();
			String sql="select supplierid,suppliername from tbsupplierinfo order by suppliername";
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbsuppliername.v.add(rs.getString("supplierid")+"#"+rs.getString("suppliername"));
			}
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error7",JOptionPane.ERROR_MESSAGE);
		}
	}
	private boolean chkvalidation()
	{
		if(!txtbookid.getText().isEmpty()){
				if(!cmbsubcatagory.txtSuggest.getText().isEmpty()){
					if(!txtbookname.getText().isEmpty()){
						if(!txtbookdescription.getText().isEmpty()){
							if(!txtunit.getText().isEmpty()){
								if(!txtdealerprice.getText().isEmpty()){
									if(!txttradeprice.getText().isEmpty()){
										if(!txtprofitperunit.getText().isEmpty()){
											if(!cmbsuppliername.txtSuggest.getText().isEmpty()){
												if(!txtusername.getText().isEmpty())
												{
													return true;
													
												}
												else
												{
													JOptionPane.showMessageDialog(null, "Insert username  plz !","Warning...",JOptionPane.WARNING_MESSAGE);
												}
											}
											else
											{
												JOptionPane.showMessageDialog(null, "Select suppliername plz !","Warning...",JOptionPane.WARNING_MESSAGE);
											}
										}
										else
										{
											JOptionPane.showMessageDialog(null, "Insert Profit per unit plz !","Warning...",JOptionPane.WARNING_MESSAGE);
										}
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Insert trade price plz !","Warning...",JOptionPane.WARNING_MESSAGE);
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Insert M.R.P price plz !","Warning...",JOptionPane.WARNING_MESSAGE);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Insert Unit plz !","Warning...",JOptionPane.WARNING_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Insert product description plz !","Warning...",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Insert product name plz !","Warning...",JOptionPane.WARNING_MESSAGE);
					}
				}
			else
			{
				JOptionPane.showMessageDialog(null, "Select subcatagory plz ! !","Warning...",JOptionPane.WARNING_MESSAGE);
			}	
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Insert Book id plz !","Warning...",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean deletedata()
	{
		try
		{
			Dbconection.conect();
			String sql="delete from tbbooksinfo where bookid='"+txtbookid.getText().trim()+"";
			Dbconection.sta.executeUpdate(sql);
			Dbconection.con.close();
			return true;
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error9..",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public void tbdataload()
	{
		try
		{
			int a;
			for(a=booktable.getRowCount()-1;a>=0;a--)
			{
				bookmodel.removeRow(a);
			}
			Dbconection.conect();
			String sql="select bookid,bookname,dealerprice,tradeprice from tbbooksinfo order by bookname ";
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				bookmodel.addRow(new Object[]
						{
							rs.getString("bookid"),
							rs.getString("bookname"),
							rs.getString("dealerprice"),
							rs.getString("tradeprice")
						});	
			}
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error...",JOptionPane.ERROR_MESSAGE);
		}
	}
	private boolean confirmation(String caption)
	{
		int a=JOptionPane.showConfirmDialog(null,caption,"confirm..",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
	private void btnaction()
	{
		btnadd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(chkvalidation())
				{
					if(confirmation(isupdate?"Do you want to Edit ?":"Do you want to Save ?"))
					{
						if(isupdate)
						{
							if(deletedata())
							{
								if(insertdata())
									{
										tbdataload();
										reset();
										JOptionPane.showMessageDialog(null, "All infomation edited Successfully !","Information",JOptionPane.INFORMATION_MESSAGE);
										cmbsearchwork();
										booksifoautoid();
									}
							}
						}
						else
						{
							if(insertdata())
							{
								tbdataload();
								reset();
								JOptionPane.showMessageDialog(null, "All infomation saved Successfully !","Information",JOptionPane.INFORMATION_MESSAGE);
								cmbsearchwork();
								booksifoautoid();
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
				bookinitial(true);
				isupdate=true;
			}
		});
		
		btnrefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				bookinitial(true);
				reset();
				booksifoautoid();
			}
		});
		
		btndelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				if(chkvalidation())
				{
					if(confirmation("Do you want to Delete ?"))
					{
						deletedata();
						tbdataload();
						reset();
						JOptionPane.showMessageDialog(null, "All info deleted Successfully !","Info...",JOptionPane.INFORMATION_MESSAGE);
						booksifoautoid();
					}
				}
			}
		});
		cmbsubcatagory.cmbSuggest.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbsubcatagory.txtSuggest.getText().isEmpty())
				{
					String subcatidname=cmbsubcatagory.txtSuggest.getText();
					StringTokenizer token=new StringTokenizer(subcatidname,"#");
					String subid=token.nextToken();
					cmbcatdataload(subid);
				}
				else
				{
					cmbcatagory.removeAllItems();
				}
			}
		});	
		
		txttradeprice.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				profitperunitcal();
			}
			public void keyPressed(KeyEvent arg0) {}
		});
        txttradeprice.addKeyListener(new KeyListener() 
        {
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				profitperunitcal();
			}
			public void keyPressed(KeyEvent arg0) {}
		});
	}
	public void cmbsearchwork()
	{
		try
		{
			cmbsearch.v.clear();
			cmbsearch.v.add("");
			String sql="select bookid,bookname from tbbooksinfo order by bookname";
			Dbconection.conect();
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbsearch.v.add(rs.getString("bookid")+"#"+rs.getString("bookname"));
			}
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error...",JOptionPane.ERROR_MESSAGE);
		}
	}
	private boolean insertdata()
	{
		String catidname=cmbcatagory.getSelectedItem().toString();
		StringTokenizer cattoken=new StringTokenizer(catidname,"#");
		String catid=cattoken.nextToken();
		String catname=cattoken.nextToken();
		
		String subcatidname=cmbsubcatagory.txtSuggest.getText();
		StringTokenizer subcattoken=new StringTokenizer(subcatidname,"#");
		String subcatid=subcattoken.nextToken();
		String subcatname=subcattoken.nextToken();
		
		String supidname=cmbsuppliername.txtSuggest.getText();
		StringTokenizer suptoken=new StringTokenizer(supidname,"#");
		String supid=suptoken.nextToken();
		String supname=suptoken.nextToken();
		
		String sql="insert into tbbooksinfo (Bookid,subcatagoryid,subcatagoryname,catagoryid,catagoryname,bookname,bookdescription,unit,"+
				   "dealerprice,tradeprice,profitperunit,Supplierid,suppliername,username,entrytime,userip) "+
                   "values ('"+txtbookid.getText().trim()+"','"+subcatid+"','"+subcatname+"','"+catid+"','"+catname+"'," +
                   "'"+txtbookname.getText().trim()+"','"+txtbookdescription.getText().trim()+"','"+txtunit.getText().trim()+"'," +
                   "'"+txtdealerprice.getText().trim()+"','"+txttradeprice.getText().trim()+"','"+txtprofitperunit.getText().trim()+"','"+supid+"'," +
                   "'"+supname+"','"+txtusername.getText().trim()+"',now(),'')";
		
		Dbconection.conect();
		try
		{
			Dbconection.sta.executeUpdate(sql);
			Dbconection.con.close();
			return true;
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error8...",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public void subcatagorydataload()
	{
		try
		{
			cmbsubcatagory.v.clear();
			cmbsubcatagory.v.add("");
			cmbcatagory.removeAllItems();
			
			String sql="select subcatagoryid,subcatagoryname from tbsubcatagoryinfo order by subcatagoryname";
			com.Dbconection.conect();
			ResultSet rs=com.Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbsubcatagory.v.add(rs.getString("subcatagoryid")+"#"+rs.getString("subcatagoryname"));
			}
			com.Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp, "Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void cmbcatdataload(String subid)
	{	
		try
		{
			cmbcatagory.removeAllItems();
			String sql="select catagoryid,catagoryname from tbsubcatagoryinfo where subcatagoryid like '"+subid+"'";
			Dbconection.conect();
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbcatagory.addItem(rs.getString("catagoryid")+"#"+rs.getString("catagoryname"));
			}	
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e,"Error.",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void booksifoautoid()
	{
		try
		{	
			Dbconection.conect();
			String sql="select ifnull(max(cast(substring(bookid,locate('-',bookid)+1,length(bookid)-locate('-',bookid))"+
					   " as UNSIGNED)),0)+1 id from tbbooksinfo";
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			if(rs.next())
			{
				String id=rs.getString("id");
				txtbookid.setText("Book-"+id);
			}
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void panelwork() 
	{
		setLayout(new BorderLayout());
		add(panelcenter,BorderLayout.CENTER);
		panelcenterwork();
		add(panelwest,BorderLayout.WEST);
		panelwestwork();
	}
	private void panelwestwork() 
	{
		panelwest.setBackground(Color.LIGHT_GRAY);
		panelwest.setLayout(new BorderLayout());
		panelwest.setPreferredSize(new Dimension(580,740));
		panelwest.setBorder(BorderFactory.createRaisedBevelBorder());
		panelwest.add(panelwestnorth, BorderLayout.NORTH);
		panelwestnorthwork();
		panelwest.add(panelwestcenter, BorderLayout.CENTER);
		panelwestcenterwork();
		panelwest.add(panelwestsouth, BorderLayout.SOUTH);
		panelwestsouthwork();
	}
	private void panelwestsouthwork() 
	{
		panelwestsouth.setBackground(Color.LIGHT_GRAY);
		panelwestsouth.setPreferredSize(new Dimension(580,150));
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
		panelwestcenter.setPreferredSize(new Dimension(400,540));
		panelwestcenter.setLayout(new GridBagLayout());
		GridBagConstraints con=new GridBagConstraints();
		con.fill=GridBagConstraints.BOTH;
		con.insets=new Insets(5, 5, 5, 5);
		con.gridx=0;
		con.gridy=0;
		panelwestcenter.add(lblbookid,con);
		lblbookid.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		con.gridx=1;
		con.gridy=0;
		panelwestcenter.add(txtbookid,con);
		txtbookid.setEditable(false);
		
		con.gridx=0;
		con.gridy=1;
		panelwestcenter.add(lblsubcatagory,con);
		lblsubcatagory.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=1;
		panelwestcenter.add(cmbsubcatagory.cmbSuggest,con);
		
		con.gridx=0;
		con.gridy=2;
		panelwestcenter.add(lblcatagory,con);
		lblcatagory.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=2;
		panelwestcenter.add(cmbcatagory,con);

		con.gridx=0;
		con.gridy=3;
		panelwestcenter.add(lblproductname,con);
		lblproductname.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=3;
		panelwestcenter.add(txtbookname,con);

		con.gridx=0;
		con.gridy=4;
		panelwestcenter.add(lblbookdescription,con);
		lblbookdescription.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=4;
		panelwestcenter.add(txtbookdescription,con);
	
		con.gridx=0;
		con.gridy=5;
		panelwestcenter.add(lblunit,con);
		lblunit.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=5;
		panelwestcenter.add(txtunit,con);
		
		con.gridx=0;
		con.gridy=6;
		panelwestcenter.add(lbldealerprice,con);
		lbldealerprice.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=6;
		panelwestcenter.add(txtdealerprice,con);
		
		con.gridx=0;
		con.gridy=7;
		panelwestcenter.add(lbltradeprice,con);
		lbltradeprice.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=7;
		panelwestcenter.add(txttradeprice,con);

		con.gridx=0;
		con.gridy=8;
		panelwestcenter.add(lblprofitperunit,con);
		lblprofitperunit.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	
		con.gridx=1;
		con.gridy=8;
		panelwestcenter.add(txtprofitperunit,con);
		
		con.gridx=0;
		con.gridy=9;
		panelwestcenter.add(lblsuppliername,con);
		lblsuppliername.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		con.gridx=1;
		con.gridy=9;
		panelwestcenter.add(cmbsuppliername.cmbSuggest,con);
		
		con.gridx=0;
		con.gridy=10;
		panelwestcenter.add(lblusername,con);
		lblusername.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		con.gridx=1;
		con.gridy=10;
		panelwestcenter.add(txtusername,con);
		txtusername.setText("sourav");
	}
	private void panelwestnorthwork() 
	{
		panelwestnorth.setBackground(Color.LIGHT_GRAY);
		panelwestnorth.setPreferredSize(new Dimension(580,100));
		panelwestnorth.setBorder(BorderFactory.createRaisedBevelBorder());
		FlowLayout flow=new FlowLayout();
		panelwestnorth.setLayout(flow);
		flow.setVgap(30);
		panelwestnorth.add(lblsearch);
		lblsearch .setFont(new Font("serif", Font.BOLD+Font.ITALIC, 20));
		panelwestnorth.add(photoofsearch);
		panelwestnorth.add(cmbsearch.cmbSuggest);
		cmbsearch.cmbSuggest.setPreferredSize(new Dimension(300,30));	
	}
	private void panelcenterwork()
	{
		panelcenter.setBackground(Color.LIGHT_GRAY);
		FlowLayout flow=new FlowLayout();
		panelcenter.setLayout(flow);
		panelcenter.add(scroll);
		booktable.getTableHeader().setReorderingAllowed(false);
		scroll.setPreferredSize(new Dimension(550,700));
	}
}
