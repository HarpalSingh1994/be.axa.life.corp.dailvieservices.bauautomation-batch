package be.axa.life.corp.dailvieservices.foundation;

public class Constants {

	//public static final String TFTS = "//Nc0fsr01/dailvie_ft/viax/tfts";
	//public static final String RESOURCES_DIR = "C:/J/Resources/";
	public static final String TFTS_IMAGE = "TftsScreenShot.jpeg";
	public static final String READ_PENDING_DIFFERED = "select * from tb_historic_contract_aff where DEMAND_NATURE = '13 ' and demand_status = 'Z' and DEMAND_DATE<to_date(?, 'dd/MM/yyyy HH24:mi:ss') order by DEMAND_DATE desc";
	//public static final String HOST = "outlook.office365.com";// change accordingly
	public static final String MAIL_STORE_TYPE = "pop3";
//	public static final String USERNAME = "harpal.singh.external@axa.be";// change accordingly
//	public static final String PASSWORD = "J@ckport55&&";// change accordingly
	public static final String MAIL_SUBJECT_DIFFERED_AFFILIATION = "Test1";// change accordingly
	public static final String DATE_FORMAT = "EEEEE dd MMMMM yyyy HH:mm:ss";
	public static final String DATE_FORMAT_MONTH = "MM";
	public static final String IMAGE_FORMAT = "jpeg";
	
	//Tokens to remove
	public static final String TFTS = "C:\\Users\\Harpal Singh\\Desktop\\fireworks\\";
	public static final String RESOURCES_DIR = "C:\\Users\\Harpal Singh\\Desktop\\fireworks\\";
	public static final String HOST = "pop.gmail.com";// change accordingly
	public static final String USERNAME = "harpals429@gmail.com";// change accordingly
	public static final String PASSWORD = "1101336035";// change accordingly
	
	public static final String FROM_MAIL_ID = "learnitwell2018@gmail.com";
	public static final String TO_MAIL_ID   = "harpals429@gmail.com";
	public static final String SUBJECT_DIFF_AFF = "Affiliations différées";
	public static final String SUBJECT_SORTIE_AFTER_DIFF = "Error for SortieListener on PRD (After GC97DIFF)";
	public static final String SUBJECT_QUITTANCE = "SUIVI Doc quittance en Dail";
	public static final String FROM_PASSWORD = "gurukul2018";
	public static final String PARAM_FILE_PATH = "C:\\Users\\Harpal Singh\\Desktop\\fireworks\\";
	public static final String DIFF_AFF_PARAM_FILE_NAME = "differed_params.txt";
	public static final String SIGNATURE_MAIL = "</br>Thanks,</br>Harpal Singh";
}
