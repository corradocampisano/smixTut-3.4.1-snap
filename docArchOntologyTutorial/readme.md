
# docArchOntologyTutorial for uAAL 3.4.1-snapshot





## general setup and build instructions


see parent (root) project readme for instructions.

those are a prerequisite to run any of the tutorials.





## running this tutorial


first make sure you can setup the environment correctly, see previous section.

once done, see the following for deployment instructions. 



TO BE UPDATED BELOW !!!



### 1) Add feature repository:

since the feature got installed locally (mvn: = maven local repo):

	[INFO] Installing /home/corrado/uaal/smixTut-3.4.1-snap/docArchOntologyTutorial/feature/target/features/features.xml to /home/corrado/.m2/repository/eu/servicemix/uaal/tutorials/docArchOntologyTutorial.feature/3.4.1-SNAPSHOT/docArchOntologyTutorial.feature-3.4.1-SNAPSHOT-features.xml


 we can add the local (mvn: = maven local repo) feature-repo in Karaf:

	karaf@uAAL>feature:repo-add mvn:eu.servicemix.uaal.tutorials/docArchOntologyTutorial.feature/3.4.1-SNAPSHOT/xml/features
	Adding feature url mvn:eu.servicemix.uaal.tutorials/docArchOntologyTutorial.feature/3.4.1-SNAPSHOT/xml/features


double check:

	karaf@uAAL>feature:repo-list | grep -i docarch
	uaalTutorials-Feature             | mvn:eu.servicemix.uaal.tutorials/docArchOntologyTutorial.feature/3.4.1-SNAPSHOT/xml/features



### 2) install feature:

add "-v" (verbose) to let it show more:

	
	karaf@uAAL>feature:install -v uaalTuts-docArchOntologyTutorial
	Installing feature uaalTuts-docArchOntologyTutorial 3.4.1-SNAPSHOT
	Installing bundle mvn:eu.servicemix.uaal.tutorials/docArchOntologyTutorial.bundle/3.4.1-SNAPSHOT
	Error executing command: Can't install feature uaalTuts-docArchOntologyTutorial/0.0.0: 	
	Could not start bundle mvn:eu.servicemix.uaal.tutorials/docArchOntologyTutorial.bundle/3.4.1-SNAPSHOT in feature(s) uaalTuts-docArchOntologyTutorial-3.4.1-SNAPSHOT: Unresolved constraint in bundle docArchOntologyTutorial.bundle [99]: Unable to resolve 99.0: missing requirement [99.0] osgi.wiring.package; (&(osgi.wiring.package=org.universAAL.ontology.device)(version>=3.4.0)(!(version>=4.0.0)))
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



double check:

	karaf@uAAL>list | grep -i docarch
	103 | Active |  80 | 3.4.1.SNAPSHOT | serviceUiCallerTutorial - Bundle      


when closing karaf, you should see these messages:

	karaf@uAAL>^D
	...
	STOPPING : package eu.servicemix.uaal.tutorials.serviceUiCallerTutorial.bundle

