package ru.addressbook.generator;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList()); //коллектор формирует список
    }

    private Object generateGroups() {
        return generateData(()-> new GroupData()
                    .withName(randomString(2))
                    .withFooter(randomString(2))
                    .withHeader(randomString(2)));
            }

    private Object generateContacts() {
        return generateData(() -> new ContactData()
                        .withFname(randomString(2))
                        .withLname(randomString(2)));
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)){
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(data); //Файл будет помещен в переменную json

           try (var writer = new FileWriter(output)){
               writer.write(json); //пишем строку в файл
           }
           // writer.close();//закрываем файл
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных" + format);
        }
    }
}
