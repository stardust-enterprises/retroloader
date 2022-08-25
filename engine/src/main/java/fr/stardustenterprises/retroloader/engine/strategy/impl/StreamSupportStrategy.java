package fr.stardustenterprises.retroloader.engine.strategy.impl;

import fr.stardustenterprises.retroloader.engine.api.JavaVersion;
import fr.stardustenterprises.retroloader.engine.api.strategy.IProcessingStrategy;
import fr.stardustenterprises.retroloader.engine.api.strategy.MissingAPIStrategy;

@IProcessingStrategy.Metadata(
        id = "streamsupport",
        priority = 0,
        targetsUnder = JavaVersion.VERSION_1_8
)
public class StreamSupportStrategy extends MissingAPIStrategy {
    public static final StreamSupportStrategy INSTANCE = new StreamSupportStrategy();

    public StreamSupportStrategy() {
        this.provides("java8/util/stream", "java/util/stream");
    }
}
