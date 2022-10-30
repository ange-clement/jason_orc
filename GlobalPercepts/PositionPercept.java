import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

public class PositionPercept extends GlobalPercept {
	private static String perceptName = "pos"; 
	
	public PositionPercept() {
		super(PositionPercept.perceptName);
	}
	
	public Literal construct(int id, Orc o) {
		return Literal.parseLiteral(this.perceptName+"("+id+"," + o.posX + "," + o.posY + ")");
	}
}
