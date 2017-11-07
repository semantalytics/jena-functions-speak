package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class ContainsWhitespace extends AbstractFunction implements StringFunction {

    protected ContainsWhitespace() {
        super(2, StringVocabulary.containsWhitespace.toString());
    }

    private ContainsWhitespace(final ContainsWhitespace containsWhitespace) {
        super(containsWhitespace);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();

      return Values.literal(StringUtils.containsWhitespace(string));
    }

    @Override
    public ContainsWhitespace copy() {
        return new ContainsWhitespace(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.containsWhitespace.name();
    }
}
