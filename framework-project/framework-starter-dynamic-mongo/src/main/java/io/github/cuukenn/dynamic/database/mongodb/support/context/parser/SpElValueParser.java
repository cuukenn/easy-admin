/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.cuukenn.dynamic.database.mongodb.support.context.parser;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @author changgg
 */
public class SpElValueParser extends AbstractChainValueParser {
	/**
	 * 参数发现器
	 */
	private static final ParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();
	/**
	 * Express语法解析器
	 */
	private static final ExpressionParser PARSER = new SpelExpressionParser();
	/**
	 * 解析上下文的模板
	 */
	private ParserContext parserContext = new TemplateParserContext();
	private BeanResolver beanResolver;

	@Override
	public boolean match(String value) {
		return true;
	}

	@Override
	public String doParse(MethodInvocation invocation, String key) {
		Method method = invocation.getMethod();
		Object[] arguments = invocation.getArguments();
		ExpressionRootObject rootObject = new ExpressionRootObject(method, arguments, invocation.getThis());
		StandardEvaluationContext context = new MethodBasedEvaluationContext(rootObject, method, arguments, NAME_DISCOVERER);
		context.setBeanResolver(beanResolver);
		Expression expression;
		if (parserContext != null) {
			expression = PARSER.parseExpression(key, parserContext);
		} else {
			expression = PARSER.parseExpression(key);
		}
		final Object value = expression.getValue(context);
		return value == null ? null : value.toString();
	}

	public void setParserContext(ParserContext parserContext) {
		this.parserContext = parserContext;
	}

	public void setBeanResolver(BeanResolver beanResolver) {
		this.beanResolver = beanResolver;
	}

	public static class ExpressionRootObject {
		private final Method method;

		private final Object[] args;

		private final Object target;

		public ExpressionRootObject(Method method, Object[] args, Object target) {
			this.method = method;
			this.args = args;
			this.target = target;
		}

		public Method getMethod() {
			return method;
		}

		public Object[] getArgs() {
			return args;
		}

		public Object getTarget() {
			return target;
		}
	}
}
