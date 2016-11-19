/**
 * 
 */
package genericCheckpointing.util;

/**
 * @author shank
 *
 */
public class SerializeTags {
	
	private enum Tags{
		INT,
		LONG,
		STRING,
		BOOLEAN,
		DOUBLE,
		FLOAT,
		SHORT,
		CHAR	
	}

	public String getXMLStartTag(){
		return "<DPSerialization>";
	}
	
	public String getXMLEndTag(){
		return "</DPSerialization>";
	}
	
	public String getXMLComplexTypeStartTag(String type){
		return " <complexType xsi:type=\""+type+"\">";
	}
	

	public String getXMLComplexTypeEndTag(){
		return " </complexType>";
	}
	

	public String getXMLMyTag(String tagName, String tagValue){
		if(tagName.toUpperCase().contains(Tags.INT.toString())){
			return "  <"+tagName+" xsi:type=\"xsd:int\">"+tagValue+"</"+tagName+">";	
		} else if(tagName.toUpperCase().contains(Tags.LONG.toString())){
			return "  <"+tagName+" xsi:type=\"xsd:long\">"+tagValue+"</"+tagName+">";	
		} else if(tagName.toUpperCase().contains(Tags.STRING.toString())){
			return "  <"+tagName+" xsi:type=\"xsd:string\">"+tagValue+"</"+tagName+">";	
		} else if(tagName.toUpperCase().contains(Tags.BOOLEAN.toString())){
			return "  <"+tagName+" xsi:type=\"xsd:boolean\">"+tagValue+"</"+tagName+">";	
		} else if(tagName.toUpperCase().contains(Tags.DOUBLE.toString())){
			return "  <"+tagName+" xsi:type=\"xsd:double\">"+tagValue+"</"+tagName+">";	
		} else if(tagName.toUpperCase().contains(Tags.FLOAT.toString())){
			return "  <"+tagName+" xsi:type=\"xsd:float\">"+tagValue+"</"+tagName+">";	
		} else if(tagName.toUpperCase().contains(Tags.SHORT.toString())){
			return "  <"+tagName+" xsi:type=\"xsd:short\">"+tagValue+"</"+tagName+">";	
		} else if(tagName.toUpperCase().contains(Tags.CHAR.toString())){
			return "  <"+tagName+" xsi:type=\"xsd:char\">"+tagValue+"</"+tagName+">";	
		} else
			return null;
	}
}
