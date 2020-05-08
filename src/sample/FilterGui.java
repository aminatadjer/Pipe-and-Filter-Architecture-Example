package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class FilterGui extends Filter{
Controller c;
String a,b;


    public FilterGui(Pipe _dataINPipe, Pipe _dataOUTPipe) {
        super();
        this._dataINPipe = _dataINPipe;
        this._dataOUTPipe = _dataOUTPipe;
    }


    Pipe _dataINPipe;
    Pipe _dataOUTPipe;

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

    public void setOp(){
         a=c.op1.getText();
         b=c.op2.getText();
        if (a.isEmpty()) {
           a="0";
           c.op1.setText("0");

        }
        if(b.isEmpty()){
            b="0";
            c.op2.setText("0");
        }
    }
    @Override
    public synchronized void execute() {

        // create JavaFX scene

            new Thread() {
                @Override
                public void run() {
                    javafx.application.Application.launch(Controller.class);
                }
            }.start();
         c = Controller.waitForLaunch();


        c.produit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setOp();
                String o = "p";
                _dataOUTPipe.dataIN(a);
                _dataOUTPipe.dataIN(b);
                _dataOUTPipe.dataIN(o);

            }

        });
        c.somme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                setOp();
                String o = "s";
                _dataOUTPipe.dataIN(a);
                _dataOUTPipe.dataIN(b);
                _dataOUTPipe.dataIN(o);
            }
        });
        c.facto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                setOp();
                String o = "f";
                _dataOUTPipe.dataIN(a);
                _dataOUTPipe.dataIN(b);
                _dataOUTPipe.dataIN(o);
            }
        });
        c.trace.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String o = "n";
                c.loadTrace();
            }
        });

    }

}
