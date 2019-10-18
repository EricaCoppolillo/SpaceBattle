package SpaceBattle.LogicaCondivisa.core;
import java.util.ArrayList;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Main.GameMain;
import SpaceBattle.Arena.core.Matrice;

public class Nave extends Oggetto implements Runnable 
{
	public GestoreProiettili gestoreProiettili = new GestoreProiettili();
	private int direzione;
	private int codice;
	private int vita;
	private int velocita;
	private int potenza;
	private int danno;
	private int imgCorrente;
	private int imgEspl;
	private boolean colpita;
	private boolean morta;
	private int contEsplosioni;
	private boolean primaEsplosione = true;
	private boolean fermo = true;
	private int contatore = 0; 
	
	public Nave() 
	{
		super();
		imgEspl = 0;
		colpita = false;
		morta = false;
		
		direzione = 0;
		codice = 0;
		vita = GameMain.archivio.vite.get(codice);
		velocita = GameMain.archivio.velocita.get(codice);
		danno = GameMain.archivio.danno.get(codice);
		potenza = 45;
		
		this.contEsplosioni = 0;
		Thread t = new Thread(this);
		t.start();
	}
	
	public Nave(int x, int y) 
	{
		super(x, y, 0, 0);
		imgEspl = 0;
		colpita = false;
		morta = false;

		direzione = 0;
		codice = 0;
		vita = GameMain.archivio.vite.get(codice);
		velocita = GameMain.archivio.velocita.get(codice);
		danno = GameMain.archivio.danno.get(codice);
		potenza = 45;
		
		this.contEsplosioni = 0;
		Thread t = new Thread(this);
		t.start();
	}
	
	public Nave(int x, int y, int cod) 
	{
		super(x, y, 0, 0);
		imgEspl = 0;
		colpita = false;
		morta = false;

		direzione = 0;
		codice = cod;
		vita = GameMain.archivio.vite.get(codice);
		velocita = GameMain.archivio.velocita.get(codice);
		danno = GameMain.archivio.danno.get(codice);
		potenza = 45;
		
		this.contEsplosioni = 0;
		Thread t = new Thread(this);
		t.start();
	}

	public int getDirezione() {
		return direzione;
	}

