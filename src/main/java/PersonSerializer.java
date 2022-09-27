import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PersonSerializer extends StdSerializer<Person> {
    public PersonSerializer() {
        this(null);
    }

    public PersonSerializer(Class<Person> t) {
        super(t);
    }

    public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", person.getId());
        jsonGenerator.writeStringField("firstName", person.getFirstName());
        jsonGenerator.writeStringField("lastName", person.getLastName());
        jsonGenerator.writeStringField("emailAddress", person.getEmailAddress());
        jsonGenerator.writeStringField("phoneNumber", person.getPhoneNumber());
        jsonGenerator.writeEndObject();
    }

}
