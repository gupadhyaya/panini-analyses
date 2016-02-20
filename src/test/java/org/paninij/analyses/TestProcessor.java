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

package org.paninij.analyses;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.Trees;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A simple annotation processor for testing {@link SunTreeAdapter}.
 *
 * @author dwtj
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class TestProcessor extends AbstractProcessor {

    Trees treeUtils;
    SunTreeAdapter treeAdapter;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        treeUtils = Trees.instance(processingEnv);
        treeAdapter = new SunTreeAdapter();
    }

    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Hello, World!");
        for (CompilationUnitTree root : getCompilationUnitTrees(roundEnv)) {
            System.out.println(treeAdapter.adapt(root).toString());
        }
        return false;
    }

    List<CompilationUnitTree> getCompilationUnitTrees(RoundEnvironment roundEnv) {
        List<CompilationUnitTree> trees = new ArrayList<CompilationUnitTree>();
        for (Element root : roundEnv.getRootElements()) {
            trees.add(treeUtils.getPath(root).getCompilationUnit());
        }
        return trees;
    }
}
