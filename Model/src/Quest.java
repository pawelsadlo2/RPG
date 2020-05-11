import java.util.ArrayList;
import java.util.List;

public class Quest {
    private Integer ID;
    private String name;
    private String description;
    private Integer rewardExperiencePoints;
    private Integer rewardGold;
    private Item rewardItem;

    private List<QuestCompletionItem> questCompletionItems;

    public Quest(Integer ID, String name, String description, Integer rewardExperiencePoints, Integer rewardGold) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.rewardExperiencePoints = rewardExperiencePoints;
        this.rewardGold = rewardGold;
        this.questCompletionItems = new ArrayList<>();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRewardExperiencePoints() {
        return rewardExperiencePoints;
    }

    public void setRewardExperiencePoints(Integer rewardExperiencePoints) {
        this.rewardExperiencePoints = rewardExperiencePoints;
    }

    public Integer getRewardGold() {
        return rewardGold;
    }

    public void setRewardGold(Integer rewardGold) {
        this.rewardGold = rewardGold;
    }

    public Item getRewardItem() {
        return rewardItem;
    }

    public void setRewardItem(Item rewardItem) {
        this.rewardItem = rewardItem;
    }

    public List<QuestCompletionItem> getQuestCompletionItems() {
        return questCompletionItems;
    }

    public void setQuestCompletionItems(List<QuestCompletionItem> questCompletionItems) {
        this.questCompletionItems = questCompletionItems;
    }
}
