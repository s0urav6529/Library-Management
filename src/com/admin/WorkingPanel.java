package com.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.Menu.Booksinfo;
import com.Menu.Borrowedbooks;
import com.Menu.Borrowerinfo;
import com.Menu.Catagory;
import com.Menu.Changepassword;
import com.Menu.Exit;
import com.Menu.Logoff;
import com.Menu.Newuser;
import com.Menu.Openningstock;
import com.Menu.Purchaserecipent;
import com.Menu.Returnbooks;
import com.Menu.Returnerinfo;
import com.Menu.Salesbooks;
import com.Menu.Subcatagory;
import com.Menu.Supplierinfo;
import com.Menu.Update;
import com.Menu.Wastage;


public class WorkingPanel extends JPanel
{
	Catagory catagory=new Catagory();
	Subcatagory subcatagory=new Subcatagory();
	Booksinfo booksinfo=new Booksinfo();
	Newuser newuser=new Newuser();
	Changepassword chagepassword=new Changepassword();
	Update update=new Update();
	Logoff logoff=new Logoff();
	Exit exit=new Exit();
	Supplierinfo supplierinfo=new Supplierinfo();
	Borrowerinfo borrowerinfo=new Borrowerinfo();
	Returnerinfo returnerinfo=new Returnerinfo();
	Openningstock openningstock=new Openningstock();
	Purchaserecipent purcahserecipent=new Purchaserecipent();
	Borrowedbooks borrowedbook=new Borrowedbooks();
	Salesbooks salesbooks=new Salesbooks();
	Returnbooks returnbooks=new Returnbooks();
	Wastage wastage=new Wastage();
	
	JPanel panelcenter=new JPanel();
	JPanel panelwest=new JPanel();
	
	JPanel panelbtn=new JPanel();
	JPanel panelsetup=new JPanel();
	JPanel panelsequrity=new JPanel();
	JPanel panelothers=new JPanel();
	JPanel panelinfo=new JPanel();
	JPanel paneltask=new JPanel();
	
	
	JLabel lblsetupicon=new JLabel(new ImageIcon("image/setupicon.PNG"));
	JLabel lblcvicon=new JLabel(new ImageIcon("image/cv.PNG"));
	JLabel lblinfoicon=new JLabel(new ImageIcon("image/infoicon.PNG"));
	JLabel lblsequrityicon=new JLabel(new ImageIcon("image/sequrityicon.PNG"));
	JLabel lbltaskicon=new JLabel(new ImageIcon("image/taskicon.PNG"));
	JLabel lblothericon=new JLabel(new ImageIcon("image/othericon.PNG"));
	
	JLabel lblsetup=new JLabel("Setup");
	JLabel lblsequrity=new JLabel("Sequrity");
	JLabel lblothers=new JLabel("Others");
	JLabel lblinfo=new JLabel("Info");
	JLabel lbltask=new JLabel("Task");
	JLabel lblcatagory=new JLabel("Catagory");
	JLabel lblsubcatagory=new JLabel("Sub catagory");
	JLabel lblbooksinfo=new JLabel("Books Info");
	JLabel lblbacksetup=new JLabel("Back");
	JLabel lblbacksequrty=new JLabel("Back");//,new ImageIcon("image/towards.png"),3);
	JLabel lblbackothers=new JLabel("Back");
	JLabel lblbackinfo=new JLabel("Back");
	JLabel lblbacktask=new JLabel("Back");
	JLabel lblnewuser=new JLabel("New User");
	JLabel lbllogoff=new JLabel("Log Off");
	JLabel lblexit=new JLabel("Exit");
	JLabel lblchangepassword=new JLabel("Change Password");
	JLabel lblupdate=new JLabel("Update");
	JLabel lblsupplierinfo=new JLabel("Supplier Info");
	JLabel lblborrowerinfo=new JLabel("Borrower Info");
	JLabel lblreturnerinfo=new JLabel("Returner Info");
	JLabel lblopenningbooks=new JLabel("Openning Books");
	JLabel lblpurchasebooks=new JLabel("Purchase Books");
	JLabel lblborrowedbooks=new JLabel("Borrowed Books");
	JLabel lblsalesbooks=new JLabel("Sales Books");
	JLabel lblreturnbooks=new JLabel("Return Books");
	JLabel lblwastage=new JLabel("Wastage");
	
