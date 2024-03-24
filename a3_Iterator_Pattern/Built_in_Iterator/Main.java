package a3_Iterator_Pattern.Built_in_Iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        // Iterating over a LinkedList
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Electron");
        linkedList.add("Positron");
        linkedList.add("Muon");
        linkedList.add("Nutrino");
        linkedList.add("Photon");
        linkedList.add("Z");
        linkedList.add("W");
        linkedList.add("Higgs");
        linkedList.add("Protons");
        linkedList.add("Nutron");
        linkedList.add("pi-Meson");
        linkedList.add("k-Meson");
        // ... Find and add other particle(s)

        // Iterating Forwards
        Iterator<String> forward_iterator = linkedList.iterator();
        printForwardAggregate(forward_iterator);

        System.out.println("--------------");

        // Iterating Backwards
        ListIterator<String> backward_iterator = linkedList.listIterator(linkedList.size());
        printBackwardAggregate(backward_iterator);
    }

    static void printForwardAggregate(Iterator<String> itr) {
        System.out.println("Iterating forward over collection:");
        while (itr.hasNext()) {
            String element = itr.next();
            System.out.println(element);
        }
    }

    static void printBackwardAggregate(ListIterator<String> itr) {
        System.out.println("Iterating backward over collection:");
        while (itr.hasPrevious()) {
            String element = itr.previous();
            System.out.println(element);
        }
    }

}