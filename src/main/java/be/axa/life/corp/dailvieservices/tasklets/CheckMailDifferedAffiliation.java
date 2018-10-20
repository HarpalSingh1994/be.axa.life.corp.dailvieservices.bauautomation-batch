package be.axa.life.corp.dailvieservices.tasklets;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import be.axa.life.corp.dailvieservices.foundation.Constants;

public class CheckMailDifferedAffiliation implements Tasklet {

	private static final Logger LOG = LoggerFactory.getLogger(CheckMailDifferedAffiliation.class
			.getName());

	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		LOG.info("Harpal Singh ");
		return RepeatStatus.FINISHED;
		/*String mailRecivedDate ;
		String mailRecievedMonth;
		SimpleDateFormat dateFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		SimpleDateFormat dateFormatterMonth = new SimpleDateFormat(Constants.DATE_FORMAT_MONTH);
		String CurrentMonth = dateFormatterMonth.format(new Date());
		Message [] messages; 
		messages = check(Constants.HOST,Constants.MAIL_STORE_TYPE,Constants.USERNAME,Constants.PASSWORD);

		for(int i = messages.length-1 ; i>0; i--)
		{
			Message message = messages[i];
			mailRecivedDate = dateFormatter.format(message.getSentDate());
			mailRecievedMonth = dateFormatterMonth.format(message.getSentDate());

			if(!(CurrentMonth.equals(mailRecievedMonth))){
				break;
			}
			if(Constants.MAIL_SUBJECT_DIFFERED_AFFILIATION.equalsIgnoreCase(message.getSubject())){
				LOG.info("Scheduler has ran Successfully on date :"+mailRecivedDate);
				LOG.info("From: " + message.getFrom()[0]);
				LOG.info("Subject: " + message.getSubject());
				LOG.info("Text: " + message.getContent());
				break;
			}
		}

		return RepeatStatus.FINISHED;
	}

	public static Message[] check(String host, String storeType, String user,
			String password) {
		try {
			// create properties field
			Properties properties = new Properties();

			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3s");

			store.connect(host, user, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			return emailFolder.getMessages();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			return null;
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/
	}

}
