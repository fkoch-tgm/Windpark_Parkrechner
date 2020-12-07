package windpark.parkrechner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import windpark.model.ParkTextData;
import windpark.parkrechner.mom.MOMReceiver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ParkrechnerApplication {
    public static List<String> queues;
    public static ParkTextData data;

    private MOMReceiver receiver;

    public static void main(String[] args) {
        SpringApplication.run(ParkrechnerApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Starting Parkrechner");
            data = new ParkTextData();
            receiver = new MOMReceiver();
        };
    }
}
