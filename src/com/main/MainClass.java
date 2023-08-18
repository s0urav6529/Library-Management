package com.main;
import javax.swing.UIManager;
import com.Dbconection;
import com.admin.Login;;
public class MainClass 
{
	public static void main(String args[])
	{
		try 
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		Login log=new Login();
		Dbconection.conect();
	}
}
