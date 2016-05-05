package com.zero2ipo.dictionary.webc;

import com.zero2ipo.mobile.services.dictionary.IDictionaryService;
import com.zero2ipo.module.entity.code.CodeInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengyunfei on 2015/8/31.
 */
@Controller
public class DictionaryAct {
    /*
	 * 核心服务接口注入
	 */
    @Autowired
	public IDictionaryService dictionaryService;
	/**
	 * 根据pcode查询其下面所有codeinfo
	 */
	@RequestMapping(value = "/water/getCodeInfoList.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addAddressForPost(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String pCode,RedirectAttributes redirectAttributes ) {
		Map<String,Object> resultMap=new HashMap<String, Object>();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("pCode",pCode);
		List<CodeInfoEntity> codeInfoEntityList=dictionaryService.findCodeInfosByMap(map);
		resultMap.put("list",codeInfoEntityList);
		return resultMap;
	}

}

