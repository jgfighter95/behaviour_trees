package scripts;

import components.tutorial_island.TutorialIslandTree;
import framework.Context;
import framework.Node;
import org.dreambot.api.Client;
import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name="Tutorial Island", author="JGFighter95", version=1,category = Category.QUEST)
public class TutorialIslandScript extends AbstractScript {
	private Node tree;
	private Context context;
	
	@Override
	public int onLoop() {
		tree.execute(context);
		return 0;
	}
	
	@Override
	public void onStart() {
		MethodProvider.sleepUntil(() -> Client.getGameState() == GameState.LOGGED_IN, 60000);
		this.context = new Context();
		tree = TutorialIslandTree.build(this.context);
	}
}