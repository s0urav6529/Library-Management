package com.Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.Dbconection;
import com.admin.SuggestText;
import com.toedter.calendar.JDateChooser;

public class Newuser extends JPanel
{
	JPanel panelcenter=new JPanel();
	JPanel panelwest=new JPanel();
	
	JPanel panelwestnorth=new JPanel();
	JPanel panelwestsouth=new JPanel();
	
	JPanel panelwestcenter=new JPanel();
	JPanel panelwestcenterwest=new JPanel();
	JPanel panelwestcentercenter=new JPanel();
	
	JPanel panelwestcentercenternorth=new JPanel();
	JPanel panelwestcentercentercenter=new JPanel();

	JLabel lblpaswwordhint=new JLabel("Password Hint");
	JLabel lbluserid=new JLabel("User ID");
	JLabel lblname=new JLabel("Name");
	JLabel lblusername=new JLabel("User Name");
	JLabel lbldesignation=new JLabel("Designation");
	JLabel lblpassword=new JLabel("Password");
	JLabel lbldate=new JLabel("Date Of Join");
	JLabel lblactivition=new JLabel("Activition");
	JLabel lblemail=new JLabel("Email-Address");
	JLabel lblnationalid=new JLabel("National ID");
	JLabel lbladdress=new JLabel("Address");
	JLabel lblusertype=new JLabel("UserType");
	JLabel lblmobile=new JLabel("Mobile");
	JLabel newuserphoto=new JLabel();
	JLabel lblsearch=new JLabel("Search:  ");
	JLabel photoofsearch=new JLabel(new ImageIcon("image/search.png"));
	
	SuggestText cmbsearch=new SuggestText();
	SuggestText cmbactivation=new SuggestText();
	SuggestText cmbusertype=new SuggestText();
	
