package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class StripStart extends AbstractFunction implements StringFunction {

    protected StripStart() {
        super(2, StringVocabulary.stripStart.stringValue());
    }

    private StripStart(final StripStart stripStart) {
        super(stripStart);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String stripChars = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.stripStart(string, stripChars));
    }

    @Override
    public StripStart copy() {
        return new StripStart(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.stripStart.name();
    }
}