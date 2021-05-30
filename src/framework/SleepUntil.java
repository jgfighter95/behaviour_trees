package framework;

import org.dreambot.api.methods.MethodProvider;

import java.util.function.Function;

public class SleepUntil extends Node {
    private int timeout;
    private Function<Context, Boolean> condition;

    public void registerCondition(Function<Context, Boolean> condition, int timeout) {
        this.condition = condition;
        this.timeout = timeout;
    }

    @Override
    public Status execute(Context context) {
        if (MethodProvider.sleepUntil(() -> condition.apply(context), timeout)) {
            return Status.Success;
        }
        return Status.Failure;
    }
}
