package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

public class DeleteDemo {

    static Configuration conf = HBaseConfiguration.create();

    public static void main(String[] args) throws IOException {
        conf.set("hbase.zookeeper.quorum", "localhost");
        conf.set("hbase.zookeeper.quorum.clientPort", "2181");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("demo"));

        Delete del = new Delete("3".getBytes());

        System.out.println("Deleting row-key 3");

        table.delete(del);
    }
}
