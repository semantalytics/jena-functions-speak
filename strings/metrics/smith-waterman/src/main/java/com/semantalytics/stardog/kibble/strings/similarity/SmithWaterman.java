package com.semantalytics.stardog.kibble.strings.similarity;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.Expression;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.expr.Constant;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.openrdf.model.Value;
import org.simmetrics.metrics.functions.AffineGap;
import org.simmetrics.metrics.functions.MatchMismatch;

public final class SmithWaterman extends AbstractFunction implements StringFunction {

    private org.simmetrics.metrics.SmithWaterman smithWaterman;

    protected SmithWaterman() {
        super(Range.closed(2, 7), StringSimilarityVocab.SMITH_WATERMAN.iri().stringValue());
    }

    private SmithWaterman(final SmithWaterman smithWaterman) {
        super(smithWaterman);
    }

    @Override
    public void initialize() {
        smithWaterman = null;
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        return Values.literal(getSmithWatermanFunction(values).compare(firstString, secondString));
    }

    private org.simmetrics.metrics.SmithWaterman getSmithWatermanFunction(final Value... values) throws ExpressionEvaluationException {
        if (smithWaterman == null) {
            if (values.length == 7) {
                for(final Expression expression : getArgs()) {
                    if(!(expression instanceof Constant)) {
                        throw new ExpressionEvaluationException("Parameters 2 through 7 must be constant expressions");
                    }
                }
                final float gapA = assertNumericLiteral(values[2]).floatValue();
                final float gapB = assertNumericLiteral(values[3]).floatValue();
                
                final float subPenaltyA = assertNumericLiteral(values[4]).floatValue();
                final float subPenaltyB = assertNumericLiteral(values[5]).floatValue();
                
                final int windowSize = assertNumericLiteral(values[6]).intValue();
                
                smithWaterman = new org.simmetrics.metrics.SmithWaterman(new AffineGap(gapA, gapB), new MatchMismatch(subPenaltyA, subPenaltyB), windowSize);
            } else {
                smithWaterman = new org.simmetrics.metrics.SmithWaterman();
            }
        }
        return smithWaterman;
    }

    @Override
    public Function copy() {
        return new SmithWaterman(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Smith Waterman";
    }
}