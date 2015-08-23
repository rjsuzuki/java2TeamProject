import java.util.Date;

public class Defect {

    private int defectId;
    private String status;
    private String priority;
    private String assignee;
    private String summary;
    private String description;
    Date dateOfCreation;

    public void setDefectId(int defectId) {
        this.defectId = defectId;
    }

    public int getDefectId() {
        return defectId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    //Relationship between Assignee and User is unclear.
    public String getAssignee() {
        assignee = User.getEmail();
        return assignee;
    }

    public void setAssignee(String assignee) {
        assignee = User.setEmail(this.assignee);
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDateOfCreation(Date dateOfCreation) {
          this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfCreation() {
          return dateOfCreation;
    }
}
