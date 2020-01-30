package com.example.demo.services.dto;

import com.example.demo.config.validations.customAnnotations.IpAddress;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ActorDTO {

    private String id;

    @Size(min = 3, max = 10)
    private String actorCode;

    private String firstName;

    @Size(min = 4, max = 12)
    private String lastName;

    @IpAddress
    private String ipAddress; // just for testing custom validator annotation

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getActorCode() {
        return actorCode;
    }

    public void setActorCode(String actorCode) {
        this.actorCode = actorCode;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActorDTO actorDTO = (ActorDTO) o;
        if (actorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), actorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
