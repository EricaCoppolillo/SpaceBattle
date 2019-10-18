package SpaceBattle.LogicaCondivisa.core;

import java.util.ArrayList;
import java.util.Random;

import SpaceBattle.Config.Impostazioni;

public class GestoreNemici
{
	
	Random r = new Random();
	
	public ArrayList<Nemico> nemici;
	
	public GestoreNemici() 
	{
		nemici = new ArrayList<Nemico>();
	}
		
	public int size()
	{return nemici.size();}
	
	public void add(Nemico n)
	{
		nemici.add(n);
	}
	
	public synchronized Nave get(int i)
	{
		return nemici.get(i);
	}
	
	public void generaNemici(int n, int x, int y, int cod)
	{
		for(int i = 0; i<n; i++)
			nemici.add(new Nemico(x,y,cod));
	}
	
	public void generaNemici(int n, int x, int y)
	{
		for(int i = 0; i<n; i++)
			nemici.add(new Nemico(x,y));
	}
	
	public void generaNemici(int n)
	{
		nemici.add(new Nemico());
	}
	
	public void rimuoviNemiciDistrutti()
	{
		for(int i=0; i<nemici.size(); i++)
		{
			if(nemici.get(i).getVita() < Impostazioni.VITA_TERMINATA)
				nemici.remove(i);
		}
	}
	
	public void remove(int i)
	{
		nemici.remove(i);
	}

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
