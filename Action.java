import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;
import jason.NoValueException;
import java.util.List;

public abstract class Action {
	private String actionName;
	
	public Action(String actionName) {
		this.actionName = actionName;
	}
	
	public boolean correspondsTo(Structure action) {
		return action.getFunctor().equals(actionName);
	}
	
	public abstract void execute(String ag, Structure action, Orc o, List<Orc> allOrcs) throws NoValueException;
}
