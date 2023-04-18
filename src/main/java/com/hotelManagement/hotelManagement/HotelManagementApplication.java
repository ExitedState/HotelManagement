package com.hotelManagement.hotelManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
public class HotelManagementApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HotelManagementApplication.class, args);
//        // Open default web browser with the URL of the application
//        String url = "http://localhost:8081/";
//        String osName = System.getProperty("os.name").toLowerCase();
//        Runtime rt = Runtime.getRuntime();
//        try {
//            if (osName.contains("win")) {
//                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
//            } else if (osName.contains("mac")) {
//                rt.exec("open " + url);
//            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
//                String[] browsers = { "epiphany", "firefox", "mozilla", "konqueror", "netscape", "opera", "links",
//                        "lynx" };
//                String browser = null;
//                for (String b : browsers) {
//                    if (rt.exec(new String[] { "which", b }).waitFor() == 0) {
//                        browser = b;
//                        break;
//                    }
//                }
//                if (browser == null) {
//                    throw new Exception("Could not find web browser");
//                } else {
//                    rt.exec(new String[] { browser, url });
//                }
//            } else {
//                throw new Exception("Unsupported operating system");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
