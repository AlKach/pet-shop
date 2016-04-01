service postgresql stop && \
rm -rf __BACKUP__ && \
tar czvf __BACKUP__ -C __DATA__ . && \
service postgresql start