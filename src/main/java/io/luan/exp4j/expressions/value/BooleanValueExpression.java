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

package io.luan.exp4j.expressions.value;

import io.luan.exp4j.Expression;
import io.luan.exp4j.ExpressionType;
import io.luan.exp4j.ExpressionVisitor;
import io.luan.exp4j.expressions.BooleanExpression;

public class BooleanValueExpression extends ValueExpression implements BooleanExpression {

    public static final BooleanValueExpression True = new BooleanValueExpression(true);
    public static final BooleanValueExpression False = new BooleanValueExpression(false);

    public BooleanValueExpression(boolean value) {
        super(value);
    }

    public Expression accept(ExpressionVisitor visitor) {
        return visitor.visitBooleanValue(this);
    }

    @Override
    public boolean equals(Expression other) {
        if (other instanceof BooleanValueExpression) {
            BooleanValueExpression otherExp = (BooleanValueExpression) other;
            return otherExp.getBooleanValue() == getBooleanValue();
        }
        return false;
    }

    public boolean getBooleanValue() {
        return (Boolean)getObject();
    }

    public int getSize() {
        return 1;
    }

    public ExpressionType getType() {
        return ExpressionType.BooleanValue;
    }
}
