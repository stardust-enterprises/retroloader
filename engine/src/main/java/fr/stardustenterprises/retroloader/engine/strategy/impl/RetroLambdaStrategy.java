package fr.stardustenterprises.retroloader.engine.strategy.impl;

import fr.stardustenterprises.retroloader.engine.strategy.IProcessingStrategy;
import org.objectweb.asm.tree.ClassNode;

public enum RetroLambdaStrategy implements IProcessingStrategy {
    INSTANCE;

    /**
     * @return the strategy's identifier
     */
    @Override
    public String getId() {
        return "retrolambda";
    }

    /**
     * @return the strategy's priority order
     */
    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public boolean adapt(ClassNode classNode) {
        return false;
    }

}
