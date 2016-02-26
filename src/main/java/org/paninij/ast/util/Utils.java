package org.paninij.ast.util;

import boa.types.Ast.*;

import com.sun.tools.javac.util.List;

public class Utils {

	public static final List<Type> getParameterTypes(Method method) {
		List<Type> argTypes = List.<Type>nil();
		for (Variable methodArg : method.getArgumentsList()) {
			argTypes.add(methodArg.getVariableType());
		}
		return argTypes;
	}
	
	public static final boolean isArrayType(Type type) {
		if (type.getName().contains("["))
			return true;
		return false;
	}
}
