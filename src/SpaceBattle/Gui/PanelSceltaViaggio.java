package SpaceBattle.Gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Main.GameMain;
import SpaceBattle.Menu.core.EscListener;
import SpaceBattle.Negozio.core.NegozioListener;
import SpaceBattle.Viaggio.core.SceltaViaggioListener;

public class PanelSceltaViaggio extends JPanel {

	ArrayList<JLabel>label = new ArrayList<JLabel>();
	public PanelSceltaViaggio() 
	{
		this.setLayout(new GridLayout(Impostazioni.RIGHE_LAYOUT_PANEL_SCELTA_VIAGGIO, Impostazioni.COLONNE_LAYOUT_PANEL_SCELTA_VIAGGIO));
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
		label.get(label.size()-1).addMouseListener(new NegozioListener(-1, label.get(label.size()-1)));
		this.add(label.get(label.size()-1));
		
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());

		label.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoni().get(6))));
		label.get(label.size()-1).addMouseListener(new SceltaViaggioListener(0, label.get(label.size()-1)));
		this.add(label.get(label.size()-1));
		
		this.add(new JLabel());
		this.add(new JLabel());
		
		label.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoni().get(7))));
		label.get(label.size()-1).addMouseListener(new SceltaViaggioListener(1, label.get(label.size()-1)));
		this.add(label.get(label.size()-1));
		for(int i = 0; i<4; i++)
			this.add(new JLabel());
		
		
	}
}
