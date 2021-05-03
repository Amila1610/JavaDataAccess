package one;

import one.dao.SerializablePersonDao;
import one.dao.XMLPersonDao;
import one.exception.DaoException;
import one.dao.FilePersonDao;
import one.personModel.Person;
import two.JsonDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tester {
    public static void main(String[] args) throws DaoException {

        Person ana=new Person("1234","Ana","Anich",13);
        Person ena=new Person("2345","Ena","Enich",18);
        Person ema=new Person("3456","Ema","Emich",26);
        Person marko=new Person("4567","Marko","Marich",44);
        Person toni=new Person("5678","Toni","Tonich",36);
        Person tin=new Person("6789","Tin","Tinich",31);
        List<Person>personList=new ArrayList<>(Arrays.asList(ana,ena,ema,marko,tin,toni));

   PersonPersistanceStrategy persistanceStrategy=new PersonPersistanceStrategy(new FilePersonDao());
         persistanceStrategy.writePersons(personList);//TXT

        persistanceStrategy.setPersonDao(new SerializablePersonDao());
        persistanceStrategy.writePersons(personList);//DAT

        persistanceStrategy.setPersonDao(new XMLPersonDao());
        persistanceStrategy.writePersons(personList);//XML

        persistanceStrategy.setPersonDao(new JsonDao());
        persistanceStrategy.writePersons(personList);//JSON

     persistanceStrategy.setPersonDao(new FilePersonDao());
        List<Person> ucitanePersone =persistanceStrategy.readPersons();
        ucitanePersone.forEach(System.out::println);
        System.out.println();

        persistanceStrategy.setPersonDao(new SerializablePersonDao());
        List<Person> serijalizovanePersone=persistanceStrategy.readPersons();
        serijalizovanePersone.forEach(System.out::println);
        System.out.println();

        persistanceStrategy.setPersonDao(new XMLPersonDao());
        List<Person> xmlPersone=persistanceStrategy.readPersons();
        xmlPersone.forEach(System.out::println);
        System.out.println();

        persistanceStrategy.setPersonDao(new JsonDao());
        List<Person> jsonPersone=persistanceStrategy.readPersons();
        jsonPersone.forEach(System.out::println);
}
}