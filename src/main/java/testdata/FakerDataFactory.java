package testdata;

import com.github.javafaker.Faker;
import model.FakerDataModel;

import java.util.Locale;

public class FakerDataFactory {

    public Faker faker = new Faker(new Locale("en"));

    public FakerDataModel produceFakeData(){

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userName = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = faker.regexify("[A-Z][a-z0-9]{11}[0-9][@#$!]");

        return new FakerDataModel(firstName,lastName,userName,email,password);
    }
}