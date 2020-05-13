public class InventoryItem {
    private Item details;
    private int quantity;
    private Integer dropPercentage = 0;
    private Boolean isDefaultItem = false;

    public InventoryItem(Item details, Integer dropPercentage, Boolean isDefaultItem) {
        this.details = details;
        this.dropPercentage = dropPercentage;
        this.isDefaultItem = isDefaultItem;
    }

    public InventoryItem(Item details) {
        this.details = details;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
