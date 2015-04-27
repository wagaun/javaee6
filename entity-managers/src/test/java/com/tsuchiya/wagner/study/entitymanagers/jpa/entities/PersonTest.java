package com.tsuchiya.wagner.study.entitymanagers.jpa.entities;

import com.tsuchiya.wagner.study.entitymanagers.jpa.dao.HibernatePeopleDAO;
import com.tsuchiya.wagner.study.entitymanagers.jpa.dao.JpaPeopleDAO;
import com.tsuchiya.wagner.study.entitymanagers.jpa.dao.PeopleDAO;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by wagner on 4/27/15.
 */
public class PersonTest {

    private Context context;

    @Before
    public void setUp() {
        // using hsql database
        final Properties p = new Properties();
        p.put("exampleDatabase", "new://Resource?type=DataSource");
        p.put("exampleDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("exampleDatabase.JdbcUrl", "jdbc:hsqldb:mem:moviedb");

        context = EJBContainer.createEJBContainer(p).getContext();
    }

    @After
    public void after() throws NamingException {
        context.close();
    }

    public void shouldSaveAndDeletePerson(PeopleDAO peopleDAO) throws Exception {
        peopleDAO.savePerson(new Person("Quentin Tarantino", PersonType.WEIRD));
        peopleDAO.savePerson(new Person("Joel Coen", PersonType.OK));
        peopleDAO.savePerson(new Person("Wagner Tsuchiya", PersonType.AWESOME));

        List<Person> list = peopleDAO.getPeople();
        assertEquals("List.size()", 3, list.size());

        for (Person person : list) {
            peopleDAO.deletePerson(person);
        }

        assertEquals("Movies.getMovies()", 0, peopleDAO.getPeople().size());
    }

    @Test
    public void shouldJpaSaveAndDeletePerson() throws Exception {
        JpaPeopleDAO jpaPeopleDAO = (JpaPeopleDAO) context.lookup("java:global/entity-managers/JpaPeopleDAO");
        shouldSaveAndDeletePerson(jpaPeopleDAO);
    }

    @Test
    public void shouldHibernateSaveAndDeletePerson() throws Exception {
        HibernatePeopleDAO jpaPeopleDAO = (HibernatePeopleDAO) context.lookup("java:global/entity-managers/HibernatePeopleDAO");
        shouldSaveAndDeletePerson(jpaPeopleDAO);
    }

    @Test
    public void shouldFindByType() throws NamingException {
        JpaPeopleDAO peopleDAO = (JpaPeopleDAO) context.lookup("java:global/entity-managers/JpaPeopleDAO");
        peopleDAO.savePerson(new Person("Quentin Tarantino", PersonType.WEIRD));
        peopleDAO.savePerson(new Person("Joel Coen", PersonType.OK));
        peopleDAO.savePerson(new Person("Wagner Tsuchiya", PersonType.AWESOME));
        List<Person> people = peopleDAO.getByType(PersonType.AWESOME);
        assertThat(people.size(), equalTo(1));
        assertThat(people.get(0).getName(), equalTo("Wagner Tsuchiya"));
        assertThat(people.get(0).getType(), equalTo(PersonType.AWESOME));
        for (Person person : peopleDAO.getPeople()) {
            peopleDAO.deletePerson(person);
        }
    }
}