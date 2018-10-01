package be.axa.life.corp.dailvieservices.dao;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;

import be.axa.life.corp.dailvieservices.foundation.Constants;

@Repository
public class OracleDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	public List<Object> checkDifferdAffiliation(int env) {

		jdbcTemplate = new JdbcTemplate(getOracleConnection(env));
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
	public List<Object> getDifferedSortieData(int env) {

		jdbcTemplate = new JdbcTemplate(getOracleConnection(env));
		TbModificationAskedRowMapper tbModificationAskedRowMapper = new TbModificationAskedRowMapper();
		Calendar c = Calendar.getInstance(); // this takes current date
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date date = c.getTime();

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH24:MI");
		String date1 = dateFormatter.format(date);
		Object[] params = new Object[] { date1 };
		return getJdbcTemplate().query(Constants.DATA_DIFF_SORTIE, params,
				tbModificationAskedRowMapper);
	}
	public int countDiffredSortie(int env) {

		jdbcTemplate = new JdbcTemplate(getOracleConnection(env));
		Calendar c = Calendar.getInstance(); // this takes current date
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date date = c.getTime();

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH24:MI");
		String date1 = dateFormatter.format(date);
		Object[] params = new Object[] { date1 };
		return getJdbcTemplate().queryForObject(Constants.COUNT_DIFF_SORTIE, params,Integer.class);
	}
	public List<Integer> checkQuittanceQueries(int env) {
		List<Integer> resultCount = new ArrayList<Integer>();
		jdbcTemplate = new JdbcTemplate(getOracleConnection(env));
		Calendar c = Calendar.getInstance(); // this takes current date
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date date = c.getTime();

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = dateFormatter.format(date);
		Object[] params = new Object[] { date1 };
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE1, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE2, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE3, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE4, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE5, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE6, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE7, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE8, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE9, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE0, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE10, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE4_90, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE4_00, params,Integer.class));
		resultCount.add(getJdbcTemplate().queryForObject(Constants.READ_COUNT_DOCTYPE4_NOT_90, params,Integer.class));
		 return resultCount;
	}


	public SimpleDriverDataSource getOracleConnection(int env){

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
}