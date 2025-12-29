package exercises.shopping_app;

import edu.touro.mcon264.apps.collections.ListInterface;
import edu.touro.mcon264.apps.collections.ArrayBasedList;
import java.util.Scanner;
import java.util.Iterator;

/**
 * Creates a ListInterface<ShoppingItem> instance.
 * Has a main method that runs a console application.
 * Provides a simple text-based menu to:
 * Add items (in sorted order).
 * View the current list.
 * "Shop" the next item on the list.
 * Exit the program.
 */
public class ShoppingListApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Choose implementation (try ArrayBasedList first):
        ListInterface<ShoppingItem> shoppingList = new edu.touro.mcon264.apps.collections.LinkedBasedList<>();

        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter aisle: ");
                    String aisleStr = scanner.nextLine().trim();
                    int aisleNum = Integer.parseInt(aisleStr);
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine().trim();
                    ShoppingItem newItem = new ShoppingItem(aisleNum, name);
                    try {
                        insertSorted(shoppingList, newItem);
                        System.out.println("Added: " + newItem);
                    } catch (Exception e) {
                        System.out.println("ERROR in insertSorted: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case "2":
                    printList(shoppingList);
                    break;
                case "3":
                    ShoppingItem next = shopNext(shoppingList);
                    if (next == null) {
                        System.out.println("All done! No items left to shop.");
                    } else {
                        System.out.println("Next item to buy: " + next);
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Unknown option. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== Shopping List Menu ===");
        System.out.println("1. Add item");
        System.out.println("2. Show current shopping list");
        System.out.println("3. Shop next item");
        System.out.println("0. Exit");
    }

    public static void insertSorted(ListInterface<ShoppingItem> list, ShoppingItem item) {
        if (list.size() == 0) {
            list.add(0, item);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            ShoppingItem current = list.get(i);
            if (item.compareTo(current) < 0) {
                list.add(i, item);
                return;
            }
        }

        list.add(list.size(), item);
    }

    public static ShoppingItem shopNext(ListInterface<ShoppingItem> list) {
        if (list.size() == 0) {
            return null;
        }

        ShoppingItem next = list.get(0);
        list.remove(0);
        return next;
    }

    private static void printList(ListInterface<ShoppingItem> list) {
        if (list.size() == 0) {
            System.out.println("Shopping list is empty.");
            return;
        }

        System.out.println("Current shopping list:");
        java.util.Iterator<ShoppingItem> iter = list.iterator();
        while (iter.hasNext()) {
            ShoppingItem item = iter.next();
            System.out.println("  " + item);
        }
    }
}
