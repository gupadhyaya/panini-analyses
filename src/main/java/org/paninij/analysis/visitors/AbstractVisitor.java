/*
 * Copyright 2014, Hridesh Rajan, Robert Dyer, 
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
package org.paninij.analysis.visitors;

import java.util.List;

import boa.types.Ast.*;

/**
 * Abstract AST visitor inspired from BoaAbstractVisitor
 * 
 * The <code>visit()</code> methods first call <code>preVisit()</code> for the node.
 * If <code>preVisit()</code> returns <code>true</code>, then each of that node's children are visited and then <code>postVisit()</code> is called.
 * 
 * By default, all <code>preVisit()</code> methods call {@link #defaultPreVisit()} and return <code>true</code>.
 * By default, all <code>postVisit()</code> methods call {@link #defaultPostVisit()}.
 * 
 * @author rdyer
 * @author ganeshau
 */
public abstract class AbstractVisitor {
	/**
	 * Initializes any visitor-specific data before starting a visit.
	 * 
	 * @return itself, to allow method chaining
	 */
	public AbstractVisitor initialize() {
		return this;
	}

	/**
	 * Provides a default action for pre-visiting nodes.
	 * Any <code>preVisit()</code> method that is not overridden calls this method.
	 * 
	 * @return always returns true
	 */
	protected boolean defaultPreVisit() throws Exception {
		return true;
	}

	protected boolean preVisit(final ASTRoot node) throws Exception {
		return defaultPreVisit();
	}
	protected boolean preVisit(final Namespace node) throws Exception {
		return defaultPreVisit();
	}
	protected boolean preVisit(final Declaration node) throws Exception {
		return defaultPreVisit();
	}
	protected boolean preVisit(final Type node) throws Exception {
		return defaultPreVisit();
	}
	protected boolean preVisit(final Method node) throws Exception {
		return defaultPreVisit();
	}
	protected boolean preVisit(final Variable node) throws Exception {
		return defaultPreVisit();
	}
	protected boolean preVisit(final Statement node) throws Exception {
		return defaultPreVisit();
	}
	protected boolean preVisit(final Expression node) throws Exception {
		return defaultPreVisit();
	}
	protected boolean preVisit(final Modifier node) throws Exception {
		return defaultPreVisit();
	}
	protected boolean preVisit(final Comment node) throws Exception {
		return defaultPreVisit();
	}

	/**
	 * Provides a default action for post-visiting nodes.
	 * Any <code>postVisit()</code> method that is not overridden calls this method.
	 */
	protected void defaultPostVisit() throws Exception { }

	protected void postVisit(final ASTRoot node) throws Exception {
		defaultPostVisit();
	}
	protected void postVisit(final Namespace node) throws Exception {
		defaultPostVisit();
	}
	protected void postVisit(final Declaration node) throws Exception {
		defaultPostVisit();
	}
	protected void postVisit(final Type node) throws Exception {
		defaultPostVisit();
	}
	protected void postVisit(final Method node) throws Exception {
		defaultPostVisit();
	}
	protected void postVisit(final Variable node) throws Exception {
		defaultPostVisit();
	}
	protected void postVisit(final Statement node) throws Exception {
		defaultPostVisit();
	}
	protected void postVisit(final Expression node) throws Exception {
		defaultPostVisit();
	}
	protected void postVisit(final Modifier node) throws Exception {
		defaultPostVisit();
	}
	protected void postVisit(final Comment node) throws Exception {
		defaultPostVisit();
	}

