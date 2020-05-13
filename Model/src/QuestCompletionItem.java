public class QuestCompletionItem {
    private Item details;
    private Integer quantity;

    public QuestCompletionItem(Item details, Integer quantity) {
        this.details = details;
        this.quantity = quantity;
    }

    public Item getDetails() {
        return details;
    }

    public void setDetails(Item details) {
        this.details = details;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
