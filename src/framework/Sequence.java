package framework;

public class Sequence extends Composite {
    @Override
    protected boolean shouldEndProcessing(Status status) {
        return status == Status.Failure
                || (status == Status.Success && currentIndex == children.size() - 1);
    }

    @Override
    protected boolean shouldGoToNextChild(Status status) {
        return status == Status.Success && currentIndex < children.size() - 1;
    }

    @Override
    protected Status getDefaultStatus(Status status) {
        return Status.Running;
    }
}