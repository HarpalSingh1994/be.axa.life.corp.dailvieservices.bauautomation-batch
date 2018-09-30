package be.axa.life.corp.dailvieservices.conf;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import be.axa.life.corp.dailvieservices.tasklets.CheckMailDifferedAffiliation;
import be.axa.life.corp.dailvieservices.tasklets.CheckNasLocation;
import be.axa.life.corp.dailvieservices.tasklets.RunOracleQueryDifferedAffiliation;
import be.axa.life.corp.dailvieservices.tasklets.SendMail;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
@ComponentScan("be.axa.life.corp.dailvieservices")
public class BatchApplicationContext {

	//private static final Logger LOG = LoggerFactory.getLogger(BatchApplicationContext.class.getName());

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public SimpleJobLauncher jobLauncher;

	@Bean
	public Job DifferedAffiliationJob() {
		return jobBuilderFactory.get("DifferedAffiliationJob").incrementer(new RunIdIncrementer())
				.start(stepCheckMailDifferedAffiliation()).next(stepCheckNasLocation())
				.next(stepRunOracleQueryDifferedAffiliation()).next(stepSendMail())
				.build();
	}

	@Bean
	public Step stepCheckMailDifferedAffiliation() {
		return stepBuilderFactory.get("stepCheckMailDifferedAffiliation").tasklet(checkMailDifferedAffiliation()).build();
	}

	@Bean
	public CheckMailDifferedAffiliation checkMailDifferedAffiliation() {
		return new CheckMailDifferedAffiliation();
	}
	@Bean
	public Step stepCheckNasLocation() {
		return stepBuilderFactory.get("stepCheckNasLocation").tasklet(checkNasLocation()).build();
	}

	@Bean
	public CheckNasLocation checkNasLocation() {
		return new CheckNasLocation();
	}
	
	@Bean
	public Step stepRunOracleQueryDifferedAffiliation() {
		return stepBuilderFactory.get("stepRunOracleQueryDifferedAffiliation").tasklet(runOracleQueryDifferedAffiliation()).build();
	}

	@Bean
	public RunOracleQueryDifferedAffiliation runOracleQueryDifferedAffiliation() {
		return new RunOracleQueryDifferedAffiliation();
	}
	
	@Bean
	public Step stepSendMail() {
		return stepBuilderFactory.get("stepSendMail").tasklet(sendMail()).build();
	}

	@Bean
	public SendMail sendMail() {
		return new SendMail();
	}

	@Scheduled(cron = "* 1 * * * *")
	public void perform() throws Exception {

		System.out.println("Harpal - Job Started at :" + new Date());

		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		JobExecution execution = jobLauncher.run(DifferedAffiliationJob(), param);

		System.out.println("Harpal - Job finished with status :" + execution.getStatus());
		System.out.println("I am called by Spring scheduler");
	}
	@Bean
	public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository);
		return launcher;
	}
}
