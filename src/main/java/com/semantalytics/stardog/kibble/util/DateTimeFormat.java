package com.semantalytics.stardog.kibble.util;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.complexible.common.rdf.model.Values.literal;

public class DateTimeFormat extends AbstractFunction implements UserDefinedFunction {

    public DateTimeFormat() {
        super(2, UtilVocabulary.dateTimeFormat.stringValue());
    }

    private DateTimeFormat(final DateTimeFormat dateTimeFormat) {
        super(dateTimeFormat);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {

        final String time = assertStringLiteral(values[0]).stringValue();
        final String pattern = assertStringLiteral(values[1]).stringValue();

        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        return literal(LocalDate.parse(time, format).toString());
    }

    @Override
    public DateTimeFormat copy() {
        return new DateTimeFormat(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return UtilVocabulary.fromSpokenTime.name();
    }
}
