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

            var id = node.get("cliente").get("uuid").asText();

            var cliente = Cliente.builder()
                    .nombre(node.get("cliente").get("nombre").asText())
                    .apellidoPaterno(node.get("cliente").get("apellidoPaterno").asText())
                    .apellidoMaterno(node.get("cliente").get("apellidoMaterno").asText())
                    .rfc(node.get("cliente").get("rfc").asText())
                    .ine(node.get("cliente").get("ine").asText())
                    .fechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(node.get("cliente").get("fechaNacimiento").asText()))
                    .fechaRegistro(new SimpleDateFormat("yyyy-MM-dd").parse(node.get("cliente").get("fechaRegistro").asText()))
                    .fechaModificacion(new SimpleDateFormat("yyyy-MM-dd").parse(node.get("cliente").get("fechaModificacion").asText()))
                    .activo(node.get("cliente").get("activo").asBoolean())
                    .build();

            var contacto = Contacto.builder()
                    .telefono1(node.get("contacto").get("telefono1").asText())
                    .telefono2(node.get("contacto").get("telefono2").asText())
                    .correo(node.get("contacto").get("correo").asText())
                    .build();

            var fotos = new ArrayList<Fotos>();
            for (JsonNode foto : node.get("fotos")) {
                fotos.add(Fotos.builder()
                        .foto(foto.get("foto").asText())
                        .fechaRegistro(new SimpleDateFormat("yyyy-MM-dd").parse(foto.get("fechaRegistro").asText()))
                        .eliminado(foto.get("eliminado").asBoolean())
                        .build());
            }

            var referencias = new ArrayList<Referencias>();
            for (JsonNode referencia : node.get("referencias")) {
                referencias.add(Referencias.builder()
                        .nombre(referencia.get("nombre").asText())
                        .apellidoPaterno(referencia.get("apellidoPaterno").asText())
                        .apellidoMaterno(referencia.get("apellidoMaterno").asText())
                        .parentesco(referencia.get("parentesco").asText())
                        .telefono(referencia.get("telefono").asText())
                        .build());
            }

            var ubicacion = Ubicacion.builder()
                    .calle(node.get("ubicacion").get("calle").asText())
                    .numeroInterior(node.get("ubicacion").get("numeroInterior").asText())
                    .numeroExterior(node.get("ubicacion").get("numeroExterior").asText())
                    .colonia(node.get("ubicacion").get("colonia").asText())
                    .codigoPostal(node.get("ubicacion").get("codigoPostal").asText())
                    .referencias(node.get("ubicacion").get("referencias").asText())
                    .municipio(node.get("ubicacion").get("municipio").asText())
                    .estado(node.get("ubicacion").get("estado").asText())
                    .pais(node.get("ubicacion").get("pais").asText())
                    .latitud(node.get("ubicacion").get("latitud").asText())
                    .longitud(node.get("ubicacion").get("longitud").asText())
                    .build();

            var tipoCliente = TipoCliente.valueOf(node.get("tipoCliente").asText());


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
