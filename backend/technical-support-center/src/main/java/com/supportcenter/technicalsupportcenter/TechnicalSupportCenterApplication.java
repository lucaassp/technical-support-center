package com.supportcenter.technicalsupportcenter;

import com.supportcenter.technicalsupportcenter.domains.Called;
import com.supportcenter.technicalsupportcenter.domains.Client;
import com.supportcenter.technicalsupportcenter.domains.Technician;
import com.supportcenter.technicalsupportcenter.domains.enums.Priority;
import com.supportcenter.technicalsupportcenter.domains.enums.ProfileStatus;
import com.supportcenter.technicalsupportcenter.domains.enums.Status;
import com.supportcenter.technicalsupportcenter.repositories.CalledRepository;
import com.supportcenter.technicalsupportcenter.repositories.ClientRepository;
import com.supportcenter.technicalsupportcenter.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class TechnicalSupportCenterApplication implements CommandLineRunner {

	@Autowired
	private TechnicianRepository technicianRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CalledRepository calledRepository;

	public static void main(String[] args) {
		SpringApplication.run(TechnicalSupportCenterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Criando técnicos
		Technician t1 = new Technician(null, "José Santos", "45678912309", "jose.santos@teste.com", "s#$gfdhg");
		t1.addProfile(ProfileStatus.ADMIN);
		Technician t2 = new Technician(null, "Lucas Ferreira", "45675896042", "lucas.ferreira@teste.com", "DEEFRrfgr#");
		Technician t3 = new Technician(null, "Carlos Andre", "45283849950", "carlos.andre@teste.com", "D$R%FR");
		Technician t4 = new Technician(null, "Kaio Silva", "550.482.150-95", "kaio@mail.com", "ksldjsl&%$dj");
		Technician t5 = new Technician(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", "ksldj345#sldj");
		Technician t6 = new Technician(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", "ksldj85858sldj");
		Technician t7 = new Technician(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", "ksldjsldj");
		Technician t8 = new Technician(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", "ksldjfgfgfsldj");

		// Criando clientes
		Client c1 = new Client(null, "Pereira Silva", "12345678901", "joao.silva@teste.com", "@#$FDF");
		Client c2 = new Client(null, "Leticia Souza", "98765432109", "leticia.souza@teste.com", "**7KJDD6");
		Client c3 = new Client(null, "Josenildo Souza", "68776562109", "josenildo.souza@teste.com", "eijeofij@@");
		Client c4 = new Client(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", "ddf@#$FDF");
		Client c5 = new Client(null, "Marie Curie", "322.429.140-06", "curie@mail.com", "gy@#$ghgFDF");
		Client c6 = new Client(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", "fv99#$FDF");
		Client c7 = new Client(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", "2588DF");
		Client c8 = new Client(null, "Max Planck", "081.399.300-83", "planck@mail.com", "@sssfef55g");

		// Criando Calleds
		Called called1 = new Called(null, Priority.HIGH, Status.OPEN, "Problema no servidor", "O servidor está fora do ar", t1, c1);
		Called called2 = new Called(null, Priority.MEDIUM, Status.CLOSED, "Problema na impressora", "A impressora não imprime", t2, c2);
		Called called3 = new Called(null, Priority.LOW, Status.OPEN, "Problema no software", "O software não funciona corretamente", t2, c2);
		Called called4 = new Called(null, Priority.MEDIUM, Status.PROGRESS, "Problema de travamento", "o Software travando", t8, c4);
		Called called5 = new Called(null, Priority.HIGH, Status.OPEN, "Sistema nao inicia", "O sistema nao esta iniciando", t7, c8);
		Called called6 = new Called(null, Priority.LOW, Status.CLOSED, "Disco corrompido", "O disco de memoria esta corrompido", t5, c7);
		Called called7 = new Called(null, Priority.HIGH, Status.OPEN, "Travamento", "Travamento no sistema", t7, c8);
		Called called8 = new Called(null, Priority.MEDIUM, Status.PROGRESS, "O PC não liga", "O Computador não inicia", t6, c6);
		Called called9 = new Called(null, Priority.LOW, Status.CLOSED, "Problema de DNS", "Problema de DNS na rede", t4, c5);

		technicianRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8));
		clientRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8));
		calledRepository.saveAll(Arrays.asList(called1, called2, called3, called4, called5, called6, called7, called8, called9));
	}
}
