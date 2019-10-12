package co.nit.data.flow.resource;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataFlow implements Serializable {

	private static final long serialVersionUID = 1848776975993438051L;
	
	private String dataflowId;
	private String dataflowName;
	private String dataflowSeg;
	private int srcStepId;
	private int trgtStepId;

	@XmlElement
	public String getDataflowId() {
		return dataflowId;
	}

	public void setDataflowId(String dataflowId) {
		this.dataflowId = dataflowId;
	}

	@XmlElement
	public String getDataflowName() {
		return dataflowName;
	}

	public void setDataflowName(String dataflowName) {
		this.dataflowName = dataflowName;
	}

	@XmlElement
	public String getDataflowSeg() {
		return dataflowSeg;
	}

	public void setDataflowSeg(String dataflowSeg) {
		this.dataflowSeg = dataflowSeg;
	}

	@XmlElement
	public int getSrcStepId() {
		return srcStepId;
	}

	public void setSrcStepId(int srcStepId) {
		this.srcStepId = srcStepId;
	}

	@XmlElement
	public int getTrgtStepId() {
		return trgtStepId;
	}

	public void setTrgtStepId(int trgtStepId) {
		this.trgtStepId = trgtStepId;
	}

	@Override
	public String toString() {
		return "DataFlow [dataflowId=" + dataflowId + ", dataflowName="
				+ dataflowName + ", dataflowSeg=" + dataflowSeg
				+ ", srcStepId=" + srcStepId + ", trgtStepId=" + trgtStepId
				+ "]";
	}

}
