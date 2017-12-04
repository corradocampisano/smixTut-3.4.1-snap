package eu.servicemix.uaal.tutorials.filePollerTutorial.bundle;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.universAAL.middleware.context.ContextPublisher;

public class ErrorProcessor implements Processor {

	Logger logger = Logger.getLogger(this.getClass());
	ContextPublisher contextPublisher;

	public ErrorProcessor(ContextPublisher cp) {
		this.contextPublisher = cp;
	}

	public void process(Exchange exchange) throws Exception {
		logger.error("Error with an exchange going on.");
		logger.error(exchange.getFromEndpoint().getEndpointUri());
		logger.error(exchange.getIn().getHeader("CamelFileName"));
		logger.error(exchange.getIn().getBody());
		logger.error(exchange.getIn().getBody().getClass());
		
		logger.error("FILE CONTENT TYPE: " + exchange.getIn().getHeader(Exchange.FILE_CONTENT_TYPE));
	}
}
