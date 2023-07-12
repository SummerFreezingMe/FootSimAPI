package com.footsim.domain.dto;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {

    private Long playerId;

    private Long clubFromId;

    private Long clubToId;

    private Long transferFee;

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
