package com.semantalytics.stardog.kibble.say;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.ibm.icu.text.RuleBasedNumberFormat;
import org.openrdf.model.Value;
import pl.allegro.finance.tradukisto.MoneyConverters;

import java.math.BigDecimal;
import java.util.Locale;

public class Money extends AbstractFunction implements UserDefinedFunction {

    public Money() {
        super(1, SayVocabulary.money.stringValue());
    }

    private Money(final Money number) {
        super(number);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {
        final int value = assertNumericLiteral(values[0]).intValue();
        
        //TODO Handle language tag
        
        MoneyConverters converter = MoneyConverters.ENGLISH_BANKING_MONEY_VALUE;
        return Values.literal(converter.asWords(new BigDecimal(value)));
    }

    @Override
    public Money copy() {
        return new Money(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return SayVocabulary.money.name();
    }
}
