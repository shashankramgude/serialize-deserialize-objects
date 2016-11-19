/**
 * 
 */
package genericCheckpointing.util;

import genericCheckpointing.server.SerStrategy;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shank
 *
 */
public class XMLSerialization implements SerStrategy{

	private FileProcessor fileProcessor;
	
	public XMLSerialization(){
		
	}
	
	/**
	 * @param fileProcessor
	 */
	public XMLSerialization(FileProcessor fileProcessorIn) {
		fileProcessor = fileProcessorIn;
	}

	@Override
	public void processInput(SerializableObject sObject) {
		Class<?> cls = sObject.getClass();
		SerializeTags serializeTags = new SerializeTags();
		Field[] filedList = cls.getDeclaredFields();
		try {
			fileProcessor.writeLineToFile(serializeTags.getXMLStartTag());
			fileProcessor.writeLineToFile(serializeTags.getXMLComplexTypeStartTag(cls.getName()));
			
			for (Field field : filedList) {
				if(field.getType() == int.class){
					Method method = cls.getMethod("get"+field.getName());
					int value = (int) method.invoke(sObject);
					fileProcessor.writeLineToFile(serializeTags.getXMLMyTag(field.getName(), Integer.toString(value)));
				} else if(field.getType() == long.class){
					Method method = cls.getMethod("get"+field.getName());
					long value = (long) method.invoke(sObject);
					fileProcessor.writeLineToFile(serializeTags.getXMLMyTag(field.getName(), Long.toString(value)));
				}  else if(field.getType() == String.class){
					Method method = cls.getMethod("get"+field.getName());
					String value = (String) method.invoke(sObject);
					fileProcessor.writeLineToFile(serializeTags.getXMLMyTag(field.getName(), value));
				}  else if(field.getType() == boolean.class){
					Method method = cls.getMethod("get"+field.getName());
					boolean value = (boolean) method.invoke(sObject);
					fileProcessor.writeLineToFile(serializeTags.getXMLMyTag(field.getName(), Boolean.toString(value)));
				}  else if(field.getType() == double.class){
					Method method = cls.getMethod("get"+field.getName());
					double value = (double) method.invoke(sObject);
					fileProcessor.writeLineToFile(serializeTags.getXMLMyTag(field.getName(), Double.toString(value)));
				} else if(field.getType() == float.class){
					Method method = cls.getMethod("get"+field.getName());
					float value = (float) method.invoke(sObject);
					fileProcessor.writeLineToFile(serializeTags.getXMLMyTag(field.getName(), Float.toString(value)));
				} else if(field.getType() == short.class){
					Method method = cls.getMethod("get"+field.getName());
					short value = (short) method.invoke(sObject);
					fileProcessor.writeLineToFile(serializeTags.getXMLMyTag(field.getName(), Short.toString(value)));
				} else if(field.getType() == char.class){
					Method method = cls.getMethod("get"+field.getName());
					char value = (char) method.invoke(sObject);
					fileProcessor.writeLineToFile(serializeTags.getXMLMyTag(field.getName(), Character.toString(value)));
				} 
			}
			fileProcessor.writeLineToFile(serializeTags.getXMLComplexTypeEndTag());
			fileProcessor.writeLineToFile(serializeTags.getXMLEndTag());
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
		} finally{
			
		}
		
	}

}
