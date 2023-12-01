package com.prestixpress.customers.framework.adapters.input.rest.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.prestixpress.customers.domain.vo.*;
import com.prestixpress.customers.framework.adapters.input.rest.deserializer.CustomerDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CreateCustomer {
    @JsonProperty
    private Id id;
    @JsonProperty
    private Cliente cliente;
    @JsonProperty
    private Contacto contacto;
    @JsonProperty
    private ArrayList<Fotos> fotos;
    @JsonProperty
    private ArrayList<Referencias> referencias;
    @JsonProperty
    private Ubicacion ubicacion;
    @JsonProperty
    private TipoCliente tipoCliente;
}
