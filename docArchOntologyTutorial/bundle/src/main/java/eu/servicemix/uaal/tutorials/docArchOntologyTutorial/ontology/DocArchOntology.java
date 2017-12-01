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

import org.universAAL.middleware.owl.DataRepOntology;
import org.universAAL.middleware.owl.ManagedIndividual;
import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.middleware.owl.OntClassInfoSetup;
import org.universAAL.middleware.owl.Ontology;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.TypeMapper;

//This is the main central class where you actually define the restrictions and
//relations between your concepts. It�s like a representation of an OWL file.
//You can have as many of these classes as you want in your bundle, 
//but in general one is enough.
public class DocArchOntology extends Ontology {
	
	// The factory is used for serialization
	private static DocArchFactory factory = new DocArchFactory();
	
	// Namespaces must follow this format
	public static final String NAMESPACE = "http://www.servicemix.eu/docArchOntology.owl#";

	public DocArchOntology(String ontURI) {
		super(ontURI);
		// This constructor should always be like this
	}

	public DocArchOntology() {
		super(NAMESPACE);
		// This constructor should always be like this
	}

	// This is where you actually define the relationships and restrictions
	public void create() {
		// First create the information for the whole ontology
		Resource r = getInfo();
		r.setResourceComment("A simple ontology for archiving documents");
		r.setResourceLabel("DocArchOntology: a simple ontology for archiving documents");
		// You must add an import for every ontology domain you use in your
		// concepts
		// We add DataRepresentation domain because we use ManagedIndividuals
		addImport(DataRepOntology.NAMESPACE);

		// This is the information object that you can reuse for all your
		// concepts
		OntClassInfoSetup oci;

		// For each concept you have created in your ontology, you have to
		// define the information, properties and restrictions

		
		// load DOCUMENT
		// ---------------------------------------------------------
		// First create the basic information. Because MyConcept can be
		// instantiated and serialized you must use this constructor passing the
		// serialization factory with a single ID for this concept
		oci = createNewOntClassInfo(Document.MY_URI, factory, 0);
		oci.setResourceComment("A Document is simply a file, with some path, mimetype and an import date");
		oci.setResourceLabel("A Document is simply a file, with some path, mimetype and an import date");
		// Add the superclass of your concept. You can use multiple inheritance.
		// If your concept does not extend any special resource, it must extend
		// ManagedIndividual
		oci.addSuperClass(ManagedIndividual.MY_URI);
		
		
		// After the main information you must declare the properties and the
		// restrictions

		// This restriction means that in this property there must be a URL concept, 
		// with cardinality 1:1 (a mandatory single URL).
		// SetFunctional means it can only have one value (max cardinality is 1)

		oci.addObjectProperty(Document.PROP_DOCUMENT_MD5).setFunctional();
		oci.addRestriction(MergedRestriction.getAllValuesRestrictionWithCardinality(Document.PROP_DOCUMENT_MD5,
				TypeMapper.getDatatypeURI(String.class), 1, 1));
		
		oci.addObjectProperty(Document.PROP_DOCUMENT_URL).setFunctional();
		oci.addRestriction(MergedRestriction.getAllValuesRestrictionWithCardinality(Document.PROP_DOCUMENT_URL,
				TypeMapper.getDatatypeURI(String.class), 1, 1));
		
		oci.addObjectProperty(Document.PROP_IMPORT_TIMESTAMP).setFunctional();
		oci.addRestriction(MergedRestriction.getAllValuesRestrictionWithCardinality(Document.PROP_IMPORT_TIMESTAMP,
				TypeMapper.getDatatypeURI(String.class), 1, 1));
		
		oci.addObjectProperty(Document.PROP_DOCUMENT_MIMETYPE).setFunctional();
		oci.addRestriction(MergedRestriction.getAllValuesRestrictionWithCardinality(Document.PROP_DOCUMENT_MIMETYPE,
				TypeMapper.getDatatypeURI(String.class), 1, 1));
		
		// There are other methods to declare restrictions, and even construct
		// more complex ones, but these are the most commonly used. You can also
		// restrict on properties you inherit from parent concepts.		
	}
}
