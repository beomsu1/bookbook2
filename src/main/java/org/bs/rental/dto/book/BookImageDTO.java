package org.bs.rental.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookImageDTO {

    private int imgno;
    private String uuid;
    private String fname;
    private boolean img;

}
