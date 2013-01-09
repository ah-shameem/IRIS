package com.temenos.interaction.sdk.interaction;

/**
 * This class holds information about a resource state machine
 */
public class IMTransition {

	private String targetEntityName;		//Entity name associated to target state
	private String linkProperty;			//The depend element of a referential constraint (i.e. the property that specifies the target resource)
	private String targetStateName;			//Name of target state
	private boolean isCollectionState;		//Indicates if target is a collection state
	private boolean isUniqueTransition = true;		//Workaround - indicates to create intermediate 'navigation' states for this transition
	private String reciprocalLinkState;		//State which leads a target state back to the current state
	private IMResourceStateMachine targetResourceStateMachine;	//Resource state machine of target state
	private String filter;					//Filter expression for transitions to collection resources
	private String title;					//Transition label

	public IMTransition(String targetEntityName, String linkProperty, String targetStateName, boolean isCollectionState, String reciprocalLinkState, IMResourceStateMachine targetResourceStateMachine, String filter, String title) {
		this.targetEntityName = targetEntityName;
		this.linkProperty = linkProperty;
		this.targetStateName = targetStateName;
		this.isCollectionState = isCollectionState;
		this.reciprocalLinkState = reciprocalLinkState;
		this.targetResourceStateMachine = targetResourceStateMachine;
		this.filter = filter;
		this.title = title;
	}
	
	public String getTargetEntityName() {
		return targetEntityName;
	}
	
	public String getLinkProperty() {
		return linkProperty;
	}

	public String getTargetStateName() {
		return targetStateName;
	}

	public boolean isCollectionState() {
		return isCollectionState;
	}
	
	public void notUniqueTransition() {
		this.isUniqueTransition = false; 
	}
	
	public boolean isUniqueTransition() {
		return isUniqueTransition;
	}

	public String getFilter() {
		return filter != null && !filter.equals("") ? filter : "1 eq 1";
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getTitleAssignment() {
		String title = "";
		if(getTitle() != null) {
			title = "title=\"" + getTitle() + "\" ";
		}
		return title;
	}
	
	/**
	 * Returns the resource state name of the target RSM which is either the
	 * reciprocal state or, if not defined, the entity state of the target RSM. 
	 * @return resource state name 
	 */
	public String getTargetRsmStateName() {
		return (reciprocalLinkState != null && !reciprocalLinkState.equals("")) ? targetResourceStateMachine.getEntityStateName() + "_" + reciprocalLinkState : targetResourceStateMachine.getEntityStateName();
	}
	
	public IMResourceStateMachine getTargetResourceStateMachine() {
		return targetResourceStateMachine;
	}
}