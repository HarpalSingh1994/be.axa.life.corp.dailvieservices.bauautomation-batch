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
import be.axa.life.corp.dailvieservices.tasklets.CheckNasLocationDifferedAffiliation;
import be.axa.life.corp.dailvieservices.tasklets.RunOracleQueryDifferedAffiliation;
import be.axa.life.corp.dailvieservices.tasklets.SendMailDifferedAffiliation;

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
				.start(stepCheckMailDifferedAffiliation()).next(stepCheckNasLocationDifferedAffiliation())
				.next(stepRunOracleQueryDifferedAffiliation()).next(stepSendMailDifferedAffiliation())
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
	public Step stepCheckNasLocationDifferedAffiliation() {
		return stepBuilderFactory.get("stepCheckNasLocationDifferedAffiliation").tasklet(checkNasLocationDifferedAffiliation()).build();
	}

	@Bean
	public CheckNasLocationDifferedAffiliation checkNasLocationDifferedAffiliation() {
		return new CheckNasLocationDifferedAffiliation();
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
	public Step stepSendMailDifferedAffiliation() {
		return stepBuilderFactory.get("stepSendMailDifferedAffiliation").tasklet(sendMailDifferedAffiliation()).build();
	}

	@Bean
	public SendMailDifferedAffiliation sendMailDifferedAffiliation() {
		return new SendMailDifferedAffiliation();
	}

	@Scheduled(cron = "*/10 * * * * *")
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
