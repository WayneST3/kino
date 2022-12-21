package dev.neiro.dental.controller.shared;

import dev.neiro.dental.entity.MovieSessionSeatId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/movie-sessions-seats")
@Tag(name = "movie-sessions-seats", description = "Бронь на место на фильм")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApiResponse(responseCode = "" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, description = "Внутренняя ошибка сервера")
public interface MovieSessionSeatController {

    @Operation(summary = "Бронь места", description = "Бронь места на определенный киносеанс")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NO_CONTENT, description = "Место забронировано")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "* Киносеанс с таким id не найден \n* Место с таким id не найдено")
    @POST
    void create(
            @Parameter(description = "Id киносеанса и id места", required = true) final MovieSessionSeatId id);

    @Operation(summary = "Удалить бронь")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_NO_CONTENT, description = "Бронь на место успешно удалена")
    @DELETE
    void delete(
            @Parameter(description = "Id киносеанса и id места", required = true) final MovieSessionSeatId id);
}
