package com.prestixpress.customers.application.ports.output;

import com.prestixpress.customers.domain.entity.Customer;

import java.util.List;

public interface CustomerManagementOutputPort {

    Customer persistCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    List<Customer> retrieveCustomers(String FilterType, String FilterValue);

}
