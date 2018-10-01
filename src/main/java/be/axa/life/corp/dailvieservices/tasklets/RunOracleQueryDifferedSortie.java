package be.axa.life.corp.dailvieservices.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import be.axa.life.corp.dailvieservices.dao.OracleDao;

public class RunOracleQueryDifferedSortie implements Tasklet {
	
	@Autowired
	OracleDao oracleDao;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		int rowCount = oracleDao.countDiffredSortie(1);
		System.out.println("row count differed Sortie:"+rowCount);
		return RepeatStatus.FINISHED;
	}
	public void setOracleDao(OracleDao oracleDao) {
		this.oracleDao = oracleDao;
	}
}
