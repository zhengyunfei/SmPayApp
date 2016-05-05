package com.zero2ipo.mobile.services.dictionary.impl;

import com.zero2ipo.mobile.dao.dictionary.IDictionaryDao;
import com.zero2ipo.mobile.services.dictionary.IDictionaryService;
import com.zero2ipo.module.entity.code.CodeInfoEntity;
import com.zero2ipo.module.entity.code.CodeSortEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 字典数据操作服务接口
 * @author zhengyunfei
 *
 */
@Component("dictionaryService")
public class DictionaryServiceImpl implements IDictionaryService {

	Logger log = Logger.getLogger(DictionaryServiceImpl.class);

	/*
	 * 根据codeValue查找codeInfo
	 */
	public List<CodeInfoEntity> findCodeInfoByCodeValues(String[] codeValues) {
		return dictionaryDao.findCodeInfoByCodeValues(codeValues);
	}

	/*
	 * 根据父类型子类型查找codeInfo信息
	 */
	public List<CodeInfoEntity> findCodeInfoByTypes(String supType, String version, String subType) {
		return dictionaryDao.findCodeInfoByTypes(supType, version, subType);
	}

	/*
	 * 根据codeValue查找codeInfo对象
	 */
	public CodeInfoEntity findCodeInfoByCodeValue(String codeValue) {
		return dictionaryDao.findCodeInfoByCodeValue(codeValue);
	}

	/*
	 * 根据ID查找CodeInfo信息
	 */
	public CodeInfoEntity findCodeInfoByCodeId(String codeId) {
		return dictionaryDao.findCodeInfoByCodeId(codeId);
	}

	/*
	 * 根据sort查找codeinfos
	 */
	public List<CodeInfoEntity> findCodeInfosBySortcode(String codeType) {
		return dictionaryDao.findCodeInfoListBySortId(codeType);
	}
	public List<CodeInfoEntity> findCodeInfosByMap(Map<String,Object> queryMap) {
		return dictionaryDao.findCodeInfosByMap(queryMap);
	}
	/*
	 * 根据类型与版本号查找CodeSort
	 */
	public CodeSortEntity findCodeInfoByVersionAndType(String version, String type) {
		return dictionaryDao.findCodeInfoByVersionAndType(version, type);
	}

	/*
	 * 根据父ID查找子数据
	 */
	public List<CodeInfoEntity> findCodeInfosByPcode(String codeValue) {
		return dictionaryDao.findCodeInfosByPcode(codeValue);
	}

	@Resource(name = "dictionaryDao")
	private IDictionaryDao dictionaryDao;

}
