/**
 * 
 */
package genericCheckpointing.util;

/**
 * @author shank
 * 
 */
public class MyAllTypesFirst extends SerializableObject{
	private int myInt;
	private long myLong;
	private String myString;
	private boolean myBoolean;

	public MyAllTypesFirst() {

	}

	public MyAllTypesFirst(int myIntIn, long myLongIn, String myStringIn,
			boolean myBooleanIn) {
		super();
		myInt = myIntIn;
		myLong = myLongIn;
		myString = myStringIn;
		myBoolean = myBooleanIn;
	}

	public int getmyInt() {
		return myInt;
	}

	public void setmyInt(int myIntIn) {
		myInt = myIntIn;
	}

	public long getmyLong() {
		return myLong;
	}

	public void setmyLong(long myLongIn) {
		myLong = myLongIn;
	}

	public String getmyString() {
		return myString;
	}

	public void setmyString(String myStringIn) {
		myString = myStringIn;
	}

	public boolean getmyBoolean() {
		return myBoolean;
	}

	public void setmyBoolean(boolean myBooleanIn) {
		myBoolean = myBooleanIn;
	}

	@Override
	public String toString() {
		return "MyAllTypesFirst [myInt=" + myInt + ", myLong=" + myLong
				+ ", myString=" + myString + ", myBoolean=" + myBoolean + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (myBoolean ? 1231 : 1237);
		result = prime * result + myInt;
		result = prime * result + (int) (myLong ^ (myLong >>> 32));
		result = prime * result
				+ ((myString == null) ? 0 : myString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		MyAllTypesFirst other = (MyAllTypesFirst) obj;
		if ((obj == null) || (getClass() != obj.getClass()) ||(myBoolean != other.myBoolean) || (myInt != other.myInt) || (myLong != other.myLong))
			return false;
		if (myString == null) {
			if (other.myString != null)
				return false;
		} else if (!myString.equals(other.myString))
			return false;
		return true;
	}
}
