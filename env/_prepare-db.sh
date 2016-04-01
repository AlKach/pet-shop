apt-get update -y && \
apt-get upgrade -y && \
apt-get install postgresql -y && \
cd __CONFIG__ && \
cp pg_hba.conf pg_hba.conf.orig && \
echo "host all all all md5" >> pg_hba.conf && \
cp postgresql.conf postgresql.conf.orig && \
echo "listen_addresses = '*'" >> postgresql.conf && \
service postgresql restart && \
su - postgres -c "createdb __SERVICE__" && \
su - postgres -c "createdb __TEST_SERVICE__" && \
su - postgres <<-'ENDPOSTGRES'
	psql <<-'ENDPSQL'
		create user __USER__ with password '__PASSWORD__';
		grant all privileges on database "__SERVICE__" to __USER__;
		create user __TEST_USER__ with password '__TEST_PASSWORD__';
		grant all privileges on database "__TEST_SERVICE__" to __TEST_USER__;
		\q
	ENDPSQL
ENDPOSTGRES
