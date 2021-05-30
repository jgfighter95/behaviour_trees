package framework;

import org.dreambot.api.methods.MethodProvider;

public class Action extends Leaf {
    private int retryLimit = 3;
    private int retryAttempts = 0;

    @Override
    public Status execute(Context context) {
        MethodProvider.sleep(800, 1500);
        Status status = super.execute(context);
        if (status == Status.Success || status == Status.Running) {
            return status;
        }
        if (retryAttempts < retryLimit) {
            retryAttempts++;
            return execute(context) ;
        } else {
            return Status.Failure;
        }
    }
}
