/**********************************************************************************************
 * EncryptCases.java helper class
 * Defines 3 different encryption cases for user data, and encrypts accounts and images files.
 **********************************************************************************************/
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

public class EncryptCases 
{
	public static void EncryptCase0 (String photoName)
	{
		try
		{
			String FileName = "Data_" + photoName + ".txt";
			encrypt trial = new encrypt (FileName);

			//x y coordinates of the photo to write on
			int x=1;
			int y=1;

			BufferedImage img = null; 
			File f = null; 

			f = new File("Output.png"); 
			img = ImageIO.read(f); 

			for (int i=0; i<trial.numberOfLines();i++)
			{

				String line= trial.FileReader(i);
				String line1 = trial.stringReader(line);

				for(int i1=0; i1<line.length();i1++)
				{	
					int p=img.getRGB(x, y);
					char read = trial.charReader(line1, i1);
					int r = trial.translate(read);

					p = trial.Rsetter(r);
					img.setRGB(x, y, p); 
					y++;
				}

				int p = trial.Rsetter(250);
				img.setRGB(++x, y, p);
				x++; y=0;
			}

			f = new File("Output_"+ photoName +".png"); 
			ImageIO.write(img, "png", f); 

			//encrypted text generator
			int m = trial.count();

			FileWriter fw1 = new FileWriter(FileName);
			PrintWriter PrintOnFile = new PrintWriter(fw1);
			PrintOnFile.print(trial.FakeText(m));
			PrintOnFile.close();

			//System.out.println("Cipher 0");
		}
		catch (IOException e) {}
	}

	public static void EncryptCase1 (String photoName)
	{
		try
		{
			String FileName = "Data_" + photoName + ".txt";
			encrypt trial = new encrypt (FileName);

			//x y coordinates of the photo to write on
			int x=1;
			int y=1;

			BufferedImage img = null; 
			File f = null; 

			f = new File("Output.png"); 
			img = ImageIO.read(f); 

			for (int i=0; i<trial.numberOfLines();i++)
			{

				String line= trial.FileReader(i);
				String line1 = trial.stringReader(line);

				for(int i1=0; i1<line.length();i1++)
				{	
					int p=img.getRGB(x, y);
					char read = trial.charReader(line1, i1);
					int r = trial.translate(read);

					p = trial.Gsetter(r);
					img.setRGB(x, y, p); 
					y++;
				}
				int p = trial.Gsetter(250);
				img.setRGB(++x, y, p);
				x++; y=0;
			}

			f = new File("Output_"+ photoName +".png"); 
			ImageIO.write(img, "png", f); 

			//encrypted text generator
			int m = trial.count();

			FileWriter fw1 = new FileWriter(FileName);
			PrintWriter PrintOnFile = new PrintWriter(fw1);
			PrintOnFile.print(trial.FakeText(m));
			PrintOnFile.close();

			//System.out.print("Cipher 1");
		}
		catch (IOException e) {}
	}

	public static void EncryptCase2 (String photoName)
	{
		try
		{
			String FileName = "Data_" + photoName + ".txt";
			encrypt trial = new encrypt (FileName);

			//x y coordinates of the photo to write on
			int x=1;
			int y=1;

			BufferedImage img = null; 
			File f = null; 

			f = new File("Output.png"); 
			img = ImageIO.read(f); 

			for (int i=0; i<trial.numberOfLines();i++)
			{

				String line= trial.FileReader(i);
				String line1 = trial.stringReader(line);

				for(int i1=0; i1<line.length();i1++)
				{	
					int p=img.getRGB(x, y);
					char read = trial.charReader(line1, i1);
					int r = trial.translate(read);

					p = trial.Bsetter(r);
					img.setRGB(x, y, p); 
					y++;
				}
				int p = trial.Bsetter(250);
				img.setRGB(++x, y, p);
				x++; y=0;
			}

			f = new File("Output_"+ photoName +".png"); 
			ImageIO.write(img, "png", f); 

			//encrypted text generator
			int m = trial.count();

			FileWriter fw1 = new FileWriter(FileName);
			PrintWriter PrintOnFile = new PrintWriter(fw1);
			PrintOnFile.print(trial.FakeText(m));
			PrintOnFile.close();

			//System.out.println("Cipher 2");
		}
		catch (IOException e) {}
	}

	public static void EncryptAccounts()
	{
		try
		{
			String FileName = "accounts.txt";
			encrypt trial = new encrypt (FileName);

			//x y coordinates of the photo to write on
			int x=1;
			int y=1;

			BufferedImage img = null; 
			File f = null; 

			f = new File("Output.png"); 
			img = ImageIO.read(f); 

			for (int i=0; i<trial.numberOfLines();i++)
			{
				String line= trial.FileReader(i);
				String line1 = trial.stringReader(line);

				for(int i1=0; i1<line.length();i1++)
				{	
					int p=img.getRGB(x, y);
					char read = trial.charReader(line1, i1);
					int r = trial.translate(read);

					p = trial.Gsetter(r);
					img.setRGB(x, y, p); 
					y++;
				}
				int p = trial.Gsetter(250);
				img.setRGB(++x, y, p);
				x++; y=0;
			}

			f = new File("Output_accs.png"); 
			ImageIO.write(img, "png", f); 

			//encrypted text generator
			int m = trial.count();

			FileWriter fw1 = new FileWriter(FileName);
			PrintWriter PrintOnFile = new PrintWriter(fw1);
			PrintOnFile.print(trial.FakeText(m));
			PrintOnFile.close();

			//System.out.print("Cipher 1");
		}
		catch (IOException e) {}
	}

	public static void EncryptImages()
	{
		try
		{
			String FileName = "profImages.txt";
			encrypt trial = new encrypt (FileName);

			//x y coordinates of the photo to write on
			int x=1;
			int y=1;

			BufferedImage img = null; 
			File f = null; 

			f = new File("Output.png"); 
			img = ImageIO.read(f); 

			for (int i=0; i<trial.numberOfLines();i++)
			{
				String line= trial.FileReader(i);
				String line1 = trial.stringReader(line);

				for(int i1=0; i1<line.length();i1++)
				{	
					int p=img.getRGB(x, y);
					char read = trial.charReader(line1, i1);
					int r = trial.translate(read);

					p = trial.Gsetter(r);
					img.setRGB(x, y, p); 
					y++;
				}
				int p = trial.Gsetter(250);
				img.setRGB(++x, y, p);
				x++; y=0;
			}

			f = new File("Output_imgs.png"); 
			ImageIO.write(img, "png", f); 

			//encrypted text generator
			int m = trial.count();

			FileWriter fw1 = new FileWriter(FileName);
			PrintWriter PrintOnFile = new PrintWriter(fw1);
			PrintOnFile.print(trial.FakeText(m));
			PrintOnFile.close();

			//System.out.print("Cipher 1");
		}
		catch (IOException e) {}
	}

}
