package fr.stardustenterprises.retroloader.engine;

import fr.stardustenterprises.retroloader.engine.strategy.IProcessingStrategy;
import fr.stardustenterprises.retroloader.engine.strategy.impl.RetroLambdaStrategy;
import fr.stardustenterprises.retroloader.engine.strategy.impl.StreamSupportStrategy;
import fr.stardustenterprises.retroloader.engine.strategy.impl.ThreeTenBPStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Class-preprocessor engine to allow for cross-version compatibility
 * for different JVM versions.
 *
 * @author xtrm
 * @since 0.0.1
 */
enum RetroPreprocessor {
    INSTANCE;

    private final List<IProcessingStrategy> strategies = new ArrayList<>();

    RetroPreprocessor() {
        this.strategies.add(RetroLambdaStrategy.INSTANCE);
        this.strategies.add(StreamSupportStrategy.INSTANCE);
        this.strategies.add(ThreeTenBPStrategy.INSTANCE);
    }
}
