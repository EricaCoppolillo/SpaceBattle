package SpaceBattle.Gui;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpaceBattle.Config.GestoreImmagini;
import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Main.GameMain;
import SpaceBattle.Menu.core.MenuListener;
import SpaceBattle.Menu.core.GestoreMenu;

public class PanelMenu extends JPanel implements Runnable{
	
	
	
	public ArrayList<JLabel> Jlabel;
	public ArrayList<JLabel> laterali;
	public GestoreMenu gestoreMenu; 
	
	public PanelMenu()
	{ 
		GridLayout grid = new GridLayout(Impostazioni.RIGHE_LAYOUT_MENU, Impostazioni.COLONNE_LAYOUT_MENU);
		grid.setHgap(Impostazioni.HGAP_MENU);
		grid.setVgap(Impostazioni.VGAP_MENU);
		this.setLayout(grid);

		Jlabel = new ArrayList<JLabel>();
		laterali = new ArrayList<JLabel>();
		gestoreMenu = new GestoreMenu();
		
		gestisciJLabel();
		
		Thread t = new Thread(this);
		t.start();		
		
		this.addKeyListener(new MenuListener());
		this.setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
				
		disegnaSfondo(g);
		disegnaTitolo(g);
		disegnaNavi(g);		
		disegnaProiettili(g);	
	}
	
	public void disegnaSfondo(Graphics g)
	{
		g.drawImage(GameMain.gestoreImmagini.caricaSfondoMenu(), 0, 0, null);
	}
	
	public void disegnaTitolo(Graphics g)
	{
		g.drawImage(GameMain.gestoreImmagini.caricaTitolo(), Impostazioni.WIDTH/90, Impostazioni.HEIGHT/20, null);
	}
	
	public void disegnaNavi(Graphics g)
	{
		if(gestoreMenu.giocatore.getVita() > Impostazioni.VITA_TERMINATA)
			g.drawImage(GameMain.gestoreImmagini.caricaNaviMenu().get(0), gestoreMenu.giocatore.getX(), gestoreMenu.giocatore.getY(), Impostazioni.DIMGIOCATOREMENU, Impostazioni.DIMGIOCATOREMENU, null);//dim 150x150
		
		for(int i=0; i<gestoreMenu.gestoreNemici.size(); i++)
		{
			if(gestoreMenu.gestoreNemici.get(i).getVita() > Impostazioni.VITA_TERMINATA)
				g.drawImage(GameMain.gestoreImmagini.caricaNaviMenu().get(gestoreMenu.index.get(i)), gestoreMenu.gestoreNemici.get(i).getX(), gestoreMenu.gestoreNemici.get(i).getY(), Impostazioni.DIMNEMICIMENU, Impostazioni.DIMNEMICIMENU, null);//dim 200x200
			
			else
			{
				g.drawImage(GameMain.gestoreImmagini.esplosioni.get(gestoreMenu.gestoreNemici.get(i).getImgEspl()%31), gestoreMenu.gestoreNemici.get(i).getX(), gestoreMenu.gestoreNemici.get(i).getY(), null);
				gestoreMenu.gestoreNemici.get(i).setContEsplosioni(gestoreMenu.gestoreNemici.get(i).getContEsplosioni()+1);
			}
		}	
	}
	
	public void disegnaProiettili(Graphics g)
	{		
		for(int i = 0; i<gestoreMenu.giocatore.pro().size(); i++)
		{
			if(gestoreMenu.giocatore.pro().get(i)!=null)
				g.drawImage(GameMain.gestoreImmagini.caricaProiettiliMenu().get(1), gestoreMenu.giocatore.pro().get(i).getX(), gestoreMenu.giocatore.pro().get(i).getY(), null);
		}
			
		for(int i=0; i<gestoreMenu.gestoreNemici.size(); i++)			
		{
			for(int j=0; j<gestoreMenu.gestoreNemici.get(i).pro().size(); j++)
			{
				if(gestoreMenu.gestoreNemici.get(i).pro().get(j)!=null)	
					g.drawImage(GameMain.gestoreImmagini.caricaProiettiliMenu().get(0), gestoreMenu.gestoreNemici.get(i).pro().get(j).getX(), gestoreMenu.gestoreNemici.get(i).pro().get(j).getY(), null);
			}
		}
	}
	
	
	public void gestisciJLabel()
	{	
		ImageIcon temp;
		for(int i=0; i<6; i++)
			this.add(new JLabel());
		
		for(int i=0; i<6; i++)
		{
			this.add(new JLabel());
			
			temp= new ImageIcon(GameMain.gestoreImmagini.caricaBottoni().get(i));
			Jlabel.add(new JLabel(temp));
			this.add(Jlabel.get(i));		
			Jlabel.get(i).addMouseListener(new MenuListener(i, Jlabel.get(i)));
			
			this.add(new JLabel());
		}

	}
	
	@Override
	public void run() {
		while(true)
		{
			this.repaint();
			try {
				Thread.sleep(Impostazioni.FPS_MENU);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	


}
