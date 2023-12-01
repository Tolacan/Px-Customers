package com.prestixpress.customers.domain.vo;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Fotos {
    private String foto;
    private Date fechaRegistro;
    private boolean eliminado;
}
