package SpaceBattle.Gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpaceBattle.Arena.core.SceltaArenaListener;
import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Main.GameMain;
import SpaceBattle.Menu.core.EscListener;

public class PanelSceltaArena extends JPanel{

	ArrayList<JLabel>label = new ArrayList<JLabel>();
	public PanelSceltaArena() 
	{
		this.setLayout(new GridLayout(Impostazioni.RIGHE_LAYOUT_PANEL_SCELTA_ARENA,Impostazioni.COLONNE_LAYOUT_PANEL_SCELTA_ARENA));
		gestisciJlabel();
		this.setFocusable(true);
		this.addKeyListener(new EscListener());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		disegnaSfondo(g);
		
	}
	
	public void disegnaSfondo(Graphics g)
	{
		g.drawImage(GameMain.gestoreImmagini.caricaSfondoMenu(), 0, 0, null);
	}
	
	public void gestisciJlabel()
	{
		label.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(3))));
		label.get(label.size()-1).addMouseListener(new SceltaArenaListener(label.get(label.size()-1),1));
		this.add(label.get(label.size()-1));
		
		label.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniSceltaArena().get(0))));
		label.get(label.size()-1).addMouseListener(new SceltaArenaListener(label.get(label.size()-1),2));
		this.add(label.get(label.size()-1));
		
		this.add(new JLabel());
		this.add(new JLabel());
		
		label.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniSceltaArena().get(1))));
		label.get(label.size()-1).addMouseListener(new SceltaArenaListener(label.get(label.size()-1),3));
		this.add(label.get(label.size()-1));
		for(int i = 0; i<Impostazioni.LABEL_VUOTE_SCELTA_ARENA; i++)
			this.add(new JLabel());
		
		
	}
}
