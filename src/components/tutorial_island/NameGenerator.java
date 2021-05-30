package components.tutorial_island;

import org.dreambot.api.methods.Calculations;

import java.util.Arrays;
import java.util.List;

public class NameGenerator {
	private List<String> adjectives = Arrays.asList(
			"Strong", "Mighty", "Major", "Super", "Fast", "Smart", "Brave"
	);
	private List<String> nouns = Arrays.asList(
			"Warrior", "Mage", "Rogue", "Lord", "King", "Fighter", "Brawler"
			,"Man", "Runner", "Boxer", "Player"
	);
	
	public String generate() {
		String name = null;
		do {
			String adjective = adjectives.get(Calculations.random(0, adjectives.size()));
			String noun = nouns.get(Calculations.random(0, nouns.size()));
			name = adjective + noun;
		} while (name.length() > 12);
		return name;
	}
}