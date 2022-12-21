package dev.neiro.dental.controller.shared;

import dev.neiro.dental.entity.Movie;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/movies")
@Tag(name = "movies", description = "Фильмы")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApiResponse(responseCode = "" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, description = "Внутренняя ошибка сервера")
public interface MovieController {

    @Operation(summary = "Добавление фильма", description = "Добавить фильм")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Созданная модель",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class)))
    @POST
    Movie create(
            @Parameter(description = "Фильм", required = true) final Movie movie);

    @Operation(summary = "Обновить фильм", description = "Обновить данные фильма")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NO_CONTENT, description = "Данные фильма успешно обновлены")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Фильм с таким id не найден")
    @PUT
    @Path("/{id}")
    Movie update(
            @Parameter(description = "Идентификатор фильма", required = true, schema = @Schema(example = "123"))
            @PathParam("id") final Long id,
            @Parameter(description = "Фильм", required = true) final Movie movie);

    @Operation(summary = "Удалить фильм")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NO_CONTENT, description = "Данные о фильме успешно удалены")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Фильм с таким id не найден")
    @DELETE
    @Path("/{id}")
    void delete(
            @Parameter(description = "Идентификатор фильма", required = true, schema = @Schema(example = "123"))
            @PathParam("id") final Long id);

    @Operation(summary = "Список фильмов с пагинацией и фильтрацией",
            description = "Получить все фильмы")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            description = "Все фильмы",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(type = "array", implementation = Movie.class))))
    @GET
    List<Movie> findAll(
            @Parameter(description = "Номер страницы")
            @QueryParam("pageNum") final Integer pageNum,
            @Parameter(description = "Размер страницы")
            @QueryParam("pageSize") final Integer pageSize,
            @Parameter(description = "Поле для сортировки")
            @QueryParam("sortField") final String sortField);

    @Operation(summary = "Получение фильма по id", description = "Получить данные фильма по его id")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            description = "Фильм",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class)))
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Фильм с таким id не найден")
    @GET
    @Path("/{id}")
    Movie findById(
            @Parameter(description = "Идентификатор фильма", required = true)
            @PathParam("id") final Long id);
}
