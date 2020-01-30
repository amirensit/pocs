package com.example.demo.domain;

import com.example.demo.config.validations.OnCreate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "market")
public class Market implements Serializable {

    @Id
    private String id;

    private String marketCode;

    @NotNull(groups = OnCreate.class) // this means that the label field should not be null in creation. It can be null in other scenarios like edit.
    private String label;

    private String adress;

    @DBRef
    private Set<Actor> affectedOperators = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public Set<Actor> getAffectedOperators() {
        return affectedOperators;
    }

    public void setAffectedOperators(Set<Actor> affectedOperators) {
        this.affectedOperators = affectedOperators;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Market addAffectedOperator(Actor actor) {
        this.affectedOperators.add(actor);
        return this;
    }

    public Market removeAffectedOperator(Actor actor) {
        this.affectedOperators.remove(actor);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Market)) {
            return false;
        }
        return id != null && id.equals(((Market) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Market{" +
                "id='" + id + '\'' +
                ", marketCode='" + marketCode + '\'' +
                ", label='" + label + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
