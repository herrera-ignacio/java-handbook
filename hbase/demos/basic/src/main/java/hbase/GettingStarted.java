package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class GettingStarted {

    static Configuration conf = HBaseConfiguration.create();

    public static void main(String[] args) throws IOException {
        conf.set("hbase.zookeeper.quorum", "localhost");
        conf.set("hbase.zookeeper.quorum.clientPort", "2181");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("demo"));

        // Read example
        Scan scan1 = new Scan();
        ResultScanner scanner1 = table.getScanner(scan1);

        for(Result rs : scanner1) {
            // System.out.println(rs);
            System.out.println(Bytes.toString(rs.getRow()));
            System.out.println(Bytes.toString(rs.getValue("cf1".getBytes(), "column1".getBytes())));
            System.out.println(Bytes.toString(rs.getValue("cf1".getBytes(), "column2".getBytes())));
        }

        scanner1.close();
    }

}
