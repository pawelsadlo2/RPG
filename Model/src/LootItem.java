public class LootItem {
    private Item details;
    private Integer quantity;
    private boolean isDefaultItem;

    public LootItem(Item details, Integer quantity, boolean isDefaultItem) {
        this.details = details;
        this.quantity = quantity;
        this.isDefaultItem = isDefaultItem;
    }
}
