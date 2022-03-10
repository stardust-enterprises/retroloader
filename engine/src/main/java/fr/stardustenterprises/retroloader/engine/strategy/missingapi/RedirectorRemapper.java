package fr.stardustenterprises.retroloader.engine.strategy.missingapi;

import fr.stardustenterprises.retroloader.engine.utils.Tuple;
import org.objectweb.asm.commons.Remapper;

import java.util.ArrayList;
import java.util.List;

public class RedirectorRemapper extends Remapper {
    private final List<String> targettedPackages = new ArrayList<>();

    private final List<Tuple<String, String>> packageRedirections;
    private boolean used;

    public RedirectorRemapper(List<Tuple<String, String>> packageRedirections) {
        this.packageRedirections = packageRedirections;

        for (Tuple<String, String> redirection : packageRedirections) {
            this.targettedPackages.add(redirection.getKey());
        }
    }

    @Override
    public String map(String typeName) {
        for (String target : this.targettedPackages) {
            if (typeName.startsWith(target)) {
                
            }
        }
        return super.map(typeName);
    }
}
