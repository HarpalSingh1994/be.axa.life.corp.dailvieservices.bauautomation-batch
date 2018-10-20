package be.axa.life.corp.dailvieservices.launch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import be.axa.life.corp.dailvieservices.conf.BatchApplicationContext;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class AppLaunch {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = null;
		try {

			SpringApplicationBuilder builder = new SpringApplicationBuilder(BatchApplicationContext.class);
			builder.headless(false);
			context = builder.run(args);

		} finally {
			Thread.sleep(1000*60*4);
			if (context != null) {
				context.close();
			}
		}
	}
}
