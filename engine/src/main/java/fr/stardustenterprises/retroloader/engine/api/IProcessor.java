package fr.stardustenterprises.retroloader.engine.api;

import java.io.File;
import java.io.IOException;

public interface IProcessor {
	byte[] processClassBuffer(byte[] classfileBuffer);

	void processJarfile(File jarFile, File outputFile) throws IOException;
}
