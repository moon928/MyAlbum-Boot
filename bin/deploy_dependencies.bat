cd ..
cd myalbum-dependencies
call mvn clean install

cd ..
cd myalbum-commons
call mvn clean install

cd ..
cd myalbum-commons-domain
call mvn clean install

cd ..
cd myalbum-commons-mapper
call mvn clean install

cd ..
cd myalbum-commons-service
call mvn clean install

cd ..
cd myalbum-commons-consumer
call mvn clean install

cd ..
cd myalbum-zipkin
call mvn clean install

cd ..
cd myalbum-service-provider-test
call mvn clean install

cd ..
cd myalbum-service-consumer-test
call mvn clean install

cd ..
cd myalbum-service-linux
call mvn clean install

cd ..
cd myalbum-service-provider-backstage
call mvn clean install

cd ..
cd myalbum-service-security-oauth2
call mvn clean install


cd ..
cd myalbum-service-email
call mvn clean install

cd ..
cd myalbum-service-redis
call mvn clean install

cd ..
cd myalbum-service-register
call mvn clean install


cd ..
cd myalbum-service-gateway
call mvn clean install