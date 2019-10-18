package SpaceBattle.Menu.core;

import java.util.ArrayList;
import java.util.Random;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.LogicaCondivisa.core.GestoreCollisioni;
import SpaceBattle.LogicaCondivisa.core.GestoreNemici;
import SpaceBattle.LogicaCondivisa.core.Nave;
import SpaceBattle.LogicaCondivisa.core.Nemico;

public class GestoreMenu implements Runnable 
{
	public ArrayList<Integer> index;
	public GestoreNemici gestoreNemici;
	public GestoreCollisioni gestoreCollisioni;
	public Nave giocatore = null;
	
	Random r = new Random();
	
	public GestoreMenu()
	{
		index = new ArrayList<Integer>();
		gestoreNemici = new GestoreNemici();
		gestoreCollisioni = new GestoreCollisioni();
		
		creaNaveGiocatore();
		creaNaviMenu();
		inizializzaIndexMenu();
		
		Thread t = new Thread(this);
		t.start();
		
		Thread t1 = new Thread(new ThreadDirezionale(this));
		t1.start();
		
		Thread t2 = new Thread(new ThreadMenu(this));
		t2.start();
	}
	
	
	public void creaNaveGiocatore()
	{
		giocatore = new Nave(Impostazioni.WIDTH/6, Impostazioni.HEIGHT/2);
		giocatore.setAltezza(Impostazioni.DIMGIOCATOREMENU);
		giocatore.setLunghezza(Impostazioni.DIMGIOCATOREMENU);
		giocatore.upgrade(3);
	}
	

	public void creaNaviMenu()
	{
		gestoreNemici.add(new Nemico(Impostazioni.WIDTH-(Impostazioni.WIDTH/4), Impostazioni.HEIGHT/3));
		gestoreNemici.add(new Nemico(Impostazioni.WIDTH-(Impostazioni.WIDTH/4), Impostazioni.HEIGHT-(Impostazioni.HEIGHT/3)));
		
		for(int i=0; i<gestoreNemici.size(); i++)
		{
			gestoreNemici.get(i).setVita(Impostazioni.VITA_NEMICI_MENU);
			gestoreNemici.get(i).setAltezza(Impostazioni.DIMNEMICIMENU);
			gestoreNemici.get(i).setLunghezza(Impostazioni.DIMNEMICIMENU);
		}
	}
	
	public void inizializzaIndexMenu()
	{
		for(int i = 0; i<gestoreNemici.size(); i++)
			index.add(new Integer(r.nextInt(4)+1));
	}
	
	public void aggiornaNemiciMenu(int x)
	{
		Random pos = new Random();
		if(gestoreNemici.size() < Impostazioni.NUMERO_NEMICI_MENU)
		{
			if(pos.nextInt(2)==0)
				gestoreNemici.add(new Nemico(Impostazioni.WIDTH-(Impostazioni.WIDTH/4), Impostazioni.HEIGHT/3));
				
			else
				gestoreNemici.add(new Nemico(Impostazioni.WIDTH-(Impostazioni.WIDTH/4), Impostazioni.HEIGHT-(Impostazioni.HEIGHT/3)));
			
			gestoreNemici.get(gestoreNemici.size()-1).setLunghezza(Impostazioni.DIMNEMICIMENU);
			gestoreNemici.get(gestoreNemici.size()-1).setAltezza(Impostazioni.DIMNEMICIMENU);
			
			gestoreNemici.get(gestoreNemici.size()-1).gestoreProiettili.aggiungiProiettile(gestoreNemici.get(gestoreNemici.size()-1).getX()+Impostazioni.DIMNEMICIMENU/2, gestoreNemici.get(gestoreNemici.size()-1).getY()+Impostazioni.DIMNEMICIMENU/2, Impostazioni.PROIETTILE_SINISTRA);

			if(x==0)
			{
				index.set(0, index.get(1)); 
				index.set(1, pos.nextInt(4)+1);
			}
			else
				index.set(1, pos.nextInt(4)+1);
			
		}
		
	}
	
