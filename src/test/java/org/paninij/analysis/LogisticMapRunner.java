package org.paninij.analysis;

import static java.util.Arrays.asList;
import static org.paninij.analysis.TestProcessorRunner.finish;
import static org.paninij.analysis.TestProcessorRunner.processJavaSourceResourceFiles;

import java.util.Collection;
import java.util.List;

public class LogisticMapRunner {
	private final static String SRC_DIR_PREFIX = "edu/rice/habanero/benchmarks/logmap/";

	public LogisticMapRunner() {
		try {
			processResourceFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final Collection<String> knownTestResource() {
		List<String> resources = asList(
				"LogisticMapConfig.java",
				"LogisticMapTemplate.java", "RateComputerTemplate.java",
				"SeriesWorkerTemplate.java"
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
		// TODO Auto-generated method stub
		LogisticMapRunner run = new LogisticMapRunner();
	}

}
