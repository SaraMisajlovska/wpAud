package mk.finki.ukim.wpaud.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiscountDto {
    LocalDateTime validUntil;

    public DiscountDto() {
    }

    public DiscountDto(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
