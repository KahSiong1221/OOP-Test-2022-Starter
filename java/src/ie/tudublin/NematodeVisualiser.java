package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NematodeVisualiser extends PApplet
{

	ArrayList<Nematode> nematodes = new ArrayList<Nematode>();
	int nIndex = 0;
	int nSize;
	float c = 0;

	public void keyPressed()
	{		
		if (keyCode == LEFT)
		{
			if (nIndex == 0)
			{
				nIndex = nSize;
			}
			else
			{
				nIndex--;
			}
		}	
		else if (keyCode == RIGHT)
		{
			if (nIndex == nSize)
			{
				nIndex = 0;
			}
			else
			{
				nIndex++;
			}
		}	

		c = map(nIndex, 0, nSize, 0, 255);
	}


	public void settings()
	{
		size(800, 800);
	}

	public void setup() 
	{
		colorMode(HSB);
		background(0);
		smooth();		
		
		loadNematodes();
		printNematodes();
		nSize = nematodes.size() - 1;
	}
	

	public void loadNematodes()
	{
		Table table = loadTable("nematodes.csv", "header");

		for (TableRow r:table.rows())
		{
			Nematode n = new Nematode(r);
			nematodes.add(n);
		}
	}

	public void printNematodes()
	{
		for (Nematode n:nematodes)
		{
			System.out.println(n);
		}
	}

	public void drawNematodes()
	{
		nematodes.get(nIndex).render(this, c);
	}

	public void draw()
	{	
		background(0);
		drawNematodes();

		noFill();
		stroke(c, 255, 255);

		float tx = width * 3 / 4;
		float ty = height / 2;
		triangle(tx, ty, tx - 50, ty - 50, tx - 50, ty + 50);
		tx = width * 1 / 4;
		triangle(tx, ty, tx + 50, ty - 50, tx + 50, ty + 50);
	}
}
