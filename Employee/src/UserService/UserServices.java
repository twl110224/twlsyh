package UserService;

import java.util.List;

import Entity.Btn;
import Entity.MenudtreeData;

import Entity.layui;
import Entity.menu;
import Entity.role;
import Entity.user;

public interface UserServices {
	public user Login(String loginname, String password);// 登录

	public List<menu> guan(int id);

	public List<menu> fuid(int id);

	public layui<user> userseleall();

	public int usercount();

	public layui<menu> menuseleall();

	public int menucount();

	public layui<user> roleseleall();

	public int rolecount();

	// 删除
	public int deleted(int id);

	// 增加
	public boolean insertd(user ys);

	// 修改
	public boolean updated(user ys);

	public user updateid(int id);

	
	// 查询权限
	public layui<MenudtreeData> allMenu();

	// 获取权限id
	public List<menu> allMenuid(int id);

	// 分配增加删除
	public int fenpei(int id, String[] num);

	
	
	// 查询按钮
	public List<Btn> selectBtn();

	// 增加权限
	public boolean insertQx(menu ys);

	// 查询权限type
	public List<menu> seqxList(int type);
}
