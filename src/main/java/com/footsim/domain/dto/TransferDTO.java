package com.footsim.domain.dto;

import com.footsim.domain.model.Coach;
import com.footsim.domain.model.Player;
import com.footsim.domain.model.Club;
import com.google.common.base.Objects;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * A DTO for the operation of transferring person (either {@link Player} or a {@link Coach})
 * from one club to another.
 */

@Data
public class TransferDTO {

    /**
     * An ID of a person that is getting transferred.
     */
    @NotNull(message = "personId cannot be null")
    private Long personId;

    /**
     * An ID of a {@link Club} from where the person is getting transferred.
     * null if free transfer
     */
    @NotNull(message = "clubFromId cannot be null")
    private Long clubFromId;
    /**
     * An ID of a {@link Club} to which the person is getting transferred.
     * null if club release
     */
    @NotNull(message = "clubToId cannot be null")
    private Long clubToId;

    /**
     * The transfer fee paid by buying club in dollars.
     */
    @NotNull(message = "transferFee cannot be null")
    private Long transferFee;
    /**
     * The date of a transfer.
     */
    @NotNull(message = "transferDate cannot be null")
    private LocalDateTime transferDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferDTO that = (TransferDTO) o;
        return Objects.equal(getPersonId(), that.getPersonId()) && Objects.equal(getClubFromId(), that.getClubFromId()) && Objects.equal(getClubToId(), that.getClubToId()) && Objects.equal(getTransferFee(), that.getTransferFee()) && Objects.equal(getTransferDate(), that.getTransferDate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPersonId(), getClubFromId(), getClubToId(), getTransferFee(), getTransferDate());
    }

    @Override
    public String toString() {
        return "TransferDTO{" +
                "playerId=" + personId +
                ", clubFromId=" + clubFromId +
                ", clubToId=" + clubToId +
                ", transferFee=" + transferFee +
                ", transferDate=" + transferDate +
                '}';
    }
}
