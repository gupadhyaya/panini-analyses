package org.paninij.systemgraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import javax.tools.JavaFileObject;

import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.List;

import boa.types.Ast.Modifier.Visibility;
import boa.types.Ast.*;

/**
 * 
 * @author ganeshau, yuheng
 *
 */
public class SystemGraph {
	public static class Node {
		public Set<Method> procedures = new HashSet<Method>();
		public HashMap<String, Node> connections = new HashMap<String, Node>();
		public Declaration capsule;
		public String name; // name of the capsule instance

		public Node(String name, Declaration capsuleDecl) {
			this.capsule = capsuleDecl;
			this.name = name;
			for (Method method : capsuleDecl.getMethodsList()) {
				addProc(method);
			}
		}

		private void addProc(Method method) {
			procedures.add(method);
		}

		public void addConnection(String name, Node node) {
			connections.put(name, node);
		}

		public String toString() {
			String str = capsule.getName() + " " + name + " {";
			for (Method m : procedures) {
				str += m.toString() + ",";
			}
			str += "}";
			return str;
		}
	}

	public static class Edge {
		public final Node fromNode, toNode;
		public final Method fromProcedure, toProcedure;
		// the source code postion of this call edge
		public final int pos, line;
		// the source code statement of this call edge
		public final Expression tree;
		// the source file that contains this effect
		public final JavaFileObject src;

		public Edge(Node fromNode, Method fromProcedure, Node toNode,
				Method toProcedure, int pos, int line, Expression tree,
				JavaFileObject src) {
			this.fromNode = fromNode;
			this.fromProcedure = fromProcedure;
			this.toNode = toNode;
			this.toProcedure = toProcedure;
			this.pos = pos;
			this.line = line;
			this.tree = tree;
			this.src = src;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			toString(sb);
			return sb.toString();
		}

		public void toString(StringBuilder sb) {
			sb.append(fromNode.name);
			sb.append(".");
			sb.append(fromProcedure);
			sb.append(" --> ");
			sb.append(toNode.name);
			sb.append(".");
			sb.append(toProcedure);
			sb.append("\n");
		}

		public final int hashCode() {
			return fromNode.hashCode() + toNode.hashCode()
					+ fromProcedure.hashCode() + toProcedure.hashCode() + pos;
		}

		public final boolean equals(Object obj) {
			if (obj instanceof Edge) {
				Edge other = (Edge) obj;
				return fromNode.equals(other.fromNode)
						&& toNode.equals(other.toNode)
						&& fromProcedure.equals(other.fromProcedure)
						&& toProcedure.equals(other.toProcedure)
						&& pos == other.pos;
			}
			return false;
		}
	}

	public static class Path {
		public List<Node> nodes;

		public Path() {
			nodes = List.<Node> nil();
		}

		public Path(Node node) {
			nodes = List.<Node> nil();
			nodes = nodes.append(node);
		}

		public Path(Path path) {
			nodes = List.<Node> nil();
			nodes = nodes.appendList(path.nodes);
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			toString(sb);
			return sb.toString();
		}

		public void toString(StringBuilder sb) {
			sb.append(nodes.get(0).name);
			final int size = nodes.size();
			for (int i = 1; i < size; i++) {
				sb.append(" --> ");
				sb.append(nodes.get(i).name);
			}
		}
	}

	public HashMap<String, Node> nodes = new HashMap<String, Node>();
	public Set<Edge> edges = new HashSet<Edge>();
	// this is to save size of arrays. maybe view arrays as an whole instead.
	public HashMap<String, Integer> capsuleArrays = new HashMap<String, Integer>();

	public void addNode(String name, Declaration sym) {
		Assert.check(!nodes.containsKey(name),
				"Graph already contains node for " + name);
		nodes.put(name, new Node(name, sym));
	}

	public void setConnection(String fromNode, String alias, String toNode) {
		if (!toNode.toString().equals("null")) {
			nodes.get(fromNode).addConnection(alias, nodes.get(toNode));
		} else
			nodes.get(fromNode).addConnection(alias, null);
	}

	public void setEdge(Node fromNode, Method fromProc, Node toNode, Method toProc,
			int pos, int line, Expression tree, JavaFileObject src) {
		edges.add(new Edge(fromNode, fromProc, toNode, toProc, pos, line, tree,
				src));
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Nodes: \n");
		for (Node node : nodes.values()) {
			s.append("\t");
			s.append(node);
			s.append("\n");
		}
		s.append("Connections: \n");
		for (Node node : nodes.values()) {
			s.append("\tNode ");
			s.append(node.name);
			s.append(":\n");
			for (Entry<String, Node> c : node.connections.entrySet()) {
				s.append("\t\t");
				s.append(c.getKey());
				s.append(" --> ");
				if (c.getValue() != null)
					s.append(c.getValue().name);
				else
					s.append("null");
				s.append("\n");
				;
			}
		}
		s.append("Edges: \n");
		for (Edge edge : edges) {
			if (edge.fromProcedure.toString().contains("$Original")
					|| edge.toProcedure.toString().contains("$Original")
					|| !isPublic(edge.fromProcedure, edge.toProcedure))
				continue;
			edge.toString(s);
		}
		return s.toString();
	}

	public List<Edge> getEdges(Node head, Method fromSym, List<Node> tail) {
		List<Edge> edges = List.<Edge> nil();
		for (Edge e : this.edges) {
			if (e.fromNode == head
					&& e.fromProcedure.toString().equals(fromSym.toString())
					&& e.toNode == tail.head) {
				edges = edges.append(e);
			}
		}
		return edges;
	}
	//TODO: detectCyclicReferences is not implemented for now.
	
	private boolean isPublic(Method fromProcedure, Method toProcedure) {
		if (isPublic(fromProcedure) && isPublic(toProcedure))
			return true;
		return false;
	}

	private boolean isPublic(Method procedure) {
		for (Modifier modifier : procedure.getModifiersList()) {
			if (modifier.getVisibility() == Visibility.PUBLIC)
				return true;
		}
		return false;
	}
}
