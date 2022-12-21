package dev.neiro.dental.controller.shared;

import dev.neiro.dental.entity.MovieSession;
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

@Path("/movie-sessions")
@Tag(name = "movie-sessions", description = "Сессия фильма")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApiResponse(responseCode = "" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, description = "Внутренняя ошибка сервера")
public interface MovieSessionController {

    @Operation(summary = "Добавление сессии фильма в кинотеатр", description = "Добавить сессию фильма в кинотеатре в определенный кинозал")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Созданная модель",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieSession.class)))
    @POST
    MovieSession create(
            @Parameter(description = "Сессия фильма", required = true) final MovieSession movieSession);

    @Operation(summary = "Обновить сессию фильма", description = "Обновить данные о сессии фильма")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NO_CONTENT, description = "Данные сессии фильма успешно обновлены")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Сессия фильма с таким id не найдена")
    @PUT
    @Path("/{id}")
    MovieSession update(
            @Parameter(description = "Идентификатор сессии фильма", required = true, schema = @Schema(example = "123"))
            @PathParam("id") final Long id,
            @Parameter(description = "Сессия фильма", required = true) final MovieSession movieSession);

    @Operation(summary = "Удалить сессию фильма")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NO_CONTENT, description = "Данные о сессии фильма успешно удалены")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Сессия фильма с таким id не найдена")
    @DELETE
    @Path("/{id}")
    void delete(
            @Parameter(description = "Идентификатор сессии фильма", required = true, schema = @Schema(example = "123"))
            @PathParam("id") final Long id);

    @Operation(summary = "Список сессий фильмов с пагинацией и фильтрацией",
            description = "Получить все сессии фильмов")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            description = "Все сессии фильмов",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(type = "array", implementation = MovieSession.class))))
    @GET
    List<MovieSession> findAll(
            @Parameter(description = "Номер страницы")
            @QueryParam("pageNum") final Integer pageNum,
            @Parameter(description = "Размер страницы")
            @QueryParam("pageSize") final Integer pageSize,
            @Parameter(description = "Поле для сортировки")
            @QueryParam("sortField") final String sortField);

    @Operation(summary = "Получение сессии фильма по id", description = "Получить данные сессии фильма по её id")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            description = "Сессия фильма",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieSession.class)))
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Сессия фильма с таким id не найдена")
    @GET
    @Path("/{id}")
    MovieSession findById(
            @Parameter(description = "Идентификатор сессии фильма", required = true)
            @PathParam("id") final Long id);
}
