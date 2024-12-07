package org.esteban.lescano.dmhouse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Integer personId;
    private String name;
    private Integer countryId;
    private Integer typeDocumenId;
    private String documen;
    private Date birthdate;

}