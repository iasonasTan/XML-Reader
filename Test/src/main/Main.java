package main;

import com.je.xml.reader.JeXmlReader;
import com.je.console.ConsoleController;

public class Main {
	public static final class Data {
		public String yes;
		public String hello;
		public String no;
		public String value;
	
		public Data() {
		}
		
		@Override
		public String toString() {
		    return "Data{"+yes+", "+hello+", "+no+", "+value+"}";
		}
	}

	public static void main(String[] args) {
		ConsoleController.setConsoleEnabled(true);
		JeXmlReader<Data> reader = new JeXmlReader<>();
		Data d = reader.read(Main.class.getResourceAsStream("/data.xml"),
			Data.class,
			JeXmlReader.FORMAT_NODE_VALUE);
		IO.println(d);
	}
}
