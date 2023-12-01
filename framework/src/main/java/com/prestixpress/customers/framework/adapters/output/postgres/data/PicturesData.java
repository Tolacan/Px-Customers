package com.prestixpress.customers.framework.adapters.output.postgres.data;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "PicturesData")
@Table(name = "pictures")
public class PicturesData implements java.io.Serializable{

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "picture_id_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "picture_id_seq",sequenceName = "picture_id_seq",allocationSize = 1)
    private int id;

    @Basic(optional = false)
    @Column(name = "customer_uuid", nullable = false)
    private String uuid;

    @Basic(optional = false)
    @Column(name = "foto", nullable = false)
    private String foto;

    @Basic(optional = false)
    @Column(name = "fecha_registro", nullable = false)
    private java.util.Date fechaRegistro;

    @Basic(optional = false)
    @Column(name = "eliminado", nullable = false)
    private boolean eliminado;
}
