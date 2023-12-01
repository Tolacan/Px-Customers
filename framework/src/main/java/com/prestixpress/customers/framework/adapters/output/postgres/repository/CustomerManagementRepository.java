package com.prestixpress.customers.framework.adapters.output.postgres.repository;

import com.prestixpress.customers.framework.adapters.output.postgres.data.CustomerData;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerManagementRepository implements PanacheRepositoryBase<CustomerData,Long>{
}
