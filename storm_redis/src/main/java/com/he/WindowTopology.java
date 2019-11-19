package com.he;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.base.BaseWindowedBolt;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.state.StateFactory;
import org.apache.storm.trident.testing.FixedBatchSpout;
import org.apache.storm.trident.testing.Split;
import org.apache.storm.trident.windowing.config.SlidingDurationWindow;
import org.apache.storm.trident.windowing.config.WindowConfig;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WindowTopology {
    public static void main(String[] args) {
        Config conf = new Config();
        conf.setDebug(true);
        conf.put("hbase.conf", new HashMap());
        if (args.length == 0) {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("topN", conf, buildTopology());
        } else {
            conf.setNumWorkers(3);
            try {
                StormSubmitter.submitTopologyWithProgressBar(args[0], conf, buildTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }
        }
    }

    public static StormTopology buildTopology() {

        FixedBatchSpout fixedBatchSpout = new FixedBatchSpout(new Fields("sentence"), 3, new Values("the cow jumped over the moon"),
                new Values("the man went to the store and bought some candy"), new Values("four score and seven years ago"),
                new Values("how many apples can you eat"), new Values("to or not to be the person"));
        fixedBatchSpout.setCycle(true);

        //HBase相关配置
        //定义Mapper，把HBase的RowKey，列簇，列，对应到Trident中Tuple的Field
        //SimpleTridentHBaseMapper是TridentHBaseMapper的一个简单继承
//        TridentHBaseMapper tridentHBaseMapper=new SimpleTridentHBaseMapper()
//                .withColumnFamily("cf")
//                .withColumnFields(new Fields("word","count"))
//                .withRowKeyField("rank");
//
//        //定义HBase数据到Tuple的投影，加入cf列族的word和count列
//        HBaseProjectionCriteria projectionCriteria=new HBaseProjectionCriteria()
//                .addColumn(new HBaseProjectionCriteria.ColumnMetaData("cf","word"))
//                .addColumn(new HBaseProjectionCriteria.ColumnMetaData("cf","count"));
//
//        //定义HbaseState的属性类Options
//        HBaseState.Options options=new HBaseState.Options()
//                .withConfigKey("hbase.conf")
//                .withMapper(tridentHBaseMapper)
//                .withDurability(Durability.SYNC_WAL)
////                .withProjectionCriteria(projectionCriteria)
//                .withTableName("window");
//
//        //使用工厂类生产HbaseState对象
//        StateFactory factory=new HBaseStateFactory(options);

        //定义时间滑动窗口 每10秒计算过去30秒的数据
        WindowConfig slidingDurationWindow = SlidingDurationWindow.of(new BaseWindowedBolt.Duration(30, TimeUnit.SECONDS), new BaseWindowedBolt.Duration(10, TimeUnit.SECONDS));

        //创建TridentTopology
        //HBaseUpdater是用于更新HBaseState的类
        //在滑动窗口中使用聚合操作
        TridentTopology topology = new TridentTopology();
        topology.newStream("spout", fixedBatchSpout)
                .each(new Fields("sentence"), new Split(), new Fields("word"))
                .window(slidingDurationWindow, new Fields("word"), new Aggregate(), new Fields("rank", "word", "count"));
        return topology.build();
    }
}
