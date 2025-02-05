package com.virtualpairprogrammers.services.diary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virtualpairprogrammers.dataaccess.ActionDao;
import com.virtualpairprogrammers.domain.Action;

@Transactional
@Service
public class DiaryManagementServiceProductionImpl implements DiaryManagementService 
{
	private ActionDao dao;
	
	@Autowired
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
