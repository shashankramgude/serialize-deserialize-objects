/**
 * 
 */
package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;

/**
 * @author shank
 *
 */
public interface SerStrategy {
	void processInput(SerializableObject sObject);
}
