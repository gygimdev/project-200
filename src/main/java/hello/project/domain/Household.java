package hello.project.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Household extends AuditableEntity{

    protected Household() {}

    public Household(String name) {
        this.name = name;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="household_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
}
