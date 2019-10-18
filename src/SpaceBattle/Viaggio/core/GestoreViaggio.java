package SpaceBattle.Viaggio.core;

import java.util.Random;

import javax.swing.JPanel;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.LogicaCondivisa.core.GestoreCollisioni;
import SpaceBattle.LogicaCondivisa.core.GestoreNemici;
import SpaceBattle.LogicaCondivisa.core.GestoreSuono;
import SpaceBattle.LogicaCondivisa.core.Nave;
import SpaceBattle.LogicaCondivisa.core.Nemico;
import SpaceBattle.LogicaCondivisa.core.PowerUp;
import SpaceBattle.Main.GameMain;

public class GestoreViaggio extends JPanel implements Runnable {

	private static final int MIN = 50;//limite minimo dal bordo
		
	public Nave giocatore;
	public GestoreNemici gestoreNemici;
	public GestoreCollisioni gestoreCollisioni;
	public GestoreSuono gestoreSuono;

	public PowerUp cuore;
	public PowerUp coin;
	public int punteggio;
	public int monete;
	
	Random pos;
	
	public int livelloCorrente;
	public int step;
	public boolean disegnaScritte;
	public int imgScrittaCorr;
	private int contScritte;

	Random imgNemici;
	
	private boolean terminato;
	private boolean primoBonus;
	
	public boolean secondoGiocatore;
	public Nave giocatore2;
	
	public GestoreViaggio(boolean x)
	{
		gestoreNemici = new GestoreNemici();
		gestoreCollisioni = new GestoreCollisioni();
		gestoreSuono = new GestoreSuono();
		
		pos = new Random();
		coin = new PowerUp(pos.nextInt(Impostazioni.WIDTH), Impostazioni.POS_COIN, Impostazioni.DIM_COIN, Impostazioni.DIM_COIN, 0);
		cuore = new PowerUp(pos.nextInt(Impostazioni.WIDTH), Impostazioni.POS_CUORE, Impostazioni.DIM_CUORE, Impostazioni.DIM_CUORE, 1);		
		
		punteggio = 0;
		monete = 0;
		
		imgNemici = new Random();
		terminato = false;
		primoBonus = true;

		disegnaScritte = true;
		imgScrittaCorr = 0;
		contScritte = 0;
		
		livelloCorrente = 1;
		step = 0;

		secondoGiocatore = x;		
		gestisciGiocatore();
	
		gestisciLivelli();
		
		Thread t1 = new Thread(this);
		t1.start();
		Thread t2 = new Thread(new ThreadMuoviNemici(this));
		t2.start();
		Thread t3 = new Thread(new ThreadSpara(this));
		t3.start();
	}
	
	public void gestisciGiocatore()
	{
		giocatore = new Nave(Impostazioni.WIDTH/2-(Impostazioni.DIM_GIOCATORE_P0/2), Impostazioni.HEIGHT-Impostazioni.DIM_GIOCATORE_P2 - MIN);
		
		if(secondoGiocatore)
		{
			giocatore2 = new Nave(Impostazioni.WIDTH/2-(Impostazioni.DIM_GIOCATORE_P0), Impostazioni.HEIGHT-Impostazioni.DIM_GIOCATORE_P2 - MIN);
			giocatore.setVita(250);
			giocatore2.setVita(250);
		}
		else
			giocatore.setVita(500);
	}
	
	public void gestisciLivelli()
	{
		switch(livelloCorrente)
		{
		case 1:
			aggiungiNemiciLiv1(step);
			break;
			
		case 2:
			aggiungiNemiciLiv2(step);
			break;
			
		case 3:
			aggiungiNemiciLiv3(step);
			break;
			
		case 4:
			livelloBonus();
			break;
			
		case 5:
			imgScrittaCorr = 7;
			break;
		}
	}
	
	public void settaPosizioniNemici()
	{
		for(int i=0; i<gestoreNemici.size(); i++)
		{
			gestoreNemici.get(i).setX(pos.nextInt(Impostazioni.WIDTH));
			gestoreNemici.get(i).setY(pos.nextInt(Impostazioni.HEIGHT/4));
			gestoreNemici.get(i).setImgCorrente(imgNemici.nextInt(4));
		}
	}
	
