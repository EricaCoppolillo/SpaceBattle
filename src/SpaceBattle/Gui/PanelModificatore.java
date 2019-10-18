package SpaceBattle.Gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JSplitPane;

import SpaceBattle.Config.Impostazioni;

public class PanelModificatore extends JSplitPane{

	
	public PanelModificatore()
	{
		super(JSplitPane.VERTICAL_SPLIT, new PanelGriglia(), new PanelScelta());
		this.setDividerLocation(Impostazioni.LOCAZIONE_DIVIDER_MODIFICATORE);
		this.setDividerSize(Impostazioni.SIZE_DIVIDER_MODIFICATORE);
		this.setEnabled(false);
		this.setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
