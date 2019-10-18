package SpaceBattle.Config;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class GestoreImmagini {

	//attributi Menu
	private Image sfondoMenu;
	private Image titolo;
	private ArrayList<Image> bottoni;
	private ArrayList<Image> bottoniPremuti;
	
	private ArrayList<Image> proiettiliMenu;//sx dx su giu sxSu sxGiu dxSu dxGiu

	//attributi Viaggio
	private Image sfondoViaggio;
	private ArrayList<Image> scritteViaggio;

	private ArrayList<Image> navi;
	private ArrayList<Image> naviGiocatoreColpite;
	private ArrayList<Image> nemici;
	private ArrayList<Image> nemiciColpiti;
	
	private ArrayList<Image> proiettiliViaggio;//sx dx su giu sxSu sxGiu dxSu dxGiu
	private ArrayList<Image> proiettiliGiocatoreViaggio;
	public ArrayList<Image> esplosioni;
	
	private ArrayList<Image> coin;
	private ArrayList<Image> cuori;
	private Image cuoreSecondoGiocatore;
	private Image trofeo;
	private Font font;
	
	//attributi Arena
	private ArrayList<Image> mattonelle;
	private Image sfondoArena;
	private Image morti;
	
	private ArrayList<Image> nave_arena;
	private ArrayList<Image> pro_arena;
	private ArrayList<Image> proNemiciArena;
	private ArrayList<Image> nave_arena_nemico_0;
	private ArrayList<Image> nave_arena_nemico_1;
	private ArrayList<Image> nave_arena_nemico_2;
	private ArrayList<Image> nave_arena_nemico_3;
	private ArrayList<Image> esplosioniArena;
	private ArrayList<Image> bottoniSceltaArena;
	
	//attributi Negozio
	private Image sfondoNegozio;
	private ArrayList<Image> naviNegozio;
	private ArrayList<Image> bottoniNegozio;
	private Image titoloNegozio;
	private Font fontNegozio;
	
	//attributi Editor
	private Image titoloEditor;
	private ArrayList<Image> bottoniEditor;
	private ArrayList<Image> mattonelleEditor;
	private ArrayList<Image> blocchiEditor;
	private ArrayList<Image> buchiEditor;
		
	//attributi Classifica
	private Image titoloClassifica;
	private Image scrittaMigliorPunteggioViaggio;
	private Image scrittaMigliorPunteggioArena;
	private ArrayList<Image> corone;
	
	//attributi Comandi
	private Image titoloComandi;
	private ArrayList<Image> iconeComandi;
	
	public GestoreImmagini()
	{
		bottoni= new ArrayList<Image>();
		bottoniPremuti = new ArrayList<Image>();
		
		proiettiliMenu = new ArrayList<Image>();
		
		scritteViaggio = new ArrayList<Image>();
		navi= new ArrayList<Image>();
		naviGiocatoreColpite = new ArrayList<Image>();
		nemici = new ArrayList<Image>();
		nemiciColpiti = new ArrayList<Image>();
		
		proiettiliViaggio = new ArrayList<Image>();//sx dx su giu sxSu sxGiu dxSu dxGiu
		proiettiliGiocatoreViaggio = new ArrayList<Image>();
		esplosioni = new ArrayList<Image>();
		
		coin = new ArrayList<Image>();
		cuori = new ArrayList<Image>();
		
		mattonelle = new ArrayList<Image>();
	
		pro_arena = new ArrayList<Image>();
		nave_arena = new ArrayList<Image>();
		proNemiciArena = new ArrayList<Image>();
		nave_arena_nemico_0 = new ArrayList<Image>();
		nave_arena_nemico_1 = new ArrayList<Image>();
		nave_arena_nemico_2 = new ArrayList<Image>();
		nave_arena_nemico_3 = new ArrayList<Image>();
		esplosioniArena = new ArrayList<Image>();
		bottoniSceltaArena = new ArrayList<Image>();
		
		naviNegozio = new ArrayList<Image>();
		bottoniNegozio = new ArrayList<Image>();
		
		bottoniEditor = new ArrayList<Image>();
		mattonelleEditor = new ArrayList<Image>();
		blocchiEditor = new ArrayList<Image>();
		buchiEditor = new ArrayList<Image>();
			
		corone = new ArrayList<Image>();
	
		iconeComandi = new ArrayList<Image>();

		caricaImmagini();
	}
	
	public void caricaImmagini()
	{
		try {			

			//esecuzioni funzioni Menu
			aggiungiSfondoMenu();
			aggiungiTitoloMenu();
			aggiungiBottoniMenu();
			aggiungiBottoniPremuti();
			aggiungiProiettiliMenu();
			aggiungiNaviMenu();
			
			//esecuzioni funzioni Viaggio
			aggiungiEsplosioni();
			aggiungiSfondoViaggio();
			aggiungiScritteViaggio();
			aggiungiNemiciViaggio();
			aggiungiProiettiliNemiciViaggio();
			aggiungiProiettiliGiocatoreViaggio();
			aggiungiGiocatoriViaggioColpiti();
			aggiungiCoinViaggio();
			aggiungiCuoriViaggio();
			aggiungiCuoreSecondoGiocatore();
			aggiungiTrofeoViaggio();
			aggiungiFontViaggio();

			//esecuzione funzione Arena
			aggiungiNaviArena();
			aggiungiProiettileNaveBluArena();
			aggiungiProiettiliNemiciArena();
			aggiungiEsplosioniArena();
			aggiungiIconaMorti();
			aggiungiMattonelle();
			aggiungiBottoniSceltaArena();
			
			//esecuzione funzioni Negozio
			aggiungiSfondoNegozio();
			aggiungiNaviNegozio();
			aggiungiBottoniNegozio();
			aggiungiTitoloNegozio();
			aggiungiFontNegozio();
			
			//esecuzione funzioni Editor
			aggiungiTitoloEditor();
			aggiungiMattonelleEditor();
			aggiungiBuchiEditor();
			aggiungiBlocchiEditor();
			aggiungiBottoniEditor();
			
			//esecuzione funzioni Classifica
			aggiungiTitoloClassifica();
			aggiungiScrittaMigliorPunteggioViaggio();
			aggiungiScrittaMigliorArena();
			aggiungiCorone();
			
			//esecuzione funzioni Comandi
			aggiungiTitoloIstruzioni();
			aggiungiIconeIstruzioni();

		} catch (Exception e) {
		}
	}
	
	//funzioni Menu
	public void aggiungiSfondoMenu()
	{
		try {
			sfondoMenu = ImageIO.read(new File("risorse" + File.separator + "sfondi" + File.separator + "sfondo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Image caricaSfondoMenu()
	{
		return sfondoMenu;
	}
	
	public void aggiungiTitoloMenu()
	{
		try {
			titolo=ImageIO.read(new File("risorse" + File.separator+ "titoli" + File.separator + "titolo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Image caricaTitolo()
	{
		return titolo;
	}
	
	public void aggiungiBottoniMenu()
	{
		try {
			bottoni.add(ImageIO.read(new File("risorse" + File.separator + "bottoniMenu" + File.separator + "bottoneViaggio.png")));
			bottoni.add(ImageIO.read(new File("risorse" + File.separator + "bottoniMenu" + File.separator + "bottoneArena.png")));
			bottoni.add(ImageIO.read(new File("risorse" + File.separator + "bottoniMenu" + File.separator + "bottoneNegozio.png")));
			bottoni.add(ImageIO.read(new File("risorse" + File.separator + "bottoniMenu" + File.separator + "bottoneEditor.png")));
			bottoni.add(ImageIO.read(new File("risorse" + File.separator + "bottoniMenu" + File.separator + "bottoneClassifica.png")));
			bottoni.add(ImageIO.read(new File("risorse" + File.separator + "bottoniMenu" + File.separator + "bottoneComandi.png")));
			bottoni.add(ImageIO.read(new File("risorse" + File.separator + "bottoniMenu" + File.separator + "bottoneGiocatoreSingolo.png")));
			bottoni.add(ImageIO.read(new File("risorse" + File.separator + "bottoniMenu" + File.separator + "bottoneMultigiocatore.png")));
			bottoni.add(ImageIO.read(new File("risorse" + File.separator + "bottoniMenu" + File.separator + "bottoneSfida.png")));
			bottoni.add(ImageIO.read(new File("risorse" + File.separator + "bottoniMenu" + File.separator + "bottonePersonalizzata.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaBottoni()
	{
		return bottoni;
	}
	
	public void aggiungiBottoniPremuti()
	{
		try {
			bottoniPremuti.add(ImageIO.read(new File("risorse" + File.separator+ "bottoniPremuti" +File.separator+"bottoneViaggio2.png")));
			bottoniPremuti.add(ImageIO.read(new File("risorse" + File.separator+ "bottoniPremuti" +File.separator+"bottoneArena2.png")));
			bottoniPremuti.add(ImageIO.read(new File("risorse" + File.separator+ "bottoniPremuti" +File.separator+"bottoneNegozio2.png")));
			bottoniPremuti.add(ImageIO.read(new File("risorse" + File.separator+ "bottoniPremuti" +File.separator+"bottoneEditor2.png")));
			bottoniPremuti.add(ImageIO.read(new File("risorse" + File.separator+ "bottoniPremuti" +File.separator+"bottoneClassifica2.png")));
			bottoniPremuti.add(ImageIO.read(new File("risorse" + File.separator+ "bottoniPremuti" +File.separator+"bottoneComandi2.png")));
			
			bottoniPremuti.add(ImageIO.read(new File("risorse" + File.separator+ "bottoniPremuti" +File.separator+"bottoneGiocatoreSingolo2.png")));
			bottoniPremuti.add(ImageIO.read(new File("risorse" + File.separator+ "bottoniPremuti" +File.separator+"bottoneMultigiocatore2.png")));
			//mancano quelli dell'arena
		
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Image> caricaBottoniPremuti()
	{
		return bottoniPremuti;
	}
	
	public void aggiungiProiettiliMenu()
	{
		try {
			proiettiliMenu.add(ImageIO.read(new File("risorse" + File.separator + "proiettili" + File.separator + "proMenuSx.png")));
			proiettiliMenu.add(ImageIO.read(new File("risorse" + File.separator + "proiettili" + File.separator + "proMenuDx.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaProiettiliMenu()
	{
		return proiettiliMenu;
	}
	
	public void aggiungiNaviMenu()
	{
		try {
			for(int i=1; i<6; i++)
				navi.add(ImageIO.read(new File("risorse" + File.separator + "naviMenu" + File.separator + "naveMenu" + i + ".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaNaviMenu()
	{		
		return navi;
	}
	
	
	//funzioni Viaggio
	
	
	public void aggiungiSfondoViaggio()
	{
		try {
			sfondoViaggio = ImageIO.read(new File("risorse" + File.separator + "sfondi" + File.separator+ "sfondoViaggio.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image caricaSfondoViaggio()
	{
		return sfondoViaggio;
	}
	
	public void aggiungiScritteViaggio()
	{
		try {
			for(int i=1; i<4; i++)
				scritteViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "scritteViaggio" +File.separator+"livello"+i+".png")));
			
			scritteViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "scritteViaggio" +File.separator+"livelloBonus.png")));
			scritteViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "scritteViaggio" +File.separator+"pronto.png")));
			scritteViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "scritteViaggio" +File.separator+"vai.png")));
			scritteViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "scritteViaggio" +File.separator+"gameover.png")));
			scritteViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "scritteViaggio" +File.separator+"haivinto.png")));

			scritteViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "scritteViaggio" +File.separator+"premiEsc.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaScritteViaggio()
	{
		return scritteViaggio;
	}
	
	public void aggiungiNemiciViaggio()
	{
		try {
			for(int i=1; i<5; i++)
			{
				nemici.add(ImageIO.read(new File("risorse" + File.separator + "naviNemicheViaggio" + File.separator + "naveNemico" + i + ".png")));
				nemiciColpiti.add(ImageIO.read(new File("risorse" + File.separator + "naviNemicheColpiteViaggio" + File.separator + "naveNemicoColpito" + i + ".png")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaNemiciViaggio()
	{
		return nemici;
	} 
	
	public ArrayList<Image> caricaNemiciColpitiViaggio()
	{
		return nemiciColpiti;
	}
	
	public void aggiungiProiettiliNemiciViaggio()
	{
		try {
			proiettiliViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "proiettili" + File.separator + "proSx.png")));
			proiettiliViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "proiettili" + File.separator + "proDx.png")));
			proiettiliViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "proiettili" + File.separator + "proSu.png")));
			proiettiliViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "proiettili" + File.separator + "proGiu.png")));
			proiettiliViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "proiettili" + File.separator + "proSxSu.png")));
			proiettiliViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "proiettili" + File.separator + "proSxGiu.png")));
			proiettiliViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "proiettili" + File.separator + "proDxSu.png")));
			proiettiliViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "proiettili" + File.separator + "proDxGiu.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaProiettiliNemiciViaggio()
	{		
		return 	proiettiliViaggio;
	}
	
	public void aggiungiProiettiliGiocatoreViaggio()
	{
		try {
			proiettiliGiocatoreViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "proiettili" + File.separator + "proSuGiocatore.png")));
			proiettiliGiocatoreViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "proiettili" + File.separator + "proSxSuGiocatore.png")));
			proiettiliGiocatoreViaggio.add(ImageIO.read(new File("risorse" + File.separator+ "proiettili" + File.separator + "proDxSuGiocatore.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaProiettiliGiocatoreViaggio()
	{
		return proiettiliGiocatoreViaggio;
	}
	
	public void aggiungiGiocatoriViaggioColpiti()
	{
		try {
			naviGiocatoreColpite.add(ImageIO.read(new File("risorse" + File.separator+ "naviColpiteViaggio" + File.separator+ "naveGiocatoreColpito.png")));
			naviGiocatoreColpite.add(ImageIO.read(new File("risorse" + File.separator+ "naviColpiteViaggio" + File.separator+ "naveGiocatoreColpito1.png")));
			naviGiocatoreColpite.add(ImageIO.read(new File("risorse" + File.separator+ "naviColpiteViaggio" + File.separator+ "naveGiocatoreColpito2.png")));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaGiocatoriViaggioColpiti()
	{
		return naviGiocatoreColpite;
	}
	
	public void aggiungiEsplosioni()
	{
		try {
		for(int i=1; i<32; i++)
			esplosioni.add(ImageIO.read(new File("risorse" + File.separator + "esplosioni" + File.separator + "explosion" + i + ".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaEsplosioni()
	{
		return esplosioni;
	}
	
	public void aggiungiCoinViaggio()
	{
		try {
			for(int i=1; i<11; i++)
				coin.add(ImageIO.read(new File("risorse" + File.separator+ "coin" + File.separator + "coin" + i + ".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaCoinViaggio()
	{
		return coin;
	}
	
	public void aggiungiCuoriViaggio()
	{
		try {
			for(int i=1; i<13; i++)
				cuori.add(ImageIO.read(new File("risorse" + File.separator+ "cuori" + File.separator + "heart" + i + ".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaCuoriViaggio()
	{
		return cuori;
	}
	
	public void aggiungiCuoreSecondoGiocatore()
	{
		try {
			cuoreSecondoGiocatore = ImageIO.read(new File("risorse" + File.separator+ "cuori" + File.separator + "heartSecondPlayer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image caricaCuoreSecondoGiocatore()
	{
		return cuoreSecondoGiocatore;
	}
	
	public void aggiungiTrofeoViaggio()
	{
		try {
			trofeo = ImageIO.read(new File("risorse" + File.separator + "trophy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image caricaTrofeoViaggio()
	{
		return trofeo;
	}
	
	public void aggiungiFontViaggio()
	{
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("risorse" + File.separator + "font" + File.separator + "Pixeled.ttf"))).deriveFont(Font.BOLD, 24);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Font caricaFontViaggio()
	{
		return font;
	}
	
	
	//funzioni Arena
	public void aggiungiNaviArena()
	{
		try {
			for(int i = 0; i<4; i++)
			{
				nave_arena.add(ImageIO.read(new File("risorse"+File.separator+"Nave_Blu" + File.separator+ i+".png")));
				nave_arena_nemico_1.add(ImageIO.read(new File("risorse"+File.separator+"Nave_Rossa" + File.separator+ i+".png")));
				nave_arena_nemico_0.add(ImageIO.read(new File("risorse"+File.separator+"Nave_Gialla" + File.separator+ i+".png")));
				nave_arena_nemico_2.add(ImageIO.read(new File("risorse"+File.separator+"Nave_Arancione" + File.separator+ i+".png")));
				nave_arena_nemico_3.add(ImageIO.read(new File("risorse"+File.separator+"Nave_arancio" + File.separator+ i+".png")));
			} 
			}catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaNaveArena()
	{
		return nave_arena;
	}
	
	public ArrayList<Image> caricaNemico0Arena()
	{
		return nave_arena_nemico_0;
	}
	
	public ArrayList<Image> caricaNemico1Arena()
	{
		return nave_arena_nemico_1;
	}
	
	public ArrayList<Image> caricaNemico2Arena()
	{
		return nave_arena_nemico_2;
	}
	
	public ArrayList<Image> caricaNemico3Arena()
	{
		return nave_arena_nemico_3;
	}
	
	public void aggiungiProiettileNaveBluArena()
	{
		for(int i = 0; i<4; i++)
		{
			try {
				pro_arena.add(ImageIO.read(new File("risorse"+File.separator+"pro_blu" + File.separator+ i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Image> caricaProiettileNaveBluArena()
	{
		return pro_arena;
	}
	
	public void aggiungiProiettiliNemiciArena()
	{
		for(int i = 0; i<4; i++)
		{
			try {
				proNemiciArena.add(ImageIO.read(new File("risorse" + File.separator + "proiettiliArena" + File.separator+ i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Image> caricaProiettiliNemiciArena()
	{
		return proNemiciArena;
	}
	
	public void aggiungiEsplosioniArena()
	{
		for(int i = 0; i<Impostazioni.NESPLOSIONIARENA; i++)
		{
			try {
				esplosioniArena.add(ImageIO.read(new File("risorse"+File.separator+"esplosioni"+File.separator+i+ ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Image> caricaEsplosioniArena()
	{
		return esplosioniArena;
	}
	
	public void aggiungiIconaMorti()
	{
		try {
			morti = ImageIO.read(new File("risorse"+File.separator+ "morti.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public Image caricaIconaMorti()
	{
		return morti;
	}
	
	public void aggiungiMattonelle()
	{
		for(int i = 0; i<Impostazioni.N_TILES_ARENA_ORIGINALE; i++)
			try {
				mattonelle.add(ImageIO.read(new File("risorse"+File.separator+"mattonelle"+File.separator+i+ ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public ArrayList<Image> caricaMattonelle()
	{
		return mattonelle;
	}
	
	public void aggiungiBottoniSceltaArena()
	{
		try {
			for(int i = 0; i<Impostazioni.N_BOTTONI_SCELTA_ARENA; i++)
				bottoniSceltaArena.add(ImageIO.read(new File("risorse"+File.separator+"bottoniSceltaArena"+File.separator+i+ ".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaBottoniSceltaArena()
	{
		return bottoniSceltaArena;
	}	
	
	public void aggiungisfondoArena()
	{
		try {
			sfondoArena = ImageIO.read(new File("risorse"+ File.separator + "sfondoArena.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image caricaSfondoArena()
	{
		return sfondoArena;
	}
	
	
	//funzioni Negozio
	public void aggiungiSfondoNegozio()
	{
		try {
			sfondoNegozio = ImageIO.read(new File("risorse" + File.separator + "sfondi" + File.separator + "sfondoNegozio.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image caricaSfondoNegozio()
	{
		return sfondoNegozio;
	}
	
	public void aggiungiTitoloNegozio()
	{
		try {
			titoloNegozio = ImageIO.read(new File("risorse" + File.separator + "titoli" + File.separator + "titoloNegozio.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image caricaTitoloNegozio()
	{
		return titoloNegozio;
	}
	
	public void aggiungiNaviNegozio()
	{
		try {
			naviNegozio.add(ImageIO.read(new File("risorse" + File.separator + "naviNegozio" + File.separator + "naveGiocatore.png")));
			naviNegozio.add(ImageIO.read(new File("risorse" + File.separator + "naviNegozio" + File.separator + "naveGiocatore1.png")));
			naviNegozio.add(ImageIO.read(new File("risorse" + File.separator + "naviNegozio" + File.separator + "naveGiocatore2.png")));
			naviNegozio.add(ImageIO.read(new File("risorse" + File.separator + "naviNegozio" + File.separator + "naveGiocatoreA0.png")));
			naviNegozio.add(ImageIO.read(new File("risorse" + File.separator + "naviNegozio" + File.separator + "naveGiocatoreA1.png")));
			naviNegozio.add(ImageIO.read(new File("risorse" + File.separator + "naviNegozio" + File.separator + "naveGiocatoreA2.png")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaNaviNegozio()
	{
		return naviNegozio;
	}
	
	public void aggiungiBottoniNegozio()
	{
		try {
		for(int i = 1; i<4; i++)	
			bottoniNegozio.add(ImageIO.read(new File("risorse"+File.separator+"bottoniNegozio"+File.separator+"bottoneNegozio"+i+".png")));
			
			bottoniNegozio.add(ImageIO.read(new File("risorse"+File.separator+"bottoniNegozio"+File.separator+"tornaIndietro0.png")));
			bottoniNegozio.add(ImageIO.read(new File("risorse"+File.separator+"bottoniNegozio"+File.separator+"tornaIndietro1.png")));
			bottoniNegozio.add(ImageIO.read(new File("risorse"+File.separator+"bottoniNegozio"+File.separator+"bottoneNegozioSpunta.png")));
			bottoniNegozio.add(ImageIO.read(new File("risorse"+File.separator+"bottoniNegozio"+File.separator+"bottoneNegozioSelezione.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaBottoniNegozio()
	{
		return bottoniNegozio;
	}	
	
	public void aggiungiFontNegozio()
	{
		try {
			fontNegozio = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("risorse" + File.separator + "font" + File.separator + "ROGFONTS-REGULAR.OTF"))).deriveFont(Font.BOLD, 24);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Font caricaFontNegozio()
	{
		return fontNegozio;
	}
	
	
	//funzioni Editor
	public void aggiungiTitoloEditor()
	{
		try {
			titoloEditor = ImageIO.read(new File(("risorse" + File.separator+ "titoli" +File.separator+ "titoloEditor.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image caricaTitoloEditor()
	{
		return titoloEditor;
	}
	
	public void aggiungiMattonelleEditor()
	{
		for(int i = 0; i<Impostazioni.NUMERO_MATTONELLE_EDITOR; i++)
		{
			try {
				mattonelleEditor.add( ImageIO.read(new File("risorse" + File.separator+ "mattonelleEditor"+File.separator+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Image>caricaMattonelleEditor()
	{
		return mattonelleEditor;
	}
	
	public void aggiungiBuchiEditor()
	{
		for(int i = 0; i<Impostazioni.NUMERO_BUCHI_EDITOR; i++)
		{
			try {
				buchiEditor.add( ImageIO.read(new File("risorse" + File.separator+ "buchiEditor"+File.separator+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Image>caricaBuchiEditor()
	{
		return buchiEditor;
	}
	
	public void aggiungiBlocchiEditor()
	{
		for(int i = 0; i<Impostazioni.NUMERO_BLOCCHI_EDITOR; i++)
		{
			try {
				blocchiEditor.add( ImageIO.read(new File("risorse" + File.separator+ "blocchiEditor"+File.separator+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Image>caricaBlocchiEditor()
	{
		return blocchiEditor;
	}	
	
	public void aggiungiBottoniEditor()
	{
		for(int i = 0; i<Impostazioni.NUMERO_BOTTONI_ARENA; i++)
		{
			try {
				bottoniEditor.add(ImageIO.read(new File(("risorse" + File.separator+ "bottoniEditor"+File.separator+i+".png"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Image> caricaBottoniEditor()
	{
		return bottoniEditor;
	}
	
	
	//funzioni Classifica
	public void aggiungiTitoloClassifica()
	{
		try {
			titoloClassifica = ImageIO.read(new File("risorse"+File.separator+ "titoli" + File.separator + "titoloClassifica.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image caricaTitoloClassifica()
	{
		return titoloClassifica;
	}
	
	public void aggiungiScrittaMigliorPunteggioViaggio()
	{
		try {
			scrittaMigliorPunteggioViaggio = ImageIO.read(new File("risorse"+File.separator+"scritteClassifica"+File.separator+"ScrittaBestViaggio.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image caricaScrittaMigliorPunteggioViaggio()
	{
		return scrittaMigliorPunteggioViaggio;
	}
	
	public void aggiungiScrittaMigliorArena()
	{
		try {
			scrittaMigliorPunteggioArena = ImageIO.read(new File("risorse"+File.separator+"scritteClassifica"+File.separator+"ScrittaBestArena.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image caricaScrittaMigliorPunteggioArena()
	{
		return scrittaMigliorPunteggioArena;
	}
	
	public void aggiungiCorone()
	{
		for(int i = 0; i<4; i++)
		{
			try {
				corone.add(ImageIO.read(new File("risorse"+File.separator+"crown"+File.separator+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Image> caricaCorone()
	{
		return corone;
	}
	
	
	//funzioni Comandi
	public void aggiungiTitoloIstruzioni()
	{
		try {
			titoloComandi = ImageIO.read(new File("risorse" + File.separator + "titoli" + File.separator + "titoloComandi.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image caricaTitoloIstruzioni()
	{
		return titoloComandi;
	}
	
	public void aggiungiIconeIstruzioni()
	{
		try {
			iconeComandi.add(ImageIO.read(new File("risorse" + File.separator + "iconeComandi" + File.separator + "icona2.png")));
			iconeComandi.add(ImageIO.read(new File("risorse" + File.separator + "iconeComandi" + File.separator + "icona3.png")));
			iconeComandi.add(ImageIO.read(new File("risorse" + File.separator + "iconeComandi" + File.separator + "icona4.png")));
			iconeComandi.add(ImageIO.read(new File("risorse" + File.separator + "iconeComandi" + File.separator + "icona5.png")));
			iconeComandi.add(ImageIO.read(new File("risorse" + File.separator + "iconeComandi" + File.separator + "icona6.png")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Image> caricaIconeIstruzioni()
	{
		return iconeComandi;
	}
}
