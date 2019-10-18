package SpaceBattle.Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Main.GameMain;
import SpaceBattle.Menu.core.EscListener;
import SpaceBattle.Negozio.core.NegozioListener;

public class PanelNegozio extends JPanel implements Runnable{
	
	
	private ArrayList<JLabel> label;
	public ArrayList<JLabel> bottoni;
	private JLabel labelCoin;
	
	
	public PanelNegozio()
	{
		label = new ArrayList<JLabel>();
		bottoni = new ArrayList<JLabel>();
		
		this.setLayout(new GridLayout(Impostazioni.RIGHE_LAYOUT_NEGOZIO, Impostazioni.COLONNE_LAYOUT_NEGOZIO));
		gestisciJLabel();
		settaBottoneLabel();

		this.addKeyListener(new EscListener());
		this.setFocusable(true);
		Thread t = new Thread(this);
		t.start();
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
		JLabel l;

		l = new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(3)));
		l.addMouseListener(new NegozioListener(-1, l));
		this.add(l);

		this.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaTitoloNegozio())));
	

		ImageIcon icon = new ImageIcon(GameMain.gestoreImmagini.caricaCoinViaggio().get(2));
		labelCoin = new JLabel(GameMain.gestoreNegozio.ottieniCoin(), icon, JLabel.HORIZONTAL);
		this.add(labelCoin);
		labelCoin.setFont(GameMain.gestoreImmagini.caricaFontViaggio());
		labelCoin.setForeground(Color.WHITE);

		GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(GameMain.gestoreImmagini.caricaFontNegozio());
		
		for(int i=0; i<2; i++)
		{
			String nave1 = "<html>VELOCITA : 15 <Br> DANNO : 1 </html>";
			String nave2 = "<html>VELOCITA : 25 <Br> DANNO : 11 </html>";
			String nave3 = "<html>VELOCITA : 35 <Br> DANNO : 21 </html>";
			
			if(i == 0)
				label.add(new JLabel(nave1, new ImageIcon(GameMain.gestoreImmagini.caricaNaviNegozio().get(0)), JLabel.HORIZONTAL)); //immagine della navicella
			
			if(i == 1)
				label.add(new JLabel(nave1, new ImageIcon(GameMain.gestoreImmagini.caricaNaviNegozio().get(3)), JLabel.HORIZONTAL)); //immagine della navicella

			label.get(label.size()-1).setFont(GameMain.gestoreImmagini.caricaFontNegozio());
			label.get(label.size()-1).setForeground(Color.WHITE);
			this.add(label.get(label.size()-1));
			
		
			if(i == 0)
				label.add(new JLabel(nave2, new ImageIcon(GameMain.gestoreImmagini.caricaNaviNegozio().get(1)), JLabel.HORIZONTAL)); //immagine della navicella; //immagine della navicella
			
			if(i == 1)
				label.add(new JLabel(nave2, new ImageIcon(GameMain.gestoreImmagini.caricaNaviNegozio().get(4)), JLabel.HORIZONTAL)); //immagine della navicella; //immagine della navicella

			label.get(label.size()-1).setFont(GameMain.gestoreImmagini.caricaFontNegozio());
			label.get(label.size()-1).setForeground(Color.WHITE);
			this.add(label.get(label.size()-1));
			
			if(i == 0)
				label.add(new JLabel(nave3, new ImageIcon(GameMain.gestoreImmagini.caricaNaviNegozio().get(2)), JLabel.HORIZONTAL)); //immagine della navicella
			
			if(i == 1)
				label.add(new JLabel(nave3, new ImageIcon(GameMain.gestoreImmagini.caricaNaviNegozio().get(5)), JLabel.HORIZONTAL)); //immagine della navicella
				
			label.get(label.size()-1).setFont(GameMain.gestoreImmagini.caricaFontNegozio());
			label.get(label.size()-1).setForeground(Color.WHITE);
			this.add(label.get(label.size()-1));

			bottoni.add(new JLabel("300", new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(0)), JLabel.HORIZONTAL)); 
			bottoni.get(bottoni.size()-1).setFont(new Font(Font.SERIF, Font.BOLD, 0));
			bottoni.get(bottoni.size()-1).addMouseListener(new NegozioListener(i*3, bottoni.get(bottoni.size()-1)));
			this.add(bottoni.get(bottoni.size()-1));
			
			bottoni.add(new JLabel("600", new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(1)), JLabel.HORIZONTAL)); 
			bottoni.get(bottoni.size()-1).setFont(new Font(Font.SERIF, Font.BOLD, 0));
			bottoni.get(bottoni.size()-1).addMouseListener(new NegozioListener(i*3+1, bottoni.get(bottoni.size()-1)));
			this.add(bottoni.get(bottoni.size()-1));
			
			bottoni.add(new JLabel("800", new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(2)), JLabel.HORIZONTAL)); 
			bottoni.get(bottoni.size()-1).setFont(new Font(Font.SERIF, Font.BOLD, 0));
			bottoni.get(bottoni.size()-1).addMouseListener(new NegozioListener(i*3+2, bottoni.get(bottoni.size()-1)));
			this.add(bottoni.get(bottoni.size()-1));

			if(i==0)
			{
				this.add(new JLabel());
				this.add(new JLabel());
				this.add(new JLabel());
			}		
		}
	}
	
	public void settaBottoneLabel()
	{
		for(int i=0; i<bottoni.size(); i++)
		{
			if(GameMain.gestoreNegozio.getValore(i).equals("1"))
				bottoni.get(i).setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(5)));
	
			if(GameMain.gestoreNegozio.getValore(i).equals("2"))
				bottoni.get(i).setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(6)));
		}
	}
	
	public void settaDue()
	{
		for(int j=0; j<bottoni.size(); j++)
		{
			if(j == GameMain.gestoreNegozio.naveSelezionata)
				GameMain.gestoreNegozio.modificaValore(j, "2");

			else if(GameMain.gestoreNegozio.getValore(j).equals("2") && j!=GameMain.gestoreNegozio.naveSelezionata)
			{
				GameMain.gestoreNegozio.modificaValore(j, "1");
				bottoni.get(j).setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniNegozio().get(5)));
			}
		}
	}
	
	@Override
	public void run() {
		while(true)
		{
			labelCoin.setText(GameMain.gestoreNegozio.ottieniCoin());
			
			settaDue();
			
			
			try {
				Thread.sleep(Impostazioni.FPS_NEGOZIO);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
