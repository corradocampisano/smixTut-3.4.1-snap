
# contextPubSubTutorial for uAAL 3.4.1-snapshot




## general setup and build instructions


see parent (root) project readme for instructions.

those are a prerequisite to run any of the tutorials.




## running this tutorial


first make sure you can setup the environment correctly, see previous section.

once done, see the following for deployment instructions. 



### 0) Install Camel into distro.karaf:


add the feature repository for Camel this way:

	karaf@uAAL>feature:repo-add mvn:org.apache.camel.karaf/apache-camel/2.9.0/xml/features
	Adding feature url mvn:org.apache.camel.karaf/apache-camel/2.9.0/xml/features


double check:

	karaf@uAAL>feature:list | grep -i camel
	xml-specs-api                | 1.9.0            |           | camel-2.9.0                       |  
	camel                        | 2.9.0            |           | camel-2.9.0                       |   
	camel-core                   | 2.9.0            |           | camel-2.9.0                       |   
	...
	camel-zookeeper              | 2.9.0            |           | camel-2.9.0                       |   

once the Camel feature repo is configured, the tutorial feature can reference it.



### 1) Add feature repository:


since the feature got installed locally (mvn: = maven local repo):

	[INFO] Installing /home/corrado/uaal/smixTut-3.4.1-snap/filePollerTutorial/feature/target/features/features.xml to /home/corrado/.m2/repository/eu/servicemix/uaal/tutorials/filePollerTutorial.feature/3.4.1-SNAPSHOT/filePollerTutorial.feature-3.4.1-SNAPSHOT-features.xml


we can add the local (mvn: = maven local repo) feature-repo in Karaf:

	karaf@uAAL>feature:repo-add mvn:eu.servicemix.uaal.tutorials/filePollerTutorial.feature/3.4.1-SNAPSHOT/xml/features
	Adding feature url mvn:eu.servicemix.uaal.tutorials/filePollerTutorial.feature/3.4.1-SNAPSHOT/xml/features


double check:

	karaf@uAAL>feature:repo-list | grep -i filepoll
	uaalTutorials-Feature             | mvn:eu.servicemix.uaal.tutorials/filePollerTutorial.feature/3.4.1-SNAPSHOT/xml/features



### 2) install feature:

add "-v" (verbose) to let it show more:

	karaf@uAAL>feature:install -v uaalTuts-filePollerTutorial
	Installing feature uaalTuts-filePollerTutorial 3.4.1-SNAPSHOT
	Installing feature camel-core 2.18.4
	Installing feature xml-specs-api 2.7.0
	Installing bundle mvn:org.apache.servicemix.specs/org.apache.servicemix.specs.activation-api-1.1/2.7.0
	Installing bundle mvn:org.apache.servicemix.specs/org.apache.servicemix.specs.stax-api-1.0/2.7.0
	Installing bundle mvn:org.apache.servicemix.specs/org.apache.servicemix.specs.jaxb-api-2.2/2.7.0
	Installing bundle mvn:org.codehaus.woodstox/stax2-api/3.1.4
	Installing bundle mvn:org.codehaus.woodstox/woodstox-core-asl/4.4.1
	Installing bundle mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.jaxb-impl/2.2.11_1
	Installing bundle mvn:org.apache.camel/camel-core/2.18.4
	Installing bundle mvn:org.apache.camel/camel-catalog/2.18.4
	Installing bundle mvn:org.apache.camel/camel-commands-core/2.18.4
	Installing feature uaalTuts-docArchOntologyTutorial 3.4.1-SNAPSHOT
	Installing bundle mvn:eu.servicemix.uaal.tutorials/docArchOntologyTutorial.bundle/3.4.1-SNAPSHOT
	Installing bundle mvn:eu.servicemix.uaal.tutorials/filePollerTutorial.bundle/3.4.1-SNAPSHOT
	STARTING : package eu.servicemix.uaal.tutorials.filePollerTutorial.bundle
	SENDING EVENT : urn:org.universAAL.middleware.context.rdf:ContextEvent#_:7f000101fefe5560:bb



WHAT'S BELOW SHALL BE UPDATED

double check:

	karaf@uAAL>list | grep -i pubsub
	101 | Active |  80 | 3.4.1.SNAPSHOT | universAAL Tutorials Context Bus PubSub - Subscriber Bundle 
	102 | Active |  80 | 3.4.1.SNAPSHOT | universAAL Tutorials Context Bus PubSub - Publisher Bundle     


when closing karaf, you should see these messages:

	karaf@uAAL>^D
	...
	STOPPING : package eu.servicemix.uaal.tutorials.contextPubSubTutorial.pubBundle
	STOPPING : package eu.servicemix.uaal.tutorials.contextPubSubTutorial.subBundle
