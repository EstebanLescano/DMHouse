package org.esteban.lescano.dmhouse.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet {

@Id
@Column(name = "wallet_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer billeteraId;
@OneToOne
@JoinColumn(name = "person_id",referencedColumnName = "person_id")
private Person person;
@OneToMany(mappedBy = "billetera",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
private List<Acount> acount = new ArrayList<>();

}