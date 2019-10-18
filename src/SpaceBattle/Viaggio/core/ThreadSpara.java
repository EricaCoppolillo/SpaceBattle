package SpaceBattle.Viaggio.core;

import SpaceBattle.Config.Impostazioni;

public class ThreadSpara implements Runnable {

	private static final int FPS = 1500;
	private GestoreViaggio g;

	public ThreadSpara(GestoreViaggio g) {
		this.g = g;
	}

	@Override
	public void run()
	{
		while(!g.giocatore.isMorta() || (g.secondoGiocatore && !g.giocatore2.isMorta()))
		{
			try 
			{
				for(int i = 0; i < g.gestoreNemici.size(); i++) 
				{
					if(g.gestoreNemici.get(i).getVita() > Impostazioni.VITA_TERMINATA && !g.disegnaScritte) 
					{
						g.gestoreSuono.avviaSparo();
						switch (g.gestoreNemici.get(i).getCodice()) 
						{
						case 0:
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SOTTO);
							break;
	
						case 1:
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - (4*Impostazioni.MIN), g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza() / 2, Impostazioni.PROIETTILE_SOTTO);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 + (4*Impostazioni.MIN), g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SOTTO);
							break;
	
						case 2:
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SX_GIU);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX()+ g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SOTTO);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX()+ g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_DX_GIU);
							break;
	
						case 3:
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SINISTRA);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX()+ g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SX_GIU);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SINISTRA);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_DX_GIU);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_DESTRA);
							break;
	
						case 4:
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SOPRA);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SX_SU);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SINISTRA);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SX_GIU);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SOTTO);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_DX_GIU);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_DESTRA);
							g.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(g.gestoreNemici.get(i).getX() + g.gestoreNemici.get(i).getLunghezza()/2 - Impostazioni.MIN, g.gestoreNemici.get(i).getY() + g.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_DX_SU);
							break;
						}
					}	
				}
			
				Thread.sleep(FPS);
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

