package one;

import one.exception.DaoException;
import one.dao.PersonDao;
import one.personModel.Person;

import java.util.List;

public class PersonPersistanceStrategy {
    private PersonDao personDao;

    public PersonPersistanceStrategy(PersonDao personDao){
        this.personDao=personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person>readPersons() throws DaoException{
        return personDao.readAll();
    }
    public void writePersons(List<Person> personList) throws DaoException{
        personDao.writeAll(personList);
    }
}
