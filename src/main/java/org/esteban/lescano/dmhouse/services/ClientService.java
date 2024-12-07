package org.esteban.lescano.dmhouse.services;

import org.esteban.lescano.dmhouse.entities.Client;
import org.esteban.lescano.dmhouse.entities.Person;
import org.esteban.lescano.dmhouse.entities.Wallet;
import org.esteban.lescano.dmhouse.mensajeria.EmailService;
import org.esteban.lescano.dmhouse.models.ClientDTO;
import org.esteban.lescano.dmhouse.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private final ClientRepository usersRepository;
	private final PersonService personService;
	private final WalletService walletService;
	private final EmailService emailService;

	public ClientService(ClientRepository usersRepository, PersonService personService, WalletService walletService,
	                     EmailService emailService) {
		this.usersRepository = usersRepository;
		this.personService = personService;
		this.walletService = walletService;
		this.emailService = emailService;
	}


	public boolean registerUser(ClientDTO dto) {
		try {
			Person person = personService.createPerson(dto);
			Client client = new Client();
			client.setPerson(person);
			usersRepository.save(client);

			Wallet wallet = walletService.createWallet(client);
			if (wallet == null) {
				throw new RuntimeException("Failed to create wallet for the user.");
			}

			emailService.sendWelcomeEmail(client.getPerson().getEmail(), client.getPerson().getName());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
