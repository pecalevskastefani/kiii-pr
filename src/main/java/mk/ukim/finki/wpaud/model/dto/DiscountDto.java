package mk.ukim.finki.wpaud.model.dto;

import lombok.Data;
import mk.ukim.finki.wpaud.model.Discount;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Data
public class DiscountDto {
    private LocalDateTime validUntil;

    public DiscountDto() {
    }

    public DiscountDto(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
