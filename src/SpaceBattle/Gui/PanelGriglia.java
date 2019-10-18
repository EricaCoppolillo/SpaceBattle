package SpaceBattle.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Editor.core.EditorColoraCella;
import SpaceBattle.Main.GameMain;

public class PanelGriglia extends JPanel{

	
	public ArrayList<MyJLabel>mattonelle = new ArrayList<MyJLabel>();

	
	public PanelGriglia()
	{
		GridLayout layout = new GridLayout(Impostazioni.RIGHE_PANELGRIGLIA,Impostazioni.COLONNE_PANELGRIGLIA);
		this.setLayout(layout);
		this.setBackground(Color.black);
		this.setFocusable(true);
		gestisciJLabel();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void gestisciJLabel()
	{
		for(int i = 0; i<Impostazioni.RIGHE_PANELGRIGLIA; i++)
		{
			for(int j = 0; j<Impostazioni.COLONNE_PANELGRIGLIA; j++)
			{
				if(j<7 || j>Impostazioni.COLONNE_PANELGRIGLIA-8)
					mattonelle.add(new MyJLabel());
				else
					{
						mattonelle.add(new MyJLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBuchiEditor().get(3)),i,j));
						mattonelle.get(mattonelle.size()-1).addMouseListener(new EditorColoraCella(mattonelle.get(mattonelle.size()-1)));
					}
				this.add(mattonelle.get(mattonelle.size()-1));
			}
		}
	}
}
