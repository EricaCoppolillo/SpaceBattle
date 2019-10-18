package SpaceBattle.Config;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Impostazioni {

	
	//Impostazioni Nave
	public static final int FPS_NAVE = 60;
	
	//Impostazioni schermo
	static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	public final static int WIDTH= dim.width;
	public final static int HEIGHT= dim.height;
	
	//Impostazioni Menu
	public static final int FPS_VIAGGIO_LISTENER = 60;
	public static final int NUMERO_NEMICI_MENU = 2;
	public static final int FPS_MENU= 60;
	public final static int RIGHE_LAYOUT_MENU = 8;
	public final static int COLONNE_LAYOUT_MENU = 3;
	public final static int HGAP_MENU = 425;
	public final static int VGAP_MENU = 50;
	public final static int NESPLOSIONIMENU = 31;
	public final static int MIN = 10;
	public final static int FPS_THREAD_MENU = 1500;
	public final static int VITA_NEMICI_MENU = 1;
	
	public final static int DIMGIOCATOREMENU = 150;
	public final static int DIMNEMICIMENU = 200;
	
	//Impostazioni Viaggio
	public final static int DIM_SFONDO_VIAGGIO = 1000;
	public final static int DIM_LABEL_VIAGGIO = 60;
	public final static int FPS_PANEL_VIAGGIO = 60;
	public final static int FPS_GESTORE_VIAGGIO = 80;
	
	public final static int RIGHE_PANEL_VIAGGIO = 7;
	public final static int COLONNE_PANEL_VIAGGIO = 3;
	
	public final static int DIM_CUORE = 35;
	public final static int DIM_COIN = 35;
	public final static int POS_CUORE = -45;
	public final static int POS_COIN = -45;
	
	public final static int DIM_GIOCATORE_P0 = 100;
	public final static int DIM_GIOCATORE_P1 = 150;
	public final static int DIM_GIOCATORE_P2 = 200;

	public final static int DIM_NEMICO_P0 = 100;
	public final static int DIM_NEMICO_P1 = 130;
	public final static int DIM_NEMICO_P2 = 150;
	public final static int DIM_NEMICO_P3 = 180;
	public final static int DIM_NEMICO_P4 = 210;
	
	public final static int L_PROP = 20;
	public final static int A_PROP = 25;
	
	//Impostazioni PanelSceltaViaggio
	public final static int RIGHE_LAYOUT_PANEL_SCELTA_VIAGGIO = 4;
	public final static int COLONNE_LAYOUT_PANEL_SCELTA_VIAGGIO = 3;
	
	
	//Impostazioni PanelSceltaArena
	public final static int RIGHE_LAYOUT_PANEL_SCELTA_ARENA = 3;
	public final static int COLONNE_LAYOUT_PANEL_SCELTA_ARENA = 3;
	public final static int LABEL_VUOTE_SCELTA_ARENA = 4;
	
	
	//Impostazioni Arena
	public final static int CODICE_COIN = 0;
	public final static int CODICE_CUORE = 1;
	public final static int PUNTEGGIO_CAMBIO_LIVELLO_ARENA = 50;
	public final static int DANNO_NEMICI_ARENA = 20;
	public final static int POTENZA_NEMICI_ARENA = 20;
	public final static int VITA_NEMICI_ARENA = 1;
	public final static int FPS_ARENA = 20;
	public final static int RIGHE_MATRICE = 16;
	public final static int COLONNE_MATRICE = 32;	
	public final static int DIMPROIETTILI = 20;
	public final static int NESPLOSIONIARENA = 26;
	public final static int DIM_BLOCCO_ARENA = 60;
	public final static int N_TILES_ARENA_ORIGINALE = 13;
	public final static int N_BOTTONI_SCELTA_ARENA = 4;
	public final static int INIZIO_ARENA_X = 0;
	public final static int INIZIO_ARENA_Y = 0;
	public static final int DIM_POWERUP_ARENA = 30;
	public final static int NUMERO_IMMAGINI_COINS_ARENA = 10;
	public final static int NUMERO_IMMAGINI_CUORI_ARENA = 12;
	public final static int DESTRA = 0;
	public final static int SINISTRA = 2;
	public final static int SOPRA = 1;
	public final static int SOTTO = 3;
	public final static int SPAWN_NAVE_UTENTE_ARENA_X = 900;
	public final static int SPAWN_NAVE_UTENTE_ARENA_Y = 450;
	public final static int VALORE_DI_AGGIUSTAMENTO_SCRITTA_GAMEOVER_WIDTH = 530;
	public final static int VALORE_DI_AGGIUSTAMENTO_SCRITTA_GAMEOVER_HEIGHT = 260;
	public final static int VALORE_DI_AGGIUSTAMENTO_SCRITTA_PREMIESC_WIDTH = 240;
	public final static int VALORE_DI_AGGIUSTAMENTO_SCRITTA_PREMIESC_HEIGHT = 110;
	public final static int FATTORE_RIPRODUZIONE_ESPLOSIONI = 2;
	public final static int FATTORE_RIPRODUZIONE_SPARO_NEMICI = 10;
	public final static int FATTORE_AGGIORNAMENTO_PROIETTILI_NEMICI = 2;
	
	public final static int BUCO_STANDARD = 12;
	public final static int PAVIMENTO_SABBIOSO = 1;
	public final static int PAVIMENTO_GRAY = 6;
	public final static int PAVIMENTO_ERBA = 9;
	public final static int PAVIMENTO_AMBRA = 10;
	public final static int BUCO_ARENA_SFIDA = 3;
	public final static int BUCO_ERBA = 8;
	public final static int BUCO_CASTELLO = 11;
	public final static int BLOCCO_TESCHIO = 0 ;
	public final static int BLOCCO_FERRO = 2;
	public final static int BLOCCO_ARENA_SFIDA = 4;
	public final static int BLOCCO_CELESTE = 5;
	public final static int BLOCCO_GHIACCIO = 7;
	
	public final static int VITA_TERMINATA = 0;
	
	public final static int PROIETTILE_SINISTRA = 0;
	public final static int PROIETTILE_DESTRA = 1;
	public final static int PROIETTILE_SOPRA = 2;
	public final static int PROIETTILE_SOTTO = 3;
	public final static int PROIETTILE_SX_SU = 4;
	public final static int PROIETTILE_SX_GIU = 5;
	public final static int PROIETTILE_DX_SU = 6;
	public final static int PROIETTILE_DX_GIU = 7;
	
	
	public final static int FPS_PANEL_ARENA = 30;
	public final static int LABEL_COINS_ARENA = 27;
	public final static int LABEL_VITA_ARENA = 28;
	public final static int LABEL_MORTI_ARENA = 29;
	
	public final static int FPS_ARENA_LISTENER = 60;
	public final static int RIGHE_LAYOUT_ARENA = 10;
	public final static int COLONNE_LAYOUT_ARENA = 7;
	public final static int NUMERO_JLABELS_PANELARENA = 30;
	
	public final static int LABEL_TORNA_MENU = 1;
	public final static int LABEL_ARENA_SFIDA = 2;
	public final static int LABEL_ARENA_PERSONALIZZATA = 3;
	public final static int TIPOLOGIE_NAVI_ARENA = 4;
	
	public final static int NUMERO_BLOCCHI_MASSIMO = 450;
	public final static int NUMERO_BUCHI_MINIMO = 8;
	public final static int NUMERO_PAVIMENTO_MASSIMO = 504;
	
	public static int NUMERO_NEMICI_ARENA = 6;
	public static int VITA_NAVE_UTENTE_ARENA = 10;
	public static int VELOCITA_NAVE_UTENTE_ARENA = 10;
	public static int DANNO_NAVE_UTENTE_ARENA = 10;
	public static int POTENZA_NAVE_UTENTE_ARENA = 10;
	
	//Impostazioni Negozio
	public static final int FPS_NEGOZIO = 100;
	public static final int RIGHE_LAYOUT_NEGOZIO = 6;
	public static final int COLONNE_LAYOUT_NEGOZIO = 3;
	

	
	//ImpostazioniEditor
	public final static int NUMERO_BOTTONI_ARENA = 7;
	public final static int NUMERO_MATTONELLE_EDITOR = 4;
	public final static int NUMERO_BUCHI_EDITOR = 4;
	public final static int NUMERO_BLOCCHI_EDITOR = 5;
	public final static int LOCAZIONE_DIVIDER_EDITOR = 100;
	
	//ImpostazioniPanelTitoloEditor
	public final static int RIGHE_LAYOUT_PANEL_TITOLO_EDITOR = 1;
	public final static int COLONNE_LAYOUT_PANEL_TITOLO_EDITOR = 3;

	//Impostazioni PanelScelta
	public static final int RIGHE_PANELSCELTA = 3;
	public static final int COLONNE_PANELSCELTA = 4;
	public static final int FPS_PANELSCELTA = 60;
	public static final int LABEL_SCELTA_PAVIMENTO = 4;
	public static final int LABEL_SCELTA_BLOCCO = 5;
	public static final int LABEL_SCELTA_BUCO = 6;
	
	//Impostazioni PanelGriglia
	public final static int  RIGHE_PANELGRIGLIA = 16;
	public final static int COLONNE_PANELGRIGLIA = 46;
	public final static int  CONTATORE_PANELGRIGLIA = 0;
	
	//Impostazioni PanelModificatore
	public final static int LOCAZIONE_DIVIDER_MODIFICATORE = 720;
	public final static int SIZE_DIVIDER_MODIFICATORE = 0;
	
	//Impostazioni classifica
	public final static int RIGHE_LAYOUT_PANELCLASSIFICA = 7;
	public final static int COLONNE_LAYOUT_PANELCLASSIFICA = 7;
	
	//Impostazioni Comandi 
	public final static int RIGHE_LAYOUT_PANELCOMANDI = 4;
	public final static int COLONNE_LAYOUT_PANELCOMANDI = 3;
	
	
	

	
	
}
