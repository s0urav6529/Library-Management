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
import java.io.File;
import java.sql.ResultSet;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.Dbconection;
import com.admin.SuggestText;

public class Supplierinfo extends JPanel
{
	JPanel panelwest=new JPanel();
	JPanel panelcenter=new JPanel();

	SuggestText cmbsearch=new SuggestText();
	JLabel lblsearch=new JLabel("Search:  ");
	JLabel photoofsearch=new JLabel(new ImageIcon("image/search.png"));

	JPanel panelwestsouth=new JPanel();
	JPanel panelwestcenter=new JPanel();
	JPanel panelwestnorth=new JPanel();
	JPanel panelwestcenterwest=new JPanel();
	JPanel panelwestcentercenter=new JPanel();
	JPanel panelwestcentercenternorth=new JPanel();
	JPanel panelwestcentercentercenter=new JPanel();

	JButton btnadd=new  JButton("Add",new ImageIcon("image/add.png"));
	JButton btnedit=new  JButton("Edit",new ImageIcon("image/edit.png"));
	JButton btnrefresh=new  JButton("Refresh",new ImageIcon("image/refresh.png"));
	JButton btndelete=new  JButton("Delete",new ImageIcon("image/delete.png"));
	JButton btnupload=new JButton("Upload",new ImageIcon("image/Upload.png"));

	JLabel lblsupllierid=new JLabel("Supplier Id");
	JLabel lblsuplliername=new JLabel("Supplier Name");
	JLabel lblmailaddress=new JLabel("Mail Address");
	JLabel lblphonenumber=new JLabel("Phone Number");
	JLabel lbladress=new JLabel("Address");
	JLabel lbluser=new JLabel("username");
	JLabel photo=new JLabel();

