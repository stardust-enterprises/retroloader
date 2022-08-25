package fr.stardustenterprises.retroloader.engine.strategy.impl;

import fr.stardustenterprises.retroloader.engine.api.JavaVersion;
import fr.stardustenterprises.retroloader.engine.api.strategy.IProcessingStrategy;
import fr.stardustenterprises.retroloader.engine.api.strategy.MissingAPIStrategy;

@IProcessingStrategy.Metadata(
        id = "threetenbp",
        priority = 0,
        targetsUnder = JavaVersion.VERSION_1_8
)
public class ThreeTenBPStrategy extends MissingAPIStrategy {
    public static final ThreeTenBPStrategy INSTANCE = new ThreeTenBPStrategy();

    public ThreeTenBPStrategy() {
        this.provides("org/threeten/bp", "java/time");
    }
}
