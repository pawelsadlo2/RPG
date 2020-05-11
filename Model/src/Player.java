

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class Player extends LivingCreature {
    private Integer gold;
    private Integer experiencePoints;
    private Integer level;
    private List<InventoryItem> inventory;
    private List<PlayerQuest> quests;
    private Location currentLocation;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);


    public Player(Integer maximumHitPoints, Integer currentHitPoints, Integer gold, Integer experiencePoints, Integer level) {
        super(maximumHitPoints, currentHitPoints);
        this.gold = gold;
        this.experiencePoints = experiencePoints;
        this.level = level;
        this.inventory = new ArrayList<>();
        this.quests = new ArrayList<>();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }


    public void setCurrentHitPoints(Integer currentHitPoints) {
        Integer oldValue = super.getCurrentHitPoints();
        super.setCurrentHitPoints(currentHitPoints);
        pcs.firePropertyChange("currentHitPoints", oldValue, currentHitPoints);
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(Integer experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        Integer oldLevel = this.level;
        this.level = level;
        pcs.firePropertyChange("level", oldLevel, level);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    public List<PlayerQuest> getQuests() {
        return quests;
    }

    public void setQuests(List<PlayerQuest> quests) {
        this.quests = quests;
    }

    public boolean hasRequiredItemToEnterThisLocation(Location location) {
        if (location.getItemRequiredToEnter() != null)
            return inventory.stream().anyMatch(
                    item -> item.getDetails().getID().equals(location.getItemRequiredToEnter().getID())
            );
        return false;
    }


}
