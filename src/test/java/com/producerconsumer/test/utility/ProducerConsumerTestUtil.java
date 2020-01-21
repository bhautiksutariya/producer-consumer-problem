package com.producerconsumer.test.utility;

import com.producerconsumer.entity.Producer;
import com.producerconsumer.entity.Queue;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ProducerConsumerTestUtil {

    //Common methods
    public static void produceData(Queue queue, Producer producer) throws Exception {
        for(int i=queue.items.size();i<queue.capacity;i++)
            producer.produce();
    }

    public static String getType(String data) {
        Object obj= JSONValue.parse(data);
        JSONObject jsonObject = (JSONObject) obj;
        return (String)jsonObject.getOrDefault("type","");
    }

    public static void getTypeof(String type,Queue queue,Producer producer) throws Exception {
        if(queue.items.size()==0)
            produceData(queue,producer);
        for(int i=0;i<queue.capacity;i++)
        {
            if(ProducerConsumerTestUtil.getType(queue.items.get(0)).equals(type))
                return;
            queue.items.removeFirst();
        }
        getTypeof(type,queue,producer);
    }

}
