package com.footsim.domain.dto;


import com.footsim.domain.model.Team;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link Team} entity.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO implements Serializable {
    private Long id;

    private String name;

    private String rating;

    private String stadium;

    private String description;

    private Long leagueId;

    private String image;

    private Long balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDTO teamDTO = (TeamDTO) o;
        return Objects.equal(getId(), teamDTO.getId()) && Objects.equal(getName(), teamDTO.getName()) && Objects.equal(getRating(), teamDTO.getRating()) && Objects.equal(getStadium(), teamDTO.getStadium()) && Objects.equal(getDescription(), teamDTO.getDescription()) && Objects.equal(getLeagueId(), teamDTO.getLeagueId()) && Objects.equal(getBalance(), teamDTO.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getRating(), getStadium(), getDescription(), getLeagueId(), getBalance());
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
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