	JTextField txtsuppllierid=new JTextField(20);
	JTextField txtsupplliername=new JTextField(20);
	JTextField txtmailadress=new JTextField(20);
	JTextField txtphonenumber=new JTextField(20);
	JTextField txtuser=new JTextField(20);
	JTextArea  txtadress=new JTextArea(5,3);
	JScrollPane scoll=new JScrollPane(txtadress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	String colname[]={"Supplier Id","Supplier Name", "Mobile Number"};
	Object rowname[][]={};
	DefaultTableModel model=new DefaultTableModel(rowname,colname);
	JTable table=new JTable(model);
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	boolean isupdate=false;
	boolean isdelete=false;

	JFileChooser filechooser=null;
	File file=null;
	int flag=0;
	public Supplierinfo()
	{
		panelwork();
		btnaction();
		eventlistener();
		initial(true);
	}
	private boolean initial(boolean a)
	{
		btnadd.setEnabled(a);
		btnedit.setEnabled(!a);
		return a;
	}
	private void eventlistener()
	{

		table.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				Suppliersearch(model.getValueAt(table.getSelectedRow(),0).toString());
			}
			public void keyPressed(KeyEvent arg0){}
		});

		table.addMouseListener(new MouseListener()
		{

			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				Suppliersearch(model.getValueAt(table.getSelectedRow(),0).toString());
			}
		});

		cmbsearch.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbsearch.txtSuggest.getText().trim().isEmpty())
				{
					String  idname=cmbsearch.txtSuggest.getText();
					StringTokenizer token=new StringTokenizer(idname,"#");
					Suppliersearch(token.nextToken());
				}
			}
		});
	}

	public void Suppliersearch(String id)
	{
		try
		{

			Dbconection.conect();
			String sql="select SupplierId,SupplierName,MailId,Address,PhoneNumber,username,picture from tbsupplierinfo where SupplierId='"+id+"'";
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			if(rs.next())
			{
				txtsuppllierid.setText(rs.getString("SupplierId"));
				txtsupplliername.setText(rs.getString("SupplierName"));
				txtmailadress.setText(rs.getString("MailId"));
				txtadress.setText(rs.getString("Address"));
				txtphonenumber.setText(rs.getString("PhoneNumber"));
				txtuser.setText(rs.getString("username"));
				
				if(!rs.getString("picture").toString().equalsIgnoreCase("0"))
				{
					Image img=Toolkit.getDefaultToolkit().getImage("F:/librarymanagementimage/supplierimage/"+txtsuppllierid.getText().toString()+".jpg");
					Image resize=img.getScaledInstance(photo.getWidth(), photo.getHeight(), Image.SCALE_DEFAULT);
					
					photo.setIcon(new ImageIcon(resize));
				}
				else{
					photo.setIcon(new ImageIcon(""));
				}
			}
			Dbconection.con.close();

			initial(false);

		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void btnaction()
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
									supautoid();
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
								supautoid();
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

				initial(true);
				isupdate=true;
			}
		});
		
		btnrefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				isupdate=false;
				supautoid();
				initial(true);
				reset();
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
						if(deletedata())
						{
							imagedelete();
							reset();
							tbdataload();
							JOptionPane.showMessageDialog(null, "All info deleted successfully !","Information..",JOptionPane.INFORMATION_MESSAGE);
							supautoid();

						}
					}
				}
			}
		});

	}
	private void uploadaction()
	{
		filechooser=new JFileChooser();
		filechooser.setDialogType(filechooser.OPEN_DIALOG);
		filechooser.setDialogTitle("Image selection");
		FileNameExtensionFilter filter=new FileNameExtensionFilter("Image", "jpg","png","gif");
		filechooser.setFileFilter(filter);
		filechooser.showOpenDialog(this);

		file=filechooser.getSelectedFile();

		if(file!=null)
		{

			Image img=Toolkit.getDefaultToolkit().getImage(file.getPath());
			Image resize=img.getScaledInstance( photo.getWidth(),photo.getHeight(),Image.SCALE_DEFAULT);

			photo.setIcon(new ImageIcon(resize));
			flag=1;
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Image/file not selected...!","Warning",JOptionPane.WARNING_MESSAGE);
		}

	}
	private void imagedelete()
	{
		String imagedestination="F://librarymanagementimage//supplierimage//"+txtsuppllierid.getText()+".jpg";
		File file=new File(imagedestination);
		if(file.exists())
		{
			file.delete();
		}
	}
	private boolean insertdata()
	{
		Dbconection.conect();
		String sql="insert into tbsupplierinfo (SupplierId,SupplierName,MailId,Address,picture,PhoneNumber,Username,EntryTime,UserIp)" +
				   " values ('"+txtsuppllierid.getText().trim()+"','"+txtsupplliername.getText().trim()+"','"+txtmailadress.getText().trim()+"'," +
				   "'"+txtadress.getText().trim()+"','"+txtsuppllierid.getText().trim()+".jpg','"+txtphonenumber.getText().trim()+"'," +
				   "'"+txtuser.getText().trim()+"',now(),'')";
		try
		{
			Dbconection.sta.executeUpdate(sql);
			Dbconection.con.close();

			if(flag==1)
			{
				BufferedImage buffer=ImageIO.read(file);
				String destination="F:\\librarymanagementimage\\supplierimage";
				File folderdsetination=new File(destination);
				if(!folderdsetination.isDirectory())
				{
					folderdsetination.mkdirs();
				}
				String imgdestination=destination+"//"+txtsuppllierid.getText().trim()+".jpg";
				File imagedestination=new File(imgdestination);
				if(imagedestination.exists())
				{
					imagedestination.delete();
				}

				ImageIO.write(buffer,"jpg" , imagedestination);
			}
			return true;
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp, "Error..",JOptionPane.ERROR_MESSAGE);
		}
		return false;

	}
	private boolean deletedata()
	{
		try
		{
			Dbconection.conect();
			String sql="delete from tbsupplierinfo where SupplierId='"+txtsuppllierid.getText().trim()+"'";
			System.out.println(sql);
			Dbconection.sta.executeUpdate(sql);
			Dbconection.con.close();
			
			return true;
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error..",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public void cmbsearchwork()
	{
		try
		{
			cmbsearch.v.clear();
			cmbsearch.v.add("");

			Dbconection.conect();
			String sql="select SupplierId,SupplierName from tbsupplierinfo order by suppliername";
			ResultSet rs=Dbconection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbsearch.v.add(rs.getString("SupplierId")+"#"+rs.getString("SupplierName"));
			}
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void tbdataload()
	{
		try
		{
			int a;
			for(a=table.getRowCount()-1;a>=0;a--)
			{
				model.removeRow(a);
			}
			Dbconection.conect();
			String sql="select SupplierId,SupplierName,PhoneNumber from tbsupplierinfo order by SupplierName";
			ResultSet rs=Dbconection.sta. executeQuery(sql);
			while(rs.next())
			{
				model.addRow(new Object[]{
						rs.getString("SupplierId"),
						rs.getString("SupplierName"),
						rs.getString("PhoneNumber")
				}
						);

			}
			Dbconection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void reset()
	{
		cmbsearch.txtSuggest.setText("");
		txtsupplliername.setText("");
		txtmailadress.setText("");
		txtadress.setText("");
		txtphonenumber.setText("");
		txtuser.setText("Sourav");
		photo.setIcon(new ImageIcon(""));
		file=null;
		isupdate=false;
		isdelete=false;
		flag=0;
		btnupload.setEnabled(true);
	}
	private boolean confirmation(String caption)
	{
		int a=JOptionPane.showConfirmDialog(null, caption,"Confirm",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
	private boolean chkvalidation()
	{
		if(!txtsupplliername.getText().isEmpty())
		{
			if(!txtmailadress.getText().isEmpty())
			{
				if(!txtadress.getText().isEmpty())
				{
					if(!txtphonenumber.getText().isEmpty())
					{
						if(!txtuser.getText().isEmpty())
						{
							if(file!=null||isupdate||isdelete)
							{
								return true;
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Select file plz ..!","Information",JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Insert username plz ..!","Information",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Insert PhoneNumber plz ..!","Information",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Insert Address plz ..!","Information",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Insert MailiId plz ..!","Information",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Insert suppliername plz ..!","Information",JOptionPane.INFORMATION_MESSAGE);

		}
		return false;
	}
	public  void supautoid()
	{
		try
		{

			com.Dbconection.conect();
			String sql="select ifnull(max(cast(substring(SupplierId,locate('-',SupplierId)+1,"+
					   "length(SupplierId)-locate('-',SupplierId))"+
					   "as UNSIGNED)),0)+1 id from tbsupplierinfo";
			ResultSet rs=com.Dbconection.sta.executeQuery(sql);

			if(rs.next())
			{
				txtsuppllierid.setText("Supllier-"+rs.getString("id"));
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
		add(panelcenter,BorderLayout.CENTER);
		panelcenterwork();
		add(panelwest,BorderLayout.WEST);
		panelwestwork();
	}
	
	private void panelcenterwork() 
	{
		panelcenter.setBackground(Color.LIGHT_GRAY);
		FlowLayout flow=new FlowLayout();
		panelcenter.setLayout(flow);
		panelcenter.add(scroll);
		table.getTableHeader().setReorderingAllowed(false);
		scroll.setPreferredSize(new Dimension(550,700));
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
		panelwestsouth.setPreferredSize(new Dimension(580,300));
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
		panelwestcenter.setLayout(new BorderLayout());
		panelwestcenter.add(panelwestcentercenter, BorderLayout.CENTER);
		panelwestcentercenterwork();
		panelwestcenter.add(panelwestcenterwest, BorderLayout.WEST);
		panelwestcenterwestwork();
	}
	
	private void panelwestcenterwestwork() 
	{
		panelwestcenterwest.setBackground(Color.LIGHT_GRAY);

		panelwestcenterwest.setPreferredSize(new Dimension(400,540));
		panelwestcenterwest.setLayout(new GridBagLayout());
		GridBagConstraints con=new GridBagConstraints();
		con.fill=GridBagConstraints.BOTH;
		con.insets=new Insets(5, 5, 5, 5);

		con.gridx=0;
		con.gridy=0;
		panelwestcenterwest.add(lblsupllierid,con);
		lblsupllierid.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=0;
		panelwestcenterwest.add(txtsuppllierid,con);
		txtsuppllierid.setEditable(false);

		con.gridx=0;
		con.gridy=1;
		panelwestcenterwest.add(lblsuplliername,con);
		lblsuplliername.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=1;
		panelwestcenterwest.add(txtsupplliername,con);

		con.gridx=0;
		con.gridy=2;
		panelwestcenterwest.add(lblmailaddress,con);
		lblmailaddress.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=2;
		panelwestcenterwest.add(txtmailadress,con);

		con.gridx=0;
		con.gridy=3;
		panelwestcenterwest.add(lblphonenumber,con);
		lblphonenumber.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=3;
		panelwestcenterwest.add(txtphonenumber,con);

		con.gridx=0;
		con.gridy=4;
		panelwestcenterwest.add(lbladress,con);
		lbladress.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=4;
		panelwestcenterwest.add(scoll,con);

		con.gridx=0;
		con.gridy=5;
		panelwestcenterwest.add(lblphonenumber,con);
		lblphonenumber.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=5;
		panelwestcenterwest.add(txtphonenumber,con);

		con.gridx=0;
		con.gridy=6;
		panelwestcenterwest.add(lbluser,con);
		lbluser.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

		con.gridx=1;
		con.gridy=6;
		panelwestcenterwest.add(txtuser,con);
		txtuser.setText("Sourav");
		txtuser.setEditable(false);
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

	private void panelwestcentercentercenterwork() 
	{
		panelwestcentercentercenter.setBackground(Color.LIGHT_GRAY);
		panelwestcentercentercenter.setLayout(new FlowLayout());
		panelwestcentercentercenter.add(btnupload);
		btnupload.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 15));

	}
	private void panelwestcentercenternorthwork() 
	{
		panelwestcentercenternorth.setBackground(Color.LIGHT_GRAY);

		panelwestcentercenternorth.setLayout(new BorderLayout());
		panelwestcentercenternorth.setPreferredSize(new Dimension(180,35));

		photo.setBorder(BorderFactory.createLineBorder(Color.black,1, true));
		photo.setPreferredSize(new Dimension(100,140));

		FlowLayout flow=new FlowLayout();
		flow.setVgap(8);
		panelwestcentercentercenter.setLayout(flow);
		panelwestcentercentercenter.add(photo);

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
		lblsearch.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 20));
		panelwestnorth.add(photoofsearch);
		panelwestnorth.add(cmbsearch.cmbSuggest);
		cmbsearch.cmbSuggest.setPreferredSize(new Dimension(300,30));

	}

}
