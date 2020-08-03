package com.xiayuan.sparkProject.test;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.*;

/**
 * @author yeunsher
 * @date 2020-04-14 - 11:48
 */
public class MockRealTimeData extends Thread{
    private static final Random RANDOM = new Random();
    private static final String[] provinces = new String[]{"Jiangsu", "Hubei", "Hunan", "Henan", "Hebei"};
    private static final Map<String, String[]> provinceCityMap = new HashMap<>();

    private Producer<Integer, String> producer;

    public MockRealTimeData() {
        provinceCityMap.put("Jiangsu", new String[]{"Nanjing", "Suzhou"});
        provinceCityMap.put("Hubei", new String[]{"Wuhan", "Jingzhou"});
        provinceCityMap.put("Hunan", new String[]{"Changsha", "Xiangtan"});
        provinceCityMap.put("Henan", new String[]{"Zhengzhou", "Luoyang"});
        provinceCityMap.put("Hebei", new String[]{"Shijiazhuang", "Tangshan"});

        producer = new Producer<Integer, String>(createProducerConfig());
    }

    private ProducerConfig createProducerConfig() {
        Properties props = new Properties();
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "10.211.55.55:9092");
//        props.put("bootstrap.servers", "10.211.55.55:9092");
        return new ProducerConfig(props);
    }

    public void run() {
        while (true){
            String province = provinces[RANDOM.nextInt(5)];
            String city = provinceCityMap.get(province)[RANDOM.nextInt(2)];

            //log信息为：time province city userid adid
            String log = new Date().getTime() + " " + province + " " + city + " "
                    + RANDOM.nextInt(1000) + " " + RANDOM.nextInt(10);
            producer.send(new KeyedMessage<>("AdRealTimeLog", log));

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 启动kafka Producer
     * @param args
     */
    public static void main(String[] args){
        MockRealTimeData producer = new MockRealTimeData();
        producer.start();
    }
}
