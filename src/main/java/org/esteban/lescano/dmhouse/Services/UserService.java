package org.esteban.lescano.dmhouse.Services;

import org.esteban.lescano.dmhouse.Repository.UsersRepository;
import org.esteban.lescano.dmhouse.Repository.WalletRepository;
import org.esteban.lescano.dmhouse.entities.Account;
import org.esteban.lescano.dmhouse.entities.Person;
import org.esteban.lescano.dmhouse.entities.Users;
import org.esteban.lescano.dmhouse.entities.Wallet;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class UserService {

private final UsersRepository usersRepository;
private final PersonService personService;
private final WalletService walletService;
private final WalletRepository walletRepository;

public UserService(UsersRepository usersRepository, PersonService personService, WalletService walletService, WalletRepository walletRepository) {
	this.usersRepository = usersRepository;
	this.personService = personService;
	this.walletService = walletService;
	this.walletRepository = walletRepository;
}

public Users createUsers(String name, int country, int DocumentType, String document, Date birthdate,
                         String email, String password) {
	/*
	 * 1.Metodo: Crear Usuario 1.1-->Crear una Persona(setearle un usuario)
	 * 1.2-->crear un usuario 1.3-->Crear una billetera(setearle una persona)
	 * 1.4-->Crear una cuenta en pesos y otra en dolares
	 */
	
	Person person = new Person();
	person.setName(name);
	person.setCountryId(country);
	person.setTypeDocumenId(DocumentType);
	person.setDocumen(document);
	person.setBirthdate(birthdate);
	
	Users users = new Users();
	users.setUserName(email);
	users.setPassword(Crypto.encrypt(password, email));
	person.setUsers(users);
	personService.savePerson(person);
	
	Wallet wallet = new Wallet();
	
	Account pesos = new Account();
	pesos.setBalance(new BigDecimal(0));
	pesos.setMoney("ARS");
	wallet.addAccount(pesos);
	
}
}
