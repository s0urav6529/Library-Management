package com;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Dbconection 
{

	public static Connection con=null;
	public static java.sql.Statement sta=null;
	public static void conect()
	{
		try
		{
			String server=null,port=null,username=null,password=null,dbfile=null;
			File file=new File("src/database_conection.txt");
			Scanner scan=new Scanner(file);
			int a=1;
			while(scan.hasNextLine())
			{
				String s1=scan.nextLine();
				if(a==1)
				{
					StringTokenizer token=new StringTokenizer(s1);
					token.nextToken();
					server=token.nextToken();
				}
				else if(a==2)
				{
					StringTokenizer token=new StringTokenizer(s1);
					token.nextToken();
					port=token.nextToken();
				}
				else if(a==3)
				{
					StringTokenizer token=new StringTokenizer(s1);
					token.nextToken();
					username=token.nextToken();
				}
				else if(a==4)
				{
					StringTokenizer token=new StringTokenizer(s1);
					token.nextToken();
					password=token.nextToken();
				}
				else if(a==5)
				{
					StringTokenizer token=new StringTokenizer(s1);
					token.nextToken();
					dbfile=token.nextToken();
				}
				else
				{
					break;
				}
				
				a++;
			}
			
			String url="jdbc:mysql://"+server+":"+port+"/"+dbfile;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con=DriverManager.getConnection(url,username,password);
			sta=con.createStatement();
			System.out.println ("Database connection established");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
