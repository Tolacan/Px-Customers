package com.prestixpress.customers.framework.adapters.input.rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.prestixpress.customers.domain.entity.Customer;
import com.prestixpress.customers.domain.entity.factory.CustomerFactory;
import com.prestixpress.customers.domain.vo.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

public class CustomerDeserializer extends StdDeserializer<Customer>{

    public CustomerDeserializer(){
        this(null);
    }

    public CustomerDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Customer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try{
            JsonNode node = p.getCodec().readTree(p);

            var id = node.get("cliente").get("uuid").asText().toLowerCase();

            var cliente = Cliente.builder()
                    .nombre(node.get("cliente").get("nombre").asText().toLowerCase())
                    .apellidoPaterno(node.get("cliente").get("apellidoPaterno").asText().toLowerCase())
                    .apellidoMaterno(node.get("cliente").get("apellidoMaterno").asText().toLowerCase())
                    .rfc(node.get("cliente").get("rfc").asText().toLowerCase())
                    .ine(node.get("cliente").get("ine").asText().toLowerCase())
                    .fechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(node.get("cliente").get("fechaNacimiento").asText().toLowerCase()))
                    .fechaRegistro(new SimpleDateFormat("yyyy-MM-dd").parse(node.get("cliente").get("fechaRegistro").asText().toLowerCase()))
                    .fechaModificacion(new SimpleDateFormat("yyyy-MM-dd").parse(node.get("cliente").get("fechaModificacion").asText().toLowerCase()))
                    .activo(node.get("cliente").get("activo").asBoolean())
                    .build();

            var contacto = Contacto.builder()
                    .telefono1(node.get("contacto").get("telefono1").asText().toLowerCase())
                    .telefono2(node.get("contacto").get("telefono2").asText().toLowerCase())
                    .correo(node.get("contacto").get("correo").asText().toLowerCase())
                    .build();

            var fotos = new ArrayList<Fotos>();
            for (JsonNode foto : node.get("fotos")) {
                fotos.add(Fotos.builder()
                        .foto(foto.get("foto").asText().toLowerCase())
                        .fechaRegistro(new SimpleDateFormat("yyyy-MM-dd").parse(foto.get("fechaRegistro").asText().toLowerCase()))
                        .eliminado(foto.get("eliminado").asBoolean())
                        .build());
            }

            var referencias = new ArrayList<Referencias>();
            for (JsonNode referencia : node.get("referencias")) {
                referencias.add(Referencias.builder()
                        .nombre(referencia.get("nombre").asText().toLowerCase())
                        .apellidoPaterno(referencia.get("apellidoPaterno").asText().toLowerCase())
                        .apellidoMaterno(referencia.get("apellidoMaterno").asText().toLowerCase())
                        .parentesco(referencia.get("parentesco").asText().toLowerCase())
                        .telefono(referencia.get("telefono").asText().toLowerCase())
                        .build());
            }

            var ubicacion = Ubicacion.builder()
                    .calle(node.get("ubicacion").get("calle").asText().toLowerCase())
                    .numeroInterior(node.get("ubicacion").get("numeroInterior").asText().toLowerCase())
                    .numeroExterior(node.get("ubicacion").get("numeroExterior").asText().toLowerCase())
                    .colonia(node.get("ubicacion").get("colonia").asText().toLowerCase())
                    .codigoPostal(node.get("ubicacion").get("codigoPostal").asText().toLowerCase())
                    .referencias(node.get("ubicacion").get("referencias").asText().toLowerCase())
                    .municipio(node.get("ubicacion").get("municipio").asText().toLowerCase())
                    .estado(node.get("ubicacion").get("estado").asText().toLowerCase())
                    .pais(node.get("ubicacion").get("pais").asText().toLowerCase())
                    .latitud(node.get("ubicacion").get("latitud").asText().toLowerCase())
                    .longitud(node.get("ubicacion").get("longitud").asText().toLowerCase())
                    .build();

            var tipoCliente = TipoCliente.valueOf(node.get("tipoCliente").asText().toLowerCase());


            var customer = CustomerFactory.getCustomer(
                    cliente,
                    contacto,
                    fotos,
                    referencias,
                    ubicacion,
                    id == null ? Id.sinId() : Id.conId(id),
                    tipoCliente
            );

            return customer;
        }catch (Exception e){
            throw new IOException(e);
        }
    }

}
