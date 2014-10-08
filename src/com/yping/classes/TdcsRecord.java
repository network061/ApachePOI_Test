package com.yping.classes;

/**
 * TdcsInfoֻ��һ���򵥵�data class(��������),��Ӧ������Ϣ���зֱ���"������Ϣ��Դ"��"������"��"�������"��
 * "����ʱ��"��"�������"��"����ԭ��"��"Ӱ�췶Χ"
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

	private String Source; 		//������Ϣ��Դ,��:ĳ������̨��ĳ��칫�ҡ���������ȵ�
	private String ReportMan;   //�ϱ�������Ϣ����Ա,�����Ա���ε��������ȵ�
	private String Description; //������Ϣ�������
	private String ReceiveTime; //������Ϣ����ʱ��
	private String Process;     //������Ϣ�������
	private String Reason;      //���²�����Ϣ������ԭ��
	private String Range;	    //������Ϣ��ҵ���Ӱ�췶Χ
	private String Remarks;		//��ע
	private int sheetId;        //������Ϣ���ڹ�����
	
	//������Ϣ�ڵ��ӱ���е���λ�÷ֱ���1,2,3,4,5,6,8,13
	
}
