package com.footsim.domain.dto;


import com.footsim.domain.model.Club;
import com.google.common.base.Objects;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link Club} entity.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class ClubDTO implements Serializable {

    @NotNull(message = "id cannot be null")
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "rating cannot be null")
    private Integer rating;

    private String stadium;

    private String description;

    private Long leagueId;

    private String image;

    @NotNull(message = "balance cannot be null")
    private Long balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubDTO clubDTO = (ClubDTO) o;
        return Objects.equal(getId(), clubDTO.getId()) && Objects.equal(getName(), clubDTO.getName()) &&
                Objects.equal(getRating(), clubDTO.getRating()) && Objects.equal(getStadium(), clubDTO.getStadium()) &&
                Objects.equal(getDescription(), clubDTO.getDescription()) &&
                Objects.equal(getLeagueId(), clubDTO.getLeagueId()) && Objects.equal(getBalance(), clubDTO.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getRating(), getStadium(), getDescription(), getLeagueId(), getBalance());
    }

    @Override
    public String toString() {
        return "ClubDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", stadium='" + stadium + '\'' +
                ", description='" + description + '\'' +
                ", leagueId=" + leagueId +
                ", image='" + image + '\'' +
                ", balance=" + balance +
                '}';
    }
}
