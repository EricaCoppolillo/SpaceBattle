package SpaceBattle.Arena.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Gui.PanelArena;
import SpaceBattle.Gui.PanelMenu;
import SpaceBattle.Main.GameMain;

public class SceltaArenaListener implements MouseListener {

	JLabel label;
	int indice;
	
	public  SceltaArenaListener() {
		indice = 0;
	}
	
	public SceltaArenaListener(JLabel label, int indice)
	{
		this.label = label;
		this.indice = indice;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(indice == Impostazioni.LABEL_TORNA_MENU)
		{
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(4)));
		}	
		else if(indice == Impostazioni.LABEL_ARENA_SFIDA)
		{
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniSceltaArena().get(2)));
		}
		else if(indice == Impostazioni.LABEL_ARENA_PERSONALIZZATA)
		{
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniSceltaArena().get(3)));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(indice == Impostazioni.LABEL_TORNA_MENU)
		{
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(3)));
		}		
		else if(indice == Impostazioni.LABEL_ARENA_SFIDA)
		{
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniSceltaArena().get(0)));
		}
		else if(indice == Impostazioni.LABEL_ARENA_PERSONALIZZATA)
		{
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniSceltaArena().get(1)));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(indice == Impostazioni.LABEL_TORNA_MENU)
		{
			GameMain.contenitore.removeAll();
			PanelMenu panelMenu = new PanelMenu();
			GameMain.contenitore.add(panelMenu);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			panelMenu.requestFocus();
		}
		else if(indice == Impostazioni.LABEL_ARENA_SFIDA)
		{
			GameMain.contenitore.removeAll();
			PanelArena panelArena = new PanelArena(new GestoreArena(new Matrice(),false));
			GameMain.contenitore.add(panelArena);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			panelArena.requestFocus();
		}
		else if(indice == Impostazioni.LABEL_ARENA_PERSONALIZZATA)
		{
			GameMain.contenitore.removeAll();
			PanelArena panelArena = new PanelArena(new GestoreArena(new Matrice(GameMain.gestoreEditor.leggiDaFile()),true));
			GameMain.contenitore.add(panelArena);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			panelArena.requestFocus();
		}
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
