


import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;


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
}
