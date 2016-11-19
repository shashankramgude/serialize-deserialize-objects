/**
 * 
 */
package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;

/**
 * @author shank
 *
 */
public interface DeserStrategy {
	SerializableObject processInput();
}
