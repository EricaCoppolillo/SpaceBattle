package SpaceBattle.LogicaCondivisa.core;

public class Proiettile extends Oggetto{

	
	private int direzione = 0;
	
	public Proiettile() 
	{
		x=0;
		y=0;
		this.lunghezza = 20;
		this.altezza = 20;
	}
	
	public Proiettile(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.lunghezza = 20;
		this.altezza = 20;
	}
	
	public Proiettile(int x, int y, int dir)
	{
		this.x=x;
		this.y=y;
		this.direzione = dir;
		this.lunghezza = 20;
		this.altezza = 20;
	}

	public int getDirezione()
	{
		return direzione;
	}
	
	public void setDirezione(int dir)
	{
		direzione = dir;
	}
	
	public void sparaSx(int potenza, int min)
	{
		if(x > min)
			x -= potenza;
	}
	
	public void sparaDx(int potenza, int max)
	{
		if(x < max)
			x += potenza;
	}
	
	public void sparaSu(int potenza, int min)
	{
		if(y > min)
			y -= potenza;
	}
	
	public void sparaGiu(int potenza, int max)
	{
		if(y < max)
			y += potenza;
	}
	
	public void sparaSxSu(int potenza, int min1, int min2)
	{
		if(x > min2 && y > min2)
		{
			x -= potenza;
			y -= potenza;
		}
	}
		
	public void sparaSxGiu(int potenza, int min, int max)
	{
		if(x > min && y < max)
		{
			x -= potenza;
			y += potenza;
		}
	}
	
	public void sparaDxSu(int potenza, int max, int min)
	{
		if(x < max && y > min)
		{
			x += potenza;
			y -= potenza;
		}
	}
	
	public void sparaDxGiu(int potenza, int max1, int max2)
	{
		if(x < max1 && y < max2)
		{
			x += potenza;
			y += potenza;
		}
	}
	
}
