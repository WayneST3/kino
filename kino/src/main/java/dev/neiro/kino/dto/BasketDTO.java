package dev.neiro.kino.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Wayne Stark
 * @since 26.10.2022
 */
@Data
@NoArgsConstructor
@Schema(description = "Транспортный объект товаров в корзине")
public class BasketDTO {

    @Schema(description = "Номер заказа")
    private Long id;
    @Schema(description = "Общая сумма")
    private Double totalPrice;
    @Schema(description = "Дата заказа")
    private LocalDateTime date;
    @Schema(description = "Статус")
    private String status;
//    @Schema(description = "Список товаров")
//    private List<ProductDTO> products;
    @Schema(description = "Оплачен заказ")
    private Boolean isPaid;

    public BasketDTO(Long id, Double totalPrice, LocalDateTime date, String status, Boolean isPaid) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.date = date;
        this.status = status;
        this.isPaid = isPaid;
    }

    public BasketDTO(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
