package SpaceBattle.Editor.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Gui.MyJLabel;
import SpaceBattle.Main.GameMain;

public class EditorColoraCella implements MouseListener
{
	MyJLabel label;
	
	public EditorColoraCella(MyJLabel label) 
	{
		this.label = label;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(GameMain.gestoreEditor.selezionato == 0) //quindi sto andando ad inserire un blocco
		{
			if(GameMain.gestoreEditor.pavimentoCorrente == 0)
			{
				stoInserendoUnPavimento(Impostazioni.PAVIMENTO_AMBRA);
			}
			else if(GameMain.gestoreEditor.pavimentoCorrente == 1)
			{
				stoInserendoUnPavimento(Impostazioni.PAVIMENTO_SABBIOSO);
			}
			else if(GameMain.gestoreEditor.pavimentoCorrente == 2)
			{
				stoInserendoUnPavimento(Impostazioni.PAVIMENTO_GRAY);
				
			}
			else if(GameMain.gestoreEditor.pavimentoCorrente == 3)
			{
				stoInserendoUnPavimento(Impostazioni.PAVIMENTO_ERBA);
				
			}
		}
		else if(GameMain.gestoreEditor.selezionato == 1)
		{
			
			if(GameMain.gestoreEditor.bloccoCorrente == 0)
			{
				stoInserendoUnBlocco(Impostazioni.BLOCCO_TESCHIO);
			}
			else if(GameMain.gestoreEditor.bloccoCorrente == 1)
			{
				stoInserendoUnBlocco(Impostazioni.BLOCCO_FERRO);
			}
			else if(GameMain.gestoreEditor.bloccoCorrente == 2)
			{
				stoInserendoUnBlocco(Impostazioni.BLOCCO_ARENA_SFIDA);
			}
			else if(GameMain.gestoreEditor.bloccoCorrente == 3)
			{
				stoInserendoUnBlocco(Impostazioni.BLOCCO_CELESTE);
			}
			else if(GameMain.gestoreEditor.bloccoCorrente == 4)
			{
				stoInserendoUnBlocco(Impostazioni.BLOCCO_GHIACCIO);
			}
			
		}
		else if(GameMain.gestoreEditor.selezionato == 2)
		{
			if(GameMain.gestoreEditor.bucoCorrente == 0)
			{
				stoInserendoUnBuco(Impostazioni.BUCO_ARENA_SFIDA);
			}
			else if(GameMain.gestoreEditor.bucoCorrente == 1)
			{
				stoInserendoUnBuco(Impostazioni.BUCO_ERBA);
			}
			else if(GameMain.gestoreEditor.bucoCorrente == 2)
			{
				stoInserendoUnBuco(Impostazioni.BUCO_CASTELLO);
			}
			else if(GameMain.gestoreEditor.bucoCorrente == 3)
			{
				stoInserendoUnBuco(Impostazioni.BUCO_STANDARD);
			}
			
		}
 	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	public void stoInserendoUnPavimento(int tipoPavimento)
	{
		if (GameMain.gestoreEditor.isBlock(GameMain.gestoreEditor.returnValoreCella(label.riga, label.colonna-7))) 
		{
			if (GameMain.gestoreEditor.numeroPavimenti < Impostazioni.NUMERO_PAVIMENTO_MASSIMO) 
			{
				GameMain.gestoreEditor.numeroPavimenti++;
				GameMain.gestoreEditor.settaCellaMatrice("" + tipoPavimento, label.riga,
						label.colonna - 7);
				label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaMattonelleEditor().get(GameMain.gestoreEditor.pavimentoCorrente)));
			}
		} 
		else if (GameMain.gestoreEditor.isHole(GameMain.gestoreEditor.returnValoreCella(label.riga, label.colonna-7))) 
		{
			if (GameMain.gestoreEditor.numeroPavimenti < Impostazioni.NUMERO_PAVIMENTO_MASSIMO
					&& GameMain.gestoreEditor.numeroBuchi >= Impostazioni.NUMERO_BUCHI_MINIMO) 
			{
				
					GameMain.gestoreEditor.numeroPavimenti++;
					GameMain.gestoreEditor.numeroBuchi--;
					GameMain.gestoreEditor.settaCellaMatrice("" + tipoPavimento, label.riga,label.colonna - 7);
					label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaMattonelleEditor().get(GameMain.gestoreEditor.pavimentoCorrente)));
			}
			
		}
		else
		{
			GameMain.gestoreEditor.settaCellaMatrice("" + tipoPavimento, label.riga,
					label.colonna - 7);
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaMattonelleEditor().get(GameMain.gestoreEditor.pavimentoCorrente)));
		}
	}
	
	public void stoInserendoUnBlocco(int tipoBlocco)
	{
		if (GameMain.gestoreEditor.isFloor(GameMain.gestoreEditor.returnValoreCella(label.riga, label.colonna-7))) 
		{
			if (GameMain.gestoreEditor.numeroBlocchi < Impostazioni.NUMERO_BLOCCHI_MASSIMO) 
			{
				GameMain.gestoreEditor.numeroBlocchi++;
				GameMain.gestoreEditor.numeroPavimenti--;
				GameMain.gestoreEditor.settaCellaMatrice("" + tipoBlocco, label.riga,
						label.colonna - 7);
				label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBlocchiEditor().get(GameMain.gestoreEditor.bloccoCorrente)));
			}
		} 
		else if (GameMain.gestoreEditor.isHole(GameMain.gestoreEditor.returnValoreCella(label.riga, label.colonna-7))) 
		{
			if (GameMain.gestoreEditor.numeroBlocchi < Impostazioni.NUMERO_BLOCCHI_MASSIMO
					&& GameMain.gestoreEditor.numeroBuchi >= Impostazioni.NUMERO_BUCHI_MINIMO) 
			{
				GameMain.gestoreEditor.numeroBlocchi++;
				GameMain.gestoreEditor.numeroBuchi--;
				GameMain.gestoreEditor.settaCellaMatrice("" + tipoBlocco, label.riga,
						label.colonna - 7);
				label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBlocchiEditor().get(GameMain.gestoreEditor.bloccoCorrente)));
			}
			
		}
		else 
		{
			GameMain.gestoreEditor.settaCellaMatrice("" + tipoBlocco, label.riga,
					label.colonna - 7);
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBlocchiEditor().get(GameMain.gestoreEditor.bloccoCorrente)));
		}
	}
	
	public void stoInserendoUnBuco(int tipoBuco)
	{
		if (GameMain.gestoreEditor.isFloor(GameMain.gestoreEditor.returnValoreCella(label.riga, label.colonna-7))) 
		{			
				GameMain.gestoreEditor.numeroBuchi++;
				GameMain.gestoreEditor.numeroPavimenti--;
				GameMain.gestoreEditor.settaCellaMatrice("" + tipoBuco, label.riga,	label.colonna - 7);
				label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBuchiEditor().get(GameMain.gestoreEditor.bucoCorrente)));
		} 
		else if (GameMain.gestoreEditor.isBlock(GameMain.gestoreEditor.returnValoreCella(label.riga, label.colonna-7))) 
		{
				GameMain.gestoreEditor.numeroBlocchi--;
				GameMain.gestoreEditor.numeroBuchi++;
				GameMain.gestoreEditor.settaCellaMatrice("" + tipoBuco, label.riga,
						label.colonna - 7);
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBuchiEditor().get(GameMain.gestoreEditor.bucoCorrente)));
		}
		else 
		{
			GameMain.gestoreEditor.settaCellaMatrice("" + tipoBuco, label.riga,
					label.colonna - 7);
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBuchiEditor().get(GameMain.gestoreEditor.bucoCorrente)));
		}
	}	
}
