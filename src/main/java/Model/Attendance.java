package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable (tableName = "Attendance")
public class Attendance {
    @DatabaseField (columnName = "AttendanceId")
    private long attendanceId;
    @DatabaseField (columnName = "StudentId") // Query for Id ->
    private long studentId;
    @DatabaseField (columnName = "DayId")
    private long dayId;
    @DatabaseField (columnName = "AttendedOrNot")
    private boolean attendedOrNot;

    public long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getDayId() {
        return dayId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }

    public boolean isAttendedOrNot() {
        return attendedOrNot;
    }

    public void setAttendedOrNot(boolean attendedOrNot) {
        this.attendedOrNot = attendedOrNot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return attendanceId == that.attendanceId &&
                studentId == that.studentId &&
                dayId == that.dayId &&
                attendedOrNot == that.attendedOrNot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendanceId, studentId, dayId, attendedOrNot);
    }

    public Attendance() {
    }

    public Attendance(long attendanceId, long studentId, long dayId, boolean attendedOrNot) {

        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.dayId = dayId;
        this.attendedOrNot = attendedOrNot;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", studentId=" + studentId +
                ", dayId=" + dayId +
                ", attendedOrNot=" + attendedOrNot +
                '}';
    }
}
