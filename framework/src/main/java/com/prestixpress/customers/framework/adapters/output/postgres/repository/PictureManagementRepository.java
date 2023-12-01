package com.prestixpress.customers.framework.adapters.output.postgres.repository;


import com.prestixpress.customers.framework.adapters.output.postgres.data.PicturesData;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PictureManagementRepository implements PanacheRepositoryBase<PicturesData,Long> {
}
