import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;

public class PersonDeserializer extends StdDeserializer<Person> {

    public PersonDeserializer() {
        this(null);
    }

    public PersonDeserializer(Class<?> vc) {
        super(vc);
    }

    public Person deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        int id = Integer.valueOf(jsonNode.get("id").asText());
        String personName = jsonNode.get("personName").asText();
        String personLastName = jsonNode.get("personLastName").asText();
        String personEmailAddress = jsonNode.get("emailAddress").asText();
        return new Person(id, personName, personEmailAddress, personLastName);
    }
}
