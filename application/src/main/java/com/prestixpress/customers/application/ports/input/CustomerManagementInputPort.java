package com.prestixpress.customers.application.ports.input;

import com.prestixpress.customers.application.ports.output.CustomerManagementOutputPort;
import com.prestixpress.customers.application.usecases.CustomerManagementUseCase;
import com.prestixpress.customers.domain.entity.Customer;
import com.prestixpress.customers.domain.entity.factory.CustomerFactory;
import com.prestixpress.customers.domain.vo.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerManagementInputPort implements CustomerManagementUseCase {

    @Inject
    CustomerManagementOutputPort customerManagementOutputPort;

    @Override
    public Customer createCustomer(Cliente cliente, Contacto contacto, ArrayList<Fotos> fotos, ArrayList<Referencias> referencias, Ubicacion ubicacion, Id id, TipoCliente tipoCliente) {
        return CustomerFactory.getCustomer(
                cliente,
                contacto,
                fotos,
                referencias,
                ubicacion,
                id,
                tipoCliente
        );
    }

    @Override
    public Customer persistCustomer(Customer customer) {
        return customerManagementOutputPort.persistCustomer(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerManagementOutputPort.updateCustomer(customer);
    }

    @Override
    public List<Customer> retrieveCustomers(String FilterType, String FilterValue) {
        return customerManagementOutputPort.retrieveCustomers(FilterType, FilterValue);
    }
}