	public final void visit(final ASTRoot node) throws Exception {
		if (preVisit(node)) {
			final List<Namespace> namespacesList = node.getNamespacesList();
			final int namespacesSize = namespacesList.size();
			for (int i = 0; i < namespacesSize; i++)
				visit(namespacesList.get(i));

			postVisit(node);
		}
	}
	public final void visit(final Namespace node) throws Exception {
		if (preVisit(node)) {
			final List<Declaration> declarationsList = node.getDeclarationsList();
			final int declarationsSize = declarationsList.size();
			for (int i = 0; i < declarationsSize; i++)
				visit(declarationsList.get(i));

			final List<Modifier> modifiersList = node.getModifiersList();
			final int modifiersSize = modifiersList.size();
			for (int i = 0; i < modifiersSize; i++)
				visit(modifiersList.get(i));

			postVisit(node);
		}
	}
	public final void visit(final Declaration node) throws Exception {
		if (preVisit(node)) {
			final List<Modifier> modifiersList = node.getModifiersList();
			final int modifiersSize = modifiersList.size();
			for (int i = 0; i < modifiersSize; i++)
				visit(modifiersList.get(i));

			final List<Type> genericParamsList = node.getGenericParametersList();
			final int genericParamsSize = genericParamsList.size();
			for (int i = 0; i < genericParamsSize; i++)
				visit(genericParamsList.get(i));

			final List<Type> parentsList = node.getParentsList();
			final int parentsSize = parentsList.size();
			for (int i = 0; i < parentsSize; i++)
				visit(parentsList.get(i));

			final List<Method> methodsList = node.getMethodsList();
			final int methodsSize = methodsList.size();
			for (int i = 0; i < methodsSize; i++)
				visit(methodsList.get(i));

			final List<Variable> fieldsList = node.getFieldsList();
			final int fieldsSize = fieldsList.size();
			for (int i = 0; i < fieldsSize; i++)
				visit(fieldsList.get(i));

			final List<Declaration> nestedList = node.getNestedDeclarationsList();
			final int nestedSize = nestedList.size();
			for (int i = 0; i < nestedSize; i++)
				visit(nestedList.get(i));

			postVisit(node);
		}
	}
	public final void visit(final Type node) throws Exception {
		if (preVisit(node)) {
			postVisit(node);
		}
	}
	public final void visit(final Method node) throws Exception {
		if (preVisit(node)) {
			visit(node.getReturnType());

			final List<Modifier> modifiersList = node.getModifiersList();
			final int modifiersSize = modifiersList.size();
			for (int i = 0; i < modifiersSize; i++)
				visit(modifiersList.get(i));

			final List<Type> genericParametersList = node.getGenericParametersList();
			final int genericParametersSize = genericParametersList.size();
			for (int i = 0; i < genericParametersSize; i++)
				visit(genericParametersList.get(i));

			final List<Variable> argumentsList = node.getArgumentsList();
			final int argumentsSize = argumentsList.size();
			for (int i = 0; i < argumentsSize; i++)
				visit(argumentsList.get(i));

			final List<Type> exceptionTypesList = node.getExceptionTypesList();
			final int exceptionTypesSize = exceptionTypesList.size();
			for (int i = 0; i < exceptionTypesSize; i++)
				visit(exceptionTypesList.get(i));

			final List<Statement> statementsList = node.getStatementsList();
			final int statementsSize = statementsList.size();
			for (int i = 0; i < statementsSize; i++)
				visit(statementsList.get(i));

			postVisit(node);
		}
	}
	public final void visit(final Variable node) throws Exception {
		if (preVisit(node)) {
			visit(node.getVariableType());

			final List<Modifier> modifiersList = node.getModifiersList();
			final int modifiersSize = modifiersList.size();
			for (int i = 0; i < modifiersSize; i++)
				visit(modifiersList.get(i));

			if (node.hasInitializer())
				visit(node.getInitializer());

			postVisit(node);
		}
	}
	public final void visit(final Statement node) throws Exception {
		if (preVisit(node)) {
			final List<Statement> statementsList = node.getStatementsList();
			final int statementsSize = statementsList.size();
			for (int i = 0; i < statementsSize; i++)
				visit(statementsList.get(i));
			// TODO: check why initializations are now statements instead of expressions?
			final List<Statement> initsList = node.getInitializationsList();
			final int initsSize = initsList.size();
			for (int i = 0; i < initsSize; i++)
				visit(initsList.get(i));

			if (node.hasCondition())
				visit(node.getCondition());

			final List<Expression> updatesList = node.getUpdatesList();
			final int updatesSize = updatesList.size();
			for (int i = 0; i < updatesSize; i++)
				visit(updatesList.get(i));

			if (node.hasVariableDeclaration())
				visit(node.getVariableDeclaration());

			if (node.hasTypeDeclaration())
				visit(node.getTypeDeclaration());

			if (node.hasExpression())
				visit(node.getExpression());

			postVisit(node);
		}
	}
	public final void visit(final Expression node) throws Exception {
		if (preVisit(node)) {
			final List<Expression> expressionsList = node.getExpressionsList();
			final int expressionsSize = expressionsList.size();
			for (int i = 0; i < expressionsSize; i++)
				visit(expressionsList.get(i));

			final List<Variable> varDeclsList = node.getVariableDeclsList();
			final int varDeclsSize = varDeclsList.size();
			for (int i = 0; i < varDeclsSize; i++)
				visit(varDeclsList.get(i));

			if (node.hasType())
				visit(node.getType());

			final List<Type> genericParametersList = node.getGenericParametersList();
			final int genericParametersSize = genericParametersList.size();
			for (int i = 0; i < genericParametersSize; i++)
				visit(genericParametersList.get(i));

			final List<Expression> methodArgsList = node.getMethodArgsList();
			final int methodArgsSize = methodArgsList.size();
			for (int i = 0; i < methodArgsSize; i++)
				visit(methodArgsList.get(i));

			if (node.hasAnonDeclaration())
				visit(node.getAnonDeclaration());

			postVisit(node);
		}
	}
	public final void visit(final Modifier node) throws Exception {
		if (preVisit(node)) {
			final List<Expression> annotationValuesList = node.getAnnotationValuesList();
			final int annotationValuesSize = annotationValuesList.size();
			for (int i = 0; i < annotationValuesSize; i++)
				visit(annotationValuesList.get(i));

			postVisit(node);
		}
	}
	public final void visit(final Comment node) throws Exception {
		if (preVisit(node)) {
			postVisit(node);
		}
	}
}
