package Serializers;

import Model.Tutors;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class TutorSerializer extends StdSerializer<Tutors> {
    public TutorSerializer(Class<Tutors> t) {
        super(t);
    }

    public TutorSerializer() {
        this(null);
    }

    @Override
    public void serialize(Tutors tutors, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("TutorId", tutors.getTutorId());
        jsonGenerator.writeStringField("FirstName", tutors.getFirstName());
        jsonGenerator.writeStringField("LastName", tutors.getLastName());
        jsonGenerator.writeStringField("DateOfBirth", tutors.getDateOfBirth());
        jsonGenerator.writeStringField("PhoneNumber", tutors.getPhoneNumber());
        jsonGenerator.writeStringField("RoleOfTutor", String.valueOf(tutors.getRoleOfTutor()));
        jsonGenerator.writeStringField("Login", tutors.getLoginOfTutor());
        jsonGenerator.writeStringField("Password", tutors.getPasswordOfTutor());
        jsonGenerator.writeEndObject();
    }
}
