package Deserializers;

import Model.Tutors;
import Utils.Role;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class TutorDeserializer extends StdDeserializer<Tutors> {


    protected TutorDeserializer(Class<?> vc) {
        super(vc);
    }

    public TutorDeserializer() {
        this(null);
    }

    @Override
    public Tutors deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        long tutorId = Long.valueOf(jsonNode.get("TutorId").asText());
        String firstName = jsonNode.get("FirstName").asText();
        String lastName = jsonNode.get("LastName").asText();
        String dateOfBirth = jsonNode.get("DateOfBirth").asText();
        String phoneNumber = jsonNode.get("PhoneNumber").asText();
        String role = jsonNode.get("RoleOfTutor").asText();
        String passwordOfTutor = jsonNode.get("Password").asText();
        String loginOfTutor = jsonNode.get("Login").asText();
        return new Tutors(tutorId, firstName, lastName, dateOfBirth, phoneNumber, loginOfTutor, BCrypt.hashpw(passwordOfTutor, BCrypt.gensalt()), Role.valueOf(role));
    }
}
