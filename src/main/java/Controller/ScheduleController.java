package Controller;

import Model.*;
import Utils.Connection;
import Utils.Constants;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.spring.DaoFactory;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;

public class ScheduleController {
    public Dao<Schedule, Long> scheduleDao;
    private Logger logger;

    public ScheduleController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try {
            scheduleDao = DaoManager.createDao(Connection.getSource(), Schedule.class);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured creating Dao");
        }

    }

    public void create(@NotNull Context context) {
        Schedule schedule = context.bodyAsClass(Schedule.class); // читать Exceptions внимательнее чтобы избежать Unrecognized Field Exception -> писать названия полей в ARC так, как написано в Exception'e
        try {
            scheduleDao.create(schedule);
            context.status(Constants.CREATED_201);
        } catch (SQLException e) {
            e.printStackTrace();
            context.status(Constants.BAD_REQUEST);
            logger.error("Error occured creating a record");
        }
    }

    public void getAll(@NotNull Context context) {
        try {
            context.json(scheduleDao.queryForAll());
            context.status(Constants.OK_200);
        } catch (SQLException e) {
            e.printStackTrace();
            context.status(Constants.INTERNAL_SERVER_ERROR);
            logger.error("Error occured getting records");
        }
    }
}
