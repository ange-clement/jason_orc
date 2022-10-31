import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;
import jason.NoValueException;
import java.util.List;

public class ChangeTarget extends Action {
	private static String actionName = "target";
	
	public ChangeTarget() {
		super(ChangeTarget.actionName);
	}
	
	@Override
	public void execute(String ag, Structure action, Orc o, List<Orc> allOrcs) throws NoValueException {
		int x = (int)((NumberTerm)action.getTerm(0)).solve();
		int y = (int)((NumberTerm)action.getTerm(1)).solve();
		
		o.target(x, y);
	}
}
