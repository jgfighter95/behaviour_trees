package framework;

public class Selector extends Composite{
    @Override
    protected boolean shouldEndProcessing(Status status) {
        return status == Status.Success
                || (status == Status.Failure && currentIndex == children.size() - 1);
    }

    @Override
    protected boolean shouldGoToNextChild(Status status) {
        return status == Status.Failure && currentIndex < children.size() - 1;
    }

    @Override
    protected Status getDefaultStatus(Status status) {
        return Status.Running;
    }
}