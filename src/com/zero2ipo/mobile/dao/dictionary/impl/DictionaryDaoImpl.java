package com.zero2ipo.mobile.dao.dictionary.impl;

import com.zero2ipo.mobile.dao.base.IbatisBaseDao;
import com.zero2ipo.mobile.dao.dictionary.IDictionaryDao;
import com.zero2ipo.mobile.services.dictionary.impl.DictionaryServiceImpl;
import com.zero2ipo.module.entity.code.CodeInfoEntity;
import com.zero2ipo.module.entity.code.CodeSortEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典数据交互DAO
 * @author zhengyunfei
 *
 */
@Component("dictionaryDao")
public class DictionaryDaoImpl extends IbatisBaseDao implements IDictionaryDao {

	private static final String FINDCODEINFOS_BY_SORTID = "zero2ipo.mobile.dictionary.findCodeInfoListBySortId";
	private static final String FINDCODEINFOS_BY_MAP = "zero2ipo.mobile.dictionary.findCodeInfoListByMap";
	private static final String FINDCODEINFO_BY_CODEVALUES = "zero2ipo.mobile.dictionary.findCodeInfoByCodeValues";

	private static final String FINDCODEINFO_BY_TYPES = "zero2ipo.mobile.dictionary.findCodeInfoByTypes";
	private static final String FINDCODEINFO_BY_VALUE = "zero2ipo.mobile.dictionary.findCodeInfoByCodeValue";

	private static final String FIND_CODEINFO_CODEINFOID = "zero2ipo.mobile.dictionary.findCodeInfoByCodeId";

	private static final String FIND_CODESORT_VERSIONTYPE = "zero2ipo.mobile.dictionary.findCodeSortByVersionAndType";

	private static final String FIND_CODEINFO_PCODE = "zero2ipo.mobile.dictionary.findCodeInfosByPcode";

	Logger log = Logger.getLogger(DictionaryServiceImpl.class);

	/*
	 * 根据codeSortId查找codeInfo数据集合
	 */
	@SuppressWarnings("unchecked")
	public List<CodeInfoEntity> findCodeInfoListBySortId(String pcodeType) {
		Map<String,Object> queryMap=new HashMap<String, Object>();
		queryMap.put("codeType",pcodeType);
		return (List<CodeInfoEntity>) this.queryAll(FINDCODEINFOS_BY_SORTID, queryMap);
	}
	public List<CodeInfoEntity> findCodeInfosByMap(Map<String,Object> queryMap) {
		return (List<CodeInfoEntity>) this.queryAll(FINDCODEINFOS_BY_MAP, queryMap);
	}
	/*
	 * 根据codeValue查找codeInfo
	 */
	@SuppressWarnings("unchecked")
	public List<CodeInfoEntity> findCodeInfoByCodeValues(String[] codeValues) {

		return (List<CodeInfoEntity>) this.queryAll(FINDCODEINFO_BY_CODEVALUES, codeValues);
	}

	/*
	 * 根据父类型子类型查找codeInfo信息
	 */
	@SuppressWarnings("unchecked")
	public List<CodeInfoEntity> findCodeInfoByTypes(String supType, String version, String subType) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("supType", supType);
		params.put("version", version);
		params.put("subType", subType);

		return (List<CodeInfoEntity>) this.queryAll(FINDCODEINFO_BY_TYPES, params);
	}

	/*
	 * 根据codeValue查找codeInfo对象
	 */
	public CodeInfoEntity findCodeInfoByCodeValue(String codeValue) {
		return (CodeInfoEntity) this.query(FINDCODEINFO_BY_VALUE, codeValue);
	}

	/*
	 * 根据ID查找CodeInfo信息
	 */
	public CodeInfoEntity findCodeInfoByCodeId(String codeId) {

		return (CodeInfoEntity) this.query(FIND_CODEINFO_CODEINFOID, codeId);
	}

	/*
	 * 根据类型与版本号查找CodeSort
	 */
	public CodeSortEntity findCodeInfoByVersionAndType(String version, String type) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("version", version);

		return (CodeSortEntity) this.query(FIND_CODESORT_VERSIONTYPE, params);
	}

	/*
	 * 根据父ID查找子数据
	 */
	@SuppressWarnings("unchecked")
	public List<CodeInfoEntity> findCodeInfosByPcode(String codeValue) {

		return (List<CodeInfoEntity>) this.queryAll(FIND_CODEINFO_PCODE, codeValue);
	}

}
