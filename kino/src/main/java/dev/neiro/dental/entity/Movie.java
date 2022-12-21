package dev.neiro.dental.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Table(schema = "core", name = "movies")
@ToString(callSuper = true, exclude = "genres")
@EqualsAndHashCode(callSuper = false, exclude = "genres")
@NoArgsConstructor
@Schema(description = "Фильм")
public class Movie implements Serializable {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор записи", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Название", required = true)
    private String name;

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Ссылка на картинку")
    private String image_url;

    @Schema(description = "Ссылка на трейлер")
    private String trailer_url;

    @Schema(description = "Возрастная категория")
    private String ageCategory;

    @Schema(description = "Страна")
    private String country;

    @Schema(description = "Режиссер")
    private String director;

    @Schema(description = "Длительность")
    private LocalDateTime duration;

    @Schema(description = "Минимальная стоимость")
    private Double minPrice;

    @Schema(description = "Жанры фильма")
    @OneToMany(fetch = EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movie_id")
    private Set<Genre> genres;
}
