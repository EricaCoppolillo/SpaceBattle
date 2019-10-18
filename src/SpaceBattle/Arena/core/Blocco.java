package SpaceBattle.Arena.core;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.LogicaCondivisa.core.Oggetto;

public class Blocco extends Oggetto {

	public int lato = Impostazioni.DIM_BLOCCO_ARENA;
	
	public Blocco(int x, int y)
	{
		super(x,y,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA);
	}
}
