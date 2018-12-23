keytool -genkey -alias signFiles -keystore mykeystore -keypass kpi135 -dname "cn=panda" -storepass ab987c
jarsigner -keystore mykeystore -storepass ab987c -keypass kpi135 -signedjar SignedSQLClient.jar SQLClient.jar signFiles
REM keytool -export -keystore compstore -storepass ab987c -alias signFiles -file CompanyCer.cer
REM keytool -import -alias company1 -file CompanyCer.cer -keystore raystore -storepass abcdefgh
appletviewer -J-Djava.security.policy==SignedSQLClient.policy SignedSQLClient.html