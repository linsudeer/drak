package com.linsu.modular.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linsu.core.support.HttpServletRequestHolder;
import com.linsu.core.support.IdGenerator;
import com.linsu.core.support.SqlHelper;
import com.linsu.modular.blog.dao.TagMapper;
import com.linsu.modular.blog.model.Tag;
import com.linsu.modular.blog.service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{
	
	@Autowired
	private TagMapper tagMapper;

	@Override
	@Transactional
	public long save(Tag tag) {
		long oid = IdGenerator.generateOId();
		tag.setOid(oid);
		tag.setTagUri("/tag/"+oid);
		if(SqlHelper.retBool(tagMapper.insertSelective(tag))){
			return tag.getOid();
		}
		return 0L;
	}

	@Override
	public Tag getByName(String name) {
		return tagMapper.getByName(name);
	}

	@Override
	public List<Tag> getTagsByName(String name, Integer maxCnt) {
		return tagMapper.getTagsByName(name, maxCnt==null?5:maxCnt);
	}

	@Override
	public List<Tag> getAllAvailableTags() {
		return tagMapper.getAllAvailableTags();
	}


}
