package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> peopleList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\New folder\\workwithfile\\src\\Docs\\people.json"));
            String line;
            StringBuilder jsonContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            jsonContent = new StringBuilder(jsonContent.substring(1, jsonContent.length() - 1));
            String[] personJsons = jsonContent.toString().split("},\\s*\\{");

            for (String personJson : personJsons) {
                personJson = "{" + personJson + "}";
                Person person = parsePerson(personJson);
                peopleList.add(person);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Person person : peopleList) {
            System.out.println(person);
        }
    }
    private static Person parsePerson(String json) {
        json = json.substring(2, json.length() - 1);

        String[] keyValuePairs = json.split(",");
        String name = null, surname = null, gender = null, birthDate = null;
        int age = 0;

        for (String pair : keyValuePairs) {
            String[] parts = pair.split(":", 2);
            String key = parts[0].trim().replace("\"", "");
            String value = parts[1].trim();

            switch (key) {
                case "name":
                    name = value;
                    break;
                case "surname":
                    surname = value;
                    break;
                case "age":
                    age = Integer.parseInt(value);
                    break;
                case "gender":
                    gender = value;
                    break;
                case "birthDate":
                    birthDate = value;
                    break;
            }
        }

        return new Person(name, surname, age, gender, birthDate);
    }

}
