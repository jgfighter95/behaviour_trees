package framework;

public abstract class Decorator extends ParentNode {
    protected Node child;

    public void setChild(Node child) {
        this.child = child;
    }

    @Override
    public void addChild(Node node) {
        child = node;
    }
}
