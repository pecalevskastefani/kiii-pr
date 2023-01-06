package mk.ukim.finki.wpaud.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Category {
    @Id// mora da ima category identifikator/primaren kluc
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 5000)
    private String description;

    public Category(String name, String description) {

        this.name = name;
        this.description = description;
    }

    public Category() {
    }
}
