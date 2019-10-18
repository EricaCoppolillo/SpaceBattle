package SpaceBattle.Gui;

import java.awt.Graphics;

import javax.swing.JSplitPane;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Menu.core.EscListener;

public class PanelEditor extends JSplitPane 
{
	public PanelEditor()
	{
		super(JSplitPane.VERTICAL_SPLIT,new PanelTitoloEditor(),new PanelModificatore());
		this.setDividerLocation(Impostazioni.LOCAZIONE_DIVIDER_EDITOR);
		this.setDividerSize(0);
		this.addKeyListener(new EscListener());
		this.setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	
	
}
