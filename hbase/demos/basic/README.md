# HBase CRUD Example

## Requirements

* Setup HBase standalone
* Connect with HBase Shell
* Setup demo tables

### Setting Up Tables

```
> create 'demo', 'cf1'
> put 'demo', '1', 'cf1:colum', 'value1'
> put 'demo', '2', 'cf1:column2', 'value2'
> scan 'demo'
```

## Useful HBase Shell commands

```
> get 'demo', '3'
> get 'demo', '3', 'cf1:column1'
> delete 'demo', '3'
> delete 'demo', '3', 'cf1:column2'
```
