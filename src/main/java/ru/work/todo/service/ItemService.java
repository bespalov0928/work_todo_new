package ru.work.todo.service;

import org.springframework.stereotype.Service;
import ru.work.todo.model.Item;
import ru.work.todo.model.User;
import ru.work.todo.persistence.ItemStore;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemStore store;

    public ItemService(ItemStore store) {
        this.store = store;
    }

    public List<Item> findAll() {
        return store.findAll();
    }

    public List<Item> findAllNew() {
        return store.findAllNew();
    }

    public List<Item> findAllDone() {
        return store.findAllDone();
    }

    public Item findBuId(Integer id) {
        return store.findItemId(id);
    }

    public void updateByIdWhenDone(int id) {
        boolean rsl = store.updateByIdWhenDone(id);
    }

}
