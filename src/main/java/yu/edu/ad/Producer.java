package yu.edu.ad;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Producer {

    // private String topic = "yellow-taxis";
    public static void main(String[] args) {

        if(args.length < 3) throw new IllegalArgumentException("Producer requires:\n1. Property config file (look in resources folder)\n2. Path to input file\n3. Topic name");
        String propFile = args[0];
        String inputFilePath = args[1];
        String topic = args[2];
         // set up the producer
         KafkaProducer<String, String> producer = null;

         try (InputStream props = Resources.getResource(propFile).openStream()) {
             Properties properties = new Properties();
             properties.load(props);
             producer = new KafkaProducer<>(properties);
         } catch(IOException e) {
             e.printStackTrace();
         }

         BufferedReader reader = null;
         try {
             reader = new BufferedReader(new FileReader(inputFilePath));

             String line = reader.readLine();
             do {
                line = reader.readLine();
                // System.out.println(line);
                producer.send(new ProducerRecord<String,String>(topic,"key",line));
             } while(line != null);
             producer.flush();
             System.out.println("Finished producing everything");
         } catch(Throwable throwable) {
             throwable.printStackTrace();
         } finally {
            //  reader.close();
             producer.close();
         }
    }
}