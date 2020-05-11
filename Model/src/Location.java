import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Location {
    private Integer ID;
    private String name;
    private String description;
    private Item itemRequiredToEnter;
    private Quest questAvailableHere;
    private Monster monsterLivingHere;
    private Location locationToNorth;
    private Location locationToEast;
    private Location locationToSouth;
    private Location locationToWest;

    public static class Builder {
        private Integer ID;
        private String name;
        private String description;

        private Item itemRequiredToEnter = null;
        private Quest questAvailableHere = null;
        private Monster monsterLivingHere = null;

        public Builder(Integer ID, String name, String description) {
            this.ID = ID;
            this.name = name;
            this.description = description;
        }

        public Builder itemRequiredToEnter(Item itemRequiredToEnter) {
            this.itemRequiredToEnter = itemRequiredToEnter;
            return this;
        }

        public Builder questAvailableHere(Quest questAvailableHere) {
            this.questAvailableHere = questAvailableHere;
            return this;
        }

        public Builder monsterLivingHere(Monster monsterLivingHere) {
            this.monsterLivingHere = monsterLivingHere;
            return this;
        }

        public Location build() {
            return new Location(this);
        }

    }

    private Location(Builder builder) {
        ID = builder.ID;
        name = builder.name;
        description = builder.description;
        itemRequiredToEnter = builder.itemRequiredToEnter;
        questAvailableHere = builder.questAvailableHere;
        monsterLivingHere = builder.monsterLivingHere;
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

    public Item getItemRequiredToEnter() {
        return itemRequiredToEnter;
    }

    public void setItemRequiredToEnter(Item itemRequiredToEnter) {
        this.itemRequiredToEnter = itemRequiredToEnter;
    }

    public Quest getQuestAvailableHere() {
        return questAvailableHere;
    }

    public void setQuestAvailableHere(Quest questAvailableHere) {
        this.questAvailableHere = questAvailableHere;
    }

    public Monster getMonsterLivingHere() {
        return monsterLivingHere;
    }

    public void setMonsterLivingHere(Monster monsterLivingHere) {
        this.monsterLivingHere = monsterLivingHere;
    }

    public Location getLocationToNorth() {
        return locationToNorth;
    }

    public void setLocationToNorth(Location locationToNorth) {
        this.locationToNorth = locationToNorth;
    }

    public Location getLocationToEast() {
        return locationToEast;
    }

    public void setLocationToEast(Location locationToEast) {
        this.locationToEast = locationToEast;
    }

    public Location getLocationToSouth() {
        return locationToSouth;
    }

    public void setLocationToSouth(Location locationToSouth) {
        this.locationToSouth = locationToSouth;
    }

    public Location getLocationToWest() {
        return locationToWest;
    }

    public void setLocationToWest(Location locationToWest) {
        this.locationToWest = locationToWest;
    }

    public Collection<Location> avaliableLocations() {
        Collection<Location> ret = new ArrayList<>();
        if (locationToEast != null)
            ret.add(locationToEast);
        if (locationToWest != null)
            ret.add(locationToWest);
        if (locationToNorth != null)
            ret.add(locationToNorth);
        if (locationToSouth != null)
            ret.add(locationToSouth);
        return ret;
    }
}
