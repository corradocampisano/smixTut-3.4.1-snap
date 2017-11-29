
# smix.eu tutorials for uAAL 3.4.1-snapshot





## running any tutorial


first make sure you can setup the environment correctly, see next section.

once done, see readme inside any tutorial aggregator for deployment instructions. 





## general setup and build instructions


these are a prerequisite to run any of the tutorials.



### 0) get maven (3.5) and oracle JDK (8)


I installed oracle jdk here:

	/home/corrado/eclipseJee/jdk8/jdk1.8.0_112/jre


and maven here:

	/home/corrado/eclipseJee/apache-maven-3.5.0/bin/mvn


so I will setup the JAVA_HOME environment value like this:

	export JAVA_HOME=/home/corrado/eclipseJee/jdk8/jdk1.8.0_112/jre


and I will setup an alias for that maven to run with that java:

	alias mj8='JAVA_HOME=/home/corrado/eclipseJee/jdk8/jdk1.8.0_112/jre /home/corrado/eclipseJee/apache-maven-3.5.0/bin/mvn'


#### NOTE: on different OSes, your mileage may vary, up to you to get that running.



### 1) clone, compile and install the tutorial (them all)


clone tutorials repo:

	corrado@powerdesk2:~/uaal$ git clone https://github.com/corradocampisano/smixTut-3.4.1-snap.git


move into the root tutorial folder:
	
	corrado@powerdesk2:~/uaal$ cd smixTut-3.4.1-snap/


run "mvn clean install" (I used the "mj8" alias):

	corrado@powerdesk2:~/uaal/smixTut-3.4.1-snap$ mj8 clean install
	[INFO] Scanning for projects...
	...
	[INFO] ------------------------------------------------------------------------
	[INFO] Reactor Summary:
	[INFO] 
	[INFO] smixUaalTutorials pom (inherits tut.pom) ........... SUCCESS [  1.735 s]
	[INFO] contextPubSubTutorial - Aggregator ................. SUCCESS [  1.674 s]
	[INFO] contextPubSubTutorial - Publisher Bundle ........... SUCCESS [  7.580 s]
	[INFO] contextPubSubTutorial - Subscriber Bundle .......... SUCCESS [  2.534 s]
	[INFO] contextPubSubTutorial - Feature .................... SUCCESS [  1.727 s]
	[INFO] serviceUiCallerTutorial - Aggregator ............... SUCCESS [  1.151 s]
	[INFO] serviceUiCallerTutorial - Bundle ................... SUCCESS [  2.027 s]
	[INFO] serviceUiCallerTutorial - Feature .................. SUCCESS [  1.372 s]
	[INFO] ------------------------------------------------------------------------
	[INFO] BUILD SUCCESS
	[INFO] ------------------------------------------------------------------------
	[INFO] Total time: 23.536 s
	[INFO] Finished at: 2017-11-19T12:41:41+01:00
	[INFO] Final Memory: 52M/1280M
	[INFO] ------------------------------------------------------------------------



### 2) get Karaf-distro from git repo:


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



### 3) start a (clean) Karaf instance


export java 8 as JAVA_HOME:

	export JAVA_HOME=/home/corrado/eclipseJee/jdk8/jdk1.8.0_112/jre


run a clean distro:

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



	
