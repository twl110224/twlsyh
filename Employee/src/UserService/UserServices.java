package UserService;

import java.util.List;

import Entity.Btn;
import Entity.MenudtreeData;

import Entity.layui;
import Entity.menu;
import Entity.role;
import Entity.user;

public interface UserServices {
	public user Login(String loginname, String password);// ��¼

	public List<menu> guan(int id);

	public List<menu> fuid(int id);

	public layui<user> userseleall();

	public int usercount();

	public layui<menu> menuseleall();

	public int menucount();

	public layui<user> roleseleall();

	public int rolecount();

	// ɾ��
	public int deleted(int id);

	// ����
	public boolean insertd(user ys);

	// �޸�
	public boolean updated(user ys);

	public user updateid(int id);

	
	// ��ѯȨ��
	public layui<MenudtreeData> allMenu();

	// ��ȡȨ��id
	public List<menu> allMenuid(int id);

	// ��������ɾ��
	public int fenpei(int id, String[] num);

	
	
	// ��ѯ��ť
	public List<Btn> selectBtn();

	// ����Ȩ��
	public boolean insertQx(menu ys);

	// ��ѯȨ��type
	public List<menu> seqxList(int type);
}
