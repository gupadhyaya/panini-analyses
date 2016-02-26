package org.paninij.ast.util;

import java.util.List;

import org.paninij.systemgraph.SystemGraph;
import org.paninij.systemgraph.SystemGraphBuilder;

import boa.types.Ast.*;

public class ASTToSystemGraphConverter {
	public final List<ASTRoot> roots;
	public final SystemGraphBuilder sysgraphBuilder;
	
	public ASTToSystemGraphConverter(List<ASTRoot> roots) {
		this.roots = roots;
		this.sysgraphBuilder = new SystemGraphBuilder();
		buildGraph();
	}
	
	private final void buildGraph() {
		SystemGraph graph = this.sysgraphBuilder.createSystemGraph();
		// using this graph to add nodes and edges.
	}
}
