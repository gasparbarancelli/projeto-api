package br.com.viasoft.projetoapi.controller;

import org.apache.tomcat.jni.Time;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.LocalTime;

@EnableScheduling
@Controller
public class JobController {

    // Scheduler executará o comando a cada 5 minutos, começando em 00 (15:00, 15:05, 15:10, (...))
    @Scheduled(cron = "0 */5 * ? * *")
    public void test() {
        System.out.println("Job executado " + LocalTime.now().toString());
    }
}


