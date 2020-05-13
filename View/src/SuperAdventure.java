


import sun.tools.java.Environment;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.stream.Collectors;


public class SuperAdventure implements EventListener {

    private JPanel panel1;
    private JButton btnTestButton;
    private JLabel lblGold;
    private JLabel lblExperience;
    private JLabel lblLevel;
    private JLabel lblHitPoints;
    private JPanel panel2;
    private JComboBox cboWeapons;
    private JComboBox cboPotions;
    private JButton btnNorth;
    private JButton btnWest;
    private JButton btnEast;
    private JButton btnSouth;
    private JButton btnUseWeapon;
    private JButton btnUsePotion;
    private JTextArea rtbMessages;
    private JTextArea rtbLocation;
    private JTable dgvInventory;
    private JTable dgvQuests;
    private JButton testButton;

    private Player player;
    private Monster currentMonster;

    private SuperAdventure() {
        player = new Player(10, 10, 20, 0, 1);

        player.setCurrentLocation(World.LocationByID(1));
        this.lblGold.setText(player.getGold().toString());
        this.lblExperience.setText(player.getExperiencePoints().toString());
        this.lblLevel.setText(player.getLevel().toString());
        this.lblHitPoints.setText(player.getCurrentHitPoints().toString());

        player.addPropertyChangeListener(
                e -> {
                    lblHitPoints.setText(String.valueOf(e.getNewValue()));
                    System.out.println("health changed to" + e.getNewValue());
                });

        btnEast.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MoveTo(player.getCurrentLocation().getLocationToEast());
            }
        });

        btnWest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MoveTo(player.getCurrentLocation().getLocationToWest());
            }
        });
        btnNorth.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MoveTo(player.getCurrentLocation().getLocationToNorth());
            }
        });
        btnSouth.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MoveTo(player.getCurrentLocation().getLocationToSouth());
            }
        });

        testButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                player.setCurrentHitPoints(RandomNumberGenerator.numberBetween(1, 20));
            }
        });

    }


    public static void main(String[] args) {
        SuperAdventure superAdventure = new SuperAdventure();
        JFrame frame = new JFrame("Super Adventure");
        frame.setContentPane(superAdventure.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void MoveTo(Location newLocation) {
        final String msgDivisor = "---------------------------" + System.lineSeparator();

        if (newLocation == null) {
            rtbMessages.insert(
                    "no location in this direction" + System.lineSeparator()
                            + msgDivisor, 0);
            return;
        }

        if (newLocation.getItemRequiredToEnter() != null
                && player.getInventory().stream()
                .noneMatch(x -> x.getDetails().equals(newLocation.getItemRequiredToEnter()))) {

            String msg = "You must have a " + newLocation.getItemRequiredToEnter().getName()
                    + " to enter this location." + System.lineSeparator()
                    + msgDivisor;
            rtbMessages.insert(msg, 0);

            return;
        }

        player.setCurrentLocation(newLocation);

        rtbLocation.setText(newLocation.getDescription());
        rtbLocation.insert(newLocation.getName() + System.lineSeparator(), 0);

        String successMsg = "successfully entered " + newLocation.getName() + System.lineSeparator()
                + Optional.ofNullable(newLocation.getLocationToSouth()).map(loc -> "s " + loc.getName() + System.lineSeparator()).orElse("")
                + Optional.ofNullable(newLocation.getLocationToEast()).map(loc -> "e " + loc.getName() + System.lineSeparator()).orElse("")
                + Optional.ofNullable(newLocation.getLocationToNorth()).map(loc -> "n " + loc.getName() + System.lineSeparator()).orElse("")
                + Optional.ofNullable(newLocation.getLocationToWest()).map(loc -> "w " + loc.getName() + System.lineSeparator()).orElse("")
                + msgDivisor;

        rtbMessages.insert(successMsg, 0);
        setButtonsVisibility(newLocation);
        player.heal();
        manageLocationQuest(newLocation);

//        rtbMessages.setText("successfully entered " + newLocation.getName());
//        rtbMessages.setText("s " + newLocation.getName());
//        rtbMessages.setText("e " + newLocation.getLocationToEast().getName());
//        rtbMessages.setText("n " + newLocation.getLocationToNorth().getName());
//        rtbMessages.setText("w " + newLocation.getLocationToWest().getName());

    }

    public void setButtonsVisibility(Location location) {
        btnSouth.setVisible(location.getLocationToSouth() != null);
        btnEast.setVisible(location.getLocationToEast() != null);
        btnNorth.setVisible(location.getLocationToNorth() != null);
        btnWest.setVisible(location.getLocationToWest() != null);
    }

    private void manageLocationQuestFunctional(Location location) {
        Quest locationQuest = location.getQuestAvailableHere();
        Collection<InventoryItem> playerInventory = player.getInventory();

        Optional.ofNullable(locationQuest)
                .ifPresent(locQuest -> player.getQuests().stream()
                        .filter(playerQuest -> playerQuest.getDetails().equals(locQuest) && !playerQuest.getCompleted()).findAny()
                        .<Runnable>map(incompletedPlayerQuest ->
                                () -> Optional.of(incompletedPlayerQuest)
                                        .filter(q1 -> q1.getDetails().getQuestCompletionItems().stream()
                                                .allMatch(questItm -> playerInventory.stream()
                                                        .anyMatch(invItm -> invItm.getDetails().equals(questItm.getDetails()))))
                                        .ifPresent(this::completeQuest)) //If not, does the player have the items to complete the quest?
                        .orElse(
                                () -> addQuest(locQuest)
                        ).run());
    }

    private void manageLocationQuest(Location location) {
        //Does the location have a quest?
        //If so, does the player already have the quest?
        //If so, is the quest already completed?
        Quest locationQuest = location.getQuestAvailableHere();
        Optional<PlayerQuest> incompletedPlayerQuestOption = player.getQuests().stream()
                .filter(q -> q.getDetails().equals(locationQuest) && !q.getCompleted())
                .findAny();
        Collection<InventoryItem> playerInventory = player.getInventory();

        if (incompletedPlayerQuestOption.isPresent()) {
            incompletedPlayerQuestOption.filter(playerQuest -> playerQuest.getDetails().getQuestCompletionItems().stream()
                    .allMatch(questItm -> playerInventory.stream()
                            .anyMatch(invItm -> invItm.getDetails().equals(questItm.getDetails()))))    //If not, does the player have the items to complete the quest?
                    .ifPresent(this::completeQuest);
        } else {
            Optional.ofNullable(locationQuest).ifPresent(this::addQuest);
        }
    }
/**
 * Is there a monster at the location?
 * If so,
 * Display message
 * Spawn new monster to fight
 * Display combat comboboxes and buttons
 * Repopulate comboboxes, in case inventory changed
 * If not
 * Hide combat comboboxes and buttons
 * Refresh the player’s inventory in the UI – in case it changed
 * Refresh the player’s quest list in the UI – in case it changed
 * Refresh the cboWeapons ComboBox in the UI
 * Refresh the cboPotions ComboBox in the UI
 * */
    private void manageLocationMonster(Location location) {
        if (location.getMonsterLivingHere() != null) {
            rtbMessages.insert("You see a " + location.getMonsterLivingHere().getName() + System.lineSeparator(), 0);
            Monster currentMonster = World.MonsterByID(location.getMonsterLivingHere().getID()).clone();

        } else {

        }
    }

    private void completeQuest(PlayerQuest playerQuest) {
        Collection<InventoryItem> playerInventory = player.getInventory();
        Collection<QuestCompletionItem> questCompletionItems = playerQuest.getDetails().getQuestCompletionItems();
        //Display messages
        rtbMessages.insert("quest " + playerQuest.getDetails().getName() + " completed", 0);
        //Remove quest completion items from inventory
        questCompletionItems.forEach(inventoryItem ->
                questCompletionItems.stream()
                        .filter(questCompletionItem -> inventoryItem.getDetails().equals(questCompletionItem.getDetails()))
                        .findAny()
                        .ifPresent(questItem -> inventoryItem.setQuantity(inventoryItem.getQuantity() - questItem.getQuantity())));
        //Give quest rewards
        questCompletionItems.forEach(
                itm -> playerInventory.add(new InventoryItem(itm.getDetails())));
        //Mark player’s quest as completed
        playerQuest.setCompleted(true);
    }

    private void addQuest(Quest quest) {
        player.getQuests().add(new PlayerQuest(quest, false));
        rtbMessages.insert("You receive the " + quest.getName() + " quest." + System.lineSeparator()
                        + quest.getDescription() + System.lineSeparator()
                        + "To complete it, return with:" + System.lineSeparator()
                        + quest.getQuestCompletionItems().stream()
                        .map(itm -> itm.getDetails().getNamePlural()).reduce("\n", String::concat)
                , 0);

    }

}
