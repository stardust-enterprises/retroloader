package fr.stardustenterprises.retroloader.cli;

import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import java.io.File;
import java.io.IOException;

public class Main {
	private static final String VERSION = "@VERSION@";

	public static void main(String[] args) throws IOException {
		OptionParser parser = new OptionParser();

		OptionSpec<File> input =
				parser.accepts("input", "The input file")
						.withRequiredArg()
						.ofType(File.class)
						.required();

		OptionSpec<?> help =
				parser.accepts("help", "Prints a help page")
						.forHelp();
		OptionSpec<?> version =
				parser.accepts("version", "Prints the version")
						.forHelp();

		OptionSet parse;

		try {
			parse = parser.parse(args);
		} catch (OptionException e) {
			System.err.println(e.getMessage() + " (try --help)");
			return;
		}

		if (parse.has(help)) {
			parser.printHelpOn(System.err);
			return;
		}
		if (parse.has(version)) {
			System.out.println("RetroLoader CLI - v" + VERSION);
			return;
		}
	}
}
