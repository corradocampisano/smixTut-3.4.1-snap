
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

	[INFO] Installing /home/corrado/uaal/smixTut-3.4.1-snap/serviceUiCallerTutorial/feature/target/features/features.xml to /home/corrado/.m2/repository/eu/servicemix/uaal/tutorials/serviceUiCallerTutorial.feature/3.4.1-SNAPSHOT/serviceUiCallerTutorial.feature-3.4.1-SNAPSHOT-features.xml

 we can add the local (mvn: = maven local repo) feature-repo in Karaf:

	karaf@uAAL>feature:repo-add mvn:eu.servicemix.uaal.tutorials/serviceUiCallerTutorial.feature/3.4.1-SNAPSHOT/xml/features
	Adding feature url mvn:eu.servicemix.uaal.tutorials/serviceUiCallerTutorial.feature/3.4.1-SNAPSHOT/xml/features

double check:

	karaf@uAAL>feature:repo-list | grep -i uicaller
	uaalTutorials-Feature             | mvn:eu.servicemix.uaal.tutorials/serviceUiCallerTutorial.feature/3.4.1-SNAPSHOT/xml/features



### 2) install feature:

add "-v" (verbose) to let it show more:

	karaf@uAAL>feature:install -v uaalTuts-serviceUiCallerTutorial
	Installing feature uaalTuts-serviceUiCallerTutorial 3.4.1-SNAPSHOT
	Installing feature uAAL-Ont.Profile 3.4.1-SNAPSHOT
	Installing feature uAAL-Ont.PhWorld 3.4.1-SNAPSHOT
	Found installed feature uAAL-MW 3.4.1-SNAPSHOT
	Installing bundle wrap:mvn:jp.go.ipa/jgcl/1.0
	Installing bundle mvn:org.universAAL.ontology/ont.phWorld/3.4.1-SNAPSHOT
	Installing bundle mvn:org.universAAL.ontology/ont.profile/3.4.1-SNAPSHOT
	Installing bundle mvn:eu.servicemix.uaal.tutorials/serviceUiCallerTutorial.bundle/3.4.1-SNAPSHOT
	STARTING : package eu.servicemix.uaal.tutorials.serviceUiCallerTutorial.bundle
		

If you had some other tutorial installed, you may get a different message:	

	karaf@uAAL>feature:install -v uaalTuts-serviceUiCallerTutorial
	Installing feature uaalTuts-serviceUiCallerTutorial 3.4.1-SNAPSHOT
	Found installed feature uAAL-Ont.Profile 3.4.1-SNAPSHOT
	Installing bundle mvn:eu.servicemix.uaal.tutorials/serviceUiCallerTutorial.bundle/3.4.1-SNAPSHOT
	STARTING : package eu.servicemix.uaal.tutorials.serviceUiCallerTutorial.bundle


double check:

	karaf@uAAL>list | grep -i uicaller
	103 | Active |  80 | 3.4.1.SNAPSHOT | serviceUiCallerTutorial - Bundle      


when closing karaf, you should see these messages:

	karaf@uAAL>^D
	...
	STOPPING : package eu.servicemix.uaal.tutorials.serviceUiCallerTutorial.bundle

