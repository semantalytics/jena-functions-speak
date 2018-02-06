package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class ReplaceEach extends AbstractFunction implements StringFunction {

    protected ReplaceEach() {
        super(3, StringVocabulary.replace.stringValue());
    }

    private ReplaceEach(final ReplaceEach replaceEach) {
        super(replaceEach);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String[] searchList = assertStringLiteral(values[1]).stringValue().split("\u001f");
        final String[] replacementList = assertStringLiteral(values[2]).stringValue().split("\u001f");

        return Values.literal(StringUtils.replaceEach(string, searchList, replacementList));
    }

    @Override
    public ReplaceEach copy() {
        return new ReplaceEach(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.replaceEach.name();
    }
}