	public void setDirezione(int direzione) {
		this.direzione = direzione;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public int getVita() {
		return vita;
	}

	public void setVita(int vita) {
		this.vita = vita;
	}

	public int getVelocita() {
		return velocita;
	}

	public void setVelocita(int velocita) {
		this.velocita = velocita;
	}

	public int getPotenza() {
		return potenza;
	}

	public void setPotenza(int potenza) {
		this.potenza = potenza;
	}

	public int getDanno() {
		return danno;
	}

	public void setDanno(int danno) {
		this.danno = danno;
	}

	public int getImgCorrente() {
		return imgCorrente;
	}

	public void setImgCorrente(int imgCorrente) {
		this.imgCorrente = imgCorrente;
	}

	public int getImgEspl() {
		return imgEspl;
	}

	public void setImgEspl(int imgEspl) {
		this.imgEspl = imgEspl;
	}

	public boolean isColpita() {
		return colpita;
	}

	public void setColpita(boolean colpita) {
		this.colpita = colpita;
	}

	public boolean isMorta() {
		return morta;
	}

	public void setMorta(boolean morta) {
		this.morta = morta;
	}

	public int getContEsplosioni() {
		return contEsplosioni;
	}

	public void setContEsplosioni(int contEsplosioni) {
		this.contEsplosioni = contEsplosioni;
	}

	public boolean isPrimaEsplosione() {
		return primaEsplosione;
	}

	public void setPrimaEsplosione(boolean primaEsplosione) {
		this.primaEsplosione = primaEsplosione;
	}

	public boolean isFermo() {
		return fermo;
	}

	public void setFermo(boolean fermo) {
		this.fermo = fermo;
	}

	public int getContatore() {
		return contatore;
	}

	public void setContatore(int contatore) {
		this.contatore = contatore;
	}

	public int path(Cella iniziale, Cella finale, Matrice matrix)
	{
		int cont = 0;
		ArrayList<Cella>visitati = new ArrayList<Cella>();
		ArrayList<Cella>daVisitare = new ArrayList<Cella>();
		
		daVisitare.add(new Cella(iniziale));
		visitati.add(new Cella(iniziale));
		
		
		while(!daVisitare.isEmpty())
		{
			Cella momentanea = new Cella(daVisitare.get(0)); 
			daVisitare.remove(0);
			
			if(momentanea.colonna+1 < matrix.colonne) 
			{
				if(matrix.get(momentanea.riga, momentanea.colonna+1).navigabile == true) 
				{
					Cella temp = new Cella(momentanea.riga, momentanea.colonna+1, momentanea.direzione);
					if(cont == 0)
					{
						temp.direzione = Impostazioni.DESTRA;
					}
					if(!visitati.contains(temp)) 
					{
						if(temp.riga == finale.riga && temp.colonna == finale.colonna) 
						{
							return temp.direzione;
						}
						visitati.add(new Cella(temp));
						daVisitare.add(new Cella(temp));
					}
				}
			}
			if(momentanea.colonna-1 >= 0)
			{
				if(matrix.get(momentanea.riga, momentanea.colonna-1).navigabile == true) 
				{
					Cella temp = new Cella(momentanea.riga, momentanea.colonna-1, momentanea.direzione);
					if(cont == 0)
					{
						temp.direzione = Impostazioni.SINISTRA;
					}
					if(!visitati.contains(temp)) 
					{
						if(temp.riga == finale.riga && temp.colonna == finale.colonna)
						{
							return temp.direzione;
						}
						visitati.add(new Cella(temp));
						daVisitare.add(new Cella(temp));
					}
				}
			}
			if(momentanea.riga-1 >= 0)
			{
				if(matrix.get(momentanea.riga-1, momentanea.colonna).navigabile == true) 
				{
					Cella temp = new Cella(momentanea.riga-1, momentanea.colonna, momentanea.direzione);
					if(cont == 0)
					{
						temp.direzione = Impostazioni.SOPRA;
					}
					if(!visitati.contains(temp)) 
					{
						if(temp.riga == finale.riga && temp.colonna == finale.colonna)
						{
							return temp.direzione;
						}
						visitati.add(new Cella(temp));
						daVisitare.add(new Cella(temp));
					}
				}
			}
			if(momentanea.riga+1 < matrix.righe)
			{
				if(matrix.get(momentanea.riga+1, momentanea.colonna).navigabile == true) 
				{
					Cella temp = new Cella(momentanea.riga+1, momentanea.colonna, momentanea.direzione);
					if(cont == 0)
					{
						temp.direzione = Impostazioni.SOTTO;
					}
					if(!visitati.contains(temp)) 
					{
						if(temp.riga == finale.riga && temp.colonna == finale.colonna)
						{
							return temp.direzione;
						}
						visitati.add(new Cella(temp));
						daVisitare.add(new Cella(temp));
					}
				}
			}
			
			cont++;
		}
		return -1;
	}
	
	
	public void muoviSx(int min)
	{
		if(x > min )
			x = x - velocita;
		else
		{
			if(direzione<3)
				direzione++;
			else
				direzione=0;
		}
	}
	
	public void muoviDx(int max)
	{
		if(x < max)
			x = x + velocita;
		else
		{
			if(direzione<3)
				direzione++;
			else
				direzione=0;
		}
	}
	
	public void muoviSu(int min)
	{
		if(y > min)
			y = y - velocita;
		else
		{
			if(direzione<3)
				direzione++;
			else
				direzione=0;
		}
	}
	
	public void muoviGiu(int max)
	{
		if(y < max)
			y = y + velocita;
		else
		{
			if(direzione<3)
				direzione++;
			else
				direzione=0;
		}
	}
	
	public void setLunghezza(int l)
	{
		lunghezza = l;
	}
	
	public void setAltezza(int a)
	{
		altezza = a;
	}
	
	public void aggiornaProiettiliArena()
	{
		for(int i = 0; i<pro().size(); i++)
		{
			if(pro().get(i)!=null)
			{
				if(pro().get(i).getDirezione() == Impostazioni.PROIETTILE_DESTRA)
				{
					pro().get(i).x+=this.potenza;
				}
				else if(pro().get(i).getDirezione() == Impostazioni.PROIETTILE_SINISTRA)
				{
					pro().get(i).x-=this.potenza;
				}
				else if(pro().get(i).getDirezione() == Impostazioni.PROIETTILE_SOPRA)
				{
					pro().get(i).y-=this.potenza;
				}
				else if(pro().get(i).getDirezione() == Impostazioni.PROIETTILE_SOTTO)
				{
					pro().get(i).y+=this.potenza;
				}
			}				
		}
	}
	
	public void aggiornaProiettiliViaggio()
	{
		for(int i = 0; i<pro().size(); i++)
		{
			if(pro().get(i)!=null) 
			{
				switch(pro().get(i).getDirezione())
				{
				case 0:
					pro().get(i).sparaSx(potenza, Impostazioni.MIN-100);
					break;
					
				case 1:
					pro().get(i).sparaDx(potenza, Impostazioni.WIDTH);
					break;
				
				case 2:
					pro().get(i).sparaSu(potenza, -Impostazioni.MIN);
					break;
				
				case 3:
					pro().get(i).sparaGiu(potenza, Impostazioni.HEIGHT);
					break;
				
				case 4:
					pro().get(i).sparaSxSu(potenza, Impostazioni.MIN, 0);
					break;
				
				case 5:
					pro().get(i).sparaSxGiu(potenza, -Impostazioni.MIN-100, Impostazioni.HEIGHT);
					break;
				
				case 6:
					pro().get(i).sparaDxSu(potenza, Impostazioni.WIDTH, 0);
					break;
				
				case 7:
					pro().get(i).sparaDxGiu(potenza, Impostazioni.WIDTH, Impostazioni.HEIGHT);
					break;	
				}
			}
		}
	}

	public ArrayList<Proiettile> pro()
	{
		return gestoreProiettili.pro;
	}
	
	public Cella cella_corrente(int dim_cella)
	{
		Cella cella = new Cella();
			
		cella.colonna = this.x/dim_cella;
		cella.riga = this.y/dim_cella;
			
		return cella;
	}
	
	
	public void upgrade(int codice)
	{
		this.codice = codice;
		vita= GameMain.archivio.vite.get(codice);
		velocita= GameMain.archivio.velocita.get(codice);
		danno= GameMain.archivio.danno.get(codice);
	}

	@Override
	public void run() {
		
		while(true)
		{
			if(vita<=Impostazioni.VITA_TERMINATA)
				imgEspl++;
			
			try {
				Thread.sleep(Impostazioni.FPS_NAVE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
