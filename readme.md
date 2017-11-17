



### 1) get Karaf-distro from git repo:


use git clone:

	corrado@powerdesk2:~/uaal$ git clone https://github.com/universAAL/distro.karaf.git


double check:

	corrado@powerdesk2:~/uaal/distro.karaf$ git status
	On branch karaf3.0.5
	Your branch is up-to-date with 'origin/karaf3.0.5'.
	Changes not staged for commit:
	  (use "git add <file>..." to update what will be committed)
	  (use "git checkout -- <file>..." to discard changes in working directory)
	
		modified:   bin/karaf
	
	no changes added to commit (use "git add" and/or "git commit -a")



the only change is making karaf script executable:

	corrado@powerdesk2:~/uaal/distro.karaf$ chmod u+x bin/karaf



### 2) start a (clean) Karaf instance

	corrado@powerdesk2:~/uaal/distro.karaf$ bin/karaf clean
	
	                                                  ':oxxd:                     
	                                             .,cdkOkxdxkO,                    
	                                         .;oxdc;'.      c.                    
	                                      'cdo;.                                  
	                                   'cc;.                                      
	                                .::'                                          
	                             .''.                                             
	                         .::ol                     'dxd.          ;xxxxxd.    
	                      .l0K0NMMx                 .:0WMMMX'        ;WMMMMM:     
	 'xkkl     :o      'oxo:kWMMMMM0.           .:oo;dMMMMMMWl      ;WMMMMMo      
	,NMMK.    oN'   ;dxl.    dMMMMMMX;       .ckx;    ,KMMMMMMx    .NMMMMMo       
	0MMW'   .xK'.:kWKc;;;;;,  cWMMMMMMo   'o0XOc;;;;;. .0MMMMMMX'  ,MMMMMWl;;;;;;'
	,oddolllc' ;oxxxxxxxxxxc   .dxxxxxx::kxcldddddxxd.   ldxxxxdo   'lddxxxxxxxxd,
	                               .,cdo;.                                        
	                   l.   ..';coxd:'                                            
	                  .kOOkOOkdl;.                                                
	                   'odoc,.                                                    
	
	  universAAL (3.4.0)
	
	Hit '<tab>' for a list of available commands
	and '[cmd] --help' for help on a specific command.
	Hit '<ctrl-d>' or 'osgi:shutdown' to shutdown Karaf.
	
	karaf@uAAL>
	GMS: address=b79d4a04-86b3-4bee-bf75-f99ac166e3a4, cluster=mw.modules.aalspace.osgi8888, physical address=192.168.1.100:56029
	
	GMS: address=b79d4a04-86b3-4bee-bf75-f99ac166e3a4, cluster=mw.bus.ui.osgi8888, physical address=192.168.1.100:58730
	
	GMS: address=b79d4a04-86b3-4bee-bf75-f99ac166e3a4, cluster=mw.brokers.control.osgi8888, physical address=192.168.1.100:38869
	
	GMS: address=b79d4a04-86b3-4bee-bf75-f99ac166e3a4, cluster=mw.bus.context.osgi8888, physical address=192.168.1.100:34096
	
	GMS: address=b79d4a04-86b3-4bee-bf75-f99ac166e3a4, cluster=mw.bus.service.osgi8888, physical address=192.168.1.100:47434
	
	karaf@uAAL>



