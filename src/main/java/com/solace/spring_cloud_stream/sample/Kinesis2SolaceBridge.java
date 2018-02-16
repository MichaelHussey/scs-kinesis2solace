package com.solace.spring_cloud_stream.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class to which the SolaceBinder will pass Messages received from VMR
 */
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.aws.support.AwsHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;

import com.solace.spring_cloud_stream.binder.InputMessageChannelAdapter;
import com.solace.spring_cloud_stream.binder.SolaceBinderConstants;

@EnableBinding(Processor.class)
public class Kinesis2SolaceBridge {  

	private static final Logger logger = LoggerFactory.getLogger(Kinesis2SolaceBridge.class);

	/**
	 * 
	 * @param message
	 * @return
	 */
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Message<?> receiveAndForward(Message<?> message) {
		logger.info("Received message: "+message.toString());
		MessageBuilder<?> mb = MessageBuilder.fromMessage(message);
		String destName = (String) message.getHeaders().get(SolaceBinderConstants.FIELD_DESTINATION_NAME);
		mb.setHeader(AwsHeaders.PARTITION_KEY, destName);
		message = mb.build();
		return message;
	}
	
}
