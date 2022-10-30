// Environment code for project orcTest.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;

import java.util.ArrayList;

public class Arena extends Environment {

    private Logger logger = Logger.getLogger("orcTest.mas2j."+Arena.class.getName());
	
	private long previousFrame;
	
	private ArenaGraphics graphics;
	private ArrayList<Orc> orcs;
	private TextZone text;
	private int wantedFPS = 60;
	private long moyFPS = 0;
	private int waitToShowFPS;
	
	private ArrayList<Action> actions;
	private ArrayList<GlobalPercept> percepts;
	
	public static final String per_id = "id";
	
	private int nbOrcs = 10;

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
		
		graphics = new ArenaGraphics(500, 500);
		text = new TextZone(10, 20, "alo?");
		graphics.addDrawable(text);
		
		actions = new ArrayList<>();
		actions.add(new Move());
		actions.add(new ChangeTarget());
		actions.add(new SetFollowTarget());
		
		percepts = new ArrayList<>();
		percepts.add(new PositionPercept());
		percepts.add(new TargetPercept());
		percepts.add(new HPPercept());
		
		orcs = new ArrayList<>();
		for (int i = 0; i < nbOrcs; i++) {
			orcs.add(new Orc(i, i%5 * 10 + 225, i/5 * 50 + 225, 5));
			graphics.addDrawable(orcs.get(i));
			
			addPercept("orc"+i, Literal.parseLiteral(per_id+"(" + i + ")"));
		}
		
		updatePercepts();
		
		previousFrame = System.currentTimeMillis();
    }

    @Override
    public boolean executeAction(String ag, Structure action) {
		Orc o = orcs.get(Integer.parseInt(""+ag.charAt(3)));
		
        try {
			for (int i = 0; i < actions.size(); i++) {
				if (actions.get(i).correspondsTo(action)) {
					actions.get(i).execute(ag, action, o);
				}
			}
        } catch (Exception e) {
            e.printStackTrace();
        }

        update();

		long curtime = System.currentTimeMillis();
		long time = curtime - previousFrame;
		previousFrame = curtime;
		
		try {
			int waitTime = (int)Math.max(0, (1000 / wantedFPS) - time);
			if (waitToShowFPS < 0) {
				waitToShowFPS = wantedFPS;
				text.text = "fps : "+ (1000 * wantedFPS / (float)moyFPS);
				moyFPS = 0;
			}
			else {
				waitToShowFPS--;
				moyFPS += (time+waitTime);
			}
			Thread.sleep(waitTime);
		} catch (Exception e) {}
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
		
		for (int i = 0; i < orcs.size(); i++) {
			Orc orc = orcs.get(i);
			for (int j = 0; j < percepts.size(); j++) {
				addPercept(percepts.get(j).construct(i, orc));
			}
		}
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}

