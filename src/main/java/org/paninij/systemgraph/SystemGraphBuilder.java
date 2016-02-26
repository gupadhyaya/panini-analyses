package org.paninij.systemgraph;

import org.paninij.ast.util.Utils;

import com.sun.tools.javac.util.List;

import boa.types.Ast.*;

/**
 * 
 * @author ganeshau, yuheng
 *
 */
public class SystemGraphBuilder {

	public SystemGraphBuilder() {

	}

	public SystemGraph createSystemGraph() {
		return new SystemGraph();
	}

	/**
	 * Adds a single Node with the name and capsuleSymbol to graph
	 */
	public void addSingleNode(SystemGraph graph, String name,
			final Declaration c) {
		graph.addNode(name, c);
	}

	/**
	 * Adds an array of capsules as multiple nodes. Name of these nodes are
	 * represented as "capsuleName[index]" in the graph
	 */
	public void addMultipleNodes(SystemGraph graph, String name, int amount,
			final Declaration c) {
		for (int i = 0; i < amount; i++) {
			graph.addNode(name + "[" + i + "]", c);
		}
		graph.capsuleArrays.put(name, amount);
	}

	public void addConnection(SystemGraph graph, String fromNode, String arg,
			String toNode) {
		graph.setConnection(fromNode, arg, toNode);
	}

	/**
	 * Connects a single capsule to a capsule Array
	 */
	public void addConnectionsOneToMany(SystemGraph graph, String fromNode,
			String arg, String toNode) {
		int amount = graph.capsuleArrays.get(toNode);
		for (int i = 0; i < amount; i++) {
			addConnection(graph, fromNode, (arg + "[" + i + "]"), (toNode + "["
					+ i + "]"));
		}
	}

	/**
	 * Connects from every capsule of a capsule array to a single capsule. Used
	 * for foreach loops in systems
	 */
	public void addConnectionsManyToOne(SystemGraph graph, String fromNode,
			String arg, String toNode) {
		int amount = graph.capsuleArrays.get(fromNode);
		for (int i = 0; i < amount; i++) {
			addConnection(graph, (fromNode + "[" + i + "]"), arg, toNode);
		}
	}

	/**
	 * Connects from every capsule of a capsule array to a capsule array. Used
	 * for foreach loops in systems
	 */
	public void addConnectionsManyToMany(SystemGraph graph, String fromNode,
			String arg, String toNode) {
		int fromAmount = graph.capsuleArrays.get(fromNode);
		int toAmount = graph.capsuleArrays.get(fromNode);
		for (int i = 0; i < fromAmount; i++) {
			for (int j = 0; j < toAmount; j++)
				addConnection(graph, (fromNode + "[" + i + "]"),
						(arg + "[" + i + "]"), (toNode + "[" + j + "]"));
		}
	}
	
	private static final String types(Method ms) {
		List<Type> args = Utils.getParameterTypes(ms);
		StringBuilder buf = new StringBuilder();
		buf.append(ms.getName().toString() + "(");

		if (!args.isEmpty()) {
			while (args.tail.nonEmpty()) {
				String temp = args.head.toString();
				int index = temp.indexOf("<");

				// remove the polymorphic type info
				if (index == -1) { buf.append(temp);
				} else { buf.append(temp.substring(0, index)); }
				args = args.tail;
				buf.append(',');
			}
			if (Utils.isArrayType(args.head)) {
				buf.append(args.head); //((ArrayType)args.head).elemtype);
				buf.append("...");
			} else {
				String temp = args.head.toString();
				int index = temp.indexOf("<");
	
				// remove the polymorphic type info
				if (index == -1) { buf.append(temp);
				} else { buf.append(temp.substring(0, index)); }
			}
		}

		buf.append(")");
		return buf.toString();
	}
}
