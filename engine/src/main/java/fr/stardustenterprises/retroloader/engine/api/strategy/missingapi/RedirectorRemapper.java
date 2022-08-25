package fr.stardustenterprises.retroloader.engine.api.strategy.missingapi;

import org.objectweb.asm.commons.Remapper;

import java.util.Map;

public class RedirectorRemapper extends Remapper {
    private final Map<String, String> packageRedirections;
    private boolean used;

    public RedirectorRemapper(Map<String, String> packageRedirections) {
        this.packageRedirections = packageRedirections;
    }

    @Override
    public String map(String typeName) {
        for (String target : this.packageRedirections.keySet()) {
            if (typeName.startsWith(target)) {
                
            }
        }
        return super.map(typeName);
    }

    public boolean wasUsed() {
        return used;
    }
}
