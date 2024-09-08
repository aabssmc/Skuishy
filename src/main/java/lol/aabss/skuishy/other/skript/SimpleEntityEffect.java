package lol.aabss.skuishy.other.skript;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

public abstract class SimpleEntityEffect<T extends Entity> extends EntityEffect<T> {

    abstract protected void execute(T t);

    @Override
    protected void execute(T t, Event event) {
        execute(t);
    }

}
