package sample;

public  class FilterCalcul extends Filter {

    Pipe _dataINPipe;
    Pipe _dataOUTPipe;


    public FilterCalcul(Pipe _dataINPipe, Pipe _dataOUTPipe) {
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
	@Override
	public synchronized void execute() {
         Long res = Long.valueOf(0);
		// TODO Auto-generated method stub
		while (true) {
			int op1=Integer.parseInt(_dataINPipe.dataOUT());
			int op2=Integer.parseInt(_dataINPipe.dataOUT());
			String oper= _dataINPipe.dataOUT();
			switch(oper){
				case "s":
					res = somme(op1,op2);
					break;
				case "p":
					res = produit(op1,op2);
					break;
				case "f":
					res = fact(op1);
					break;
			}
			String out=oper+"\t"+op1+"\t"+op2+"\t"+res;
			_dataOUTPipe.dataIN(res.toString());
			_dataOUTPipe.dataIN(out);



		}
	}

	public static long somme(int a,int b){
		long result;
		result=a+b;

		return result;
	}
	public static long produit(int a,int b){
		long result;
		result=a*b;
		return result;
	}
	public static long fact(int a){
		long result=1;
		if (a>1) {
			int i;
			for(i=2;i<=a;i++){
				result=result*i;
			}
		}
		return result;
	}
}
 