package com.tcs.ins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tcs.ins.pms.messaging.DefaultBatchProcessTriggeredEventProducer;
import com.tcs.ins.pms.service.model.ProjectDetailModel;

@SpringBootApplication
public class PmsApiApplication implements CommandLineRunner {

	@Autowired
	DefaultBatchProcessTriggeredEventProducer defaultTriggeredEventProducer;

	public static void main(String[] args) {
		SpringApplication.run(PmsApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ProjectDetailModel projectDetailModel = new ProjectDetailModel();
		projectDetailModel.setCustomer("Producer");
		projectDetailModel.setProjectName("Category-1");
		projectDetailModel.setProjectSummary("Sub Category-1");
		defaultTriggeredEventProducer.sendMessage(projectDetailModel);
		System.out.println("sending message from producer : " + projectDetailModel);
	}
}