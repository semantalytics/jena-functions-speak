package com.semantalytics.stardog.kibble.say;

import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;


public class TestOrdinalize extends AbstractStardogTest {

    public static final String DB = "test";

    @BeforeClass
    public static void beforeClass() {

        try(final AdminConnection adminConnection = AdminConnectionConfiguration.toEmbeddedServer()
                .connect()) {

            if (adminConnection.list().contains(DB)) {
                adminConnection.drop(DB);
            }

            adminConnection.newDatabase(DB).create();
        }
    }

    @Before
    public void setUp() {
        connection = ConnectionConfiguration.to(DB)
                .connect();

        /*
        connection.begin();
        connection.add().statement(Values.iri("http://example/test"),
                Values.iri("http://creativecommons.org/ns#license"),
                Values.iri("http://example.com/testLicense"));
        connection.commit();
        */
    }

    @After
    public void tearDown() {
        connection.close();
    }

    @Test
    public void testOrdinalize() {

            final String aQuery = "prefix say: <http://semantalytics.com/2017/09/ns/stardog/kibble/say/> " +
                    "select ?result where { bind(say:ordinalize(1) as ?result) } ";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("1st", aValue);
                assertFalse("Should have no more results", aResult.hasNext());

            }
    }
}
