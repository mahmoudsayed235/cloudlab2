package email;

import java.io.*;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Cloud1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/plain");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		String from = "mahmoud.sayed235@gmail.com";
		String to = request.getParameter("to");// "mmody910@gmail.com";
		String subject = request.getParameter("subject");// "cloud";
		String message = request.getParameter("message");// "servlet";
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"mahmoud.sayed235@gmail.com", "m@hmoudMODY2010");
					}
				});
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					"mmody910@gmail.com"));
			msg.setSubject("cloud");
			msg.setText("servlet");
			Transport.send(msg);

			response.getWriter().write("success");
			System.out.println("success");
		} catch (MessagingException e) {
			response.getWriter().write("error");

			System.out.println("error");
			throw new RuntimeException(e);
		}
		return;

	}
}

class SMTPAuthenticator extends Authenticator {

	private PasswordAuthentication authentication;

	public SMTPAuthenticator(String login, String password) {
		authentication = new PasswordAuthentication(login, password);
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return authentication;

	}
}
