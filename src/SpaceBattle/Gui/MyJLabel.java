package SpaceBattle.Gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyJLabel extends JLabel
{
	public int riga;
	public int colonna;
	
	public MyJLabel()
	{
		super();
	}
	
	public MyJLabel(ImageIcon icon, int riga, int colonna) 
	{
		super(icon);
		this.riga = riga;
		this.colonna = colonna;

	}

	public int getColonna() {
		return colonna;
	}

	public void setColonna(int colonna) {
		this.colonna = colonna;
	}


	public int getRiga() {
		return riga;
	}

	public void setRiga(int riga) {
		this.riga = riga;
	}
}
