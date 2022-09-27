import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Person John = new Person(324343, "John", "Doe", "johndoe@gmail.com", "+32424398");
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Person.class, new PersonSerializer());
//        module.addDeserializer(Person.class, new PersonDeserializer());
        mapper.registerModule(module);
//        String serialized = mapper.writeValueAsString(John);
//        System.out.println(serialized);
//        String json = "{ \"personName\" : \"John\",  \"id\" : 4, \"emailAddress\" : \"johndoe@example.com\", \"personLastName\" :\"Doe\" }";

//        Person readValue = mapper.readValue(json, Person.class);
//        System.out.println(readValue);

    }
}
