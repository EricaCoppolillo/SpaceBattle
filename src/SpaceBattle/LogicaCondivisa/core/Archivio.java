package SpaceBattle.LogicaCondivisa.core;
import java.util.ArrayList;

public class Archivio 
{
	final int codici = 5;
	final ArrayList<Integer> vite=new ArrayList<Integer>();
	final ArrayList<Integer> velocita=new ArrayList<Integer>();
	final ArrayList<Integer> danno=new ArrayList<Integer>();
	
	public Archivio() 
	{
		int n = 5;
		for(int i=0; i<codici; i++)
		{
			vite.add(i*n + 10);
			velocita.add(i*n + 15);
			danno.add(i*n + 1);
		}
	}
}
