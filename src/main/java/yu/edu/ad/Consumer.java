package yu.edu.ad;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import com.google.common.io.Resources;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Consumer {
    public static void main(String[] args) {

        if(args.length < 2) throw new IllegalArgumentException("Need to provide:\n1. Consumer config file (look in resources folder)\n2. topic name");
         // and the consumer
         KafkaConsumer<String, String> consumer = null;

         try (InputStream props = Resources.getResource(args[0]).openStream()) {
             Properties properties = new Properties();
             properties.load(props);
             if (properties.getProperty("group.id") == null) {
                 properties.setProperty("group.id", "group-" + new Random().nextInt(100000));
             }
             consumer = new KafkaConsumer<>(properties);
             
            } catch(IOException e) {
                e.printStackTrace();
            }
            
            try {
                
                consumer.subscribe(Arrays.asList(args[1]));
                int i = 0;
                while(true) {
                    
                    ConsumerRecords<String, String> recs = consumer.poll(Duration.ofMillis(500));
                    
                    // System.out.println("recs count: " + recs.count());
                    for(ConsumerRecord<String, String> record : recs) {
                        System.out.println(record.value());
                    }
                    // System.out.println(i);
                }

         } catch(Throwable throwable) {
            throwable.printStackTrace();
         } finally {
             consumer.close();
         }
    }
}