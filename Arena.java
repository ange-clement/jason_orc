// Environment code for project orcTest.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;

import java.util.ArrayList;

public class Arena extends Environment {

    private Logger logger = Logger.getLogger("orcTest.mas2j."+Arena.class.getName());
	
	private ArenaGraphics graphics;
	private ArrayList<OrcGraphics> orcs;
	
	public static final String mv = "move";
	public static final String tg = "target";
	public static final String ft = "follow_target";
	/*
    public static final Term    pg = Literal.parseLiteral("pick(garb)");
    public static final Term    dg = Literal.parseLiteral("drop(garb)");
    public static final Term    bg = Literal.parseLiteral("burn(garb)");
    public static final Literal g1 = Literal.parseLiteral("garbage(r1)");
    public static final Literal g2 = Literal.parseLiteral("garbage(r2)");
	*/
	
	private int nbOrcs = 10;

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
		
		graphics = new ArenaGraphics(500, 500);
		
		orcs = new ArrayList<>();
		for (int i = 0; i < nbOrcs; i++) {
			orcs.add(new OrcGraphics(i%5 * 10 + 225, i/5 * 50 + 225, 5));
			graphics.addDrawable(orcs.get(i));
			
			
			addPercept("orc"+i, Literal.parseLiteral("id(" + i + ")"));
		}
		
		updatePercepts();
    }

    @Override
    public boolean executeAction(String ag, Structure action) {
        // logger.info("executing: "+action+", but not implemented!");
        // if (true) { // you may improve this condition
        //      informAgsEnvironmentChanged();
        // }
        // return true; // the action was executed with success
		
		//orcs.forEach(o -> o.draw());
		
		logger.info(ag+" doing: "+ action);
		
		OrcGraphics o = orcs.get(Integer.parseInt(""+ag.charAt(3)));
		
		
        try {
			if (action.getFunctor().equals(mv)) {
                int x = (int)((NumberTerm)action.getTerm(0)).solve();
                int y = (int)((NumberTerm)action.getTerm(1)).solve();
				
				o.move(x, y);
            } else if (action.getFunctor().equals(tg)) {
                int x = (int)((NumberTerm)action.getTerm(0)).solve();
                int y = (int)((NumberTerm)action.getTerm(1)).solve();
				
				o.target(x, y);
            } else if (action.getFunctor().equals(ft)) {
				boolean val = (int)((NumberTerm)action.getTerm(0)).solve() > 0;
				
				o.setFollowTarget(val);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        update();

		if (ag.equals("orc0")) {
			try {
				Thread.sleep(16);
			} catch (Exception e) {}
		}
        informAgsEnvironmentChanged();
        return true;
    }
	
	private void update() {
		updateOrcs();
		updatePercepts();
		graphics.update();
	}
	
	private void updateOrcs() {
		orcs.forEach(o -> o.update());
	}
	
	private void updatePercepts() {
        clearPercepts();
		
		removePerceptsByUnif(Literal.parseLiteral("target"));
		
		for (int i = 0; i < orcs.size(); i++) {
			OrcGraphics orc = orcs.get(i);
			addPercept("orc"+i, Literal.parseLiteral("target(" + orc.targetX + "," + orc.targetY + ")"));
			if (orc.followTarget)
				addPercept("orc"+i, Literal.parseLiteral("following_target"));
			addPercept(Literal.parseLiteral("pos("+i+"," + orc.posX + "," + orc.posY + ")"));
		}
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}

