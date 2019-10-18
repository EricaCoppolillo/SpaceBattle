package SpaceBattle.Arena.core;

import SpaceBattle.Config.Impostazioni;

public class Elemento 
{
	public int numero;
	public boolean bool;
	public Blocco blocco;
	public boolean navigabile = true;
	
	
	public Elemento()
	{
		numero = 0;
		bool = false;
		blocco = new Blocco(Impostazioni.DIM_BLOCCO_ARENA, Impostazioni.DIM_BLOCCO_ARENA);
		navigabile = true;
	}
	
}
