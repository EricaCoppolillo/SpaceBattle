package SpaceBattle.Viaggio.core;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Gui.PanelMenu;
import SpaceBattle.Main.GameMain;

public class ViaggioListener implements KeyListener, Runnable 
{
	GestoreViaggio gestoreViaggio;
	boolean movimentoG[] = {false, false, false, false}; //sx, dx, su, giu
	boolean movimentoG2[] = {false, false, false, false}; //in caso ci fosse anche il secondo giocatore

	boolean spara = false;
	boolean primoSparo = true;
	
	boolean spara2 = false;
	boolean primoSparo2 = true;

	
	public ViaggioListener(GestoreViaggio g)
	{
		this.gestoreViaggio = g;
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public void skip()
	{
		gestoreViaggio.livelloCorrente++;
		gestoreViaggio.step = 0;
		gestoreViaggio.disegnaScritte = true;
		gestoreViaggio.gestoreNemici.nemici.clear();
		gestoreViaggio.gestisciLivelli();
	}
	
	public void allocaProGiocatore()
	{
		switch(gestoreViaggio.giocatore.getCodice())
		{
		case 0:
			gestoreViaggio.giocatore.gestoreProiettili.aggiungiProiettile(gestoreViaggio.giocatore.getX() + gestoreViaggio.giocatore.getLunghezza()/2-Impostazioni.MIN, gestoreViaggio.giocatore.getY(), Impostazioni.PROIETTILE_SOPRA);
			break;
			
		case 2:
			gestoreViaggio.giocatore.gestoreProiettili.aggiungiProiettile(gestoreViaggio.giocatore.getX() + gestoreViaggio.giocatore.getLunghezza()/2 - (4*Impostazioni.MIN), gestoreViaggio.giocatore.getY(), Impostazioni.PROIETTILE_SOPRA);
			gestoreViaggio.giocatore.gestoreProiettili.aggiungiProiettile(gestoreViaggio.giocatore.getX() + gestoreViaggio.giocatore.getLunghezza()/2 + (2*Impostazioni.MIN), gestoreViaggio.giocatore.getY(), Impostazioni.PROIETTILE_SOPRA);
			break;
			
		case 4:
			gestoreViaggio.giocatore.gestoreProiettili.aggiungiProiettile(gestoreViaggio.giocatore.getX() + gestoreViaggio.giocatore.getLunghezza()/2-Impostazioni.MIN, gestoreViaggio.giocatore.getY(), Impostazioni.PROIETTILE_SX_SU);
			gestoreViaggio.giocatore.gestoreProiettili.aggiungiProiettile(gestoreViaggio.giocatore.getX() + gestoreViaggio.giocatore.getLunghezza()/2-Impostazioni.MIN, gestoreViaggio.giocatore.getY(), Impostazioni.PROIETTILE_SOPRA);
			gestoreViaggio.giocatore.gestoreProiettili.aggiungiProiettile(gestoreViaggio.giocatore.getX() + gestoreViaggio.giocatore.getLunghezza()/2-Impostazioni.MIN, gestoreViaggio.giocatore.getY(), Impostazioni.PROIETTILE_DX_SU);
			break;
		}
	}
	
	public void allocaProGiocatore2()
	{
		gestoreViaggio.giocatore2.gestoreProiettili.aggiungiProiettile(gestoreViaggio.giocatore2.getX() + gestoreViaggio.giocatore2.getLunghezza()/2-Impostazioni.MIN, gestoreViaggio.giocatore2.getY(), 2);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(!gestoreViaggio.giocatore.isMorta() && !gestoreViaggio.disegnaScritte)
		{
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_LEFT:
				movimentoG[0] = true;
				break;
			
			case KeyEvent.VK_RIGHT:
				movimentoG[1] = true;
				break;
			
			case KeyEvent.VK_UP:
				movimentoG[2] = true;
				break;
			
			case KeyEvent.VK_DOWN:
				movimentoG[3] = true;
				break;
			
			case KeyEvent.VK_SPACE:
				spara = true;
				break;
				
			case KeyEvent.VK_N://per il prof 
				skip();
				break;
			}
			
		}
		
		if(gestoreViaggio.secondoGiocatore)
		{
			if(gestoreViaggio.giocatore2.getVita() > Impostazioni.VITA_TERMINATA && !gestoreViaggio.disegnaScritte)
			{
				switch(e.getKeyCode())
				{
				case KeyEvent.VK_A:
					movimentoG2[0] = true;
					break;
				
				case KeyEvent.VK_D:
					movimentoG2[1] = true;
					break;
				
				case KeyEvent.VK_W:
					movimentoG2[2] = true;
					break;
				
				case KeyEvent.VK_S: 
					movimentoG2[3] = true;
					break;
				
				case KeyEvent.VK_T:
					spara2 = true;
					break;
				}
				
			}
		}
			
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			gestoreViaggio.gestoreSuono.fermaSparo();
			gestoreViaggio.gestoreSuono.fermaEsplosione();
			gestoreViaggio.gestoreSuono.fermaSottofondo();
			GameMain.contenitore.removeAll();
			PanelMenu m = new PanelMenu();
			GameMain.contenitore.add(m);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			m.requestFocus();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			movimentoG[0] = false;
			break;
			
		case KeyEvent.VK_RIGHT:
			movimentoG[1] = false;
			break;
			
		case KeyEvent.VK_UP:
			movimentoG[2] = false;
			break;
			
		case KeyEvent.VK_DOWN:
			movimentoG[3] = false;
			break;
			
		case KeyEvent.VK_SPACE:
			spara = false;
			primoSparo = true;
			break;
		}
		
