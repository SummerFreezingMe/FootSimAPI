package com.footsim.domain.dto;

import com.footsim.domain.model.Coach;
import com.google.common.base.Objects;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Coach} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class CoachDTO implements Serializable {

    @NotNull(message = "id cannot be null")
    private Long id;

    private Long clubId;

    @NotNull(message = "rating cannot be null")
    private Integer rating;

    @NotNull(message = "Name cannot be null")
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachDTO coachDTO = (CoachDTO) o;
        return Objects.equal(getId(), coachDTO.getId()) && Objects.equal(getClubId(), coachDTO.getClubId()) &&
                Objects.equal(getRating(), coachDTO.getRating()) && Objects.equal(getName(), coachDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getClubId(), getRating(), getName());
    }

    @Override
    public String toString() {
            return "CoachDTO{" +
                "id=" + id +
                ", clubId=" + clubId +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                '}';
    }
}
