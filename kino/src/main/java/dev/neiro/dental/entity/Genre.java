package dev.neiro.dental.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(schema = "core", name = "movie_genres")
@ToString(callSuper = true, exclude = "parent")
@EqualsAndHashCode(callSuper = false, exclude = "parent")
@NoArgsConstructor
@Schema(description = "Жанр фильма")
public class Genre implements Serializable {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Идентификатор фильма", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "movie_id")
    private Long movieId;

    @Schema(description = "Жанр", required = true)
    private String genre;
}