	public void settaNemiciBonus()
	{
		Random r = new Random();
		int n = r.nextInt(5)+1;
		for(int i=0; i<n; i++)
			gestoreNemici.generaNemici(1, 0, 0, r.nextInt(3));
		
		settaPosizioniNemici();
	}
	
	public void livelloBonus()
	{
		if(primoBonus)
		{
			if(!giocatore.isMorta() && secondoGiocatore)
				giocatore.setVita(250);
			
			if(secondoGiocatore)
				giocatore2.setVita(250);
				
			else if(!giocatore.isMorta())
				giocatore.setVita(500);
			
			primoBonus = false;
		}
		if(gestoreNemici.nemici.isEmpty())
			settaNemiciBonus();

	}
	
	public void aggiungiNemiciLiv1(int step)
	{
		switch(step)
		{
		case 0:
			gestoreNemici.generaNemici(3, 0, 0, 0);
			settaPosizioniNemici();
			break;
			
		case 1:
			gestoreNemici.generaNemici(1, 0, 0, 1);
			gestoreNemici.generaNemici(2, 0, 0, 0);

			settaPosizioniNemici();
			break;
			
		case 2:
			gestoreNemici.generaNemici(1, 0, 0, 2);
			gestoreNemici.generaNemici(1, 0, 0, 1);
			gestoreNemici.generaNemici(2, 0, 0, 0);

			settaPosizioniNemici();
			break;
		}
	}
	
	public void aggiungiNemiciLiv2(int step)
	{
		switch(step)
		{
		case 0:
			gestoreNemici.generaNemici(3, 0, 0, 1);
			settaPosizioniNemici();
			break;
			
		case 1:
			gestoreNemici.generaNemici(1, 0, 0, 2);
			gestoreNemici.generaNemici(2, 0, 0, 1);

			settaPosizioniNemici();
			break;
			
		case 2:
			gestoreNemici.generaNemici(1, 0, 0, 3);
			gestoreNemici.generaNemici(1, 0, 0, 2);

			settaPosizioniNemici();
			break;
		}
	}
	
	public void aggiungiNemiciLiv3(int step)
	{
		switch(step)
		{
		case 0:
			gestoreNemici.generaNemici(3, 0, 0, 2);
			settaPosizioniNemici();
			break;
			
		case 1:
			gestoreNemici.generaNemici(1, 0, 0, 3);
			gestoreNemici.generaNemici(2, 0, 0, 2);

			settaPosizioniNemici();
			break;
			
		case 2:
			gestoreNemici.generaNemici(1, 0, 0, 4);

			settaPosizioniNemici();
			break;
		}
	}
	
	public void settaGiocatore()
	{
		giocatore.setColpita(false);
		
		if(GameMain.gestoreNegozio.naveSelezionata == 0 || GameMain.gestoreNegozio.naveSelezionata == 3)
		{
			giocatore.setCodice(0);
			giocatore.setLunghezza(Impostazioni.DIM_GIOCATORE_P0);
			giocatore.setAltezza(Impostazioni.DIM_GIOCATORE_P0);
		}
		
		else if(GameMain.gestoreNegozio.naveSelezionata == 1 || GameMain.gestoreNegozio.naveSelezionata == 4)
		{
			giocatore.setCodice(2);
			giocatore.setLunghezza(Impostazioni.DIM_GIOCATORE_P1);
			giocatore.setAltezza(Impostazioni.DIM_GIOCATORE_P1);
		}
		
		else if(GameMain.gestoreNegozio.naveSelezionata == 2 || GameMain.gestoreNegozio.naveSelezionata == 5)
		{
			giocatore.setCodice(4);
			giocatore.setLunghezza(Impostazioni.DIM_GIOCATORE_P2);
			giocatore.setAltezza(Impostazioni.DIM_GIOCATORE_P2);
		}	
		
		if(secondoGiocatore)
		{
			giocatore2.setColpita(false);
			giocatore2.setCodice(0);
			giocatore2.setLunghezza(Impostazioni.DIM_GIOCATORE_P0);
			giocatore2.setAltezza(Impostazioni.DIM_GIOCATORE_P0);
		}
	}
	
