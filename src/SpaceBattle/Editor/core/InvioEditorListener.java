package SpaceBattle.Editor.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import SpaceBattle.Main.GameMain;

public class InvioEditorListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {
		GameMain.gestoreEditor.scriviSuFile();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
