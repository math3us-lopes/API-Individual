package com.example.mercearia.services;

import java.text.DecimalFormat;
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
    private String emailServerHost;

    @Value("${spring.mail.port}")
    private Integer emailServerPort;

    @Value("${spring.mail.password}")
    private String emailPassword;

    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(emailRemetente);
        mailSender.setHost(emailServerHost);
        mailSender.setPort(emailServerPort);
        mailSender.setPassword(emailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true"); // Corrigido
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    public String writeEmail() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("diogoportelladantas1234@gmail.com");
        message.setSubject("API... Não sei pra onde vou, mas sei que pra onde eu for vai dar merda");
        message.setText("Parangaricotirrimiroaro " + dateTime.format(dateForm));

        try {
            javaMailSender.send(message);
            return "Email enviado com sucesso";
        } catch (Exception e) {
            return "Erro ao enviar email: " + e.getMessage();
        }
    }

    public String writeEmail2() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("Alguma coisa");
            helper.setBcc("diogoportelladantas1234@gmail.com");

            String emailText = "<h1>Título que faça sentido</h1>" +
                               "<p>Alguma coisa</p>" +
                               "<p>Alguma outra coisa " + dateTime.format(dateForm) + "</p><br/>";
            helper.setText(emailText, true);
            javaMailSender.send(message);
            return "Email enviado com sucesso! hummmm é mesmo?? já já da ruim aguarde";
        } catch (MessagingException e) {
            return "Erro ao enviar e-mail: " + e.getMessage();
        }
    }

    public void writeEmail3() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DecimalFormat df = new DecimalFormat("R$ #,##0.00");
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("Boa noite Fatima Bernards");
            helper.setTo("ton509270@gmail.com");

            StringBuilder sbuilder = new StringBuilder();
            sbuilder.append("<html>\r\n");
            sbuilder.append("    <body>\r\n");
            sbuilder.append("        <div style=\"text-align: center;\">\r\n");
            sbuilder.append("            <p>Aula</p>\r\n");
            sbuilder.append("            <p>Data: " + localDate.format(dateFormatter) + "</p>\r\n");
            sbuilder.append("        </div>\r\n");
            sbuilder.append("        <br>\r\n");
            sbuilder.append("        <table border='2' cellpadding='2'>\r\n");
            sbuilder.append("            <tr><th>Nome</th><th>Preço</th></tr>\r\n");
            sbuilder.append("            <tr><td>Esponja</td><td>" + df.format(5) + "</td></tr>\r\n");
            sbuilder.append("        </table>\r\n");
            sbuilder.append("    </body>\r\n");
            sbuilder.append("</html>");

            helper.setText(sbuilder.toString(), true);
            javaMailSender.send(message);

        } catch (MessagingException e) {
            System.out.println("Erro ao enviar email: " + e.getMessage());
        }
    }

	public void writerTeste() {
		// TODO Auto-generated method stub
		
	}

	public void writerTeste2() {
		// TODO Auto-generated method stub
		
	}
}
