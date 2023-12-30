package Data;

/**
 * An interface representing objects that can be searched based on title and/or title and item type.
 */
public interface Searchable {
    /**
     * Searches for an item by title.
     *
     * @param title The title to search for.
     * @return true if an item with the specified title is found, false otherwise.
     */
    boolean searchByTitle(String title);

    /**
     * Searches for an item by both title and item type.
     *
     * @param title    The title to search for.
     * @param itemType The item type to search for.
     * @return true if an item with the specified title and item type is found, false otherwise.
     */
    boolean searchByTitleAndItemType(String title, String itemType);
}