### 3) clone, compile and install the tutorial (this one)

	corrado@powerdesk2:~/uaal$ https://github.com/corradocampisano/smixTut-3.4.1-snap.git
	
	corrado@powerdesk2:~/uaal$ cd smixTut-3.4.1-snap
	
	corrado@powerdesk2:~/uaal/smixTut-3.4.1-snap$ mj8 clean install
	[INFO] Scanning for projects...
	...
	[INFO] 
	[INFO] Reactor Build Order:
	[INFO] 
	[INFO] Tutorial aggregator (bundle and feature)
	[INFO] universAAL Tutorials Context Bus Publisher - Bundle
	[INFO] universAAL Tutorials Context Bus Publisher - Feature
	... 
	[INFO] Installing /home/corrado/uaal/smixTut-3.4.1-snap/feature/target/features/features.xml to 
		/home/corrado/.m2/repository/eu/servicemix/uaal/tutorials/contextPublisherFeature/3.4.1-SNAPSHOT/contextPublisherFeature-3.4.1-SNAPSHOT-features.xml
	[INFO] 
	[INFO] Reactor Summary:
	[INFO] 
	[INFO] Tutorial aggregator (bundle and feature) ........... SUCCESS [  0.145 s]
	[INFO] universAAL Tutorials Context Bus Publisher - Bundle  SUCCESS [ 15.803 s]
	[INFO] universAAL Tutorials Context Bus Publisher - Feature SUCCESS [  0.481 s]
	[INFO] 
	[INFO] BUILD SUCCESS
	[INFO] 
	[INFO] Total time: 16.782 s
	[INFO] Finished at: 2017-11-17T08:53:32+01:00
	[INFO] Final Memory: 27M/390M
	[INFO] 



### 4) Add feature repository:

since the feature got installed locally, we can add the local (mvn:) maven repo:

    [INFO] Installing /home/corrado/uaal/smixTut-3.4.1-snap/feature/target/features/features.xml to 
    	/home/corrado/.m2/repository/eu/servicemix/uaal/tutorials/contextPublisherFeature/3.4.1-SNAPSHOT/contextPublisherFeature-3.4.1-SNAPSHOT-features.xml

do this in Karaf:

    karaf@uAAL>feature:repo-add mvn:eu.servicemix.uaal.tutorials/contextPublisherFeature/3.4.1-SNAPSHOT/xml/features 
    Adding feature url mvn:eu.servicemix.uaal.tutorials/contextPublisherFeature/3.4.1-SNAPSHOT/xml/features


double check:

    karaf@uAAL>feature:repo-list 
    Repository                        | URL
    ------------------------------------------------------------------------------------------------------------------------
    standard-3.0.8                    | mvn:org.apache.karaf.features/standard/3.0.8/xml/features
    ...
    uaalTutorials-Feature             | mvn:eu.servicemix.uaal.tutorials/contextPublisherFeature/3.4.1-SNAPSHOT/xml/features
    ...


### 5) install feature:

	karaf@uAAL>feature:install -v uaalTutorials-contextPublisher
	Installing feature uaalTutorials-contextPublisher 3.4.1-SNAPSHOT
	Installing feature uAAL-Ont.Device 3.4.1-SNAPSHOT
	Installing feature uAAL-Ont.Profile 3.4.1-SNAPSHOT
	Installing feature uAAL-Ont.PhWorld 3.4.1-SNAPSHOT
	Found installed feature uAAL-MW 3.4.1-SNAPSHOT
	Installing bundle wrap:mvn:jp.go.ipa/jgcl/1.0
	Installing bundle mvn:org.universAAL.ontology/ont.phWorld/3.4.1-SNAPSHOT
	Installing bundle mvn:org.universAAL.ontology/ont.profile/3.4.1-SNAPSHOT
	Installing feature uAAL-Ont.Measurement 3.4.1-SNAPSHOT
	Installing feature uAAL-Ont.Unit 3.4.1-SNAPSHOT
	Found installed feature uAAL-MW 3.4.1-SNAPSHOT
	Installing bundle mvn:org.universAAL.ontology/ont.unit/3.4.1-SNAPSHOT
	Installing bundle mvn:org.universAAL.ontology/ont.measurement/3.4.1-SNAPSHOT
	Installing bundle mvn:org.universAAL.ontology/ont.device/3.4.1-SNAPSHOT
	Installing bundle mvn:eu.servicemix.uaal.tutorials/contextPublisherBundle/3.4.1-SNAPSHOT


double check:

	karaf@uAAL>list | grep -i publisher
	101 | Active | 100 | 3.4.1.SNAPSHOT | universAAL Tutorials Context Bus Publisher - Bundle   

