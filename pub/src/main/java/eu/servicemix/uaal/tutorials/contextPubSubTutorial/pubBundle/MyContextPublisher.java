/*
    Copyright 2017-2021 Corrado Campisano

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
package eu.servicemix.uaal.tutorials.contextPubSubTutorial.pubBundle;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.DefaultContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.ontology.device.LightActuator;
import org.universAAL.ontology.device.ValueDevice;

public class MyContextPublisher extends DefaultContextPublisher {

	public MyContextPublisher(ModuleContext context, ContextProvider providerInfo) {
		super(context, providerInfo);

		// Create a context event telling the system that the brightness of a
		// light source has changed. According to the device ontology the event
		// describes a triple of the form:
		// LightActuator hasValue x
		// In this example, the brightness of the kitchen light was dimmed to
		// 100% (= the kitchen light was turned on)

		Resource subject = new LightActuator("urn:org.universAAL.aal_space:KitchenLight");
		String predicate = ValueDevice.PROP_HAS_VALUE;
		Object value = 100;
		ContextEvent event = new ContextEvent(subject, predicate, value);
		this.publish(event);
	}

}
