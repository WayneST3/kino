package dev.neiro.kino.controller;

import dev.neiro.kino.controller.shared.HallController;
import dev.neiro.kino.entity.Hall;
import dev.neiro.kino.service.HallService;
import dev.neiro.kino.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import java.util.List;

@Component
@Path("/halls")
public class HallControllerImpl implements HallController {

    private final HallService hallService;

    @Autowired
    public HallControllerImpl(HallService hallService) {
        this.hallService = hallService;
    }

    @Override
    public List<Hall> findAll(Integer pageNum, Integer pageSize, String sortField) {
        return hallService.findAll(PageableUtil.generatePageable(pageNum, pageSize, sortField));
    }

    @Override
    public Hall findById(Long id, Long sessionId) {
        return hallService.findById(id, sessionId);
    }
}
