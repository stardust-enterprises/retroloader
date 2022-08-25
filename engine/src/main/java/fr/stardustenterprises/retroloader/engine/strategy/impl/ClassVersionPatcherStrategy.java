package fr.stardustenterprises.retroloader.engine.strategy.impl;

import fr.stardustenterprises.retroloader.engine.api.JavaVersion;
import fr.stardustenterprises.retroloader.engine.api.strategy.IProcessingStrategy;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.tree.ClassNode;
import software.coley.versionpatcher.VersionPatcher;

@IProcessingStrategy.Metadata(
        id = "class-version-patcher",
        priority = 1000,
        targetsUnder = JavaVersion.VERSION_HIGHER
)
public enum ClassVersionPatcherStrategy implements IProcessingStrategy {
    INSTANCE;

    public boolean adapt(ClassNode node) {
        ClassVisitor visitor = new VersionPatcher(node, 8);
        node.accept(visitor);
        return true;
    }
}
