package SpaceBattle.LogicaCondivisa.core;

public class Oggetto 
{
	protected int altezza;
	protected int lunghezza;
	protected int x;
	protected int y;
	
	public Oggetto(int x, int y, int l, int h)
	{
		this.x = x;
		this.y = y;
		this.altezza = h;
		this.lunghezza = l;
	}
	
	public Oggetto(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Oggetto() 
	{
		x = 0;
		y = 0;
	}

	public int getAltezza() {
		return altezza;
	}

	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}

	public int getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(int lunghezza) {
		this.lunghezza = lunghezza;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
