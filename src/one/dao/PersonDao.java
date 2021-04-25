package one.dao;

import one.personModel.Person;

import java.util.List;

public interface PersonDao extends Dao<Person>{

    default boolean isValidArgument(List<Person> personList){
        return personList!=null || !personList.isEmpty();
    }
    default boolean isInvalisArgument(List<Person> personList){
        return !isValidArgument(personList);
    }
}
