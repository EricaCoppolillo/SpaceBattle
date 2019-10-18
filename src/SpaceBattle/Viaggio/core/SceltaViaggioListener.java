package SpaceBattle.Viaggio.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import SpaceBattle.Gui.PanelViaggio;
import SpaceBattle.Main.GameMain;

public class SceltaViaggioListener implements MouseListener {

	private int sorgente;
	private JLabel labelCorrente;
	
	public SceltaViaggioListener(int s, JLabel l)
	{
		sorgente = s;
		labelCorrente = l;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		labelCorrente.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniPremuti().get(sorgente+6)));
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		labelCorrente.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoni().get(sorgente+6)));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		switch(sorgente)
		{
		case 0:
			GameMain.contenitore.removeAll();
			PanelViaggio panel = new PanelViaggio(false);
			GameMain.contenitore.add(panel);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			panel.requestFocus();
			break;
			
		case 1:
			GameMain.contenitore.removeAll();
			PanelViaggio panel2 = new PanelViaggio(true);
			GameMain.contenitore.add(panel2);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			panel2.requestFocus();
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	
}
