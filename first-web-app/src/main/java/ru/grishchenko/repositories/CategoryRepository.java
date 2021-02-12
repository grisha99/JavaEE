package ru.grishchenko.repositories;

import ru.grishchenko.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CategoryRepository {

    private final Map<Long, Category> categoryMap = new ConcurrentHashMap<Long, Category>();

    private final AtomicLong identity = new AtomicLong(0);

    public CategoryRepository() {
        saveOrUpdate(new Category(null, "Продукты", "Продукты питания"));
        saveOrUpdate(new Category(null, "Бытовая техника", "Техника для дома"));
        saveOrUpdate(new Category(null, "Мебель", "Спальни, прихожые и тд..."));
    }

    public List<Category> findAll() {
        return new ArrayList<Category>(categoryMap.values());
    }

    public Category findById(Long id) {
        return categoryMap.get(id);
    }

    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            Long id = identity.incrementAndGet();
            category.setId(id);
        }
        categoryMap.put(category.getId(), category);
    }

    public void deleteById(Long id) {
        categoryMap.remove(id);
    }
}
