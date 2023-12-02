package com.prestixpress.customers.framework.adapters.output.postgres;

import com.prestixpress.customers.application.ports.output.CustomerManagementOutputPort;
import com.prestixpress.customers.domain.entity.Customer;
import com.prestixpress.customers.framework.adapters.input.rest.CustomerManagementAdapter;
import com.prestixpress.customers.framework.adapters.output.postgres.data.CustomerData;
import com.prestixpress.customers.framework.adapters.output.postgres.data.PicturesData;
import com.prestixpress.customers.framework.adapters.output.postgres.data.ReferenceData;
import com.prestixpress.customers.framework.adapters.output.postgres.mappers.CustomerMapper;
import com.prestixpress.customers.framework.adapters.output.postgres.repository.CustomerManagementRepository;
import com.prestixpress.customers.framework.adapters.output.postgres.repository.PictureManagementRepository;
import com.prestixpress.customers.framework.adapters.output.postgres.repository.ReferenceManagementRepository;
import io.quarkus.panache.common.Sort;

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
                LOG.info("Customer in output: "+ customer.getId().getId());
              var customerForUpdate = customerManagementRepository.find("uuid",customer.getId().getId().toString()).firstResult();

                customerForUpdate.setNombre(customer.getCliente().getNombre());
                customerForUpdate.setApellidoPaterno(customer.getCliente().getApellidoPaterno());
                customerForUpdate.setApellidoMaterno(customer.getCliente().getApellidoMaterno());
                customerForUpdate.setRfc(customer.getCliente().getRfc());
                customerForUpdate.setIne(customer.getCliente().getIne());
                customerForUpdate.setFechaNacimiento(customer.getCliente().getFechaNacimiento());
                customerForUpdate.setFechaModificacion(customer.getCliente().getFechaModificacion());
                customerForUpdate.setTelefono1(customer.getContacto().getTelefono1());
                customerForUpdate.setTelefono2(customer.getContacto().getTelefono2());
                customerForUpdate.setCorreo(customer.getContacto().getCorreo());
                customerForUpdate.setCalle(customer.getUbicacion().getCalle());
                customerForUpdate.setNumeroInterior(customer.getUbicacion().getNumeroInterior());
                customerForUpdate.setNumeroExterior(customer.getUbicacion().getNumeroExterior());
                customerForUpdate.setColonia(customer.getUbicacion().getColonia());
                customerForUpdate.setMunicipio(customer.getUbicacion().getMunicipio());
                customerForUpdate.setMunicipio(customer.getUbicacion().getMunicipio());
                customerForUpdate.setEstado(customer.getUbicacion().getEstado());
                customerForUpdate.setPais(customer.getUbicacion().getPais());
                customerForUpdate.setCodigoPostal(customer.getUbicacion().getCodigoPostal());
                customerForUpdate.setLatitud(customer.getUbicacion().getLatitud());
                customerForUpdate.setLongitud(customer.getUbicacion().getLongitud());
                customerForUpdate.setReferencias(customer.getUbicacion().getReferencias());
                customerForUpdate.setActivo(customer.getCliente().isActivo());

                var referenceForUpdate = referenceManagementRepository.find("customer_uuid",customer.getId().getId().toString()).list();
                referenceForUpdate.forEach(referenceData -> {
                    referenceManagementRepository.delete(referenceData);
                });

                customer.getReferencias().forEach(reference -> {
                    var referenceForSave = CustomerMapper.referenceDomainToData(reference, customer.getId().getId().toString());
                    referenceManagementRepository.persist(referenceForSave);
                });

                //TODO: Delete pictures
                var pictureForUpdate = pictureManagementRepository.find("customer_uuid",customer.getId().getId().toString()).list();
                pictureForUpdate.forEach(pictureData -> {
                    pictureManagementRepository.delete(pictureData);
                });

                //TODO: Save pictures
                customer.getFotos().forEach(picture -> {
                    var pictureForSave = CustomerMapper.picturesDomainToData(picture, customer.getId().getId().toString());
                    pictureManagementRepository.persist(pictureForSave);
                });

              return customer;
         }
         catch (Exception e){
             LOG.info("Error: "+ e.getMessage());
              return null;
       }
    }

    @Override
    public List<CustomerData> retrieveCustomers(String FilterType, String FilterValue) {

        try{
            List<CustomerData> findIt;

            switch (FilterType){
                case "uuid":
                    findIt = customerManagementRepository.find("uuid like '%" + FilterValue.toLowerCase() + "'", Sort.by("fecha_modificacion").descending()).list();

                    break;
              /*  case "descripcion":
                    findIt = salidasManagementRepository.find("UPPER(descripcion) like '%" + valor.toUpperCase() + "%'",Sort.by("modificado")).list();
                    break;
                case "folio":
                    findIt = salidasManagementRepository.find("folio like '%" + valor.toUpperCase()+ "'",Sort.by("modificado").descending()).list();
                    break;
                case "acabado" :
                    findIt = salidasManagementRepository.find("acabado = '" + valor.toUpperCase() + "'",Sort.by("modificado").descending()).list();
                    break;
                case "categoria" :
                    findIt = salidasManagementRepository.find("categoria = '" + valor.toUpperCase() + "'",Sort.by("modificado").descending()).list();
                    break;
                case "documento" :
                    findIt = salidasManagementRepository.find("UPPER(documento_salida) like '%" + valor.toUpperCase() + "%'",Sort.by("modificado").descending()).list();
                    break;
                case "rango" :
                    String inicio = valor.split("_")[0];
                    String fin = valor.split("_")[1];
                    findIt = salidasManagementRepository.find("creado >= '"+inicio+"' and creado <= '"+fin+"'",Sort.by("modificado").descending()).list();
                    break;*/
                case "todos" :
                    findIt = customerManagementRepository.findAll(Sort.by("fecha_modificacion").descending()).page(0,500).list();
                    break;
                default:
                    findIt = null;
            }

            if(findIt == null)
            {   LOG.info("No se encontro ");
                return null;
            }
            else
            {
                return findIt;
            }
        }
        catch (Exception e)
        {   LOG.info("Error" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<ReferenceData> retrieveReferences(String uuid) {
        List<ReferenceData> findIt = referenceManagementRepository.find("customer_uuid = '" + uuid+ "'").list();
        return findIt;
    }

    @Override
    public List<PicturesData> retrievePhotos(String uuid) {
        List<PicturesData> findIt = pictureManagementRepository.find("customer_uuid = '" + uuid+ "'").list();
        return findIt;
    }
}
