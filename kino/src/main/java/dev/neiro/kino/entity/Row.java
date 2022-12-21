package dev.neiro.kino.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Table(schema = "core", name = "rows")
@ToString(callSuper = true, exclude = "seats")
@EqualsAndHashCode(callSuper = false, exclude = "seats")
@NoArgsConstructor
@Schema(description = "Ряд")
public class Row implements Serializable {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор записи", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Номер", required = true)
    private Integer number;

    @Schema(description = "Коэффициент цены")
    private Double priceKoef;

    @Transient
    @Schema(description = "Цена на фильм с учетом коэфициента на ряд")
    private Double price;

    @Schema(description = "Зал")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<Seat> seats = new ArrayList<>();
}
