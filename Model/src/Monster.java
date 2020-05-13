import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monster extends LivingCreature implements Cloneable {
    private Integer ID;
    private String name;
    private Integer maximumDamage;
    private Integer rewardExperiencePoints;
    private Integer rewardGold;
    private List<LootItem> lootTable;

    public Monster(Integer ID, String name, Integer maximumDamage, Integer rewardExperiencePoints, Integer rewardGold, Integer maximumHitPoints, Integer currentHitPoints) {
        super(maximumHitPoints, currentHitPoints);
        this.ID = ID;
        this.name = name;
        this.maximumDamage = maximumDamage;
        this.rewardExperiencePoints = rewardExperiencePoints;
        this.rewardGold = rewardGold;
        this.lootTable = new ArrayList<>();
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

    public Integer getMaximumDamage() {
        return maximumDamage;
    }

    public void setMaximumDamage(Integer maximumDamage) {
        this.maximumDamage = maximumDamage;
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

    public List<LootItem> getLootTable() {
        return lootTable;
    }

    public void setLootTable(List<LootItem> lootTable) {
        this.lootTable = lootTable;
    }

    @Override
    public Monster clone() {
        Monster mon = new Monster(
                this.ID,
                this.name,
                this.maximumDamage,
                this.rewardExperiencePoints,
                this.rewardGold,
                this.getMaximumHitPoints(),
                this.getCurrentHitPoints()
        );
        mon.lootTable = this.lootTable;
        return mon;
    }
}
