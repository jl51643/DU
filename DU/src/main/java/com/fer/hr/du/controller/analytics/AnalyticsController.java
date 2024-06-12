package com.fer.hr.du.controller.analytics;

import com.fer.hr.du.model.analytics.Analytics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @PostMapping("")
    public ResponseEntity<Void> postAnalytics(@RequestBody Analytics analytics) {
        // Ime datoteke i putanja
        String filePath = "src/main/resources/logs/logs.txt";

        // Stvaranje objekta File
        File file = new File(filePath);

        try {
            // Provjera postoji li datoteka, ako ne postoji, stvori ju
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            // Zapisivanje primljenog zahtjeva u datoteku
            FileWriter writer = new FileWriter(file, true);
            writer.write(LocalDateTime.now() + ": Received analytics: " + analytics.toString() + "\n");
            writer.close();

            // Odgovor na zahtjev
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            e.printStackTrace();
            // U slučaju greške, vratimo odgovarajući odgovor
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }
}
