package be.axa.life.corp.dailvieservices.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import be.axa.life.corp.dailvieservices.dao.OracleDao;

public class RunOracleQueryDifferedAffiliation implements Tasklet {
	@Autowired
	OracleDao oracleDao;

	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		
		/*List<Object> OracleData = oracleDao.checkDifferdAffiliation(1);
		for(Object row : OracleData)
		{
			System.out.println(row);
		}
		System.out.println("RUn OK");*/
		return RepeatStatus.FINISHED;
	}

	public void setOracleDao(OracleDao oracleDao) {
		this.oracleDao = oracleDao;
	}
}
