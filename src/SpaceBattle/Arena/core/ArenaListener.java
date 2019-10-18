package SpaceBattle.Arena.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import SpaceBattle.Config.Impostazioni;

public class ArenaListener implements KeyListener, Runnable {

	public GestoreArena gestoreArena = null;
	boolean movimentoG[] = {false, false, false, false}; 
	boolean spara = false;
	boolean primoSparo = true;
	
	public ArenaListener(GestoreArena gestoreArena)
	{
		this.gestoreArena = gestoreArena;
		Thread  t = new Thread(this);
		t.start();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if(!gestoreArena.collisioni.nave_blocco_sopra_sotto(gestoreArena.nave_utente, gestoreArena.matrix))
				movimentoG[Impostazioni.SOTTO] = true;
			
		}
		
		else if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			if(!(gestoreArena.collisioni.nave_blocco_sotto_sopra(gestoreArena.nave_utente, gestoreArena.matrix)))
				movimentoG[Impostazioni.SOPRA] = true;
		}
		
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(!(gestoreArena.collisioni.nave_blocco_destra_sinistra(gestoreArena.nave_utente, gestoreArena.matrix)))
				movimentoG[Impostazioni.SINISTRA] = true;
		}
		
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(!(gestoreArena.collisioni.nave_blocco_sinistra_destra(gestoreArena.nave_utente, gestoreArena.matrix)))
				movimentoG[Impostazioni.DESTRA] = true;
		}
		
		else if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			spara = true;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_A)
		{
			if(gestoreArena.nave_utente.getVelocita() <= Impostazioni.VELOCITA_NAVE_UTENTE_ARENA*2)
				gestoreArena.nave_utente.setVelocita(Impostazioni.VELOCITA_NAVE_UTENTE_ARENA*2);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			movimentoG[Impostazioni.SINISTRA] = false;
			break;
			
		case KeyEvent.VK_RIGHT:
			movimentoG[Impostazioni.DESTRA] = false;
			break;
			
		case KeyEvent.VK_UP:
			movimentoG[Impostazioni.SOPRA] = false;
			break;
			
		case KeyEvent.VK_DOWN:
			movimentoG[Impostazioni.SOTTO] = false;
			break;
		case KeyEvent.VK_SPACE:
			spara = false;
			primoSparo = true;
		case KeyEvent.VK_A:
			if(gestoreArena.nave_utente.getVelocita() >= Impostazioni.VELOCITA_NAVE_UTENTE_ARENA*2)
				gestoreArena.nave_utente.setVelocita(gestoreArena.nave_utente.getVelocita()/2);
			
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}
	@Override
	public void run() {
		
		while(true)
		{
			if(gestoreArena.nave_utente.getVita() > Impostazioni.VITA_TERMINATA)
			{
			if(movimentoG[Impostazioni.DESTRA] && !movimentoG[Impostazioni.SOPRA] && !movimentoG[Impostazioni.SINISTRA] && !movimentoG[Impostazioni.SOTTO])
			{
				gestoreArena.nave_utente.setDirezione(Impostazioni.DESTRA);
				if(!(gestoreArena.collisioni.nave_blocco_sinistra_destra(gestoreArena.nave_utente, gestoreArena.matrix)))
					gestoreArena.nave_utente.muoviDx(Impostazioni.DIM_BLOCCO_ARENA*(gestoreArena.matrix.colonne-1));
			}
			if(!movimentoG[Impostazioni.DESTRA] && movimentoG[Impostazioni.SOPRA] && !movimentoG[Impostazioni.SINISTRA] && !movimentoG[Impostazioni.SOTTO])
			{
				gestoreArena.nave_utente.setDirezione(Impostazioni.SOPRA);
				if(!(gestoreArena.collisioni.nave_blocco_sotto_sopra(gestoreArena.nave_utente, gestoreArena.matrix)))
					gestoreArena.nave_utente.muoviSu(Impostazioni.INIZIO_ARENA_Y);
			}
			if(!movimentoG[Impostazioni.DESTRA] && !movimentoG[Impostazioni.SOPRA] &&  movimentoG[Impostazioni.SINISTRA] && !movimentoG[Impostazioni.SOTTO])
			{
				gestoreArena.nave_utente.setDirezione(Impostazioni.SINISTRA);
				if(!(gestoreArena.collisioni.nave_blocco_destra_sinistra(gestoreArena.nave_utente, gestoreArena.matrix)))
					gestoreArena.nave_utente.muoviSx(Impostazioni.INIZIO_ARENA_X);
			}
			if(!movimentoG[Impostazioni.DESTRA] && !movimentoG[Impostazioni.SOPRA] && !movimentoG[Impostazioni.SINISTRA] && movimentoG[Impostazioni.SOTTO]) 
			{
				gestoreArena.nave_utente.setDirezione(Impostazioni.SOTTO);
				if(!gestoreArena.collisioni.nave_blocco_sopra_sotto(gestoreArena.nave_utente, gestoreArena.matrix))
					gestoreArena.nave_utente.muoviGiu(Impostazioni.DIM_BLOCCO_ARENA*(Impostazioni.RIGHE_MATRICE-1));
					
			}
			if(spara && primoSparo) 
			{
				switch(gestoreArena.nave_utente.getDirezione()) 
				{
				case Impostazioni.DESTRA:
					gestoreArena.nave_utente.gestoreProiettili.aggiungiProiettile(gestoreArena.nave_utente.getX()+gestoreArena.nave_utente.getAltezza(), gestoreArena.nave_utente.getY()+gestoreArena.nave_utente.getAltezza()/3,Impostazioni.PROIETTILE_DESTRA);
					break;
				case Impostazioni.SOPRA:
					gestoreArena.nave_utente.gestoreProiettili.aggiungiProiettile(gestoreArena.nave_utente.getX()+gestoreArena.nave_utente.getLunghezza()/3, gestoreArena.nave_utente.getY(),Impostazioni.PROIETTILE_SOPRA);
					break;
				case Impostazioni.SINISTRA:
					gestoreArena.nave_utente.gestoreProiettili.aggiungiProiettile(gestoreArena.nave_utente.getX(), gestoreArena.nave_utente.getY()+gestoreArena.nave_utente.getAltezza()/3,Impostazioni.PROIETTILE_SINISTRA);
					break;
				case Impostazioni.SOTTO:
					gestoreArena.nave_utente.gestoreProiettili.aggiungiProiettile(gestoreArena.nave_utente.getX()+gestoreArena.nave_utente.getLunghezza()/3, gestoreArena.nave_utente.getY()+gestoreArena.nave_utente.getAltezza(),Impostazioni.PROIETTILE_SOTTO);
					break;	
				
				}
				primoSparo = false;
			}
			}
			try {
				Thread.sleep(Impostazioni.FPS_ARENA_LISTENER);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
