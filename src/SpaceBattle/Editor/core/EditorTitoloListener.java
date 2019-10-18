package SpaceBattle.Editor.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import SpaceBattle.Gui.PanelMenu;
import SpaceBattle.Main.GameMain;

public class EditorTitoloListener implements MouseListener
{
	public JLabel label;
	
	public  EditorTitoloListener(JLabel label) 
	{
		this.label = label;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		this.label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(6)));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(5)));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		GameMain.contenitore.removeAll();
		PanelMenu panelMenu = new PanelMenu();
		GameMain.contenitore.add(panelMenu);
		GameMain.contenitore.revalidate();
		GameMain.contenitore.repaint();
		panelMenu.requestFocus();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
