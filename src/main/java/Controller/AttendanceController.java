package Controller;

import Model.Attendance;
import Utils.Connection;
import Utils.Constants;
import java.sql.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.spring.DaoFactory;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AttendanceController {
    public Dao<Attendance, Long> attendanceDao;
    private Logger logger;

    public AttendanceController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try {
            attendanceDao = DaoFactory.createDao(Connection.getSource(), Attendance.class);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured creating dao");
        }
    }

    public void create(@NotNull Context context) {
        Attendance attendance = context.bodyAsClass(Attendance.class);
        try {
            attendanceDao.create(attendance);
            context.status(Constants.CREATED_201);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }


    public void getAll(@NotNull Context context) {
        try {
            context.json(attendanceDao.queryForAll());
            context.status(Constants.OK_200);
        } catch (SQLException e) {
            e.printStackTrace();
            context.status(Constants.INTERNAL_SERVER_ERROR);
            logger.error("Error occured getting records");
        }
    }


    public void getOne(@NotNull Context context, @NotNull String s) {
        long studentId = Long.valueOf(s);
        try {
            Attendance attendance = attendanceDao.queryForId(studentId);
            if (attendance != null)
            {
                context.json(attendance);
                context.status(Constants.OK_200);
            }
            else
            {
                System.out.println(attendance);
                context.status(Constants.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured getting a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
    java.sql.Connection connection = DriverManager.getConnection(Connection.getSource(), "", "");
}
