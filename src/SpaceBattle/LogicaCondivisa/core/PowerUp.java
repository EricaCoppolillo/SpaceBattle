package SpaceBattle.LogicaCondivisa.core;

public class PowerUp extends Oggetto{
	
	public int imgPowerUp;
	public boolean preso;
	public int tipo;
	
	public PowerUp(int x, int y, int l, int a, int tipo)
	{
		super(x, y, l, a);
		imgPowerUp = 0;
		preso = false;
		this.tipo = tipo;
	}
}
