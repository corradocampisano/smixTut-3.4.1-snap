
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



double check:

	karaf@uAAL>list | grep -i poller
	115 | Active |  90 | 3.4.1.SNAPSHOT | filePollerTutorial - Bundle   



### 3) test with files to be polled:

I haven't added any configuration-helper at the moment, so the camel file poller will look for files in:

	/home/corrado/uaal/RUNDIR/NEW

if you put any pdf files there, then, in the karaf logs, you should see these messages:

	2017-12-05 12:51:35,867 | DEBUG | /uaal/RUNDIR/NEW | FileConsumer                     | 111 - org.apache.camel.camel-core - 2.18.4 | Took 0.001 seconds to poll: /home/corrado/uaal/RUNDIR/NEW
	2017-12-05 12:51:36,255 | WARN  | 9f3-50092f0ec2b6 | UDP                              | 66 - org.jgroups - 3.4.0.Final | JGRP000012: discarded message from different cluster mw.modules.aalspace.osgi8888 (our cluster is mw.bus.ui.osgi8888). Sender was 1c7e8197-86a7-4f27-99f3-50092f0ec2b6 (received 6 identical messages from 1c7e8197-86a7-4f27-99f3-50092f0ec2b6 in the last 68167 ms)
	2017-12-05 12:51:36,255 | WARN  | 9f3-50092f0ec2b6 | UDP                              | 66 - org.jgroups - 3.4.0.Final | JGRP000012: discarded message from different cluster mw.modules.aalspace.osgi8888 (our cluster is mw.bus.service.osgi8888). Sender was 1c7e8197-86a7-4f27-99f3-50092f0ec2b6 (received 6 identical messages from 1c7e8197-86a7-4f27-99f3-50092f0ec2b6 in the last 68167 ms)
	2017-12-05 12:51:36,255 | WARN  | 9f3-50092f0ec2b6 | UDP                              | 66 - org.jgroups - 3.4.0.Final | JGRP000012: discarded message from different cluster mw.modules.aalspace.osgi8888 (our cluster is mw.bus.context.osgi8888). Sender was 1c7e8197-86a7-4f27-99f3-50092f0ec2b6 (received 6 identical messages from 1c7e8197-86a7-4f27-99f3-50092f0ec2b6 in the last 68167 ms)
	2017-12-05 12:51:36,255 | WARN  | 9f3-50092f0ec2b6 | UDP                              | 66 - org.jgroups - 3.4.0.Final | JGRP000012: discarded message from different cluster mw.modules.aalspace.osgi8888 (our cluster is mw.brokers.control.osgi8888). Sender was 1c7e8197-86a7-4f27-99f3-50092f0ec2b6 (received 6 identical messages from 1c7e8197-86a7-4f27-99f3-50092f0ec2b6 in the last 68167 ms)
	2017-12-05 12:51:36,367 | DEBUG | /uaal/RUNDIR/NEW | FileConsumer                     | 111 - org.apache.camel.camel-core - 2.18.4 | Took 0.000 seconds to poll: /home/corrado/uaal/RUNDIR/NEW
	2017-12-05 12:51:36,867 | DEBUG | /uaal/RUNDIR/NEW | FileConsumer                     | 111 - org.apache.camel.camel-core - 2.18.4 | Took 0.000 seconds to poll: /home/corrado/uaal/RUNDIR/NEW
	2017-12-05 12:51:37,368 | DEBUG | /uaal/RUNDIR/NEW | FileConsumer                     | 111 - org.apache.camel.camel-core - 2.18.4 | Took 0.001 seconds to poll: /home/corrado/uaal/RUNDIR/NEW
	2017-12-05 12:51:37,368 | DEBUG | /uaal/RUNDIR/NEW | FileConsumer                     | 111 - org.apache.camel.camel-core - 2.18.4 | Total 1 files to consume
	2017-12-05 12:51:37,368 | DEBUG | /uaal/RUNDIR/NEW | FileConsumer                     | 111 - org.apache.camel.camel-core - 2.18.4 | About to process file: GenericFile[/home/corrado/uaal/RUNDIR/NEW/sc_tecnica_pellet.pdf] using exchange: Exchange[]
	2017-12-05 12:51:37,369 | DEBUG | /uaal/RUNDIR/NEW | FilterProcessor                  | 111 - org.apache.camel.camel-core - 2.18.4 | Filter matches: true for exchange: Exchange[ID-powerdesk2-58452-1512474385669-0-10]
	2017-12-05 12:51:37,369 | INFO  | /uaal/RUNDIR/NEW | PdfProcessor                     | 115 - filePollerTutorial.bundle - 3.4.1.SNAPSHOT | There is an exchange going on.
	2017-12-05 12:51:37,369 | INFO  | /uaal/RUNDIR/NEW | PdfProcessor                     | 115 - filePollerTutorial.bundle - 3.4.1.SNAPSHOT | file:///home/corrado/uaal/RUNDIR/NEW?probeContentType=true
	2017-12-05 12:51:37,369 | INFO  | /uaal/RUNDIR/NEW | PdfProcessor                     | 115 - filePollerTutorial.bundle - 3.4.1.SNAPSHOT | sc_tecnica_pellet.pdf
	2017-12-05 12:51:37,369 | INFO  | /uaal/RUNDIR/NEW | PdfProcessor                     | 115 - filePollerTutorial.bundle - 3.4.1.SNAPSHOT | GenericFile[/home/corrado/uaal/RUNDIR/NEW/sc_tecnica_pellet.pdf]
	SENDING EVENT : urn:org.universAAL.middleware.context.rdf:ContextEvent#_:7f000101d06fb293:959
	2017-12-05 12:51:37,369 | INFO  | /uaal/RUNDIR/NEW | PdfProcessor                     | 115 - filePollerTutorial.bundle - 3.4.1.SNAPSHOT | class org.apache.camel.component.file.GenericFile
	2017-12-05 12:51:37,369 | INFO  | /uaal/RUNDIR/NEW | PdfProcessor                     | 115 - filePollerTutorial.bundle - 3.4.1.SNAPSHOT | FILE CONTENT TYPE: application/pdf
	2017-12-05 12:51:37,369 | DEBUG | /uaal/RUNDIR/NEW | bundle                           | 75 - mw.container.osgi - 3.4.1.SNAPSHOT | AccessControl->checkPermission(): Permission denied for Matchable: ContextEvent: urn:org.universAAL.middleware.context.rdf:ContextEvent#_:7f000101d06fb293:959
	2017-12-05 12:51:37,369 | DEBUG | /uaal/RUNDIR/NEW | SendProcessor                    | 111 - org.apache.camel.camel-core - 2.18.4 | >>>> file:///home/corrado/uaal/RUNDIR/ARK Exchange[ID-powerdesk2-58452-1512474385669-0-10]
	2017-12-05 12:51:37,369 | DEBUG | /uaal/RUNDIR/NEW | FileOperations                   | 111 - org.apache.camel.camel-core - 2.18.4 | Using FileChannel to write file: /home/corrado/uaal/RUNDIR/ARK/sc_tecnica_pellet.pdf
	2017-12-05 12:51:37,370 | DEBUG | Strategy Handler | osgi                             | 75 - mw.container.osgi - 3.4.1.SNAPSHOT | ContextStrategy->notifyAllLocalSubscribers(): No subscribers registered for received context event:
	
	@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
	@prefix ns: <http://www.servicemix.eu/docArchOntology.owl#> .
	@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .
	@prefix : <http://ontology.universAAL.org/Context.owl#> .
	<urn:org.universAAL.middleware.context.rdf:ContextEvent#_:7f000101d06fb293:959> :hasProvider <urn:eu.servicemix.uaal.tutorials.filePollerTutorial:pubBundle> ;
	  a :ContextEvent ;
	  rdf:subject <urn:eu.servicemix.uaal.tutorials.filePollerTutorial:MokedDocumentHashMap> ;
	  :hasTimestamp "1512474697369"^^xsd:long ;
	  rdf:object "2017-51-05_12:51:37"^^xsd:string .
	<urn:eu.servicemix.uaal.tutorials.filePollerTutorial:pubBundle> a :ContextProvider ;
	  :hasType :controller .
	:controller a :ContextProviderType .
	<urn:eu.servicemix.uaal.tutorials.filePollerTutorial:MokedDocumentHashMap> a ns:Document ;
	  ns:isLocatedAt "nowhere"^^xsd:string ;
	  ns:hasMd5Hash "123456789012345678900123456789012345678901234567890012345678901234"^^xsd:string ;
	  ns:hasMimeType "pdf"^^xsd:string ;
	  <mockDocImportedOn> "2017-51-05_12:51:37"^^xsd:string ;
	  ns:hasBeenImportedOn "2017-51-05_12:51:37"^^xsd:string .
	
	2017-12-05 12:51:37,371 | INFO  | Thread-3896      | osgi                             | 75 - mw.container.osgi - 3.4.1.SNAPSHOT | BroadcastExecutor->run()(): Preparing to BROADCAST the message {"channelNames":["mw.bus.context.osgi"],"sender":{"URI_PREFIX":"urn:uuid:","peerID":"1c7e8197-86a7-4f27-99f3-50092f0ec2b6","role":"COORDINATOR","platform":"","container":"","os":"Linux - 3.16.0-4-amd64- amd64"},"content":"\u003cuAAL:BusMessage\u003e\n  \u003cuAAL:BusMessage#id\u003e5\u003c/uAAL:BusMessage#id\u003e\n  \u003cuAAL:BusMessage#type\u003eevent\u003c/uAAL:BusMessage#type\u003e\n  \u003cuAAL:BusMessage#content\u003e\n\n@prefix rdf: \u003chttp://www.w3.org/1999/02/22-rdf-syntax-ns#\u003e .\n@prefix ns: \u003chttp://www.servicemix.eu/docArchOntology.owl#\u003e .\n@prefix xsd: \u003chttp://www.w3.org/2001/XMLSchema#\u003e .\n@prefix : \u003chttp://ontology.universAAL.org/Context.owl#\u003e .\n\u003curn:org.universAAL.middleware.context.rdf:ContextEvent#_:7f000101d06fb293:959\u003e :hasProvider \u003curn:eu.servicemix.uaal.tutorials.filePollerTutorial:pubBundle\u003e ;\n  a :ContextEvent ;\n  rdf:subject \u003curn:eu.servicemix.uaal.tutorials.filePollerTutorial:MokedDocumentHashMap\u003e ;\n  :hasTimestamp \"1512474697369\"^^xsd:long ;\n  rdf:object \"2017-51-05_12:51:37\"^^xsd:string .\n\u003curn:eu.servicemix.uaal.tutorials.filePollerTutorial:pubBundle\u003e a :ContextProvider ;\n  :hasType :controller .\n:controller a :ContextProviderType .\n\u003curn:eu.servicemix.uaal.tutorials.filePollerTutorial:MokedDocumentHashMap\u003e a ns:Document ;\n  ns:isLocatedAt \"nowhere\"^^xsd:string ;\n  ns:hasMd5Hash \"123456789012345678900123456789012345678901234567890012345678901234\"^^xsd:string ;\n  ns:hasMimeType \"pdf\"^^xsd:string ;\n  \u003cmockDocImportedOn\u003e \"2017-51-05_12:51:37\"^^xsd:string ;\n  ns:hasBeenImportedOn \"2017-51-05_12:51:37\"^^xsd:string .\n\n    \u003c/uAAL:BusMessage#content\u003e\n  \u003cuAAL:BusMessage#sender\u003e\nPeer ID: 1c7e8197-86a7-4f27-99f3-50092f0ec2b6 - Peer Role: COORDINATOR\n    \u003c/uAAL:BusMessage#sender\u003e\n  \u003cuAAL:BusMessage#brokerName\u003e\nmw.bus.context.osgi\n    \u003c/uAAL:BusMessage#brokerName\u003e\n\u003c/uAAL:BusMessage\u003e"}
	2017-12-05 12:51:37,476 | DEBUG | /uaal/RUNDIR/NEW | GenericFileProducer              | 111 - org.apache.camel.camel-core - 2.18.4 | Wrote [/home/corrado/uaal/RUNDIR/ARK/sc_tecnica_pellet.pdf] to [file:///home/corrado/uaal/RUNDIR/ARK]
	2017-12-05 12:51:37,476 | DEBUG | /uaal/RUNDIR/NEW | GenericFileOnCompletion          | 111 - org.apache.camel.camel-core - 2.18.4 | Done processing file: GenericFile[/home/corrado/uaal/RUNDIR/NEW/sc_tecnica_pellet.pdf] using exchange: Exchange[ID-powerdesk2-58452-1512474385669-0-10]
	2017-12-05 12:51:37,477 | DEBUG | /uaal/RUNDIR/NEW | FileUtil                         | 111 - org.apache.camel.camel-core - 2.18.4 | Retrying attempt 0 to delete file: /home/corrado/uaal/RUNDIR/NEW/.camel/sc_tecnica_pellet.pdf
	2017-12-05 12:51:37,477 | DEBUG | /uaal/RUNDIR/NEW | FileUtil                         | 111 - org.apache.camel.camel-core - 2.18.4 | Tried 1 to delete file: /home/corrado/uaal/RUNDIR/NEW/.camel/sc_tecnica_pellet.pdf with result: true
	2017-12-05 12:51:37,477 | DEBUG | /uaal/RUNDIR/NEW | GenericFileRenameProcessStrategy | 111 - org.apache.camel.camel-core - 2.18.4 | Renaming file: GenericFile[/home/corrado/uaal/RUNDIR/NEW/sc_tecnica_pellet.pdf] to: GenericFile[/home/corrado/uaal/RUNDIR/NEW/.camel/sc_tecnica_pellet.pdf]
	2017-12-05 12:51:37,477 | DEBUG | /uaal/RUNDIR/NEW | FileUtil                         | 111 - org.apache.camel.camel-core - 2.18.4 | Tried 1 to rename file: /home/corrado/uaal/RUNDIR/NEW/sc_tecnica_pellet.pdf to: /home/corrado/uaal/RUNDIR/NEW/.camel/sc_tecnica_pellet.pdf with result: true
	2017-12-05 12:51:37,477 | DEBUG | /uaal/RUNDIR/NEW | FileUtil                         | 111 - org.apache.camel.camel-core - 2.18.4 | Retrying attempt 0 to delete file: /home/corrado/uaal/RUNDIR/NEW/sc_tecnica_pellet.pdf.camelLock
	2017-12-05 12:51:37,477 | DEBUG | /uaal/RUNDIR/NEW | FileUtil                         | 111 - org.apache.camel.camel-core - 2.18.4 | Tried 1 to delete file: /home/corrado/uaal/RUNDIR/NEW/sc_tecnica_pellet.pdf.camelLock with result: true
	2017-12-05 12:51:37,978 | DEBUG | /uaal/RUNDIR/NEW | FileConsumer                     | 111 - org.apache.camel.camel-core - 2.18.4 | Took 0.000 seconds to poll: /home/corrado/uaal/RUNDIR/NEW
		
