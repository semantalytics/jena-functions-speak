package com.semantalytics.stardog.kibble.say;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

public class NumericOrdinal extends AbstractFunction implements UserDefinedFunction {

    protected NumericOrdinal() {
        super(1, SayVocabulary.numericOrdinal.stringValue());
    }

    public NumericOrdinal(final NumericOrdinal numericOrdinal) {
        super(numericOrdinal);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        return null;
    }

    @Override
    public Function copy() {
        return new NumericOrdinal(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return SayVocabulary.numericOrdinal.name();
    }
}
