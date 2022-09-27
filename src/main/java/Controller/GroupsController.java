package Controller;

import Model.Groups;
import Utils.Connection;
import Utils.Constants;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.spring.DaoFactory;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class GroupsController {
    private Logger logger;
    public Dao<Groups, Long> groupsDao;

    public GroupsController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try {
            groupsDao = DaoFactory.createDao(Connection.getSource(), Groups.class);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured creating Dao");
        }
    }

    public void create(@NotNull Context context) {
        Groups group = context.bodyAsClass(Groups.class);
        try {
            groupsDao.create(group);
            context.status(Constants.CREATED_201);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
            context.status(Constants.BAD_REQUEST);
        }
    }

    public void getAll(@NotNull Context context) {
        try {
            context.json(groupsDao.queryForAll());
            context.status(Constants.OK_200);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }

    }
}
