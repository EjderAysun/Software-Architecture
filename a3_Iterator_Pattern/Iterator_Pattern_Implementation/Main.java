package a3_Iterator_Pattern.Iterator_Pattern_Implementation;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create Aggregate
        AbstractAggregate aggregate = new Collection();
        aggregate.add(new Item("Electron"));
        aggregate.add(new Item("Positron"));
        aggregate.add(new Item("Muon"));
        aggregate.add(new Item("Nutrino"));
        aggregate.add(new Item("Photon"));
        aggregate.add(new Item("Z"));
        aggregate.add(new Item("W"));
        aggregate.add(new Item("Higgs"));
        aggregate.add(new Item("Proton"));
        aggregate.add(new Item("Nutron"));
        aggregate.add(new Item("pi-Meson"));
        aggregate.add(new Item("k-Meson"));

        // Create Iterator
        AbstractIterator iterator = aggregate.CreateIterator();

        // Traverse the Aggregate
        printAggregate(iterator);
    }

    static void printAggregate(AbstractIterator i) {
        System.out.println("Iterating over collection:");
        for (i.First(); !i.IsDone(); i.Next()) {
            System.out.println(i.CurrentItem().getName());
        }
        System.out.println();
    }
}

// the object in question
// Our concrete collection consists of Items.
class Item {
    private String _name;
    public Item(String name) {_name = name;}
    public String getName() {return _name;}
}

interface AbstractAggregate {
    public AbstractIterator CreateIterator();
    public void add(Item it); // Not needed for iteration.
    public int getCount(); // Needed for iteration.
    public Item get(int idx); // Needed for iteration.
}

class Collection implements AbstractAggregate {
    private ArrayList<Item> _items = new ArrayList<Item>();
    public CollectionIterator CreateIterator() {
        return new CollectionIterator(this);
    }
    public int getCount() { return _items.size(); }
    public void add(Item item) { _items.add(item); }
    public Item get(int index) { return _items.get(index); }
}

interface AbstractIterator {
    void First();
    void Next();
    Boolean IsDone();
    Item CurrentItem();
}

// This is the concrete Iterator for collection.
class CollectionIterator implements AbstractIterator {
    private Collection _collection;
    private int _current = 0;

    public CollectionIterator(Collection collection) {
        _collection = collection;
        _current = 0;
    }

    public void First() {_current = 0;}
    public void Next() {_current++;}
    public Boolean IsDone() {return _current >= _collection.getCount();}
    public Item CurrentItem() {return (IsDone()?null:_collection.get(_current));}
}