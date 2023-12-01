package com.prestixpress.customers.domain.vo;


import lombok.*;

@Builder
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Referencias {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String parentesco;
}
