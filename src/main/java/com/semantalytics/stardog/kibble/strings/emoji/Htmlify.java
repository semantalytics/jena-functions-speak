package com.semantalytics.stardog.kibble.strings.emoji;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import emoji4j.EmojiUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class Htmlify extends AbstractFunction implements StringFunction {

    protected Htmlify() {
        super(1, EmojiVocabulary.htmlify.stringValue());
    }

    private Htmlify(final Htmlify htmlify) {
        super(htmlify);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(EmojiUtils.htmlify(string));
    }

    @Override
    public Htmlify copy() {
        return new Htmlify(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return EmojiVocabulary.htmlify.name();
    }
}
