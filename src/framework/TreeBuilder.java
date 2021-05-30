package framework;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.Function;

public class TreeBuilder {
    private final Context context;
    private Stack<ParentNode> parentStack = new Stack<>();
    private ParentNode currentNode;

    public TreeBuilder(Context context) {
        this.context = context;
    }

    public TreeBuilder leaf(Function<Context, Boolean> task) {
        Leaf leaf = new Leaf();
        leaf.registerTask(task);
        return registerChild(leaf);
    }

    public TreeBuilder action(Function<Context, Boolean> task) {
        Action action = new Action();
        action.registerTask(task);
        return registerChild(action);
    }

    public TreeBuilder sequence() {
        Sequence sequence = new Sequence();
        return registerParent(sequence);
    }

    public TreeBuilder selector() {
        Selector selector = new Selector();
        return registerParent(selector);
    }

    public TreeBuilder insert(Node node) {
        return registerChild(node);
    }

    public TreeBuilder condition(Function<Context, Boolean> logic) {
        Condition condition = new Condition();
        condition.setCondition(logic);
        return registerParent(condition);
    }

    public TreeBuilder loopUntilSuccess() {
        LoopUntilSuccess loop = new LoopUntilSuccess();
        return registerParent(loop);
    }

    public TreeBuilder finish() {
        currentNode = parentStack.pop();
        return this;
    }

    public TreeBuilder sleepUntil(Function<Context, Boolean> condition, int timeout) {
        SleepUntil sleepUntil = new SleepUntil();
        sleepUntil.registerCondition(condition, timeout);
        return registerChild(sleepUntil);
    }

    public Node build() {
        return currentNode;
    }

    private TreeBuilder registerParent(ParentNode node) {
        if (!parentStack.isEmpty()) {
            parentStack.peek().addChild(node);
        }
        parentStack.push(node);
        return this;
    }

    private TreeBuilder registerChild(Node node) {
        parentStack.peek().addChild(node);
        return this;
    }
}
