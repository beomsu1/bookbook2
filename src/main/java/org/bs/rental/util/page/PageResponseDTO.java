package org.bs.rental.util.page;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageResponseDTO<E> {

    private List<E> list;
    private int total;

    public PageResponseDTO(List<E> list, int total) {
        this.list = list;
        this.total = total;
        
    }
    
}
