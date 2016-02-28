apt-get install postgresql-9.1 -y && \
cd /etc/postgresql/9.1/main && \
cp pg_hba.conf pg_hba.conf.orig && \
echo "host all all all md5" >> pg_hba.conf && \
cp postgresql.conf postgresql.conf.orig && \
echo "listen_addresses = '*'" >> postgresql.conf && \
service postgresql restart && \
su - postgres -c "createdb __SERVICE__" && \
su - postgres <<-'ENDPOSTGRES'
	psql <<-'ENDPSQL'
		create user __USER__ with password '__PASSWORD__';
		grant all privileges on database "__SERVICE__" to __USER__;
		\q
	ENDPSQL
ENDPOSTGRES
