package SpaceBattle.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpaceBattle.Classifica.core.GestoreClassifica;
import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Main.GameMain;
import SpaceBattle.Menu.core.EscListener;
import SpaceBattle.Negozio.core.NegozioListener;

public class PanelClassifica extends JPanel
{
	public JLabel indietro;
	public GestoreClassifica gestoreClassifica;

	public PanelClassifica()
	{
		gestoreClassifica = new GestoreClassifica();
		this.setLayout(new GridLayout(Impostazioni.RIGHE_LAYOUT_PANELCLASSIFICA, Impostazioni.COLONNE_LAYOUT_PANELCLASSIFICA));
		
		this.addKeyListener(new EscListener());
		this.setFocusable(true);
		gestisciJLabel();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		disegnaSfondo(g);		
	}
	
	public void disegnaSfondo(Graphics g)
	{
		g.drawImage(GameMain.gestoreImmagini.caricaSfondoNegozio(), 0, 0, Impostazioni.WIDTH,Impostazioni.HEIGHT ,null);
	}
	
	public void gestisciJLabel()
	{
		indietro = new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(3)));
		indietro.addMouseListener(new NegozioListener(-1, indietro));
		this.add(indietro);
		JLabel l;
		l = new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaTitoloClassifica()));
		this.add(l);
		
		this.add(new JLabel());
		this.add(new JLabel());
		
		l = new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaScrittaMigliorPunteggioViaggio()));
		this.add(l);

		this.add(new JLabel());
		this.add(new JLabel());
		
		l = new JLabel(gestoreClassifica.ottieniMigliorPunteggioViaggio(),new ImageIcon(GameMain.gestoreImmagini.caricaCorone().get(0)),JLabel.HORIZONTAL);
		l.setFont(GameMain.gestoreImmagini.caricaFontViaggio());
		l.setForeground(Color.white);
		this.add(l);
		
		this.add(new JLabel());
		this.add(new JLabel());

		this.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaScrittaMigliorPunteggioArena())));
	
		this.add(new JLabel());
		this.add(new JLabel());

		l = new JLabel(gestoreClassifica.ottieniMigliorPunteggiArena().get(0), new ImageIcon(GameMain.gestoreImmagini.caricaCorone().get(1)),JLabel.HORIZONTAL);
		l.setFont(GameMain.gestoreImmagini.caricaFontViaggio());
		l.setForeground(Color.white);

		this.add(l);
		
		this.add(new JLabel());
		this.add(new JLabel());

		l =	new JLabel(gestoreClassifica.ottieniMigliorPunteggiArena().get(1),new ImageIcon(GameMain.gestoreImmagini.caricaCorone().get(2)),JLabel.HORIZONTAL);
		l.setFont(GameMain.gestoreImmagini.caricaFontViaggio());
		l.setForeground(Color.white);
		this.add(l);
		
		this.add(new JLabel());
		this.add(new JLabel());

		l = new JLabel(gestoreClassifica.ottieniMigliorPunteggiArena().get(2),new ImageIcon(GameMain.gestoreImmagini.caricaCorone().get(3)),JLabel.HORIZONTAL);
		l.setFont(GameMain.gestoreImmagini.caricaFontViaggio());
		l.setForeground(Color.white);
		this.add(l);

		this.add(new JLabel());
	}
	
	
	
}
