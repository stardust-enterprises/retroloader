package fr.stardustenterprises.retroloader.engine.strategy.missingapi;

import fr.stardustenterprises.retroloader.engine.strategy.IProcessingStrategy;
import fr.stardustenterprises.retroloader.engine.utils.Tuple;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.RemappingClassAdapter;
import org.objectweb.asm.tree.ClassNode;

import java.util.List;

/**
 * Abstract strategy designed for missing or changed Java APIs.
 *
 * @author xtrm
 * @since 0.0.1
 */
public abstract class MissingAPIStrategy implements IProcessingStrategy {

    protected final RedirectorRemapper redirectorRemapper;

    public MissingAPIStrategy() {
        this.redirectorRemapper = new RedirectorRemapper(getRedeclaredPackages());
    }

    /**
     * Allows for entire removed packages to easily
     * be redirected to their reimplementation.
     *
     * @return the redeclared packages
     */
    public abstract List<Tuple<String, String>> getRedeclaredPackages();

    @Override
    public boolean adapt(ClassNode node, ClassReader reader, ClassWriter writer) {
        List<Tuple<String, String>> redeclaredPackages = getRedeclaredPackages();
        if (redeclaredPackages.isEmpty()) {
            return false;
        }

        RemappingClassAdapter remappingClassAdapter = new RemappingClassAdapter(writer, this.redirectorRemapper);
        reader.accept(remappingClassAdapter, ClassReader.EXPAND_FRAMES);

        return this.redirectorRemapper.wasUsed();
    }
}
