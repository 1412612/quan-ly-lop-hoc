package demo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Book {
    private Integer id;

    public Book(Integer id, String title, Integer quantity, Double price) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    private String title;
    private Integer quantity;
    private Double price;
    private Double totalMoney;
}
