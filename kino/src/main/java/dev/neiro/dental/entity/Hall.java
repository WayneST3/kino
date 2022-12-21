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

@Data
@Entity
@Table(schema = "core", name = "halls")
@ToString(callSuper = true, exclude = "rows")
@EqualsAndHashCode(callSuper = false, exclude = "rows")
@NoArgsConstructor
@Schema(description = "Зал")
public class Hall implements Serializable {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор записи", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Название", required = true)
    private String name;

    @Schema(description = "Время перерыва между сеансами")
    private LocalDateTime cleaningTime;

    @OneToMany(mappedBy = "hall", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<Row> rows = new ArrayList<>();
}
