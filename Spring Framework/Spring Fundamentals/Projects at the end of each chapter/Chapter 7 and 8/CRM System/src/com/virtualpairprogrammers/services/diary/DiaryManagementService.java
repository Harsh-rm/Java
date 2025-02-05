 package com.virtualpairprogrammers.services.diary;

import java.util.List;

import com.virtualpairprogrammers.domain.Action;

/**
 * This interface defines the functionality required in the Diary Management Service.
 *
 * @author Richard Chesterwood
 */
public interface DiaryManagementService 
{
	/**
	 * Records an action in the diary
	 */
	public void recordAction(Action action);
	
	/**
	 * Gets all actions for a particular user that have not yet been complete
	 */
	public List<Action> getAllIncompleteActions(String requiredUser);
}
