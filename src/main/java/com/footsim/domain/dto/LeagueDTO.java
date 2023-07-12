package com.footsim.domain.dto;


import com.footsim.domain.model.League;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link League} entity.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeagueDTO {
    private Long id;

    private Integer participants;

    private String name;

    @Override
    public String toString() {
        return "LeagueDTO{" +
                "id=" + id +
                ", participants=" + participants +
                ", name='" + name + '\'' +
                '}';
    }
}
