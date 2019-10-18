package SpaceBattle.Menu.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import SpaceBattle.Gui.PanelMenu;
import SpaceBattle.Main.GameMain;

public class EscListener implements KeyListener {

	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			GameMain.contenitore.removeAll();
			PanelMenu panelMenu = new PanelMenu();
			GameMain.contenitore.add(panelMenu);
			GameMain.contenitore.revalidate();
			GameMain.contenitore.repaint();
			panelMenu.requestFocus();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
