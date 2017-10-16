package com.semantalytics.stardog.kibble.strings.similarity;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class Damerau extends AbstractFunction implements StringFunction {

    private static final info.debatty.java.stringsimilarity.Damerau damerau;

    static {
        damerau = new info.debatty.java.stringsimilarity.Damerau();
    }

    protected Damerau() {
        super(2, StringSimilarityVocabulary.damerau.stringValue());
    }

    private Damerau(final Damerau damerau) {
        super(damerau);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string1 = assertStringLiteral(values[0]).stringValue();
        final String string2 = assertStringLiteral(values[1]).stringValue();

        return literal(damerau.distance(string1, string2));
    }

    public Function copy() {
        return new Damerau(this);
    }

    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringSimilarityVocabulary.damerau.name();
    }
}