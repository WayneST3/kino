package dev.neiro.kino.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "core", name = "movie_sessions")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, exclude = "parent")
@NoArgsConstructor
@Schema(description = "Сессии фильмов в кинотеатрах")
public class MovieSession implements Serializable {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор записи", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Идентификатор фильма", required = true)
    private Long movieId;

    @Schema(description = "Идентификатор кинозала", required = true)
    private Long hallId;

    @Schema(description = "Время начала сеанса")
    private LocalDateTime startTime;

    @Schema(description = "Время конца сеанса")
    private LocalDateTime endTime;

    @Schema(description = "Дата сеанса")
    private LocalDate sessionDate;
}
