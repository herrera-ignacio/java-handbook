package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class GetDemo {

    static Configuration conf = HBaseConfiguration.create();

    public static void main(String[] args) throws IOException {
        conf.set("hbase.zookeeper.quorum", "localhost");
        conf.set("hbase.zookeeper.quorum.clientPort", "2181");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("demo"));

        Get get = new Get("3".getBytes());
        Result getResult = table.get(get);

        System.out.println("Printing columns for row-key 3");
        System.out.println(getResult);
        System.out.println(Bytes.toString(getResult.getRow()));
        System.out.println(Bytes.toString(getResult.getValue("cf1".getBytes(), "column1".getBytes())));
        System.out.println(Bytes.toString(getResult.getValue("cf1".getBytes(), "column2".getBytes())));
    }
}
