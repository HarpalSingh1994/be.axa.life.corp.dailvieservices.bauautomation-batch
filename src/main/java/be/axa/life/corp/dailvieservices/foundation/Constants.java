package be.axa.life.corp.dailvieservices.foundation;

public class Constants {

	//public static final String TFTS = "//Nc0fsr01/dailvie_ft/viax/tfts";
	//public static final String RESOURCES_DIR = "C:/J/Resources/";
	public static final String TO_GD_IMAGE = "ToGDScreenShot.jpeg";
	public static final String READ_PENDING_DIFFERED = "select * from tb_historic_contract_aff where DEMAND_NATURE = '13 ' and demand_status = 'Z' and DEMAND_DATE<to_date(?, 'dd/MM/yyyy HH24:mi:ss') order by DEMAND_DATE desc";
	public static final String HOST = "outlook.office365.com";// change accordingly
	public static final String MAIL_STORE_TYPE = "pop3";
	public static final String USERNAME = "harpal.singh.external@axa.be";// change accordingly
	public static final String PASSWORD = "J@ckport55&&";// change accordingly
	public static final String MAIL_SUBJECT_DIFFERED_AFFILIATION = "Test1";// change accordingly
	public static final String DATE_FORMAT = "EEEEE dd MMMMM yyyy HH:mm:ss";
	public static final String DATE_FORMAT_MONTH = "MM";
	public static final String IMAGE_FORMAT = "jpeg";
	
	//Tokens to remove
	public static final String TFTS = "C:\\Users\\Harpal Singh\\Desktop\\fireworks\\";
	public static final String RESOURCES_DIR = "C:\\Users\\Harpal Singh\\Desktop\\fireworks\\";
}
