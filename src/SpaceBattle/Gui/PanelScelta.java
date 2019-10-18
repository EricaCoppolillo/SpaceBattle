package SpaceBattle.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Editor.core.EditorListenerSelezionato;
import SpaceBattle.Editor.core.GestoreEditor;
import SpaceBattle.Editor.core.InvioEditorListener;
import SpaceBattle.Editor.core.SceltaEditorListener;
import SpaceBattle.Main.GameMain;

public class PanelScelta extends JPanel implements Runnable{

	ArrayList<JLabel>Jlabel =new ArrayList<JLabel>();
	
	public PanelScelta()
	{
		this.setLayout(new GridLayout(Impostazioni.RIGHE_PANELSCELTA,Impostazioni.COLONNE_PANELSCELTA));
		gestisciJLabel();
		this.setBackground(Color.black);
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
	}
	
	public void gestisciJLabel()
	{
		//prima riga
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(1))));
		Jlabel.get(Jlabel.size()-1).addMouseListener(new SceltaEditorListener(Jlabel.get(Jlabel.size()-1), GameMain.gestoreEditor, true,0));
		this.add(Jlabel.get(Jlabel.size()-1));
		
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(1))));
		Jlabel.get(Jlabel.size()-1).addMouseListener(new SceltaEditorListener(Jlabel.get(Jlabel.size()-1), GameMain.gestoreEditor, true,1));
		this.add(Jlabel.get(Jlabel.size()-1));
		
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(1))));
		Jlabel.get(Jlabel.size()-1).addMouseListener(new SceltaEditorListener(Jlabel.get(Jlabel.size()-1), GameMain.gestoreEditor, true,2));
		this.add(Jlabel.get(Jlabel.size()-1));
		
		
		
		Jlabel.add(new JLabel());
		this.add(Jlabel.get(Jlabel.size()-1));
		//seconda riga
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaMattonelleEditor().get(0))));
		Jlabel.get(Jlabel.size()-1).addMouseListener(new EditorListenerSelezionato(0));
		this.add(Jlabel.get(Jlabel.size()-1));
		
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBlocchiEditor().get(0))));
		Jlabel.get(Jlabel.size()-1).addMouseListener(new EditorListenerSelezionato(1));
		this.add(Jlabel.get(Jlabel.size()-1));
		
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBuchiEditor().get(0))));
		Jlabel.get(Jlabel.size()-1).addMouseListener(new EditorListenerSelezionato(2));
		this.add(Jlabel.get(Jlabel.size()-1));
		
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(4))));
		Jlabel.get(Jlabel.size()-1).addMouseListener(new InvioEditorListener());
		this.add(Jlabel.get(Jlabel.size()-1));
		
		//terza riga
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(0))));
		Jlabel.get(Jlabel.size()-1).addMouseListener(new SceltaEditorListener(Jlabel.get(Jlabel.size()-1), GameMain.gestoreEditor, false,0));
		this.add(Jlabel.get(Jlabel.size()-1));
		
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(0))));
		Jlabel.get(Jlabel.size()-1).addMouseListener(new SceltaEditorListener(Jlabel.get(Jlabel.size()-1),GameMain.gestoreEditor, false,1));
		this.add(Jlabel.get(Jlabel.size()-1));
		
		Jlabel.add(new JLabel(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(0))));
		Jlabel.get(Jlabel.size()-1).addMouseListener(new SceltaEditorListener(Jlabel.get(Jlabel.size()-1), GameMain.gestoreEditor, false,2));
		this.add(Jlabel.get(Jlabel.size()-1));
		
		
		Jlabel.add(new JLabel());
		this.add(Jlabel.get(Jlabel.size()-1));
		
		
		
	}
	
	public void aggiornaScelti() {
		
		Jlabel.get(Impostazioni.LABEL_SCELTA_PAVIMENTO).setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaMattonelleEditor().get(GameMain.gestoreEditor.pavimentoCorrente)));
		Jlabel.get(Impostazioni.LABEL_SCELTA_BLOCCO).setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBlocchiEditor().get(GameMain.gestoreEditor.bloccoCorrente)));
		Jlabel.get(Impostazioni.LABEL_SCELTA_BUCO).setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBuchiEditor().get(GameMain.gestoreEditor.bucoCorrente)));
		
	}

	@Override
	public void run() {
		while(true)
		{
			aggiornaScelti();
			try {
				Thread.sleep(Impostazioni.FPS_PANELSCELTA);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
