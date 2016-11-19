/**
 * 
 */
package genericCheckpointing.server;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

/**
 * @author shank
 *
 */
public interface StoreI extends StoreRestoreI {

	/**
	 * @param myFirst
	 * @param string
	 */
	public void writeObj(MyAllTypesFirst myFirst, String string);

	/**
	 * @param mySecond
	 * @param string
	 */
	public void writeObj(MyAllTypesSecond mySecond, String string);

}
