package one.dao;

import one.exception.DaoException;
import one.personModel.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class FilePersonDao implements PersonDao{
    static final String FILE_NAME="persons.txt";

    @Override
    public List<Person> readAll() throws DaoException {
        List<Person> personList=new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader(FILE_NAME))){
            String line=null;
            while((line= br.readLine())!=null){
                StringTokenizer stringTokenizer=new StringTokenizer(line,";");
                String nin=stringTokenizer.nextToken();
                String name=stringTokenizer.nextToken();
                String surname=stringTokenizer.nextToken();
                Integer age=Integer.parseInt(stringTokenizer.nextToken());
                Person person=new Person(nin,name,surname,age);
                personList.add(person);
            }
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
        try (PrintWriter printWriter=new PrintWriter(new FileWriter(FILE_NAME))){
            for(Person person:persons){
                String line= new StringJoiner(";")
                        .add(person.getNationalIdentificationNumber())
                        .add(person.getName())
                        .add(person.getSurname())
                        .add(person.getAge()+"").toString();
                printWriter.println(line);
            }

        }catch (Exception e){
            throw new DaoException(e.getMessage());
        }
    }
}
