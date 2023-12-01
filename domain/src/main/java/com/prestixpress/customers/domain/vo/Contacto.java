package com.prestixpress.customers.domain.vo;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Contacto {
    private String telefono1;
    private String telefono2;
    private String correo;
}
