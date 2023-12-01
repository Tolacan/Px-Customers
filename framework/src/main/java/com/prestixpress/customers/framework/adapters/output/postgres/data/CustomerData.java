package com.prestixpress.customers.framework.adapters.output.postgres.data;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "CustomerData")
@Table(name = "customers")
public class CustomerData implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "customer_id_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_id_seq",sequenceName = "customer_id_seq",allocationSize = 1)
    private int id;

    @Basic(optional = false)
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Basic(optional = false)
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Basic(optional = false)
    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Basic(optional = false)
    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    @Basic(optional = false)
    @Column(name = "rfc", nullable = false)
    private String rfc;

    @Basic(optional = false)
    @Column(name = "ine", nullable = false)
    private String ine;

    @Basic(optional = false)
    @Column(name = "fecha_nacimiento", nullable = false)
    private java.util.Date fechaNacimiento;

    @Basic(optional = false)
    @Column(name = "fecha_registro", nullable = false)
    private java.util.Date fechaRegistro;

    @Basic(optional = false)
    @Column(name = "fecha_modificacion", nullable = false)
    private java.util.Date fechaModificacion;

    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Basic(optional = false)
    @Column(name = "telefono1", nullable = false)
    private String telefono1;

    @Basic(optional = false)
    @Column(name = "telefono2", nullable = false)
    private String telefono2;

    @Basic(optional = false)
    @Column(name = "correo", nullable = false)
    private String correo;

    @Basic(optional = false)
    @Column(name = "calle", nullable = false)
    private String calle;

    @Basic(optional = false)
    @Column(name = "numero_exterior", nullable = false)
    private String numeroExterior;

    @Basic(optional = false)
    @Column(name = "numero_interior", nullable = false)
    private String numeroInterior;

    @Basic(optional = false)
    @Column(name = "colonia", nullable = false)
    private String colonia;

    @Basic(optional = false)
    @Column(name = "codigo_postal", nullable = false)
    private String codigoPostal;

    @Basic(optional = false)
    @Column(name = "referencias", nullable = false)
    private String referencias;

    @Basic(optional = false)
    @Column (name = "municipio", nullable = false)
    private String municipio;

    @Basic(optional = false)
    @Column (name = "estado", nullable = false)
    private String estado;

    @Basic(optional = false)
    @Column (name = "pais", nullable = false)
    private String pais;

    @Basic(optional = false)
    @Column (name = "latitud", nullable = false)
    private String latitud;

    @Basic(optional = false)
    @Column (name = "longitud", nullable = false)
    private String longitud;

    @Basic(optional = false)
    @Column (name = "categoria", nullable = false)
    private String categoria;

}
