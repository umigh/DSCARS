package edu.gatech.omscs.dscars.action;

public class InstructorAdminAction extends SelectAction {
	private static final long serialVersionUID = 9149826260758390091L;
	

	public InstructorAdminAction() {
		super();
	}

	public String execute() {
		setLists();
	    return SUCCESS;
	}
}
