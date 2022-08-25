package fr.stardustenterprises.retroloader.engine.api.strategy;

import fr.stardustenterprises.retroloader.engine.api.JavaVersion;
import org.objectweb.asm.tree.ClassNode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Processing strategy.
 *
 * @author xtrm
 * @since 0.0.1
 */
public interface IProcessingStrategy {
    boolean adapt(ClassNode node);

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Metadata {
        /**
         * @return the strategy's identifier
         */
        String id();

        /**
         * @return the strategy's priority order
         */
        int priority();

        /**
         * @return the highest (excluded) {@link JavaVersion} which this
         * transformer should {@link IProcessingStrategy#adapt}
         */
        JavaVersion targetsUnder();
    }
}
