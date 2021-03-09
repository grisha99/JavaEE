package dto;


public class CategoryDto {

    private Long id;

    private String title;

    private String description;

//    private List<Product> productList;

    public CategoryDto() {

    }

//    public CategoryDto(Category category) {
//        this.id = category.getId();
//        this.title = category.getTitle();
//        this.description = category.getDescription();
//    }

    public CategoryDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
