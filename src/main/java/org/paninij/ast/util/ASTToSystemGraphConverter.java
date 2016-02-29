package org.paninij.ast.util;

import java.util.ArrayList;
import java.util.List;

import org.paninij.systemgraph.SystemGraph;
import org.paninij.systemgraph.SystemGraphBuilder;

import boa.types.Ast.*;

public class ASTToSystemGraphConverter {
	public final List<ASTRoot> roots;
	public final SystemGraphBuilder sysgraphBuilder;
	
	public ASTToSystemGraphConverter() {
		this.roots = new ArrayList<ASTRoot>();
		this.sysgraphBuilder = new SystemGraphBuilder();
	}
	
	public void add (ASTRoot tree) {
		this.roots.add(tree);
	}
	
	/**
	 * builds systemgraph for each panini root capsule
	 */
	public final void buildGraph() {
		SystemGraph graph = this.sysgraphBuilder.createSystemGraph();
		System.out.println("ASTs to process: " + roots.size());
		// using this graph to add nodes and edges.
		for (ASTRoot astRoot : roots) {
			System.out.println();
		}
	}
}
