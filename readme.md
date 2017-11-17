







1) get Karaf-distro from git repo:


use git clone:

`git clone https://github.com/universAAL/distro.karaf.git`


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

`chmod u+x bin/karaf`



2) start a (clean) Karaf instance


    corrado@powerdesk2:~/uaal/distro.karaf$ bin/karaf --clean
    
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
    -------------------------------------------------------------------
    GMS: address=b79d4a04-86b3-4bee-bf75-f99ac166e3a4, cluster=mw.modules.aalspace.osgi8888, physical address=192.168.1.100:56029
    -------------------------------------------------------------------
    
    -------------------------------------------------------------------
    GMS: address=b79d4a04-86b3-4bee-bf75-f99ac166e3a4, cluster=mw.bus.ui.osgi8888, physical address=192.168.1.100:58730
    -------------------------------------------------------------------
    
    -------------------------------------------------------------------
    GMS: address=b79d4a04-86b3-4bee-bf75-f99ac166e3a4, cluster=mw.brokers.control.osgi8888, physical address=192.168.1.100:38869
    -------------------------------------------------------------------
    
    -------------------------------------------------------------------
    GMS: address=b79d4a04-86b3-4bee-bf75-f99ac166e3a4, cluster=mw.bus.context.osgi8888, physical address=192.168.1.100:34096
    -------------------------------------------------------------------
    
    -------------------------------------------------------------------
    GMS: address=b79d4a04-86b3-4bee-bf75-f99ac166e3a4, cluster=mw.bus.service.osgi8888, physical address=192.168.1.100:47434
    -------------------------------------------------------------------
    
    karaf@uAAL>



3) compile and install the tutorial (this one)


    corrado@powerdesk2:~/uaal/smixTut-3.4.1-snap$ mj8 clean install
    [INFO] Scanning for projects...
    ...
    [INFO] ------------------------------------------------------------------------
    [INFO] Reactor Build Order:
    [INFO] 
    [INFO] Tutorial aggregator (bundle and feature)
    [INFO] universAAL Tutorials Context Bus Publisher - Bundle
    [INFO] universAAL Tutorials Context Bus Publisher - Feature
    ... 
    [INFO] Installing /home/corrado/uaal/smixTut-3.4.1-snap/feature/target/features/features.xml to /home/corrado/.m2/repository/eu/servicemix/uaal/tutorials/contextPublisherFeature/3.4.1-SNAPSHOT/contextPublisherFeature-3.4.1-SNAPSHOT-features.xml
    [INFO] ------------------------------------------------------------------------
    [INFO] Reactor Summary:
    [INFO] 
    [INFO] Tutorial aggregator (bundle and feature) ........... SUCCESS [  0.145 s]
    [INFO] universAAL Tutorials Context Bus Publisher - Bundle  SUCCESS [ 15.803 s]
    [INFO] universAAL Tutorials Context Bus Publisher - Feature SUCCESS [  0.481 s]
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 16.782 s
    [INFO] Finished at: 2017-11-17T08:53:32+01:00
    [INFO] Final Memory: 27M/390M
    [INFO] ------------------------------------------------------------------------



4) Add feature repository:

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


2) try install feature:

clear logs before doing it:

    karaf@uAAL>log:clear 
    karaf@uAAL>feature:install uaalTutorials-contextPublisher
    Error executing command: Can't install feature uaalTutorials-contextPublisher/0.0.0
    Could not start bundle mvn:org.universAAL.ontology/ont.profile/3.4.1-SNAPSHOT in feature(s) uAAL-Ont.Profile-3.4.1-SNAPSHOT: 
    	Unresolved constraint in bundle ont.profile [131]: Unable to resolve 131.0: missing requirement [131.0] 
	    osgi.wiring.package; (&(osgi.wiring.package=org.universAAL.ontology.location)(version>=3.4.0)(!(version>=4.0.0)))



