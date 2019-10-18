package SpaceBattle.Classifica.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestoreClassifica 
{
	
	public String migliorPunteggioViaggio;
	public ArrayList<String> miglioriPunteggiArena;
	public ArrayList<String> stringhe;
	
	BufferedReader reader;
	BufferedWriter writer;
	
	public GestoreClassifica()
	{
		miglioriPunteggiArena = new ArrayList<String>();
		stringhe = new ArrayList<String>();
	}
	
	public synchronized String ottieniMigliorPunteggioViaggio()
	{
		try {
			reader = new BufferedReader(new FileReader("filetxt" + File.separator + "Punteggi.txt"));
			migliorPunteggioViaggio = reader.readLine();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return migliorPunteggioViaggio;
	}
	
	public synchronized void modificaMigliorPunteggioViaggio(int p)
	{
		stringhe.clear();
		try {
			reader = new BufferedReader(new FileReader("filetxt" + File.separator + "Punteggi.txt"));
			while(reader.ready())
			{
				String temp = reader.readLine();
				stringhe.add(temp);
			}
			reader.close();
			Integer pCorrente = Integer.parseInt(stringhe.get(0));
			if(pCorrente < p)
			{
				writer = new BufferedWriter(new FileWriter("filetxt" + File.separator + "Punteggi.txt"));
				writer.append(""+p);
				for(int i=1; i<stringhe.size(); i++)
				{
					writer.newLine();
					writer.append(stringhe.get(i));
				}
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized ArrayList<String> ottieniMigliorPunteggiArena()
	{
		try {
			reader = new BufferedReader(new FileReader("filetxt" + File.separator + "Punteggi.txt"));
			reader.readLine();
			while(reader.ready())
			{
				miglioriPunteggiArena.add(reader.readLine());
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return miglioriPunteggiArena;
	}
	
	public synchronized void modificaMigliorPunteggioArena(int p)
	{
		stringhe.clear();
		try {
			reader = new BufferedReader(new FileReader("filetxt" + File.separator + "Punteggi.txt"));
			while(reader.ready())
			{
				String temp = reader.readLine();
				stringhe.add(temp);
			}
			reader.close();
			Integer migliorP1 = Integer.parseInt(stringhe.get(1));
			Integer migliorP2 = Integer.parseInt(stringhe.get(2));
			Integer migliorP3 = Integer.parseInt(stringhe.get(3));
			if(migliorP1 < p)
			{
				writer = new BufferedWriter(new FileWriter("filetxt" + File.separator + "Punteggi.txt"));
				writer.append(stringhe.get(0));
				writer.newLine();
				writer.append(""+p);
				for(int i=1; i<stringhe.size()-1; i++)
				{
					writer.newLine();
					writer.append(stringhe.get(i));
				}
				writer.close();
			}
			
			else if(migliorP2 < p)
			{
				writer = new BufferedWriter(new FileWriter("filetxt" + File.separator + "Punteggi.txt"));
				writer.append(stringhe.get(0));
				writer.newLine();
				writer.append(stringhe.get(1));
				writer.newLine();
				writer.append(""+p);
				writer.newLine();
				writer.append(stringhe.get(stringhe.size()-1));
				writer.close();
			}
			
			else if(migliorP3 < p)
			{
				writer = new BufferedWriter(new FileWriter("filetxt" + File.separator + "Punteggi.txt"));
				for(int i=0; i<stringhe.size()-1; i++)
				{
					writer.newLine();
					writer.append(stringhe.get(i));
				}
				writer.newLine();
				writer.append(""+p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
