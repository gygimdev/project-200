package hello.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Size(min = 4, max = 25)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

   @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();
}
