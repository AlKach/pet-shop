# pet-shop

Simple internet shop application. Created for practicing in developing web application from scratch.

Runtime environment:
 - JVM 1.8
 - Tomcat 7
 - PostgreSQL 9.1

Frameworks and libraries:
 - [Spring](https://spring.io/) - IOC container, MVC, transactions handling
 - [Hibernate](http://hibernate.org/) - data access
 - [Flywaydb](https://flywaydb.org/) - DB migration
 
## Environment setup

pet-shop includes scripts for automating environment setup.

#### Database

pet-shop uses PostgreSQL DB to store its data. It is recommended to run DB on separate VM. Envirnoment setup scripts (stored in `/env` folder) automate running, resetting and configuring VM in VirtualBox.

In order to set up development DB, perform following steps:
 1. Create VM in VirtualBox.
 2. Install Debian on it.
 3. Create VM snapshot in VirtualBox (in order to be able to reset VM and run new version of setup script).
 4. Configure SSH access to this VM. Make sure posts 22 (SSH) and 5432 (PostgreSQL) are passed through from VMto your local machine.
 5. Specify VM and snapshot names, DB service name, user and password in `/env/vm.properties` file.
 6. Make sure VirtualBox folder is in your `PATH`
 7. Run VM in headless mode by running `/env/db-vm-run.sh`
 8. Run `/env/db-vm-init.sh` to install and configure PostgreSQL on VM.
 
Now you have VM with installed PostgreSQL. Empty DB with specified service name, user and password will be created automatically. Running application for the first time will automatically create all needed tables.
