interface IFrontBookkeeper {
    String updateFront(String[] news);
}

public class FrontBookkeeper61790 implements IFrontBookkeeper {
	public FrontBookkeeper61790(){
	}
	
	public String updateFront(String[] news) {
	    NewsTransformer n = new NewsTransformer(news); 
	    n.sendCommands();
	    String toReturn = n.getUpdateFront();
		return toReturn;
	}

}

