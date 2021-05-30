package components.tutorial_island;

import framework.Context;
import framework.Node;
import framework.TreeBuilder;
import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.widgets.WidgetChild;

public class TutorialIslandTree {
	public static Node build(Context context) {
		return new TreeBuilder(context)
				.selector()
					.insert(displayName(context))
				.finish()
				.build();
	}
	
	private static Node displayName(Context context) {
		return new TreeBuilder(context)
				.sequence()
					.leaf(TutorialIslandTree::isDisplayName)
					.action(TutorialIslandTree::focusNameField)
					.action(TutorialIslandTree::enterName)
				.finish()
				.build();
	}
	
	private static Boolean isDisplayName(Context context) {
		WidgetChild widget = Widgets.getChildWidget(558, 3);
		return widget != null;
	}
	
	private static Boolean focusNameField(Context context) {
		WidgetChild nameField = Widgets.getChildWidget(558, 7);
		return nameField.interact();
	}
	
	private static Boolean enterName(Context context) {
		NameGenerator generator = new NameGenerator();
		String name = generator.generate();
		Keyboard.type(name, false);
		return true;
	}
}