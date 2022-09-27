package UserSerializer;

import Model.Students;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class StudentUserSerializer extends StdSerializer<Students> {
    public StudentUserSerializer(Class<Students> t) {
        super(t);
    }

    public StudentUserSerializer() {
        this(null);
    }

    @Override
    public void serialize(Students students, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("FirstName", students.getFirstName());
        jsonGenerator.writeStringField("LastName", students.getLastName());
        jsonGenerator.writeStringField("MobilePhone", students.getMobilePhone());
        jsonGenerator.writeEndObject();
    }
}
