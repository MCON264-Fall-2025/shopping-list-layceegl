package exercises.shopping_app;

public class ShoppingItem implements Comparable<ShoppingItem> {
    private int aisle;
    private String name;

    public ShoppingItem(int aisle, String name) {
        this.aisle = aisle;
        this.name = name;
    }

    public int getAisle() {
        return aisle;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(ShoppingItem other) {
        // First compare aisles (ascending numbers)
        int aisleCompare = Integer.compare(this.aisle, other.aisle);
        if (aisleCompare != 0) {
            return aisleCompare;
        }
        // If aisles equal, compare names alphabetically (ignore case)
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return "Aisle " + aisle + ": " + name;
    }
}
