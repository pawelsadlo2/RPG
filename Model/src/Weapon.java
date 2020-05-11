public class Weapon extends Item {
    private Integer minimumDamage;
    private Integer maximumDamage;

    public Weapon(Integer ID, String name, String namePlural, Integer minimumDamage, Integer maximumDamage) {
        super(ID, name, namePlural);
        this.minimumDamage = minimumDamage;
        this.maximumDamage = maximumDamage;
    }


    public Integer getMinimumDamage() {
        return minimumDamage;
    }

    public void setMinimumDamage(Integer minimumDamage) {
        this.minimumDamage = minimumDamage;
    }

    public Integer getMaximumDamage() {
        return maximumDamage;
    }

    public void setMaximumDamage(Integer maximumDamage) {
        this.maximumDamage = maximumDamage;
    }


}
