package service;

import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

public class AuditService {
    //clasa singleton
    private static AuditService single_instance = null;
    private BufferedWriter buffer;

    private AuditService(){
        try{
            String path = "src/files/Audit.csv";

            //clean file
            new FileWriter(path, false).close();

            buffer = new BufferedWriter(new FileWriter(path, true));
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static synchronized AuditService getInstance(){
        if (single_instance == null)
            single_instance = new AuditService();

        return single_instance;
    }

    public void WriteTimestamp(String action){
        try{
            Timestamp instant = Timestamp.from(Instant.now());

            buffer.write(action +"," + instant + "\n");
            buffer.flush();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
