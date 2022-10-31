import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;
import java.util.List;

public abstract class GlobalPercept {
	private String perceptName; 
	
	public GlobalPercept(String perceptName) {
		this.perceptName = perceptName;
	}
	
	public abstract Literal construct(int id, Orc o, List<Orc> allOrcs);
}
