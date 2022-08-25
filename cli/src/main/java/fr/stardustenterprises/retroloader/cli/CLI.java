package fr.stardustenterprises.retroloader.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum CLI {
	INSTANCE;

	private final Logger logger;

	CLI() {
		this.logger = LoggerFactory.getLogger(getClass());
	}
}
