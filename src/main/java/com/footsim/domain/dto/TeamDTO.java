package com.footsim.domain.dto;


import com.footsim.domain.model.Team;
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

    private String genreDescriptors;

    private String description;

    private Long leagueId;

    private String image;

}
