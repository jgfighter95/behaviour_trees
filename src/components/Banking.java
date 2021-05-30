package components;

import framework.Context;
import framework.Node;
import framework.TreeBuilder;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;

import java.util.HashMap;

public class Banking {
    public static Node withdrawCoinsSubTree(Context context) {
        return new TreeBuilder(context)
            .condition(ctx -> Inventory.count("Coins") == 0 )
                .sequence()
                    .condition(ctx -> !Bank.isOpen())
                        .action(ctx -> Bank.open())
                    .finish()
                    .action(ctx -> Bank.withdrawAll("Coins"))
                    .action(ctx -> Bank.close())
                .finish()
            .finish()
            .build();
    }
}