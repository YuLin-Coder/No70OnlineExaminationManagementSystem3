package cn.itbaizhan.tyut.exam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DBUnitHelper {

	/**
	 * 获取数据库链接
	 * @return
	 */
	public static Connection getConn(){
		Connection conn = null;	
		try {
			
			DbUtils.loadDriver("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/no70_zaixianexam?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8", "root", "123456");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Integer executeUpdate(String sql,Object ...objects){
		
		Connection conn = getConn();
		QueryRunner qr = new QueryRunner();
		Integer rtn = 0;
		try {
			if(objects == null){
				rtn = qr.update(conn, sql);
			}else{
				rtn = qr.update(conn, sql, objects);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return rtn;
	}
	
	public static Integer executeUpdate(String sql){
		return executeUpdate(sql, null);
	}
	
	public static <T> List<T> executeQuery(String sql,Class<T> cls,Object ...objects){
		Connection conn = getConn();
		List<T> list = null;
		try{
			QueryRunner rq = new QueryRunner();
			if(objects == null){
				list = rq.query(conn, sql,new BeanListHandler<T>(cls)); 
			}else{
				list = rq.query(conn, sql,new BeanListHandler<T>(cls),objects); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	public static <T> List<T> executeQuery(String sql,Class<T> cls){
		return executeQuery(sql,cls,null);
	}
	
	/**
	 * 带分页的查询
	 * @param sql SQL语句
	 * @param map SQL参数
	 * @param pc 分页控制对象，需要传递参数：当前第几页（currentindex）,每页显示多少行：(pagesize)
	 * 分页控件显示多少也：showpcount
	 * @return
	 */
	public static <T> Pager<T> execlist(String sql,PageControl pc,Class cls,String pk,Object...object){

		//获取总记录数sql		 
		String sqlcount = "select count(*) as count from ("+sql+") a";
		//获取具体数据的SQL语句
		Integer min = (pc.getCurrentindex()-1)*pc.getPagesize();
		Integer max = pc.getPagesize();
		String sqllist = "select * from ("+sql+") a where a."+pk+" limit "+min+","+max;
		
		Connection conn = getConn();
		Pager<T> pager = new Pager<T>();
		try {
			
			QueryRunner rq = new QueryRunner();
			Object count = rq.query(conn, sqlcount, new ScalarHandler<Object>("count"), object);
			List<T> list = executeQuery(sqllist,cls,object);
			//设置总记录数
			Integer c = 0;
			if(count!=null){
				c=Integer.parseInt(count.toString());
			}
			pc.setRscount(c);
			
			pager.setList(list);
			pc = dealpage(pc);
			pager.setPagectrl(pc);	
			DbUtils.close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}					
		return pager;
	}
	/**
	 * 处理分页参数
	 * @param pc
	 * @return
	 */
	private static PageControl dealpage(PageControl pc){
		//获取总页数
		Integer pagecount = pc.getRscount()/pc.getPagesize();
		if(pc.getRscount()%pc.getPagesize()>0){
			pagecount++;
		}
		pc.setPagecount(pagecount);
		
		//计算最大(最小)显示页数
		Integer showpcount = pc.getShowpcount();//分页一次显示多少页
		Integer maxpage = 0;//当前显示最大页码
		Integer minpage = 0;
		Integer index = pc.getCurrentindex();//当前第几页
		if(pagecount<=showpcount){//当总页数小于等于显示的页数时
			maxpage = pagecount;
			minpage = 1;
		}else{
			Integer buff = showpcount/2; //取中间数。maxpage=index+buff
			buff = index+buff;
			if(buff<=showpcount){
				maxpage = showpcount;
				minpage = 1;
			}else if(buff<pagecount){
				maxpage = buff;
				minpage = maxpage - showpcount + 1;
				
			}else if(buff>=pagecount){
				maxpage = pagecount;
				minpage = maxpage - showpcount + 1;
			}
		}
		pc.setMaxpage(maxpage);	
		pc.setMinpage(minpage);
		return pc;
	}
	/*public static void main(String args[]) throws SQLException
	{
		Connection con=getConn();
		for(int i=0;i<=700;i++){
		String sql="insert into r values(?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, i);
		pstmt.executeUpdate();
		System.out.println("插入成功"+i);
		}
	}*/
}
