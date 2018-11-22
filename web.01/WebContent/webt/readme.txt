#############################################################
# 1. WebT Resource를 JEUSMain.xml에 등록하여 사용하는 방식
#############################################################
테스트 소스 : tpcall_from.jsp , tpcall_datasource.jsp 
 
[JEUSMain.xml] 
---------------------------------------------------------------------------------- 
   <resource>
      <external-resource> 
        <property> 
          <key>header-type</key>  
          <value>extendedV4</value> 
        </property> 
        <property> 
          <key>log-file-name</key> 
          <value>D:/log_home/webtlog/webt_jndi.log</value> 
        </property> 
        <property> 
          <key>log-level</key> 
          <value>info</value> 
        </property> 
        <property> 
          <key>fdl-file</key> 
          <value>D:/DevTool/TmaxSoft/JEUS6.0.09_64_eclipse/webt/fdl/tmax.fdl</value> 
        </property> 
        <property> 
          <key>log-valid-day</key> 
          <value>1</value> 
        </property> 
        <property> 
          <key>monitor-interval</key> 
          <value>30</value> 
        </property> 
        <property> 
          <key>check-alive</key> 
          <value>true</value> 
        </property> 
        <property> 
          <key>enable-failback</key> 
          <value>false</value> 
        </property> 
        <property> 
          <key>enable-autoclose</key> 
          <value>true</value> 
        </property> 
        <property-group> 
            <name-prefix>tmax1</name-prefix>
            <property> 
              <key>type</key> 
              <value>shared</value> 
            </property> 
            <property> 
              <key>host-name</key> 
              <value>192.168.137.133</value> 
            </property> 
            <property> 
              <key>host-port</key> 
              <value>8888</value> 
            </property> 
<!--            
            <property> 
              <key>backup-host-name</key> 
              <value>192.168.190.129</value> 
            </property> 
            <property> 
              <key>backup-port</key> 
              <value>8888</value> 
            </property>
-->            
            <property> 
              <key>min</key> 
              <value>5</value> 
            </property> 
            <property> 
              <key>max</key> 
              <value>20</value> 
            </property>            
            <property> 
              <key>step</key> 
              <value>2</value> 
            </property>            
            <property> 
              <key>period</key> 
              <value>60000</value> 
            </property>             
            <property> 
              <key>check-acquired</key> 
              <value>true</value> 
            </property>             
            <property> 
              <key>support-xa</key> 
              <value>true</value>             
            </property>            
        </property-group>
        <name>webt</name>
        <class-name>tmax.webt.external.WebtResource</class-name>
      </external-resource> 
   </resource>      
---------------------------------------------------------------------------------- 
      
#############################################################
# 2. WebT Resource를 webt.properties에 등록하여 사용하는 방식      
#############################################################
테스트 소스 : tpcall_from.jsp , tpcall_not_datasource.jsp 
 
[webt.properties]  
 command-option에 설정파일 경로 추가
 -Dwebt.properties=D:/DevTool/TmaxSoft/JEUS6.0.09_64_eclipse/webt/webt.properties
---------------------------------------------------------------------------------- 
 #
# logging related parameters
############################
# set log level valid values are none, info, debug. default is none
log.level=debug
# set directory in which the log file places. if not set, log is
# printed to standard out
log.dir=/user/svcdesk/jeus5020/logs/webt
# set the name of the log file. default is webt.log
log.file=webt.log
# set log buffering size. default is 0
log.bufsize=512
# set log valid day -> seperated by date-string. default is -1
log.valid.days=1
#
# FDL related parameters
########################
# set the fdl file.
#fdl.file=/east/hope/prod/jeus/system/tmax.fdl
#
# pipe related paramters
########################
# enable/disable pipe. default is disable(false)
#pipe=false
#
# input/output buffer size parameter
# set input buffer size. default is 4096. minimum is 1024
#inbuf.size=4096
# set output buffer size. default is 4096. minimum is 1024
#outbuf.size=4096
# set application wide default character set. default is system default
defaultCharset=euc-kr
# Tmax 3.11.x extended Header size
#extendedHeader=true
#
# monioring related parameter
###################################
# enable/disable alive check. default is disable(false)
monitoring.pool.checkAlive=true
# enable/disable idle check. default is disable(false)
monitoring.pool.checkIdle=true
# set monitoring interval. default is 60sec
monitoring.pool.interval=60
# set monitoring log file valid-days.default is -1
log.valid.days=1
# enable/disable webt.properties modify. Default is disable(false)
enableModify=false
# connection pool realted paramter
###################################
# enable/disable connection pool. default is disable(false)
enableConnectionPool=true

#getConnectionPool() H#Cb=C D?3X<G @/H?<: C<E)
connectionPool.getConnectionCheckedAlive=true  ---->  WebT 3.5.12.2     @L;s


# WebtConnectionGroup name list
connectionPool.groups=tmax1
# set connection group type valid values are shared, non-shared, non-shared2. default is shared
connectionPool.tmax1.type=shared
# set Tmax Server Address.
connectionPool.tmax1.hostAddr=192.167.10.37
# set Tmax Server Port.
connectionPool.tmax1.hostPort=7777
# set Backup Tmax Server Address.
#connectionPool.tmax1.hostBackupAddr=70.12.203.177
# set Backup Tmax Server Port.
#connectionPool.tmax1.hostBackupPort=8999
# set default user name for security.
#connectionPool.tmax1.userName=tmax
# set default user passward for security.
#connectionPool.tmax1.userPasswd=1234
# set default domain name for security.
#connectionPool.tmax1.domainName=choco
# set default domain passward for security.
#connectionPool.tmax1.domainPasswd=1234
# set initial pool size. default is 10
#connectionPool.tmax1.initCapacity=1
connectionPool.tmax1.initCapacity=10
# set max pool size. default is 20
#connectionPool.tmax1.maxCapacity=10
connectionPool.tmax1.maxCapacity=30
# set increment step size. default is 5
#connectionPool.tmax1.incrementRate=2
connectionPool.tmax1.incrementRate=1
# set connection idle time. default is 60sec
connectionPool.tmax1.maxIdleTime=60
# set tptimeout.
connectionPool.tmax1.tpTimeout=240
# set txtimeout.
connectionPool.tmax1.txTimeout=240
# set connection timeout.
connectionPool.tmax1.connectTimeout=4
# enable to event service.
#connectionPool.tmax1.enableEvent=true
# set event service Type.
#connectionPool.tmax1.eventSvcType=all
# set event handler object.
#connectionPool.tmax1.eventHandler=tcpservet.event.GenericEvent
---------------------------------------------------------------------------------- 
 