	public void settaNemici()
	{
		for(int i=0; i<gestoreNemici.size(); i++)
		{
			gestoreNemici.get(i).setColpita(false);
			switch(gestoreNemici.get(i).getCodice())
			{
			case 0:
				gestoreNemici.get(i).setLunghezza(Impostazioni.DIM_NEMICO_P0);
				gestoreNemici.get(i).setAltezza(Impostazioni.DIM_NEMICO_P0);
				break;
				
			case 1:
				gestoreNemici.get(i).setLunghezza(Impostazioni.DIM_NEMICO_P1);
				gestoreNemici.get(i).setAltezza(Impostazioni.DIM_NEMICO_P1);
				break;
				
			case 2:
				gestoreNemici.get(i).setLunghezza(Impostazioni.DIM_NEMICO_P2);
				gestoreNemici.get(i).setAltezza(Impostazioni.DIM_NEMICO_P2);
				break;
				
			case 3:
				gestoreNemici.get(i).setLunghezza(Impostazioni.DIM_NEMICO_P3);
				gestoreNemici.get(i).setAltezza(Impostazioni.DIM_NEMICO_P3);
				break;
				
			case 4:
				gestoreNemici.get(i).setLunghezza(Impostazioni.DIM_NEMICO_P4);
				gestoreNemici.get(i).setAltezza(Impostazioni.DIM_NEMICO_P4);
				break;
			}
		}
	}
	
	public void aggiornaProiettiliNavi()
	{
		giocatore.aggiornaProiettiliViaggio();
		if(secondoGiocatore)
			giocatore2.aggiornaProiettiliViaggio();
		
		for(int i=0; i<gestoreNemici.size(); i++)
			gestoreNemici.get(i).aggiornaProiettiliViaggio();		
	}
	
	public void ricaricaProiettili()
	{
		giocatore.gestoreProiettili.ricaricaProiettili();
		
		if(secondoGiocatore)
			giocatore2.gestoreProiettili.ricaricaProiettili();
		
		for(int i=0; i<gestoreNemici.size(); i++)
			gestoreNemici.get(i).gestoreProiettili.ricaricaProiettili();		
	}
	
