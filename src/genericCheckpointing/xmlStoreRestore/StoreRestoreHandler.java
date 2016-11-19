/**
 * 
 */
package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.server.DeserStrategy;
import genericCheckpointing.server.SerStrategy;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.XMLDeserialization;
import genericCheckpointing.util.XMLSerialization;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author shank
 * 
 */
public class StoreRestoreHandler implements InvocationHandler {

	private String fileName;
	private FileProcessor fileProcessor;
	private String mode;

	public StoreRestoreHandler() {
	}

	public StoreRestoreHandler(String fileNameIn, String modeIn) {
		fileName = fileNameIn;
		mode = modeIn;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodName = method.getName();
		if (methodName.equals("writeObj")) {
			String wireFormat = (String) args[1];
			if (wireFormat.equals("XML"))
				serializeData((SerializableObject) args[0],
						new XMLSerialization(fileProcessor));
		} else if (methodName.equals("readObj")) {
			String wireFormat = (String) args[0];
			if (wireFormat.equals("XML")) {
				SerializableObject sObject = deserializeData(new XMLDeserialization(
						fileProcessor));
				return sObject;
			}
		}
		return null;
	}

	public void openFile(String fileName, String mode) {
		fileProcessor=new FileProcessor(fileName, mode);
	}

	public void closeFile(String mode) {
		if(mode.equalsIgnoreCase("R")){
			fileProcessor.closeBufferedReader();
		} else{
			fileProcessor.closeBufferedWriter();
		}
		
	}
	
	public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
        
		 sStrategy.processInput(sObject);
	}
	
	public SerializableObject deserializeData(DeserStrategy sStrategy) {
       
		 SerializableObject sObject=sStrategy.processInput();
		 
		 return sObject;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileNameIn) {
		fileName = fileNameIn;
	}

	public FileProcessor getFileProcessor() {
		return fileProcessor;
	}

	public void setFileProcessor(FileProcessor fileProcessorIn) {
		fileProcessor = fileProcessorIn;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String modeIn) {
		mode = modeIn;
	}

	@Override
	public String toString() {
		return "StoreRestoreHandler [fileName=" + fileName + ", fileProcessor="
				+ fileProcessor + ", mode=" + mode + "]";
	}
}
