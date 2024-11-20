package org.esteban.lescano.dmhouse.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="user")
public class Users {

@Id
@Column(name="User_id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int userId;
private String userName;
private String password;
private String email;
@Column(name = "date_login")
private Date fechaLogin;
@OneToOne
@JoinColumn(name = "person_id", referencedColumnName = "person_id")
private Person person;

@Override
public final boolean equals(Object o) {
	if (this == o) return true;
	if (o == null) return false;
	Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
	Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
	if (thisEffectiveClass != oEffectiveClass) return false;
	Users users = (Users) o;
	return getUserId() != 0 && Objects.equals(getUserId(), users.getUserId());
}

@Override
public final int hashCode() {
	return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
}
}
