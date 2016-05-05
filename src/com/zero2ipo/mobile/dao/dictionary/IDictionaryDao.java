package com.zero2ipo.mobile.dao.dictionary;

import com.zero2ipo.module.entity.code.CodeInfoEntity;
import com.zero2ipo.module.entity.code.CodeSortEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 字典数据交互DAO
 * @author zhengyunfei
 *
 */
@Repository
public interface IDictionaryDao {

	/*
	 * 根据codeSortId查找codeInfo数据集合
	 */
	public List<CodeInfoEntity> findCodeInfoListBySortId(String sortId);
	public List<CodeInfoEntity> findCodeInfosByMap(Map<String,Object> queryMap);

	/*
	 * 根据codeValue查找codeInfo
	 */
	public List<CodeInfoEntity> findCodeInfoByCodeValues(String[] codeValues);

	/*
	 * 根据父类型子类型查找codeInfo信息
	 */
	public List<CodeInfoEntity> findCodeInfoByTypes(String supType, String version, String subType);

	/*
	 * 根据codeValue查找codeInfo对象
	 */
	public CodeInfoEntity findCodeInfoByCodeValue(String codeValue);

	/*
	 * 根据ID查找CodeInfo信息
	 */
	public CodeInfoEntity findCodeInfoByCodeId(String codeId);

	/*
	 * 根据类型与版本号查找CodeSort
	 */
	public CodeSortEntity findCodeInfoByVersionAndType(String version, String type);

	/*
	 * 根据父ID查找子数据
	 */
	public List<CodeInfoEntity> findCodeInfosByPcode(String codeValue);

}
