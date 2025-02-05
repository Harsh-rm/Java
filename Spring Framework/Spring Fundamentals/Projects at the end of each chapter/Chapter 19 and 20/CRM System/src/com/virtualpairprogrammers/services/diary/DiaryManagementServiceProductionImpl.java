package com.virtualpairprogrammers.services.diary;

import java.util.List;

import com.virtualpairprogrammers.dataaccess.ActionDao;
import com.virtualpairprogrammers.domain.Action;

public class DiaryManagementServiceProductionImpl implements DiaryManagementService 
{
	private ActionDao dao;
	
	public DiaryManagementServiceProductionImpl(ActionDao dao)
	{
		this.dao = dao;
	}
	
	@Override
	public void recordAction(Action action) 
	{
		dao.create(action);
	}

	@Override
	public List<Action> getAllIncompleteActions(String requiredUser) 
	{
		return dao.getIncompleteActions(requiredUser);
	}

}
