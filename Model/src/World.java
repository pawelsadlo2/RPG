import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum World {
    ;
    private static List<Item> items = new ArrayList<>();
    private static List<Monster> monsters = new ArrayList<>();
    private static List<Quest> quests = new ArrayList<>();
    private static List<Location> Locations = new ArrayList<>();

    public static final int ITEM_ID_RUSTY_SWORD = 1;
    public static final int ITEM_ID_RAT_TAIL = 2;
    public static final int ITEM_ID_PIECE_OF_FUR = 3;
    public static final int ITEM_ID_SNAKE_FANG = 4;
    public static final int ITEM_ID_SNAKESKIN = 5;
    public static final int ITEM_ID_CLUB = 6;
    public static final int ITEM_ID_HEALING_POTION = 7;
    public static final int ITEM_ID_SPIDER_FANG = 8;
    public static final int ITEM_ID_SPIDER_SILK = 9;
    public static final int ITEM_ID_ADVENTURER_PASS = 10;

    public static final int MONSTER_ID_RAT = 1;
    public static final int MONSTER_ID_SNAKE = 2;
    public static final int MONSTER_ID_GIANT_SPIDER = 3;

    public static final int QUEST_ID_CLEAR_ALCHEMIST_GARDEN = 1;
    public static final int QUEST_ID_CLEAR_FARMERS_FIELD = 2;

    public static final int LOCATION_ID_HOME = 1;
    public static final int LOCATION_ID_TOWN_SQUARE = 2;
    public static final int LOCATION_ID_GUARD_POST = 3;
    public static final int LOCATION_ID_ALCHEMIST_HUT = 4;
    public static final int LOCATION_ID_ALCHEMISTS_GARDEN = 5;
    public static final int LOCATION_ID_FARMHOUSE = 6;
    public static final int LOCATION_ID_FARM_FIELD = 7;
    public static final int LOCATION_ID_BRIDGE = 8;
    public static final int LOCATION_ID_SPIDER_FIELD = 9;

    static {
        PopulateItems();
        PopulateMonsters();
        PopulateQuests();
        PopulateLocations();
    }

    public static List<Item> getItems() {
        return items;
    }

    public static List<Monster> getMonsters() {
        return monsters;
    }

    public static List<Quest> getQuests() {
        return quests;
    }

    public static List<Location> getLocations() {
        return Locations;
    }

    private static void PopulateItems() {
        items.add(new Weapon(ITEM_ID_RUSTY_SWORD, "Rusty sword", "Rusty swords", 0, 5));
        items.add(new Item(ITEM_ID_RAT_TAIL, "Rat tail", "Rat tails"));
        items.add(new Item(ITEM_ID_PIECE_OF_FUR, "Piece of fur", "Pieces of fur"));
        items.add(new Item(ITEM_ID_SNAKE_FANG, "Snake fang", "Snake fangs"));
        items.add(new Item(ITEM_ID_SNAKESKIN, "Snakeskin", "Snakeskins"));
        items.add(new Weapon(ITEM_ID_CLUB, "Club", "Clubs", 3, 10));
        items.add(new HealingPotion(ITEM_ID_HEALING_POTION, "Healing potion", "Healing potions", 5));
        items.add(new Item(ITEM_ID_SPIDER_FANG, "Spider fang", "Spider fangs"));
        items.add(new Item(ITEM_ID_SPIDER_SILK, "Spider silk", "Spider silks"));
        items.add(new Item(ITEM_ID_ADVENTURER_PASS, "Adventurer pass", "Adventurer passes"));
    }

    private static void PopulateMonsters() {
        Monster rat = new Monster(MONSTER_ID_RAT, "Rat", 5, 3, 10, 3, 3);
        rat.getLootTable().add(new LootItem(ItemByID(ITEM_ID_RAT_TAIL), 75, false));
        rat.getLootTable().add(new LootItem(ItemByID(ITEM_ID_PIECE_OF_FUR), 75, true));

        Monster snake = new Monster(MONSTER_ID_SNAKE, "Snake", 5, 3, 10, 3, 3);
        snake.getLootTable().add(new LootItem(ItemByID(ITEM_ID_SNAKE_FANG), 75, false));
        snake.getLootTable().add(new LootItem(ItemByID(ITEM_ID_SNAKESKIN), 75, true));

        Monster giantSpider = new Monster(MONSTER_ID_GIANT_SPIDER, "Giant spider", 20, 5, 40, 10, 10);
        giantSpider.getLootTable().add(new LootItem(ItemByID(ITEM_ID_SPIDER_FANG), 75, true));
        giantSpider.getLootTable().add(new LootItem(ItemByID(ITEM_ID_SPIDER_SILK), 25, false));

        monsters.add(rat);
        monsters.add(snake);
        monsters.add(giantSpider);
    }

    private static void PopulateQuests() {
        Quest clearAlchemistGarden = new Quest(
                QUEST_ID_CLEAR_ALCHEMIST_GARDEN,
                "Clear the alchemist's garden",
                "Kill rats in the alchemist's garden and bring back 3 rat tails. You will receive a healing potion and 10 gold pieces.",
                20,
                10);

        clearAlchemistGarden.getQuestCompletionItems().add(new QuestCompletionItem(ItemByID(ITEM_ID_RAT_TAIL), 3));

        clearAlchemistGarden.setRewardItem(ItemByID(ITEM_ID_HEALING_POTION));

        Quest clearFarmersField =
                new Quest(
                        QUEST_ID_CLEAR_FARMERS_FIELD,
                        "Clear the farmer's field",
                        "Kill snakes in the farmer's field and bring back 3 snake fangs. You will receive an adventurer's pass and 20 gold pieces.",
                        20,
                        20);

        clearFarmersField.getQuestCompletionItems().add(new QuestCompletionItem(ItemByID(ITEM_ID_SNAKE_FANG), 3));

        clearFarmersField.setRewardItem(ItemByID(ITEM_ID_ADVENTURER_PASS));

        quests.add(clearAlchemistGarden);
        quests.add(clearFarmersField);
    }

    private static void PopulateLocations() {
        // Create each location
        Location home = new Location.Builder(
                LOCATION_ID_HOME,
                "Home",
                "Your house. You really need to clean up the place.").build();

        Location townSquare = new Location.Builder(
                LOCATION_ID_TOWN_SQUARE,
                "Town square",
                "You see a fountain.").build();

        Location alchemistHut = new Location.Builder(
                LOCATION_ID_ALCHEMIST_HUT,
                "Alchemist's hut",
                "There are many strange plants on the shelves.")
                .questAvailableHere(QuestByID(QUEST_ID_CLEAR_ALCHEMIST_GARDEN))
                .build();


        Location alchemistsGarden = new Location.Builder(
                LOCATION_ID_ALCHEMISTS_GARDEN,
                "Alchemist's garden",
                "Many plants are growing here.")
                .monsterLivingHere(MonsterByID(MONSTER_ID_RAT))
                .build();


        Location farmhouse = new Location.Builder(
                LOCATION_ID_FARMHOUSE,
                "Farmhouse",
                "There is a small farmhouse, with a farmer in front.")
                .questAvailableHere(QuestByID(QUEST_ID_CLEAR_FARMERS_FIELD))
                .build();


        Location farmersField = new Location.Builder(
                LOCATION_ID_FARM_FIELD,
                "Farmer's field",
                "You see rows of vegetables growing here.")
                .monsterLivingHere(MonsterByID(MONSTER_ID_SNAKE))
                .build();

        Location guardPost = new Location.Builder(
                LOCATION_ID_GUARD_POST,
                "Guard post",
                "There is a large, tough-looking guard here.")
                .itemRequiredToEnter(ItemByID(ITEM_ID_ADVENTURER_PASS))
                .build();

        Location bridge = new Location.Builder(
                LOCATION_ID_BRIDGE,
                "Bridge",
                "A stone bridge crosses a wide river.")
                .build();

        Location spiderField = new Location.Builder(
                LOCATION_ID_SPIDER_FIELD,
                "Forest",
                "You see spider webs covering covering the trees in this forest.")
                .monsterLivingHere(MonsterByID(MONSTER_ID_GIANT_SPIDER))
                .build();


        // Link the locations together
        home.setLocationToNorth(townSquare);

        townSquare.setLocationToNorth(alchemistHut);
        townSquare.setLocationToSouth(home);
        townSquare.setLocationToEast(guardPost);
        townSquare.setLocationToWest(farmhouse);

        farmhouse.setLocationToEast(townSquare);
        farmhouse.setLocationToWest(farmersField);

        farmersField.setLocationToEast(farmhouse);

        alchemistHut.setLocationToSouth(townSquare);
        alchemistHut.setLocationToNorth(alchemistsGarden);

        alchemistsGarden.setLocationToSouth(alchemistHut);

        guardPost.setLocationToEast(bridge);
        guardPost.setLocationToWest(townSquare);

        bridge.setLocationToWest(guardPost);
        bridge.setLocationToEast(spiderField);

        spiderField.setLocationToWest(bridge);

        // Add the locations to the static list
        Locations.add(home);
        Locations.add(townSquare);
        Locations.add(guardPost);
        Locations.add(alchemistHut);
        Locations.add(alchemistsGarden);
        Locations.add(farmhouse);
        Locations.add(farmersField);
        Locations.add(bridge);
        Locations.add(spiderField);
    }

    public static Item ItemByID(int id) {

        for (Item item : items)
            if (item.getID() == id) return item;
        return null;
    }

    public static Monster MonsterByID(int id) {
        for (Monster monster : monsters)
            if (monster.getID() == id) return monster;
        return null;
    }

    public static Quest QuestByID(int id) {
        for (Quest quest : quests)
            if (quest.getID() == id) return quest;
        return null;
    }

    public static Location LocationByID(int id) {
        for (Location location : Locations)
            if (location.getID() == id)
                return location;
        return null;
    }


}
