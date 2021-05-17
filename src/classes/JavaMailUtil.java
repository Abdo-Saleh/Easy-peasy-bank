package classes;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import data.Database;

import javax.mail.PasswordAuthentication;
import javax.mail.Message;

public class JavaMailUtil {
	
	/**
	 * 
	 * @author abd alrahman saleh 
	 * @param recepient, receiver of the message
	 * @param userInfo, infromation about the user 
	 */
	public static void sendMail(String recepient,String userInfo) 
	{
		//set properties
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		
		String sender = Database.getSender();
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new  PasswordAuthentication(sender, Database.getSenderPassword());
			}
		});
		
		Message messageToBeSent = prepareMessage(session,sender,recepient,userInfo);
		try {
			Transport.send(messageToBeSent);
			System.out.println("Message sent successfully");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * @author abd alrahman saleh 
	 * @param session
	 * @param sender
	 * @param recepient
	 * @param userInfo
	 */
	private static Message prepareMessage(Session session, String sender , String recepient,String userInfo) {		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setText(userInfo);
			return message;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Couldn't send message");
		}
		
		return null;
	}
	
}
