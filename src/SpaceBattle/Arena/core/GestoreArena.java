package SpaceBattle.Arena.core;

import java.util.ArrayList;
import java.util.Random;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.LogicaCondivisa.core.Cella;
import SpaceBattle.LogicaCondivisa.core.GestoreCollisioni;
import SpaceBattle.LogicaCondivisa.core.GestoreNemici;
import SpaceBattle.LogicaCondivisa.core.Nave;
import SpaceBattle.LogicaCondivisa.core.Nemico;
import SpaceBattle.LogicaCondivisa.core.PowerUp;
import SpaceBattle.LogicaCondivisa.core.Proiettile;
import SpaceBattle.Main.GameMain;

public class GestoreArena implements Runnable {
	
	Random r = new Random();
	public int valoreMax = 24;
	public int divisore = 2;
	public int velocitaNemiciArena = 5;
	public boolean spawnIniziale;
	public boolean partitaFinita = false;
	public int contatore = 0;
	public int cont = 0;
	public int punteggio = 0;
	public int coin = 0;
	public ArrayList<PowerUp> powerUps;
	public GestoreNemici gestoreNemici;
	public GestoreCollisioni collisioni;
	public Nave nave_utente;
	public Matrice matrix;
	public boolean difficoltaIniziale = true;
	public int tipologia;
	
	public GestoreArena(Matrice matrix, boolean bool) 
	{
		powerUps = new ArrayList<PowerUp>();
		gestoreNemici = new GestoreNemici();
		collisioni = new GestoreCollisioni();
		this.matrix = matrix;
		this.spawnIniziale = bool;
		if(this.spawnIniziale == true)
			this.tipologia = 1;
		else 
			this.tipologia = 0;
		settaNaveUtenteArena();
		spawnaNemici();
		new Thread(this).start();
	}
	
	
	public void settaNaveUtenteArena()
	{
		if(!spawnIniziale)
			nave_utente = new Nave(Impostazioni.SPAWN_NAVE_UTENTE_ARENA_X, Impostazioni.SPAWN_NAVE_UTENTE_ARENA_Y); // questi numeri scompariranno
		else
		{
			nave_utente = new Nave(matrix.trovaPrimoPavimentoOBuco().colonna*Impostazioni.DIM_BLOCCO_ARENA, matrix.trovaPrimoPavimentoOBuco().riga*Impostazioni.DIM_BLOCCO_ARENA );
		}
		nave_utente.setDanno(Impostazioni.DANNO_NAVE_UTENTE_ARENA);
		nave_utente.setVelocita(Impostazioni.VELOCITA_NAVE_UTENTE_ARENA); 
		nave_utente.setAltezza(Impostazioni.DIM_BLOCCO_ARENA);
		nave_utente.setLunghezza(Impostazioni.DIM_BLOCCO_ARENA);
		nave_utente.setVita(Impostazioni.VITA_NAVE_UTENTE_ARENA);
		nave_utente.setPotenza(Impostazioni.POTENZA_NAVE_UTENTE_ARENA);
	}

	public void spawnaNemici() {

		ArrayList<Cella> liberi = matrix.calcolaLiberi();
		if(spawnIniziale)
			liberi.remove(new Cella(matrix.trovaPrimoPavimentoOBuco().riga, matrix.trovaPrimoPavimentoOBuco().colonna));

		for (int i = gestoreNemici.size(); i < Impostazioni.NUMERO_NEMICI_ARENA; i++) {
			if (!liberi.isEmpty()) {
				Cella c = liberi.get(r.nextInt(liberi.size()));
				liberi.remove(c);
				gestoreNemici.add(new Nemico(c.colonna * Impostazioni.DIM_BLOCCO_ARENA, c.riga * Impostazioni.DIM_BLOCCO_ARENA, r.nextInt(Impostazioni.TIPOLOGIE_NAVI_ARENA)));
				gestoreNemici.get(i).setVelocita(velocitaNemiciArena);
				gestoreNemici.get(i).setAltezza(Impostazioni.DIM_BLOCCO_ARENA);
				gestoreNemici.get(i).setLunghezza(Impostazioni.DIM_BLOCCO_ARENA);
				gestoreNemici.get(i).setDanno(Impostazioni.DANNO_NEMICI_ARENA);
				gestoreNemici.get(i).setVita(Impostazioni.VITA_NEMICI_ARENA);
				gestoreNemici.get(i).setPotenza(Impostazioni.POTENZA_NEMICI_ARENA);
				gestoreNemici.get(i).setFermo(true);
				
			}

		}
		spawnIniziale = false;
	}

