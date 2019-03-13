package utilities;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogsWriter {
    private LocalDateTime time;
    private FileWriter fstream;
    private BufferedWriter out;
    public LogsWriter(){
        time = LocalDateTime.now();
        String filename = "logs_" + time.getDayOfMonth() + time.getMonthValue() + time.getYear() + "_" + time.getHour() + time.getMinute() + time.getSecond() + ".log";
        try {
            this.fstream = new FileWriter(new File(".").getAbsolutePath() + "//logs//" +filename, true);
            this.out = new BufferedWriter(fstream);
            System.out.println("logs saved");
            out.write( time.getDayOfMonth()  + "/" + time.getMonthValue()+ "/" + time.getYear() + ", " + time.getHour() + ":" + time.getMinute());
            out.flush();
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeLogs(String logs){
        try {
            out.write(printTime());
            out.write(logs);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public String printTime(){
        String actualTime = "";
        actualTime = "[" + time.getHour() + ":" + time.getMinute() + "]";
        return actualTime;
    }

    public static void main(String[] args) {
    }
}
