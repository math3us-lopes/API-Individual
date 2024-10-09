package com.example.mercearia.services;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {

	@Autowired
	public JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String emailRemetente;

	@Value("${spring.mail.host}")
	private String emailServeHost;

	@Value("${spring.mail.port}")
	private Integer emailServePort;

	@Value("${spring.mail.password}")
	private String emailPassword;

	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(emailRemetente);
		mailSender.setHost(emailServeHost);
		mailSender.setPort(emailServePort);
		mailSender.setPassword(emailPassword);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		return mailSender;
	}

	public String writerTeste() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("xgditx@gmail.com");
		message.setSubject("hmmmm..pensando aqui");
		message.setText("veganismo vibes e afins e talz" + dateTime.format(dateForm));

		try {
			javaMailSender.send(message);
			return "Email enviado com sucesso!";
		} catch (Exception e) {
			return "Erro ao enviar o Email" + e.getMessage();
		}
	}

	public String writerTeste2() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject("Teste email2");
			helper.setTo("xgditx@gmail.com");

			String emailText = "<h1>Faça sentido</h1>" + "<p>Diogo é meu amigo!</p>" + "<p>Diogo é meu amigo!</p>"
					+ "<p>na relíquia do Julio Braz " 
					+ dateTime.format(dateForm) + "</p>" 
					
					//ADICIONANDO IMAGEM NO E-MAIL
					
					//O cid:flamengoMaiorTime se refere a imagem que vou embutir no email(identificador)
				+ "<br> "
				+ "<img src='https://img.ifunny.co/images/8032e951bd9df5efca6fda6edc505ef4c964c9a0a32f5c4170fd192c99aba050_3.jpg'>"+"<br>";
		
		helper.setText(emailText, true);
		
//			
////			//Referencia da imagem embutida no email, indicando o caminho da imagem
        File MclaremCar = new File("C:\\Users\\mathe\\Desktop\\tbGrupo1\\bugatti.jpg");
////           
////            //Adiciona a imagem no e-mail com o identificad criado 'flamengoMaiorTime'.
        helper.addInline("MclaremCar", MclaremCar);
////           
////            //Caminho da imagem que sera adicionado ao e-mail
            File Mclaremcar = new File("C:\\Users\\mathe\\Desktop\\tbGrupo1\\bugatti.jpg");
////            
//            //Adiciona a imagem como um anexo. o nome "imagens" será o nome do arquivo no e-mail.
          helper.addAttachment("bugatti.jpg", MclaremCar);
//			
            //Envia o e-mail
			javaMailSender.send(message);
			return "email enviado com sucesso!";
		} catch (MessagingException e) {
			return "Erro ao enviar o e-mail" + e.getMessage();
		}

	}
}

