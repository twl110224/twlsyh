package UserDao;

import java.util.List;

import Entity.Btn;
import Entity.MenudtreeData;
import Entity.layui;


import Entity.menu;
import Entity.role;
import Entity.user;

public interface UserDaos {
	public user Login(String loginname,String password);//登录
	public List<menu> guan(int id);
	public List<menu> fuid(int id);
	
	public List<user> userseleall();
	public int usercount();
	
	public List<menu> menuseleall();
	public int menucount();
	
	public List<user> roleseleall();
	public int rolecount();
	
	
	 //删除
    public int deleted(int id);
    //修改
    public int updated(user ys);
    public user updateid(int id);
    //增加
    public int insertd(user ys);
    
    //查询权限
    public layui<MenudtreeData> allMenu();
    //获取权限id
    public List<menu> allMenu(int id);
    //分配删除
    public int deleteqx(int id);
    //分配增加
    public int insertqx(int shenid,int quanid);
    
    //查询按钮
    public List<Btn>selectBtn();
    //增加权限
    public int insertQx(menu ys);
    //查询权限type
    public List<menu> seqxList(int type);
}
