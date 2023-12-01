package com.prestixpress.customers.framework.adapters.output.postgres;

import com.prestixpress.customers.application.ports.output.CustomerManagementOutputPort;
import com.prestixpress.customers.domain.entity.Customer;
import com.prestixpress.customers.framework.adapters.input.rest.CustomerManagementAdapter;
import com.prestixpress.customers.framework.adapters.output.postgres.mappers.CustomerMapper;
import com.prestixpress.customers.framework.adapters.output.postgres.repository.CustomerManagementRepository;
import com.prestixpress.customers.framework.adapters.output.postgres.repository.PictureManagementRepository;
import com.prestixpress.customers.framework.adapters.output.postgres.repository.ReferenceManagementRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


@ApplicationScoped
public class CustomerManagementPostgresAdapter implements CustomerManagementOutputPort {

    @Inject
    CustomerManagementRepository customerManagementRepository;
    @Inject
    PictureManagementRepository pictureManagementRepository;
    @Inject
    ReferenceManagementRepository referenceManagementRepository;

    private static final Logger LOG = Logger.getLogger(CustomerManagementPostgresAdapter.class.toString());

    @Override
    public Customer persistCustomer(Customer customer) {
          try{
              LOG.info("Customer in output: "+customer.getCliente().getNombre());
              var customerForSave = CustomerMapper.customerDomainToData(customer);
              customerManagementRepository.persist(customerForSave);

              if(customer.getFotos().size() > 0) {
                  //TODO: Save pictures
                  customer.getFotos().forEach(picture -> {
                      var pictureForSave = CustomerMapper.picturesDomainToData(picture, customerForSave.getUuid());
                      pictureManagementRepository.persist(pictureForSave);
                  });
              }
              if(customer.getReferencias().size() > 0)
              {
                  customer.getReferencias().forEach(reference -> {
                      var referenceForSave = CustomerMapper.referenceDomainToData(reference, customerForSave.getUuid());
                      referenceManagementRepository.persist(referenceForSave);
                  });
              }
              return customer;
        }
        catch (Exception e){
            LOG.info("Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
       try{
              var customerForUpdate = customerManagementRepository.find("uuid",customer.getId()).firstResult();
                customerForUpdate.setNombre(customer.getCliente().getNombre());
                customerForUpdate.setApellidoPaterno(customer.getCliente().getApellidoPaterno());
                customerForUpdate.setApellidoMaterno(customer.getCliente().getApellidoMaterno());
                customerForUpdate.setRfc(customer.getCliente().getRfc());
                customerForUpdate.setIne(customer.getCliente().getIne());
                customerForUpdate.setFechaNacimiento(customer.getCliente().getFechaNacimiento());
                customerForUpdate.setFechaRegistro(customer.getCliente().getFechaRegistro());
                customerForUpdate.setFechaModificacion(customer.getCliente().getFechaModificacion());
                customerForUpdate.setTelefono1(customer.getContacto().getTelefono1());
                customerForUpdate.setTelefono2(customer.getContacto().getTelefono2());
                customerForUpdate.setCorreo(customer.getContacto().getCorreo());
                customerForUpdate.setCalle(customer.getUbicacion().getCalle());
                customerForUpdate.setNumeroInterior(customer.getUbicacion().getNumeroInterior());
                customerForUpdate.setNumeroExterior(customer.getUbicacion().getNumeroExterior());
                customerForUpdate.setColonia(customer.getUbicacion().getColonia());
                customerForUpdate.setMunicipio(customer.getUbicacion().getMunicipio());
                customerForUpdate.setEstado(customer.getUbicacion().getEstado());
                customerForUpdate.setPais(customer.getUbicacion().getPais());
                customerForUpdate.setCodigoPostal(customer.getUbicacion().getCodigoPostal());
                customerForUpdate.setLatitud(customer.getUbicacion().getLatitud());
                customerForUpdate.setLongitud(customer.getUbicacion().getLongitud());
                customerForUpdate.setReferencias(customer.getUbicacion().getReferencias());
                customerForUpdate.setActivo(customer.getCliente().isActivo());

                var referenceForUpdate = referenceManagementRepository.find("customer_uuid",customer.getId()).list();
                referenceForUpdate.forEach(referenceData -> {
                    referenceManagementRepository.delete(referenceData);
                });

                customer.getReferencias().forEach(reference -> {
                    var referenceForSave = CustomerMapper.referenceDomainToData(reference, customer.getId().toString());
                    referenceManagementRepository.persist(referenceForSave);
                });

                //TODO: Delete pictures
                var pictureForUpdate = pictureManagementRepository.find("customer_uuid",customer.getId()).list();
                pictureForUpdate.forEach(pictureData -> {
                    pictureManagementRepository.delete(pictureData);
                });

                //TODO: Save pictures
                customer.getFotos().forEach(picture -> {
                    var pictureForSave = CustomerMapper.picturesDomainToData(picture, customer.getId().toString());
                    pictureManagementRepository.persist(pictureForSave);
                });

              return customer;
         }
         catch (Exception e){
              return null;
       }
    }

    @Override
    public List<Customer> retrieveCustomers(String FilterType, String FilterValue) {
        return null;
    }
}