	public void utenteSuicida()
	{
		for(int i = 0; i<gestoreNemici.size(); i++)
			if(collisioni.collisioneOggetti(nave_utente, gestoreNemici.get(i)))
			{
				gestoreNemici.get(i).setVita(Impostazioni.VITA_TERMINATA);
				nave_utente.setVita(Impostazioni.VITA_TERMINATA); 
			}
	}

	public void nemiciSparanti() {
		for (int i = 0; i < gestoreNemici.size(); i++) {
			if(nave_utente.getVita() > Impostazioni.VITA_TERMINATA)
			{
			if (gestoreNemici.get(i).getVita() > Impostazioni.VITA_TERMINATA) 
			{
				if (gestoreNemici.get(i).getDirezione() == Impostazioni.DESTRA) 
				{
					if (gestoreNemici.get(i).cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).riga == nave_utente.cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).riga) 
					{
						gestoreNemici.get(i).pro()
								.add(new Proiettile(gestoreNemici.get(i).getX() + Impostazioni.DIMPROIETTILI,
										gestoreNemici.get(i).getY() + Impostazioni.DIMPROIETTILI, Impostazioni.PROIETTILE_DESTRA));
					}
				} 
				else if (gestoreNemici.get(i).getDirezione() == Impostazioni.SOPRA) 
				{
					if (gestoreNemici.get(i).cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).colonna == nave_utente.cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).colonna) 
					{
						gestoreNemici.get(i).pro()
								.add(new Proiettile(gestoreNemici.get(i).getX() + Impostazioni.DIMPROIETTILI,
										gestoreNemici.get(i).getY() + Impostazioni.DIMPROIETTILI, Impostazioni.PROIETTILE_SOPRA));
					}
				} 
				else if (gestoreNemici.get(i).getDirezione() == Impostazioni.SINISTRA) 
				{
					if (gestoreNemici.get(i).cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).riga == nave_utente
							.cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).riga) {
						gestoreNemici.get(i).pro()
								.add(new Proiettile(gestoreNemici.get(i).getX() + Impostazioni.DIMPROIETTILI,
										gestoreNemici.get(i).getY() + Impostazioni.DIMPROIETTILI, Impostazioni.PROIETTILE_SINISTRA));
					}
				} 
				else if (gestoreNemici.get(i).getDirezione() == Impostazioni.SOTTO) 
				{
					if (gestoreNemici.get(i).cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).colonna == nave_utente
							.cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).colonna) {
						gestoreNemici.get(i).pro()
								.add(new Proiettile(gestoreNemici.get(i).getX() + Impostazioni.DIMPROIETTILI,
										gestoreNemici.get(i).getY() + Impostazioni.DIMPROIETTILI, Impostazioni.PROIETTILE_SOTTO));
					}
				}
			}
		}
		}
	}

	public void aggiornaProiettiliNemici() {
		for (int i = 0; i < gestoreNemici.size(); i++) {
			gestoreNemici.get(i).aggiornaProiettiliArena();
		}
	}

	public void controlla_Arena() {
		for (int i = 0; i < nave_utente.pro().size(); i++) 
		{
			if(nave_utente.pro().get(i)!=null)
			{
				if (nave_utente.getDirezione() == Impostazioni.DESTRA
						&& collisioni.proiettile_blocco_sinistra_destra(nave_utente.pro().get(i), matrix)) {
					nave_utente.pro().remove(i);
				} else if (nave_utente.getDirezione() == Impostazioni.SOPRA
						&& collisioni.proiettile_blocco_sotto_sopra(nave_utente.pro().get(i), matrix)) {
					nave_utente.pro().remove(i);
				} else if (nave_utente.getDirezione() == Impostazioni.SINISTRA
						&& collisioni.proiettile_blocco_destra_sinistra(nave_utente.pro().get(i), matrix)) {
					nave_utente.pro().remove(i);
				} else if (nave_utente.getDirezione() == Impostazioni.SOTTO
						&& collisioni.proiettile_blocco_sopra_sotto(nave_utente.pro().get(i), matrix)) {
					nave_utente.pro().remove(i);
				}
			}

		}
	}

	public void verificaCollisioniArena() 
	{
		controllaCollisioniPowerUp();
		utenteSparaNemici();
		nemiciSparanoUtente();
		nemiciSparanoNemici();
		utenteSuicida();
		rimuoviProiettiliNemici();
		rimuoviProiettiliUtente();		
	}

	public void rimuoviProiettiliNemici()
	{
		for (int i = 0; i < gestoreNemici.size(); i++) 
		{
			for (int j = 0; j < gestoreNemici.get(i).pro().size(); j++) 
			{
				if ( gestoreNemici.get(i).pro().get(j)!=null ) 
				{
					if (gestoreNemici.get(i).pro().get(j).getX() <= 0 
							|| gestoreNemici.get(i).pro().get(j).getX() >= Impostazioni.WIDTH
							|| gestoreNemici.get(i).pro().get(j).getY() <= 0
							|| gestoreNemici.get(i).pro().get(j).getY() >= Impostazioni.HEIGHT - (Impostazioni.DIM_BLOCCO_ARENA * 2))
						gestoreNemici.get(i).pro().remove(j);
					else if (collisioni.proiettile_blocco_destra_sinistra(gestoreNemici.get(i).pro().get(j), matrix)
							|| collisioni.proiettile_blocco_sinistra_destra(gestoreNemici.get(i).pro().get(j), matrix)
							|| collisioni.proiettile_blocco_sopra_sotto(gestoreNemici.get(i).pro().get(j), matrix)
							|| collisioni.proiettile_blocco_sotto_sopra(gestoreNemici.get(i).pro().get(j), matrix))
						gestoreNemici.get(i).pro().remove(j);
				}
			}
		}
	}
	
	public void rimuoviProiettiliUtente()
	{
		for (int j = 0; j < nave_utente.pro().size(); j++) 
		{
			if ( nave_utente.pro().get(j)!=null ) 
			{
				if (nave_utente.pro().get(j).getX() <= 0 
						|| nave_utente.pro().get(j).getX() >= Impostazioni.WIDTH
						|| nave_utente.pro().get(j).getY() <= 0
						|| nave_utente.pro().get(j).getY() >= Impostazioni.HEIGHT - (Impostazioni.DIM_BLOCCO_ARENA * 2))
					nave_utente.pro().remove(j);
				else if (collisioni.proiettile_blocco_destra_sinistra(nave_utente.pro().get(j), matrix)
						|| collisioni.proiettile_blocco_sinistra_destra(nave_utente.pro().get(j), matrix)
						|| collisioni.proiettile_blocco_sopra_sotto(nave_utente.pro().get(j), matrix)
						|| collisioni.proiettile_blocco_sotto_sopra(nave_utente.pro().get(j), matrix))
					nave_utente.pro().remove(j);
			}
		}
	}

	public void nemiciSparanoUtente() {
		for (int i = 0; i < gestoreNemici.size(); i++) {
			if (collisioni.gestisciCollisioneProArena(gestoreNemici.get(i),nave_utente)) {
				if(nave_utente.getVita() > Impostazioni.VITA_TERMINATA)
					nave_utente.setVita(nave_utente.getVita()-1);
			}
		}
	}

	public void nemiciSparanoNemici() {
		for (int i = 0; i < gestoreNemici.size(); i++)
			for (int j = 0; j < gestoreNemici.size(); j++)
				if (i != j) {
					collisioni.gestisciCollisioneProArena(gestoreNemici.get(i), gestoreNemici.get(j));
				}
	}
	
	public void aggiornaImmagineEsplosione()
	{
		if(nave_utente.getVita() == Impostazioni.VITA_TERMINATA)
		{
			nave_utente.setContEsplosioni(nave_utente.getContEsplosioni()+1);;
			if(nave_utente.getContEsplosioni() == Impostazioni.NESPLOSIONIARENA)
				nave_utente.setContEsplosioni(0);
		}
	}

	public void utenteSparaNemici() 
	{
		for (int i = 0; i < gestoreNemici.size(); i++) 
		{
			if (collisioni.gestisciCollisioneProArena(nave_utente, gestoreNemici.get(i))) 				
					gestoreNemici.get(i).setVita(gestoreNemici.get(i).getVita() - nave_utente.getDanno()); 

			if (gestoreNemici.get(i).pro().size() == 0) 
			{
				if (gestoreNemici.get(i).getContEsplosioni() > Impostazioni.NESPLOSIONIARENA) 
				{
					gestoreNemici.get(i).setContEsplosioni(0);
					
					int casuale  = r.nextInt(2);
					powerUps.add(new PowerUp(gestoreNemici.get(i).getX(), gestoreNemici.get(i).getY(), Impostazioni.DIM_POWERUP_ARENA, Impostazioni.DIM_POWERUP_ARENA,casuale));
						
					gestoreNemici.remove(i);
					punteggio++;

				}
			}
		}
	}


	public void controllaCollisioniPowerUp() {
		if(nave_utente.getVita() > Impostazioni.VITA_TERMINATA)
		{
			for (int i = 0; i < powerUps.size(); i++) 
			{
				if (collisioni.collisioneOggetti(powerUps.get(i), nave_utente)) 
				{
					if(powerUps.get(i).tipo == 0) 
						coin++;
					else 
						nave_utente.setVita(nave_utente.getVita()+1);
					powerUps.remove(i);
				}
			}
		}
	}

	

	public void aggiornaImmagineBonus() {
		for(int i = 0; i< powerUps.size(); i++)
		{
			powerUps.get(i).imgPowerUp++;
			if(powerUps.get(i).tipo == 0) //è un coin
			{
				powerUps.get(i).imgPowerUp %= Impostazioni.NUMERO_IMMAGINI_COINS_ARENA;
			}
			else
			{
				powerUps.get(i).imgPowerUp %= Impostazioni.NUMERO_IMMAGINI_CUORI_ARENA;
			}
		}

	}

	public void rinascitaNemici() {
		if (gestoreNemici.size() < Impostazioni.NUMERO_NEMICI_ARENA && nave_utente.getVita() > Impostazioni.VITA_TERMINATA )
			spawnaNemici();
	}

	public void calcolatoreMovimentoNemici() {
		for (int i = 0; i < gestoreNemici.size(); i++) {
			if (gestoreNemici.get(i).getContatore() == 0 && gestoreNemici.get(i).getVita() > Impostazioni.VITA_TERMINATA) 
			{
				Cella iniziale = new Cella(gestoreNemici.get(i).cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).riga,gestoreNemici.get(i).cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).colonna, null);
				Cella finale = new Cella(nave_utente.cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).riga, nave_utente.cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).colonna,null);
				
				gestoreNemici.get(i).setDirezione(gestoreNemici.get(i).path(iniziale, finale, matrix));
				
				if(gestoreNemici.get(i).getDirezione() == -1)
					gestoreNemici.get(i).setFermo(true);
				else 
					gestoreNemici.get(i).setFermo(false);
				
				if(!gestoreNemici.get(i).isFermo()) {
				if (gestoreNemici.get(i).cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).riga == nave_utente.cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).riga
						&& gestoreNemici.get(i).cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).colonna == nave_utente.cella_corrente(Impostazioni.DIM_BLOCCO_ARENA).colonna)
					gestoreNemici.get(i).setFermo(true);
				else
					gestoreNemici.get(i).setFermo(false);;
				}
			}
		}
	}

	public void incrementaContatore() {
		for (int i = 0; i < gestoreNemici.size(); i++)
			gestoreNemici.get(i).setContatore(gestoreNemici.get(i).getContatore()+1);
	}

	public void azzeraContatore() {
		for (int i = 0; i < gestoreNemici.size(); i++) {
			if (gestoreNemici.get(i).getContatore() == valoreMax) {
				gestoreNemici.get(i).setContatore(0);
			}
		}
	}

	public void muoviNemiciperStepMultiplo() {
		for (int i = 0; i < gestoreNemici.size(); i++) {
			if (gestoreNemici.get(i).getContatore() % divisore == 0) {
				if (gestoreNemici.get(i).getVita() > Impostazioni.VITA_TERMINATA)
						muoviNave(i);
			}
		}
	}

	public void muoviNave(int i) {
		if (gestoreNemici.get(i).isFermo() == false) {
			if (gestoreNemici.get(i).getDirezione() == Impostazioni.DESTRA) {
				gestoreNemici.get(i).setX(gestoreNemici.get(i).getX()+gestoreNemici.get(i).getVelocita());

			} else if (gestoreNemici.get(i).getDirezione() == Impostazioni.SINISTRA) {
				gestoreNemici.get(i).setX(gestoreNemici.get(i).getX()-gestoreNemici.get(i).getVelocita());

			} else if (gestoreNemici.get(i).getDirezione() == Impostazioni.SOPRA) {
				gestoreNemici.get(i).setY(gestoreNemici.get(i).getY()-gestoreNemici.get(i).getVelocita());

			} else if (gestoreNemici.get(i).getDirezione() == Impostazioni.SOTTO) {
				gestoreNemici.get(i).setY(gestoreNemici.get(i).getY()+gestoreNemici.get(i).getVelocita());
			}
		}
	}
	
	public void inserisciPunteggioAPartitaFinita()
	{
		if(partitaFinita == false && nave_utente.getVita() <= Impostazioni.VITA_TERMINATA )
		{
			partitaFinita = true;
			if(this.tipologia == 0) //se sono nella modalita sfida di arena
			{
		     	GameMain.gestoreClassifica.modificaMigliorPunteggioArena(this.punteggio);
				Integer moneteCorr = Integer.parseInt(GameMain.gestoreNegozio.ottieniCoin());
				Integer moneteTot = moneteCorr + coin;
				GameMain.gestoreNegozio.modificaCoin(""+moneteTot);
			}
		}
	}
	
	public void settaAFermo()
	{
		if(nave_utente.getVita() == Impostazioni.VITA_TERMINATA)
		{
			for(int i = 0; i<gestoreNemici.size(); i++)
				gestoreNemici.get(i).setFermo(true);
		}
		
	}
	
	public void aumentaDifficolta()
	{
		if(this.punteggio >= Impostazioni.PUNTEGGIO_CAMBIO_LIVELLO_ARENA && difficoltaIniziale)
		{
			this.difficoltaIniziale = false;
			this.valoreMax = 12;
			this.velocitaNemiciArena = velocitaNemiciArena*2;
			
			for(int i = 0; i<gestoreNemici.size(); i++)
			{
				gestoreNemici.get(i).setVita(Impostazioni.VITA_TERMINATA);
			}
			
		}
	}

	@Override
	public void run() {

		while (true) {

			calcolatoreMovimentoNemici();
			inserisciPunteggioAPartitaFinita();
			nave_utente.aggiornaProiettiliArena();;
			verificaCollisioniArena();
			controlla_Arena();
			aggiornaImmagineBonus();
			aumentaDifficolta();
			
			if(cont%Impostazioni.FATTORE_RIPRODUZIONE_ESPLOSIONI==0)
				aggiornaImmagineEsplosione();
			
			if (cont % Impostazioni.FATTORE_RIPRODUZIONE_SPARO_NEMICI == 0) 
				nemiciSparanti();
			
			if (cont % Impostazioni.FATTORE_AGGIORNAMENTO_PROIETTILI_NEMICI == 0) 
				aggiornaProiettiliNemici();
			
			rinascitaNemici();
			settaAFermo();

			cont++;
			
			muoviNemiciperStepMultiplo();
			incrementaContatore();

			azzeraContatore();
			try {
				Thread.sleep(Impostazioni.FPS_ARENA);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
