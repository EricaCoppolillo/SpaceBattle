package SpaceBattle.LogicaCondivisa.core;


public class Cella {
	public Integer riga;
	public Integer colonna;
	public Integer direzione;
	
	public Cella(int riga, int colonna, Integer direzione)
	{
		this.riga = riga;
		this.colonna = colonna;
		this.direzione = direzione;
	}
	
	public Cella()
	{}
	
	public Cella(int riga, int colonna)
	{
		this.riga = riga;
		this.colonna = colonna;
	}
	
	
	public Cella(Cella cella)
	{
		this.colonna = cella.colonna;
		this.riga = cella.riga;
		this.direzione = cella.direzione;
	}
	
	@Override
	public boolean equals(Object o)
	{
		Cella cella = (Cella)o;
			return this.riga == cella.riga && this.colonna == cella.colonna && this.direzione == cella.direzione;
	}

	
	
}
