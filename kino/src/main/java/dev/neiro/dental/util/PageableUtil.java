package dev.neiro.dental.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * @author Wayne Stark
 * @since 30.10.2022
 */
public class PageableUtil {

    public static Pageable generatePageable(Integer pageNum, Integer pageSize, String sortField) {
        if (pageNum != null && pageNum > -1 && pageSize != null && pageSize > 0) {
            return PageRequest.of(pageNum, pageSize, ASC, sortField);
        }
        return null;
    }
}
