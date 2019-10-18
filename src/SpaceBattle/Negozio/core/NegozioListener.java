package SpaceBattle.Negozio.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import SpaceBattle.Gui.PanelMenu;
import SpaceBattle.Main.GameMain;

public class NegozioListener implements MouseListener
{
	private int sorgente;
	private JLabel l;
	
	public NegozioListener(int s, JLabel l) 
	{
		sorgente = s;
		this.l = l;
	}
	
	public NegozioListener()
	{}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		if(sorgente == -1)
			l.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(4)));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		if(sorgente == -1)
			l.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(3)));
	}

	@Override
	public void mousePressed(MouseEvent e) {

		switch(sorgente)
		{
		case -1:
			GameMain.contenitore.removeAll();
			PanelMenu m = new PanelMenu();
			GameMain.contenitore.add(m);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			m.requestFocus();	
			break;
			
		case 0:
			gestisciAcquisto();
			break;
		
		case 1:
			gestisciAcquisto();
			break;
			
		case 2:
			gestisciAcquisto();
			break;
		
		case 3:
			gestisciAcquisto();
			break;
			
		case 4:
			gestisciAcquisto();
			break;
		
		case 5:
			gestisciAcquisto();
			break;
			
		}	
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	public void gestisciAcquisto()
	{
		if(GameMain.gestoreNegozio.getValore(sorgente).equals("0"))
		{
			int prezzo = Integer.parseInt(l.getText());
			int coinCorrenti = Integer.parseInt(GameMain.gestoreNegozio.ottieniCoin());
			if(prezzo <= coinCorrenti)
			{
				l.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(5)));

				coinCorrenti-=prezzo;
				GameMain.gestoreNegozio.modificaCoin(""+coinCorrenti);
				GameMain.gestoreNegozio.modificaValore(sorgente, "1");
			}
		}
		
		else if(GameMain.gestoreNegozio.getValore(sorgente).equals("1"))
		{
			l.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(6)));
			GameMain.gestoreNegozio.naveSelezionata = sorgente;
			GameMain.gestoreNegozio.controlla = true;//per evitare che sia il keylistener che il thread entrino in getValore() 
			//e uno dei due chiuda il reader mentre l'altro sta eseguendo la lettura
		}

		
	}
}
	
