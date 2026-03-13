package com.je.xml.reader;

import com.je.utils.Console;
import com.je.utils.IterableNodeList;

import java.io.*;
import java.lang.reflect.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import java.util.*;

public class JeXmlReader<T> {
	public static final String FORMAT_NODE_VALUE = "je.reader.format.<fieldName>value</fieldName>";

	public T read(InputStream input, Class<T> clazz, String formatType) {
		// String xml = null;
		// try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
		// 	StringBuilder builder = new StringBuilder();
		// 	String line = null;
		// 	while((line = reader.readLine()) != null) {
		// 		builder.append(line);
		// 	}
		// 	xml = builder.toString();
		// } catch (IOException e) {
		// 	throw new RuntimeException(e);
		// }
		// assert xml != null;

		T t;

		try {
			Constructor<T> constructor = clazz.getConstructor();
			constructor.setAccessible(true);
			t = constructor.newInstance();
		} catch (InstantiationException | 
				IllegalAccessException | 
				InvocationTargetException |
				NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		assert t != null;

		Document document;
		try {
			DocumentBuilder builder = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder();
			document = builder.parse(input);
		} catch(ParserConfigurationException | SAXException | IOException e) {
			throw new RuntimeException(e);
		}
		assert document != null;

		try {
		Console.get().debug("Working with class "+clazz.getName());
		Field[] fields = clazz.getFields();

        for(Field field: fields){
            readValueInNode(document, t, field);
        }
        } catch (Exception e) {
        	Console.get().debug(e);
        }

		return t;
	}

	private void readValueInNode(Document document, T t, Field field) throws Exception {
	    Console.get().debug("Searching for tags named '"+field.getName()+"'");
		NodeList nodes = document.getElementsByTagName(field.getName());
		for(Node node: new IterableNodeList(nodes)) {
		    if(node instanceof Element element) {
		        String content = element.getTextContent();
				Console.get().debug("Found value '"+content+"'");
                field.set(t, content);
		    }
		}
	}
}
