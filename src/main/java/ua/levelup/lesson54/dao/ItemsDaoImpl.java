package ua.levelup.lesson54.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import ua.levelup.lesson54.domain.Item;
import ua.levelup.lesson54.service.ItemService;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Repository
public class ItemsDaoImpl implements ItemsDao{
    private final static String INSERT_NEW_ITEM = "INSERT INTO lesson54.items (name, description, price) VALUES(?,?,?)";
    private final static String SELECT_ALL_ITEMS = "SELECT * FROM lesson54.items";

    @Autowired
    private final ItemService itemService;
    public ItemsDaoImpl(ItemService itemService){
        this.itemService = itemService;
    }
    @Override
    public List<Item> showAllItems() {
        return itemService.getJdbcTemplate().query(SELECT_ALL_ITEMS, new BeanPropertyRowMapper<>(Item.class));
    }

    @Override
    public int[] insertMultiItems(List<Item> list) {

        return itemService.getJdbcTemplate().batchUpdate(INSERT_NEW_ITEM , new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, list.get(i).getName());
                ps.setString(2, list.get(i).getDescription());
                ps.setInt(3, list.get(i).getPrice());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    @Override
    public int insertOneItem(Item item) {
        return itemService.getJdbcTemplate().update(INSERT_NEW_ITEM, item.getName(),
                item.getDescription(),
                item.getPrice());
    }
}
