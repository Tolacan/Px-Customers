package com.prestixpress.customers.application.ports.output;

import com.prestixpress.customers.domain.entity.Customer;

import java.util.List;

public interface CustomerManagementOutputPort {

    Customer persistCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    <T> List<T> retrieveCustomers(String FilterType, String FilterValue);

    <T> List<T>  retrieveReferences(String uuid);

    <T> List<T>  retrievePhotos(String uuid);

}
