package eu.servicemix.uaal.tutorials.filePollerTutorial.bundle;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.universAAL.middleware.context.ContextPublisher;

public class ContentBasedFileMover extends RouteBuilder {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	ContextPublisher contextPublisher;
	
	public ContentBasedFileMover(ContextPublisher cp) {
		this.contextPublisher = cp;
	}

	@Override
	public void configure() throws Exception {

		String newEndpoint = String.format("file://%s", "/home/corrado/uaal/RUNDIR/NEW?probeContentType=true");
		String arkEndpoint = String.format("file://%s", "/home/corrado/uaal/RUNDIR/ARK");
		String errEndpoint = String.format("file://%s", "/home/corrado/uaal/RUNDIR/ERR");
		
		onException(Exception.class).process(new ErrorProcessor(contextPublisher)).to(errEndpoint);
		
		from(newEndpoint).choice()
				// only process PDF files for now
				.when(header(Exchange.FILE_CONTENT_TYPE).isEqualToIgnoreCase("application/pdf"))
				.process(new PdfProcessor(contextPublisher)).to(arkEndpoint)
				// other files to error
				.otherwise().process(new ErrorProcessor(contextPublisher)).to(errEndpoint);
	}
}
