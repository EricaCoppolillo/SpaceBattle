package SpaceBattle.Editor.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;


import SpaceBattle.Config.Impostazioni;

public class GestoreEditor {
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public int pavimentoCorrente = 0;
	public int bucoCorrente = 0;
	public int bloccoCorrente = 0;
	
	public int selezionato = -1;
	
	public int numeroBuchi = 512;
	public int numeroBlocchi = 0;
	public int numeroPavimenti = 0;
	
	public String[][] matrice = new String[Impostazioni.RIGHE_MATRICE][Impostazioni.COLONNE_MATRICE];

	
	public GestoreEditor()
	{
		popolaMatriceIniziale();
	}
	
	public void popolaMatriceIniziale()
	{
		for(int i = 0; i<Impostazioni.RIGHE_MATRICE; i++)
		{
			for(int j = 0; j<Impostazioni.COLONNE_MATRICE; j++)
			{
				matrice[i][j] = "12";
			}
		}
	}
	
	public int[][] leggiDaFile()
	{
		int[][] matrix = new int[Impostazioni.RIGHE_MATRICE][Impostazioni.COLONNE_MATRICE];
		int i = 0;
		StringTokenizer s;
		try {
			
			reader = new BufferedReader(new FileReader("filetxt" + File.separator + "Mappa.txt"));
			while(reader.ready())
			{
				s = new StringTokenizer(reader.readLine());
				
					for(int j = 0; j < Impostazioni.COLONNE_MATRICE; j++)
					{
						Integer x = Integer.parseInt(s.nextToken());
						matrix[i][j] = x;
					}
				i++;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matrix;
	}
	
	public void scriviSuFile()
	{
		try {
			writer = new BufferedWriter(new FileWriter("filetxt" + File.separator + "Mappa.txt"));
			
			for(int i = 0; i <Impostazioni.RIGHE_MATRICE; i++)
			{
				for(int j = 0; j < Impostazioni.COLONNE_MATRICE; j++)
					writer.append("" + matrice[i][j] + " ");
				writer.append("\n");	 
			}
			
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void settaCellaMatrice(String valore, int riga, int colonna)
	{
		matrice[riga][colonna] = valore;
	}
	
	public int returnValoreCella(int riga, int colonna)
	{
		return Integer.parseInt(matrice[riga][colonna]);
	}
	
	public boolean isHole(int valore)
	{
		if(valore == Impostazioni.BUCO_ARENA_SFIDA || valore == Impostazioni.BUCO_CASTELLO || valore == Impostazioni.BUCO_ERBA || valore == Impostazioni.BUCO_STANDARD)
			return true;
		return false;
	}
	
	public boolean isBlock(int valore)
	{
		if(valore == Impostazioni.BLOCCO_ARENA_SFIDA || valore == Impostazioni.BLOCCO_CELESTE || valore == Impostazioni.BLOCCO_FERRO || valore == Impostazioni.BLOCCO_GHIACCIO || valore == Impostazioni.BLOCCO_TESCHIO )
			return true;
		return false;
	}
	
	public boolean isFloor(int valore)
	{
		if(valore == Impostazioni.PAVIMENTO_AMBRA || valore == Impostazioni.PAVIMENTO_ERBA || valore == Impostazioni.PAVIMENTO_GRAY || valore == Impostazioni.PAVIMENTO_SABBIOSO)
			return true;
		return false;
	}
	
}
