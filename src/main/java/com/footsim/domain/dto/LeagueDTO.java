package com.footsim.domain.dto;


import com.footsim.domain.model.League;
import com.google.common.base.Objects;
import lombok.*;

/**
 * A DTO for the {@link League} entity.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class LeagueDTO {
    private Long id;

    private Integer participants;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueDTO leagueDTO = (LeagueDTO) o;
        return Objects.equal(getId(), leagueDTO.getId()) && Objects.equal(getParticipants(), leagueDTO.getParticipants()) && Objects.equal(getName(), leagueDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getParticipants(), getName());
    }

    @Override
    public String toString() {
        return "LeagueDTO{" +
                "id=" + id +
                ", participants=" + participants +
                ", name='" + name + '\'' +
                '}';
    }
}
