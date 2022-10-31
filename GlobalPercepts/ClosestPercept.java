import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;
import java.util.List;

public class ClosestPercept extends GlobalPercept {
	private static String perceptName = "closest"; 
	
	public ClosestPercept() {
		super(ClosestPercept.perceptName);
	}
	
	private double sqrDistance(Orc o1, Orc o2) {
		double diffY = o1.posY - o2.posY;
		double diffX = o1.posX - o2.posX;
		return diffY*diffY + diffX*diffX;
	}
	
	public Literal construct(int id, Orc o, List<Orc> allOrcs) {
		int minId = -1;
		double curDist = 99999;
		for (int i = 0; i < allOrcs.size(); i++) {
			Orc curentOrc = allOrcs.get(i);
			if (i != id && curentOrc.isAlive()) {
				double d = sqrDistance(o, curentOrc);
				if (d < curDist) {
					minId = i;
					curDist = d;
				}
			}
		}
		return Literal.parseLiteral(this.perceptName+"("+id+"," + minId + ")");
	}
}
