package com.footsim.domain.dto;

import com.footsim.domain.model.Player;
import com.footsim.domain.model.Team;
import com.google.common.base.Objects;
import lombok.*;

import java.time.LocalDateTime;
/**
 * A DTO for the operation of transferring player from one team to another.
 */

@Data
public class TransferDTO {

    /**
     * An ID of a {@link Player} that is getting transferred.
     */
    private Long playerId;

    /**
     * An ID of a {@link Team} from where the player is getting transferred.
     */
    private Long clubFromId;
    /**
     * An ID of a {@link Team} to which the player is getting transferred.
     */
    private Long clubToId;

    /**
     * The transfer fee paid by buying team in dollars.
     */
    private Long transferFee;
    /**
     * The date of a transfer.
     */
    private LocalDateTime transferDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferDTO that = (TransferDTO) o;
        return Objects.equal(getPlayerId(), that.getPlayerId()) && Objects.equal(getClubFromId(), that.getClubFromId()) && Objects.equal(getClubToId(), that.getClubToId()) && Objects.equal(getTransferFee(), that.getTransferFee()) && Objects.equal(getTransferDate(), that.getTransferDate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPlayerId(), getClubFromId(), getClubToId(), getTransferFee(), getTransferDate());
    }

    @Override
    public String toString() {
        return "TransferDTO{" +
                "playerId=" + playerId +
                ", clubFromId=" + clubFromId +
                ", clubToId=" + clubToId +
                ", transferFee=" + transferFee +
                ", transferDate=" + transferDate +
                '}';
    }
}
