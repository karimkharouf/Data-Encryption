/**********************************************************************************************
 * DecryptCases.java helper class
 * Defines 3 different decryption cases for user data, and decrypts accounts and images files.
 ***********************************************************************************************/
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;

public class DecryptCases 
{	
	public static void DecryptCase0 (String photoName)
	{
		try
		{
			BufferedImage img = null;
			File f= null;
			String newFile = "Data_" + photoName + ".txt";
			FileWriter fw = new FileWriter(newFile);

			//creating a method to extract to the file
			PrintWriter WriteOnFile = new PrintWriter(fw);

			f = new File ("Output_"+ photoName +".png");
			img = ImageIO.read(f); 

			int x=0;
			int y=0;

			int width=img.getWidth();
			int height=img.getHeight();

			int r;
			r=0;

			for (x=0;x<width;x++)
			{
				for (y=0;y<height;y++)
				{
					int p = img.getRGB(x,y);
					r=decrypt.Rreader(p);	
					if(r!=255)
						WriteOnFile.print(decrypt.reverseTranslate(r));
				}

			}

			WriteOnFile.close();
		//	decrypt.formatImage(photoName);
			f.delete();
		}
		catch (IOException e) {}
	}

	public static void DecryptCase1 (String photoName) 
	{
		try
		{
			BufferedImage img = null;
			File f= null;
			String newFile = "Data_" + photoName + ".txt";
			FileWriter fw = new FileWriter(newFile);

			//creating a method to extract to the file
			PrintWriter WriteOnFile = new PrintWriter(fw);

			f = new File("Output_"+ photoName +".png");

			img = ImageIO.read(f); 

			int x=0;
			int y=0;

			int width=img.getWidth();
			int height=img.getHeight();

			int g;
			g=0;

			for (x=0;x<width;x++)
			{
				for (y=0;y<height;y++)
				{
					int p = img.getRGB(x,y);
					g=decrypt.Greader(p);	
					if(g!=255)
						WriteOnFile.print(decrypt.reverseTranslate(g));
				}
			}

			WriteOnFile.close();
			//decrypt.formatImage(photoName);
			f.delete();
		}
		catch (IOException e) {}
	}

	public static void DecryptCase2 (String photoName)
	{
		try
		{
			BufferedImage img = null;
			File f= null;
			String newFile = "Data_" + photoName + ".txt";
			FileWriter fw = new FileWriter(newFile);

			//creating a method to extract to the file
			PrintWriter WriteOnFile = new PrintWriter(fw);

			f = new File ("Output_"+ photoName +".png");

			img = ImageIO.read(f); 

			int x=0;
			int y=0;

			int width=img.getWidth();
			int height=img.getHeight();

			int b;
			b=0;

			for (x=0;x<width;x++)
			{
				for (y=0;y<height;y++)
				{
					int p = img.getRGB(x,y);
					b=decrypt.Breader(p);	
					if(b!=255)
						WriteOnFile.print(decrypt.reverseTranslate(b));
				}
			}

			WriteOnFile.close();
			//decrypt.formatImage(photoName);
			f.delete();
		}
		catch (IOException e) {}
	}

	public static void DecryptAccounts()
	{
		try
		{
			BufferedImage img = null;
			File f= null;
			String newFile = "accounts.txt";
			FileWriter fw = new FileWriter(newFile);

			//creating a method to extract to the file
			PrintWriter WriteOnFile = new PrintWriter(fw);

			f = new File("Output_accs.png");

			img = ImageIO.read(f); 

			int x=0;
			int y=0;

			int width=img.getWidth();
			int height=img.getHeight();

			int g;
			g=0;

			for (x=0;x<width;x++)
			{
				for (y=0;y<height;y++)
				{
					int p = img.getRGB(x,y);
					g=decrypt.Greader(p);	
					if(g!=255)
						WriteOnFile.print(decrypt.reverseTranslate(g));
				}
			}

			WriteOnFile.close();
			//decrypt.formatImageAcc();
		}
		catch (IOException e) {}
	}

	public static void DecryptImages()
	{
		try
		{
			BufferedImage img = null;
			File f= null;
			String newFile = "profImages.txt";
			FileWriter fw = new FileWriter(newFile);

			//creating a method to extract to the file
			PrintWriter WriteOnFile = new PrintWriter(fw);

			f = new File("Output_imgs.png");

			img = ImageIO.read(f); 

			int x=0;
			int y=0;

			int width=img.getWidth();
			int height=img.getHeight();

			int g;
			g=0;

			for (x=0;x<width;x++)
			{
				for (y=0;y<height;y++)
				{
					int p = img.getRGB(x,y);
					g=decrypt.Greader(p);	
					if(g!=255)
						WriteOnFile.print(decrypt.reverseTranslate(g));
				}
			}

			WriteOnFile.close();
			//decrypt.formatImagePics();
		}
		catch (IOException e) {}
	}
}
