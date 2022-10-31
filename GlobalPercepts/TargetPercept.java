import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;
import java.util.List;

public class TargetPercept extends GlobalPercept {
	private static String perceptName = "target"; 
	
	public TargetPercept() {
		super(TargetPercept.perceptName);
	}
	
	public Literal construct(int id, Orc o, List<Orc> allOrcs) {
		return Literal.parseLiteral(this.perceptName+"("+id+"," + o.targetX + "," + o.targetY + ")");
	}
}
