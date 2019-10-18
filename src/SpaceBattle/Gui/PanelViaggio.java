package SpaceBattle.Gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.LogicaCondivisa.core.Proiettile;
import SpaceBattle.Main.GameMain;
import SpaceBattle.Viaggio.core.GestoreViaggio;
import SpaceBattle.Viaggio.core.ViaggioListener;

public class PanelViaggio extends JPanel implements Runnable {
		
	public GestoreViaggio gestoreViaggio;
	
	private int ySfondo;
	public ArrayList<JLabel> label;
	public ArrayList<ImageIcon> icon;
	Proiettile temp;
		
	public PanelViaggio(boolean x)
	{
		gestoreViaggio = new GestoreViaggio(x);
		gestoreViaggio.gestoreSuono.avviaSottofondo();
		
		ySfondo = -Impostazioni.DIM_SFONDO_VIAGGIO;
		label = new ArrayList<JLabel>();
		icon = new ArrayList<ImageIcon>();
		temp = null;
				
		aggiungiJLabel();
	
		Thread t = new Thread(this);
		t.start();
		
		this.addKeyListener(new ViaggioListener(gestoreViaggio));
		this.setLayout(new GridLayout(Impostazioni.RIGHE_PANEL_VIAGGIO, Impostazioni.COLONNE_PANEL_VIAGGIO));
		this.setFocusable(true);
	}
	
