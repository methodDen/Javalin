package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "Schedule")
public class Schedule {
    @DatabaseField(columnName = "ScheduleId")
    private long scheduleId;
    @DatabaseField(columnName = "GroupId")
    private long groupId;

    public Schedule() {
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return scheduleId == schedule.scheduleId &&
                groupId == schedule.groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId, groupId);
    }
}
