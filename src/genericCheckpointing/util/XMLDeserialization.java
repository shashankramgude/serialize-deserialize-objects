/**
 * 
 */
package genericCheckpointing.util;

import genericCheckpointing.server.DeserStrategy;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shank
 * 
 */
public class XMLDeserialization implements DeserStrategy {

	private FileProcessor fileProcessor;

	public XMLDeserialization() {

	}

	/**
	 * @param fileProcessor
	 */
	public XMLDeserialization(FileProcessor fileProcessorIn) {
		fileProcessor = fileProcessorIn;
	}

	@Override
	public SerializableObject processInput() {

		SerializableObject serializableObject = null;
		try {
			String line = null;
			while((line = fileProcessor.readLineFromFile())!=null){
				if(line.contains("<complexType")){
					break;
				}
			}

			String className = line.substring(line.indexOf("\"") + 1,
					line.lastIndexOf("\""));
			Class<?> cls = Class.forName(className);
			serializableObject = (SerializableObject) cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				DeserializeTags deserializeTags = new DeserializeTags();
				line = fileProcessor.readLineFromFile();
				Class<?>[] signature = new Class<?>[1];
				if (line.contains("<myInt")) {
					signature[0] = int.class;
					Object[] params = new Object[1];
					params[0] = Integer.parseInt(deserializeTags.deserializeTag(line));
					Method methodInt = cls.getMethod("setmyInt",
							signature);
					methodInt.invoke(serializableObject, params);
				} else if (line.contains("<myLong")) {
					signature[0] = long.class;
					Object[] params = new Object[1];
					params[0] = Long.parseLong(deserializeTags.deserializeTag(line));
					Method methodInt = cls.getMethod("setmyLong",
							signature);
					methodInt.invoke(serializableObject, params);
				} else if (line.contains("<myString")) {
					signature[0] = String.class;
					Object[] params = new Object[1];
					params[0] = deserializeTags.deserializeTag(line);
					Method methodInt = cls.getMethod("setmyString",
							signature);
					methodInt.invoke(serializableObject, params);
				} else if (line.contains("<myBoolean")) {
					signature[0] = boolean.class;
					Object[] params = new Object[1];
					params[0] = Boolean.parseBoolean(deserializeTags.deserializeTag(line));
					Method methodInt = cls.getMethod("setmyBoolean",
							signature);
					methodInt.invoke(serializableObject, params);
				} else if (line.contains("<myDouble")) {
					signature[0] = double.class;
					Object[] params = new Object[1];
					params[0] = Double.parseDouble(deserializeTags.deserializeTag(line));
					Method methodInt = cls.getMethod("setmyDoubleT",
							signature);
					methodInt.invoke(serializableObject, params);
				} else if (line.contains("<myFloat")) {
					signature[0] = float.class;
					Object[] params = new Object[1];
					params[0] = Float.parseFloat(deserializeTags.deserializeTag(line));
					Method methodInt = cls.getMethod("setmyFloatT",
							signature);
					methodInt.invoke(serializableObject, params);
				} else if (line.contains("<myShort")) {
					signature[0] = short.class;
					Object[] params = new Object[1];
					params[0] = Short.parseShort(deserializeTags.deserializeTag(line));
					Method methodInt = cls.getMethod("setmyShortT",
							signature);
					methodInt.invoke(serializableObject, params);
				} else if (line.contains("<myChar")) {
					signature[0] = char.class;
					Object[] params = new Object[1];
					params[0] = deserializeTags.deserializeTag(line).charAt(0);
					Method methodInt = cls.getMethod("setmyCharT",
							signature);
					methodInt.invoke(serializableObject, params);
				} 
			}
		} catch (NumberFormatException e) {
			System.err.println("Error in parsing.");
			e.printStackTrace();
			System.exit(1);	
		} catch (IOException e) {
			System.err.println("Error in file writing.");
			e.printStackTrace();
			System.exit(1);
		} catch (NoSuchMethodException e) {
			System.err.println("No such method found.");
			e.printStackTrace();
			System.exit(1);
		} catch (SecurityException e) {
			System.err.println("Security Error.");
			e.printStackTrace();
			System.exit(1);
		} catch (IllegalAccessException e) {
			System.err.println("Access denied.");
			e.printStackTrace();
			System.exit(1);
		} catch (IllegalArgumentException e) {
			System.err.println("Illegal argument(s).");
			e.printStackTrace();
			System.exit(1);
		} catch (InvocationTargetException e) {
			System.err.println("Error in invocation.");
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			System.err.println("No such class found.");
			e.printStackTrace();
			System.exit(1);
		} catch (InstantiationException e) {
			System.err.println("Error in creating instance.");
			e.printStackTrace();
			System.exit(1);
		} finally{
			
		}
		return serializableObject;
	}
}
