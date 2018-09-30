package be.axa.life.corp.dailvieservices.tasklets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.joda.time.DateTime;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import be.axa.life.corp.dailvieservices.foundation.Constants;
import be.axa.life.corp.dailvieservices.foundation.Mailer;

public class SendMail implements Tasklet {
	
	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		String subject = null;
		String messageBody = null;
		DateTime now = new DateTime();
		String day = ""+now.getDayOfMonth();
		subject = prepareSubject(day);
		messageBody = prepareBody(day);
		if ((null != subject && null != messageBody) && !(subject.isEmpty() || messageBody.isEmpty())) {
			Mailer.send(Constants.FROM_MAIL_ID, Constants.FROM_PASSWORD, Constants.TO_MAIL_ID, subject, messageBody);
			System.out.println("Mail Sent Successfully.");
		} else {
			System.out.println("Unable to send Mail - Either subject or body is empty.");
		}
		return RepeatStatus.FINISHED;
	}
	
	public String prepareBody(String day) {

		if (null != day && day.isEmpty()) {
			return null;
		} else if (day.equalsIgnoreCase("1")) {
			return differedAffBody();
		} else if (day.equalsIgnoreCase("2")) {
			return sortieDiffBody();
		} else if (day.equalsIgnoreCase("3")) {
			return quittanceBody();
		} else {
			return null;
		}
	}

	public String quittanceBody() {
	String htmlText = "<p>Hello Marguerite,</br></p><p></br>We have run the SQL on prod oracle table for <b>Quittancement.</b> "
			+ "The result values have been uploaded in below link .</p>"
			+ "<p><a href=\"https://team.axa.be/sites/Applications/AMLife/DailVie/Shared%20Documents/BAU/Suivi%20Doc%20quittance%20en%20Dail.xls.\">https://team.axa.be/sites/Applications/AMLife/DailVie/Shared%20Documents/BAU/Suivi%20Doc%20quittance%20en%20Dail.xls.</a></p>" 
			+ "<p></br></br>We have highlighted the column for which the difference is more than 10%.</p>"
			+"<p></br></br>Please have a look and let us know in case of any concern.</p><p>"+Constants.SIGNATURE_MAIL+"</p>";

		
		return htmlText;
	}

	public String sortieDiffBody() {

		return null;
	}

	public String differedAffBody() {
		try {
			String[] parms = readFile(Constants.DIFF_AFF_PARAM_FILE_NAME);
			MimeMultipart multipart = new MimeMultipart("related"); 
			BodyPart messageBodyPart = new MimeBodyPart(); 
			StringBuffer messagePart1 = new StringBuffer(); 
			String htmlText ="<P>Hello Marguerite,</br></br></p>" +
					"<P>According to point 22 in Dailvie BAU handbook, we checked the NAS location and ";
			messagePart1.append(htmlText);
			String numberOfFilesPresent = null;
			String recordsInOracle = null;
			if(null != parms) {
			 numberOfFilesPresent = parms[0];
			 recordsInOracle = parms[1];
			}
			if("0".equals(numberOfFilesPresent.trim()))
			{
				messagePart1.append("there is no file.</P>");
			}
			else {
				messagePart1.append(numberOfFilesPresent+" files are present there.</P>");
			}
			messagePart1.append("<P></br>Please find the ScreenShot of NAS location attached.");
			messagePart1.append("</br>We also executed the below query in oracle. There are ");
			if("0".equals(recordsInOracle.trim())) {
				messagePart1.append("no");
			}
			else {
				messagePart1.append(recordsInOracle.trim());
			}
			messagePart1.append(" record found.</br></P>");
			messagePart1.append("<P></br>Oracle Query:</br></br></p>"+Constants.PARAM_FILE_PATH);
			messagePart1.append("<P></br>"+Constants.SIGNATURE_MAIL+"</P>");
			
			return messagePart1.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public String[] readFile(String FileName) throws IOException {
		String[] parmArray = null;
		File file = new File(Constants.PARAM_FILE_PATH + FileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while ((st = br.readLine()) != null) {
			parmArray = st.split(";");
		}
		return parmArray;
	}

	public String prepareSubject(String day) {

		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMMM YYYY");
		String month = dateFormatter.format(date);
		if (null != day && day.isEmpty()) {
			return null;
		} else if (day.equalsIgnoreCase("1")) {
			return Constants.SUBJECT_DIFF_AFF + " - " + month;
		} else if (day.equalsIgnoreCase("2")) {
			return Constants.SUBJECT_SORTIE_AFTER_DIFF;
		} else if (day.equalsIgnoreCase("3")) {
			return Constants.SUBJECT_QUITTANCE + " - " + month;
		} else {
			return null;
		}
	}

}