	JTextField txtpasswordhint=new JTextField(11);
	JTextField txtuserid=new JTextField(15);
	JTextField txtname=new JTextField(11);
	JTextField txtusername=new JTextField(11);
	JTextField txtdesignation=new JTextField(11);
	JPasswordField txtpassword=new JPasswordField(11);
	JDateChooser txtdate=new JDateChooser();
	JTextField txtemail=new JTextField(11);
	JTextField txtnationalid=new JTextField(11);
	JTextArea txtaddress=new JTextArea(3,11);
	JTextField txtmobile=new JTextField(11);
	JScrollPane addressscroll=new JScrollPane(txtaddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	JButton btnadd=new  JButton("Add",new ImageIcon("image/add.png"));
	JButton btnedit=new  JButton("Edit",new ImageIcon("image/edit.png"));
	JButton btnrefresh=new  JButton("Refresh",new ImageIcon("image/refresh.png"));
	JButton btndelete=new  JButton("Delete",new ImageIcon("image/delete.png"));
	JButton btnupload=new JButton("Upload",new ImageIcon("image/Upload.png"));
	JButton btnprint=new JButton("Print",new ImageIcon("image/print.png"));
	
	String colname[]={"User Id","UserName", "Activation","Email"};
	Object rowname[][]={};
	DefaultTableModel newusermodel=new DefaultTableModel(rowname,colname);
	JTable newusertable=new JTable(newusermodel);
	JScrollPane newuserscroll=new JScrollPane(newusertable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	int flag=0;
	boolean isdelete=false;
	boolean isupdate=false;
	JFileChooser filechosser=null;
	File file=null;
	
	public Newuser()
	{
		panelwork();
		btnaction();
		mousekeyevent();
		userinitial(true);
	}
	
	private boolean userinitial(boolean b) 
	{
		btnadd.setEnabled(b);
		btnedit.setEnabled(!b);
		return b;
	}
	
	private void datasearch(String id)
	{
		try
		{
			Dbconection.conect();
			String sql="select newuserid,name,username,designation,password,passwordhint,dateofjoin,activation,emailaddress,nationalid,usertype,address,picture,mobile from tbnewuserinfo where newuserid='"+id+"'";
			
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			if(rs.next())
			{
				txtuserid.setText(rs.getString("newuserid"));
				txtname.setText(rs.getString("name"));
				txtusername.setText(rs.getString("username"));
				txtdesignation.setText(rs.getString("designation"));
				txtpassword.setText(rs.getString("password"));
				txtpasswordhint.setText(rs.getString("passwordhint"));
				txtdate.setDate(rs.getDate("dateofjoin"));
				cmbactivation.txtSuggest.setText(rs.getString("activation"));
				txtemail.setText(rs.getString("emailaddress"));
				txtnationalid.setText(rs.getString("nationalid"));
				cmbusertype.txtSuggest.setText(rs.getString("usertype"));
				txtaddress.setText(rs.getString("address"));
				txtmobile.setText(rs.getString("mobile"));
				
				if(!rs.getString("picture").toString().equalsIgnoreCase("0"))
				{
					Image img=Toolkit.getDefaultToolkit().getImage("F:/librarymanagementimage/Newuser/"+txtuserid.getText().toString()+".jpg");
					Image resize=img.getScaledInstance(newuserphoto.getWidth(), newuserphoto.getHeight(), img.SCALE_DEFAULT);
					
					newuserphoto.setIcon(new ImageIcon(resize));
				}
				else
				{
					newuserphoto.setIcon(new ImageIcon(""));
				}
			}
			Dbconection.con.close();
			userinitial(false);
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	private void mousekeyevent()
	{
		newusertable.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0){}
			public void mouseClicked(MouseEvent arg0) 
			{
				datasearch(newusermodel.getValueAt(newusertable.getSelectedRow(),0).toString());	
			}
		});
		
		newusertable.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0){}
			public void keyReleased(KeyEvent arg0) 
			{
				datasearch(newusermodel.getValueAt(newusertable.getSelectedRow(), 0).toString());
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
					datasearch(token.nextToken());
				}
			}
			
		});
	}

	public void cmbloadsearch()
	{
		try
		{
			cmbsearch.v.clear();
			cmbsearch.v.add("");
			
			Dbconection.conect();
			String sql="select newuserid,username from tbnewuserinfo";
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbsearch.v.add(rs.getString("newuserid")+"#"+rs.getString("username"));
			}
			
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp,"Error",JOptionPane.ERROR_MESSAGE);
		}	
	}
	private boolean deletedata()
	{
		try
		{
			Dbconection.conect();
			String sql="delete from tbnewuserinfo where newuserid='"+txtuserid.getText().trim()+"'";
			Dbconection.sta.executeUpdate(sql);
			Dbconection.con.close();
			return true;
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp ,"Error..",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	private void uploadaction()
	{
		filechosser=new JFileChooser();
		filechosser.setDialogType(filechosser.OPEN_DIALOG);
		filechosser.setDialogTitle("Image selection");
		FileNameExtensionFilter filter=new FileNameExtensionFilter("Image", "jpg","png","gif");
		filechosser.setFileFilter(filter);
		filechosser.showOpenDialog(this);

		file=filechosser.getSelectedFile();

		if(file!=null)
		{

			Image img=Toolkit.getDefaultToolkit().getImage(file.getPath());
			Image resize=img.getScaledInstance( newuserphoto.getWidth(),newuserphoto.getHeight(),Image.SCALE_DEFAULT);
			newuserphoto.setIcon(new ImageIcon(resize));
			flag=1;
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Image/file not selected...!","Warning",JOptionPane.WARNING_MESSAGE);
		}

	}
	public void btnaction()
	{
		btnupload.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				uploadaction();
			}
		});
		
		btnadd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(chkvalidation())
				{
					if(confirmation(isupdate?"Do you want to edit ?":"Do you want to save ?"))
					{
						if(isupdate)
						{
							if(deletedata())
							{
								if(insertdata())
								{
									tbdataload();
									reset();
									JOptionPane.showMessageDialog(null, "All information edited successfully !","Info",JOptionPane.INFORMATION_MESSAGE);
									newuserautoid();
									cmbloadsearch();
								}
							}
						}
						else
						{
							if(insertdata())
							{
								tbdataload();
								reset();
								JOptionPane.showMessageDialog(null, "All information saved successfully !","Info",JOptionPane.INFORMATION_MESSAGE);
								newuserautoid();
								cmbloadsearch();
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
				userinitial(true);
				isupdate=true;
			}
		});
		
		btnrefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				userinitial(true);
				reset();
				newuserautoid();	
			}
		});
		
		btndelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				isdelete=true;
				if(chkvalidation())
				{
					if(confirmation("Sure to delete ?"))
					{
						deletedata();
						tbdataload();
						reset();
						JOptionPane.showMessageDialog(null, "All info deleted Successfully !","Info...",JOptionPane.INFORMATION_MESSAGE);
						newuserautoid();
						cmbloadsearch();
					}
				}
			}
		});
		
		btnprint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JOptionPane.showMessageDialog(null, "Hellow Boss:Please plugged in !");	
			}
		});
	}
	private void reset()
	{
		txtuserid.setText("");
		txtname.setText("");
		txtusername.setText("");
		txtdesignation.setText("");
		txtpassword.setText("");
		txtpasswordhint.setText("");
		txtdate.setDate(new Date());
		cmbactivation.txtSuggest.setText("");
		txtemail.setText("");
		txtnationalid.setText("");
		cmbusertype.txtSuggest.setText("");
		txtaddress.setText("");
		txtmobile.setText("");
		cmbsearch.txtSuggest.setText("");
		file=null;
		isupdate=false;
		isdelete=false;
		flag=0;
		newuserphoto.setIcon(new ImageIcon(""));
	}
	
	private boolean insertdata()
	{
		try
		{
			String dateofjoin=new SimpleDateFormat("yyyy-MM-dd").format(txtdate.getDate());
			String passhint="";
			if(!txtaddress.getText().isEmpty())
			{
				passhint=txtpasswordhint.getText().trim();
			}
			
			String sql="insert into tbnewuserinfo(newuserid,name,username,designation,password,passwordhint,dateofjoin,activation,emailaddress,nationalid,usertype,address,picture,mobile,EntryTime,UserIp) " +
					   "values('"+txtuserid.getText().trim()+"','"+txtname.getText().trim()+"','"+txtusername.getText().trim()+"','"+txtdesignation.getText().trim()+"','"+txtpassword.getText().trim()+"','"+passhint+"','"+dateofjoin+"'," +
					   "'"+cmbactivation.txtSuggest.getText().trim()+"','"+txtemail.getText().trim()+"','"+txtnationalid.getText().trim()+"','"+cmbusertype.txtSuggest.getText().trim()+"','"+txtaddress.getText().trim()+"'," +
					   "'"+txtuserid.getText().trim()+".jpg','"+txtmobile.getText().trim()+"',now(),'')";
			Dbconection.conect();
			Dbconection.sta.executeUpdate(sql);
			Dbconection.con.close();
			
			if(flag==1)
			{
				BufferedImage buffer=ImageIO.read(file);
				
				String destination="F:\\librarymanagementimage\\Newuser";
				File folderdestination=new File(destination);
				
				if(!folderdestination.isDirectory())
				{
					folderdestination.mkdirs();
				}
				
				String imgdestination=destination+"//"+txtuserid.getText().trim()+".jpg";
				File imagedestination=new File(imgdestination);
				if(!imagedestination.exists())
				{
					imagedestination.delete();
				}
				
				ImageIO.write(buffer,"jpg", imagedestination);
			}
			return true;
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp,"Error1",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	public void tbdataload()
	{
		try
		{
			int a;
			
			for(a=newusertable.getRowCount()-1;a>=0;a--)
			{
				newusermodel.removeRow(a);
			}
			Dbconection.conect();
			String sql="select newuserid,username ,activation,emailaddress from tbnewuserinfo order by username";
			
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				newusermodel.addRow(new Object[]
						{
							rs.getString("newuserid"),
							rs.getString("username"),
							rs.getString("activation"),
							rs.getString("emailaddress")
			
						});
			}
			Dbconection.con.close();
		
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp,"error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public boolean chkvalidation()
	{
		if(!txtuserid.getText().isEmpty()){
			if(!txtname.getText().isEmpty()){
				if(!txtusername.getText().isEmpty()){
					if(!txtdesignation.getText().isEmpty()){
						if(!txtpassword.getText().isEmpty()){
							if(!cmbactivation.txtSuggest.getText().isEmpty()){
								if(!txtemail.getText().isEmpty()){
									if(!txtnationalid.getText().isEmpty()){
										if(!cmbusertype.txtSuggest.getText().isEmpty()){
											if(!txtaddress.getText().isEmpty()){
												if(!txtmobile.getText().isEmpty()){
													if(file!=null||isupdate||isdelete)
													{
														return true;
													}
													else
													{
														JOptionPane.showMessageDialog(null, "Select file/img plz !","Warning ...",JOptionPane.WARNING_MESSAGE);
													}
												}
												else
												{
													JOptionPane.showMessageDialog(null, "Select mobileno plz !","Warning ...",JOptionPane.WARNING_MESSAGE);
												}
											}
											else
											{
												JOptionPane.showMessageDialog(null, "Insert address plz !","Warning...",JOptionPane.WARNING_MESSAGE);
											}
										}
										else
										{
											JOptionPane.showMessageDialog(null, "Insert usertype plz !","Warning...",JOptionPane.WARNING_MESSAGE);
										}
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Insert Nationalid plz !","Warning...",JOptionPane.WARNING_MESSAGE);
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Insert Emailid plz !","Warning...",JOptionPane.WARNING_MESSAGE);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Insert Activation plz !","Warning...",JOptionPane.WARNING_MESSAGE);	
							}	
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Insert Password plz !","Warning...",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Insert Designation plz !","Warning...",JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Insert UserName plz !","Warning...",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Insert Name plz !","Warning...",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Insert UserId plz !","Warning...",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean confirmation(String caption)
	{
		int a=JOptionPane.showConfirmDialog(null, caption,"Confirm...",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void newuserautoid()
	{
		try
		{
			com.Dbconection.conect();
			String sql="select ifnull(max(cast(substring" +
					"(newuserid,locate('-',newuserid)+1,length(newuserid)-locate('-',newuserid))" +
					"as UNSIGNED)),0)+1 id from tbnewuserinfo";
			
			ResultSet rs=com.Dbconection.sta.executeQuery(sql);
			
			if(rs.next())
			{
				txtuserid.setText("User-"+rs.getString("id"));
			}
			com.Dbconection.con.close();
		}
		
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp,"Error..",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void panelwork()
	{
		setLayout(new BorderLayout());
		add(panelcenter,BorderLayout.CENTER);
		panelcenterwork();
		add(panelwest,BorderLayout.WEST);
		panelwest.setPreferredSize(new Dimension(650,0));
		panelwest.setBorder(BorderFactory.createRaisedBevelBorder());
		panelwestwork();
	}
	private void panelwestwork() 
	{
		panelwest.setLayout(new BorderLayout());
		panelwest.add(panelwestcenter,BorderLayout.CENTER);
		panelwestcenterwork();
		panelwest.add(panelwestnorth,BorderLayout.NORTH);
		panelwestnorthwork();
		panelwest.add(panelwestsouth,BorderLayout.SOUTH);
		panelwestsouthwork();
		
	}
	
	private void panelwestcenterwork()
	{
		panelwestcenter.setLayout(new BorderLayout());
		panelwestcenter.add(panelwestcenterwest,BorderLayout.WEST);
		panelwestcenterwestwork();
		panelwestcenter.add(panelwestcentercenter,BorderLayout.CENTER);
		panelwestcentercenterwork();
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
		panelwestcentercenternorth.setPreferredSize(new Dimension(180,70));
		newuserphoto.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		newuserphoto.setPreferredSize(new Dimension(100,140));
		FlowLayout flow=new FlowLayout();
		panelwestcentercentercenter.setLayout(flow);
		panelwestcentercentercenter.add(newuserphoto);
		flow.setVgap(20);
	}
	
	private void panelwestcentercentercenterwork()
	{
		panelwestcentercentercenter.setBackground(Color.LIGHT_GRAY);
		panelwestcentercentercenter.setLayout(new FlowLayout());
		panelwestcentercentercenter.add(btnupload);
		btnupload.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
	}
	
	private void panelwestsouthwork() 
	{
		panelwestsouth.setPreferredSize(new Dimension(0,100));
		panelwestsouth.setBorder(BorderFactory.createRaisedBevelBorder());
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
	
	private void panelwestnorthwork()
	{
		panelwestnorth.setPreferredSize(new Dimension(0,70));
		panelwestnorth.setBorder(BorderFactory.createRaisedBevelBorder());
		FlowLayout flow=new FlowLayout();
		panelwestnorth.setLayout(flow);
		flow.setVgap(20);
		panelwestnorth.add(lblsearch);
		lblsearch.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 25));
		panelwestnorth.add(photoofsearch);
		panelwestnorth.add(cmbsearch.cmbSuggest);
		cmbsearch.cmbSuggest.setPreferredSize(new Dimension(300,30));
	}
	
	private void panelcenterwork() 
	{
		panelcenter.setBackground(Color.LIGHT_GRAY);
		FlowLayout flow=new FlowLayout();
		panelcenter.setLayout(flow);
		panelcenter.add(newuserscroll);
		newusertable.getTableHeader().setReorderingAllowed(false);
		newuserscroll.setPreferredSize(new Dimension(500,670));
		panelcenter.add(btnprint);
		btnprint.setPreferredSize(new Dimension(500,50));
		btnprint.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 20));
	}

	private void panelwestcenterwestwork()
	{
		panelwestcenterwest.setPreferredSize(new Dimension(470,570));
		panelwestcenterwest.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints c=new GridBagConstraints();
		panelwestcenterwest.setLayout(new GridBagLayout());
		
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(5,5,5,5);
		c.fill=GridBagConstraints.BOTH;
		
		panelwestcenterwest.add(lbluserid,c);
		lbluserid.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=0;
		panelwestcenterwest.add(txtuserid,c);
		txtuserid.setEditable(false);
		
		c.gridx=0;
		c.gridy=1;
		
		panelwestcenterwest.add(lblname,c);
		lblname.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=1;
		panelwestcenterwest.add(txtname,c);
		
		c.gridx=0;
		c.gridy=2;
		panelwestcenterwest.add(lblusername,c);
		lblusername.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=2;
		panelwestcenterwest.add(txtusername,c);
		
		c.gridx=0;
		c.gridy=3;
		panelwestcenterwest.add(lbldesignation,c);
		lbldesignation.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=3;
		panelwestcenterwest.add(txtdesignation,c);
		
		c.gridx=0;
		c.gridy=4;
		panelwestcenterwest.add(lblpassword,c);
		lblpassword.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=4;
		panelwestcenterwest.add(txtpassword,c);
		
		c.gridx=0;
		c.gridy=5;
		panelwestcenterwest.add(lblpaswwordhint,c);
		lblpaswwordhint.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=5;
		panelwestcenterwest.add(txtpasswordhint,c);
		
		c.gridx=0;
		c.gridy=6;
		panelwestcenterwest.add(lbldate,c);
		lbldate.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=6;
		panelwestcenterwest.add(txtdate,c);
		txtdate.setDateFormatString("dd-MM-yy");
		txtdate.setDate(new Date());
		
		c.gridx=0;
		c.gridy=7;
		panelwestcenterwest.add(lblactivition,c);
		lblactivition.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=7;
		panelwestcenterwest.add(cmbactivation.cmbSuggest,c);
		cmbactivation.cmbSuggest.setPreferredSize(new Dimension(300,30));
		cmbactivation.v.add("");
		cmbactivation.v.add("Yes");
		cmbactivation.v.add("No");
		
		c.gridx=0;
		c.gridy=8;
		panelwestcenterwest.add(lblemail,c);
		lblemail.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=8;
		panelwestcenterwest.add(txtemail,c);

		c.gridx=0;
		c.gridy=9;
		panelwestcenterwest.add(lblnationalid,c);
		lblnationalid.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=9;
		panelwestcenterwest.add(txtnationalid,c);

		c.gridx=0;
		c.gridy=10;
		panelwestcenterwest.add(lblusertype,c);
		lblusertype.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=10;
		panelwestcenterwest.add(cmbusertype.cmbSuggest,c);
		cmbusertype.v.add("");
		cmbusertype.v.add("Admin");
		cmbusertype.v.add("SuperAdmin");
		cmbusertype.v.add("Executive");

		c.gridx=0;
		c.gridy=11;
		panelwestcenterwest.add(lbladdress,c);
		lbladdress.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=11;
		panelwestcenterwest.add(addressscroll,c);

		c.gridx=0;
		c.gridy=12;
		panelwestcenterwest.add(lblmobile,c);
		lblmobile.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));
		
		c.gridx=1;
		c.gridy=12;
		panelwestcenterwest.add(txtmobile,c);		
		
	}
}
