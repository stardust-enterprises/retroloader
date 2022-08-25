package fr.stardustenterprises.retroloader.engine.strategy.impl;

import fr.stardustenterprises.retroloader.engine.api.JavaVersion;
import fr.stardustenterprises.retroloader.engine.api.strategy.IProcessingStrategy;
import org.objectweb.asm.tree.ClassNode;

@IProcessingStrategy.Metadata(
        id = "retrolambda",
        priority = 100,
        targetsUnder = JavaVersion.VERSION_1_8
)
public enum RetroLambdaStrategy implements IProcessingStrategy {
    INSTANCE;

    public boolean adapt(ClassNode node) {
        return false;
    }

}
