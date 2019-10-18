package SpaceBattle.Menu.core;

import SpaceBattle.Config.Impostazioni;

public class ThreadMenu implements Runnable{
	
	private GestoreMenu m;
	
	public ThreadMenu(GestoreMenu m)
	{
		this.m = m;
	}

	@Override
	public void run() {
		while(true)
		{
			if(m.giocatore.getVita() > Impostazioni.VITA_TERMINATA)
				m.giocatore.gestoreProiettili.aggiungiProiettile(m.giocatore.getX()+m.giocatore.getLunghezza()/2,m.giocatore.getY()+m.giocatore.getAltezza()/2, Impostazioni.PROIETTILE_DESTRA);
			m.giocatore.gestoreProiettili.ricaricaProiettili();
			
			for(int i=0; i<m.gestoreNemici.size(); i++)
			{
				if(m.gestoreNemici.get(i).getVita() > Impostazioni.VITA_TERMINATA && m.gestoreNemici.get(i)!=null)
					m.gestoreNemici.get(i).gestoreProiettili.aggiungiProiettile(m.gestoreNemici.get(i).getX() + m.gestoreNemici.get(i).getLunghezza()/2, m.gestoreNemici.get(i).getY() + m.gestoreNemici.get(i).getAltezza()/2, Impostazioni.PROIETTILE_SINISTRA);
				
				if(m.gestoreNemici.get(i)!=null)
					m.gestoreNemici.get(i).gestoreProiettili.ricaricaProiettili();
			}
			
			try {
				Thread.sleep(Impostazioni.FPS_THREAD_MENU);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
