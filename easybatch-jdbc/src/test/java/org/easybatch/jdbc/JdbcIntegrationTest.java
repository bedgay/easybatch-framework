package org.easybatch.jdbc;

import org.easybatch.core.api.ComputationalRecordProcessor;
import org.easybatch.core.api.Report;
import org.easybatch.core.api.Status;
import org.easybatch.core.impl.Engine;
import org.easybatch.core.impl.EngineBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for database processing.
 *
 * @author Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 */
public class JdbcIntegrationTest {

    private static final String DATABASE_URL = "jdbc:hsqldb:mem";

    private static final String DATA_SOURCE_NAME = "Connection URL: jdbc:hsqldb:mem | Query string: select id, name from person";

    private Connection connection;

    private String query;

    @BeforeClass
    public static void init() {
        System.setProperty("hsqldb.reconfig_logging", "false");
    }

    @Before
    public void setUp() throws Exception {
        connection = DriverManager.getConnection(DATABASE_URL, "sa", "pwd");
        createPersonTable(connection);
        populatePersonTable(connection);
        query = "select id, name from person";
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDatabaseProcessing() throws Exception {

        ComputationalRecordProcessor personProcessor = new PersonProcessor();

        Engine engine = EngineBuilder.aNewEngine()
                .reader(new JdbcRecordReader(connection, query))
                .mapper(new JdbcRecordMapper<Person>(Person.class, new String[]{"id", "name"}))
                .processor(personProcessor)
                .build();

        Report report = engine.call();

        assertThat(report).isNotNull();
        assertThat(report.getTotalRecords()).isEqualTo(2);
        assertThat(report.getErrorRecordsCount()).isEqualTo(0);
        assertThat(report.getFilteredRecordsCount()).isEqualTo(0);
        assertThat(report.getIgnoredRecordsCount()).isEqualTo(0);
        assertThat(report.getRejectedRecordsCount()).isEqualTo(0);
        assertThat(report.getSuccessRecordsCount()).isEqualTo(2);
        assertThat(report.getStatus()).isEqualTo(Status.FINISHED);
        assertThat(report.getDataSource()).isEqualTo(DATA_SOURCE_NAME);

        List<Person> persons = (List<Person>) personProcessor.getComputationResult();

        assertThat(persons).isNotEmpty().hasSize(2);

        final Person person1 = persons.get(0);
        assertThat(person1.getId()).isEqualTo(1);
        assertThat(person1.getName()).isEqualTo("foo");

        final Person person2 = persons.get(1);
        assertThat(person2.getId()).isEqualTo(2);
        assertThat(person2.getName()).isEqualTo("bar");
    }

    @After
    public void tearDown() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        //delete hsqldb tmp files
        new File("mem.log").delete();
        new File("mem.properties").delete();
        new File("mem.script").delete();
        new File("mem.tmp").delete();
    }

    private void createPersonTable(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        String query = "CREATE TABLE if not exists person (\n" +
                "  id integer NOT NULL PRIMARY KEY,\n" +
                "  name varchar(32) NOT NULL,\n" +
                ");";
        statement.executeUpdate(query);
        statement.close();
    }

    private void populatePersonTable(Connection connection) throws Exception {
        executeQuery(connection, "INSERT INTO person VALUES (1,'foo');");
        executeQuery(connection, "INSERT INTO person VALUES (2,'bar');");
    }

    private void executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    private class PersonProcessor implements ComputationalRecordProcessor<Person, Person, List<Person>> {

        private List<Person> persons = new ArrayList<Person>();

        @Override
        public Person processRecord(Person person) throws Exception {
            persons.add(person);
            return person;
        }

        @Override
        public List<Person> getComputationResult() {
            return persons;
        }

    }

}
