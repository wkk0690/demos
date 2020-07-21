package com.example.demo.file_change_listener;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class Sample {
 
    private WatchService watcher;
 
    private Path path;
 
    public Sample(Path path) throws IOException {
        this.path = path;
        watcher = FileSystems.getDefault().newWatchService();
        this.path.register(watcher, OVERFLOW, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
    }
 
    public void handleEvents() throws InterruptedException {
        // start to process the data files
        while (true) {
            // start to handle the file change event
            final WatchKey key = watcher.take();
 
            for (WatchEvent<?> event : key.pollEvents()) {
                // get event type
                final WatchEvent.Kind<?> kind = event.kind();
 
                // get file name
                @SuppressWarnings("unchecked")
                final WatchEvent<Path> pathWatchEvent = (WatchEvent<Path>) event;
                final Path fileName = pathWatchEvent.context();
 
                if (kind == ENTRY_CREATE) {
 
                    System.out.println("ENTRY_CREATE");
                    // 说明点1
                    // create a new thread to monitor the new file
                    new Thread(new Runnable() {
                        public void run() {
                            File file = new File(path.toFile().getAbsolutePath() + "/" + fileName);
                            boolean exist;
                            long size = 0;
                            long lastModified = 0;
                            int sameCount = 0;
                            while (exist = file.exists()) {
                                // if the 'size' and 'lastModified' attribute keep same for 3 times,
                                // then we think the file was transferred successfully
                                if (size == file.length() && lastModified == file.lastModified()) {
                                    if (++sameCount >= 3) {
                                        break;
                                    }
                                } else {
                                    size = file.length();
                                    lastModified = file.lastModified();
                                }
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    return;
                                }
                            }
                            // if the new file was cancelled or deleted
                            if (!exist) {
                                return;
                            } else {
 
                            }
                        }
                    }).start();
                } else if (kind == ENTRY_DELETE) {
                    System.out.println("ENTRY_DELETE");
                } else if (kind == ENTRY_MODIFY) {
                    System.out.println("ENTRY_MODIFY");
                } else if (kind == OVERFLOW) {
                    System.out.println("OVERFLOW");
                }
            }
 
            // IMPORTANT: the key must be reset after processed
            if (!key.reset()) {
                return;
            }
        }
    }
 
    public static void main(String args[]) throws IOException, InterruptedException {
        new Sample(Paths.get("D:\\T")).handleEvents();
    }
}