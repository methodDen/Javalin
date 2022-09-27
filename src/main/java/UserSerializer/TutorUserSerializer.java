package UserSerializer;

import Model.Tutors;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class TutorUserSerializer extends StdSerializer<Tutors> {


    public TutorUserSerializer(Class<Tutors> t) {
        super(t);
    }
    public TutorUserSerializer() {
        this(null);
    }

    @Override
    public void serialize(Tutors tutors, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("FirstName", tutors.getFirstName());
        jsonGenerator.writeStringField("LastName", tutors.getLastName());
        jsonGenerator.writeStringField("PhoneNumber", tutors.getPhoneNumber());
        jsonGenerator.writeEndObject();
    }
}