	JButton btnsetup=new JButton(new ImageIcon("image/setup.png"));
	JButton btnsequrity=new JButton(new ImageIcon("image/sequrity.png"));
	JButton btnothers=new JButton(new ImageIcon("image/others.png"));
	JButton btninfo=new JButton(new ImageIcon("image/Info.png"));
	JButton btntask=new JButton(new ImageIcon("image/task.png"));
	JButton btnbacksetup=new JButton(new ImageIcon("image/back.png"));
	JButton btnbacktask=new JButton(new ImageIcon("image/back.png"));
	JButton btnbackinfo=new JButton(new ImageIcon("image/back.png"));
	JButton btnbacksequrity=new JButton(new ImageIcon("image/back.png"));
	JButton btnbackothers=new JButton(new ImageIcon("image/back.png"));
	JButton btncatagory=new JButton(new ImageIcon("image/catagory.png"));
	JButton btnsubcatagory=new JButton(new ImageIcon("image/subcatagory.png"));
	JButton btnbooksinfo=new JButton(new ImageIcon("image/booksinfo.png"));
	JButton btnlogoff=new JButton(new ImageIcon("image/logoff.png"));
	JButton btnupdate=new JButton(new ImageIcon("image/update.png"));
	JButton btnexit=new JButton(new ImageIcon("image/exit.png"));	
	JButton btnnewuser=new JButton(new ImageIcon("image/newuser.png"));
	JButton btnchangepassword=new JButton(new ImageIcon("image/changepassword.png"));
	JButton btnopenningbooks=new JButton(new ImageIcon("image/openingstock.png"));
	JButton btnsupplierinfo=new JButton(new ImageIcon("image/supplierinfo.png"));
	JButton btnborrowerinfo=new JButton(new ImageIcon("image/borrower info.png"));
	JButton btnreturnerinfo=new JButton(new ImageIcon("image/returnerinfo.png"));
	JButton btnpurchasebooks=new JButton(new ImageIcon("image/purchase.png"));
	JButton btnborrowedbooks=new JButton(new ImageIcon("image/borowbooks.png"));	
	JButton btnsalesbooks=new JButton(new ImageIcon("image/sales.png"));
	JButton btnreturnbooks=new JButton(new ImageIcon("image/returnbooks.png"));
	JButton btnwastage=new JButton(new ImageIcon("image/wastage.png"));

	FlowLayout flow=new FlowLayout();
	GridBagLayout grid=new GridBagLayout();
	GridBagConstraints con=new GridBagConstraints();
	BorderLayout border=new BorderLayout();
	Font font=new Font("serif", Font.BOLD+Font.ITALIC, 15);

