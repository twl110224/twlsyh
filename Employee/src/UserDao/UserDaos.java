package UserDao;

import java.util.List;

import Entity.Btn;
import Entity.MenudtreeData;
import Entity.layui;


import Entity.menu;
import Entity.role;
import Entity.user;

public interface UserDaos {
	public user Login(String loginname,String password);//��¼
	public List<menu> guan(int id);
	public List<menu> fuid(int id);
	
	public List<user> userseleall();
	public int usercount();
	
	public List<menu> menuseleall();
	public int menucount();
	
	public List<user> roleseleall();
	public int rolecount();
	
	
	 //ɾ��
    public int deleted(int id);
    //�޸�
    public int updated(user ys);
    public user updateid(int id);
    //����
    public int insertd(user ys);
    
    //��ѯȨ��
    public layui<MenudtreeData> allMenu();
    //��ȡȨ��id
    public List<menu> allMenu(int id);
    //����ɾ��
    public int deleteqx(int id);
    //��������
    public int insertqx(int shenid,int quanid);
    
    //��ѯ��ť
    public List<Btn>selectBtn();
    //����Ȩ��
    public int insertQx(menu ys);
    //��ѯȨ��type
    public List<menu> seqxList(int type);
}
