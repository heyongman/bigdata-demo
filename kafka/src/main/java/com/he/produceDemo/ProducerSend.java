package com.he.produceDemo;

import com.he.utils.PropertiesUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerSend {
    public static void main(String[] args) {
//        获取配置文件
        Properties properties = PropertiesUtil.getProperties("producer.properties");
//        实例化生产者对象
        Producer<String,String> producer = new KafkaProducer<>(properties);
//        发送
        for (int i = 0; i < 10; i++) {
//            指定key的进入同一分区
            producer.send(new ProducerRecord<String, String>("hiktest","111","hello"+i));
        }
        producer.close();

    }
}
