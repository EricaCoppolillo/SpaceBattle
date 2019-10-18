package SpaceBattle.LogicaCondivisa.core;
import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Arena.core.Matrice;

public class GestoreCollisioni {

	public GestoreCollisioni() {}
	
	
	public boolean collisioneOggettiSx(Oggetto o1, Oggetto o2)
	{
		if(o1.x >= o2.x && o1.x < o2.x + o2.lunghezza && o1.y + o1.altezza > o2.y && o1.y < o2.y + o2.altezza)
			return true;
		return false;
	}
	
	public boolean collisioneOggettiDx(Oggetto o1, Oggetto o2)
	{
		if(o1.x+o1.lunghezza >= o2.x && o1.x + o1.lunghezza <= o2.x + o2.lunghezza 
			&& o1.y + o1.altezza > o2.y && o1.y < o2.y + o2.altezza)
			return true;
		return false;
	}
	
	public boolean collisioneOggettiSu(Oggetto o1, Oggetto o2)
	{
		if( o1.x+o1.lunghezza >= o2.x 
				&& o1.x  <= o2.x+o2.lunghezza
				&& o1.y  <= o2.y+o2.altezza
				&& o1.y >= o2.y )
				return true;
		return false;
	}
	
	public boolean collisioneOggettiGiu(Oggetto o1, Oggetto o2)
	{
		if( o1.x+o1.lunghezza >= o2.x 
				&& o1.x  <= o2.x+o2.lunghezza
				&& o1.y+o1.altezza >= o2.y 
				&& o1.y+o1.altezza <= o2.y+o2.altezza)
			return true;
		return false;
	}
	
	public boolean collisioneOggetti(Oggetto o1, Oggetto o2)
	{
		if(collisioneOggettiSx(o1, o2) || collisioneOggettiDx(o1, o2) || collisioneOggettiSu(o1, o2) || collisioneOggettiGiu(o1, o2))
		{
			return true;
		}
		return false;
	}
	
	public void gestisciCollisioneProViaggio(Nave nave1, Nave nave2)
	{
		for(int i=0; i<nave1.pro().size(); i++)
		{
			if(nave2.getVita() > Impostazioni.VITA_TERMINATA && nave1.pro().get(i) != null)
			{
				if(collisioneOggetti(nave1.pro().get(i), nave2))
				{
					nave1.pro().remove(i);
					nave2.setColpita(true);
					nave2.setVita(nave2.getVita() - nave1.getDanno());
				}
			}
		}
	}
	
	public boolean nave_blocco_sinistra_destra(Nave nave, Matrice m)
	{
		for(int i = 0; i<m.righe; i++)
			for(int j = 0; j<m.colonne; j++)
				if(m.get(i, j).navigabile==false)
				{
					if(nave.x + nave.lunghezza + nave.getVelocita() > m.get(i, j).blocco.x 
							&& nave.x + nave.lunghezza + nave.getVelocita() < m.get(i, j).blocco.x + m.get(i, j).blocco.lato 
							&& nave.y > m.get(i, j).blocco.y - m.get(i, j).blocco.lato 
							&& nave.y + nave.altezza < m.get(i, j).blocco.y + (m.get(i, j).blocco.lato*2))
						return true;
				}
		
		return false;
	}
	
	public boolean nave_blocco_destra_sinistra(Nave nave, Matrice m)
	{
		for(int i = 0; i<m.righe; i++)
			for(int j = 0; j<m.colonne; j++)
					if(m.get(i, j).navigabile==false)
					{
						if(nave.x - nave.getVelocita() > m.get(i, j).blocco.x 
								&& nave.x - nave.getVelocita() < m.get(i, j).blocco.x + m.get(i, j).blocco.lato 
								&& nave.y > m.get(i, j).blocco.y - m.get(i, j).blocco.lato 
								&& nave.y + nave.altezza < m.get(i, j).blocco.y + (m.get(i, j).blocco.lato*2))
							return true;
					}
		return false;
	}
	
