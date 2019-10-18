package SpaceBattle.Editor.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import SpaceBattle.Config.Impostazioni;
import SpaceBattle.Main.GameMain;

public class SceltaEditorListener implements MouseListener{

	JLabel label;
	boolean cosaFare; 
	int tipoBlocco;
	
	public SceltaEditorListener(JLabel label,GestoreEditor gestoreEditor, boolean cosaFare, int tipoBlocco)
	{
		this.label = label;
		this.cosaFare = cosaFare;
		this.tipoBlocco = tipoBlocco;
	}
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		if(cosaFare)
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(3)));
		else
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(2)));
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		if(cosaFare)
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(1)));
		else
			label.setIcon(new ImageIcon(GameMain.gestoreImmagini.caricaBottoniEditor().get(0)));
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(cosaFare)
		{
			if(tipoBlocco == 0)
			{
				if(GameMain.gestoreEditor.pavimentoCorrente<Impostazioni.NUMERO_MATTONELLE_EDITOR-1)
				{
					GameMain.gestoreEditor.pavimentoCorrente++;
				}
			}
			else if(tipoBlocco == 1)
			{
				if(GameMain.gestoreEditor.bloccoCorrente<Impostazioni.NUMERO_BLOCCHI_EDITOR-1)
				{
					GameMain.gestoreEditor.bloccoCorrente++;
				}
			}
			else if(tipoBlocco == 2)
			{
				if(GameMain.gestoreEditor.bucoCorrente<Impostazioni.NUMERO_BUCHI_EDITOR-1)
				{
					GameMain.gestoreEditor.bucoCorrente++;
				}
			}
		}
		else
		{
			if(tipoBlocco == 0)
			{
				if(GameMain.gestoreEditor.pavimentoCorrente > 0)
				{
					GameMain.gestoreEditor.pavimentoCorrente--;
				}
			}
			else if(tipoBlocco == 1)
			{
				if(GameMain.gestoreEditor.bloccoCorrente > 0)
				{
					GameMain.gestoreEditor.bloccoCorrente--;
				}
			}
			else if(tipoBlocco == 2)
			{
				if(GameMain.gestoreEditor.bucoCorrente > 0)
				{
					GameMain.gestoreEditor.bucoCorrente--;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
	}

}
