/*
    Copyright 2016-2020 Carsten Stockloew

    See the NOTICE file distributed with this work for additional
    information regarding copyright ownership

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
package eu.servicemix.uaal.tutorials.filePollerTutorial.bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.ComponentConfiguration;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.FileComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.InterceptStrategy;
import org.universAAL.middleware.container.ModuleActivator;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextPublisher;
import org.universAAL.middleware.context.DefaultContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.context.owl.ContextProviderType;
import eu.servicemix.uaal.tutorials.docArchOntologyTutorial.ontology.Document;

/**
 * The module activator handles the starting and stopping of this module. It is
 * similar to the OSGi BundleActivator.
 * 
 * In our case, we simply want to publish a context event, so we do this
 * directly in the start method.
 * 
 * @author Corrado Campisano
 */
public class Activator implements ModuleActivator {

	ContextPublisher contextPublisher = null;
	CamelContext camelContext = null;

	public void start(ModuleContext mc) throws Exception {

		System.out.println("STARTING : " + this.getClass().getPackage());

		setupContextPublisher(mc);

		setupCamelContext();
		camelContext.start();
	}

	public void stop(ModuleContext arg0) throws Exception {
		System.out.println("STOPPING : " + this.getClass().getPackage());

		// Close our context publisher and free all resources. The publisher
		// should be re-used if multiple events need to be published.
		if (contextPublisher != null)
			contextPublisher.close();

		if (camelContext != null)
			camelContext.stop();

	}

	private void setupContextPublisher(ModuleContext mc) {
		// Set up the context publisher by providing some information about
		// ourselves. Especially, we need a URI that uniquely identifies this
		// component and the provider type (one of: controller, gauge,
		// reasoner).
		ContextProvider provInfo = new ContextProvider("urn:eu.servicemix.uaal.tutorials.filePollerTutorial:pubBundle");
		provInfo.setType(ContextProviderType.controller);
		contextPublisher = new DefaultContextPublisher(mc, provInfo);
	}

	private void setupCamelContext() throws Exception {
		camelContext = new DefaultCamelContext();

		// add our route to the CamelContext

		camelContext.addRoutes(new RouteBuilder() {

			public void configure() {

				String dirSource = "/home/corrado/uaal/RUNDIR/NEW";
				String dirTarget = "/home/corrado/uaal/RUNDIR/ARK";
				//String fileName = "*.pdf";
				//String fromEndpoint = String.format("file://%s?fileName=%s", dirSource, fileName);
				String fromEndpoint = String.format("file://%s", dirSource);
				//String toEndpoint = String.format("file://%s?fileName=%s", dirTarget, fileName);
				String toEndpoint = String.format("file://%s", dirTarget);
				
				from(fromEndpoint).to(toEndpoint);
			}
		});
	}

	private void sendEvent() {

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
