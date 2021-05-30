package framework;

import org.dreambot.api.methods.MethodProvider;

import java.util.HashMap;

public class LoopUntilSuccess extends Decorator {
    @Override
    public Status execute(Context context) {
        if (child.execute(context) == Status.Success) {
            return Status.Success;
        }
        return Status.Running;
    }
}