package ru.grishchenko.entity;

public class Category {

    private Long id;
    private String title;
    private String Description;

    public Category() {
    }

    public Category(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        Description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
