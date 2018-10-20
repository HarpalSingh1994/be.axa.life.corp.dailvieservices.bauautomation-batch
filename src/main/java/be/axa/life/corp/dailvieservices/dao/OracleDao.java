package be.axa.life.corp.dailvieservices.dao;

import org.springframework.stereotype.Repository;

@Repository
public class OracleDao {
	
	/*@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	public List<Object> checkDifferdAffiliation(int env) {

		jdbcTemplate = new JdbcTemplate(oracleConnection(env));
		TbHistoricContractAffRowMapper tbHistoricContractAffRowMapper = new TbHistoricContractAffRowMapper();
		Calendar c = Calendar.getInstance(); // this takes current date
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date date = c.getTime();

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = dateFormatter.format(date);
		Object[] params = new Object[] { date1 };
		return getJdbcTemplate().query(Constants.READ_PENDING_DIFFERED, params,
				tbHistoricContractAffRowMapper);
	}

	public SimpleDriverDataSource oracleConnection(int env){

		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream;
		if(env==1){
			stream = loader.getResourceAsStream("prod.properties");
		}else{
			stream = loader.getResourceAsStream("stg.properties");
		}
		try {
			prop.load(stream);
		} catch (IOException e) {
			System.out.println(e);
		}
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		//dataSource.setDriver(new oracle.jdbc.OracleDriver());
		dataSource.setUrl(prop.getProperty("url"));
		dataSource.setUsername(prop.getProperty("username"));
		dataSource.setPassword(prop.getProperty("password"));	
		return dataSource;

	}
*/}