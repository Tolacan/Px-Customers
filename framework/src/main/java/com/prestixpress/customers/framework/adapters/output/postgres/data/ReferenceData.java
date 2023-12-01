package com.prestixpress.customers.framework.adapters.output.postgres.data;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "ReferenceData")
@Table(name = "referencess")
public class ReferenceData implements java.io.Serializable{

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "reference_id_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "reference_id_seq",sequenceName = "reference_id_seq",allocationSize = 1)
    private int id;

    @Basic(optional = false)
    @Column(name = "customer_uuid", nullable = false)
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
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Basic(optional = false)
    @Column(name = "parentesco", nullable = false)
    private String parentesco;

}
