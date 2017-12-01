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

import org.universAAL.middleware.owl.ManagedIndividual;


//If you are making a concept that does not inherit from any other you just 
//extend ManagedIndividual. Otherwise you extend the concept class you inherit from
public class Document extends ManagedIndividual {
	
	// Make sure you use the same namespace in all your domain ontology
	// You can declare the namespace in your main Ontology class and later reuse
	// it in the rest of classes

	// MY URI is the URI of this concept. It is mandatory for all. Must have the
	// same name than the class
	public static final String MY_URI = DocArchOntology.NAMESPACE + "Document";

	// Now declare the URI of ALL properties that this concept defines. They
	// must start with lower case.
	
	public static final String PROP_DOCUMENT_MD5 = DocArchOntology.NAMESPACE + "hasMd5Hash";
	public static final String PROP_DOCUMENT_URL = DocArchOntology.NAMESPACE + "isLocatedAt";
	public static final String PROP_IMPORT_TIMESTAMP = DocArchOntology.NAMESPACE + "hasBeenImportedOn";
	public static final String PROP_DOCUMENT_MIMETYPE = DocArchOntology.NAMESPACE + "hasMimeType";
	

	public Document() {
		super();
		// Basic constructor. In general it is like this, just a call to super.
	}

	public Document(String uri) {
		super(uri);
		// This is the commonly used constructor. In general it is like this,
		// just a call to super.
	}

	// This method is used in de/serialization and must always be the same.
	public String getClassURI() {
		return MY_URI;
	}

	// This method is used for serialization purposes, to restrict the amount of
	// information to serialize when forwarding it among nodes.
	// For each property you must return one of PROP_SERIALIZATION_FULL,
	// REDUCED, OPTIONAL or UNDEFINED.
	// Refer to their javadoc to see what they mean.
	public int getPropSerializationType(String propURI) {
		// In this case we serialize everything. It is up to you to define what
		// is important to be serialized and what is expendable in your concept.
		return PROP_SERIALIZATION_FULL;
	}

	// In this method you evaluate if an instance of your concept is properly
	// built, e.g. if all mandatory fields are present.
	public boolean isWellFormed() {
		boolean ret = true;
		// In this case we say it is well formed if the property X, that we
		// declared as mandatory, is present.
		// While you test your concept it is easier to return always true.
		
		ret &= (getDocumentLocation() != null);
		ret &= (getImportTimestamp() != null);
		ret &= (getDocumentMimetype() != null);
		
		return ret;
	}

	// From here onwards we declare the getter and setters and other helper
	// methods for our declared properties
	// These are NOT MANDATORY, but are helpful for those who will use the
	// ontology.


	// Getters and setters are the most common, but you can add as many other
	// helper methods as you want, such as remove. Take into account that all
	// properties can always be handled with the methods of ManagedIndividual
	// and Resource, which all concepts inherit. The helper methods just make
	// developers life easier.

	public String getDocumentMd5() {
		return (String) props.get(PROP_DOCUMENT_MD5);
	}
	public void setDocumentMd5(String md5) {
		props.put(PROP_DOCUMENT_MD5, md5);
	}
	
	
	public String getDocumentLocation() {
		return (String) props.get(PROP_DOCUMENT_URL);
	}
	public void setDocumentURL(String url) {
		props.put(PROP_DOCUMENT_URL, url);
	}
	
	
	public String getImportTimestamp() {
		return (String) props.get(PROP_IMPORT_TIMESTAMP);
	}
	public void setImportTimestamp(String ts) {
		props.put(PROP_IMPORT_TIMESTAMP, ts);
	}
	
	
	public String getDocumentMimetype() {
		return (String) props.get(PROP_DOCUMENT_MIMETYPE);
	}
	public void setDocumentMimetype(String mt) {
		props.put(PROP_DOCUMENT_MIMETYPE, mt);
	}
	
}