	public void muoviNaviMenu(Nave giocatore)
	{
		final int min = 30;
		
		//GIOCATORE
		if(giocatore.getDirezione() == 0)
			giocatore.muoviSx(min);
		if(giocatore.getDirezione() == 1)
			giocatore.muoviDx(Impostazioni.WIDTH/3-Impostazioni.DIMGIOCATOREMENU-min);
		if(giocatore.getDirezione() == 2)
			giocatore.muoviSu(Impostazioni.HEIGHT/4);
		if(giocatore.getDirezione() == 3)
			giocatore.muoviGiu(Impostazioni.HEIGHT-Impostazioni.DIMGIOCATOREMENU-min);
		
		//NAVE 1
		if(gestoreNemici.size()>0 && gestoreNemici.get(0).getVita() > Impostazioni.VITA_TERMINATA)
		{
			if(gestoreNemici.get(0).getDirezione() == 0)
				gestoreNemici.get(0).muoviSx(Impostazioni.WIDTH-(Impostazioni.WIDTH/3));
			if(gestoreNemici.get(0).getDirezione() == 1)
				gestoreNemici.get(0).muoviDx(Impostazioni.WIDTH-Impostazioni.DIMNEMICIMENU-min);
			if(gestoreNemici.get(0).getDirezione() == 2)
				gestoreNemici.get(0).muoviSu(Impostazioni.HEIGHT/4);
			if(gestoreNemici.get(0).getDirezione() == 3)
				gestoreNemici.get(0).muoviGiu(Impostazioni.HEIGHT-Impostazioni.DIMNEMICIMENU-min);
		}
		
		//NAVE 2
		if(gestoreNemici.size()>1 && gestoreNemici.get(1).getVita() > Impostazioni.VITA_TERMINATA)
		{
			if(gestoreNemici.get(1).getDirezione() == 0)
				gestoreNemici.get(1).muoviSx(Impostazioni.WIDTH-(Impostazioni.WIDTH/3));
			if(gestoreNemici.get(1).getDirezione() == 1)
				gestoreNemici.get(1).muoviDx(Impostazioni.WIDTH-Impostazioni.DIMNEMICIMENU-min);
			if(gestoreNemici.get(1).getDirezione() == 2)
				gestoreNemici.get(1).muoviSu(Impostazioni.HEIGHT/4);
			if(gestoreNemici.get(1).getDirezione() == 3)
				gestoreNemici.get(1).muoviGiu(Impostazioni.HEIGHT-Impostazioni.DIMNEMICIMENU-min);
		}
	}
	
	public void aggiornaProiettiliNavi()
	{
		giocatore.aggiornaProiettiliViaggio();
		for(int i = 0; i<gestoreNemici.size(); i++)
		{
			if(gestoreNemici.get(i)!=null)
				gestoreNemici.get(i).aggiornaProiettiliViaggio();
		}
	}
	
	public void verificaCollisioniMenu()
	{
		for(int i=0; i<gestoreNemici.size(); i++)
		{
			if(gestoreNemici.get(i)!=null)
				gestoreCollisioni.gestisciCollisioneProViaggio(giocatore, gestoreNemici.get(i));
					
			if(gestoreNemici.get(i).pro().size() == 0 )	
			{
				if(gestoreNemici.get(i).getContEsplosioni() >= Impostazioni.NESPLOSIONIMENU)
				{
					gestoreNemici.remove(i); 
					aggiornaNemiciMenu(i); 	
				}		
			}
			
			if(gestoreNemici.get(i)!=null)
				gestoreCollisioni.gestisciCollisioneProViaggio(gestoreNemici.get(i), giocatore);
			if(giocatore.getVita() <= 5)
				giocatore.setVita(500);
		}
	}

	public void run() {
		while(true)
		{
			muoviNaviMenu(giocatore);
			aggiornaProiettiliNavi();
			verificaCollisioniMenu();
			
			try {
				Thread.sleep(Impostazioni.FPS_MENU);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
