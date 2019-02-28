package com.semantalytics.stardog.kibble.say;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum SayVocabulary {

    number,
    numericOrdinal,
    ordinal,
    preciseTime,
    money,
    time;

    public static final String NAMESPACE = "http://semantalytics.com/2018/10/ns/stardog/kibble/say/";
    public final IRI iri;

    SayVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public static String sparqlPrefix(String prefixName) {
        return "PREFIX " + prefixName + ": <" + NAMESPACE + "> ";
    }

    public String stringValue() {
        return iri.stringValue();
    }
}