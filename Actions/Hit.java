import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;
import jason.NoValueException;
import java.util.List;

public class Hit extends Action {
	private static String actionName = "hit";
	
	public Hit() {
		super(Hit.actionName);
	}
	
	@Override
	public void execute(String ag, Structure action, Orc o, List<Orc> allOrcs) throws NoValueException {
		int idOther = (int)((NumberTerm)action.getTerm(0)).solve();
		if (idOther >= 0 && idOther < allOrcs.size()) {
			Orc otherOrc = allOrcs.get(idOther);
			if (otherOrc != null) {
				o.hit(otherOrc);
			}
		}
	}
}
