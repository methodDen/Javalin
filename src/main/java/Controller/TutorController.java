package Controller;

import Deserializers.TutorDeserializer;
import Model.Tutors;
import Serializers.TutorSerializer;
import UserSerializer.TutorUserSerializer;
import Utils.Connection;
import Utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.spring.DaoFactory;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

public class TutorController{
    public Dao<Tutors, Long> tutorDao;
    private Logger logger;

    public TutorController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try {
            tutorDao = DaoFactory.createDao(Connection.getSource(), Tutors.class);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured creating dao");
        }

    }

    public void create(@NotNull Context context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Tutors.class, new TutorDeserializer());
        mapper.registerModule(module);
        String json = context.body();
        Tutors tutor = mapper.readValue(json, Tutors.class);
        try {
            tutorDao.create(tutor);
            context.status(Constants.CREATED_201);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }


    public void getAll(@NotNull Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Tutors.class, new TutorSerializer());
        mapper.registerModule(module);
        try {
            context.result(mapper.writeValueAsString(tutorDao.queryForAll()));
            context.status(Constants.OK_200);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    public void getAllForUsers(@NotNull Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Tutors.class, new TutorUserSerializer());
        mapper.registerModule(module);

        try {
            context.result(mapper.writeValueAsString(tutorDao.queryForAll()));
            context.status(Constants.OK_200);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }


}
