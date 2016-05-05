<#-- 自定义的分页指令。
属性说明：
   pageNo      当前页号(int类型)
   pageSize    每页要显示的记录数(int类型)
   toURL       点击分页标签时要跳转到的目标URL(string类型)
   recordCount 总记录数(int类型)
 使用方式：
  <#if recordCount??>
    <#import "../ftl/fts_pagination.ftl" as p>
    <@p.pagination pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="xxxxx"/>
  </#if>
 -->

 <!-- 定义标签属性 -->
 <#macro pagination pageNo pageSize toURL recordCount>

	<!-- 分页请求处理 -->

 	<#-- 定义局部变量pageCount保存总页数 -->
 	<#assign pageCount=((recordCount + pageSize - 1) / pageSize)?int>
 	<#if recordCount==0><#return/></#if>
 	<#assign count=4?int>

 	<#-- 页号越界处理 -->
	<#if (pageNo > pageCount)>
		<#assign pageNo=pageCount>
	</#if>
	<#if (pageNo < 1)>
		<#assign pageNo=1>
	</#if>

	<#-- 输出分页表单 -->
	<input type="hidden" name="pageNo" value="${pageNo}"/>
	<input type="hidden" name="pageSize" value="${pageSize}"/>

	<#-- 翻页头信息 -->
	<#assign pageDataStart = pageSize * (pageNo - 1) + 1>
	<#assign pageDataEnd = pageSize * pageNo>

	<#if (recordCount == 0)>
		<#assign pageDataStart = 0>
	</#if>

	<#if (recordCount < (pageSize * pageNo))>
		<#assign pageDataEnd = recordCount>
	</#if>

 <div class="m7-bottom hz-hid">
<#--
		当前：${pageNo}/${((recordCount + pageSize -1)/pageSize)?int}
-->

		<#-- 如果前面页数过多,显示... -->
		<#assign start=1>


<#-- 显示当前页号和它附近的页号 -->
		<#assign end=(pageNo + 1)>
		<#if (end > pageCount)>
			<#assign end=pageCount>
		</#if>

		<#list start..end as i>
		<#if (pageNo==i)>
		<#if pageNo gt 1>
            <a  href="javascript:goPage('${pageNo-1}')" class="perv_page_no" onclick="turnOverPage(${pageNo-1});">上一页</a>
		  </#if>

			<#if (pageCount>pageNo)>
                <a href="javascript:goPage('${pageNo+1}')"  class="next_page" onclick="turnOverPage(${pageNo+1});"> <span></span>下一页</a>
			</#if>
		</#if>

		</#list>


	</div>



	<script language="javascript">
		function turnOverPage(no){
	  		var url = '${toURL}';
	  		if(url.indexOf('func:') == 0)
	  		{
				alert("if");
	  			var fName = url.replace('func:', '');
	  			window[fName](no);
	  		}
	  		else
	  		{
		  		var lh = url.replace(/\(no\)/, no);
		  		window.location.href = encodeURI(encodeURI(lh));
	  		}
		}
		function goPage(pageNo){
			var url="${toURL}?pageNo="+pageNo;
            var pxfs=$("#pxfs").val();
            var pxfs_value=$("#pxfs_value").val();
			var codeType=$("#codeType").val();
            var keywords=$("#keywords").val();
            if(''!==pxfs&&null!=pxfs){
                url+="&pxfs="+pxfs;
            }
            if(''!=pxfs_value&&null!=pxfs_value){
                url+="&pxfs_value="+pxfs_value;
            }
            if(''!=keywords&&null!=keywords){
                url+="&keywords="+keywords;
            }
			if(''!=codeType&&null!=codeType){
				url+="&codeType="+codeType;
			}
            window.location.href = encodeURI(encodeURI(url));
        }
	</script>
 </#macro>
