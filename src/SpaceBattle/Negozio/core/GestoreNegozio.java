package SpaceBattle.Negozio.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestoreNegozio {
	
	private String coin;
	public BufferedWriter writer;
	public ArrayList<String> stringhe;
	public BufferedReader reader;
	public int naveSelezionata;
	public boolean controlla;

	public GestoreNegozio()
	{
		stringhe = new ArrayList<String>();
		naveSelezionata = getSelezionata();
		controlla = false;
	}
	
	public synchronized String ottieniCoin()
	{
		try {
			reader = new BufferedReader(new FileReader("filetxt" + File.separator + "Negozio.txt"));
			coin = reader.readLine();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return coin;
	}
	
	public synchronized void modificaCoin(String coin)
	{	
		stringhe.clear();
		try {
			reader = new BufferedReader(new FileReader("filetxt" + File.separator + "Negozio.txt"));
			while(reader.ready())
			{
				String temp = reader.readLine();
				stringhe.add(temp);
			}
			reader.close();
			writer = new BufferedWriter(new FileWriter("filetxt" + File.separator + "Negozio.txt"));

			writer.append(coin);
			for(int i=1; i<stringhe.size(); i++)
			{
				writer.newLine();
				writer.append(stringhe.get(i));
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized int getSelezionata()
	{
		int cont = 0;
		String valore = "";
		try
		{
			reader = new BufferedReader(new FileReader("filetxt" + File.separator + "Negozio.txt"));
			while(reader.ready())
			{
				valore = reader.readLine();

				if(valore.equals("2"))
					break;
				cont++;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cont-1;
	}
	
	public synchronized String getValore(int index)
	{
		int cont = 0;//nella prima riga del file.txt ho sempre il valore dei coin correnti
		String valore = "";
		try
		{
			reader = new BufferedReader(new FileReader("filetxt" + File.separator + "Negozio.txt"));
			while(reader.ready())
			{
				valore = reader.readLine();

				if(cont == index+1)
					break;
				cont++;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valore;
	}
	
	public synchronized void modificaValore(int index, String v)
	{
		stringhe.clear();
		try {
			reader = new BufferedReader(new FileReader("filetxt" + File.separator + "Negozio.txt"));
			while(reader.ready())
			{
				String temp = reader.readLine();
				stringhe.add(temp);
			}
			reader.close();
			writer = new BufferedWriter(new FileWriter("filetxt" + File.separator + "Negozio.txt"));

			for(int i=0; i<stringhe.size(); i++)
			{
				if(i == index+1)
					writer.append(v);
				else
					writer.append(stringhe.get(i));
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