	public void verificaCollisioni()
	{
		if(!giocatore.isMorta())
		{
			if(giocatore.getVita() <= Impostazioni.VITA_TERMINATA)
			{
				if(giocatore.isPrimaEsplosione())
				{
					gestoreSuono.avviaEsplosione();
					giocatore.setPrimaEsplosione(false);
				}
				
				if(giocatore.getContEsplosioni() >= 30)
				{
					giocatore.setContEsplosioni(0);
					giocatore.setMorta(true);
				}
			}
				
			if(gestoreCollisioni.collisioneOggetti(coin, giocatore) && !coin.preso)
			{
				gestoreSuono.avviaRisorse();
				coin.preso = true;
				monete += 10;
			}
			
			if(gestoreCollisioni.collisioneOggetti(cuore, giocatore) && !cuore.preso)
			{
				gestoreSuono.avviaRisorse();
				cuore.preso = true;
				giocatore.setVita(giocatore.getVita()+10);
			}
		}
		
		if(secondoGiocatore)
		{	
			if(giocatore2.getVita() <= Impostazioni.VITA_TERMINATA)
			{
				if(giocatore2.isPrimaEsplosione())
				{
					gestoreSuono.avviaEsplosione();
					giocatore2.setPrimaEsplosione(false);
				}
				
				if(giocatore2.getContEsplosioni() >= 30)
				{
					giocatore2.setContEsplosioni(0);
					secondoGiocatore = false;
				}
			}
			
			if(gestoreCollisioni.collisioneOggetti(coin, giocatore2) && !coin.preso)
			{
				gestoreSuono.avviaRisorse();
				coin.preso = true;
				monete += 10;
			}
			
			if(gestoreCollisioni.collisioneOggetti(cuore, giocatore2) && !cuore.preso)
			{
				gestoreSuono.avviaRisorse();
				cuore.preso = true;
				giocatore.setVita(giocatore.getVita()+10);
			}
		}
		
		for(int i=0; i<gestoreNemici.size(); i++)
		{
			if(giocatore.getVita() > Impostazioni.VITA_TERMINATA)
			{
				gestoreCollisioni.gestisciCollisioneProViaggio(giocatore, gestoreNemici.get(i));
				gestoreCollisioni.gestisciCollisioneProViaggio(gestoreNemici.get(i), giocatore);
				if(gestoreCollisioni.collisioneOggetti(giocatore, gestoreNemici.get(i)))
				{
					giocatore.setVita(Impostazioni.VITA_TERMINATA);
					gestoreNemici.get(i).setVita(Impostazioni.VITA_TERMINATA);
				}
			}
			
			if(secondoGiocatore)
			{
				gestoreCollisioni.gestisciCollisioneProViaggio(giocatore2, gestoreNemici.get(i));
				gestoreCollisioni.gestisciCollisioneProViaggio(gestoreNemici.get(i), giocatore2);
				if(gestoreCollisioni.collisioneOggetti(giocatore2, gestoreNemici.get(i)))
				{
					giocatore2.setVita(Impostazioni.VITA_TERMINATA);
					gestoreNemici.get(i).setVita(Impostazioni.VITA_TERMINATA);
				}
			}
			
			if(gestoreNemici.get(i).getVita() <= Impostazioni.VITA_TERMINATA)	
			{
				if(gestoreNemici.get(i).isPrimaEsplosione())
				{
					gestoreSuono.avviaEsplosione();
					gestoreNemici.get(i).setPrimaEsplosione(false);
				}
				
				if(gestoreNemici.get(i).pro().size()==0)
				{
					if(gestoreNemici.get(i).getContEsplosioni() >= 30)
					{
						if(gestoreNemici.get(i).getCodice() > 0)//genero altre due navicelle piu piccole e meno potenti
						{
							int xNemicoUcciso = gestoreNemici.get(i).getX(), yNemicoUcciso = gestoreNemici.get(i).getY();
							int codNemicoUcciso = gestoreNemici.get(i).getCodice();
							int imgNemicoUcciso = gestoreNemici.get(i).getImgCorrente();
							
							gestoreNemici.add( new Nemico(xNemicoUcciso - 30, yNemicoUcciso + 20, codNemicoUcciso-1));
							gestoreNemici.get(gestoreNemici.size()-1).setImgCorrente(imgNemicoUcciso);
							gestoreNemici.add( new Nemico(xNemicoUcciso + gestoreNemici.get(i).getLunghezza() + 30, yNemicoUcciso + 20, codNemicoUcciso-1));
							gestoreNemici.get(gestoreNemici.size()-1).setImgCorrente(imgNemicoUcciso);	
						}
						gestoreNemici.get(i).setContEsplosioni(0);
						punteggio += gestoreNemici.get(i).getDanno();
						gestoreNemici.remove(i); 
					}
				}
			}
		}
	}
	
	public void seguiNave(Nave preda, Nave cacciatore, int indexCacciatore)
	{
		boolean collisione = false;
		
		if(cacciatore.getVita() > Impostazioni.VITA_TERMINATA)
		{
			if(preda.getX() + preda.getLunghezza()/2 < cacciatore.getX())
			{
				for(int j=0; j<gestoreNemici.size() && !collisione; j++)
				{
					if(indexCacciatore != j && gestoreCollisioni.collisioneOggettiSx(cacciatore, gestoreNemici.get(j)))
						collisione = true;
				}
				if(!collisione)
					cacciatore.muoviSx(Impostazioni.MIN);
					
				collisione = false;
			}
			 
			if(preda.getX() > cacciatore.getX() + (cacciatore.getLunghezza()/2))
			{
				for(int j=0; j<gestoreNemici.size() && !collisione; j++)
				{
					if(indexCacciatore!=j && gestoreCollisioni.collisioneOggettiDx(cacciatore, gestoreNemici.get(j)))
						collisione = true;
				}
				if(!collisione)
					cacciatore.muoviDx(Impostazioni.WIDTH - cacciatore.getLunghezza()-Impostazioni.MIN);	
					
				collisione = false;
			}
			
		}
	}
	
