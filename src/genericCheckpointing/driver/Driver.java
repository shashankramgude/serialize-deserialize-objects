/**
 * 
 */
package genericCheckpointing.driver;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

import java.lang.reflect.InvocationHandler;
import java.util.Vector;

/**
 * @author shank
 * 
 */
public class Driver {
	public static void main(String[] args) {
		String FILE_NAME = null;
		int NUM_OF_OBJECTS;
		String MODE = null;

		try {
			if (args.length != 3) {
				System.err.println("Invalid number of argumnets.");
				System.exit(1);
			}
			MODE = args[0];
			NUM_OF_OBJECTS = Integer.parseInt(args[1]);
			FILE_NAME = args[2];
			ProxyCreator pc = new ProxyCreator();
			InvocationHandler handler = new StoreRestoreHandler(FILE_NAME, MODE);
			StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
					new Class[] { StoreI.class, RestoreI.class }, handler);
			MyAllTypesFirst myFirst;
			MyAllTypesSecond mySecond;
			
			if (MODE.equalsIgnoreCase("serdeser")) {
				((StoreRestoreHandler) handler).openFile(FILE_NAME, "W");
				Vector<SerializableObject> serializedObjects = new Vector<SerializableObject>();
				for (int i = 0; i < NUM_OF_OBJECTS; i++) {
					myFirst = new MyAllTypesFirst(i+10,(i+10)*999,"Serialize"+i,(i%2==0)?true:false);
					mySecond = new MyAllTypesSecond(i+100.53,(float)(i+20.2),(short)(i+30),'a');
					serializedObjects.add(myFirst);
					serializedObjects.add(mySecond);
					((StoreI) cpointRef).writeObj(myFirst, "XML");
					((StoreI) cpointRef).writeObj(mySecond, "XML");
				}
				((StoreRestoreHandler) handler).closeFile("W");
				
				((StoreRestoreHandler) handler).openFile(FILE_NAME, "R");
				Vector<SerializableObject> deserializedObjects = new Vector<SerializableObject>();
				SerializableObject serializableObject;
				for (int j=0; j<2*NUM_OF_OBJECTS; j++) {
					serializableObject = ((RestoreI) cpointRef).readObj("XML");
				    deserializedObjects.addElement(serializableObject);
				}
				
				((StoreRestoreHandler)handler).closeFile("R");
				int mismatchedCount = 0;
				for(int i=0;i<2*NUM_OF_OBJECTS;i++) {
					if(!serializedObjects.get(i).equals(deserializedObjects.get(i))){
						mismatchedCount++;
					}	
				}
				System.out.println(mismatchedCount+" mismatched objects.");
				
			} else if (MODE.equalsIgnoreCase("deser")) {
				((StoreRestoreHandler) handler).openFile(FILE_NAME, "R");
				
				SerializableObject serializableObject;
				for (int j=0; j<2*NUM_OF_OBJECTS; j++) {
					serializableObject = ((RestoreI) cpointRef).readObj("XML");
				    System.out.println("Deserialized object : "+serializableObject);
				}
				((StoreRestoreHandler)handler).closeFile("R");
			}
		} catch (NumberFormatException e) {
			System.err.println("Error in reading the arguments.");
			e.printStackTrace();
			System.exit(1);
		} finally {

		}
	}
}
