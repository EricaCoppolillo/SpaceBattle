package SpaceBattle.Arena.core;

import java.util.ArrayList;
import SpaceBattle.Config.Impostazioni;
import SpaceBattle.LogicaCondivisa.core.Cella;

public class Matrice 
{

	public final int righe = 16;
	public final int colonne = 32; 
	
	public Elemento matrice[][] = new Elemento[righe][colonne] ;
	public ArrayList<Cella>liberi = new ArrayList<Cella>();
	
	public Matrice() 
	{ 
		riempi_matrice();
		crea_punti_di_spawn();
		
	}
	
	//COSTRUTTORE DI MATRICE USATO PER LA VERSIONE ARENA PERSONALIZZATA
	public Matrice(int matrice[][])
	{
		for(int i = 0; i<righe; i++)
		{
			for(int j = 0; j<colonne; j++)
			{
				this.matrice[i][j] = new Elemento();
				
				this.matrice[i][j].numero = matrice[i][j];
				
				if(this.matrice[i][j].numero == Impostazioni.BLOCCO_TESCHIO || this.matrice[i][j].numero == Impostazioni.BLOCCO_ARENA_SFIDA || this.matrice[i][j].numero == Impostazioni.BLOCCO_FERRO
						|| this.matrice[i][j].numero == Impostazioni.BLOCCO_CELESTE || this.matrice[i][j].numero == Impostazioni.BLOCCO_GHIACCIO)
					{
						this.matrice[i][j].navigabile = false;
						this.matrice[i][j].blocco = new Blocco(j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA);
					}
			}
		}
	}
	
	public Cella trovaPrimoPavimentoOBuco()
	{
		Cella cella = new Cella();
		
		for(int i = 0; i<righe; i++)
		{
			for(int j = 0; j<colonne; j++)
			{
				if(matrice[i][j].numero!=Impostazioni.BLOCCO_ARENA_SFIDA && matrice[i][j].numero!=Impostazioni.BLOCCO_CELESTE && matrice[i][j].numero!=Impostazioni.BLOCCO_FERRO && matrice[i][j].numero!=Impostazioni.BLOCCO_GHIACCIO && matrice[i][j].numero!=Impostazioni.BLOCCO_TESCHIO)
				{
					cella.riga = i;
					cella.colonna = j;
					return cella;
				}
			}
		}
		
		
		return cella;
	}
	
	
	
	public Elemento get(int i, int j)
	{
			return matrice[i][j];
			
	}
		
	public void crea_punti_di_spawn()
	{
		matrice[1][1].numero = Impostazioni.BUCO_ARENA_SFIDA; 
		matrice[1][1].navigabile = true; 
		matrice[1][1].blocco = new Blocco(1*Impostazioni.DIM_BLOCCO_ARENA,1*Impostazioni.DIM_BLOCCO_ARENA);
		
		matrice[righe-2][1].numero = Impostazioni.BUCO_ARENA_SFIDA; 
		matrice[righe-2][1].navigabile = true; 
		matrice[righe-2][1].blocco = new Blocco(1*Impostazioni.DIM_BLOCCO_ARENA,(righe-2)*Impostazioni.DIM_BLOCCO_ARENA);
		
		matrice[1][colonne-2].numero = Impostazioni.BUCO_ARENA_SFIDA;  
		matrice[1][colonne-2].navigabile = true; 
		matrice[1][colonne-2].blocco = new Blocco((colonne-2)*Impostazioni.DIM_BLOCCO_ARENA,1*Impostazioni.DIM_BLOCCO_ARENA);
		
		matrice[righe-2][colonne-2].numero = Impostazioni.BUCO_ARENA_SFIDA;  
		matrice[righe-2][colonne-2].navigabile = true; 
		matrice[righe-2][colonne-2].blocco = new Blocco((colonne-2)*Impostazioni.DIM_BLOCCO_ARENA,(righe-2)*Impostazioni.DIM_BLOCCO_ARENA);
		
		matrice[1][14].numero = Impostazioni.BUCO_ARENA_SFIDA; 
		matrice[1][14].navigabile = true;
		matrice[1][14].blocco = new Blocco(14*Impostazioni.DIM_BLOCCO_ARENA, 1*Impostazioni.DIM_BLOCCO_ARENA);
		
		matrice[1][16].numero = Impostazioni.BUCO_ARENA_SFIDA; 
		matrice[1][16].navigabile = true;
		matrice[1][16].blocco = new Blocco(16*Impostazioni.DIM_BLOCCO_ARENA, 1*Impostazioni.DIM_BLOCCO_ARENA);
		
		matrice[righe-2][14].numero = Impostazioni.BUCO_ARENA_SFIDA;  
		matrice[righe-2][14].navigabile = true;
		matrice[righe-2][14].blocco = new Blocco(14*Impostazioni.DIM_BLOCCO_ARENA, (righe-2)*Impostazioni.DIM_BLOCCO_ARENA);
		
		matrice[righe-2][16].numero = Impostazioni.BUCO_ARENA_SFIDA;  
		matrice[righe-2][16].navigabile = true;
		matrice[righe-2][16].blocco = new Blocco(16*Impostazioni.DIM_BLOCCO_ARENA, (righe-2)*Impostazioni.DIM_BLOCCO_ARENA);
		
		
		
	}
	
