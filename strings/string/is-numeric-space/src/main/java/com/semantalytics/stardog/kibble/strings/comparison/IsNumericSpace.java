package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class IsNumericSpace extends AbstractFunction implements StringFunction {

    protected IsNumericSpace() {
        super(1, StringVocabulary.isNumericSpace.toString());
    }

    private IsNumericSpace(final IsNumericSpace isNumericSpace) {
        super(isNumericSpace);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(StringUtils.isNumericSpace(string));
    }

    @Override
    public IsNumericSpace copy() {
        return new IsNumericSpace(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.isNumericSpace.name();
    }
}
