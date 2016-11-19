/**
 * 
 */
package genericCheckpointing.util;

/**
 * @author shank
 *
 */
public class DeserializeTags {
	public String deserializeTag(String tag){
		return tag.substring(tag.indexOf(">")+1, tag.lastIndexOf("<"));
	}
}
