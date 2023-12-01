module application {

    exports  com.prestixpress.customers.application.usecases;
    exports  com.prestixpress.customers.application.ports.output;
    exports  com.prestixpress.customers.application.ports.input;

    requires jakarta.enterprise.cdi.api;
    requires jakarta.inject.api;
    requires arc;
    requires domain;
    requires static lombok;

    provides com.prestixpress.customers.application.usecases.CustomerManagementUseCase
            with com.prestixpress.customers.application.ports.input.CustomerManagementInputPort;
}