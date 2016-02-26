package org.paninij.analysis;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.paninij.analysis.TestProcessorRunner.processJavaSourceResourceFiles;
import static java.util.Arrays.asList;

@RunWith(Parameterized.class)
public class SampleTest {
	private final static String SRC_DIR_PREFIX = "org/paninij/proc/helloworld/";
	private final String resourceFile;
	
	public SampleTest(String resourceFile) {
		this.resourceFile = resourceFile;
	}

	@Parameters(name = "{0}")
	public static Collection<String> knownTestResource() {
		List<String> resources = asList(
		"ConsoleTemplate.java",
		"GreeterTemplate.java",
		"HelloWorldShortTemplate.java",
		"HelloWorldTemplate.java",
		"StreamTemplate.java"
		);
		
		// Add prefix to each resource.
        for (int i = 0; i < resources.size(); i++) {
            resources.set(i, SRC_DIR_PREFIX + resources.get(i));
        }
        
        return resources;
	}
	
	@Test
    public void processResourceFile() {
        processJavaSourceResourceFiles(resourceFile);
    }

}
