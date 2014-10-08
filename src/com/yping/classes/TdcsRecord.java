package com.yping.classes;

/**
 * TdcsInfo只是一个简单的data class(纯数据类),对应不良信息的列分别是"不良信息来源"、"报告人"、"情况描述"、
 * "接收时刻"、"处理过程"、"故障原因"、"影响范围"
 * @author Administrator
 *
 */
public class TdcsRecord {

	public TdcsRecord(int sheetId,String source, String reportMan, String description,
			String receiveTime, String process, String reason, String range,
			 String remarks) {
		super();
		this.sheetId = sheetId;
		Source = source;
		ReportMan = reportMan;
		Description = description;
		ReceiveTime = receiveTime;
		Process = process;
		Reason = reason;
		Range = range;
		Remarks = remarks;
	}
	
	public TdcsRecord() {
		super();
		this.sheetId = -1;
		Source = "";
		ReportMan = "";
		Description = "";
		ReceiveTime = "";
		Process = "";
		Reason = "";
		Range = "";
		Remarks = "";
	}

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	public String getReportMan() {
		return ReportMan;
	}

	public void setReportMan(String reportMan) {
		ReportMan = reportMan;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getReceiveTime() {
		return ReceiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		ReceiveTime = receiveTime;
	}

	public String getProcess() {
		return Process;
	}

	public void setProcess(String process) {
		Process = process;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getRange() {
		return Range;
	}

	public void setRange(String range) {
		Range = range;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public int getSheetId() {
		return sheetId;
	}

	public void setSheetId(int sheetId) {
		this.sheetId = sheetId;
	}

	@Override
	public String toString() {
		return "TdcsRecord [Source=" + Source + ", ReportMan=" + ReportMan
				+ ", Description=" + Description + ", ReceiveTime="
				+ ReceiveTime + ", Process=" + Process + ", Reason=" + Reason
				+ ", Range=" + Range + ", Remarks=" + Remarks + ", sheetId="
				+ sheetId + "]";
	}

	private String Source; 		//不良信息来源,如:某个调度台、某间办公室、管外机构等等
	private String ReportMan;   //上报不良信息的人员,如调度员、段调、处调等等
	private String Description; //不良信息情况描述
	private String ReceiveTime; //不良信息接收时刻
	private String Process;     //不良信息处理过程
	private String Reason;      //导致不良信息产生的原因
	private String Range;	    //不良信息对业务的影响范围
	private String Remarks;		//备注
	private int sheetId;        //不良信息所在工作表
	
	//以上信息在电子表格中的列位置分别是1,2,3,4,5,6,8,13
	
}
