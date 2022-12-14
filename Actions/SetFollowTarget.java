import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;
import jason.NoValueException;
import java.util.List;

public class SetFollowTarget extends Action {
	private static String actionName = "follow_target";
	
	public SetFollowTarget() {
		super(SetFollowTarget.actionName);
	}
	
	@Override
	public void execute(String ag, Structure action, Orc o, List<Orc> allOrcs) throws NoValueException {
		boolean val = (int)((NumberTerm)action.getTerm(0)).solve() > 0;
		
		o.setFollowTarget(val);
	}
}
