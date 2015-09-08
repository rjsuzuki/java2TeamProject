import java.util.*;

public class Defect {

    private int defectId;
    private String status;
    private int priority;
    private String assignee;
    private String summary;
    private String description;


    public void setDefectId(int defectId) {
        this.defectId = defectId;
    }

    public int getDefectId() {
        return this.defectId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    //Relationship between Assignee and User is unclear.
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public String getAssignee() {
          return this.assignee;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
