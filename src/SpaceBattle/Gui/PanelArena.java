package SpaceBattle.Gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpaceBattle.Arena.core.ArenaListener;
import SpaceBattle.Arena.core.GestoreArena;
import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Main.GameMain;
import SpaceBattle.Menu.core.EscListener;

public class PanelArena extends JPanel implements Runnable
{
	public GestoreArena gestoreArena;
	public ArrayList<JLabel>label = new ArrayList<JLabel>();
	
	public PanelArena(GestoreArena gestoreArena) 
	{
		this.gestoreArena = gestoreArena;
		gestisciLabel();
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new ArenaListener(gestoreArena));
		this.addKeyListener(new EscListener());
		
		new Thread(this).start();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		disegna_Arena(g);		
		disegna_Nave_utente(g);
		disegnaProiettili(g);
		disegnaProiettiliNemici(g);
		disegna_nemici(g);
		disegnaBonus(g);
		disegnaGameOver(g);		
	}
	
	
	public void gestisciLabel()
	{
		this.setLayout(new GridLayout(Impostazioni.RIGHE_LAYOUT_ARENA, Impostazioni.COLONNE_LAYOUT_ARENA));
		for(int i = 0; i<Impostazioni.NUMERO_JLABELS_PANELARENA; i++)
		{
			if(i>=Impostazioni.LABEL_COINS_ARENA)
			{
				if(i==Impostazioni.LABEL_COINS_ARENA)
				{
					label.add(new JLabel(" "+gestoreArena.coin,new ImageIcon(GameMain.gestoreImmagini.caricaCoinViaggio().get(8)),JLabel.HORIZONTAL));
					
				}
				else if(i==Impostazioni.LABEL_VITA_ARENA)
				{
					label.add(new JLabel(" "+gestoreArena.nave_utente.getVita(), new ImageIcon(GameMain.gestoreImmagini.caricaCuoriViaggio().get(2)),JLabel.HORIZONTAL));
				}
				else if(i==Impostazioni.LABEL_MORTI_ARENA)
				{
					label.add(new JLabel(" "+gestoreArena.punteggio, new ImageIcon(GameMain.gestoreImmagini.caricaIconaMorti()),JLabel.HORIZONTAL));
				}
				label.get(i).setForeground(Color.WHITE);
				label.get(i).setFont(GameMain.gestoreImmagini.caricaFontViaggio());
			}	
			else
				label.add(new JLabel());
			

			
			this.add(label.get(i));
		}
	}



	
	public void disegnaBonus(Graphics g)
	{		
		for(int i = 0; i<gestoreArena.powerUps.size(); i++)
		{
			if(gestoreArena.powerUps.get(i).tipo == Impostazioni.CODICE_COIN) 
				g.drawImage(GameMain.gestoreImmagini.caricaCoinViaggio().get(gestoreArena.powerUps.get(i).imgPowerUp), gestoreArena.powerUps.get(i).getX(), gestoreArena.powerUps.get(i).getY(), Impostazioni.DIM_POWERUP_ARENA, Impostazioni.DIM_POWERUP_ARENA, null);
			else
				g.drawImage(GameMain.gestoreImmagini.caricaCuoriViaggio().get(gestoreArena.powerUps.get(i).imgPowerUp), gestoreArena.powerUps.get(i).getX(), gestoreArena.powerUps.get(i).getY(), Impostazioni.DIM_POWERUP_ARENA, Impostazioni.DIM_POWERUP_ARENA, null);
				
		}
		
		
	}
	
	public void disegna_Nave_utente(Graphics g)
	{
		if(gestoreArena.nave_utente.getVita() > Impostazioni.VITA_TERMINATA)
			g.drawImage(GameMain.gestoreImmagini.caricaNaveArena().get(gestoreArena.nave_utente.getDirezione()), gestoreArena.nave_utente.getX(), gestoreArena.nave_utente.getY(),gestoreArena.nave_utente.getAltezza(),gestoreArena.nave_utente.getLunghezza(), null);
		else
			g.drawImage(GameMain.gestoreImmagini.caricaEsplosioniArena().get(gestoreArena.nave_utente.getContEsplosioni()), gestoreArena.nave_utente.getX(), gestoreArena.nave_utente.getY(),gestoreArena.nave_utente.getAltezza(),gestoreArena.nave_utente.getLunghezza(), null);
	}
	
	public void disegnaGameOver(Graphics g)
	{
		if(gestoreArena.nave_utente.getVita() == Impostazioni.VITA_TERMINATA)
		{
			g.drawImage(GameMain.gestoreImmagini.caricaScritteViaggio().get(6), Impostazioni.WIDTH/2-Impostazioni.VALORE_DI_AGGIUSTAMENTO_SCRITTA_GAMEOVER_WIDTH, Impostazioni.HEIGHT/2-Impostazioni.VALORE_DI_AGGIUSTAMENTO_SCRITTA_GAMEOVER_HEIGHT , null);
			g.drawImage(GameMain.gestoreImmagini.caricaScritteViaggio().get(8), Impostazioni.WIDTH/2-Impostazioni.VALORE_DI_AGGIUSTAMENTO_SCRITTA_PREMIESC_WIDTH, Impostazioni.HEIGHT/2-Impostazioni.VALORE_DI_AGGIUSTAMENTO_SCRITTA_PREMIESC_HEIGHT , null);
			
		}
	}
	
	public void disegna_nemici(Graphics g)
	{
		for(int i = 0; i<gestoreArena.gestoreNemici.size(); i++)
		{
			if(gestoreArena.gestoreNemici.get(i).getVita() > Impostazioni.VITA_TERMINATA)
			{
			if(gestoreArena.gestoreNemici.get(i).getCodice() == 0 )
				g.drawImage(GameMain.gestoreImmagini.caricaNemico0Arena().get(gestoreArena.gestoreNemici.get(i).getDirezione()), gestoreArena.gestoreNemici.get(i).getX(),gestoreArena.gestoreNemici.get(i).getY() ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA,null);
			else if(gestoreArena.gestoreNemici.get(i).getCodice() == 1 )
				g.drawImage(GameMain.gestoreImmagini.caricaNemico1Arena().get(gestoreArena.gestoreNemici.get(i).getDirezione()), gestoreArena.gestoreNemici.get(i).getX(),gestoreArena.gestoreNemici.get(i).getY() ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA,null);
			else if(gestoreArena.gestoreNemici.get(i).getCodice() == 2 )
				g.drawImage(GameMain.gestoreImmagini.caricaNemico2Arena().get(gestoreArena.gestoreNemici.get(i).getDirezione()), gestoreArena.gestoreNemici.get(i).getX(),gestoreArena.gestoreNemici.get(i).getY() ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA,null);
			else if(gestoreArena.gestoreNemici.get(i).getCodice() == 3 )
				g.drawImage(GameMain.gestoreImmagini.caricaNemico3Arena().get(gestoreArena.gestoreNemici.get(i).getDirezione()), gestoreArena.gestoreNemici.get(i).getX(),gestoreArena.gestoreNemici.get(i).getY() ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA,null);
		
			}
			else
			{
				g.drawImage(GameMain.gestoreImmagini.caricaEsplosioniArena().get(gestoreArena.gestoreNemici.get(i).getImgEspl()%Impostazioni.NESPLOSIONIARENA), gestoreArena.gestoreNemici.get(i).getX(), gestoreArena.gestoreNemici.get(i).getY(), Impostazioni.DIM_BLOCCO_ARENA, Impostazioni.DIM_BLOCCO_ARENA, null);
				gestoreArena.gestoreNemici.get(i).setContEsplosioni(gestoreArena.gestoreNemici.get(i).getContEsplosioni()+1);
			}
		}
	}
	
	
	public void disegna_Arena(Graphics g)
	{
		
		for(int i = 0; i<gestoreArena.matrix.righe; i++)
		{
			for(int j = 0; j<gestoreArena.matrix.colonne; j++)
			{
				if(gestoreArena.matrix.get(i, j).numero == Impostazioni.BLOCCO_TESCHIO)
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.BLOCCO_TESCHIO), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i, j).numero==Impostazioni.PAVIMENTO_SABBIOSO) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.PAVIMENTO_SABBIOSO), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i,j).numero==Impostazioni.BLOCCO_FERRO) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.BLOCCO_FERRO), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i, j).numero == Impostazioni.BUCO_ARENA_SFIDA) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.BUCO_ARENA_SFIDA), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i, j).numero == Impostazioni.BLOCCO_ARENA_SFIDA) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.BLOCCO_ARENA_SFIDA), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i, j).numero == Impostazioni.BLOCCO_CELESTE) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.BLOCCO_CELESTE), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i, j).numero == Impostazioni.PAVIMENTO_GRAY) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.PAVIMENTO_GRAY), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i, j).numero == Impostazioni.BLOCCO_GHIACCIO) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.BLOCCO_GHIACCIO), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i, j).numero == Impostazioni.BUCO_ERBA) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.BUCO_ERBA), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i, j).numero == Impostazioni.PAVIMENTO_ERBA) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.PAVIMENTO_ERBA), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i, j).numero == Impostazioni.PAVIMENTO_AMBRA) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.PAVIMENTO_AMBRA), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i, j).numero == Impostazioni.BUCO_CASTELLO) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.BUCO_CASTELLO), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
				else if(gestoreArena.matrix.get(i, j).numero == Impostazioni.BUCO_STANDARD) 
				{
					g.drawImage(GameMain.gestoreImmagini.caricaMattonelle().get(Impostazioni.BUCO_STANDARD), j*Impostazioni.DIM_BLOCCO_ARENA, i*Impostazioni.DIM_BLOCCO_ARENA ,Impostazioni.DIM_BLOCCO_ARENA,Impostazioni.DIM_BLOCCO_ARENA, null);
				}
					
					
			}
		}
	}
	

	
	public void disegnaProiettili(Graphics g)
	{
		for(int i = 0; i<gestoreArena.nave_utente.pro().size(); i++)
		{
			if(gestoreArena.nave_utente.pro().get(i)!=null)
			{
				switch(gestoreArena.nave_utente.pro().get(i).getDirezione())
				{
					case Impostazioni.PROIETTILE_SINISTRA:
						g.drawImage(GameMain.gestoreImmagini.caricaProiettileNaveBluArena().get(0),gestoreArena.nave_utente.pro().get(i).getX(),gestoreArena.nave_utente.pro().get(i).getY(),gestoreArena.nave_utente.pro().get(i).getLunghezza(),gestoreArena.nave_utente.pro().get(i).getAltezza(),null);
						break;
					case Impostazioni.PROIETTILE_DESTRA:
						g.drawImage(GameMain.gestoreImmagini.caricaProiettileNaveBluArena().get(0),gestoreArena.nave_utente.pro().get(i).getX(),gestoreArena.nave_utente.pro().get(i).getY(),gestoreArena.nave_utente.pro().get(i).getLunghezza(),gestoreArena.nave_utente.pro().get(i).getAltezza(),null);
						break;
					case Impostazioni.PROIETTILE_SOPRA:
						g.drawImage(GameMain.gestoreImmagini.caricaProiettileNaveBluArena().get(0),gestoreArena.nave_utente.pro().get(i).getX(),gestoreArena.nave_utente.pro().get(i).getY(),gestoreArena.nave_utente.pro().get(i).getLunghezza(),gestoreArena.nave_utente.pro().get(i).getAltezza(),null);
						break;
					case Impostazioni.PROIETTILE_SOTTO:
						g.drawImage(GameMain.gestoreImmagini.caricaProiettileNaveBluArena().get(0),gestoreArena.nave_utente.pro().get(i).getX(),gestoreArena.nave_utente.pro().get(i).getY(),gestoreArena.nave_utente.pro().get(i).getLunghezza(),gestoreArena.nave_utente.pro().get(i).getAltezza(),null);
						break;	
					
				}	
			}
		}
			
	}
	
	public void disegnaProiettiliNemici(Graphics g)
	{
		for(int i = 0; i<gestoreArena.gestoreNemici.size(); i++)
		{
			for(int j = 0; j<gestoreArena.gestoreNemici.get(i).pro().size(); j++)
			{
				if(gestoreArena.gestoreNemici.get(i).pro().get(j)!=null)
				{
					switch(gestoreArena.gestoreNemici.get(i).pro().get(j).getDirezione())
					{
						case Impostazioni.PROIETTILE_SINISTRA:
							g.drawImage(GameMain.gestoreImmagini.caricaProiettiliNemiciArena().get(gestoreArena.gestoreNemici.get(i).getCodice()),gestoreArena.gestoreNemici.get(i).pro().get(j).getX(),gestoreArena.gestoreNemici.get(i).pro().get(j).getY(),gestoreArena.gestoreNemici.get(i).pro().get(j).getLunghezza(),gestoreArena.gestoreNemici.get(i).pro().get(j).getAltezza(),null);
							break;
						case Impostazioni.PROIETTILE_DESTRA:
							g.drawImage(GameMain.gestoreImmagini.caricaProiettiliNemiciArena().get(gestoreArena.gestoreNemici.get(i).getCodice()),gestoreArena.gestoreNemici.get(i).pro().get(j).getX(),gestoreArena.gestoreNemici.get(i).pro().get(j).getY(),gestoreArena.gestoreNemici.get(i).pro().get(j).getLunghezza(),gestoreArena.gestoreNemici.get(i).pro().get(j).getAltezza(),null);
							break;
						case Impostazioni.PROIETTILE_SOPRA:
							g.drawImage(GameMain.gestoreImmagini.caricaProiettiliNemiciArena().get(gestoreArena.gestoreNemici.get(i).getCodice()),gestoreArena.gestoreNemici.get(i).pro().get(j).getX(),gestoreArena.gestoreNemici.get(i).pro().get(j).getY(),gestoreArena.gestoreNemici.get(i).pro().get(j).getLunghezza(),gestoreArena.gestoreNemici.get(i).pro().get(j).getAltezza(),null);						
							break;
						case Impostazioni.PROIETTILE_SOTTO:
							g.drawImage(GameMain.gestoreImmagini.caricaProiettiliNemiciArena().get(gestoreArena.gestoreNemici.get(i).getCodice()),gestoreArena.gestoreNemici.get(i).pro().get(j).getX(),gestoreArena.gestoreNemici.get(i).pro().get(j).getY(),gestoreArena.gestoreNemici.get(i).pro().get(j).getLunghezza(),gestoreArena.gestoreNemici.get(i).pro().get(j).getAltezza(),null);						
							break;	
					}
				}
			}
		}
	}
	
	
	public void aggiornaLabelMorti()
	{
		label.get(Impostazioni.LABEL_MORTI_ARENA).setText(""+gestoreArena.punteggio);
	}
	
	public void aggiornaLabelVita()
	{
		label.get(Impostazioni.LABEL_VITA_ARENA).setText(""+gestoreArena.nave_utente.getVita());
	}
	
	public void aggiornaLabelCoin()
	{
		label.get(Impostazioni.LABEL_COINS_ARENA).setText(""+gestoreArena.coin);
	}
	
	
	





	@Override
	public void run() {
		while(true)
		{
			this.repaint();
			aggiornaLabelMorti();
			aggiornaLabelCoin();
			aggiornaLabelVita();
			try {
				Thread.sleep(Impostazioni.FPS_PANEL_ARENA);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	


	
	
	
	
	
}
