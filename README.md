# sd-project2

Project 2 for the course of Sistemas Distribuídos (Distributed Systems) at Universidade de Aveiro.

## Heist to the Museum

### Run everything out of the box
To **compile**, **build**, **deploy** and **run** the project, run the following commands in the root directory of the project:
```bash
$ chmod +x *.sh
$ ./random_machines.sh; ./build.sh global; ./run_global.sh
```
or
```bash
$ chmod +x *.sh
$ ./local_machines.sh; ./build.sh local; ./run_local.sh
```

### Deployment configurations
The deployment configuration is in the `config` file in the root directory of the project. Use this to change the **machine** and **ports** used by the servers and clients.

Put the password to access the machines in the `password` file in the root directory of the project.

If you want to deploy the project locally, don't forget to change the machine names in the `config` file to `localhost` (can be done automatically by running `./local_machines.sh` in the root directory of the project).

### Build the project
To build the project, run the following commands in the root directory of the project:
```bash
$ chmod +x build.sh build_server.sh build_client.sh deploy_local.sh deploy_global.sh
$ ./build.sh <local | global>
```
This file compiles, builds and deploys the project. The argument can be either `local` or `global`, depending on the type of deployment you want to do.

### Run the project
To run the project, run the following commands in the root directory of the project:
```bash
$ chmod +x run_global.sh
$ ./run_global.sh
```
or
```bash
$ chmod +x run_local.sh run_server.sh run_client.sh
$ ./run_local.sh
```

### Clean the project .class files and temp folder
To clean the project, run the following commands in the root directory of the project:
```bash
$ chmod +x clean.sh
$ ./clean.sh
```

### Auxiliary scripts
In the root directory of the project, there are some auxiliary scripts that are used by the previous scripts and might not work properly (or achieve any desired goal) if run directly. These scripts are: `build_server.sh`, `build_client.sh`, `deploy_global.sh`, `deploy_local.sh`, `run_server.sh` and `run_client.sh`.

### Dependencies
To deploy and remotely run the project, you need **xterm** and **sshpass**. To install them, run the following commands:
```bash
$ sudo apt-get install xterm
$ sudo apt-get install sshpass
```

### Contributors
* [Diogo Correia](https://github.com/digas99) (90327): diogo.correia99@ua.pt
* [Lara Rodrigues](https://github.com/Lararodrigues1) (93427): laravieirarodrigues@ua.pt

### Related Projects from this course
* [**sd-project1:** Pure concurrent implementation of the problem running in a single platform.](https://github.com/digas99/sd-project1)
* **sd-project2:** Distributed implementation of the problem, based on message passing, running in multiple platforms.
* [**sd-project3:** Distributed implementation of the problem, based on method invocation on remote objects, running in multiple platforms.](https://github.com/digas99/sd-project3)