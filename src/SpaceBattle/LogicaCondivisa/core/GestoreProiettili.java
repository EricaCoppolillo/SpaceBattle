package SpaceBattle.LogicaCondivisa.core;
import java.util.ArrayList;

import SpaceBattle.Config.Impostazioni;

public class GestoreProiettili 
{
	public ArrayList<Proiettile> pro = new ArrayList<Proiettile>();
	
	public GestoreProiettili() {}
	
	public void aggiungiProiettile(int x, int y, int dir)
	{
		pro.add(new Proiettile(x, y, dir));
	}
	
	public void aggiungiProiettile(int x, int y)
	{
		pro.add(new Proiettile(x, y));
	}
	
	public void ricaricaProiettili()
	{
		for(int i = 0; i<pro.size(); i++)
			if (pro.get(i)!=null && (pro.get(i).x <= 0 || pro.get(i).x >= Impostazioni.WIDTH || pro.get(i).y <= 0 || pro.get(i).y >= Impostazioni.HEIGHT))
				pro.remove(i);		
	}
		
}