	public void aggiungiJLabel()
	{
		ImageIcon iCuore = new ImageIcon(GameMain.gestoreImmagini.caricaCuoriViaggio().get(2));
		ImageIcon iPunti = new ImageIcon(GameMain.gestoreImmagini.caricaTrofeoViaggio());
		ImageIcon iCoin = new ImageIcon(GameMain.gestoreImmagini.caricaCoinViaggio().get(7));
	
		JLabel l;
		
		Image img = iCuore.getImage().getScaledInstance(Impostazioni.DIM_LABEL_VIAGGIO, Impostazioni.DIM_LABEL_VIAGGIO, Image.SCALE_SMOOTH);
		ImageIcon imgScaled = new ImageIcon(img);
		l = new JLabel("" + gestoreViaggio.giocatore.getVita(), imgScaled, JLabel.HORIZONTAL);
		l.setForeground(Color.WHITE);
		l.setFont(GameMain.gestoreImmagini.caricaFontViaggio());
		label.add(l);
			
		img = iPunti.getImage().getScaledInstance(Impostazioni.DIM_LABEL_VIAGGIO, Impostazioni.DIM_LABEL_VIAGGIO, Image.SCALE_SMOOTH);
		imgScaled = new ImageIcon(img);
		l = new JLabel(""+gestoreViaggio.punteggio, imgScaled, JLabel.HORIZONTAL);
		l.setForeground(Color.WHITE);
		l.setFont(GameMain.gestoreImmagini.caricaFontViaggio());
		label.add(l);
		
		img = iCoin.getImage().getScaledInstance(Impostazioni.DIM_LABEL_VIAGGIO, Impostazioni.DIM_LABEL_VIAGGIO, Image.SCALE_SMOOTH);
		imgScaled = new ImageIcon(img);
		l = new JLabel(""+gestoreViaggio.monete, imgScaled, JLabel.HORIZONTAL);
		l.setForeground(Color.WHITE);
		l.setFont(GameMain.gestoreImmagini.caricaFontViaggio());
		label.add(l);
		
		if(gestoreViaggio.secondoGiocatore)
		{
			ImageIcon iCuoreSecondo = new ImageIcon(GameMain.gestoreImmagini.caricaCuoreSecondoGiocatore());
			img = iCuoreSecondo.getImage().getScaledInstance(Impostazioni.DIM_LABEL_VIAGGIO, Impostazioni.DIM_LABEL_VIAGGIO, Image.SCALE_SMOOTH);
			imgScaled = new ImageIcon(img);
			l = new JLabel(""+gestoreViaggio.giocatore2.getVita(), imgScaled, JLabel.HORIZONTAL);
			l.setForeground(Color.WHITE);
			l.setFont(GameMain.gestoreImmagini.caricaFontViaggio());
			label.add(l);
		}
		
		for(int i=0; i<label.size(); i++)
			this.add(label.get(i));
		
		for(int i=0; i<18; i++)
		{
			this.add(new JLabel());	
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		disegnaSfondo(g);
		
		disegnaCoin(g);
		disegnaCuori(g);

		disegnaGiocatore(g);
		disegnaNemici(g);
		disegnaProiettili(g);
		
		disegnaScritte(g);
		
		aggiornaLabel();
	}
	
	public void disegnaScritte(Graphics g)
	{
		if(gestoreViaggio.disegnaScritte)
		{
			g.drawImage(GameMain.gestoreImmagini.caricaScritteViaggio().get(gestoreViaggio.imgScrittaCorr), Impostazioni.WIDTH/2 - GameMain.gestoreImmagini.caricaScritteViaggio().get(gestoreViaggio.imgScrittaCorr).getWidth(null)/2, Impostazioni.HEIGHT/2 - GameMain.gestoreImmagini.caricaScritteViaggio().get(gestoreViaggio.imgScrittaCorr).getWidth(null)/6, null);
			
			if(gestoreViaggio.imgScrittaCorr == 6 || gestoreViaggio.imgScrittaCorr == 7)
				g.drawImage(GameMain.gestoreImmagini.caricaScritteViaggio().get(8), Impostazioni.WIDTH/2 - GameMain.gestoreImmagini.caricaScritteViaggio().get(8).getWidth(null)/2, Impostazioni.HEIGHT/2 - GameMain.gestoreImmagini.caricaScritteViaggio().get(8).getWidth(null)/6 + 80, null);
		}
	}
	
	public void disegnaSfondo(Graphics g)
	{
		g.drawImage(GameMain.gestoreImmagini.caricaSfondoViaggio(), 0, ySfondo, Impostazioni.WIDTH + Impostazioni.DIM_SFONDO_VIAGGIO, Impostazioni.HEIGHT + Impostazioni.DIM_SFONDO_VIAGGIO, null);
	
	 	ySfondo+=2;
		if(ySfondo>=0)
			ySfondo = -Impostazioni.DIM_SFONDO_VIAGGIO;	
	}
	
	public void disegnaGiocatore(Graphics g)
	{
		if(!gestoreViaggio.giocatore.isMorta())
		{
			if(gestoreViaggio.giocatore.getVita() > Impostazioni.VITA_TERMINATA)
			{
				if(!gestoreViaggio.giocatore.isColpita())
					g.drawImage(GameMain.gestoreImmagini.caricaNaviNegozio().get(GameMain.gestoreNegozio.naveSelezionata), gestoreViaggio.giocatore.getX(), gestoreViaggio.giocatore.getY(), gestoreViaggio.giocatore.getLunghezza(), gestoreViaggio.giocatore.getAltezza(),  null);
			
				else
				{
					if(gestoreViaggio.giocatore.getCodice() == 0)
						g.drawImage(GameMain.gestoreImmagini.caricaGiocatoriViaggioColpiti().get(0), gestoreViaggio.giocatore.getX(), gestoreViaggio.giocatore.getY(), gestoreViaggio.giocatore.getLunghezza(), gestoreViaggio.giocatore.getAltezza(), null);
					else if(gestoreViaggio.giocatore.getCodice() == 2)
						g.drawImage(GameMain.gestoreImmagini.caricaGiocatoriViaggioColpiti().get(1), gestoreViaggio.giocatore.getX(), gestoreViaggio.giocatore.getY(), gestoreViaggio.giocatore.getLunghezza(), gestoreViaggio.giocatore.getAltezza(), null);
					else if(gestoreViaggio.giocatore.getCodice() == 4)
						g.drawImage(GameMain.gestoreImmagini.caricaGiocatoriViaggioColpiti().get(2), gestoreViaggio.giocatore.getX(), gestoreViaggio.giocatore.getY(), gestoreViaggio.giocatore.getLunghezza(), gestoreViaggio.giocatore.getAltezza(), null);
				}
			}
			
			else
			{
				g.drawImage(GameMain.gestoreImmagini.esplosioni.get(gestoreViaggio.giocatore.getImgEspl()%31), gestoreViaggio.giocatore.getX(), gestoreViaggio.giocatore.getY(), gestoreViaggio.giocatore.getLunghezza(), gestoreViaggio.giocatore.getAltezza(), null);	
				gestoreViaggio.giocatore.setContEsplosioni(gestoreViaggio.giocatore.getContEsplosioni()+1);;
			}
		}
	
		
		if(gestoreViaggio.secondoGiocatore)
		{
			if(gestoreViaggio.giocatore2.getVita() > Impostazioni.VITA_TERMINATA)
			{
				if(!gestoreViaggio.giocatore2.isColpita())
					g.drawImage(GameMain.gestoreImmagini.caricaNaviNegozio().get(3), gestoreViaggio.giocatore2.getX(), gestoreViaggio.giocatore2.getY(), gestoreViaggio.giocatore2.getLunghezza(), gestoreViaggio.giocatore2.getAltezza(),  null);
			
				else
					g.drawImage(GameMain.gestoreImmagini.caricaGiocatoriViaggioColpiti().get(0), gestoreViaggio.giocatore2.getX(), gestoreViaggio.giocatore2.getY(), gestoreViaggio.giocatore2.getLunghezza(), gestoreViaggio.giocatore2.getAltezza(), null);		
			}
			
			else
			{
				g.drawImage(GameMain.gestoreImmagini.esplosioni.get(gestoreViaggio.giocatore2.getImgEspl()%31), gestoreViaggio.giocatore2.getX(), gestoreViaggio.giocatore2.getY(), gestoreViaggio.giocatore2.getLunghezza(), gestoreViaggio.giocatore2.getAltezza(), null);	
				gestoreViaggio.giocatore2.setContEsplosioni(gestoreViaggio.giocatore2.getContEsplosioni()+1);;
			}
		}
	}
	
	public void disegnaNemici(Graphics g)
	{
		for(int i=0; i<gestoreViaggio.gestoreNemici.size(); i++)
		{
			if(gestoreViaggio.gestoreNemici.get(i)!=null)
			{
				if(gestoreViaggio.gestoreNemici.get(i).getVita() > Impostazioni.VITA_TERMINATA)
				{
					if(!gestoreViaggio.gestoreNemici.get(i).isColpita())
						g.drawImage(GameMain.gestoreImmagini.caricaNemiciViaggio().get(gestoreViaggio.gestoreNemici.get(i).getImgCorrente()), gestoreViaggio.gestoreNemici.get(i).getX(), gestoreViaggio.gestoreNemici.get(i).getY(), gestoreViaggio.gestoreNemici.get(i).getLunghezza(), gestoreViaggio.gestoreNemici.get(i).getAltezza(), null);
					else
						g.drawImage(GameMain.gestoreImmagini.caricaNemiciColpitiViaggio().get(gestoreViaggio.gestoreNemici.get(i).getImgCorrente()), gestoreViaggio.gestoreNemici.get(i).getX(), gestoreViaggio.gestoreNemici.get(i).getY(), gestoreViaggio.gestoreNemici.get(i).getLunghezza(), gestoreViaggio.gestoreNemici.get(i).getAltezza(), null);
				}
				
				else
				{
					g.drawImage(GameMain.gestoreImmagini.esplosioni.get(gestoreViaggio.gestoreNemici.get(i).getImgEspl()%31), gestoreViaggio.gestoreNemici.get(i).getX(), gestoreViaggio.gestoreNemici.get(i).getY(), gestoreViaggio.gestoreNemici.get(i).getLunghezza(), gestoreViaggio.gestoreNemici.get(i).getAltezza(), null);
					gestoreViaggio.gestoreNemici.get(i).setContEsplosioni(gestoreViaggio.gestoreNemici.get(i).getContEsplosioni()+1);;
				}
			}
		}
	}
	
	public void disegnaCoin(Graphics g)
	{
		if(!gestoreViaggio.coin.preso)
			g.drawImage(GameMain.gestoreImmagini.caricaCoinViaggio().get(gestoreViaggio.coin.imgPowerUp%10), gestoreViaggio.coin.getX(), gestoreViaggio.coin.getY(), gestoreViaggio.coin.getLunghezza(), gestoreViaggio.coin.getAltezza(), null);
	}
	
	public void disegnaCuori(Graphics g)
	{
		if(!gestoreViaggio.cuore.preso)
			g.drawImage(GameMain.gestoreImmagini.caricaCuoriViaggio().get(gestoreViaggio.cuore.imgPowerUp%12), gestoreViaggio.cuore.getX(), gestoreViaggio.cuore.getY(), gestoreViaggio.cuore.getLunghezza(), gestoreViaggio.cuore.getAltezza(), null);
	}
	
	public void disegnaProiettili(Graphics g)
	{
		disegnaProGiocatore(g);
		disegnaProNemici(g);
	}

	public void disegnaProGiocatore(Graphics g)
	{
		for(int i=0; i<gestoreViaggio.giocatore.pro().size(); i++)
		{
			if(gestoreViaggio.giocatore.pro().get(i)!=null)
				g.drawImage(GameMain.gestoreImmagini.caricaProiettiliGiocatoreViaggio().get(gestoreViaggio.giocatore.pro().get(i).getDirezione()/2 -1), gestoreViaggio.giocatore.pro().get(i).getX(), gestoreViaggio.giocatore.pro().get(i).getY(), Impostazioni.L_PROP, Impostazioni.A_PROP, null);				
		}
		
		if(gestoreViaggio.secondoGiocatore)
		{
			for(int i=0; i<gestoreViaggio.giocatore2.pro().size(); i++)
			{
				if(gestoreViaggio.giocatore2.pro().get(i)!=null)
					g.drawImage(GameMain.gestoreImmagini.caricaProiettiliGiocatoreViaggio().get(0), gestoreViaggio.giocatore2.pro().get(i).getX(), gestoreViaggio.giocatore2.pro().get(i).getY(), Impostazioni.L_PROP, Impostazioni.A_PROP, null);				
			}
		}
	}
	
	public void disegnaProNemici(Graphics g)
	{
		for(int i=0; i<gestoreViaggio.gestoreNemici.size(); i++)
		{
			for(int j=0; j<gestoreViaggio.gestoreNemici.get(i).pro().size(); j++)
			{
				if(gestoreViaggio.gestoreNemici.get(i).pro().get(j)!=null)
				{
					temp = gestoreViaggio.gestoreNemici.get(i).pro().get(j);
					g.drawImage(GameMain.gestoreImmagini.caricaProiettiliNemiciViaggio().get(temp.getDirezione()), temp.getX(), temp.getY(), null);
				}
			}
		}
	}
	
	public void aggiornaLabel()
	{
		if(gestoreViaggio.giocatore.getVita() > Impostazioni.VITA_TERMINATA)
			label.get(0).setText("" + gestoreViaggio.giocatore.getVita());
		else
			label.get(0).setText("0");
		
		label.get(1).setText("" + gestoreViaggio.punteggio);
		label.get(2).setText("" + gestoreViaggio.monete);
		
		if(label.size() == 4)//sono nella modalità multigiocatore
		{
			if(gestoreViaggio.giocatore2.getVita() > Impostazioni.VITA_TERMINATA)//il secondo giocatore è vivo
				label.get(3).setText("" + gestoreViaggio.giocatore2.getVita());
			else
				label.get(3).setText("0");
		}	
	}

	@Override
	public void run() {
		
		while(true) 
		{
			try {	
				
				this.repaint();
				
				Thread.sleep(Impostazioni.FPS_PANEL_VIAGGIO);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}
}	
