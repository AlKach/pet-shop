if [ ! -f ~/.ssh/id_rsa.pub ]; then
	ssh-keygen -t rsa -b 2048 -f ~/.ssh/id_rsa -q -N ""
fi

eval "\
cat ~/.ssh/id_rsa.pub |\
ssh -o 'StrictHostKeyChecking no' root@$ssh_host '\
sed -i -e \"s:PermitRootLogin:PermitRootLogin without-password #:g\" /etc/ssh/sshd_config &&\
mkdir -p ~/.ssh &&\
cat >> ~/.ssh/authorized_keys \
'"