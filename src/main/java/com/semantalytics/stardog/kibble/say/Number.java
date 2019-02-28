package com.semantalytics.stardog.kibble.say;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.ibm.icu.text.RuleBasedNumberFormat;
import org.openrdf.model.Value;

import java.util.Locale;

public class Number extends AbstractFunction implements UserDefinedFunction {

    public Number() {
        super(1, SayVocabulary.number.stringValue());
    }

    private Number(final Number number) {
        super(number);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {
        final int value = assertNumericLiteral(values[0]).intValue();
        
        //TODO Handle language tag
        
        final String number = new RuleBasedNumberFormat(Locale.US, RuleBasedNumberFormat.SPELLOUT).format(value);

        return Values.literal(number);
    }

    @Override
    public Number copy() {
        return new Number(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return SayVocabulary.number.name();
    }
}
