import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.BaseConfigurable;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.File;

import static org.apache.hadoop.hbase.util.Bytes.*;

public class Main {

    public static void main(String[] args) throws Exception {
        /* 唯一必须指定的配置项是ZooKeeper的位置 */
        //Configuration conf = new Configuration();
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum",
                "hbasemaster28.cm3.tbsite.net,hbasemaster29.cm3.tbsite.net,hbasemaster30.cm3.tbsite.net,hbase0393.cm3.tbsite.net,hbase0394.cm3.tbsite.net");
        conf.set("zookeeper.znode.parent","/hbase-A26-1");
        /* HTable是客户端访问HBase的入口类，对指定Table的CRUD操作通过该类请求 */
        HTable table = new HTable(conf, "abc_timeline_user_allbeh");
        String rowKey="0006\\x0122831417\\x012014052700";
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
            System.out.println("family:" + Bytes.toString(kv.getFamily()));
            System.out
                    .println("qualifier:" + Bytes.toString(kv.getQualifier()));
            System.out.println("value:" + Bytes.toString(kv.getValue()));
            System.out.println("Timestamp:" + kv.getTimestamp());
            System.out.println("-------------------------------------------");
        }
        //return result;
    }
}
