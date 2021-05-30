package framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public abstract class Composite extends ParentNode {
    protected List<Node> children = new ArrayList<>();
    protected int currentIndex = 0;

    @Override
    public Status execute(Context context) {
        Node child = children.get(currentIndex);
        Status status = child.execute(context);
        if (shouldEndProcessing(status)) {
            currentIndex = 0;
            return status;
        } else if (shouldGoToNextChild(status)) {
            currentIndex++;
        }
        return getDefaultStatus(status);
    }

    @Override
    public void addChild(Node node) {
        children.add(node);
    }

    protected abstract boolean shouldEndProcessing(Status status);
    protected abstract boolean shouldGoToNextChild(Status status);
    protected abstract Status getDefaultStatus(Status status);
}