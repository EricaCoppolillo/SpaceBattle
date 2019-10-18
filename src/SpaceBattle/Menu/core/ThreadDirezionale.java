package SpaceBattle.Menu.core;
import java.util.Random;

public class ThreadDirezionale implements Runnable{
    
	private static final int FPS = 800;
	private GestoreMenu m;
	Random r=new Random();
	
	public ThreadDirezionale(GestoreMenu m) {
		this.m = m;
	}
	
	public void run() {
		while(true) {
			
			try {
				int dir1=r.nextInt(4), dir2=r.nextInt(4), dir3=r.nextInt(4);
				
				m.giocatore.setDirezione(dir1);
				
				if(m.gestoreNemici.size()>0)
					m.gestoreNemici.get(0).setDirezione(dir2);
				
				if(m.gestoreNemici.size()>1)
					m.gestoreNemici.get(1).setDirezione(dir3);

				Thread.sleep(FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}