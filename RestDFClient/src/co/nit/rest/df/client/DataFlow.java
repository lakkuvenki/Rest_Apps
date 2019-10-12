package co.nit.rest.df.client;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataFlow implements Serializable {

	private static final long serialVersionUID = 1848776975993438051L;
	@XmlElement
	private String dataflowId;
	@XmlElement
	private String dataflowName;
	@XmlElement
	private String dataflowSeg;
	@XmlElement
	private int srcStepId;
	@XmlElement
	private int trgtStepId;

	public String getDataflowId() {
		return dataflowId;
	}

	public void setDataflowId(String dataflowId) {
		this.dataflowId = dataflowId;
	}

	public String getDataflowName() {
		return dataflowName;
	}

	public void setDataflowName(String dataflowName) {
		this.dataflowName = dataflowName;
	}

	public String getDataflowSeg() {
		return dataflowSeg;
	}

	public void setDataflowSeg(String dataflowSeg) {
		this.dataflowSeg = dataflowSeg;
	}

	public int getSrcStepId() {
		return srcStepId;
	}

	public void setSrcStepId(int srcStepId) {
		this.srcStepId = srcStepId;
	}

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
