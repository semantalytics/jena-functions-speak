package com.semantalytics.stardog.kibble.say;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.ibm.icu.text.RuleBasedNumberFormat;
import org.openrdf.model.Value;

import java.util.Locale;

public class Ordinal extends AbstractFunction implements UserDefinedFunction {

    public Ordinal() {
        super(1, SayVocabulary.ordinal.stringValue());
    }

    private Ordinal(final Ordinal ordinal) {
        super(ordinal);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {
        int number = assertNumericLiteral(values[0]).intValue();
        
        String ordinal = new RuleBasedNumberFormat(Locale.US, RuleBasedNumberFormat.ORDINAL).format(number);

        return Values.literal(ordinal);
    }

    @Override
    public Ordinal copy() {
        return new Ordinal(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return SayVocabulary.ordinal.name();
    }
}
