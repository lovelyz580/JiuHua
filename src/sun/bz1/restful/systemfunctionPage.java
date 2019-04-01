package sun.bz1.restful;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.TBase;
import sun.bz1.entity.systemfunction;
import sun.bz1.service.systemfunctionService;



/**
 * 系统功能控制层
 * 
 *  2018-10-7 
 *           -zz
 */

@Controller
@RequestMapping("/systemfunction")
public class systemfunctionPage {

        private Logger logger=Logger.getLogger(systemfunctionPage.class);
		
		@Autowired
		private systemfunctionService systemfunctionService;
		
		
		
		/**
		 * 查看系统功功能
		 * @param o
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/systemfunctionSelect")
		public LayuiDataTemplet<systemfunction> select(@RequestBody systemfunction systemfunction)
		{
			LayuiDataTemplet<systemfunction> data=new LayuiDataTemplet<systemfunction>();
			
			Integer count=0;
			//先查询所有数据的数量
			count=systemfunctionService.selectCount(systemfunction);
			
			data.setCount(count);
			if(count>0){
				//分页
				if(systemfunction.getPagenumber()!=-1){
					int s=systemfunction.getPagesize();
					int n=systemfunction.getPagenumber();
					systemfunction.setPagenumber((n-1)*s);
				}
				
				//查询出来所有具体数据
				List<systemfunction> system=systemfunctionService.selectSystemFunction(systemfunction);
				data.setData(system);
				data.setMsg("查询成功");
			}else{
				data.setMsg("暂无功能数据");
			}
			
            
			return data;
		}
		
		
	   /**
		 * 新增系统功功能
		 * @param o
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/systemfunctionInsert")
		public LayuiDataTemplet<systemfunction> insert(@RequestBody systemfunction systemfunction)
		{
			LayuiDataTemplet<systemfunction> data=new LayuiDataTemplet<systemfunction>();
			Boolean a=systemfunctionService.insertSystemFunction(systemfunction);
			if(a){
				data.setMsg("新增成功");
			}else{
				data.setMsg("新增失败");
			}
			return data;
		}
		
		/**
		 * 修改系统功功能
		 * @param o
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/systemfunctionUpdata")
		public LayuiDataTemplet<systemfunction> updata(@RequestBody systemfunction systemfunction)
		{
			LayuiDataTemplet<systemfunction> data=new LayuiDataTemplet<systemfunction>();
			Boolean a=systemfunctionService.updataSystemFunction(systemfunction);
			if(a){
				data.setMsg("修改成功");
			}else{
				data.setMsg("修改失败");
			}
			return data;
		}
		
		/**
		 * 删除系统功功能
		 * @param o
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/systemfunctionDelete")
		public LayuiDataTemplet<systemfunction> del(@RequestBody List<Integer> systemfunctionSid)
		{
			LayuiDataTemplet<systemfunction> data=new LayuiDataTemplet<systemfunction>();
			Boolean a=systemfunctionService.delSystemFunction(systemfunctionSid);
			if(a){
				data.setMsg("删除成功");
			}else{
				data.setMsg("删除失败");
			}
			return data;
		}

}
