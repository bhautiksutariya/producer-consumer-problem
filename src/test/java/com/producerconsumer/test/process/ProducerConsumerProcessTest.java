package com.producerconsumer.test.process;

import com.producerconsumer.process.ProducerConsumerProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.Queue;

import static com.producerconsumer.test.utility.ProducerConsumerTestUtil.getType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ProducerConsumerProcessTest {

    ProducerConsumerProcess producerConsumerProcess;
    Queue<String> queue;

    //Initialization
    @BeforeEach
    public void init() {
        producerConsumerProcess = new ProducerConsumerProcess(5);
        queue = new LinkedList<>();
    }

    @Test
    public void producerProducingData() throws Exception {
        assertEquals(producerConsumerProcess.produce(),true);
    }

    @Test
    public void getTypeOfData() throws Exception {
        assertNotNull(producerConsumerProcess.getType("{\"type\":\"push\", \"api\":\"push api\", \"msg\":\"new video is added\", \"relatedTo\":" +
                "\"personalization\", \"image\":\"abc.jpg\"}"));
    }

    @Test
    public void consumerConsumingEmailData() throws Exception {
        queue.add("{\"type\":\"email\", \"api\":\"email api\", \"email\":\"abc@gmail.com\", \"msg\":\"This is sample email message body\", \"subject\":" +
                "\"This is subject 1\"}");
        ProducerConsumerProcess pc=Mockito.mock(ProducerConsumerProcess.class);
        Mockito.when(pc.emailConsume()).thenAnswer(item -> {
            if(getType(queue.peek()).equals("email"))
                return true;
            return false;
        });
        assertEquals(true,pc.emailConsume());
    }

    @Test
    public void consumerConsumingSmsData() throws Exception {
        queue.add("{\"type\":\"sms\", \"api\":\"sms api\", \"msg\":\"get 10% off on next ride\", \"number\":" +
                "\"7656434567\"}");
        ProducerConsumerProcess pc=Mockito.mock(ProducerConsumerProcess.class);
        Mockito.when(pc.emailConsume()).thenAnswer(item -> {
            if(getType(queue.poll()).equals("sms"))
                return true;
            return false;
        });
        assertEquals(true,pc.emailConsume());
    }

    @Test
    public void consumerConsumingPushData() throws Exception {
        queue.add("{\"type\":\"push\", \"api\":\"push api\", \"msg\":\"new video is added\", \"relatedTo\":" +
                "\"personalization\", \"image\":\"abc.jpg\"}");
        ProducerConsumerProcess pc=Mockito.mock(ProducerConsumerProcess.class);
        Mockito.when(pc.emailConsume()).thenAnswer(item -> {
            if(getType(queue.poll()).equals("push"))
                return true;
            return false;
        });
        assertEquals(true,pc.emailConsume());
    }

}
