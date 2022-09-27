package Deserializers;

import Model.Students;
import Utils.Role;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class StudentDeserializer extends StdDeserializer<Students> {
    public StudentDeserializer(Class<?> vc) {
        super(vc);
    }

    public StudentDeserializer()
    {
        this(null);
    }

    @Override
    public Students deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        long studentId = Long.valueOf(jsonNode.get("StudentId").asText()); // записывать поле "StudentId" в ARC тоже, даже если в Model записан AutoIncrement
        String firstName = jsonNode.get("FirstName").asText();
        String lastName = jsonNode.get("LastName").asText();
        String mobilePhone = jsonNode.get("MobilePhone").asText();
        long groupId = Long.valueOf(jsonNode.get("GroupId").asText());
        String login = jsonNode.get("Login").asText();
        String passwordOfStudent = jsonNode.get("PasswordOfStudent").asText();
        String role = jsonNode.get("RoleOfUser").asText();
        String schoolName = jsonNode.get("SchoolName").asText();
        String dateOfBirth = jsonNode.get("DateOfBirth").asText();
        return new Students(studentId, firstName, lastName, mobilePhone, groupId, login, BCrypt.hashpw(passwordOfStudent, BCrypt.gensalt()), Role.valueOf(role), schoolName, dateOfBirth);
    }
}
