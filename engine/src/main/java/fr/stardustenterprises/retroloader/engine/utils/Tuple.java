package fr.stardustenterprises.retroloader.engine.utils;

import lombok.Data;

/**
 * A simple immutable Tuple container.
 *
 * @param <K> the key type
 * @param <V> the value type
 *
 * @author xtrm
 * @since 0.0.1
 */
@Data
public class Tuple<K, V> {
    private final K key;
    private final V value;

    public static <K, V> Tuple<K, V> of(K key, V value) {
        return new Tuple<>(key, value);
    }
}
