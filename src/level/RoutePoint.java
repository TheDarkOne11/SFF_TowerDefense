package level;

/** Uchov�v� sou�adnice v�ech, kde nep��tel mus� z�stat uprost�ed �tverce(zat��ka, z�kladna). 
 *  Posledn� bod je z�kladna. */
public class RoutePoint {
	private int xPos;
	private int yPos;
	
	public RoutePoint(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}
}
