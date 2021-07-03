package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

public class PutDemo {

    static Configuration conf = HBaseConfiguration.create();

    public static void main(String[] args) throws IOException {
        conf.set("hbase.zookeeper.quorum", "localhost");
        conf.set("hbase.zookeeper.quorum.clientPort", "2181");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("demo"));

        // Put example
        Put put = new Put("3".getBytes());

        put.addColumn("cf1".getBytes(), "column1".getBytes(), "value1".getBytes());
        put.addColumn("cf1".getBytes(), "column2".getBytes(), "value2".getBytes());

        table.put(put);

    }
}
