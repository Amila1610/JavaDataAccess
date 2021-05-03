package two;

import one.dao.PersonDao;
import one.exception.DaoException;
import one.personModel.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonDao implements PersonDao {
    final static String FILE_NAME="persons.json";

    @Override
    public List<Person> readAll() throws DaoException {
        try (FileReader fileReader = new FileReader(FILE_NAME)) {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);
            List<Person> personList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Person person = new Person();
                person.setNationalIdentificationNumber(jsonObject.get("nin").toString());
                person.setName(jsonObject.get("name").toString());
                person.setSurname(jsonObject.get("surname").toString());
                person.setAge(Integer.parseInt(jsonObject.get("age").toString()));
                personList.add(person);
            }
            return personList;
        } catch (IOException e) {
            throw new DaoException(e.getMessage());
        } catch (ParseException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void writeAll(List<Person>persons) throws DaoException {
           if(isInvalisArgument(persons)){
               return ;
           }
            JSONArray jsonArray=new JSONArray();
           for(Person person:persons){
               JSONObject jsonObject=new JSONObject();
               jsonObject.put("nin", person.getNationalIdentificationNumber());
               jsonObject.put("name", person.getName());
               jsonObject.put("surname", person.getSurname());
               jsonObject.put("age", person.getAge());
               jsonArray.add(jsonObject);
           }
           String jsonArrayText= jsonArray.toJSONString();
           try(FileWriter fileWriter=new FileWriter(FILE_NAME)){
               fileWriter.write(jsonArrayText);
           }catch (IOException e){
               throw new DaoException(e.getMessage());
           }
        }

    }
