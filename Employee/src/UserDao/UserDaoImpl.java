package UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entity.Btn;
import Entity.MenudtreeData;
import Entity.layui;
import Entity.menu;
import Entity.role;
import Entity.user;
import util.Basedao;

public class UserDaoImpl extends Basedao implements UserDaos {
	Connection conn = null;
	PreparedStatement pre = null;
	ResultSet rs = null;
	user ur = new user();

	@Override
	public user Login(String loginname, String password) {
		conn = getConnection();
		String sql = "SELECT id FROM user WHERE `loginname`=? AND `password`=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, loginname);
			pre.setString(2, password);
			rs = pre.executeQuery();
			while (rs.next()) {
				ur.setId(rs.getInt(1));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ur;
	}

	@Override
	public List<menu> guan(int id) {
		List<menu> list = new ArrayList<menu>();
		conn = getConnection();
		String sql = "SELECT * FROM `menu` WHERE id IN(SELECT menuid FROM `user_menu` WHERE userid=?)";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			while (rs.next()) {
				menu gon = new menu();
				gon.setId(rs.getInt(1));
				gon.setMenuname(rs.getString(2));
				gon.setButton(rs.getString(3));
				gon.setFatherid(rs.getInt(4));
				gon.setType(rs.getInt(5));
				gon.setUrl(rs.getString(6));
				list.add(gon);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<menu> fuid(int id) {
		List<menu> list = new ArrayList<menu>();
		conn = getConnection();
		String sql = "SELECT * FROM menu WHERE fatherid=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			while (rs.next()) {
				menu gon = new menu();
				gon.setId(rs.getInt(1));
				gon.setMenuname(rs.getString(2));
				gon.setButton(rs.getString(3));
				gon.setFatherid(rs.getInt(4));
				gon.setType(rs.getInt(5));
				gon.setUrl(rs.getString(6));
				list.add(gon);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<user> userseleall() {
		List<user> list = new ArrayList<user>();
		List<Object> param = new ArrayList<Object>();
		// 获得Connection数据库连接
		conn = getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id,username, phone,age,loginname FROM `user`");

		try {
			pre = conn.prepareStatement(sb.toString());
			if (param.size() > 0) {
				for (int i = 0; i < param.size(); i++) {
					pre.setObject(i + 1, param.get(i));
				}
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				user yo = new user();
				yo.setId(rs.getInt(1));
				yo.setUsername(rs.getString(2));
				yo.setPhone(rs.getString(3));
				yo.setAge(rs.getInt(4));
				yo.setLoginname(rs.getString(5));

				list.add(yo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pre, rs);
		}

		return list;
	}

	@Override
	public int usercount() {
		int count = 0;
		List<Object> param = new ArrayList<Object>();
		conn = this.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(1) FROM user ");
		try {
			pre = conn.prepareStatement(sb.toString());
			if (param.size() > 0) {
				for (int i = 0; i < param.size(); i++) {
					pre.setObject(i + 1, param.get(i));
				}
			}

			rs = pre.executeQuery();

			while (rs.next()) {

				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pre, rs);
		}
		return count;
	}

	@Override
	public List<menu> menuseleall() {
		List<menu> list = new ArrayList<menu>();
		List<Object> param = new ArrayList<Object>();
		// 获得Connection数据库连接
		conn = getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id,menuname,fatherid,`type`,button FROM menu");

		try {
			pre = conn.prepareStatement(sb.toString());
			if (param.size() > 0) {
				for (int i = 0; i < param.size(); i++) {
					pre.setObject(i + 1, param.get(i));
				}
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				menu yo = new menu();

				yo.setId(rs.getInt(1));
				yo.setMenuname(rs.getString(2));
				yo.setFatherid(rs.getInt(3));
				yo.setType(rs.getInt(4));
				yo.setButton(rs.getString(5));

				list.add(yo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pre, rs);
		}

		return list;
	}

	@Override
	public int menucount() {
		// TODO Auto-generated method stub
		int count = 0;
		List<Object> param = new ArrayList<Object>();
		conn = this.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(1) FROM menu ");
		try {
			pre = conn.prepareStatement(sb.toString());
			if (param.size() > 0) {
				for (int i = 0; i < param.size(); i++) {
					pre.setObject(i + 1, param.get(i));
				}
			}

			rs = pre.executeQuery();

			while (rs.next()) {

				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pre, rs);
		}
		return count;
	}

	@Override
	public List<user> roleseleall() {
		List<user> list = new ArrayList<user>();
		List<Object> param = new ArrayList<Object>();
		// 获得Connection数据库连接
		conn = getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT `user`.`loginname`,role.id,role.`rolename`,username,age,phone FROM `user`,`role` WHERE `user`.`roleid`=role.`id`");

		try {
			pre = conn.prepareStatement(sb.toString());
			if (param.size() > 0) {
				for (int i = 0; i < param.size(); i++) {
					pre.setObject(i + 1, param.get(i));
				}
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				user yo = new user();

				yo.setLoginname(rs.getString(1));
				yo.setRoleid(rs.getInt(2));
				yo.setRolename(rs.getString(3));
				yo.setUsername(rs.getString(4));
				yo.setAge(rs.getInt(5));
				yo.setPhone(rs.getString(6));
				list.add(yo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pre, rs);
		}

		return list;
	}

	@Override
	public int rolecount() {
		// TODO Auto-generated method stub
		int count = 0;
		List<Object> param = new ArrayList<Object>();
		conn = this.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(1) FROM user");
		try {
			pre = conn.prepareStatement(sb.toString());
			if (param.size() > 0) {
				for (int i = 0; i < param.size(); i++) {
					pre.setObject(i + 1, param.get(i));
				}
			}

			rs = pre.executeQuery();

			while (rs.next()) {

				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pre, rs);
		}
		return count;
	}

	@Override
	public int deleted(int id) {
		conn = this.getConnection();
		String sql = "DELETE FROM user WHERE id=?";
		// 2.提供替换?的object[]
		Object[] obj = { id };
		// 执行新增操作
		return this.getSIDU(sql, obj);
	}

	@Override
	public int updated(user ys) {
		String sql = "UPDATE user SET username=?,phone=?,age=?,loginname=? WHERE id=?";
		// 2.提供替换?的object[]
		Object[] obj = { ys.getUsername(), ys.getPhone(), ys.getAge(), ys.getLoginname(), ys.getId() };
		return this.getSIDU(sql, obj);
	}

	@Override
	public user updateid(int id) {
		conn = this.getConnection();
		user ys = new user();
		String sql = "SELECT * FROM user WHERE id=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setObject(1, id);
			rs = pre.executeQuery();// 无惨方法
			while (rs.next()) {
				ys.setId(rs.getInt(1));
				ys.setUsername(rs.getString(2));
				ys.setPhone(rs.getString(3));
				ys.setAge(rs.getInt(4));
				ys.setLoginname(rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ys;
	}

	@Override
	public int insertd(user ys) {
		conn = this.getConnection();
		String sql = "INSERT INTO user(username,phone,age,loginname,password) VALUES(?,?,?,?,?)";
		// 2.提供替换?的object[]
		Object[] obj = { ys.getUsername(), ys.getPhone(), ys.getAge(), ys.getLoginname(), ys.getPassword() };
		// 执行新增操作
		return this.getSIDU(sql, obj);
	}

	@Override
	public layui<MenudtreeData> allMenu() {
		List<menu> allMenu = menuseleall();
		layui<MenudtreeData> layui = new layui<MenudtreeData>();
		layui.setCode(0);
		layui.setCount(0);
		layui.setMsg("");
		List<MenudtreeData> dataList = new ArrayList<MenudtreeData>();
		for (menu menu : allMenu) {
			MenudtreeData data = new MenudtreeData();
			data.setId(menu.getId());
			data.setParentId(menu.getFatherid());
			data.setTitle(menu.getMenuname());
			data.setCheckArr("0");
			dataList.add(data);
		}
		layui.setData(dataList);
		return layui;
	}

	@Override
	public List<menu> allMenu(int id) {
		conn = this.getConnection();
		List<menu> list = new ArrayList<menu>();
		String sql = "SELECT menuname FROM `menu` WHERE id IN (SELECT `menuid` FROM `menu_role` WHERE `roleid`=?)";
		try {
			pre = conn.prepareStatement(sql);
			pre.setObject(1, id);
			rs = pre.executeQuery();
			while (rs.next()) {
				menu ys = new menu();
				ys.setMenuname(rs.getString(1));
				list.add(ys);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int deleteqx(int id) {
		conn = this.getConnection();
		String sql = "DELETE FROM menu_role WHERE roleid=?";
		// 2.提供替换?的object[]
		Object[] obj = { id };
		// 执行新增操作
		return this.getSIDU(sql, obj);
	}

	@Override
	public int insertqx(int shenid, int quanid) {
		conn = this.getConnection();
		String sql = "INSERT INTO menu_role(roleid,menuid) VALUES(?,?)";
		// 2.提供替换?的object[]
		Object[] obj = { shenid, quanid };
		// 执行新增操作;
		return this.getSIDU(sql, obj);
	}

	@Override
	public List<Btn> selectBtn() {
		conn = this.getConnection();
		List<Btn> list = new ArrayList<Btn>();
		String sql = "SELECT id,btntype FROM `ls_butten`";
		try {
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next()) {
				Btn bb = new Btn();
				bb.setId(rs.getInt(1));
				bb.setBtntype(rs.getString(2));
				list.add(bb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertQx(menu ys) {
		conn = this.getConnection();
		String sql = "INSERT INTO `menu` (menuname,button,fatherid,`type`,url) VALUES (?,?,?,?,?)";
		Object[] obj = { ys.getMenuname(), ys.getButton(), ys.getFatherid(), ys.getType(), ys.getUrl() };

		return this.getSIDU(sql, obj);
	}

	@Override
	public List<menu> seqxList(int type) {
		conn = this.getConnection();
		List<menu> list = new ArrayList<menu>();
		String sql = "SELECT `id`,`menuname`FROM `menu` WHERE `type`=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setObject(1, type);
			rs = pre.executeQuery();// 无惨方法
			while (rs.next()) {
				menu g = new menu();
				g.setId(rs.getInt(1));
				g.setMenuname(rs.getString(2));

				list.add(g);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}
