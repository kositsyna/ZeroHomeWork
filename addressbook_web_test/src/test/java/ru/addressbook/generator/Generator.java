package ru.addressbook.generator;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static Tests.TestBase.randomFile;
import static common.CommonFunctions.randomString;


public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-c"})
    int count;



    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)){
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных" + type);
        }
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0 ; i < count; i++){
            result.add(new GroupData()
                    .withName(randomString(i*2))
                    .withFooter(randomString(i*2))
                    .withHeader(randomString(i*2)));
        }
        return result;
    }
    private Object generateContacts() {
        var result = new ArrayList<ContactData>();//создаем список объектов ContactData
        for (int i = 0; i<count; i++) {//заполняем список в цикле
            result.add(new ContactData()
                    .withFname(randomString(i*5))//создание контакта.
                    .withLname(randomString(i*5)));}                    //.withPhoto(randomFile("src/test/resources/images")));

        return result;
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)){
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных" + format);
        }
    }
}
