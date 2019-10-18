package SpaceBattle.Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import SpaceBattle.Classifica.core.GestoreClassifica;
import SpaceBattle.Config.GestoreImmagini;
import SpaceBattle.Editor.core.GestoreEditor;
import SpaceBattle.Gui.PanelMenu;
import SpaceBattle.LogicaCondivisa.core.Archivio;
import SpaceBattle.Negozio.core.GestoreNegozio;

public class GameMain 
{
	public static JFrame gameFrame = new JFrame();
	public static Archivio archivio = new Archivio();
	public static GestoreEditor gestoreEditor = new GestoreEditor();
	public static GestoreImmagini gestoreImmagini = new GestoreImmagini(); 
	public static GestoreNegozio gestoreNegozio = new GestoreNegozio();
	public static GestoreClassifica gestoreClassifica = new GestoreClassifica();
	
	public static JPanel contenitore = new JPanel();
		
	public static void main(String[] args) {
		
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		gameFrame.setSize(dim.width, dim.height);
		gameFrame.setLocation(dim.width/2-gameFrame.getSize().width/2, dim.height/2-gameFrame.getSize().height/2);
		gameFrame.setUndecorated(true);  
		
		gameFrame.add(contenitore);
		contenitore.setLayout(new BorderLayout());
		PanelMenu menu = new PanelMenu();
		contenitore.add(menu);
		contenitore.setFocusable(true);
		gameFrame.setVisible(true); //va alla fine se ci va il fullscreen
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.requestFocus();
	}
}
