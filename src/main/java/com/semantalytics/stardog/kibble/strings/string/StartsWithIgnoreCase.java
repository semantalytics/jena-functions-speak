package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class StartsWithIgnoreCase extends AbstractFunction implements StringFunction {

    protected StartsWithIgnoreCase() {
        super(2, StringVocabulary.startsWithIgnoreCase.stringValue());
    }

    private StartsWithIgnoreCase(final StartsWithIgnoreCase startsWithIgnoreCase) {
        super(startsWithIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String prefix = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.startsWithIgnoreCase(string, prefix));
    }

    @Override
    public StartsWithIgnoreCase copy() {
        return new StartsWithIgnoreCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.startsWithIgnoreCase.name();
    }
}