	public boolean nave_blocco_sopra_sotto(Nave nave, Matrice m)
	{
		for(int i = 0; i < m.righe; i++)
			for(int j = 0; j < m.colonne; j++)
				if(m.get(i, j).navigabile==false)
				{
					if(nave.x + nave.lunghezza>m.get(i, j).blocco.x 
							&& nave.x +nave.lunghezza < m.get(i, j).blocco.x+m.get(i, j).blocco.lato*2 
							&& nave.y + nave.altezza + nave.getVelocita() > m.get(i, j).blocco.y 
							&& nave.y + nave.altezza + nave.getVelocita() < m.get(i, j).blocco.y + m.get(i, j).blocco.lato)
						return true;
				}
		return false;
	}
	
	public boolean nave_blocco_sotto_sopra(Nave nave, Matrice m)
	{
		for(int i = 0; i<m.righe; i++)
			for(int j = 0; j<m.colonne; j++)
					if(m.get(i, j).navigabile==false)
					{
						if(nave.x + nave.lunghezza > m.get(i, j).blocco.x 
								&& nave.x + nave.lunghezza < m.get(i, j).blocco.x + (m.get(i, j).blocco.lato*2)
								&& nave.y - nave.getVelocita() > m.get(i, j).blocco.y 
								&& nave.y - nave.getVelocita() < m.get(i, j).blocco.y + m.get(i, j).blocco.lato)
							return true;
					}
				
		return false;
	}
	
	
	public boolean proiettile_blocco_sinistra_destra(Proiettile pro, Matrice m)
	{
			for(int i = 0; i<m.righe; i++)
				for(int j = 0; j<m.colonne; j++)
				{
					if(m.get(i, j).navigabile==false)
					{
						if(pro!=null && 
								(pro.x+pro.lunghezza>=m.get(i, j).blocco.x && pro.x+pro.lunghezza<=m.get(i, j).blocco.x+m.get(i, j).blocco.lato)
								&&
								(pro.y+pro.altezza>m.get(i, j).blocco.y && pro.y<m.get(i, j).blocco.y+m.get(i, j).blocco.lato )
								)
							return true;
					}
				}
		return false;
	}
	
	public boolean proiettile_blocco_destra_sinistra(Proiettile pro, Matrice m)
	{
		for(int i = 0; i<m.righe; i++)
			for(int j = 0; j<m.colonne; j++)
			{
				if(m.get(i, j).navigabile==false)
				{
					if(pro!=null && 
							(pro.x>=m.get(i, j).blocco.x && pro.x<=m.get(i, j).blocco.x+m.get(i, j).blocco.lato)
							&&
							(pro.y+pro.altezza>m.get(i, j).blocco.y && pro.y<m.get(i, j).blocco.y+m.get(i, j).blocco.lato )
							)
						return true;
				}
			}
		return false;
	}
	
	public boolean proiettile_blocco_sopra_sotto(Proiettile pro, Matrice m)
	{
		for(int i = 0; i<m.righe; i++)
			for(int j = 0; j<m.colonne; j++)
			{
				if(m.get(i, j).navigabile==false)
				{
					if(pro!=null && 
							(pro.x+pro.lunghezza>m.get(i, j).blocco.x && pro.x<m.get(i, j).blocco.x+m.get(i, j).blocco.lato)
							&&
							(pro.y+pro.altezza>m.get(i, j).blocco.y && pro.y+pro.altezza<m.get(i, j).blocco.y+m.get(i, j).blocco.lato )
							)
						return true;
				}
			}
		return false;
	}
	
	public boolean proiettile_blocco_sotto_sopra(Proiettile pro, Matrice m)
	{
		for(int i = 0; i<m.righe; i++)
			for(int j = 0; j<m.colonne; j++)
			{
				if(m.get(i, j).navigabile==false)
				{
					if(pro!=null && 
							(pro.x + pro.lunghezza > m.get(i, j).blocco.x 
									&& pro.x<m.get(i, j).blocco.x+m.get(i, j).blocco.lato)
							&&
							(pro.y>m.get(i, j).blocco.y 
									&& pro.y<m.get(i, j).blocco.y+m.get(i, j).blocco.lato )
							)
						return true;
				}
			}
		return false;
	}
	
	public boolean gestisciCollisioneProArena(Nave nave1, Nave nave2)
	{
		for(int i=0; i<nave1.pro().size(); i++)
		{
			if(nave1.pro().get(i)!=null)
			{
				if(collisioneOggetti(nave1.pro().get(i), nave2)) 
				{
					nave1.pro().remove(i);
					return  true;
				}
			}
		}
		return false;
	}		
}
