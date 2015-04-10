package edu.gatech.omscs.dscars.action;

public class CourseAdminAction extends SelectAction {
	private static final long serialVersionUID = 9149826260758390091L;
	

	public CourseAdminAction() {
		super();
	}

	public String execute() {
		setLists();
	    return SUCCESS;
	}
}
