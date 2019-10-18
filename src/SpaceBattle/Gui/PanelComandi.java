package SpaceBattle.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Main.GameMain;
import SpaceBattle.Menu.core.EscListener;
import SpaceBattle.Negozio.core.NegozioListener;

public class PanelComandi extends JPanel {

	private JLabel indietro;
	
	public PanelComandi() 
	{
		this.setLayout(new GridLayout(Impostazioni.RIGHE_LAYOUT_PANELCOMANDI, Impostazioni.COLONNE_LAYOUT_PANELCOMANDI));
		this.addKeyListener(new EscListener());
		
		gestisciJLabel();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		disegnaSfondo(g);
	}
	
	public void disegnaSfondo(Graphics g)
	{
		g.drawImage(GameMain.gestoreImmagini.caricaSfondoNegozio(), 0, 0, Impostazioni.WIDTH, Impostazioni.HEIGHT, null);
	}
	
	public void gestisciJLabel()
	{
		int dLabel = 80;

		indietro = new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(3)));
		indietro.addMouseListener(new NegozioListener(-1, indietro));
		this.add(indietro);
		
		JLabel l;
		l = new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaTitoloIstruzioni()));
		this.add(l);
		
		this.add(new JLabel());
		
		GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(GameMain.gestoreImmagini.caricaFontNegozio());

		String testo;
		
		testo = "    Muoviti";
		l = new JLabel(testo, new ImageIcon(GameMain.gestoreImmagini.caricaIconeIstruzioni().get(0)),  JLabel.RIGHT);
		l.setFont(GameMain.gestoreImmagini.caricaFontNegozio());
		l.setForeground(Color.WHITE);
		this.add(l);
		
		testo = "<html>    Muovi il Secondo <br> Giocatore </html>";
		l = new JLabel(testo, new ImageIcon(GameMain.gestoreImmagini.caricaIconeIstruzioni().get(3)),  JLabel.CENTER);
		l.setFont(GameMain.gestoreImmagini.caricaFontNegozio());
		l.setForeground(Color.WHITE);
		this.add(l);
		
		ImageIcon icon = new ImageIcon(GameMain.gestoreImmagini.caricaCuoriViaggio().get(0));
		Image img = icon.getImage().getScaledInstance(dLabel, dLabel, Image.SCALE_SMOOTH);
		testo = "    Ricarica vita";
		l = new JLabel(testo, new ImageIcon(img), JLabel.LEFT);
		l.setFont(GameMain.gestoreImmagini.caricaFontNegozio());
		l.setForeground(Color.WHITE);
		this.add(l);
				
		testo = "    Spara";
		l = new JLabel(testo, new ImageIcon(GameMain.gestoreImmagini.caricaIconeIstruzioni().get(1)), JLabel.RIGHT);
		l.setFont(GameMain.gestoreImmagini.caricaFontNegozio());
		l.setForeground(Color.WHITE);
		this.add(l);
		
		testo = "<html> Spara col Secondo <br> Giocatore </html>";
		l = new JLabel(testo, new ImageIcon(GameMain.gestoreImmagini.caricaIconeIstruzioni().get(4)), JLabel.CENTER);
		l.setFont(GameMain.gestoreImmagini.caricaFontNegozio());
		l.setForeground(Color.WHITE);
		this.add(l);
		
		
		testo = "   Colleziona Trofei";
		icon = new ImageIcon(GameMain.gestoreImmagini.caricaTrofeoViaggio());
		img = icon.getImage().getScaledInstance(dLabel, dLabel, Image.SCALE_SMOOTH);
		l = new JLabel(testo, new ImageIcon(img), JLabel.LEFT);
		l.setFont(GameMain.gestoreImmagini.caricaFontNegozio());
		l.setForeground(Color.WHITE);
		this.add(l);

		testo = "    Torna al Menu";
		l = new JLabel(testo, new ImageIcon(GameMain.gestoreImmagini.caricaIconeIstruzioni().get(2)), JLabel.RIGHT);
		l.setFont(GameMain.gestoreImmagini.caricaFontNegozio());
		l.setForeground(Color.WHITE);
		this.add(l);

		this.add(new JLabel());
		
		testo = "   Ottieni coin";
		icon = new ImageIcon(GameMain.gestoreImmagini.caricaCoinViaggio().get(0));
		img = icon.getImage().getScaledInstance(dLabel, dLabel, Image.SCALE_SMOOTH);
		l = new JLabel(testo, new ImageIcon(img), JLabel.LEFT);
		l.setFont(GameMain.gestoreImmagini.caricaFontNegozio());
		l.setForeground(Color.WHITE);
		this.add(l);		
	}
	
}
