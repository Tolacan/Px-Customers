package com.prestixpress.customers.domain.vo;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Ubicacion {
    private String calle;
    private String numeroInterior;
    private String numeroExterior;
    private String colonia;
    private String codigoPostal;
    private String referencias;
    private String municipio;
    private String estado;
    private String pais;
    private String latitud;
    private String longitud;
}
