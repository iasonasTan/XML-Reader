package main;

import com.je.xml.reader.JeXmlReader;
import com.je.console.ConsoleController;
import java.io.*;
import com.je.xml.writer.JeXmlWriter;

public class Main {
	public static final class Data {
		public String yes, hello, no, value;

		public Data() {
		}

		@Override
		public String toString() {
		    return "Data{\n\t"+
				yes+",\n\t"+
				hello+",\n\t"
				+no+",\n\t"+
				value+
		    "\n}";
		}
	}

	public static void main(String[] args) {
		ConsoleController.setConsoleEnabled(true);
		InputStream xmlStream = Main.class.getResourceAsStream("/data.xml");
		JeXmlReader<Data> reader = new JeXmlReader<>();
		Data d = reader.read(xmlStream, Data.class);
		IO.println("Unchanged: "+d);
		d.value = "This is changed...";
		d.hello = "This is changed...";
		IO.println("Changed: "+d);
		try (OutputStream outputStream = new FileOutputStream(args[0])) {
			new JeXmlWriter<Data>().write(outputStream, d);
		} catch (Exception e) {
			IO.println(e);
		}
	}
}
