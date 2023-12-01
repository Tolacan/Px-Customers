package com.prestixpress.customers.domain.entity;

import com.prestixpress.customers.domain.vo.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;

@Getter
@ToString
@EqualsAndHashCode
public class Customer {
    private  Cliente cliente;
    private Contacto contacto;
    private ArrayList<Fotos> fotos;
    private ArrayList<Referencias> referencias;
    private Ubicacion ubicacion;
    private Id id;
    private TipoCliente tipoCliente;

    @Builder
    private Customer(Cliente cliente, Contacto contacto, ArrayList<Fotos> fotos, ArrayList<Referencias> referencias, Ubicacion ubicacion, Id id, TipoCliente tipoCliente) {
        this.cliente = cliente;
        this.contacto = contacto;
        this.fotos = fotos;
        this.referencias = referencias;
        this.ubicacion = ubicacion;
        this.id = id;
        this.tipoCliente = tipoCliente;
    }

    public static Predicate<Customer> getCustomerActivated() {
        return customer -> customer.getCliente().isActivo();
    }
}
