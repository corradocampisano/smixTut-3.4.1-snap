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

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.universAAL.middleware.container.ModuleActivator;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextPublisher;
import org.universAAL.middleware.context.DefaultContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.context.owl.ContextProviderType;

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

		// add our route to the CamelContext, link to uaalContextPublisher
		camelContext.addRoutes(new ContentBasedFileMover(contextPublisher));
	}
}
