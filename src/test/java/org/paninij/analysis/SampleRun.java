package org.paninij.analysis;

import static java.util.Arrays.asList;
import static org.paninij.analysis.TestProcessorRunner.finish;
import static org.paninij.analysis.TestProcessorRunner.processJavaSourceResourceFiles;

import java.util.Collection;
import java.util.List;

public class SampleRun {
	private final static String SRC_DIR_PREFIX = "org/paninij/proc/helloworld/";
	private final static String FUTURE_DIR_PREFIX = "org/paninij/runtime/futures/";
	private final static String MSG_DIR_PREFIX = "org/paninij/runtime/messages/";
	
	public SampleRun() {
		try {
			processResourceFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final Collection<String> knownTestResource() {
		List<String> resources = asList(
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
				"Stream.java",
				"StreamTemplate.java",
				"ConsoleTemplate.java", "GreeterTemplate.java",
				"HelloWorldTemplate.java", "HelloWorldShortTemplate.java",
				"boolean$Future$.java",
				"int$array$array$Future$.java",
				"int$array$Future$.java",
				"int$Future$.java",
				"java_lang_Object$array$array$Future$.java",
				"java_lang_Object$array$Future$.java",
				"java_lang_Object$Future$.java",
				"java_lang_Object$Future$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool$bool.java",
				"java_lang_Object$Future$int.java",
				"java_lang_Object$Future$ref$ref.java",
				"java_lang_Object$Future$ref.java",
				"java_lang_String$Future$.java",
				"java_lang_String$Future$int$bool.java",
				"long$Future$bool.java",
				"org_paninij_proc_check_duckability_ClassWithPrivateFinalMethod$Future$.java",
				"org_paninij_proc_shapes_interfaces_MyInterface$Future$.java",
				"void$Future$.java", "void$Future$ref.java",
				"void$Simple$.java", "void$Simple$int.java",
				"void$Simple$ref.java"
				);

		// 31 helloworld 0 
		// 18 futures
		// 3 messages
		

		// Add prefix to each resource.
		for (int i = 0; i < 31; i++) {
			resources.set(i, SRC_DIR_PREFIX + resources.get(i));
		}

		// Add prefix to each resource.
		for (int i = 31; i < 49; i++) {
			resources.set(i, FUTURE_DIR_PREFIX + resources.get(i));
		}

		// Add prefix to each resource.
		for (int i = 49; i < 52; i++) {
			resources.set(i, MSG_DIR_PREFIX + resources.get(i));
		}

		return resources;
	}
	
	public void processResourceFile() {
		for (String resourceFile: knownTestResource())
			processJavaSourceResourceFiles(resourceFile);
		finish();
	}
	
	public static void main(String[] args) {
		
		SampleRun run = new SampleRun();
	}

}
