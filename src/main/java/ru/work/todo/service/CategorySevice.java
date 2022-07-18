package ru.work.todo.service;

import org.springframework.stereotype.Service;
import ru.work.todo.model.Category;
import ru.work.todo.persistence.CategoryStore;

import java.util.List;

@Service
public class CategorySevice {
    private final CategoryStore store;

    public CategorySevice(CategoryStore store) {
        this.store = store;
    }

    public List<Category> findAll(){
        return store.findAll();
    }

    public Category findById(int id){
        return store.findById(id);
    }
}
