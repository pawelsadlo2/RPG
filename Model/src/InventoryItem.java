public class InventoryItem {
    private Item details;
    private Integer dropPercentage;
    private Boolean isDefaultItem;

    public InventoryItem(Item details, Integer dropPercentage, Boolean isDefaultItem) {
        this.details = details;
        this.dropPercentage = dropPercentage;
        this.isDefaultItem = isDefaultItem;
    }

    public Item getDetails() {
        return details;
    }

    public void setDetails(Item details) {
        this.details = details;
    }

    public Integer getDropPercentage() {
        return dropPercentage;
    }

    public void setDropPercentage(Integer dropPercentage) {
        this.dropPercentage = dropPercentage;
    }

    public Boolean getDefaultItem() {
        return isDefaultItem;
    }

    public void setDefaultItem(Boolean defaultItem) {
        isDefaultItem = defaultItem;
    }
}
