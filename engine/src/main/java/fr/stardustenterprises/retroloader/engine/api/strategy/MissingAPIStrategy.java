package fr.stardustenterprises.retroloader.engine.api.strategy;

import fr.stardustenterprises.retroloader.engine.api.strategy.missingapi.RedirectorRemapper;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract strategy designed for missing or changed Java APIs.
 *
 * @author xtrm
 * @since 0.0.1
 */
public abstract class MissingAPIStrategy implements IProcessingStrategy {
	private final RedirectorRemapper redirectorRemapper;
	private final Map<String, String> packageRedirections;
	private final List<String> providedLibraryPackages;

	public MissingAPIStrategy() {
		this.redirectorRemapper = new RedirectorRemapper(getRedeclaredPackages());
		this.packageRedirections = new HashMap<String, String>();
		this.providedLibraryPackages = new ArrayList<String>();
	}

	protected final void provides(String packageName, String... replaces) {
		this.providedLibraryPackages.add(packageName);
		for (String str : replaces) {
			this.packageRedirections.put(str, packageName);
		}
	}

	/**
	 * Allows for entire removed packages to easily
	 * be redirected to their reimplementation.
	 *
	 * @return the redeclared packages
	 */
	public final Map<String, String> getRedeclaredPackages() {
		return this.packageRedirections;
	}

	@Override
	public final boolean adapt(ClassNode node) {
		if (this.packageRedirections.isEmpty())
			return false;

		ClassRemapper classRemapper = new ClassRemapper(node, this.redirectorRemapper);
		node.accept(classRemapper);

		return this.redirectorRemapper.wasUsed();
	}
}
