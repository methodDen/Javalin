package Utils;

import Controller.TimeOfLesson;
import Model.Day;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.spring.DaoFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import Model.*;
import io.javalin.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Connection {
    private static ConnectionSource source;

    static {
        try {
            source = new JdbcConnectionSource("jdbc:sqlite:C:\\SQL\\Sch.db");
            TableUtils.createTableIfNotExists(source, Day.class);
            TableUtils.createTableIfNotExists(source, Schedule.class);
            TableUtils.createTableIfNotExists(source, Groups.class);
            TableUtils.createTableIfNotExists(source, Students.class);
            TableUtils.createTableIfNotExists(source, Tutors.class);
            TableUtils.createTableIfNotExists(source, Attendance.class);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occured creating table");
        }
    }

    public static Role getUserRole(Context context) throws SQLException {
        if (context.header("Authorization") == null) {
            return Role.ANONYMOUS;
        }
        String login = context.basicAuthCredentials().getUsername();
        String password = context.basicAuthCredentials().getPassword();
        Dao<Tutors, Long> daoForTutors = DaoFactory.createDao(Connection.getSource(), Tutors.class);
        List<Tutors> whoGotLoginWithinTutors = new ArrayList<>();
        Dao<Students, Long> daoForStudents = DaoFactory.createDao(Connection.getSource(), Students.class);
        List<Students> whoGotLoginWithinStudents = new ArrayList<>();
        whoGotLoginWithinTutors = daoForTutors.queryBuilder().where().eq("Login", login).query();
        whoGotLoginWithinStudents = daoForStudents.queryBuilder().where().eq("Login", login).query();
        if (whoGotLoginWithinStudents.size() == 1) {
            Students student = whoGotLoginWithinStudents.get(0);
            System.out.println(student);
            if (BCrypt.checkpw(password, student.getPasswordOfStudent())) { // В ARC, в авторизации - писать нехэшированный пароль
                return student.getRole();
            }
        }

        if (whoGotLoginWithinTutors.size() == 1)
        {
            Tutors tutor = whoGotLoginWithinTutors.get(0);
            System.out.println(tutor);
            if (BCrypt.checkpw(password, tutor.getPasswordOfTutor())) {
                return tutor.getRoleOfTutor();
            }
        }
        return Role.ANONYMOUS;
    }

    public static boolean isTimeAvailable(Context context) throws SQLException, ParseException {
        Day day = context.bodyAsClass(Day.class);
        String dayName = day.getWeekDayName(); // using variable for query
        String startingTime = day.getStartingTime(); // using variable for query
        String endingTime = day.getEndingTime();
        int officeNum = day.getOfficeNum(); // using variable for query
        Dao<Day, Long> dayDAO = DaoManager.createDao(Connection.getSource(), Day.class);
        List<Day> daysWithSameDay = new ArrayList<>();
        daysWithSameDay = dayDAO.queryBuilder().where().eq("weekDayName", dayName).query(); // переделать отбор: вгачаде - по дням, потом - по времени и по офису
        List<Day> daysWithSameDayAndOffice = new ArrayList<>();
        for (Day dayq : daysWithSameDay)
        {
            if (dayq.getOfficeNum() == officeNum)
            {
                daysWithSameDayAndOffice.add(dayq);
            }
        }
        for (Day dayy : daysWithSameDayAndOffice)
        {
            boolean a = TimeOfLesson.compareTwoTimelines(dayy.getStartingTime(), dayy.getEndingTime(), startingTime, endingTime);
            if (!a) {
                return false;
            }
        }
        return true;
    }
    public static ConnectionSource getSource() {
        return source;
    }

}
