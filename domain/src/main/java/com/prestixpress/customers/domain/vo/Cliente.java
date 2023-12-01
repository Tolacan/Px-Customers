package com.prestixpress.customers.domain.vo;

import java.util.Date;

import lombok.*;

@Builder
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Cliente {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rfc;
    private String ine;
    private Date fechaNacimiento;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private boolean activo;
}
