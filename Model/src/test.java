import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.*;
import java.util.List;


import static java.lang.Math.multiplyExact;


public class test {
    public static void main1(String... args) {
        World.getItems().sort(Comparator.comparing(Item::getName, Comparator.nullsFirst(Comparator.naturalOrder())));

        Collection<String> n = new LinkedList<>();
        n.add("fds");


        class eventL implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("time is: " + Instant.ofEpochMilli(e.getWhen()));
                Toolkit.getDefaultToolkit().beep();
            }
        }


        Timer t = new Timer(1000, e -> Toolkit.getDefaultToolkit().beep());

        Timer u = new Timer(1000, e -> Toolkit.getDefaultToolkit().beep());

        t.start();
        JOptionPane.showMessageDialog(null, "");
        //System.exit(0);
    }


    public static void main(String[] args) {
        System.out.println(multiplyExact(1000000000, 3));
        Monster mon = new Monster(1, "g", 4, 5, 6, 6, 6);

        List<LootItem> lt = mon.getLootTable();
        mon.getLootTable().add(new LootItem(new Item(1, "54", "5656h,j"), 4, true));
        lt.add(new LootItem(new Item(1, "5fd4", "565fsd6h,j"), 4, true));
        mon.getLootTable().add(new LootItem(new Item(1, "5fd4", "565fsd6h,j"), 4, true));
        mon.setID(45);
        Item it = World.ItemByID(1);
        System.out.println(it.getName());
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 10; i++)
            nums.add(RandomNumberGenerator.numberBetween(10, 20));

        System.out.println(nums.toString());
    }
}
