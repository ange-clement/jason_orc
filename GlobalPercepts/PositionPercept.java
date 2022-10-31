import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;
import java.util.List;

public class PositionPercept extends GlobalPercept {
	private static String perceptName = "pos"; 
	
	public PositionPercept() {
		super(PositionPercept.perceptName);
	}
	
	public Literal construct(int id, Orc o, List<Orc> allOrcs) {
		return Literal.parseLiteral(this.perceptName+"("+id+"," + o.posX + "," + o.posY + ")");
	}
}
