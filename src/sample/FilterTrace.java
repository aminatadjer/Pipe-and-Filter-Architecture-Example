package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterTrace extends Filter{
    Pipe _dataINPipe;
    Pipe _dataOUTPipe;


    public FilterTrace(Pipe _dataINPipe, Pipe _dataOUTPipe) {
        super();
        this._dataINPipe = _dataINPipe;
        this._dataOUTPipe = _dataOUTPipe;
    }
    public String getData(){
        return _dataINPipe.dataOUT();
    }

    public void sendData( String tempData){
        _dataOUTPipe.dataIN(tempData);
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        execute();
    }

    public static void savop(String trace) {
        File log = new File("traces.txt");
        try{
            if(log.exists()==false){
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.append(trace+"\n");
            out.close();
        }catch(IOException e){
            System.out.println("COULD NOT LOG!!");
        }
    }
    public static List<String> lireop(){
        List<String> result= new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get("traces.txt"), StandardCharsets.US_ASCII)) {
            result = lines.collect(Collectors.toList());
            result.forEach(System.out::println);
            lines.close();
            Collections.reverse(result);
            return result;
        }
        catch (IOException e){
            result.add("Aucune trace");
            return result;
        }


    }

    @Override
    public synchronized void execute() {
            // TODO Auto-generated method stub
            while (true){
                String res= _dataINPipe.dataOUT();
                String trace = _dataINPipe.dataOUT() ;
                savop(trace);
                Platform.runLater(()->{
                   Controller.resultLabel.setText(res);
                });
            }

        }

    }


