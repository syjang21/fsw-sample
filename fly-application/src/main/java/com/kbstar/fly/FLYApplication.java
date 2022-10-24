package com.kbstar.fly;
import static java.nio.charset.Charset.defaultCharset;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Locale.US;
import static java.util.TimeZone.getTimeZone;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistryAutoConfiguration;
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, ServiceRegistryAutoConfiguration.class } ,
scanBasePackages = { "com.kbstar.fly.*" })
public class FLYApplication {
  private static final Logger log = LoggerFactory.getLogger(FLYApplication.class);
  private static final TimeZone UTC = getTimeZone("UTC");
  public static void main(String[] args) {
    SpringApplication.run(FLYApplication.class, args);
  }
  @PostConstruct
  public void postConstruct() {
    if (!UTC.hasSameRules(TimeZone.getDefault())) {
      log.warn("Expecting {} as default timezone [current={}]", UTC.getDisplayName(),
          TimeZone.getDefault().getDisplayName());
    }
    if (!UTF_8.equals(Charset.defaultCharset())) {
      log.warn("Expecting {} as default charset [current={}]", UTF_8.name(), defaultCharset());
    }
    if (!US.equals(Locale.getDefault())) {
      log.warn("Expecting {} as default locale [current={}]", US.getDisplayName(),
          Locale.getDefault().getDisplayName());
    }
  }
}