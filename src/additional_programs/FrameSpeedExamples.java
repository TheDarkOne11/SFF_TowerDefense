package additional_programs;

public class FrameSpeedExamples {
	
	public FrameSpeedExamples() {
		new ConstantIndependentFrameRate().run();
	}
	
	public static void main(String[] args) {
		new FrameSpeedExamples();
	}
	
	/*
	 *  Mnohem slo�it�j��, hra jede po��d na 25 framech za sekundu, ale zobrazov�n� se m��e d�t i mezi updaty,
	 *  jeliko� ve h�e mus� b�t zakomponov�no p�edv�d�n�.
	 *  Vizu�ln� lep�� ne� konstantn� rychlost fram�.
	 *  
	 *  P�edv�d�n�:
	 *  Auto jede rychlost� 100.
	 *  V 5. updatu je ve vzd�lenosti 500. Proto v 11. bude ve vzd�lenosti 600.
	 *  Mohu to zobrazit uprost�ed updat�, proto prom�nn� interpolation m� hodnotu 0,5.
	 *  Proto jeho pozice v tomto �ase by byla 550(speed*interpolation).
	 */
	
	class ConstantIndependentFrameRate implements Runnable {
		Thread thread = new Thread(this);
		
		public void run() {
			int updatesPerSec = 25;
			int skipppedTics = 1000/updatesPerSec;
			int frameSkipMAX = 5;
			int loops;
			float interpolation = 0;	// Value between frameUpdates
			long nextGameTick = System.currentTimeMillis();
			boolean running = true;
			
			while(running) {
				loops = 0;
				
				while(System.currentTimeMillis() >= nextGameTick && loops < frameSkipMAX) {
					//GameUpdate
					System.out.println("NextGameTick1: " + nextGameTick);
					nextGameTick += skipppedTics;
					System.out.println("NextGameTick2: " + nextGameTick);
					loops++;
					System.out.println("loops: " + loops);
				}
				
				System.out.println("interpolation1: " + interpolation);
				interpolation = (float) (System.currentTimeMillis() + skipppedTics - nextGameTick/(float) (skipppedTics));
				System.out.println("interpolation2: " + interpolation);
				// DisplaGame(interpolation)
			}
			
		}
	}
	
	/*
	 * V�e se d�je p�i rychlosti 25 fram� za sekundu.
	 */
	class ConstantFrameRate implements Runnable {
		Thread thread = new Thread(this);
		
		public void run() {
			int updatesPerSec = 25;
			int skipppedTics = 1000/updatesPerSec;
			long nextGameTick = System.currentTimeMillis();
			boolean running = true;
			long sleepTime = 0;
			
			while(running) {
				// GameUpdate and gameDisplay
				System.out.println("NextGameTick1: " + nextGameTick);
				nextGameTick += skipppedTics;
				System.out.println("NextGameTick2: " + nextGameTick);
				System.out.println("sleepTime1: " + sleepTime);
				sleepTime = nextGameTick - System.currentTimeMillis();
				System.out.println("sleepTime2: " + sleepTime);
				
				if(sleepTime >= 0) {
					System.out.println("Sleep");
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Different situation");
				}
			}
			
		}
		
		
	}
	
}
