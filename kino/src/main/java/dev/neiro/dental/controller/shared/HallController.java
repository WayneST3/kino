package dev.neiro.dental.controller.shared;

import dev.neiro.dental.entity.Hall;
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

@Path("/halls")
@Tag(name = "halls", description = "Кинозалы")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApiResponse(responseCode = "" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, description = "Внутренняя ошибка сервера")
public interface HallController {

    @Operation(summary = "Список кинозалов с пагинацией и фильтрацией",
            description = "Получить все кинозалы")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            description = "Все кинозалы",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(type = "array", implementation = Hall.class))))
    @GET
    List<Hall> findAll(
            @Parameter(description = "Номер страницы")
            @QueryParam("pageNum") final Integer pageNum,
            @Parameter(description = "Размер страницы")
            @QueryParam("pageSize") final Integer pageSize,
            @Parameter(description = "Поле для сортировки")
            @QueryParam("sortField") final String sortField);

    @Operation(summary = "Получение кинозала по id", description = "Получить данные кинозала по его id")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            description = "Кинозал",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hall.class)))
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Кинозал с таким id не найден")
    @GET
    @Path("/{id}")
    Hall findById(
            @Parameter(description = "Идентификатор кинозала", required = true)
            @PathParam("id") final Long id,
            @Parameter(description = "Идентификатор киносеанса", required = true)
            @QueryParam("sessionId") final Long sessionId);
}
