package SpaceBattle.Viaggio.core;
import java.util.Random;

import SpaceBattle.Config.Impostazioni;

public class ThreadMuoviNemici implements Runnable {

	private static final int FPS = 80;
	private GestoreViaggio g;
	private int cont1 = 0;
	private int cont2 = 0;
	private int nave1;
	private int nave2;
	Random index = new Random();
	
	public ThreadMuoviNemici(GestoreViaggio g)
	{
		this.g = g;
		if(!g.gestoreNemici.nemici.isEmpty())
		{
			nave1 = index.nextInt(g.gestoreNemici.size());
			nave2 = index.nextInt(g.gestoreNemici.size());
		}
	}
	
	@Override
	public void run() {
		
		while(g.giocatore.getVita() > 0)
		{
			cont1+=5;
			cont2++;
			if(cont1 >= 170)
			{
				if(!g.gestoreNemici.nemici.isEmpty())
					nave1 = index.nextInt(g.gestoreNemici.size());
				

				cont1 = 0;
			}
			
			if(cont2 >= 130)
			{ 
				if(!g.gestoreNemici.nemici.isEmpty())
					nave2 = index.nextInt(g.gestoreNemici.size());
				cont2 = 0;
			}
			
			try {
		
					
				if(cont1 > 10 && cont1 < 80  && nave1 >= 0 && nave1 < g.gestoreNemici.size() && g.gestoreNemici.get(nave1)!=null && g.gestoreNemici.get(nave1).getVita() > Impostazioni.VITA_TERMINATA)
					g.gestoreNemici.get(nave1).muoviGiu(Impostazioni.HEIGHT/3);

				if (cont1 > 90 && nave1 >= 0 && nave1 < g.gestoreNemici.size() && g.gestoreNemici.get(nave1)!=null  && g.gestoreNemici.get(nave1).getVita() > Impostazioni.VITA_TERMINATA)
					g.gestoreNemici.get(nave1).muoviSu(Impostazioni.MIN);
				
				if(cont2 > 50 && cont2 < 110 && nave2 >= 0 && nave2 < g.gestoreNemici.size() && g.gestoreNemici.get(nave2)!=null && g.gestoreNemici.get(nave2).getVita() > Impostazioni.VITA_TERMINATA)
					g.gestoreNemici.get(nave2).muoviGiu(Impostazioni.HEIGHT/3);
				
				if(cont2 > 120 &&  nave2 >= 0 && nave2 < g.gestoreNemici.size() && g.gestoreNemici.get(nave2)!=null && g.gestoreNemici.get(nave2).getVita() > Impostazioni.VITA_TERMINATA)
					g.gestoreNemici.get(nave2).muoviSu(Impostazioni.MIN);
				
				Thread.sleep(FPS);
			
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
