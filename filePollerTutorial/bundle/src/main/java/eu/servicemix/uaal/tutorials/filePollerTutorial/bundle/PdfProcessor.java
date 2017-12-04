package eu.servicemix.uaal.tutorials.filePollerTutorial.bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextPublisher;

import eu.servicemix.uaal.tutorials.docArchOntologyTutorial.ontology.Document;

public class PdfProcessor implements Processor {

	Logger logger = Logger.getLogger(this.getClass());
	ContextPublisher contextPublisher;

	public PdfProcessor(ContextPublisher cp) {
		this.contextPublisher = cp;
	}

	public void process(Exchange exchange) throws Exception {
		logger.info("There is an exchange going on.");
		logger.info(exchange.getFromEndpoint().getEndpointUri());
		logger.info(exchange.getIn().getHeader("CamelFileName"));
		logger.info(exchange.getIn().getBody());
		logger.info(exchange.getIn().getBody().getClass());
		
		sendUaalEvent();
	}
	
	

	private void sendUaalEvent() {

		// Create a context event telling the system that the brightness of a
		// light source has changed. According to the device ontology the event
		// describes a triple of the form:
		// LightActuator hasValue x
		// In this example, the brightness of the kitchen light was dimmed to
		// 100% (= the kitchen light was turned on)
		/*
		 * ContextEvent evt = new ContextEvent(new
		 * LightActuator("urn:org.universAAL.aal_space:KitchenLight"),
		 * ValueDevice.PROP_HAS_VALUE, 100);
		 */

		Document document = new Document("urn:eu.servicemix.uaal.tutorials.filePollerTutorial:MokedDocumentHashMap");
		document.setDocumentURL("nowhere");
		document.setDocumentMimetype("pdf");
		document.setDocumentMd5("123456789012345678900123456789012345678901234567890012345678901234");

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd_hh:mm:ss");
		Date now = new Date();
		String ts = sdf.format(now);

		document.setImportTimestamp(ts);

		String predicate = "mockDocImportedOn";
		ContextEvent evt = new ContextEvent(document, predicate, ts);

		System.out.println("SENDING EVENT : " + evt.toString());

		contextPublisher.publish(evt);
	}
}
