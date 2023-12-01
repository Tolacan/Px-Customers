module framework {

    requires domain;
    requires application;
    requires static lombok;

    requires java.sql;
    requires java.persistence;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires jakarta.enterprise.cdi.api;
    requires jakarta.inject.api;
    requires java.ws.rs;
    requires io.smallrye.mutiny;
    requires java.xml.bind;
    requires smallrye.common.annotation;
    requires com.fasterxml.jackson.annotation;
    requires quarkus.hibernate.orm.panache;
    requires java.transaction;
    requires io.vertx.core;
    requires microprofile.context.propagation.api;
    requires microprofile.openapi.api;
    requires microprofile.rest.client.api;
    requires org.jboss.logging;
    requires jbcrypt;

    exports com.prestixpress.customers.framework.adapters.output.postgres.data;
    opens com.prestixpress.customers.framework.adapters.output.postgres.data;
    exports com.prestixpress.customers.framework.adapters.output.postgres.repository;
    opens com.prestixpress.customers.framework.adapters.output.postgres.repository;

    provides com.prestixpress.customers.application.ports.output.CustomerManagementOutputPort with com.prestixpress.customers.framework.adapters.output.postgres.CustomerManagementPostgresAdapter;

    uses  com.prestixpress.customers.application.usecases.CustomerManagementUseCase;
    uses  com.prestixpress.customers.application.ports.output.CustomerManagementOutputPort;

}