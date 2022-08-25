package fr.stardustenterprises.retroloader.engine;

import fr.stardustenterprises.retroloader.engine.api.IProcessor;
import fr.stardustenterprises.retroloader.engine.api.strategy.IProcessingStrategy;
import fr.stardustenterprises.retroloader.engine.strategy.impl.ClassVersionPatcherStrategy;
import fr.stardustenterprises.retroloader.engine.strategy.impl.RetroLambdaStrategy;
import fr.stardustenterprises.retroloader.engine.strategy.impl.StreamSupportStrategy;
import fr.stardustenterprises.retroloader.engine.strategy.impl.ThreeTenBPStrategy;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class-preprocessor engine to allow for cross-version compatibility
 * for different JVM versions.
 *
 * @author xtrm
 * @since 0.0.1
 */
public class RetroPreprocessor implements IProcessor {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(RetroPreprocessor.class);
	private static final List<IProcessingStrategy> STRATEGIES =
			new ArrayList<IProcessingStrategy>();

	private final int targetVersion;

	public RetroPreprocessor(int targetVersion) {
		this.targetVersion = Math.max(1, targetVersion);
	}

	@Override
	public byte[] processClassBuffer(byte[] classfileBuffer) {
		ClassReader classReader = new ClassReader(classfileBuffer);
		ClassNode classNode = new ClassNode(Opcodes.ASM9);
		classReader.accept(classNode, ClassReader.EXPAND_FRAMES);

		LOGGER.info("Processing {} (from: {}, to {})...", classNode.name, classNode.version, 8 + 44);

		for (IProcessingStrategy strategy : STRATEGIES) {
			IProcessingStrategy.Metadata metadata = strategy.getClass()
					.getAnnotation(IProcessingStrategy.Metadata.class);
			if (metadata == null)
				continue;
			if (strategy.adapt(classNode)) {
				LOGGER.info("Applied " + metadata.id() + "");
			}
		}
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		classNode.accept(classWriter);
		return classWriter.toByteArray();
	}

	@Override
	public void processJarfile(File jarFile, File output) throws IOException {
		File temp = File.createTempFile("retroloader", ".jar");
		if (!temp.createNewFile()) {
			throw new IOException("Couldn't create temp file.");
		}

		FileUtils.copyFile(jarFile, output);

		if (!temp.renameTo(output)) {
			//noinspection ResultOfMethodCallIgnored
			temp.delete();
			throw new IOException(String.format(
					"Couldn't rename temp file to output file (%s).",
					output.getAbsolutePath()
			));
		}
	}

	static {
		STRATEGIES.add(RetroLambdaStrategy.INSTANCE);
		STRATEGIES.add(StreamSupportStrategy.INSTANCE);
		STRATEGIES.add(ThreeTenBPStrategy.INSTANCE);
		STRATEGIES.add(ClassVersionPatcherStrategy.INSTANCE);

		Collections.sort(STRATEGIES, new Comparator<IProcessingStrategy>() {
			@Override
			public int compare(IProcessingStrategy o1, IProcessingStrategy o2) {
				IProcessingStrategy.Metadata metadata1 = o1.getClass()
						.getAnnotation(IProcessingStrategy.Metadata.class);
				IProcessingStrategy.Metadata metadata2 = o2.getClass()
						.getAnnotation(IProcessingStrategy.Metadata.class);
				if (metadata1 == null || metadata2 == null) {
					throw new RuntimeException("Invalid metadata.");
				}
				if (metadata1.priority() > metadata2.priority())
					return 1;
				if (metadata1.priority() < metadata2.priority())
					return -1;
				return 0;
			}
		});
		LOGGER.info("Initialized {} strategies.", STRATEGIES.size());
		for (IProcessingStrategy strategy : STRATEGIES) {
			IProcessingStrategy.Metadata metadata1 = strategy.getClass()
					.getAnnotation(IProcessingStrategy.Metadata.class);
			LOGGER.info(" - {} => {}", metadata1.id(), metadata1.priority());
		}
	}
}
