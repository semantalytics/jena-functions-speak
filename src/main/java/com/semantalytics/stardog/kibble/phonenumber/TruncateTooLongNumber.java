

package com.semantalytics.stardog.kibble.phonenumber;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;
import static com.github.davidmoten.geo.GeoHash.*;

public final class IsAlphaNumber extends AbstractFunction implements UserDefinedFunction {

    protected IsAlphaNumber() {
        super(2, PhoneNumberVocabulary.isAlphaNumber.stringValue());
    }

    private IsAlphaNumber(final IsAlphaNumber isAlphaNumber) {
        super(isAlphaNumber);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
        return null;
    }

    @Override
    public Function copy() {
        return new IsAlphaNumber(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return PhoneNumberVocabulary.isAlphaNumber.name();
    }
}
