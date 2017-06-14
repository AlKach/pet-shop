# pet-shop [![CI status](https://travis-ci.org/AlKach/pet-shop.svg?branch=master)](https://travis-ci.org/AlKach/pet-shop) [![BCH compliance](https://bettercodehub.com/edge/badge/AlKach/pet-shop?branch=master)](https://bettercodehub.com/)

Simple internet shop application. Created for practicing in developing web application from scratch.

Runtime environment:
 - JVM 1.8
 - Tomcat 7
 - PostgreSQL 9.1

Frameworks and libraries:
 - [Spring](https://spring.io/) - IOC container, MVC, transactions handling
 - [Hibernate](http://hibernate.org/) - data access
 - [Flywaydb](https://flywaydb.org/) - DB migration
 - [Swagger](http://swagger.io/) + [Springfox](http://springfox.github.io/springfox/) - API documentation
 
## Environment setup

pet-shop includes scripts for automating environment setup.

#### Database

pet-shop uses PostgreSQL DB to store its data. It is recommended to run DB on separate VM. Envirnoment setup scripts (stored in `/env` folder) automate running, resetting and configuring VM in VirtualBox.

In order to set up development DB, perform following steps:
 1. Create VM in VirtualBox.
 2. Install Debian on it.
 3. Create VM snapshot in VirtualBox (in order to be able to reset VM and run new version of setup script).
 4. Configure SSH access to this VM. Make sure to permit root login (`PermitRootLogin yes`) and password authentication (`PasswordAuthentication yes`) in `/etc/ssh/sshd_config`. Also make sure posts 22 (SSH) and 5432 (PostgreSQL) are passed through from VMto your local machine.
 5. Specify VM and snapshot names, DB service name, user and password in `/env/vm.properties` file. Also pay attention to `db_data_location` and `db_config_location` properties - they may vary depending on PostgreSQL version provided in your Debian distribution.
 6. Make sure VirtualBox folder is in your `PATH`
 7. Run VM in headless mode by running `/env/db-vm-run.sh`
 8. Run `/env/db-vm-init.sh` to install and configure PostgreSQL on VM.
 
Now you have VM with installed PostgreSQL. Empty DB with specified service name, user and password will be created automatically. Running application for the first time will automatically create all needed tables.

#### Environment management scripts

 - `db-backup-prepare.sh` - create and transfer to local machine backup of DB. DB will be stopped during backup procedure.
 - `db-backup-restore.sh` - restore DB from previously created backup. DB will be stopped during backup restoration procedure.
 - `db-vm-init.sh` - Connects to VM over SSH and sets up DB environment.
 - `db-vm-reset.sh` - Resets VM with DB to snapshot.
 - `db-vm-run.sh` - Starts VM with DB.
 - All scripts starting with `_` - Service scripts, should not be run standalone.

#### vm.properties

 - `vm_db_name` - name of VM with DB
 - `vm_db_snapshot` - name of snapshot for resetting VM with DB
 - `vm_db_host` - host of VM with DB (used for making SSH connections)
 - `db_service` - name of DB
 - `db_username` - username for DB access
 - `db_password` - password for DB access
 - `db_backup_name` - file name for backup
 - `db_data_location` - location of data folder of DB
