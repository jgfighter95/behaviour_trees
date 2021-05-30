package components;

import framework.Context;
import framework.Node;
import framework.TreeBuilder;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.grandexchange.GrandExchange;

import java.util.HashMap;

public class GrandExchangeTree {
    public static Node buyItemSubTree(Context context) {
        return new TreeBuilder(context)
                .loopUntilSuccess()
                    .sequence()
                        .insert(Banking.withdrawCoinsSubTree(context))
                        .action(GrandExchangeTree::openGrandExchange)
                        .loopUntilSuccess()
                            .selector()
                                .insert(createBuyOffer(context))
                                .sequence()
                                    .action(GrandExchangeTree::incrementPrice)
                                    .action(ctx -> GrandExchange.cancelOffer(ctx.integer("slot")))
                                    .action(ctx -> GrandExchange.goBack())
                                    .action(ctx -> GrandExchange.collect())
                                .finish()
                            .finish()
                        .finish()
                    .finish()
                .finish()
                .build();
    }

    private static Boolean openGrandExchange(Context context) {
        if (GrandExchange.isOpen()) {
            return true;
        }
        GrandExchange.open();
        return MethodProvider.sleepUntil(() -> GrandExchange.isOpen(), 10000);
    }

    private static Node createBuyOffer(Context context) {
        return new TreeBuilder(context)
                .sequence()
                    .action(GrandExchangeTree::initialiseSlotNumber)
                    .action(ctx -> GrandExchange.openBuyScreen(ctx.integer("slot")))
                    .action(ctx -> GrandExchange.addBuyItem(ctx.text("itemName")))
                    .action(ctx -> GrandExchange.setPrice(ctx.integer("price")))
                    .action(ctx -> GrandExchange.setQuantity(ctx.integer("quantity")))
                    .action(ctx -> GrandExchange.confirm())
                    .sleepUntil(ctx -> GrandExchange.isReadyToCollect(ctx.integer("slot")), 10000)
                .finish()
                .build();
    }

    private static Boolean initialiseSlotNumber(Context context) {
        context.put("slot", GrandExchange.getFirstOpenSlot());
        return true;
    }

    private static Boolean incrementPrice(Context context) {
        if (!context.hasKey("attempts")) {
            context.put("attempts", 1);
        } else {
            int attempts = context.integer("attempts") + 1;
            context.put("attempts", attempts);
        }
        int attempts = context.integer("attempts");
        int originalPrice = context.integer("originalPrice");
        int fivePercent = (int)(originalPrice * 0.05);
        int newPrice = originalPrice + (attempts * fivePercent);
        context.put("price", newPrice);
        MethodProvider.log("NEW PRICE: " + newPrice);
        return true;
    }
}