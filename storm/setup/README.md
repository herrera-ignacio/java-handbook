# Environment Setup

* Requirements
* Zookeper
* Configuration
* Running

## Requirements

### Hardware

* Development Hardware
  * 4GB of RAM
  * 2 Processors
  * 100GB HDD
* Production Hardware
  * Min 32GB of RAM
  * 6-core, >2GHz Processors
  * 2TB HDD
  * Min 5 machine cluster

### OS

* Linux/Unix OS
  * RHEL/CentOS
  * Ubuntu
  * Debian
* Windows only for development

> Needs Java and Python.

## Zookeper

Zookeper is needed for process coordination in Storm.

## Configuration

`conf/storm.yaml` file for configuration.

* Zookeper servers
* `nimbus.seeds`
* Storm data directory `storm.local.dir`
* JVM options for worker

## Running

* Master node: `bin/storm nimbus`, `bin/storm ui`
* Worker node: `bin/storm supervisor`

> For Windows, you may need to run `Set-ExecutionPolicy RemoteSigned` with admin privileges.

Once everything is running, you can send a job (topology) to Storm.

```
bin/storm jar <jar_file> <package.myMainClass>
```
