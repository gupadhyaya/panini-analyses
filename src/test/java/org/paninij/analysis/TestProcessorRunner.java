/*
 * Copyright 2016, Hridesh Rajan, Robert Dyer, David Johnston
 *                 and Iowa State University of Science and Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.paninij.analysis;

import boa.types.Ast;

import javax.tools.JavaFileObject;

import org.paninij.ast.util.ASTToSystemGraphConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaFileObjects.forResource;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;
import static org.junit.Assert.assertTrue;

public class TestProcessorRunner {
	
	public static ASTToSystemGraphConverter converter = new ASTToSystemGraphConverter();

    public static void processJavaSourceResourceFiles(Iterable<String> testSrcFiles) {
        TestProcessor proc = new TestProcessor();
        assert_().about(javaSources())
                .that(lookupJavaSourceResources(testSrcFiles))
                .processedWith(proc)
                .compilesWithoutError();
        assertTrue(proc.getASTRoots().size() >= 1);

        // invoke ASTToSystemGraph generator
        converter.add(proc.getASTRoots().get(0));
        /*for (Ast.ASTRoot root : proc.getASTRoots()) {
            System.out.println(root.toString());
        }*/
    }
    
    public static void finish() {
        converter.buildGraph();
    }

    public static void processJavaSourceResourceFiles(String... testSrcFiles) {
        processJavaSourceResourceFiles(Arrays.asList(testSrcFiles));
    }

    public static Iterable<JavaFileObject> lookupJavaSourceResources(Iterable<String> testSrcFiles) {
        List<JavaFileObject> files = new ArrayList<>();
        for (String file : testSrcFiles) {
            files.add(forResource(file));
        }
        return files;
    }

    public static Iterable<JavaFileObject> lookupJavaSourceResources(String... testSrcFiles) {
        return lookupJavaSourceResources(Arrays.asList(testSrcFiles));
    }
}