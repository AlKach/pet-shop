service postgresql stop && \
rm -rf __DATA__/* && \
tar -xvzf __BACKUP__ -C __DATA__ && \
rm -rf __BACKUP__ && \
service postgresql start