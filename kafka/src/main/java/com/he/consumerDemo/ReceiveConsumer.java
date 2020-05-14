package com.he.consumerDemo;

import com.he.utils.PropertiesUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Properties;

public class ReceiveConsumer {
    public static void main(String[] args) {
//        获取配置文件
        Properties props = PropertiesUtil.getProperties("consumer.properties");
//        创建consumer对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
//        根据主题订阅
        consumer.subscribe(Arrays.asList("hiktest"));
//        根据分区订阅
//        TopicPartition p0 = new TopicPartition("tocc_bus",1);
//        consumer.assign(Arrays.asList(p0));
        System.out.println("start");
        while (true){
//            每隔100ms消费一次
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String,String> record:records) {
                System.out.println(record.value());
            }
//            手动同步 offset
//            consumer.commitSync();
        }
    }

}
