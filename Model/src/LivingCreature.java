public class LivingCreature {
    private Integer maximumHitPoints;
    private Integer currentHitPoints;

    public LivingCreature(Integer maximumHitPoints, Integer currentHitPoints) {
        this.maximumHitPoints = maximumHitPoints;
        this.currentHitPoints = currentHitPoints;
    }

    public Integer getMaximumHitPoints() {
        return maximumHitPoints;
    }

    public void setMaximumHitPoints(Integer maximumHitPoints) {
        this.maximumHitPoints = maximumHitPoints;
    }

    public Integer getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(Integer currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    public void heal(int amount) {
        if (amount > maximumHitPoints)
            this.currentHitPoints = maximumHitPoints;
        else
            this.currentHitPoints = amount;
    }

    /**
     * heals entirely
     **/
    public void heal() {
        this.heal(maximumHitPoints);
    }
}
