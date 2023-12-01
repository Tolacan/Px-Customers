package com.prestixpress.customers.application.usecases;

import com.prestixpress.customers.domain.entity.Customer;
import com.prestixpress.customers.domain.vo.*;

import java.util.ArrayList;
import java.util.List;

public interface CustomerManagementUseCase {

    Customer createCustomer(
            Cliente cliente,
            Contacto contacto,
            ArrayList<Fotos> fotos,
            ArrayList<Referencias> referencias,
            Ubicacion ubicacion,
            Id id,
            TipoCliente tipoCliente
    );

    Customer persistCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    List<Customer> retrieveCustomers(String FilterType, String FilterValue);

}
