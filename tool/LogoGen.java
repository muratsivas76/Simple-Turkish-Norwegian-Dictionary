import java.io.*;

import java.awt.*;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

final
public class LogoGen
{

	private LogoGen ()
	{
		super ();
	}

	public String toString ()
	{
		return "";
	}

	final
	private static void logo (String name)
	throws IOException
	{
		BufferedImage bi=new BufferedImage (144, 144, 1);
		Graphics2D g2d=bi.createGraphics ();

		g2d.setBackground (Color.white);
		g2d.clearRect (0, 0, 144, 144);

		g2d.setFont (new Font ("Verdana", 3, 60));
		g2d.setColor (randomColor ());

		StringBuffer sb=new StringBuffer ();

		final int namelen=name.length ();

		if (namelen == 1)
		{
			sb.append (name.charAt (0));
		}
		else if (namelen > 1)
		{
			sb.append (name.charAt (0));
			sb.append (name.charAt (1));
		}
		else
		{
			sb.append (name);
		}

		name=sb.toString ();
		name=name.toUpperCase ();

		int xc=70-(((g2d.getFontMetrics ()).stringWidth (name))/2);
		int yc=90;//(((g2d.getFontMetrics ()).getHeight ())/2);

		g2d.drawString (name, xc, yc);

		g2d.setColor (randomColor ());
		g2d.setStroke (new BasicStroke (3F));
		g2d.drawOval (1, 1, 140, 140);

		File fl=new File ("res/drawable/notrlogo.png");

		ImageIO.write (bi, "PNG", fl);

		System.out.println ("Generated: res/drawable/"+fl.getName ()+"");

		return;
	}

	final
	private static Color randomColor ()
	{
		java.util.Random random=new java.util.Random ();

		int r=random.nextInt (255);
		int g=random.nextInt (255);
		int b=random.nextInt (255);

		if ( (r>200) && (g>200) && (b>200) )
		{
			g=0x0000;
		}

		Color col=new Color (r, g, b);

		return col;
	}

	final
	public static void main (final String [] args)
	{
		if (args.length < 1)
		{
			System.out.println ("/nUsage:	java -cp tool LogoGen [<newPackageName>]");
			System.out.println ("Example:	java -cp tool LogoGen notr");
			System.exit (-1);
		}

		String pn=args [0].toLowerCase ();

		try
		{
			logo (pn);
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace ();
			System.exit (-1);
		}
	}

}
