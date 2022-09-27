package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable (tableName = "Day")
public class Day {
    @DatabaseField(columnName = "DayId")
    private long dayId;
    @DatabaseField(columnName = "WeekDayName")
    private String weekDayName;
    @DatabaseField(columnName = "Office")
    private int officeNum;
    @DatabaseField(columnName = "StartingTime")
    private String startingTime;
    @DatabaseField(columnName = "EndingTime")
    private String endingTime;
    @DatabaseField(columnName = "GroupName")
    private String groupName;
    @DatabaseField (columnName = "ScheduleId")
    private long scheduleId;
    public Day() {
    }

    public long getDayId() {
        return dayId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }

    public String getWeekDayName() {
        return weekDayName;
    }

    public void setWeekDayName(String weekDayName) {
        this.weekDayName = weekDayName;
    }

    public int getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(int officeNum) {
        this.officeNum = officeNum;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return dayId == day.dayId &&
                officeNum == day.officeNum &&
                scheduleId == day.scheduleId &&
                Objects.equals(weekDayName, day.weekDayName) &&
                Objects.equals(startingTime, day.startingTime) &&
                Objects.equals(endingTime, day.endingTime) &&
                Objects.equals(groupName, day.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayId, weekDayName, officeNum, startingTime, endingTime, groupName, scheduleId);
    }

    @Override
    public String toString() {
        return "Day{" +
                "dayId=" + dayId +
                ", weekDayName='" + weekDayName + '\'' +
                ", officeNum=" + officeNum +
                ", startingTime='" + startingTime + '\'' +
                ", endingTime='" + endingTime + '\'' +
                ", groupName='" + groupName + '\'' +
                ", scheduleId=" + scheduleId +
                '}';
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }
}
