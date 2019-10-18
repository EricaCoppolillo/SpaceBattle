package SpaceBattle.Menu.core;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import SpaceBattle.Gui.PanelClassifica;
import SpaceBattle.Gui.PanelComandi;
import SpaceBattle.Gui.PanelEditor;
import SpaceBattle.Gui.PanelNegozio;
import SpaceBattle.Gui.PanelSceltaArena;
import SpaceBattle.Gui.PanelSceltaViaggio;
import SpaceBattle.Main.GameMain;

public class MenuListener extends KeyAdapter implements MouseListener {

	private int sorgente;
	private JLabel labelCorrente;
	
	public MenuListener() {}
	
	public MenuListener(int i, JLabel label) {
		sorgente = i;
		labelCorrente = label;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		labelCorrente.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniPremuti().get(sorgente)));		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		labelCorrente.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoni().get(sorgente)));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		switch(sorgente)
		{
		case 0:
			GameMain.contenitore.removeAll();
			PanelSceltaViaggio scelta = new PanelSceltaViaggio();
			GameMain.contenitore.add(scelta);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			scelta.requestFocus();
			break;
			
		case 1:
			GameMain.contenitore.removeAll();
			PanelSceltaArena panelArena = new PanelSceltaArena();
			GameMain.contenitore.add(panelArena);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			panelArena.requestFocus();
			break;
			
		case 2:
			GameMain.contenitore.removeAll();
			PanelNegozio n = new PanelNegozio();
			GameMain.contenitore.add(n);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			n.requestFocus(); //potrebbe essere inutile
			break;
			
		case 3:
			GameMain.contenitore.removeAll();
			GameMain.gestoreEditor.popolaMatriceIniziale();
			PanelEditor panelEditor = new PanelEditor();
			GameMain.contenitore.add(panelEditor);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			panelEditor.requestFocus();
			break;
			
		case 4:
			GameMain.contenitore.removeAll();
			PanelClassifica panelClassifiche = new PanelClassifica();
			GameMain.contenitore.add(panelClassifiche);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			panelClassifiche.requestFocus();
			break;
			
		case 5:
			GameMain.contenitore.removeAll();
			PanelComandi panelComandi = new PanelComandi();
			GameMain.contenitore.add(panelComandi);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			panelComandi.requestFocus();
			break;
		
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
