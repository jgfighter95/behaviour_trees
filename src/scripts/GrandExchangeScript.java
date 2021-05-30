package scripts;

import components.GrandExchangeTree;
import framework.*;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name="BT-Test", author="JGFighter95", category = Category.MISC, version = 1)
public class GrandExchangeScript extends AbstractScript {
    private Node root;
    private Context context;

    @Override
    public int onLoop() {
        root.execute(context);
        return 0;
    }

    @Override
    public void onStart() {
        context = new Context();
        context.put("itemName", "Cowhide");
        context.put("originalPrice", 100);
        context.put("price", 100);
        context.put("quantity", 1);
        root = GrandExchangeTree.buyItemSubTree(context);
    }
}