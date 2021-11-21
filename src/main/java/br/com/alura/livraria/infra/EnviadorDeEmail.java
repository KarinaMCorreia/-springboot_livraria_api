package br.com.alura.livraria.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EnviadorDeEmail {

	@Autowired
	private JavaMailSender mailSender;

	@Async
	public void enviarEmail(String destinatario, String assunto, String menssagem) {

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(destinatario);
		email.setSubject(assunto);
		email.setText(menssagem);

		mailSender.send(email);

	}

}
