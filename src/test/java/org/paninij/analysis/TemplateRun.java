package org.paninij.analysis;

import static java.util.Arrays.asList;
import static org.paninij.analysis.TestProcessorRunner.finish;
import static org.paninij.analysis.TestProcessorRunner.processJavaSourceResourceFiles;

import java.util.Collection;
import java.util.List;

public class TemplateRun {
	private final static String SRC_DIR_PREFIX = "org/paninij/proc/helloworld/";

	public TemplateRun() {
		try {
			processResourceFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final Collection<String> knownTestResource() {
		List<String> resources = asList(
				"StreamTemplate.java",
				"ConsoleTemplate.java", "GreeterTemplate.java",
				"HelloWorldTemplate.java"
				);

		// Add prefix to each resource.
		for (int i = 0; i < resources.size(); i++) {
			resources.set(i, SRC_DIR_PREFIX + resources.get(i));
		}

		return resources;
	}
	
	public void processResourceFile() {
		for (String resourceFile: knownTestResource())
			processJavaSourceResourceFiles(resourceFile);
		finish();
	}
	
	public static void main(String[] args) {
		TemplateRun run = new TemplateRun();

	}

}
