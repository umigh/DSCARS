package edu.gatech.omscs.dscars.action;

public class SummaryAdminAction extends SelectAction {
	private static final long serialVersionUID = 9149826260758390091L;
	public SummaryAdminAction() {
		super();
	}

	public String execute() {
		setLists();
		setSectionList();
	    return SUCCESS;
	}
}
