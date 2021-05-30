package framework;

import java.util.HashMap;
import java.util.function.Function;

public class Condition extends Decorator {
    private Function<Context, Boolean> condition;

    public void setCondition(Function<Context, Boolean> condition) {
        this.condition = condition;
    }

    @Override
    public Status execute(Context context) {
        if (condition.apply(context)) {
            return child.execute(context);
        } else {
            return Status.Success;
        }
    }
}