	public WorkingPanel()
	{	
		panelwork();
		btnaction();
	}
	public void lblfalse()
	{
		lblsequrityicon.setVisible(false);
		lblinfoicon.setVisible(false);
		lblothericon.setVisible(false);
		lblsetupicon.setVisible(false);
		lbltaskicon.setVisible(false);
	}
	private void panelwestfalse()
	{
		panelbtn.setVisible(false);
		panelsequrity.setVisible(false);
		panelsetup.setVisible(false);
		panelinfo.setVisible(false);
		panelothers.setVisible(false);
		paneltask.setVisible(false);
	}
	private void panelcenterfalse()
	{
		catagory.setVisible(false);
		subcatagory.setVisible(false);
		booksinfo.setVisible(false);
		newuser. setVisible(false);
		chagepassword.setVisible(false);
		update.setVisible(false);
		logoff.setVisible(false);
		exit.setVisible(false);
		supplierinfo.setVisible(false);
		returnerinfo.setVisible(false);
		returnbooks.setVisible(false);
		borrowerinfo.setVisible(false);
		borrowedbook.setVisible(false);
		openningstock.setVisible(false);
		purcahserecipent.setVisible(false);
		salesbooks.setVisible(false);
		wastage.setVisible(false);
	}
	private void btnaction() 
	{
		lblbacksequrty.addMouseListener(new MouseListener()
		{
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				panelwestfalse();
				panelbtn.setVisible(true);
				panelcenterfalse();
				lblcvicon.setVisible(true);
			}
		});
        lblbacksetup.addMouseListener(new MouseListener()
        {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				panelwestfalse();
				panelbtn.setVisible(true);
				panelcenterfalse();
				lblcvicon.setVisible(true);
			}
		});
        lblbackothers.addMouseListener(new MouseListener() 
        {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				panelwestfalse();
				panelbtn.setVisible(true);
				panelcenterfalse();
				lblcvicon.setVisible(true);		
			}
		});
        lblbacktask.addMouseListener(new MouseListener() 
        {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				panelwestfalse();
				panelbtn.setVisible(true);
				panelcenterfalse();
				lblcvicon.setVisible(true);
			}
		});
        lblbackinfo.addMouseListener(new MouseListener() 
        {
        	public void mouseReleased(MouseEvent arg0) {}
        	public void mousePressed(MouseEvent arg0) {}
        	public void mouseExited(MouseEvent arg0) {}
        	public void mouseEntered(MouseEvent arg0) {}
        	public void mouseClicked(MouseEvent arg0) 
        	{
        		panelwestfalse();
        		panelbtn.setVisible(true);
        		panelcenterfalse();
				lblcvicon.setVisible(true);
        	}
        });
		btnsetup.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				panelwestfalse();
				panelsetup.setVisible(true);
				lblcvicon.setVisible(false);
				panelcenterfalse();
				lblfalse();
				lblsetupicon.setVisible(true);
			}
		});
        btnothers.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent arg0) 
			{	
				panelwestfalse();
				panelothers.setVisible(true);
				lblcvicon.setVisible(false);
				panelcenterfalse();
				lblfalse();
				lblothericon.setVisible(true);
			}
		});
        btntask.addActionListener(new ActionListener() 
        {
	        public void actionPerformed(ActionEvent arg0) 
	        {
	        	panelwestfalse();
				paneltask.setVisible(true);
				lblcvicon.setVisible(false);
				panelcenterfalse();
				lblfalse();
				lbltaskicon.setVisible(true);	
	        }
        });
        btnsequrity.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent arg0) 
	         {
	        	panelwestfalse();
				panelsequrity.setVisible(true);
				lblcvicon.setVisible(false);
				panelcenterfalse();
				lblfalse();
				lblsequrityicon.setVisible(true);
	         }
         });
         btninfo.addActionListener(new ActionListener() 
         {
	        public void actionPerformed(ActionEvent arg0) 
	        {
	        	panelwestfalse();
				panelinfo.setVisible(true);
				lblcvicon.setVisible(false);
				panelcenterfalse();
				lblfalse();
				lblinfoicon.setVisible(true);
	        }
         });
         btncatagory.addActionListener(new ActionListener() 
         {
			public void actionPerformed(ActionEvent arg0) 
			{
				panelcenterfalse();
				catagory.setVisible(true);
				catagory.catautoid();
				catagory.catdataload();
				catagory.catcmbsearch();
			}
		});
        btnsubcatagory.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent arg0) 
			{
				panelcenterfalse();
				subcatagory.setVisible(true);
				subcatagory.cmbcatagroyload();
				subcatagory.subcatautoid();
				subcatagory.subcatdataload();
				subcatagory.subcatcmbsearch();
			}
		});
        btnbooksinfo.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent arg0) 
			{
				panelcenterfalse();
				booksinfo.setVisible(true);
				booksinfo.subcatagorydataload();
				booksinfo.booksifoautoid();
				booksinfo.suppliernameload();
				booksinfo.tbdataload();
				booksinfo.cmbsearchwork();
			}
		});
        btnnewuser.addActionListener(new ActionListener() 
        {
 			public void actionPerformed(ActionEvent arg0) 
 			{
 				panelcenterfalse();
 				newuser.setVisible(true);
 				newuser.newuserautoid();
 				newuser.tbdataload();
 				newuser.cmbloadsearch();
 			}
 		});
        btnchangepassword.addActionListener(new ActionListener() 
        {
  			public void actionPerformed(ActionEvent arg0) 
  			{
  				panelcenterfalse();
  				chagepassword.setVisible(true);
  			}
  		});
        btnupdate.addActionListener(new ActionListener() 
        {
  			public void actionPerformed(ActionEvent arg0) 
  			{
  				panelcenterfalse();
  				update.setVisible(true);
  			}
  		});
        btnlogoff.addActionListener(new ActionListener() 
        {
  			public void actionPerformed(ActionEvent arg0) 
  			{
  				panelcenterfalse();
  				logoff.setVisible(true);
  				
  			}
  		});
        btnsupplierinfo.addActionListener(new ActionListener() 
        {
  			public void actionPerformed(ActionEvent arg0) 
  			{
  				panelcenterfalse();
  				supplierinfo.setVisible(true);
  				supplierinfo.supautoid();
  				supplierinfo.tbdataload();
  				supplierinfo.cmbsearchwork();
  			}
  		});
        btnreturnerinfo.addActionListener(new ActionListener() 
        {
  			public void actionPerformed(ActionEvent arg0) 
  			{
  				panelcenterfalse();
  				returnerinfo.setVisible(true);
  				returnerinfo.referencedataload();
  				returnerinfo.borrowerid();
  			}
  		});
        btnborrowerinfo.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent arg0) 
   			{
   				panelcenterfalse();
   				borrowerinfo.setVisible(true);
   				borrowerinfo.referecebyload();
   			}
   		});
        btnopenningbooks.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent arg0) 
   			{
   				panelcenterfalse();
   				openningstock.setVisible(true);
   			}
   		});
        btnpurchasebooks.addActionListener(new ActionListener() 
        {
   			public void actionPerformed(ActionEvent arg0) 
   			{
   				panelcenterfalse();
   				purcahserecipent.setVisible(true);
   			}
   		});
        btnsalesbooks.addActionListener(new ActionListener() 
        {
   			public void actionPerformed(ActionEvent arg0) 
   			{
   				panelcenterfalse();
   				salesbooks.setVisible(true);
   			}
   		});
        btnborrowedbooks.addActionListener(new ActionListener() 
        {
   			public void actionPerformed(ActionEvent arg0)
   			{
   				panelcenterfalse();
   				borrowedbook.setVisible(true);
   			}
   		});
        btnreturnbooks.addActionListener(new ActionListener()
        {
   			public void actionPerformed(ActionEvent arg0)
   			{
   				panelcenterfalse();
   				returnbooks.setVisible(true);
   				
   			}
   		});
        btnwastage.addActionListener(new ActionListener() 
        {
   			public void actionPerformed(ActionEvent arg0) 
   			{
   				panelcenterfalse();
   				wastage.setVisible(true);
   			}
   		});
        btnexit.addActionListener(new ActionListener() 
        {
    			public void actionPerformed(ActionEvent arg0) 
    			{
    				panelcenterfalse();
    				exit.setVisible(true);
    			}
    	});
	}
	private void panelwork() 
	{
		setLayout(border);
		add(panelwest,border.WEST);
		panelwestwork();
		add(panelcenter,border.CENTER);
		panelcenterwork();
	}
	private void panelcenterwork() 
	{
		panelcenter.setBackground(Color.lightGray);
		FlowLayout flow=new FlowLayout();
		panelcenter.setLayout(flow);
		flow.setVgap(0);
		panelcenter.add(catagory);
		catagory.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(subcatagory);
		subcatagory.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(booksinfo);
		booksinfo.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(newuser);
		newuser.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(chagepassword);
		chagepassword.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(update);
		update.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(logoff);
		logoff.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(exit);
		exit.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(supplierinfo);
		supplierinfo.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(returnerinfo);
		returnerinfo.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(borrowerinfo);
		borrowerinfo.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(openningstock);
		openningstock.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(purcahserecipent);
		purcahserecipent.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(salesbooks);
		salesbooks.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(borrowerinfo);
		borrowerinfo.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(returnbooks);
		returnbooks.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(wastage);
		wastage.setPreferredSize(new Dimension(1150,768));
		
		panelcentertruefalse();
		panelcenter.add(lblcvicon);
		lblcvicon.setVisible(true);
		lblcvicon.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(lblsetupicon);
		lblsetupicon.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(lblsequrityicon);
		lblsequrityicon.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(lblothericon);
		lblothericon.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(lbltaskicon);
		lbltaskicon.setPreferredSize(new Dimension(1150,768));
		panelcenter.add(lblinfoicon);
		lblinfoicon.setPreferredSize(new Dimension(1150,768));
		lblfalse();
	}
	
	private void panelcentertruefalse()
	{
		catagory.setVisible(false);
		subcatagory.setVisible(false);
		supplierinfo.setVisible(false);
		booksinfo.setVisible(false);
		newuser.setVisible(false);
		chagepassword.setVisible(false);
		returnerinfo.setVisible(false);
		borrowerinfo.setVisible(false);;
		openningstock.setVisible(false);
		purcahserecipent.setVisible(false);
		salesbooks.setVisible(false);
		borrowedbook.setVisible(false);
		update.setVisible(false);
		wastage.setVisible(false);
		logoff.setVisible(false);
		exit.setVisible(false);
		returnbooks.setVisible(false);
	}
	private void panelwestwork()
	{
		panelwest.setBackground(Color.DARK_GRAY);
		panelwest.setPreferredSize(new Dimension(200,0));
		panelwest.add(panelbtn);
		panelbtn.setPreferredSize(new Dimension(200,768));
		panelbtn.setLayout(grid);
		panelbtn.setBackground(Color.DARK_GRAY);	
		con.fill=GridBagConstraints.BOTH;
		con.insets=new Insets(5, 5, 5, 5);	
		con.gridx=0;
		con.gridy=0;
		panelbtn.add(btnsetup,con);
		con.gridx=0;
		con.gridy=1;
		panelbtn.add(lblsetup,con);
		lblsetup.setFont(font);
		lblsetup.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=2;
		panelbtn.add(btnsequrity,con);
		con.gridx=0;
		con.gridy=3;
		panelbtn.add(lblsequrity,con);
		lblsequrity.setFont(font);
		lblsequrity.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=4;
		panelbtn.add(btninfo,con);
		con.gridx=0;
		con.gridy=5;
		panelbtn.add(lblinfo,con);
		lblinfo.setFont(font);
		lblinfo.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=6;
		panelbtn.add(btntask,con);
		con.gridx=0;
		con.gridy=7;
		panelbtn.add(lbltask,con);
		lbltask.setFont(font);
		lbltask.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=8;
		panelbtn.add(btnothers,con);
		con.gridx=0;
		con.gridy=9;
		panelbtn.add(lblothers,con);
		lblothers.setFont(font);
		lblothers.setForeground(Color.WHITE);	
		panelwestpanelwork();	
	}

	private void panelwestpanelwork()
	{
		panelwest.add(panelsequrity);
		panelsequrity.setPreferredSize(new Dimension(200,768));
		panelsequritywork();
		panelwest.add(panelsetup);
		panelsetup.setPreferredSize(new Dimension(200,768));
		panelsetupwork() ;
		panelwest.add(panelinfo);
		panelinfo.setPreferredSize(new Dimension(200,768));
		panelinfowork() ;
		panelwest.add(panelothers);
		panelotherswork();
		panelothers.setPreferredSize(new Dimension(200,768));
		panelwest.add(paneltask);
		paneltask.setPreferredSize(new Dimension(200,768));
		paneltaskwork();	
	}

	private void panelsequritywork() 
	{
		panelsequrity.setLayout(grid);
		panelsequrity.setBackground(Color.DARK_GRAY);
		con.fill=GridBagConstraints.BOTH;
		con.insets=new Insets(5, 5, 5, 5);
		con.gridx=0;
		con.gridy=0;
		panelsequrity.add(btnnewuser,con);
		con.gridx=0;
		con.gridy=1;
		panelsequrity.add(lblnewuser,con);
		lblnewuser.setFont(font);
		lblnewuser.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=2;
		panelsequrity.add(btnchangepassword,con);
		con.gridx=0;
		con.gridy=3;
		panelsequrity.add(lblchangepassword,con);
		lblchangepassword.setFont(font);
		lblchangepassword.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=7;
		panelsequrity.add(lblbacksequrty,con);
		lblbacksequrty.setFont(font);
		lblbacksequrty.setForeground(Color.WHITE);	
	}
	private void panelsetupwork() 
	{
		panelsetup.setLayout(grid);
		panelsetup.setBackground(Color.DARK_GRAY);
		con.fill=GridBagConstraints.BOTH;
		con.insets=new Insets(5, 5, 5, 5);
		con.gridx=0;
		con.gridy=0;
		panelsetup.add(btncatagory,con);
		con.gridx=0;
		con.gridy=1;
		panelsetup.add(lblcatagory,con);
		lblcatagory.setFont(font);
		lblcatagory.setForeground(Color.WHITE);	
		con.gridx=0;
		con.gridy=2;
		panelsetup.add(btnsubcatagory,con);
		con.gridx=0;
		con.gridy=3;
		panelsetup.add(lblsubcatagory,con);
		lblsubcatagory.setFont(font);
		lblsubcatagory.setForeground(Color.WHITE);	
		con.gridx=0;
		con.gridy=4;
		panelsetup.add(btnbooksinfo,con);
		con.gridx=0;
		con.gridy=5;
		panelsetup.add(lblbooksinfo,con);
		lblbooksinfo.setFont(font);
		lblbooksinfo.setForeground(Color.WHITE);	
		con.gridx=0;
		con.gridy=7;
		panelsetup.add(lblbacksetup,con);
		lblbacksetup.setFont(font);
		lblbacksetup.setForeground(Color.WHITE);
	}
	private void panelinfowork() 
	{
		panelinfo.setLayout(grid);
		panelinfo.setBackground(Color.DARK_GRAY);	
		con.fill=GridBagConstraints.BOTH;
		con.insets=new Insets(5, 5, 5, 5);
		con.gridx=0;
		con.gridy=0;
		panelinfo.add(btnsupplierinfo,con);
		con.gridx=0;
		con.gridy=1;
		panelinfo.add(lblsupplierinfo,con);
		lblsupplierinfo.setFont(font);
		lblsupplierinfo.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=2;
		panelinfo.add(btnborrowerinfo,con);
		con.gridx=0;
		con.gridy=3;
		panelinfo.add(lblborrowerinfo,con);
		lblborrowerinfo.setFont(font);
		lblborrowerinfo.setForeground(Color.WHITE);	
		con.gridx=0;
		con.gridy=4;
		panelinfo.add(btnreturnerinfo,con);
		con.gridx=0;
		con.gridy=5;
		panelinfo.add(lblreturnerinfo,con);
		lblreturnerinfo.setFont(font);
		lblreturnerinfo.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=7;
		panelinfo.add(lblbackinfo,con);
		lblbackinfo.setFont(font);
		lblbackinfo.setForeground(Color.WHITE);
	}
	private void paneltaskwork() 
	{
		paneltask.setLayout(grid);
		paneltask.setBackground(Color.DARK_GRAY);	
		con.fill=GridBagConstraints.BOTH;
		con.insets=new Insets(5, 5, 5, 5);	
		con.gridx=0;
		con.gridy=0;
		paneltask.add(btnopenningbooks,con);
		con.gridx=0;
		con.gridy=1;
		paneltask.add(lblopenningbooks,con);
		lblopenningbooks.setFont(new Font("serif",Font.BOLD+Font.ITALIC,13));
		lblopenningbooks.setForeground(Color.WHITE);
		con.gridx=1;
		con.gridy=0;
		paneltask.add(btnpurchasebooks,con);
		con.gridx=1;
		con.gridy=1;
		paneltask.add(lblpurchasebooks,con);
		lblpurchasebooks.setFont(new Font("serif",Font.BOLD+Font.ITALIC,13));
		lblpurchasebooks.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=2;
		paneltask.add(btnsalesbooks,con);
		con.gridx=0;
		con.gridy=3;
		paneltask.add(lblsalesbooks,con);
		lblsalesbooks.setFont(new Font("serif",Font.BOLD+Font.ITALIC,13));
		lblsalesbooks.setForeground(Color.WHITE);
		con.gridx=1;
		con.gridy=2;
		paneltask.add(btnborrowedbooks,con);
		con.gridx=1;
		con.gridy=3;
		paneltask.add(lblborrowedbooks,con);
		lblborrowedbooks.setFont(new Font("serif",Font.BOLD+Font.ITALIC,13));
		lblborrowedbooks.setForeground(Color.WHITE);	
		con.gridx=0;
		con.gridy=4;
		paneltask.add(btnreturnbooks,con);
		con.gridx=0;
		con.gridy=5;
		paneltask.add(lblreturnbooks,con);
		lblreturnbooks.setFont(new Font("serif",Font.BOLD+Font.ITALIC,13));
		lblreturnbooks.setForeground(Color.WHITE);		
		con.gridx=1;
		con.gridy=4;
		paneltask.add(btnwastage,con);
		con.gridx=1;
		con.gridy=5;
		paneltask.add(lblwastage,con);
		lblwastage.setFont(new Font("serif",Font.BOLD+Font.ITALIC,13));
		lblwastage.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=7;
		paneltask.add(lblbacktask,con);
		lblbacktask.setFont(new Font("serif",Font.BOLD+Font.ITALIC,13));
		lblbacktask.setForeground(Color.WHITE);	
	}
	private void panelotherswork() 
	{
		panelothers.setLayout(grid);
		panelothers.setBackground(Color.DARK_GRAY);	
		con.fill=GridBagConstraints.BOTH;
		con.insets=new Insets(5, 5, 5, 5);		
		con.gridx=0;
		con.gridy=0;
		panelothers.add(btnupdate,con);
		con.gridx=0;
		con.gridy=1;
		panelothers.add(lblupdate,con);
		lblupdate.setFont(font);
		lblupdate.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=2;
		panelothers.add(btnlogoff,con);
		con.gridx=0;
		con.gridy=3;
		panelothers.add(lbllogoff,con);
		lbllogoff.setFont(font);
		lbllogoff.setForeground(Color.WHITE);
		con.gridx=0;
		con.gridy=4;
		panelothers.add(btnexit,con);
		con.gridx=0;
		con.gridy=5;
		panelothers.add(lblexit,con);
		lblexit.setFont(font);
		lblexit.setForeground(Color.WHITE);	
		con.gridx=0;
		con.gridy=7;
		panelothers.add(lblbackothers,con);
		lblbackothers.setFont(font);
		lblbackothers.setForeground(Color.WHITE);
	}	
}
