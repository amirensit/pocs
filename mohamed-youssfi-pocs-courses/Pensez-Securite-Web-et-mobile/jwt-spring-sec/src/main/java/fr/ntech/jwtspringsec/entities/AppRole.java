package fr.ntech.jwtspringsec.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppRole {

    @Id
    @GeneratedValue
    private Long id;

    private String roleName;
}
