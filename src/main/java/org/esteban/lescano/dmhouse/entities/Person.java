package org.esteban.lescano.dmhouse.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "person")
public class Person {

@Id
@Column(name = "person_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer personId;
private String nombre;
@Column(name = "country_id")
private Integer countryId;
@Column(name = "type_documen_id")
private Integer typeDocumenId;
private String documen;
@Column(name = "fecha_nacimiento")
private Date birthdate;
@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
private Users users;
@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
private Wallet wallet;
}
