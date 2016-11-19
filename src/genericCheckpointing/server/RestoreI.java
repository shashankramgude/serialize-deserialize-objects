/**
 * 
 */
package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;

/**
 * @author shank
 *
 */
public interface RestoreI extends StoreRestoreI{
	SerializableObject readObj(String wire);
}
