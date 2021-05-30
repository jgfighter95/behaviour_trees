package framework;

import java.util.HashMap;

public class Context {
    private HashMap<String, Object> data = new HashMap<>();

    public void put(String name, Object value) {
        data.put(name, value);
    }

    public int integer(String name) {
        return (int)data.get(name);
    }

    public String text(String name) {
        return (String)data.get(name);
    }

    public boolean bool(String name) {
        return (boolean)data.get(name);
    }

    public boolean hasKey(String name) {
        return data.containsKey(name);
    }
}