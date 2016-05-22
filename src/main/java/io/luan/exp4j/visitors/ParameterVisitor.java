/*
 * Copyright 2016 Guangmiao Luan
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

package io.luan.exp4j.visitors;

import io.luan.exp4j.Expression;
import io.luan.exp4j.expressions.arithmetic.PowerExpression;
import io.luan.exp4j.expressions.arithmetic.ProductExpression;
import io.luan.exp4j.expressions.arithmetic.SumExpression;
import io.luan.exp4j.expressions.function.FunctionExpression;
import io.luan.exp4j.expressions.symbolic.ParameterExpression;

import java.util.HashSet;
import java.util.Set;

public class ParameterVisitor extends BaseExpressionVisitor {

    private HashSet<ParameterExpression> params = new HashSet<ParameterExpression>();

    public Set<ParameterExpression> getParameters() {
        return params;
    }

    public Expression visitFunction(FunctionExpression expression) {
        for (Expression param : expression.getFuncParams()) {
            param.accept(this);
        }
        return super.visitFunction(expression);
    }

    public Expression visitParameter(ParameterExpression expression) {
        params.add(expression);
        return super.visitParameter(expression);
    }

    public Expression visitPower(PowerExpression expression) {
        expression.getBase().accept(this);
        expression.getExponent().accept(this);
        return super.visitPower(expression);
    }

    public Expression visitProduct(ProductExpression expression) {
        for (Expression operand : expression.getOperands()) {
            operand.accept(this);
        }
        return super.visitProduct(expression);
    }

    public Expression visitSum(SumExpression expression) {
        for (Expression operand : expression.getOperands()) {
            operand.accept(this);
        }
        return super.visitSum(expression);
    }
}
