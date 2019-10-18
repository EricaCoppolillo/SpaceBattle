package SpaceBattle.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Editor.core.EditorTitoloListener;
import SpaceBattle.Main.GameMain;

public class PanelTitoloEditor extends JPanel{

	public ArrayList<JLabel>Jlabel = new ArrayList<JLabel>();
	public PanelTitoloEditor()
	{
		this.setLayout(new GridLayout(Impostazioni.RIGHE_LAYOUT_PANEL_TITOLO_EDITOR,Impostazioni.COLONNE_LAYOUT_PANEL_TITOLO_EDITOR));
		this.setFocusable(true);
		this.setBackground(Color.black);
		gestisciJLabel();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void gestisciJLabel()
	{
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(3))));
		Jlabel.get(Jlabel.size()-1).addMouseListener(new EditorTitoloListener(Jlabel.get(Jlabel.size()-1)));
		
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaTitoloEditor())));
		
		Jlabel.add(new JLabel());
		
		for(int i = 0; i<Jlabel.size(); i++)
			this.add(Jlabel.get(i));
		
	}
}
