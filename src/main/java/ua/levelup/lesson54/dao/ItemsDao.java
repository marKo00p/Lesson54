package ua.levelup.lesson54.dao;

import ua.levelup.lesson54.domain.Item;
import java.util.List;

public interface ItemsDao {
    List<Item> showAllItems();
    int[] insertMultiItems(List<Item> list);
    int insertOneItem(Item item);
}
