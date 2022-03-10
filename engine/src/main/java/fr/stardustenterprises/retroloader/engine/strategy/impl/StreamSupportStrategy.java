package fr.stardustenterprises.retroloader.engine.strategy.impl;

import fr.stardustenterprises.retroloader.engine.strategy.missingapi.MissingAPIStrategy;
import fr.stardustenterprises.retroloader.engine.utils.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StreamSupportStrategy extends MissingAPIStrategy {
    public static final StreamSupportStrategy INSTANCE = new StreamSupportStrategy();

    private final List<Tuple<String, String>> packageRedirections = new ArrayList<>();

    public StreamSupportStrategy() {
        this.packageRedirections.add(Tuple.of("java/util/stream", "java8/util/stream"));
    }

    /**
     * @return the strategy's identifier
     */
    @Override
    public String getId() {
        return "streamsupport";
    }

    /**
     * @return the strategy's priority order
     */
    @Override
    public int getPriority() {
        return 0;
    }

    /**
     * Allows for entire removed packages to easily
     * be redirected to their reimplementation.
     *
     * @return the redeclared packages
     */
    @Override
    public List<Tuple<String, String>> getRedeclaredPackages() {
        return this.packageRedirections;
    }
}
