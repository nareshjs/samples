package nj.samples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Slf4j
@SpringBootApplication
public class SampleApp {

  public static void main(String[] args) {
    log.info("Entering Service A");
    SpringApplication.run(SampleApp.class, args);
  }
}
