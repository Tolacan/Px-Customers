package com.prestixpress.customers.framework.adapters.output.postgres.mappers;

import com.prestixpress.customers.domain.entity.Customer;
import com.prestixpress.customers.domain.entity.factory.CustomerFactory;
import com.prestixpress.customers.domain.vo.*;
import com.prestixpress.customers.framework.adapters.output.postgres.data.CustomerData;
import com.prestixpress.customers.framework.adapters.output.postgres.data.PicturesData;
import com.prestixpress.customers.framework.adapters.output.postgres.data.ReferenceData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class CustomerMapper {

    public static Customer customerToDomain(CustomerData customerData, ArrayList<PicturesData> picturesData, ArrayList<ReferenceData> referenceData){

        Cliente cliente = Cliente.builder()
                .nombre(customerData.getNombre())
                .apellidoPaterno(customerData.getApellidoPaterno())
                .apellidoMaterno(customerData.getApellidoMaterno())
                .fechaNacimiento(customerData.getFechaNacimiento())
                .rfc(customerData.getRfc())
                .ine(customerData.getIne())
                .fechaRegistro(customerData.getFechaRegistro())
                .fechaModificacion(customerData.getFechaModificacion())
                .build();

        Contacto contacto = Contacto.builder()
                .telefono1(customerData.getTelefono1())
                .telefono2(customerData.getTelefono2())
                .correo(customerData.getCorreo())
                .build();

        Ubicacion ubicacion = Ubicacion.builder()
                .calle(customerData.getCalle())
                .numeroInterior(customerData.getNumeroInterior())
                .numeroExterior(customerData.getNumeroExterior())
                .colonia(customerData.getColonia())
                .municipio(customerData.getMunicipio())
                .estado(customerData.getEstado())
                .pais(customerData.getPais())
                .codigoPostal(customerData.getCodigoPostal())
                .latitud(customerData.getLatitud())
                .longitud(customerData.getLongitud())
                .referencias(customerData.getReferencias())
                .build();

        ArrayList<Fotos> fotos = new ArrayList<>();
        for (PicturesData picturesDatum : picturesData) {
            Fotos foto = Fotos.builder()
                    .foto(picturesDatum.getFoto())
                    .fechaRegistro(picturesDatum.getFechaRegistro())
                    .eliminado(picturesDatum.isEliminado())
                    .build();
            fotos.add(foto);
        }

       ArrayList<Referencias> referencias = new ArrayList<>();
        for(ReferenceData referenceDatum : referenceData){
            Referencias referencia = Referencias.builder()
                    .nombre(referenceDatum.getNombre())
                    .apellidoPaterno(referenceDatum.getApellidoPaterno())
                    .apellidoMaterno(referenceDatum.getApellidoMaterno())
                    .telefono(referenceDatum.getTelefono())
                    .parentesco(referenceDatum.getParentesco())
                    .build();
            referencias.add(referencia);
        }




        var customer = CustomerFactory.getCustomer(
                cliente,
                contacto,
                fotos,
                referencias,
                ubicacion,
                Id.conId(customerData.getUuid()),
                TipoCliente.valueOf(customerData.getCategoria())
        );



        return customer;
    }

    public static CustomerData customerDomainToData(Customer customer) {
        var customerData = CustomerData.builder()
                .uuid(customer.getId().getId().toString())
                .nombre(customer.getCliente().getNombre())
                .apellidoPaterno(customer.getCliente().getApellidoPaterno())
                .apellidoMaterno(customer.getCliente().getApellidoMaterno())
                .rfc(customer.getCliente().getRfc())
                .ine(customer.getCliente().getIne())
                .fechaNacimiento(customer.getCliente().getFechaNacimiento())
                .fechaRegistro(customer.getCliente().getFechaRegistro())
                .fechaModificacion(customer.getCliente().getFechaModificacion())
                .telefono1(customer.getContacto().getTelefono1())
                .telefono2(customer.getContacto().getTelefono2())
                .correo(customer.getContacto().getCorreo())
                .calle(customer.getUbicacion().getCalle())
                .numeroInterior(customer.getUbicacion().getNumeroInterior())
                .numeroExterior(customer.getUbicacion().getNumeroExterior())
                .colonia(customer.getUbicacion().getColonia())
                .municipio(customer.getUbicacion().getMunicipio())
                .estado(customer.getUbicacion().getEstado())
                .pais(customer.getUbicacion().getPais())
                .codigoPostal(customer.getUbicacion().getCodigoPostal())
                .latitud(customer.getUbicacion().getLatitud())
                .longitud(customer.getUbicacion().getLongitud())
                .referencias(customer.getUbicacion().getReferencias())
                .categoria(customer.getTipoCliente().toString())
                .activo(customer.getCliente().isActivo())
                .build();

        return customerData;
    }

    public static PicturesData picturesDomainToData(Fotos foto, String uuid){
        var picturesData = PicturesData.builder()
                .uuid(uuid)
                .foto(foto.getFoto())
                .fechaRegistro(foto.getFechaRegistro())
                .eliminado(foto.isEliminado())
                .build();

        return picturesData;
    }

    public static ReferenceData referenceDomainToData(Referencias referencia, String uuid){
        var referenceData = ReferenceData.builder()
                .uuid(uuid)
                .nombre(referencia.getNombre())
                .apellidoPaterno(referencia.getApellidoPaterno())
                .apellidoMaterno(referencia.getApellidoMaterno())
                .telefono(referencia.getTelefono())
                .parentesco(referencia.getParentesco())
                .build();

        return referenceData;
    }

}
