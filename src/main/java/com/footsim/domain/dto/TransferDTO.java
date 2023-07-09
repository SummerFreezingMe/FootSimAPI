package com.footsim.domain.dto;

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
}
