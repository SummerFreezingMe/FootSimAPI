package com.footsim.domain.dto;

import com.footsim.domain.model.Season;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link Season} entity.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeasonDTO {
    private Long id;

    private Long leagueId;

    private String year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeasonDTO seasonDTO = (SeasonDTO) o;
        return Objects.equal(getId(), seasonDTO.getId()) && Objects.equal(getLeagueId(), seasonDTO.getLeagueId()) && Objects.equal(getYear(), seasonDTO.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getLeagueId(), getYear());
    }

    @Override
    public String toString() {
        return "SeasonDTO{" +
                "id=" + id +
                ", leagueId=" + leagueId +
                ", year=" + year +
                '}';
    }
}
