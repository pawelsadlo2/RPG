

public class PlayerQuest {
    private Quest details;
    private Boolean isCompleted;

    public PlayerQuest(Quest details, Boolean isCompleted) {
        this.details = details;
        this.isCompleted = isCompleted;
    }

    public Quest getDetails() {
        return details;
    }

    public void setDetails(Quest details) {
        this.details = details;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
