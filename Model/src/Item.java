import java.util.Objects;

public class Item {
    private Integer ID;
    private String name;
    private String namePlural;

    public Item(Integer ID, String name, String namePlural) {
        this.ID = ID;
        this.name = name;
        this.namePlural = namePlural;
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

    public String getNamePlural() {
        return namePlural;
    }

    public void setNamePlural(String namePlural) {
        this.namePlural = namePlural;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (!(otherObject instanceof Item)) return false;
        Item other = ((Item) otherObject);
        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public int hashCode() {
        return this.ID == null ? 0 : this.ID.hashCode();
    }

}