	public void verificaLivello()
	{
		if(gestoreNemici.nemici.isEmpty())
		{
			if(livelloCorrente < 4)
			{
				if(step>=3)
				{
					livelloCorrente++;
					step = 0;
					disegnaScritte = true;
				}
				
				else
					step++;
			}
			
			else
			{
				livelloCorrente++;
				disegnaScritte = true;
			}
			
			gestisciLivelli();
		}
	}
	
	public void verificaFinePartita()
	{
		if(!terminato && giocatore.isMorta() && !secondoGiocatore)
		{
			terminato = true;
			Integer moneteCorr = Integer.parseInt(GameMain.gestoreNegozio.ottieniCoin());
			Integer moneteTot = moneteCorr + monete;
			GameMain.gestoreNegozio.modificaCoin(""+moneteTot);	
			GameMain.gestoreClassifica.modificaMigliorPunteggioViaggio(punteggio);
		}
	}
	
	@Override
	public void run() {

		int contCoin = 0;
		int contCuore = 0;
		
		while(true) 
		{
			if(disegnaScritte || (giocatore.isMorta() && !secondoGiocatore))
			{
				if(!secondoGiocatore && giocatore.isMorta())
				{
					disegnaScritte = true;
					if(livelloCorrente < 4)
						imgScrittaCorr = 6;//game over
					
					else
						imgScrittaCorr = 7;//hai vinto!
				}
				
				else if(livelloCorrente == 5)//ci arrivo solo nel caso in cui vado avanti premendo N
					imgScrittaCorr = 7;//hai vinto!
				
				else
				{
					contScritte ++;
				
					imgScrittaCorr = livelloCorrente-1;
					
					if(contScritte > 20)//pronto?
						imgScrittaCorr = 4;
					
					if(contScritte > 40)//vai!
						imgScrittaCorr = 5;		
					
					if(contScritte > 60)
					{
						contScritte = 0;
						disegnaScritte = false;
					}
				}					
			}
			
			else if(!disegnaScritte && (!giocatore.isMorta() || secondoGiocatore))
			{
				settaGiocatore();
				settaNemici();
				verificaCollisioni();
					
				ricaricaProiettili();
				aggiornaProiettiliNavi();
			
				if(primoBonus)
					verificaLivello();
				
				else
					livelloBonus();
				
				verificaFinePartita();

				contCoin ++;
				contCuore ++;
			
				if(contCoin >= 600)
				{
					coin.preso = false;
					contCoin = 0;
					coin.setX(pos.nextInt(Impostazioni.WIDTH - Impostazioni.MIN) + Impostazioni.MIN);
					coin.setY(Impostazioni.POS_COIN);
				}
			
				if(contCoin >= 100 && contCoin <= 500)
					coin.setY(coin.getY()+10);
		
				if(contCuore >= 500)
				{
					cuore.preso = false;
					contCuore = 0;
					cuore.setX(pos.nextInt(Impostazioni.WIDTH - Impostazioni.MIN) + Impostazioni.MIN);
					cuore.setY(Impostazioni.POS_CUORE);
				}
				
				if(contCuore >= 10 && contCuore <= 400)
					cuore.setY(cuore.getY()+10);
				
				coin.imgPowerUp++;
				cuore.imgPowerUp++;
			}
			
			for(int i=0; i<gestoreNemici.size(); i++)
			{
				if(giocatore.getVita() > Impostazioni.VITA_TERMINATA)
					seguiNave(giocatore, gestoreNemici.get(i), i);
				
				if(secondoGiocatore && giocatore.isMorta())
					seguiNave(giocatore2, gestoreNemici.get(i), i);
			}
			
			try {
				Thread.sleep(Impostazioni.FPS_GESTORE_VIAGGIO);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
