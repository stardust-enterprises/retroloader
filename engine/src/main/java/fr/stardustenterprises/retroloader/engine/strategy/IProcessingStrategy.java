package fr.stardustenterprises.retroloader.engine.strategy;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

/**
 * Processing strategy.
 *
 * @author xtrm
 * @since 0.0.1
 */
public interface IProcessingStrategy {

    /**
     * @return the strategy's identifier
     */
    String getId();

    /**
     * @return the strategy's priority order
     */
    int getPriority();

    boolean adapt(ClassNode node, ClassReader reader, ClassWriter writer);

}