report logs:

    karaf@uAAL>log:display 
    2017-11-17 09:00:33,448 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 >> "User-Agent: Aether[\r][\n]"
    2017-11-17 09:00:33,448 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 >> "Host: repository.apache.org[\r][\n]"
    2017-11-17 09:00:33,448 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 >> "Connection: Keep-Alive[\r][\n]"
    2017-11-17 09:00:33,448 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 >> "[\r][\n]"
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | HttpClientConnectionOperator     | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Connection established 192.168.1.100:41994<->54.173.252.242:443
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | MainClientExec                   | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Executing request GET /content/repositories/snapshots/org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml HTTP/1.1
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | MainClientExec                   | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Target auth state: UNCHALLENGED
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | MainClientExec                   | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Proxy auth state: UNCHALLENGED
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> GET /content/repositories/snapshots/org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml HTTP/1.1
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> Cache-control: no-cache
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> Cache-store: no-store
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> Pragma: no-cache
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> Expires: 0
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> Accept-Encoding: gzip
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> User-Agent: Aether
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> User-Agent: Aether
    2017-11-17 09:00:33,564 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> Host: oss.sonatype.org
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> Connection: Keep-Alive
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> "GET /content/repositories/snapshots/org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml HTTP/1.1[\r][\n]"
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> "Cache-control: no-cache[\r][\n]"
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> "Cache-store: no-store[\r][\n]"
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> "Pragma: no-cache[\r][\n]"
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> "Expires: 0[\r][\n]"
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> "Accept-Encoding: gzip[\r][\n]"
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> "User-Agent: Aether[\r][\n]"
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> "User-Agent: Aether[\r][\n]"
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> "Host: oss.sonatype.org[\r][\n]"
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> "Connection: Keep-Alive[\r][\n]"
    2017-11-17 09:00:33,565 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 >> "[\r][\n]"
    2017-11-17 09:00:33,568 | DEBUG | ataResolver-12-2 | HttpClientConnectionOperator     | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Connection established 192.168.1.100:41996<->54.173.252.242:443
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | MainClientExec                   | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Executing request GET /content/repositories/ops4j-snapshots/org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml HTTP/1.1
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | MainClientExec                   | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Target auth state: UNCHALLENGED
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | MainClientExec                   | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Proxy auth state: UNCHALLENGED
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> GET /content/repositories/ops4j-snapshots/org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml HTTP/1.1
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> Cache-control: no-cache
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> Cache-store: no-store
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> Pragma: no-cache
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> Expires: 0
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> Accept-Encoding: gzip
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> User-Agent: Aether
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> User-Agent: Aether
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> Host: oss.sonatype.org
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> Connection: Keep-Alive
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> "GET /content/repositories/ops4j-snapshots/org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml HTTP/1.1[\r][\n]"
    2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> "Cache-control: no-cache[\r][\n]"
	2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> "Cache-store: no-store[\r][\n]"
	2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> "Pragma: no-cache[\r][\n]"
	2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> "Expires: 0[\r][\n]"
	2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> "Accept-Encoding: gzip[\r][\n]"
	2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> "User-Agent: Aether[\r][\n]"
	2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> "User-Agent: Aether[\r][\n]"
	2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> "Host: oss.sonatype.org[\r][\n]"
	2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> "Connection: Keep-Alive[\r][\n]"
	2017-11-17 09:00:33,569 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 >> "[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "HTTP/1.1 404 Not Found[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "Date: Fri, 17 Nov 2017 07:49:55 GMT[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "Server: Nexus/2.13.0-01[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "X-Frame-Options: SAMEORIGIN[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "X-Content-Type-Options: nosniff[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "Accept-Ranges: bytes[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "Content-Type: text/html[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "Via: 1.1 repository.apache.org[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "Connection: close[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "Transfer-Encoding: chunked[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "3ec[\r][\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "<html>[\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "  <head>[\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "    <title>404 - Path /org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml not found in group repository &quot;Snapshots&quot; [id=snapshots-group].</title>[\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>[\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "[\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "    <link rel="icon" type="image/png" href="https://repository.apache.org/favicon.png">[\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "    <!--[if IE]>[\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "    <link rel="SHORTCUT ICON" href="https://repository.apache.org/favicon.ico"/>[\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "    <![endif]-->[\n]"
	2017-11-17 09:00:33,590 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "[\n]"
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "    <link rel="stylesheet" href="https://repository.apache.org/static/css/Sonatype-content.css?2.13.0-01" type="text/css" media="screen" title="no title" charset="utf-8">[\n]"
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "  </head>[\n]"
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "  <body>[\n]"
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "    <h1>404 - Path /org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml not found in group repository &quot;Snapshots&quot; [id=snapshots-group].</h1>[\n]"
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "    <p>Path /org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml not found in group repository &quot;Snapshots&quot; [id=snapshots-group].</p>[\n]"
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "  </body>[\n]"
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "</html>[\n]"
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << "[\r][\n]"
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << HTTP/1.1 404 Not Found
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << Date: Fri, 17 Nov 2017 07:49:55 GMT
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << Server: Nexus/2.13.0-01
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << X-Frame-Options: SAMEORIGIN
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << X-Content-Type-Options: nosniff
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << Accept-Ranges: bytes
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << Content-Type: text/html
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << Via: 1.1 repository.apache.org
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << Connection: close
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38 << Transfer-Encoding: chunked
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | faultManagedHttpClientConnection | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38: Shutdown connection
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | MainClientExec                   | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Connection discarded
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | faultManagedHttpClientConnection | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-38: Close connection
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | olingHttpClientConnectionManager | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Connection released: [id: 38][route: {}->http://repository.apache.org:80][total kept alive: 1; route allocated: 0 of 20; total allocated: 3 of 40]
	2017-11-17 09:00:33,591 | DEBUG | ataResolver-12-0 | DefaultUpdateCheckManager        | 1 - org.ops4j.pax.url.mvn - 2.4.7 | Writing tracking file /home/corrado/.m2/repository/org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/resolver-status.properties
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "HTTP/1.1 404 Not Found[\r][\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "Accept-Ranges: bytes[\r][\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "Content-Type: text/html[\r][\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "Date: Fri, 17 Nov 2017 07:49:55 GMT[\r][\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "Server: nginx[\r][\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "X-Content-Type-Options: nosniff[\r][\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "X-Frame-Options: SAMEORIGIN[\r][\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "transfer-encoding: chunked[\r][\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "Connection: keep-alive[\r][\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "[\r][\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "3e9[\r][\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "<html>[\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "  <head>[\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "    <title>404 - Path /org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml not found in local storage of repository &quot;Snapshots&quot; [id=snapshots]</title>[\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>[\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "[\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "    <link rel="icon" type="image/png" href="https://oss.sonatype.org/favicon.png">[\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "    <!--[if IE]>[\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "    <link rel="SHORTCUT ICON" href="https://oss.sonatype.org/favicon.ico"/>[\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "    <![endif]-->[\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "[\n]"
	2017-11-17 09:00:33,712 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "    <link rel="stylesheet" href="https://oss.sonatype.org/static/css/Sonatype-content.css?2.14.5-02" type="text/css" media="screen" title="no title" charset="utf-8">[\n]"
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "  </head>[\n]"
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "  <body>[\n]"
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "    <h1>404 - Path /org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml not found in local storage of repository &quot;Snapshots&quot; [id=snapshots]</h1>[\n]"
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "    <p>Path /org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml not found in local storage of repository &quot;Snapshots&quot; [id=snapshots]</p>[\n]"
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "  </body>[\n]"
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "</html>[\n]"
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << "[\r][\n]"
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << HTTP/1.1 404 Not Found
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << Accept-Ranges: bytes
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << Content-Type: text/html
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << Date: Fri, 17 Nov 2017 07:49:55 GMT
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << Server: nginx
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << X-Content-Type-Options: nosniff
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << X-Frame-Options: SAMEORIGIN
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << transfer-encoding: chunked
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39 << Connection: keep-alive
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | MainClientExec                   | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Connection can be kept alive indefinitely
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | faultManagedHttpClientConnection | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39: Shutdown connection
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | MainClientExec                   | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Connection discarded
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | faultManagedHttpClientConnection | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-39: Close connection
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | olingHttpClientConnectionManager | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Connection released: [id: 39][route: {s}->https://oss.sonatype.org:443][total kept alive: 1; route allocated: 1 of 20; total allocated: 2 of 40]
	2017-11-17 09:00:33,713 | DEBUG | ataResolver-12-1 | DefaultUpdateCheckManager        | 1 - org.ops4j.pax.url.mvn - 2.4.7 | Writing tracking file /home/corrado/.m2/repository/org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/resolver-status.properties
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "HTTP/1.1 404 Not Found[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "Accept-Ranges: bytes[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "Content-Type: text/html[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "Date: Fri, 17 Nov 2017 07:49:55 GMT[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "Server: nginx[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "X-Content-Type-Options: nosniff[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "X-Frame-Options: SAMEORIGIN[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "transfer-encoding: chunked[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "Connection: keep-alive[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "40d[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "<html>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "  <head>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "    <title>404 - Path /org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml not found in local storage of repository &quot;Ops4j Snapshots&quot; [id=ops4j-snapshots]</title>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "    <link rel="icon" type="image/png" href="https://oss.sonatype.org/favicon.png">[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "    <!--[if IE]>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "    <link rel="SHORTCUT ICON" href="https://oss.sonatype.org/favicon.ico"/>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "    <![endif]-->[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "    <link rel="stylesheet" href="https://oss.sonatype.org/static/css/Sonatype-content.css?2.14.5-02" type="text/css" media="screen" title="no title" charset="utf-8">[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "  </head>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "  <body>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "    <h1>404 - Path /org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml not found in local storage of repository &quot;Ops4j Snapshots&quot; [id=ops4j-snapshots]</h1>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "    <p>Path /org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/maven-metadata.xml not found in local storage of repository &quot;Ops4j Snapshots&quot; [id=ops4j-snapshots]</p>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "  </body>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "</html>[\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | wire                             | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << "[\r][\n]"
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << HTTP/1.1 404 Not Found
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << Accept-Ranges: bytes
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << Content-Type: text/html
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << Date: Fri, 17 Nov 2017 07:49:55 GMT
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << Server: nginx
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << X-Content-Type-Options: nosniff
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << X-Frame-Options: SAMEORIGIN
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << transfer-encoding: chunked
	2017-11-17 09:00:33,720 | DEBUG | ataResolver-12-2 | headers                          | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40 << Connection: keep-alive
	2017-11-17 09:00:33,721 | DEBUG | ataResolver-12-2 | MainClientExec                   | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Connection can be kept alive indefinitely
	2017-11-17 09:00:33,721 | DEBUG | ataResolver-12-2 | faultManagedHttpClientConnection | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40: Shutdown connection
	2017-11-17 09:00:33,721 | DEBUG | ataResolver-12-2 | MainClientExec                   | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Connection discarded
	2017-11-17 09:00:33,721 | DEBUG | ataResolver-12-2 | faultManagedHttpClientConnection | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | http-outgoing-40: Close connection
	2017-11-17 09:00:33,721 | DEBUG | ataResolver-12-2 | olingHttpClientConnectionManager | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Connection released: [id: 40][route: {s}->https://oss.sonatype.org:443][total kept alive: 1; route allocated: 0 of 20; total allocated: 1 of 40]
	2017-11-17 09:00:33,721 | DEBUG | ataResolver-12-2 | DefaultUpdateCheckManager        | 1 - org.ops4j.pax.url.mvn - 2.4.7 | Writing tracking file /home/corrado/.m2/repository/org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/resolver-status.properties
	2017-11-17 09:00:33,723 | DEBUG | l for user karaf | AetherBasedResolver              | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Resolved (org.universAAL.ontology:ont.device:jar:3.4.1-SNAPSHOT) as /home/corrado/.m2/repository/org/universAAL/ontology/ont.device/3.4.1-SNAPSHOT/ont.device-3.4.1-SNAPSHOT.jar
	2017-11-17 09:00:33,723 | DEBUG | l for user karaf | BundleManager                    | 20 - org.apache.karaf.features.core - 3.0.8 | Installing bundle mvn:org.universAAL.ontology/ont.device/3.4.1-SNAPSHOT
	2017-11-17 09:00:33,725 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle ont.device/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,725 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for ont.device/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,725 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle ont.device/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,725 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for ont.device/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,725 | DEBUG | l for user karaf | device                           | 132 - ont.device - 3.4.1.SNAPSHOT | BundleEvent INSTALLED - ont.device
	2017-11-17 09:00:33,725 | DEBUG | l for user karaf | FeaturesServiceImpl              | 20 - org.apache.karaf.features.core - 3.0.8 | Installing bundle mvn:org.universAAL.ontology/ont.device/3.4.1-SNAPSHOT
	2017-11-17 09:00:33,726 | DEBUG | l for user karaf | FeaturesServiceImpl              | 20 - org.apache.karaf.features.core - 3.0.8 | Optional resolver 'obr' not found, using the default resolver
	2017-11-17 09:00:33,726 | DEBUG | l for user karaf | Overrides                        | 20 - org.apache.karaf.features.core - 3.0.8 | Unable to load overrides bundles list
	java.io.FileNotFoundException: /home/corrado/uaal/distro.karaf/etc/overrides.properties (No such file or directory)
		at java.io.FileInputStream.open0(Native Method)[:1.8.0_112]
		at java.io.FileInputStream.open(FileInputStream.java:195)[:1.8.0_112]
		at java.io.FileInputStream.<init>(FileInputStream.java:138)[:1.8.0_112]
		at java.io.FileInputStream.<init>(FileInputStream.java:93)[:1.8.0_112]
		at sun.net.www.protocol.file.FileURLConnection.connect(FileURLConnection.java:90)[:1.8.0_112]
		at sun.net.www.protocol.file.FileURLConnection.getInputStream(FileURLConnection.java:188)[:1.8.0_112]
		at java.net.URL.openStream(URL.java:1045)[:1.8.0_112]
		at org.apache.karaf.features.internal.Overrides.loadOverrides(Overrides.java:165)
		at org.apache.karaf.features.internal.Overrides.override(Overrides.java:74)
		at org.apache.karaf.features.internal.FeaturesServiceImpl.doInstallFeature(FeaturesServiceImpl.java:579)
		at org.apache.karaf.features.internal.FeaturesServiceImpl.installFeatures(FeaturesServiceImpl.java:434)
		at org.apache.karaf.features.internal.FeaturesServiceImpl.installFeature(FeaturesServiceImpl.java:415)
		at org.apache.karaf.features.internal.FeaturesServiceImpl.installFeature(FeaturesServiceImpl.java:390)
		at Proxy31ad2b7f_2d37_41e9_ba88_59c701b649cc.installFeature(Unknown Source)
		at org.apache.karaf.features.command.InstallFeatureCommand.doExecute(InstallFeatureCommand.java:72)
		at org.apache.karaf.features.command.FeaturesCommandSupport.doExecute(FeaturesCommandSupport.java:38)
		at org.apache.karaf.shell.console.AbstractAction.execute(AbstractAction.java:33)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.karaf.shell.console.OsgiCommandSupport.execute(OsgiCommandSupport.java:39)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.karaf.shell.commands.basic.AbstractCommand.execute(AbstractCommand.java:33)[27:org.apache.karaf.shell.console:3.0.8]
		at Proxy708a5b31_afbe_47cc_98de_56868d5b3456.execute(Unknown Source)[:]
		at Proxy708a5b31_afbe_47cc_98de_56868d5b3456.execute(Unknown Source)[:]
		at org.apache.felix.gogo.runtime.CommandProxy.execute(CommandProxy.java:78)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.Closure.executeCmd(Closure.java:480)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.Closure.executeStatement(Closure.java:406)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.Pipe.run(Pipe.java:108)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.Closure.execute(Closure.java:182)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.Closure.execute(Closure.java:119)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.CommandSessionImpl.execute(CommandSessionImpl.java:94)
		at org.apache.karaf.shell.console.impl.jline.ConsoleImpl.run(ConsoleImpl.java:210)
		at org.apache.karaf.shell.console.impl.jline.LocalConsoleManager$2$1$1.run(LocalConsoleManager.java:109)
		at java.security.AccessController.doPrivileged(Native Method)[:1.8.0_112]
		at org.apache.karaf.jaas.modules.JaasHelper.doAs(JaasHelper.java:57)[28:org.apache.karaf.jaas.modules:3.0.8]
		at org.apache.karaf.shell.console.impl.jline.LocalConsoleManager$2$1.run(LocalConsoleManager.java:102)[27:org.apache.karaf.shell.console:3.0.8]
	2017-11-17 09:00:33,729 | DEBUG | l for user karaf | BundleManager                    | 20 - org.apache.karaf.features.core - 3.0.8 | Checking mvn:eu.servicemix.uaal.tutorials/contextPublisherBundle/3.4.1-SNAPSHOT
	2017-11-17 09:00:33,729 | DEBUG | l for user karaf | Connection                       | 1 - org.ops4j.pax.url.mvn - 2.4.7 | Resolving [mvn:eu.servicemix.uaal.tutorials/contextPublisherBundle/3.4.1-SNAPSHOT]
	2017-11-17 09:00:33,730 | DEBUG | l for user karaf | DefaultUpdateCheckManager        | 1 - org.ops4j.pax.url.mvn - 2.4.7 | Skipped remote request for eu.servicemix.uaal.tutorials:contextPublisherBundle:3.4.1-SNAPSHOT/maven-metadata.xml, locally installed metadata up-to-date.
	2017-11-17 09:00:33,731 | DEBUG | l for user karaf | DefaultUpdateCheckManager        | 1 - org.ops4j.pax.url.mvn - 2.4.7 | Skipped remote request for eu.servicemix.uaal.tutorials:contextPublisherBundle:3.4.1-SNAPSHOT/maven-metadata.xml, locally installed metadata up-to-date.
	2017-11-17 09:00:33,731 | DEBUG | l for user karaf | DefaultUpdateCheckManager        | 1 - org.ops4j.pax.url.mvn - 2.4.7 | Skipped remote request for eu.servicemix.uaal.tutorials:contextPublisherBundle:3.4.1-SNAPSHOT/maven-metadata.xml, locally installed metadata up-to-date.
	2017-11-17 09:00:33,731 | DEBUG | l for user karaf | DefaultUpdateCheckManager        | 1 - org.ops4j.pax.url.mvn - 2.4.7 | Skipped remote request for eu.servicemix.uaal.tutorials:contextPublisherBundle:3.4.1-SNAPSHOT/maven-metadata.xml, locally installed metadata up-to-date.
	2017-11-17 09:00:33,731 | DEBUG | l for user karaf | AetherBasedResolver              | 3 - org.ops4j.pax.logging.pax-logging-api - 1.8.4 | Resolved (eu.servicemix.uaal.tutorials:contextPublisherBundle:jar:3.4.1-SNAPSHOT) as /home/corrado/.m2/repository/eu/servicemix/uaal/tutorials/contextPublisherBundle/3.4.1-SNAPSHOT/contextPublisherBundle-3.4.1-SNAPSHOT.jar
	2017-11-17 09:00:33,731 | DEBUG | l for user karaf | BundleManager                    | 20 - org.apache.karaf.features.core - 3.0.8 | Installing bundle mvn:eu.servicemix.uaal.tutorials/contextPublisherBundle/3.4.1-SNAPSHOT
	2017-11-17 09:00:33,741 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle contextPublisherBundle/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,741 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for contextPublisherBundle/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,741 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle contextPublisherBundle/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,741 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for contextPublisherBundle/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,741 | DEBUG | l for user karaf | contextPublisherBundle           | 133 - contextPublisherBundle - 3.4.1.SNAPSHOT | BundleEvent INSTALLED - contextPublisherBundle
	2017-11-17 09:00:33,741 | DEBUG | l for user karaf | FeaturesServiceImpl              | 20 - org.apache.karaf.features.core - 3.0.8 | Installing bundle mvn:eu.servicemix.uaal.tutorials/contextPublisherBundle/3.4.1-SNAPSHOT
	2017-11-17 09:00:33,742 | DEBUG | l for user karaf | BundleManager                    | 20 - org.apache.karaf.features.core - 3.0.8 | Bundles to refresh: 
	2017-11-17 09:00:33,742 | DEBUG | l for user karaf | FeaturesServiceImpl              | 20 - org.apache.karaf.features.core - 3.0.8 | Starting bundle: ont.profile
	2017-11-17 09:00:33,744 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle contextPublisherBundle/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,744 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for contextPublisherBundle/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,744 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle contextPublisherBundle/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,744 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for contextPublisherBundle/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,744 | DEBUG | l for user karaf | contextPublisherBundle           | 133 - contextPublisherBundle - 3.4.1.SNAPSHOT | BundleEvent UNRESOLVED - contextPublisherBundle
	2017-11-17 09:00:33,744 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle contextPublisherBundle/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,744 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for contextPublisherBundle/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,744 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle contextPublisherBundle/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,744 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for contextPublisherBundle/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,744 | DEBUG | l for user karaf | contextPublisherBundle           |  -  -  | BundleEvent UNINSTALLED - contextPublisherBundle
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle ont.profile/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for ont.profile/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,745 | DEBUG | lixDispatchQueue | framework                        | 0 - org.apache.felix.framework - 4.2.1 | FrameworkEvent PACKAGES REFRESHED - org.apache.felix.framework
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle ont.profile/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for ont.profile/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | profile                          | 131 - ont.profile - 3.4.1.SNAPSHOT | BundleEvent UNRESOLVED - ont.profile
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle ont.profile/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for ont.profile/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle ont.profile/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for ont.profile/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | profile                          |  -  -  | BundleEvent UNINSTALLED - ont.profile
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle ont.device/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for ont.device/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle ont.device/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for ont.device/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,745 | DEBUG | l for user karaf | device                           | 132 - ont.device - 3.4.1.SNAPSHOT | BundleEvent UNRESOLVED - ont.device
	2017-11-17 09:00:33,746 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle ont.device/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,746 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for ont.device/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,746 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Starting BlueprintContainer destruction process for bundle ont.device/3.4.1.SNAPSHOT
	2017-11-17 09:00:33,746 | DEBUG | l for user karaf | BlueprintExtender                | 15 - org.apache.aries.blueprint.core - 1.6.1 | Not a blueprint bundle or destruction of BlueprintContainer already finished for ont.device/3.4.1.SNAPSHOT.
	2017-11-17 09:00:33,746 | DEBUG | l for user karaf | device                           |  -  -  | BundleEvent UNINSTALLED - ont.device
	2017-11-17 09:00:33,746 | DEBUG | lixDispatchQueue | framework                        | 0 - org.apache.felix.framework - 4.2.1 | FrameworkEvent PACKAGES REFRESHED - org.apache.felix.framework
	2017-11-17 09:00:33,747 | DEBUG | l for user karaf | LoggingCommandSessionListener    | 27 - org.apache.karaf.shell.console - 3.0.8 | Command: 'feature:install uaalTutorials-contextPublisher' failed: java.lang.IllegalStateException: Can't install feature uaalTutorials-contextPublisher/0.0.0: 	
	Could not start bundle mvn:org.universAAL.ontology/ont.profile/3.4.1-SNAPSHOT in feature(s) uAAL-Ont.Profile-3.4.1-SNAPSHOT: Unresolved constraint in bundle ont.profile [131]: Unable to resolve 131.0: missing requirement [131.0] osgi.wiring.package; (&(osgi.wiring.package=org.universAAL.ontology.location)(version>=3.4.0)(!(version>=4.0.0)))
	2017-11-17 09:00:33,747 | DEBUG | lixDispatchQueue | framework                        | 0 - org.apache.felix.framework - 4.2.1 | FrameworkEvent PACKAGES REFRESHED - org.apache.felix.framework
	2017-11-17 09:00:33,747 | DEBUG | lixDispatchQueue | framework                        | 0 - org.apache.felix.framework - 4.2.1 | FrameworkEvent PACKAGES REFRESHED - org.apache.felix.framework
	2017-11-17 09:00:33,748 | ERROR | l for user karaf | ShellUtil                        | 27 - org.apache.karaf.shell.console - 3.0.8 | Exception caught while executing command
	java.lang.IllegalStateException: Can't install feature uaalTutorials-contextPublisher/0.0.0: 	
	Could not start bundle mvn:org.universAAL.ontology/ont.profile/3.4.1-SNAPSHOT in feature(s) uAAL-Ont.Profile-3.4.1-SNAPSHOT: Unresolved constraint in bundle ont.profile [131]: Unable to resolve 131.0: missing requirement [131.0] osgi.wiring.package; (&(osgi.wiring.package=org.universAAL.ontology.location)(version>=3.4.0)(!(version>=4.0.0)))
		at org.apache.karaf.features.internal.FeaturesServiceImpl.installFeature(FeaturesServiceImpl.java:403)
		at Proxy31ad2b7f_2d37_41e9_ba88_59c701b649cc.installFeature(Unknown Source)
		at org.apache.karaf.features.command.InstallFeatureCommand.doExecute(InstallFeatureCommand.java:72)
		at org.apache.karaf.features.command.FeaturesCommandSupport.doExecute(FeaturesCommandSupport.java:38)
		at org.apache.karaf.shell.console.AbstractAction.execute(AbstractAction.java:33)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.karaf.shell.console.OsgiCommandSupport.execute(OsgiCommandSupport.java:39)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.karaf.shell.commands.basic.AbstractCommand.execute(AbstractCommand.java:33)[27:org.apache.karaf.shell.console:3.0.8]
		at Proxy708a5b31_afbe_47cc_98de_56868d5b3456.execute(Unknown Source)[:]
		at Proxy708a5b31_afbe_47cc_98de_56868d5b3456.execute(Unknown Source)[:]
		at org.apache.felix.gogo.runtime.CommandProxy.execute(CommandProxy.java:78)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.Closure.executeCmd(Closure.java:480)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.Closure.executeStatement(Closure.java:406)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.Pipe.run(Pipe.java:108)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.Closure.execute(Closure.java:182)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.Closure.execute(Closure.java:119)[27:org.apache.karaf.shell.console:3.0.8]
		at org.apache.felix.gogo.runtime.CommandSessionImpl.execute(CommandSessionImpl.java:94)
		at org.apache.karaf.shell.console.impl.jline.ConsoleImpl.run(ConsoleImpl.java:210)
		at org.apache.karaf.shell.console.impl.jline.LocalConsoleManager$2$1$1.run(LocalConsoleManager.java:109)
		at java.security.AccessController.doPrivileged(Native Method)[:1.8.0_112]
		at org.apache.karaf.jaas.modules.JaasHelper.doAs(JaasHelper.java:57)[28:org.apache.karaf.jaas.modules:3.0.8]
		at org.apache.karaf.shell.console.impl.jline.LocalConsoleManager$2$1.run(LocalConsoleManager.java:102)[27:org.apache.karaf.shell.console:3.0.8]

