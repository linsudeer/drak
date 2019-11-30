package com.linsu;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.linsu.modular.blog.dao.ArticleMapper;
import com.linsu.modular.blog.dto.ArticleDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
	
	@Autowired
	private ArticleMapper articleMapper;

	@Test
	public void contextLoads() {
		List<ArticleDTO> listByCategory = articleMapper.listByTag(1L, 0, 10, "");
		System.out.println(JSONArray.toJSONString(listByCategory));
	}

}
