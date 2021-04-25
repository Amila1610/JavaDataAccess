package one.dao;

import one.exception.DaoException;
import one.personModel.Person;

import java.io.*;
import java.util.List;

public class SerializablePersonDao implements PersonDao{
    static final String FILE_NAME="persons.dat";

    @Override
    public List<Person> readAll() throws DaoException {
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(FILE_NAME))){
            List<Person>personList=(List<Person>) ois.readObject();
            return personList;
        }catch (Exception e){
            throw new DaoException(e.getMessage());
        }
    }
    @Override
    public void writeAll(List<Person> persons) throws DaoException {
        if(isInvalisArgument(persons)){
            return;
        }
        try(ObjectOutputStream ous=new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
          ous.writeObject(persons);
        }catch (IOException e){
            throw new DaoException(e.getMessage());
        }
    }
}
