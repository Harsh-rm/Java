package com.virtualpairprogrammers.restcontrollers;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.virtualpairprogrammers.domain.Action;

@XmlRootElement(name="actions")
public class ActionCollection 
{
	private List<Action> actions;
	
	public ActionCollection() {}
	
	public ActionCollection(List<Action> actions)
	{
		this.actions = actions;
	}

	@XmlElement(name="action")
	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	
}
