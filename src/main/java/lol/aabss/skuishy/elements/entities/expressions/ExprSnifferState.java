package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Sniffer;
import org.jetbrains.annotations.Nullable;

@Name("Sniffer - State")
@Description("Gets/sets the state of an sniffer.")
@Examples({
        "set sniffer state of {_sniffer} to idling"
})
@Since("2.8")
public class ExprSnifferState extends EntityExpression<Sniffer, Sniffer.State> {

    static {
        register(ExprSnifferState.class, Sniffer.State.class, "sniffer (state|type)", "entities");
    }

    @Override
    public Sniffer.State get(Sniffer sniffer) {
        return sniffer.getState();
    }

    @Override
    public void change(Sniffer sniffer, @Nullable Sniffer.State type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            sniffer.setState(type);
        }
    }
}