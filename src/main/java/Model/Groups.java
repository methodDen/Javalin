package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "Groups")
public class Groups {
    @DatabaseField(columnName = "GroupId")
    private long groupId;
    @DatabaseField(columnName = "GroupName")
    private String groupName;
    @DatabaseField(columnName = "TutorId")
    private long tutorId;
    public long getGroupId() {
        return groupId;
    }

    public long getTutorId() {
        return tutorId;
    }

    public void setTutorId(long tutorId) {
        this.tutorId = tutorId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Groups() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups groups = (Groups) o;
        return groupId == groups.groupId &&
                tutorId == groups.tutorId &&
                Objects.equals(groupName, groups.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, tutorId);
    }
}
