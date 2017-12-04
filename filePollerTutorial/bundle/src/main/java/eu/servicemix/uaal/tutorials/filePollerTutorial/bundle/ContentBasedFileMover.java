package eu.servicemix.uaal.tutorials.filePollerTutorial.bundle;

import org.apache.camel.builder.RouteBuilder;
import org.universAAL.middleware.context.ContextPublisher;

public class ContentBasedFileMover extends RouteBuilder {

	ContextPublisher contextPublisher;
	
	public ContentBasedFileMover(ContextPublisher cp) {
		this.contextPublisher = cp;
	}

	@Override
	public void configure() throws Exception {

		String newEndpoint = String.format("file://%s", "/home/corrado/uaal/RUNDIR/NEW");
		String arkEndpoint = String.format("file://%s", "/home/corrado/uaal/RUNDIR/ARK");
		String errEndpoint = String.format("file://%s", "/home/corrado/uaal/RUNDIR/ERR");

		from(newEndpoint).choice()
				// only process PDF files for now
				.when(header("CamelFileName").endsWith("pdf"))
				.process(new PdfProcessor(contextPublisher))
				.to(arkEndpoint)
				// other files to error
				.otherwise().to(errEndpoint);
	}
}
