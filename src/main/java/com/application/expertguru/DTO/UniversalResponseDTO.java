package com.application.expertguru.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UniversalResponseDTO<T> {
        private ResponseCodeJson responseCodeJson;
        private List list;
        private T object;

}
