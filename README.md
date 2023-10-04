# springboot-camel-jdbcapi-oracle-restapi-books
About This is a project which has REST API services of Books using Apache Camel in Spring Boot. Here I have used JDBC API and Oracle SQL Developer DB.
<br>
<br>
<b>Environment Varibles:-</b>
<br>
APPDB_CONN_POOL_TYPE=oracle.ucp.jdbc.PoolDataSource
<br>
APPDB_URL=jdbc:oracle:thin:@192.168.50.10:1521:orcl?autoReconnect=true&useSSL=false
<br>
APPDB_URL_PASSWORD=BPM_APPDB
<br>
APPDB_URL_USERNAME=BPM_APPDB
<br>
JAEGER_ENDPOINT=/api/v1
<br>
JAEGER_SERVICE_NAME=JAEGER_SERVICE_NAME
<br>
UCP_POOL_CONN_FACTORY_CLASS_NAME=oracle.jdbc.pool.OracleDataSource
<br>
UCP_POOL_CONN_NAME=ODSDB
<br>
<br>
<br>
<b>How to set Environment variables in STS:-</b>
<br>
On the Project, right click and in Run As select Run Configurations. In that go to Environment tab and then put values.
