package ltweb.entity;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", length = 50, columnDefinition = "nvarchar(50) not null")
    private String name;
}
