/****************************************************************************************************
 * accounts.java helper class
 * Stores all the accounts created in program in a file(usernames and passwords)
 * Allows creating new accounts, with a password, encryption status, data file, and profile picture
 ****************************************************************************************************/
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class accounts 
{
	public enum CheckingCases {Data_File,Output_File,Main_Files,Essential_Files,Default_Img};

	private static String FileName = "accounts.txt",		//holds all account usernames/passwords/statuses
						  profImages = "profImages.txt";	//holds all account profile images with their details

	public static String Pass;
	private String userName;

	public accounts(String userName)
	{
		this.userName = userName;
		newAccount(this.userName);	
		newData(this.userName);
		newImage(this.userName);
	}

	//Generates 3 digit code (from current time) added to end of password
	private static int GenerateCode()	
	{
		DecimalFormat fmt= new DecimalFormat("0.###");
		String DateTime;
		DateTime= LocalDateTime.now()+"";
		Scanner codeScan = new Scanner(DateTime);
		codeScan.useDelimiter(":");
		String code="";
		while(codeScan.hasNext())
		{
			code = codeScan.next();
		}
		double d = Double.parseDouble(code);
		codeScan.close();
		return Integer.parseInt(fmt.format(d-(int)d).substring(2));
	}

	//Generates password for new user from first character of username and 3 digits (from GenerateCode)
	private static String passGenerator (String userName)
	{
		return userName.charAt(0)+""+ GenerateCode();
	}

	//writes new account to bottom of accounts file
	private static void newAccount (String newAccount)
	{
		try
		{
			String password = passGenerator(newAccount);
			File accFile = new File(FileName);			//accounts file
			Scanner fileScan = new Scanner(accFile);
			ArrayList<String> List = new ArrayList<String>();

			while (fileScan.hasNext())
				List.add(fileScan.nextLine());			//storing previous accounts

			PrintWriter printFile = new PrintWriter(accFile);
			for (int count=0 ; count<List.size() ; count++)
				printFile.println(List.get(count));		//rewriting previous accounts

			printFile.print(newAccount+"##"+password+"##Dec##-1");		//new account
			printFile.close();
			fileScan.close();
			Pass = password;				//static Pass is set to new users password, to give it to new user
		}
		catch (IOException e) {}
	}

	//finds the if entered account exists in the accounts file
	public static boolean AccountExists(String toFind)
	{
		boolean exists = false;
		try
		{
			Scanner fileScanner = new Scanner(new File(FileName)),lineScan;		//accounts file

			while (fileScanner.hasNextLine())
			{
				String line = fileScanner.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if((lineScan.next()).equals(toFind))
				{
					exists=true;
					lineScan.close();
					break;				
				}

			}

			fileScanner.close();
			return exists;
		}
		catch (IOException e) {return exists;}
	}

	//reads the password stored in accounts file for specified user/returns null if file not found
	public static String PassWordsReader(String user)
	{
		try
		{
			String pass="";
			Scanner fileScanner = new Scanner(new File(FileName)),lineScan;

			while (fileScanner.hasNext())
			{
				String line = fileScanner.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
					pass = lineScan.next();
			}

			fileScanner.close();
			return pass;
		}
		catch (IOException e) {return null;}
	}

	//creates new data file for the new user
	private void newData(String newAccount)
	{
		try 
		{
			File accFile = new File("Data_" + newAccount + ".txt");
			accFile.createNewFile();
		} 
		catch (IOException e) {}

	}

	//writes new profile image for new user at the bottom of profile pics file (default pic)
	private void newImage(String newAccount)
	{
		try
		{
			File imgs = new File(profImages);			//images file
			Scanner imgScan = new Scanner(imgs);
			ArrayList<String> imgList = new ArrayList<String>();

			while (imgScan.hasNext())
				imgList.add(imgScan.nextLine());			//storing previous images and their details

			PrintWriter printFile = new PrintWriter(imgs);
			for (int count=0 ; count<imgList.size() ; count++)
				printFile.println(imgList.get(count));				//rewriting previous images and their details

			printFile.print(newAccount + "##noprofile.png##1.0##0.0##0.0##100.0##98.0");		//new user + default image with its details
			printFile.close();
			imgScan.close();
		} 
		catch (IOException e) {} 
	}

	//gets the current user's image path
	public static String getImage(String user)
	{
		String image=null;
		try
		{
			File imgFile = new File(profImages);		//image file
			Scanner imgScan = new Scanner(imgFile);
			imgScan.nextLine(); 		//skip 1st line
			Scanner lineScan;

			while (imgScan.hasNext())
			{
				String line = imgScan.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					image = lineScan.next();		//scan image path
					imgScan.close();
					lineScan.close();
					return image;
				}
			}

			imgScan.close();
			return image;		
		}
		catch (IOException e) {return null;}
	}

	//gets the current user's image's scale/ returns -1 if user/file not found
	public static double getImageScale(String user)
	{
		String image=null;
		double scale;
		try
		{
			File imgFile = new File(profImages);		//image file
			Scanner imgScan = new Scanner(imgFile);
			imgScan.nextLine(); 		//skip 1st line
			Scanner lineScan;

			while (imgScan.hasNext())
			{
				String line = imgScan.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					lineScan.next();		//scan image path
					image = lineScan.next();		//scan scale
					imgScan.close();
					lineScan.close();
					scale = Double.parseDouble(image);
					return scale;
				}
			}

			imgScan.close();
			return -1;		
		}
		catch (IOException e) {return -1;}
	}

	//gets the current user's image's top left corner x coordinate/ returns -1 if user/file not found
	public static double getImageX(String user)
	{
		String image=null;
		double x;
		try
		{
			File imgFile = new File(profImages);		//image file
			Scanner imgScan = new Scanner(imgFile);
			imgScan.nextLine(); 		//skip 1st line
			Scanner lineScan;

			while (imgScan.hasNext())
			{
				String line = imgScan.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					lineScan.next();		//scan image path
					lineScan.next();		//scan scale
					image = lineScan.next();		//scan image x (top left corner x position)
					imgScan.close();
					lineScan.close();
					x = Double.parseDouble(image);
					return x;
				}
			}

			imgScan.close();
			return -1;		
		}
		catch (IOException e) {return -1;}
	}

	//gets the current user's image's top left corner y coordinate/ returns -1 if user/file not found
	public static double getImageY(String user)
	{
		String image=null;
		double y;
		try
		{
			File imgFile = new File(profImages);		//image file
			Scanner imgScan = new Scanner(imgFile);
			imgScan.nextLine(); 		//skip 1st line
			Scanner lineScan;

			while (imgScan.hasNext())
			{
				String line = imgScan.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					lineScan.next();		//scan image path
					lineScan.next();		// scan scale
					lineScan.next();		//scan image x (top left corner x position)
					image = lineScan.next();		//scan image y (top left corner y position)
					imgScan.close();
					lineScan.close();
					y = Double.parseDouble(image);
					return y;
				}
			}

			imgScan.close();
			return -1;		
		}
		catch (IOException e) {return -1;}
	}

	//gets the current user's image's width/ returns -1 if user/file not found
	public static double getImageWidth(String user)
	{
		String image=null;
		double width;
		try
		{
			File imgFile = new File(profImages);		//image file
			Scanner imgScan = new Scanner(imgFile);
			imgScan.nextLine(); 		//skip 1st line
			Scanner lineScan;

			while (imgScan.hasNext())
			{
				String line = imgScan.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					lineScan.next();		//scan image path
					lineScan.next();		//scan scale
					lineScan.next();		//scan image x (top left corner x position)
					lineScan.next();		//scan image y (top left corner y position)
					image = lineScan.next();		//scan image width
					imgScan.close();
					lineScan.close();
					width = Double.parseDouble(image);
					return width;
				}
			}

			imgScan.close();
			return -1;		
		}
		catch (IOException e) {return -1;}
	}

	//gets the current user's image's height/ returns -1 if user/file not found
	public static double getImageHeight(String user)
	{
		String image=null;
		double height;
		try
		{
			File imgFile = new File(profImages);		//image file
			Scanner imgScan = new Scanner(imgFile);
			imgScan.nextLine(); 		//skip 1st line
			Scanner lineScan;

			while (imgScan.hasNext())
			{
				String line = imgScan.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					lineScan.next();		//scan image path
					lineScan.next();		//scan scale
					lineScan.next();		//scan image x (top left corner x position)
					lineScan.next();		//scan image y (top left corner y position)
					lineScan.next();		//scan image width
					image = lineScan.next();		//scan image height
					imgScan.close();
					lineScan.close();
					height = Double.parseDouble(image);
					return height;
				}
			}

			imgScan.close();
			return -1;		
		}
		catch (IOException e) {return -1;}
	}

	//changes user image in images file along with its details (returns false if image/user not found, true if successful)
	public static boolean setImage(String user, String imgPath,double scale, double x, double y, double width, double height)
	{
		File test = new File(imgPath);	//checking if image exists
		if (test.exists())
			;//continue to next part	
		else
			return false;

		try
		{
			File imgFile = new File(profImages);		//images file
			Scanner fileScan = new Scanner(imgFile);
			fileScan.nextLine(); //skip 1st line
			Scanner lineScan;
			String line;
			ArrayList<String> imgBefore = new ArrayList<String>();		//images before user's line
			ArrayList<String> imgAfter = new ArrayList<String>();		//images after user's line
			boolean found = false;
			while (fileScan.hasNext())
			{
				line = fileScan.nextLine();
				lineScan = new Scanner(line); 
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					found=true;
					continue;
				}

				if (!found)
					imgBefore.add(line);			
				else
					imgAfter.add(line);					//storing images before and after user's image
			}

			PrintWriter printFile = new PrintWriter(imgFile);
			printFile.println("Profile Images: (user##image_path##scale##startx##starty##width##height)");	//header

			for (int count=0 ; count<imgBefore.size() ; count++)
				printFile.println(imgBefore.get(count));		//rewriting previous images

			printFile.println(user + "##" + imgPath + "##" + scale + "##" + x + "##" + y + "##" + width + "##" + height);		//new image	

			for (int count=0 ; count<imgAfter.size() ; count++)
				printFile.println(imgAfter.get(count));			//rewriting images after

			printFile.close();
			fileScan.close();
			return true;
		}
		catch (IOException e) {return false;}
	}

	//gets encryption status of current user
	public static String getStatus(String user)
	{
		String status=null;
		try
		{
			File file = new File(FileName);			//accounts file
			Scanner statusScan = new Scanner(file);
			statusScan.nextLine(); //skip 1st line
			Scanner lineScan;

			while (statusScan.hasNext())
			{
				String line = statusScan.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					lineScan.next();		//skip password
					status = lineScan.next();
					statusScan.close();
					lineScan.close();
					return status;
				}
			}

			statusScan.close();
		}
		catch (Exception e) {return null;}

		return null;
	}

	//changes encryption status and code of current user (returns false if image/user not found, true if successful)
	public static boolean setStatus(String user,long code)
	{	
		try
		{
			File file = new File(FileName);			//accounts file
			Scanner fileScan = new Scanner(file);
			fileScan.nextLine(); //skip 1st line
			Scanner lineScan;
			String line,status="";
			ArrayList<String> statusesBefore = new ArrayList<String>();
			ArrayList<String> statusesAfter = new ArrayList<String>();
			boolean found = false;
			while (fileScan.hasNext())
			{
				line = fileScan.nextLine();
				lineScan = new Scanner(line); 
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					found=true;
					lineScan.next();	//skip password
					status = lineScan.next();
					continue;
				}

				if (!found)
					statusesBefore.add(line);
				else
					statusesAfter.add(line);
			}

			String password = PassWordsReader(user);
			PrintWriter printFile = new PrintWriter(FileName);		//account
			printFile.println("UserName##Password##Enc-Dec status##Enc-Dec code");

			for (int count=0 ; count<statusesBefore.size() ; count++)
				printFile.println(statusesBefore.get(count));

			printFile.println(user + "##" + password + "##" + (status.equals("Enc")? "Dec##-1" : "Enc##" + code));	

			for (int count=0 ; count<statusesAfter.size() ; count++)
				printFile.println(statusesAfter.get(count));

			fileScan.close();
			printFile.close();
			return true;

		}
		catch (IOException e) {return false;}
	}

	//gets the encryption code of current user
	public static long getEncryptCode(String user)
	{
		String status=null;
		long Code;
		try
		{
			File file = new File(FileName);			//accounts file
			Scanner statusScan = new Scanner(file);
			statusScan.nextLine(); //skip 1st line
			Scanner lineScan;

			while (statusScan.hasNext())
			{
				String line = statusScan.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					lineScan.next();		//skip password
					lineScan.next();		//skip status
					status = lineScan.next();
					Code = Long.parseLong(status);
					statusScan.close();
					lineScan.close();
					return Code;
				}
			}

			statusScan.close();
		}
		catch (Exception e) {return -1;}

		return -1;
	}

	//changes username of the current user
	public static void changeUsername(String user , String newUser)
	{
		try
		{
			File file = new File(FileName);
			File imgs = new File(profImages);
			Scanner fileScan = new Scanner(file) , imgScan = new Scanner(imgs), lineScan;
			ArrayList<String> before = new ArrayList<String>();
			ArrayList<String> after = new ArrayList<String>();
			String userLine="";
			boolean found=false;

			while (fileScan.hasNext())
			{
				String line = fileScan.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					userLine = line;
					found = true;
					continue;
				}

				if (!found)
					before.add(line);
				else
					after.add(line);
			}

			PrintWriter printFile = new PrintWriter(file);

			for (int count1=0 ; count1<before.size() ; count1++)
				printFile.println(before.get(count1));

			printFile.println(newUser + userLine.substring(userLine.indexOf('#')));

			for (int count2=0 ; count2<after.size() ; count2++)
				printFile.println(after.get(count2));

			printFile.close();
			fileScan.close();
			before.clear();
			after.clear();
			found=false;

			while (imgScan.hasNext())
			{
				String line = imgScan.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					userLine = line;
					found = true;
					continue;
				}

				if (!found)
					before.add(line);
				else
					after.add(line);
			}

			PrintWriter printFile2 = new PrintWriter(imgs);

			for (int count1=0 ; count1<before.size() ; count1++)
				printFile2.println(before.get(count1));

			printFile2.println(newUser + userLine.substring(userLine.indexOf('#')));

			for (int count2=0 ; count2<after.size() ; count2++)
				printFile2.println(after.get(count2));

			printFile2.close();
			imgScan.close();

			File dataFile = new File("Data_" + user + ".txt");
			if (dataFile.exists())
				dataFile.renameTo(new File("Data_" + newUser + ".txt"));

			File outputFile = new File("Output_" + user + ".png");
			if (outputFile.exists())
				outputFile.renameTo(new File("Output_" + newUser + ".png"));
		}
		catch (IOException e) {}
	}

	//changes password of the current user
	public static void changePass(String user, String newPass)
	{
		try
		{
			File file = new File(FileName);
			Scanner fileScan = new Scanner(file) , lineScan;
			ArrayList<String> before = new ArrayList<String>();
			ArrayList<String> after = new ArrayList<String>();
			String userLine="";
			boolean found=false;

			while (fileScan.hasNext())
			{
				String line = fileScan.nextLine();
				lineScan = new Scanner(line);
				lineScan.useDelimiter("##");
				if (lineScan.next().equals(user))
				{
					userLine = line;
					found = true;
					continue;
				}

				if (!found)
					before.add(line);
				else
					after.add(line);
			}

			PrintWriter printFile = new PrintWriter(file);

			for (int count1=0 ; count1<before.size() ; count1++)
				printFile.println(before.get(count1));

			String abc = userLine.substring(userLine.indexOf('#'));
			printFile.println(user + "##" + newPass + abc.substring(abc.indexOf('#')));

			for (int count2=0 ; count2<after.size() ; count2++)
				printFile.println(after.get(count2));

			printFile.close();
			fileScan.close();
		}
		catch (IOException e) {}
	}

	//returns true if a file is empty (only spaces/no characters)
	public static boolean emptyCheck(String user)
	{
		try
		{
			File nw = new File("Data_" + user + ".txt");
			Scanner scan = new Scanner(nw),linescan;
			String line=null, test=null;
			while (scan.hasNext())
			{
				try 
				{
					line = scan.nextLine();
					linescan = new Scanner(line);
					test = linescan.next();
					if (!(test==null))
					{
						linescan.close();
						scan.close();
						return false;
					}
				}
				catch (NoSuchElementException e)
				{

				}		
			}

			if (test==null)
				return true;
			else
				return false;
		}
		catch (IOException e) {return false;}
	}

	public static boolean CHECKING(String user, CheckingCases ...Case)
	{
		for (int count=0 ; count<Case.length ; count++)
		{
			switch (Case[count])
			{
			case Data_File:
				File dataFile = new File("Data_" + user + ".txt");
				if (!dataFile.exists())
					return false;
				break;
				
			case Main_Files:
				File accFile = new File(FileName) , imgFile = new File(profImages) , accEnc = new File("Output_accs.png") , imgEnc = new File("Output_imgs.png");
				if (!accFile.exists() || !imgFile.exists() || !accEnc.exists() || !imgEnc.exists())
					return false;
				break;
				
			case Output_File:
				File outputFile = new File("Output_" + user + ".png");
				if (!outputFile.exists())
					return false;
				break;
				
			case Essential_Files:
				File accFile2 = new File(FileName), accEnc2 = new File("Output_accs.png");
				if (!accFile2.exists() || !accEnc2.exists())
					return false;
				break;
				
			case Default_Img:
				File defImg = new File("noprofile.png");
				if (!defImg.exists())
					return false;
				break;
				
			default: 
				break;
			}
		}

		return true;
	}

	public static boolean CHECKING(CheckingCases ...Case)
	{
		for (int count=0 ; count<Case.length ; count++)
		{
			switch (Case[count])
			{
			case Main_Files:
				File accFile = new File(FileName) , imgFile = new File(profImages) , accEnc = new File("Output_accs.png") , imgEnc = new File("Output_imgs.png");
				if (!accFile.exists() || !imgFile.exists() || !accEnc.exists() || !imgEnc.exists())
					return false;
				break;
				
			case Default_Img:
				File defImg = new File("noprofile.png");
				if (!defImg.exists())
					return false;
				break;
				
			case Essential_Files:
				File accFile2 = new File(FileName), accEnc2 = new File("Output_accs.png");
				if (!accFile2.exists() || !accEnc2.exists())
					return false;
				break;
				
			default:
				break;
			}
		}
		
		return true;
	}
}