	public void  riempi_matrice()
	{
		for(int i = 0; i<righe; i++)
		{
			for(int j = 0; j<colonne; j++)
			{
				
				matrice[i][j] = new Elemento();
				
				riempi_normale(i, j);
				costituisci_blocchi(i, j);
			}			
		}
	}
	
	public void riempi_normale(int i, int j)
	{
		if(i==0 || j==0 || i==righe-1 || j==colonne-1)
			{
				matrice[i][j].numero = Impostazioni.BLOCCO_FERRO; 
				matrice[i][j].blocco =new Blocco(j*Impostazioni.DIM_BLOCCO_ARENA,i*Impostazioni.DIM_BLOCCO_ARENA);
				matrice[i][j].navigabile = false;
			}
		else
			matrice[i][j].numero = Impostazioni.PAVIMENTO_SABBIOSO; 
		
		if((i==0 && j==0) || (i==0 && j==colonne-1) || (i==righe-1 && j==0) || (i==righe-1 && j==colonne-1))
		{
			matrice[i][j].numero = Impostazioni.BLOCCO_TESCHIO; 
			matrice[i][j].blocco =new Blocco(j*Impostazioni.DIM_BLOCCO_ARENA,i*Impostazioni.DIM_BLOCCO_ARENA);
			matrice[i][j].navigabile = false;
		}
		
	}
	
	public void costituisci_blocchi(int i, int j)
	{
		if(i%3==0 && i>0 && i <15 && j!=0 && j!=colonne-1 && j%3==0 && j>0 && j<29 )
		{
			
				matrice[i][j].numero = Impostazioni.BLOCCO_ARENA_SFIDA;  //blocchi
				matrice[i][j].blocco = new Blocco(j*Impostazioni.DIM_BLOCCO_ARENA,i*Impostazioni.DIM_BLOCCO_ARENA);
				matrice[i][j].navigabile = false;
		}
	}
	
	public ArrayList<Cella> calcolaLiberi() //questa va bene anche per l'area personalizzata dell'arena
	{
		liberi.clear();
			
		for(int i = 0; i<righe; i++)
		{
			for(int j = 0; j<colonne; j++)
			{
				if(get(i, j).numero==Impostazioni.BUCO_ARENA_SFIDA || get(i, j).numero==Impostazioni.BUCO_CASTELLO || get(i, j).numero==Impostazioni.BUCO_ERBA || get(i, j).numero ==Impostazioni.BUCO_STANDARD) 
				{
					liberi.add(new Cella(i,j));
				}
			}
		}
		return liberi;
	}
	
	
	
	

	
	
	
	
}
