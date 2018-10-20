package be.axa.life.corp.dailvieservices.tasklets;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import be.axa.life.corp.dailvieservices.foundation.Constants;

public class CheckNasLocationDifferedAffiliation implements Tasklet {

	private static final Logger LOG = LoggerFactory.getLogger(CheckNasLocationDifferedAffiliation.class.getName());

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		File file = new File(Constants.TFTS);
		if (file.isDirectory()) {
			screenShot(Constants.TFTS);
			if(file.list().length==0){
				LOG.info("No File present in location "+Constants.TFTS);
			}
			else{
				LOG.info("Number of files present in location "+Constants.TFTS+" are:"+file.list().length);
			}
		}
		return RepeatStatus.FINISHED;
	}
	void screenShot(String path) throws IOException, AWTException, InterruptedException
	{
		Desktop.getDesktop().open(new File(path));
		Thread.sleep(2000);
		Robot robot = new Robot(); 
		String fileName =Constants.RESOURCES_DIR + Constants.TO_GD_IMAGE;

		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
		ImageIO.write(screenFullImage, Constants.IMAGE_FORMAT, new File(fileName));
	}
}
