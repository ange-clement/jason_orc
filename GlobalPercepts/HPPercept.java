import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

public class HPPercept extends GlobalPercept {
	private static String perceptName = "hp"; 
	
	public HPPercept() {
		super(HPPercept.perceptName);
	}
	
	public Literal construct(int id, Orc o) {
		return Literal.parseLiteral(this.perceptName+"("+id+"," + o.health / o.maxHealth + ")");
	}
}
