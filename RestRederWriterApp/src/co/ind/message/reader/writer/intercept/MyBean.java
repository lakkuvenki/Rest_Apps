package co.ind.message.reader.writer.intercept;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyBean {
	@XmlElement
	public String anyString;
	@XmlElement
	public int anyNumber;

	public MyBean(String anyString, int anyNumber) {
		this.anyString = anyString;
		this.anyNumber = anyNumber;
	}

	// empty constructor needed for deserialization by JAXB
	public MyBean() {
	}
	
	/*public String getAnyString() {
		return anyString;
	}

	public void setAnyString(String anyString) {
		this.anyString = anyString;
	}

	public int getAnyNumber() {
		return anyNumber;
	}

	public void setAnyNumber(int anyNumber) {
		this.anyNumber = anyNumber;
	}*/

	@Override
	public String toString() {
		return "MyBean{" + "anyString='" + anyString + '\'' + ", anyNumber="
				+ anyNumber + '}';
	}
}
