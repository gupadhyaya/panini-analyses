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
		List<String> resources = 
				
		/*asList(
		"ConsoleTemplate.java",
		"GreeterTemplate.java",
		"HelloWorldShortTemplate.java",
		"HelloWorldTemplate.java",
		"StreamTemplate.java"
		);*/
		
		asList(
				"Console$Mockup.java", 
				"Console$Monitor.java", 
				"Console$Serial.java", 
				"Console$Task.java", 
				"Console$Thread.java", 
				"Console.java", 
				"Greeter$Mockup.java", 
				"Greeter$Monitor.java", 
				"Greeter$Serial.java", 
				"Greeter$Task.java", 
				"Greeter$Thread.java", 
				"Greeter.java", 
				"HelloWorld$Mockup.java", 
				"HelloWorld$Monitor.java", 
				"HelloWorld$Serial.java", 
				"HelloWorld$Task.java", 
				"HelloWorld$Thread.java", 
				"HelloWorld.java", 
				"HelloWorldShort$Mockup.java", 
				"HelloWorldShort$Monitor.java", 
				"HelloWorldShort$Serial.java", 
				"HelloWorldShort$Task.java", 
				"HelloWorldShort$Thread.java", 
				"HelloWorldShort.java", 
				"Stream$Mockup.java", 
				"Stream.java"
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
