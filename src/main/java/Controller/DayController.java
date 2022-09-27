package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Model.*;
import Utils.*;
import java.sql.SQLException;
import java.text.ParseException;

public class DayController {
    private Logger logger;
    public Dao<Day, Long> dayDao;
    public DayController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try {
            dayDao = DaoManager.createDao(Connection.getSource(), Day.class);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception");
        }
    }

    public void create(@NotNull Context context) throws SQLException, ParseException {
        Day day = context.bodyAsClass(Day.class);
        boolean isTimeAvailableBool = Connection.isTimeAvailable(context);
        if (isTimeAvailableBool == false)
        {
            context.status(400);
        }
        else {
            try {
                dayDao.create(day);
                context.status(Constants.CREATED_201);
            } catch (SQLException e) {
                logger.error("Error occured creating a record");
                context.status(Constants.INTERNAL_SERVER_ERROR);
                e.printStackTrace();
            }
        }
    }


    public void getAll(@NotNull Context context) {
        try {
            context.json(dayDao.queryForAll());
            context.status(Constants.OK_200);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
        }
    }



}
