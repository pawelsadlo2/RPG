public class HealingPotion extends Item {
    private Integer amountToHeal;

    public HealingPotion(Integer ID, String name, String namePlural, Integer amountToHeal) {
        super(ID, name, namePlural);
        this.amountToHeal = amountToHeal;
    }

    public Integer getAmountToHeal() {
        return amountToHeal;
    }

    public void setAmountToHeal(Integer amountToHeal) {
        this.amountToHeal = amountToHeal;
    }

}
