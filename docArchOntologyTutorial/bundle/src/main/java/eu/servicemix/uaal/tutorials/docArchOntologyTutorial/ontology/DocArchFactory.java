/*
	Copyright 2008-2014 ITACA-TSB, http://www.tsb.upv.es
	Instituto Tecnologico de Aplicaciones de Comunicacion 
	Avanzadas - Grupo Tecnologias para la Salud y el 
	Bienestar (TSB)
	
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
package eu.servicemix.uaal.tutorials.docArchOntologyTutorial.ontology;

import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.ResourceFactory;

//This class is needed for serialization. You usually only need one per main ontology class
public class DocArchFactory implements ResourceFactory {

	// This is the only method that you have to code, and it should always look
	// like this
	public Resource createInstance(String classURI, String instanceURI, int factoryIndex) {
		switch (factoryIndex) {
		// For each ID that you may receive (You declare the IDs in the main
		// ontology class) you must return an instance of the appropriate
		// concept class for that ID
		case 0:
			return new Document(instanceURI);
		}
		return null;
	}

}
