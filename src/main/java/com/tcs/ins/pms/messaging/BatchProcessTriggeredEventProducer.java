package com.tcs.ins.pms.messaging;

import com.tcs.ins.pms.service.model.ProjectDetailModel;

public interface BatchProcessTriggeredEventProducer {

	void sendMessage(ProjectDetailModel event);
}