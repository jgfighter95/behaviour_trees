package framework;

import org.dreambot.api.methods.MethodProvider;

import java.util.HashMap;
import java.util.function.Function;

public class Leaf extends Node {
    private Function<Context, Boolean> task;

    public void registerTask(Function<Context, Boolean> task) {
        this.task = task;
    }

    @Override
    public Status execute(Context context) {
        if(task.apply(context)) {
            return Status.Success;
        } else {
            return Status.Failure;
        }
    }
}