package additional_programs;

public class FrameSpeedExamples {
	
	public FrameSpeedExamples() {
		new ConstantIndependentFrameRate().run();
	}
	
	public static void main(String[] args) {
		new FrameSpeedExamples();
	}
	
	/*
	 *  Mnohem složitìjší, hra jede poøád na 25 framech za sekundu, ale zobrazování se mùže dít i mezi updaty,
	 *  jelikož ve høe musí být zakomponováno pøedvídání.
	 *  Vizuálnì lepší než konstantní rychlost framù.
	 *  
	 *  Pøedvídání:
	 *  Auto jede rychlostí 100.
	 *  V 5. updatu je ve vzdálenosti 500. Proto v 11. bude ve vzdálenosti 600.
	 *  Mohu to zobrazit uprostøed updatù, proto promìnná interpolation má hodnotu 0,5.
	 *  Proto jeho pozice v tomto èase by byla 550(speed*interpolation).
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
	 * Vše se dìje pøi rychlosti 25 framù za sekundu.
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
