package com.prestixpress.customers.domain.entity.factory;

import com.prestixpress.customers.domain.entity.Customer;
import com.prestixpress.customers.domain.vo.*;

import java.util.ArrayList;

public class CustomerFactory {

    public static Customer getCustomer(Cliente cliente, Contacto contacto, ArrayList<Fotos> fotos, ArrayList<Referencias> referencias, Ubicacion ubicacion, Id id, TipoCliente tipoCliente){
        return Customer.builder()
                .cliente(cliente)
                .contacto(contacto)
                .fotos(fotos)
                .referencias(referencias)
                .ubicacion(ubicacion)
                .id(id)
                .tipoCliente(tipoCliente)
                .build();
    }

}
