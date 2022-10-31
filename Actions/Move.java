import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;
import jason.NoValueException;
import java.util.List;

public class Move extends Action {
	private static String actionName = "move";
	
	public Move() {
		super(Move.actionName);
	}
	
	@Override
	public void execute(String ag, Structure action, Orc o, List<Orc> allOrcs) throws NoValueException {
		int x = (int)((NumberTerm)action.getTerm(0)).solve();
		int y = (int)((NumberTerm)action.getTerm(1)).solve();
		
		o.move(x, y);
	}
}
