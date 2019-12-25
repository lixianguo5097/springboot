package com.lxg.controller;

import com.lxg.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* 用户控制层
* 在浏览器输入 http://localhost:8080/swagger-ui.html 即可查看
* @author LXG
 */
@RestController
@RequestMapping(value = "/api")
@Api(value="用户操作接口")
public class UserController {
	/**
	 * ApiOperation注解来给API增加说明、通过@ApiParam注解来给参数增加说明。
	 * value 是标题,notes是详细说明
	 * @param user 用户
	 * @return 测试map
	 */
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
	@PostMapping("/user")
    public Map<String, Object> insert(@ApiParam(value = "用户详细实体user", required = true)@RequestBody User user) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("respMsg", "新增成功");
		map.put("respData", user);
		map.put("respCode", 200);
		return map;
    }
    
	@ApiOperation(value="更新用户", notes="根据User对象更新用户")
	@PutMapping("/user")
    public Map<String, Object>  update(@ApiParam(value = "用户详细实体user", required = true)@RequestBody User user) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("respMsg", "更新成功");
		map.put("respData", user);
		map.put("respCode", 200);
		return map;
    }
	
	@ApiOperation(value="删除用户", notes="根据id删除用户")
	@DeleteMapping("/user/{id}")
    public Map<String, Object> delete(@ApiParam(value = "用户id", required = true) @PathVariable Integer id)  {
		Map<String, Object> map = new HashMap<>(16);
		map.put("respMsg", "删除成功");
		map.put("respCode", 200);
		map.put("id", id);
		return map;
    }

	@ApiOperation(value="获取用户列表", notes="根据User对象查询用户信息")
    @GetMapping("/user")
    public List<User> findByUser() {
		List<User> list = new ArrayList<>();
		list.add(new User(1L,"张三",18));
		list.add(new User(2L,"李四",20));
        return list;
    }
}