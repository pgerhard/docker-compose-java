package com.inventiosystems.docker;

import com.inventiosystems.docker_compose.DockerComposeClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class DockerComposeDriver {

    public static void main ( String[] args ) {
         new DockerComposeDriver ().run ();
    }

    public void run () {
        final ProcessBuilder processBuilder = new ProcessBuilder ();
        processBuilder.command ( "bash", "-c", new DockerComposeClient ()
                .compatibility ()
                .up ()
                .toString () );

//        processBuilder.inheritIO ();
        try {
            Process process = processBuilder.start ();
            StreamGobbler streamGobbler =
                    new StreamGobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            int exitCode = process.waitFor ();
            System.out.println ( "\nExited with error code : " + exitCode );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace ();
        }
    }

    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader (new InputStreamReader (inputStream)).lines()
                    .forEach(consumer);
        }
    }

}
