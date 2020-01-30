package com.example.demo.services.dto;

import com.example.demo.config.validations.OnCreate;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

public class MarketDTO {

    private String id;

    private String marketCode;

    @NotNull(groups = OnCreate.class)
    private String label;

    private String adress;

    private Set<ActorDTO> affectedOperators;

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

    public Set<ActorDTO> getAffectedOperators() {
        return affectedOperators;
    }

    public void setAffectedOperators(Set<ActorDTO> affectedOperators) {
        this.affectedOperators = affectedOperators;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MarketDTO marketDTO = (MarketDTO) o;
        if (marketDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), marketDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
