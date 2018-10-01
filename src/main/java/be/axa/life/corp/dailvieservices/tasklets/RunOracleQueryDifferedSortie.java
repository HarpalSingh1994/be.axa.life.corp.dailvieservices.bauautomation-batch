package be.axa.life.corp.dailvieservices.tasklets;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import be.axa.life.corp.dailvieservices.dao.OracleDao;
import be.axa.life.corp.dailvieservices.model.TbModificationAskedVo;

public class RunOracleQueryDifferedSortie implements Tasklet {
	
	@Autowired
	OracleDao oracleDao;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		int rowCount = oracleDao.countDiffredSortie(1);
		System.out.println("row count differed Sortie:"+rowCount);
		List<TbModificationAskedVo> dataFromOracle = new ArrayList<TbModificationAskedVo>();
		
		List<String> policyInGD = readFile();
		processrecords();
		return RepeatStatus.FINISHED;
	}
	private List<String> readFile() {
		// TODO Auto-generated method stub
		return new ArrayList();
	}
	private void processrecords() {
		// TODO Auto-generated method stub
		
	}
	public void setOracleDao(OracleDao oracleDao) {
		this.oracleDao = oracleDao;
	}
}