		if(gestoreViaggio.secondoGiocatore)
		{
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_A:
				movimentoG2[0] = false;
				break;
				
			case KeyEvent.VK_D:
				movimentoG2[1] = false;
				break;
				
			case KeyEvent.VK_W:
				movimentoG2[2] = false;
				break;
				
			case KeyEvent.VK_S:
				movimentoG2[3] = false;
				break;
				
			case KeyEvent.VK_T:
				spara2 = false;
				primoSparo2 = true;
				break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void run() {
		
		int min = 10;//limite minimo dal bordo sx 

		while(true)
		{
			if(gestoreViaggio.giocatore.getVita() > Impostazioni.VITA_TERMINATA) 
			{
				if(movimentoG[0])//sx
					gestoreViaggio.giocatore.muoviSx(min);
				
				if(movimentoG[1])//dx
					gestoreViaggio.giocatore.muoviDx(Impostazioni.WIDTH - gestoreViaggio.giocatore.getLunghezza() - min);				
				
				if(movimentoG[2])//su
					gestoreViaggio.giocatore.muoviSu(min);
					
				if(movimentoG[3])//giu
					gestoreViaggio.giocatore.muoviGiu(Impostazioni.HEIGHT - gestoreViaggio.giocatore.getAltezza() - min);
				
				if(spara && primoSparo)
				{
					allocaProGiocatore();
					gestoreViaggio.gestoreSuono.avviaSparo();
					primoSparo = false;
				}
			}
			
			if(gestoreViaggio.secondoGiocatore && gestoreViaggio.giocatore2.getVita() > Impostazioni.VITA_TERMINATA)
			{
				if(movimentoG2[0])//sx
					gestoreViaggio.giocatore2.muoviSx(min);
				
				if(movimentoG2[1])//dx
					gestoreViaggio.giocatore2.muoviDx(Impostazioni.WIDTH - gestoreViaggio.giocatore2.getLunghezza() - min);				
				
				if(movimentoG2[2])//su
					gestoreViaggio.giocatore2.muoviSu(min);
					
				if(movimentoG2[3])//giu
					gestoreViaggio.giocatore2.muoviGiu(Impostazioni.HEIGHT - gestoreViaggio.giocatore2.getAltezza() - min);
				
				if(spara2 && primoSparo2)
				{
					allocaProGiocatore2();
					gestoreViaggio.gestoreSuono.avviaSparo();
					primoSparo2 = false;
				}
			}
			try {
				Thread.sleep(Impostazioni.FPS_VIAGGIO_LISTENER);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
